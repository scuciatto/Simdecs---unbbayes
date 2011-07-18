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

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import unbbayes.controller.IconController;
import unbbayes.prs.Node;
import unbbayes.prs.bn.ProbabilisticNode;
import unbbayes.prs.bn.SingleEntityNetwork;
import unbbayes.prs.bn.TreeVariable;
import unbbayes.util.ArrayMap;
import unbbayes.util.Debug;
import unbbayes.util.ResourceController;

/**
 * @author Mário Henrique Paes Vieira
 */
public class EvidenceTree extends JTree {
	
	/** Serialization runtime version number */
	private static final long serialVersionUID = 0;	
	
	private SingleEntityNetwork net;
	private NumberFormat nf;
	private boolean[] expandedNodes;
	
	//Link DefaultMutableTreeNode <-> Node
	private ArrayMap<Object, Node> objectsMap = new ArrayMap<Object, Node>();
     
	protected IconController iconController = IconController.getInstance();

    private final NetworkWindow netWindow; 
    
	private ResourceBundle resource = ResourceController.RS_GUI; 
	
	//by young
	public String strTextOutputMode;
	public static final String TEXTOUTPUTMODEMODE_USE_NAME		= "UseName";
	public static final String TEXTOUTPUTMODEMODE_USE_DESC		= "UseDescription"; 
	
		
	public EvidenceTree(SingleEntityNetwork sen, final NetworkWindow netWindow) {
		
		this.net = sen;
		this.netWindow = netWindow; 
		strTextOutputMode = TEXTOUTPUTMODEMODE_USE_NAME;
		
		nf = NumberFormat.getInstance(Locale.US);
		nf.setMaximumFractionDigits(2);

		// Set up node icons
		setCellRenderer(new EvidenceTreeCellRenderer());

		// Mouse events for the evidence tree
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				int selRow = getRowForLocation(e.getX(), e.getY());
				if (selRow == -1) {
					return;
				}

				TreePath selPath = getPathForLocation(e.getX(), e.getY());
				DefaultMutableTreeNode node =
					(DefaultMutableTreeNode) selPath.getLastPathComponent();

				if (node.isLeaf()) {
					if (e.getModifiers() == MouseEvent.BUTTON3_MASK) {
						showLikelihood(
							(DefaultMutableTreeNode) node.getParent());
					} else if (
						e.getClickCount() == 2
							&& e.getModifiers() == MouseEvent.BUTTON1_MASK) {
						treeDoubleClick(node);
						//by young
						Node newNode = getNodeMap(node);
						netWindow.getGraphPane().compiled(false, newNode);
					}
				} else {
					if (e.getModifiers() == MouseEvent.BUTTON3_MASK) {
						showLikelihood(node);
					}
					if (e.getClickCount() == 1) {
						Node newNode = getNodeMap(node);
						if (newNode != null) {
							netWindow.getGraphPane().selectNode(newNode);
							//by young
							netWindow.getGraphPane().compiled(false, newNode);
						}
					} else if (e.getClickCount() == 2) {
						DefaultMutableTreeNode root = (DefaultMutableTreeNode)getModel().getRoot();
						int index = root.getIndex(node);
						expandedNodes[index] = ! expandedNodes[index];
						/*
						Node newNode = getNodeMap(node);
						if (newNode != null) {
						  index = net.getNodeIndex(newNode.getName());
						  Debug.println(newNode+" "+index);
						  expandedNodes[index] = ! expandedNodes[index];
						}
						*/
					}
				}
			}
		});
		super.treeDidChange();

	}

	private class EvidenceTreeCellRenderer extends DefaultTreeCellRenderer {

		/** Serialization runtime version number */
		private static final long serialVersionUID = 0;	
		
		private ImageIcon folderSmallIcon = iconController.getFolderSmallIcon();
		private ImageIcon folderSmallDisabledIcon = iconController.getFolderSmallDisabledIcon();
		private ImageIcon yellowBallIcon = iconController.getYellowBallIcon();
		private ImageIcon greenBallIcon = iconController.getGreenBallIcon();

		public Component getTreeCellRendererComponent(
			JTree tree,
			Object value,
			boolean sel,
			boolean expanded,
			boolean leaf,
			int row,
			boolean hasFocus) {
			super.getTreeCellRendererComponent(
				tree,
				value,
				sel,
				expanded,
				leaf,
				row,
				hasFocus);
			if (leaf) {
				DefaultMutableTreeNode parent =
					(DefaultMutableTreeNode) (((DefaultMutableTreeNode) value)
						.getParent());
				Object obj = objectsMap.get((DefaultMutableTreeNode) parent);
				if (obj != null) {
					Node node = (Node) obj;
					if (node.getInformationType() == Node.DESCRIPTION_TYPE) {
						setIcon(yellowBallIcon);
					} else {
						setIcon(greenBallIcon);
					}
				} else {
					setIcon(yellowBallIcon);
				}
			} else {
				Object obj = objectsMap.get((DefaultMutableTreeNode) value);
				if (obj != null) {
					Node node = (Node) obj;
					if (node.getInformationType() == Node.DESCRIPTION_TYPE) {
						setOpenIcon(folderSmallIcon);
						setClosedIcon(folderSmallIcon);
						setIcon(folderSmallIcon);
					} else {
						setOpenIcon(folderSmallDisabledIcon);
						setClosedIcon(folderSmallDisabledIcon);
						setIcon(folderSmallDisabledIcon);
					}
				} else {
					setOpenIcon(folderSmallIcon);
					setClosedIcon(folderSmallIcon);
					setIcon(folderSmallIcon);
				}
			}
			return this;
		}
	}
	
	//by young
	public void setTextOutputMode( String str )
	{
		strTextOutputMode = str;
		updateTree(false);
	}
	
	//by young
	public String getTextOutputMode()
	{
		return strTextOutputMode;
	}

	/**
	 *  Collapses every single nodes from a tree
	 *  
	 */
	public void collapseTree() {
		for (int i = 0; i < getRowCount(); i++) {
			collapseRow(i);
		}

		for (int i = 0; i < expandedNodes.length; i++) {
			expandedNodes[i] = false;
		}
	}

	/**
	 *  Expand all nodes of the tree
	 *
	 */
	public void expandTree() {
		for (int i = 0; i < getRowCount(); i++) {
			expandRow(i);
		}

		for (int i = 0; i < expandedNodes.length; i++) {
			expandedNodes[i] = true;
		}
	}

	private void restoreTree() {
		for (int i = expandedNodes.length-1; i >= 0; i--) {
			if (expandedNodes[i]) {
				expandRow(i);
			} else {
				collapseRow(i);
			}
		}
	}
	
	/**
	 * Reset the evidence tree to its default.
	 */
	public void resetTree() {
		expandedNodes = null;
	}

	/**
	 *  Update the marginals on the tree
	 */
	public void updateTree(boolean reset) {

		if (expandedNodes == null) {
			expandedNodes = new boolean[net.getNodesCopy().size()];
			for (int i = 0; i < expandedNodes.length; i++) {
				expandedNodes[i] = true;
			}
		}

		DefaultMutableTreeNode root =
			(DefaultMutableTreeNode) getModel().getRoot();
		root.removeAllChildren();
		objectsMap.clear();
		DefaultTreeModel model =
			new DefaultTreeModel(
				(DefaultMutableTreeNode) net
					.getHierarchicTree()
					.copyTree()
					.getModel()
					.getRoot());
	
		this.setModel(model);
		
		root = (DefaultMutableTreeNode) getModel().getRoot();
		
		//by young
		root.removeAllChildren();
		
		ArrayList<Node> nodes = net.getNodesCopy();
		int size = nodes.size();
		for (int i = 0; i < size; i++) {
			Node node = (Node) nodes.get(i);
			TreeVariable treeVariable = (TreeVariable) node;
			DefaultMutableTreeNode treeNode = null;
			
			//by young
			if( getTextOutputMode() == TEXTOUTPUTMODEMODE_USE_NAME )
				treeNode = findUserObject(node.getName(), root);
			else
			if( getTextOutputMode() == TEXTOUTPUTMODEMODE_USE_DESC )
				treeNode = findUserObject(node.getDescription(), root);
			
			if (treeNode == null) 
			{
				if( getTextOutputMode() == TEXTOUTPUTMODEMODE_USE_NAME )
					treeNode = new DefaultMutableTreeNode(node.getName());
				else
				if( getTextOutputMode() == TEXTOUTPUTMODEMODE_USE_DESC )
					treeNode = new DefaultMutableTreeNode(node.getDescription());
				
			 
				root.add(treeNode);
			}
			
			objectsMap.put(treeNode, node);
			
			int statesSize = node.getStatesSize();
			
			Debug.println("new tree node "+ treeNode.toString() );
			
			for (int j = 0; j < statesSize; j++) {
				String label;
				if (treeVariable.getType() == Node.PROBABILISTIC_NODE_TYPE) {
					label =
						node.getStateAt(j)
							+ ": "
							+ nf.format(treeVariable.getMarginalAt(j) * 100.0);
				} else {
					label =
						node.getStateAt(j)
							+ ": "
							+ nf.format(treeVariable.getMarginalAt(j));
				}
				
				treeNode.add(new DefaultMutableTreeNode(label));
				
				Debug.println("new tree node sub "+ label );
			}
			 
		}
		restoreTree();
		((DefaultTreeModel) getModel()).reload(root);
		restoreTree();
		
		
		//by young
		netWindow.getGraphPane().compiled(reset, null);
	}
	
	//by young, modified
	public void selectTreeItemByNode(Node n) 
	{
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) getModel().getRoot();
		DefaultMutableTreeNode treeNode = null;
		
		if( getTextOutputMode() == TEXTOUTPUTMODEMODE_USE_NAME )
			treeNode = findUserObject(n.getName(), root);
		else
		if( getTextOutputMode() == TEXTOUTPUTMODEMODE_USE_DESC )
			treeNode = findUserObject(n.getDescription(), root);
		
		if( treeNode != null )
		{
			TreePath tp = new TreePath(treeNode.getPath());
	        expandPath(tp);
			scrollPathToVisible(tp);
			setSelectionPath(tp);
		}
	}
	
	//by young 
	public void selectTreeItemByState(Node node, String stateName) 
	{
		DefaultMutableTreeNode parent = findUserObject(node);
		for (int i = 0; i < parent.getChildCount(); i++) 
		{
			DefaultMutableTreeNode auxNode = (DefaultMutableTreeNode) parent.getChildAt(i);
			
			int n = parent.getIndex(auxNode);
			if( node.getStateAt(n) == stateName )
			{
				treeDoubleClick( auxNode );
				return;
			}			
		}
	}
	

	/**
	 * Modify the numbers format
	 *
	 * @param local local of number's format.
	 */
	public void setNumberFormat(Locale local) {
		nf = NumberFormat.getInstance(local);
	}

	private DefaultMutableTreeNode findUserObject(Node node)
	{
		String name = null;
		if( getTextOutputMode() == TEXTOUTPUTMODEMODE_USE_NAME )
			name = node.getName();
		else
		if( getTextOutputMode() == TEXTOUTPUTMODEMODE_USE_DESC )
			name = node.getDescription();
		
		return findUserObject( name );
	}
	
	private DefaultMutableTreeNode findUserObject(String treeNode)
	{
		DefaultMutableTreeNode root = (DefaultMutableTreeNode) getModel().getRoot();
		return findUserObject( treeNode, root );
	}
	
	private DefaultMutableTreeNode findUserObject(
		String treeNode,
		DefaultMutableTreeNode root) {
		Enumeration e = root.breadthFirstEnumeration();
		while (e.hasMoreElements()) {
			DefaultMutableTreeNode node =
				(DefaultMutableTreeNode) e.nextElement();
			if (node.getUserObject().toString().equals(treeNode)) {
				return node;
			}
		}
		return null;
	}

	/**
	 * Add a evidence
	 *
	 * @param  treeNode  path to the state be setted 100%
	 * @see             TreePath
	 */
	private void treeDoubleClick(DefaultMutableTreeNode treeNode) {
		DefaultMutableTreeNode parent =
			(DefaultMutableTreeNode) ((treeNode).getParent());
		Object obj = objectsMap.get((DefaultMutableTreeNode) parent);
		if (obj != null) {
			TreeVariable node = (TreeVariable) obj;

			//Only propage description nodes
			if (node.getInformationType() == Node.DESCRIPTION_TYPE) {
				for (int i = 0; i < parent.getChildCount(); i++) {
					DefaultMutableTreeNode auxNode =
						(DefaultMutableTreeNode) parent.getChildAt(i);
					auxNode.setUserObject(node.getStateAt(i) + ": 0");
				}

				if (node.getType() == Node.PROBABILISTIC_NODE_TYPE) {
					treeNode.setUserObject(						
						node.getStateAt(parent.getIndex(treeNode)) + ": 100");
				} else {
					treeNode.setUserObject(
						node.getStateAt(parent.getIndex(treeNode)) + ": **");
				}
				node.addFinding(parent.getIndex(treeNode));
				((DefaultTreeModel) getModel()).reload(parent);
				
				//by young
				netWindow.getGraphPane().selectNode(node);
			}
		}
	}

	/**
	 *  Open a new modal window for input the values for the likelihood
	 *
	 * @param  node 
	 */
	private void showLikelihood(DefaultMutableTreeNode node) {
		
		ProbabilisticNode auxProbNode = (ProbabilisticNode) objectsMap.get(node);
		
		if (   (auxProbNode != null)
			&& (auxProbNode.getInformationType() == Node.DESCRIPTION_TYPE)) {
			
			//Build the panel
			int i;
			JPanel panel = new JPanel();
			JTable table = new JTable(auxProbNode.getStatesSize(), 2); 
			for (i = 0; i < auxProbNode.getStatesSize(); i++) {
				table.setValueAt(auxProbNode.getStateAt(i), i, 0);
				table.setValueAt("100", i, 1);
			}
			JLabel label = new JLabel(auxProbNode.toString());
			panel.add(label);
			panel.add(table);
			
			//Ask the user the confirmation
			if (JOptionPane.showConfirmDialog(
					this.netWindow.getDesktopPane(),
					panel,
					resource.getString("likelihoodName"),
					JOptionPane.OK_CANCEL_OPTION)
				    == 
				    JOptionPane.OK_OPTION) {
				
		    //Get the original probabilities values
				DefaultMutableTreeNode auxNode;

				float[] stateProbabilities = new float[auxProbNode.getStatesSize()];

				try {
					for (i = 0; i < auxProbNode.getStatesSize(); i++) {
						stateProbabilities[i] = 
							nf.parse((String) table.getValueAt(i, 1)).floatValue();
					}
				} catch (ParseException e) {
					System.err.println(e.getMessage());
					return;
				}

			//Get the total probability 
				float totalProbability = 0; 
				for (i = 0; i < auxProbNode.getStatesSize(); i++) {
					totalProbability += stateProbabilities[i];
				}

				if (totalProbability == 0.0) {
					System.err.println("likelihoodException");
					return;
				}

			//Normalize the probabilities values
				// Also verify the state that has the highest probability
				for (i = 0; i < auxProbNode.getStatesSize(); i++) {
					stateProbabilities[i] = stateProbabilities[i] / totalProbability;
				}

//				auxProbNode.addFinding(1);
				auxProbNode.addLikeliHood(stateProbabilities);
				
			//Reset text with the values of probabilities 
				String str;
				for (i = 0; i < node.getChildCount(); i++) {
					auxNode = (DefaultMutableTreeNode) node.getChildAt(i);
					str = (String) auxNode.getUserObject();
					auxNode.setUserObject(
							str.substring(0, str.lastIndexOf(':') + 1)
							+ nf.format(stateProbabilities[i] * 100));
				}
				
				((DefaultTreeModel) getModel()).reload(node);
			
			}
		}
	}

	public Node getNodeMap(DefaultMutableTreeNode node) {
		Object obj = objectsMap.get(node);
		if (obj != null) {
			return (Node) obj;
		}
		return null;
	}
}