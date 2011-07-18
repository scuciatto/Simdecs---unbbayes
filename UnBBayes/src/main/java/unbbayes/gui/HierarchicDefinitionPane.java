/*
 *  UnBBayes
 *  Copyright (C) 2002, 2008 Universidade de Brasilia - http://www.unb.br
 *
 *  This file is part of UnBBayes.
 *
 *  UnBBayes is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  UnBBayes is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with UnBBayes.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package unbbayes.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import unbbayes.controller.IconController;
import unbbayes.prs.Node;
import unbbayes.prs.bn.SingleEntityNetwork;
import unbbayes.util.Debug;


/**
 * @author Mário Henrique Paes Vieira (mariohpv@bol.com.br)
 * @version $1.0 $ (04/11/2002)
 */

public class HierarchicDefinitionPane extends JPanel
{
  /** Serialization runtime version number */
  private static final long serialVersionUID = 0;	
	
  private JSplitPane centerPanel;
  private JScrollPane descriptionScrollPane;
  private JScrollPane explanationScrollPane;
  private HierarchicTree hierarchicTree;
  private DefaultTreeModel model;
  private SingleEntityNetwork net;
  private HierarchicTree explanationTree;
  private NetworkWindow netWindow;
  private JPanel bottomPanel;
  private JPanel topPanel;
  private JButton deleteButton;
  private JButton renameButton;
  private JButton addFolderButton;
  private JToolBar jToolBar;
  private JButton expand;
  private JButton edit;
  private JButton collapse;
  private JLabel statusBar;
  private DefaultMutableTreeNode selectedNode;
  protected IconController iconController = IconController.getInstance();

  /** Load resource file from this package */
  private static ResourceBundle resource = unbbayes.util.ResourceController.newInstance().getBundle(
		  unbbayes.gui.resources.GuiResources.class.getName());

  public HierarchicDefinitionPane(SingleEntityNetwork net, NetworkWindow netWindow)
  {
    super();
    this.net = net;
    this.netWindow = netWindow;
    this.setLayout(new BorderLayout());

    jToolBar       = new JToolBar();
    jToolBar.setBorder(null);
    jToolBar.setFloatable(false);
    topPanel       = new JPanel(new GridLayout(0,1));
    centerPanel    = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
    bottomPanel    = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 1));
    statusBar      = new JLabel("Definicao da hierarquia");
    hierarchicTree = net.getHierarchicTree();

    //cria botoes que serao usados nodeList toolbars
    deleteButton        = new JButton(iconController.getDeleteFolderIcon());
    renameButton        = new JButton(iconController.getRenameFolderIcon());
    addFolderButton     = new JButton(iconController.getAddFolderIcon());
    expand              = new JButton(iconController.getExpandIcon());
    edit                = new JButton(iconController.getEditIcon());
    collapse            = new JButton(iconController.getColapseIcon());

    //seta tooltip para esses botoes
    deleteButton.setToolTipText("Delete Folder");
    renameButton.setToolTipText("Rename Folder");
    addFolderButton.setToolTipText("Add Folder");
    expand.setToolTipText(resource.getString("expandToolTip"));
    edit.setToolTipText(resource.getString("editToolTip"));
    collapse.setToolTipText(resource.getString("collapseToolTip"));

    deleteButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        deleteButton_actionPerformed(e);
      }
    });

    renameButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        renameButton_actionPerformed(e);
      }
    });

    addFolderButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        addFolderButton_actionPerformed(e);
      }
    });

    expand.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        hierarchicTree.expandTree();
      }
    });

    edit.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        edit_actionPerformed(e);
      }
    });

    collapse.addActionListener(new java.awt.event.ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        hierarchicTree.collapseTree();
      }
    });

    //trata os eventos de mouse para a arvore de hierarquia
    hierarchicTree.addMouseListener(new MouseAdapter()
    {
      private int oldRow = -1;

      public void mousePressed(MouseEvent e)
      {
        int selRow = hierarchicTree.getRowForLocation(e.getX(), e.getY());
        explanationTree.clearSelection();
        if (selRow == -1)
        {
          return;
        }
        else
        {
          if ((oldRow != -1) && (selRow == oldRow))
          {
            hierarchicTree.clearSelection();
            oldRow = -1;
          }
          else
          {
            oldRow = selRow;
          }
        }
      }
    });

    //coloca botoes no toolbar e edte no painel principal
    jToolBar.add(expand);
    jToolBar.add(collapse);

    jToolBar.addSeparator();

    jToolBar.add(addFolderButton, null);

    jToolBar.addSeparator();

    jToolBar.add(renameButton, null);
    jToolBar.add(deleteButton, null);

    jToolBar.addSeparator();

    jToolBar.add(edit, null);
    topPanel.add(jToolBar, BorderLayout.CENTER);

    //adiciona panels ao centerPanel
    descriptionScrollPane = new JScrollPane();
    explanationScrollPane = new JScrollPane();
    centerPanel.add(descriptionScrollPane,JSplitPane.TOP);
    centerPanel.add(explanationScrollPane,JSplitPane.BOTTOM);

    // Definicao da arvore de explanacao
    DefaultMutableTreeNode root = new DefaultMutableTreeNode("root");
    DefaultTreeModel explanationModel = new DefaultTreeModel(root);
    explanationTree = new HierarchicTree(explanationModel);

    explanationScrollPane.getViewport().add(explanationTree, null);

    //trata os eventos de mouse para a arvore de explanacao
    explanationTree.addMouseListener(new MouseAdapter()
    {
      private int oldRow = -1;

      public void mousePressed(MouseEvent e)
      {
        int selRow = explanationTree.getRowForLocation(e.getX(), e.getY());
        hierarchicTree.clearSelection();
        if (selRow == -1)
        {
          return;
        }
        else
        {
          if ((oldRow != -1) && (selRow == oldRow))
          {
            explanationTree.clearSelection();
            oldRow = -1;
          }
          else
          {
            oldRow = selRow;
          }
        }
      }

    });

    // adiciona o statusBar ao bottomPanel
    bottomPanel.add(statusBar);

    //adiciona containers para o contentPane
    this.add(topPanel, BorderLayout.NORTH);
    this.add(centerPanel, BorderLayout.CENTER);
    this.add(bottomPanel, BorderLayout.SOUTH);
    setVisible(true);

    updateExplanationTree();
  }

  public void updateExplanationTree()
  {
    this.hierarchicTree = net.getHierarchicTree();

    hierarchicTree.setProbabilisticNetwork(net,HierarchicTree.DESCRIPTION_TYPE);
    model = (DefaultTreeModel)hierarchicTree.getModel();
    descriptionScrollPane.getViewport().removeAll();
    descriptionScrollPane.getViewport().add(hierarchicTree, null);
    hierarchicTree.expandTree();

    explanationTree.setProbabilisticNetwork(net,HierarchicTree.EXPLANATION_TYPE);
    explanationScrollPane.getViewport().removeAll();
    explanationScrollPane.getViewport().add(explanationTree, null);

    centerPanel.setDividerLocation(0.5);
  }

  private void deleteButton_actionPerformed(ActionEvent e)
  {
    selectedNode = (DefaultMutableTreeNode)hierarchicTree.getLastSelectedPathComponent();
    if (selectedNode != null)
    {
      Enumeration enumeration = selectedNode.breadthFirstEnumeration();
      while (enumeration.hasMoreElements())
      {
        DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode)enumeration.nextElement();
        Debug.println(treeNode.toString());
        Node node = hierarchicTree.getNodeInformation(treeNode);
        if (node == null)
        {
          model.removeNodeFromParent(selectedNode);
        }
        else
        {
          node.setInformationType(Node.EXPLANATION_TYPE);
        }
      }
      updateExplanationTree();
    }
  }

  private void renameButton_actionPerformed(ActionEvent e)
  {
    selectedNode = (DefaultMutableTreeNode)hierarchicTree.getLastSelectedPathComponent();
    if (selectedNode != null)
    {
      disableButtons();
      String newNodeName = JOptionPane.showInternalInputDialog(this,"","New Folder Name",JOptionPane.QUESTION_MESSAGE);
      enableButtons();
      Node node = hierarchicTree.getNodeInformation(selectedNode);
      if ((node != null)&&(newNodeName != null)&&(!newNodeName.equals("")))
      {
        node.setDescription(newNodeName);
        selectedNode.setUserObject(newNodeName);
        model.reload(selectedNode);
      }
      else if ((newNodeName != null)&&(!newNodeName.equals("")))
      {
        selectedNode.setUserObject(newNodeName);
        model.reload(selectedNode);
      }
    }
    selectedNode = (DefaultMutableTreeNode)explanationTree.getLastSelectedPathComponent();
    if (selectedNode != null)
    {
      disableButtons();
      String newNodeName = JOptionPane.showInternalInputDialog(this,"","New Folder Name",JOptionPane.QUESTION_MESSAGE);
      enableButtons();
      Node node = explanationTree.getNodeInformation(selectedNode);
      if ((node != null)&&(newNodeName != null)&&(!newNodeName.equals("")))
      {
        node.setDescription(newNodeName);
        selectedNode.setUserObject(newNodeName);
        model.reload((TreeNode)model.getRoot());
      }
    }
  }

  private void addFolderButton_actionPerformed(ActionEvent e)
  {
    selectedNode = (DefaultMutableTreeNode)hierarchicTree.getLastSelectedPathComponent();
    Node node = hierarchicTree.getNodeInformation(selectedNode);
    if (node == null)
    {
      if (selectedNode == null)
      {
        disableButtons();
        String newNodeName = JOptionPane.showInternalInputDialog(this,"","Add Top Folder",JOptionPane.QUESTION_MESSAGE);
        enableButtons();
        if ((newNodeName != null)&&(newNodeName != null)&&(!newNodeName.equals("")))
        {
          DefaultMutableTreeNode root = ((DefaultMutableTreeNode)model.getRoot());
          insertNewNode(root,newNodeName);
        }
      }
      else
      {
        disableButtons();
        String newNodeName = JOptionPane.showInternalInputDialog(this,"","Add Child Folder",JOptionPane.QUESTION_MESSAGE);
        enableButtons();
        if ((newNodeName != null)&&(newNodeName != null)&&(!newNodeName.equals("")))
        {
          insertNewNode(selectedNode,newNodeName);
        }
      }
    }
  }

  private void disableButtons()
  {
    deleteButton.setEnabled(false);
    renameButton.setEnabled(false);
    addFolderButton.setEnabled(false);
    expand.setEnabled(false);
    edit.setEnabled(false);
    collapse.setEnabled(false);
  }

  private void enableButtons()
  {
    deleteButton.setEnabled(true);
    renameButton.setEnabled(true);
    addFolderButton.setEnabled(true);
    expand.setEnabled(true);
    edit.setEnabled(true);
    collapse.setEnabled(true);
  }

  private void insertNewNode(DefaultMutableTreeNode node,String nodeName)
  {
    DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(nodeName);
    model.insertNodeInto(newNode,node,node.getChildCount());
    showNewNode(newNode);
  }

  private void showNewNode(DefaultMutableTreeNode newNode)
  {   TreeNode[] nodes = model.getPathToRoot(newNode);
      TreePath path = new TreePath(nodes);
      hierarchicTree.scrollPathToVisible(path);
  }

  private void edit_actionPerformed(ActionEvent e)
  {   netWindow.changeToPNEditionPane();
  }
}