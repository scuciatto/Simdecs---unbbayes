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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;

import unbbayes.controller.IconController;
import unbbayes.controller.NetworkController;


/**
 * <p>Title: UnBBayes</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2001</p>
 * <p>Company: UnB</p>
 * @author Rommel Novaes Carvalho, Michael Onishi
 * @version 1.0
 */

public class EditNet extends JPanel {

	/** Serialization runtime version number */
	private static final long serialVersionUID = 0;		
	
    private final NetworkWindow netWindow;

    private final NetworkController controller;
    private final IconController iconController = IconController.getInstance();
    private final JSplitPane centerPanel;

    private final JPanel topPanel;
    private final JToolBar jtbEdition;

    private final JButton compile;
    private final JButton arc;
    private final JButton printNet;
    private final JButton previewNet;
    private final JButton saveNetImage;

	private JButton simdecsBt;

    /** Load resource file from this package */
    private static ResourceBundle resource = unbbayes.util.ResourceController.newInstance().getBundle(
    		unbbayes.gui.resources.GuiResources.class.getName());

    public EditNet(NetworkWindow _netWindow,
                            NetworkController _controller) {
        super();
        this.netWindow     = _netWindow;
        this.controller    = _controller;
        this.setLayout(new BorderLayout());

        topPanel    = new JPanel(new GridLayout(0,1));
        jtbEdition  = new JToolBar();
        centerPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        
        // Create buttons used by nodeList toolbars

        compile           = new JButton(iconController.getCompileIcon());
        arc               = new JButton(iconController.getEdgeIcon());
        printNet          = new JButton(iconController.getPrintNetIcon());
        previewNet        = new JButton(iconController.getPrintPreviewNetIcon());
        saveNetImage      = new JButton(iconController.getSaveNetIcon());
        
        // Set tooltip for those buttons

        compile.setToolTipText(resource.getString("compileToolTip"));
        arc.setToolTipText(resource.getString("arcToolTip"));
        printNet.setToolTipText(resource.getString("printNetToolTip"));
        previewNet.setToolTipText(resource.getString("previewNetToolTip"));
        saveNetImage.setToolTipText(resource.getString("saveNetImageToolTip"));

        // By clicking the compile button, calls compilation method of the network and
        // updates the toolbars

        compile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
            	System.out.println("i came to edit net \n");
                if (! controller.compileNetwork()) {
                    return;
                }
                netWindow.changeToPNCompilationPane();
            }
        });

        // by clicking the button arc, we set those boolean variables and states of the buttons.
        
        arc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                //netWindow.getIGraph().setbSelect(false);
                //netWindow.getIGraph().setbArc(true);
            	netWindow.getGraphPane().setAction(GraphAction.CREATE_EDGE);
            }
        });

        // action para imprimir a rede
        printNet.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                controller.printNet(netWindow.getGraphPane(), controller.calculateNetRectangle());
            }
        });

        // action para visualizar a rede.
        previewNet.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                controller.previewPrintNet(netWindow.getGraphPane(), controller.calculateNetRectangle());
            }
        });


        saveNetImage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.saveNetImage();
            }
        });

        // Puts buttons and controllers of look-and-feel into toolbar and this is for topPanel.
        
        jtbEdition.add(printNet);
        jtbEdition.add(previewNet);
        jtbEdition.add(saveNetImage);

        jtbEdition.addSeparator();
        jtbEdition.addSeparator();
        
        

        jtbEdition.add(arc);
        jtbEdition.add(compile);
        
        topPanel.add(jtbEdition);
        
        
        

        //setar os tamanho de cada jsp(tabela e graph) para os seus PreferredSizes
        centerPanel.resetToPreferredSizes();

        //adiciona containers para o contentPane
        this.add(topPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        setVisible(true);

    }

    /**
     *  Retorna o painel do centro onde fica o graph e a table.
     *
     *@return    retorna o centerPanel (<code>JSplitPane</code>)
     *@see       JSplitPane
     */
    public JSplitPane getCenterPanel() {
      return this.centerPanel;
    }

    public JButton getArc() {
        return this.arc;
    }

    public JButton getCompile() {
        return this.compile;
    }

    public JButton getPreviewNet() {
        return this.previewNet;
    }

    public JButton getPrintNet() {
        return this.printNet;
    }

    public JButton getSaveNetImage() {
        return this.saveNetImage;
    }
}