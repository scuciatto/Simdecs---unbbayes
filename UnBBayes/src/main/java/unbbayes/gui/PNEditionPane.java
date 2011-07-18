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
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.EventObject;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import unbbayes.controller.CompilationThread;
import unbbayes.controller.IconController;
import unbbayes.controller.NetworkController;
import unbbayes.gui.table.extension.IProbabilityFunctionPanelBuilder;
import unbbayes.gui.util.SplitToggleButton;
import unbbayes.prs.Node;
import unbbayes.prs.bn.IRandomVariable;
import unbbayes.prs.bn.ProbabilisticTable;
import unbbayes.prs.bn.cpt.ITableFunction;
import unbbayes.prs.bn.cpt.impl.FillUniformTableFunction;
import unbbayes.prs.bn.cpt.impl.NormalizeTableFunction;
import unbbayes.prs.bn.cpt.impl.UniformTableFunction;
import unbbayes.util.Debug;
import unbbayes.util.extension.dto.INodeClassDataTransferObject;
import unbbayes.util.extension.manager.CoreCPFPluginManager;
import unbbayes.util.extension.manager.CorePluginNodeManager;
import unbbayes.util.extension.manager.UnBBayesPluginContextHolder;


/**
 * <p>Title: UnBBayes</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2001</p>
 * <p>Company: UnB</p>
 * @author Rommel Novaes Carvalho, Michael Onishi
 * @version 1.0
 */

public class PNEditionPane extends JPanel {

	/** Serialization runtime version number */
	private static final long serialVersionUID = 0;		
	
    private final NetworkWindow netWindow;

    private GlobalOptionsDialog go = null;
    private JTable table;
    private final JTextField txtName;
    private final JTextField txtDescription;
    private final NetworkController controller;
    private final JScrollPane jspTable;
    private final JSplitPane centerPanel;
    private Node tableOwner;
    private final JLabel status;
    private final JPanel bottomPanel;

    private final JPanel topPanel;
    private final JToolBar jtbState;
    private final JToolBar jtbEdition;

    private final JLabel lblName;
    private final JLabel lblDescription;

    private final JButton btnEvaluate;
    
    private final JButton btnCompile;
    private final JButton btnAddState;
    private final JButton btnRemoveState;
    
    private final ToolBarEdition tbEdition; 
    
    private final JButton btnPrintNet;
    private final JButton btnPrintTable;
    private final JButton btnPreviewNet;
    private final JButton btnPreviewTable;
    private final JButton btnSaveNetImage;
    private final JButton btnSaveTableImage;
    private final JButton btnGlobalOption;
    private final JButton btnHierarchy;
    private final Pattern wordPattern = Pattern.compile("[a-zA-Z_0-9(),]*");
    private final Pattern descriptionPattern = Pattern.compile("[ a-zA-Z_0-9áéíóúãõçâêîôûüà(),]*");
    private Matcher matcher;

    private final IconController iconController = IconController.getInstance();
    
    /** This is a pane to edit conditional probability functions */
    private JComponent cpfPane;
    

	/** Load resource file from this package */
  	private static ResourceBundle resource = unbbayes.util.ResourceController.newInstance().getBundle(
  			unbbayes.gui.resources.GuiResources.class.getName());

    public PNEditionPane(NetworkWindow _netWindow,
                            NetworkController _controller) {
        super();
        this.netWindow     = _netWindow;
        this.controller    = _controller;
        this.setLayout(new BorderLayout());

        table       = new JTable();
        jspTable    = new JScrollPane(table);
        topPanel    = new JPanel(new GridLayout(0,1));
        jtbState    = new JToolBar();
        jtbEdition  = new JToolBar();
        centerPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 1));
        status      = new JLabel(resource.getString("statusReadyLabel"));

        //criar labels e textfields que ser�o usados no jtbState
        lblName       = new JLabel(resource.getString("nameLabel") + " ");
        lblDescription = new JLabel(resource.getString("descriptionLabel") + " ");
        txtName           = new JTextField(10);
        txtDescription     = new JTextField(15);

        // Create buttons that are going to be used in the nodeList tool bar
        btnEvaluate           = new JButton(iconController.getEvaluateIcon());
        btnCompile           = new JButton(iconController.getCompileIcon());
        btnAddState              = new JButton(iconController.getMoreIcon());
        btnRemoveState              = new JButton(iconController.getLessIcon());
        btnPrintNet          = new JButton(iconController.getPrintNetIcon());
        btnPrintTable        = new JButton(iconController.getPrintTableIcon());
        btnPreviewNet        = new JButton(iconController.getPrintPreviewNetIcon());
        btnPreviewTable      = new JButton(iconController.getPrintPreviewTableIcon());
        btnSaveNetImage      = new JButton(iconController.getSaveNetIcon());
        btnSaveTableImage    = new JButton(iconController.getSaveTableIcon());
        btnGlobalOption      = new JButton(iconController.getGlobalOptionIcon());
        btnHierarchy         = new JButton(iconController.getHierarchyIcon());

        // Set tool tip for the following buttons
        btnEvaluate.setToolTipText(resource.getString("evaluateToolTip"));
        btnCompile.setToolTipText(resource.getString("compileToolTip"));
        btnAddState.setToolTipText(resource.getString("moreToolTip"));
        btnRemoveState.setToolTipText(resource.getString("lessToolTip"));
        btnPrintNet.setToolTipText(resource.getString("printNetToolTip"));
        btnPrintTable.setToolTipText(resource.getString("printTableToolTip"));
        btnPreviewNet.setToolTipText(resource.getString("previewNetToolTip"));
        btnPreviewTable.setToolTipText(resource.getString("previewTableToolTip"));
        btnSaveNetImage.setToolTipText(resource.getString("saveNetImageToolTip"));
        btnSaveTableImage.setToolTipText(resource.getString("saveTableImageToolTip"));
        btnGlobalOption.setToolTipText(resource.getString("globalOptionTitle"));
        btnHierarchy.setToolTipText(resource.getString("hierarchyToolTip"));

        //ao clicar no botao btnGlobalOption, mostra-se o menu para escolha das opcoes
        btnGlobalOption.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                setCursor(new Cursor(Cursor.WAIT_CURSOR));
                if (go == null) {
                	go = new GlobalOptionsDialog(controller);
                }
                go.setVisible(true);
                netWindow.getGraphPane().update();
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        //ao clicar no botao btnHierarchy, chama-se a tela para definicao de hierarquia
        btnHierarchy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                netWindow.changeToHierarchy();
            }
        });

        //Compile the network when this button is pressed
        btnCompile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
            	
            	new CompilationThread(controller,netWindow);
               
            }
        });
        
        // Call the evaluate method in the controller
        btnEvaluate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                controller.evaluateNetwork();
            }
        });

        // listener responsible for updating the node's name.
        txtName.addKeyListener(new KeyAdapter() {
          public void keyPressed(KeyEvent e) {
            Object selected = netWindow.getGraphPane().getSelected();
            if (selected instanceof Node) {
              Node nodeAux = (Node)selected;
              if ((e.getKeyCode() == KeyEvent.VK_ENTER) && (txtName.getText().length()>0)) {
                try {
                    String name = txtName.getText(0,txtName.getText().length());
                    matcher = wordPattern.matcher(name);
                    if (matcher.matches()) {
                      nodeAux.setName(name);
                    //by young3
                      netWindow.getGraphPane().updateSelectedNode();
                    //  repaint();
                    }  else {
                        JOptionPane.showMessageDialog(netWindow, resource.getString("nameError"), resource.getString("nameException"), JOptionPane.ERROR_MESSAGE);
                        txtName.selectAll();
                    }
                }
                catch (javax.swing.text.BadLocationException ble) {
                    System.out.println(ble.getMessage());
                }
              }
            }
          }
        });


        // listener responsavel pela atualizacao do texo da descricao do no
        txtDescription.addKeyListener(new KeyAdapter() {
          public void keyPressed(KeyEvent e) {
            Object selected = netWindow.getGraphPane().getSelected();
            if (selected instanceof Node)
            {
              Node nodeAux = (Node)selected;
              if ((e.getKeyCode() == KeyEvent.VK_ENTER) && (txtDescription.getText().length()>0)) {
                try {
                    String name = txtDescription.getText(0,txtDescription.getText().length());
                    matcher = descriptionPattern.matcher(name);
                    if (matcher.matches()) {
                      nodeAux.setDescription(name);
                      //by young3
                      netWindow.getGraphPane().updateSelectedNode();
                      repaint();
                    } else {
                        JOptionPane.showMessageDialog(netWindow, resource.getString("descriptionError"), resource.getString("nameException"), JOptionPane.ERROR_MESSAGE);
                        txtDescription.selectAll();
                    }
                }
                catch (javax.swing.text.BadLocationException ble) {
                    System.out.println(ble.getMessage());
                }
              }
            }
          }
        });

        //ao clicar no botao btnRemoveState, chama-se o metodo removerEstado do controller
        //para que esse remova um estado do no
        btnRemoveState.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent ae) {
            if (netWindow.getGraphPane().getSelected() instanceof Node) {
               controller.removeState((Node)netWindow.getGraphPane().getSelected());
            }
          }
        });

        //ao clicar no botao btnRemoveState, chama-se o metodo inserirEstado do controller
        //para que esse insira um novo estado no no
        btnAddState.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent ae) {
            if (netWindow.getGraphPane().getSelected() instanceof Node) {
               controller.insertState((Node)netWindow.getGraphPane().getSelected());
            }
          }
        });

        // action para imprimir a rede
        btnPrintNet.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                controller.printNet(netWindow.getGraphPane(), controller.calculateNetRectangle());
            }
        });

        // action para imprimir a tabela
        btnPrintTable.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                controller.printTable();
            }
        });

        // action para visualizar a tabela
        btnPreviewTable.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                controller.previewPrintTable();
            }
        });

        // action para visualizar a rede.
        btnPreviewNet.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                controller.previewPrintNet(netWindow.getGraphPane(), controller.calculateNetRectangle());
            }
        });


        btnSaveNetImage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.saveNetImage();
            }
        });


        btnSaveTableImage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controller.saveTableImage();
            }
        });

        //colocar botoes e controladores do look-and-feel no toolbar e esse no topPanel
        jtbEdition.add(btnPrintNet);
        jtbEdition.add(btnPreviewNet);
        jtbEdition.add(btnSaveNetImage);
        jtbEdition.add(btnPrintTable);
        jtbEdition.add(btnPreviewTable);
        jtbEdition.add(btnSaveTableImage);

        jtbEdition.addSeparator();

        tbEdition = new ToolBarEdition(); 
        jtbEdition.add(tbEdition); 
        
        jtbEdition.addSeparator(); 
        
        jtbEdition.add(btnCompile);
        jtbEdition.add(btnEvaluate);

        jtbEdition.addSeparator();

        jtbEdition.add(btnGlobalOption);
        jtbEdition.add(btnHierarchy);

        topPanel.add(jtbEdition);

        //colocar bot�es, labels e textfields no toolbar e coloc�-lo no topPanel
        jtbState.add(lblName);
        jtbState.add(txtName);

        jtbState.addSeparator();
        jtbState.addSeparator();

        jtbState.add(btnAddState);
        jtbState.add(btnRemoveState);

        jtbState.addSeparator();
        jtbState.addSeparator();

        jtbState.add(lblDescription);
        jtbState.add(txtDescription);

        topPanel.add(jtbState);

        //setar o preferred size do jspTable para ser usado pelo SplitPanel
        jspTable.setPreferredSize(new Dimension(150,50));

        //setar o auto resize off para que a tabela fique do tamanho ideal
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        //adicionar tela da tabela(JScrollPane) da tabela de estados para o painel do centro
        centerPanel.setTopComponent(jspTable);

        //setar o tamanho do divisor entre o jspGraph(vem do NetWindow) e jspTable
        centerPanel.setDividerSize(7);

        //setar os tamanho de cada jsp(tabela e graph) para os seus PreferredSizes
        centerPanel.resetToPreferredSizes();

        bottomPanel.add(status);

        //adiciona containers para o contentPane
        this.add(topPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
        setVisible(true);

    }

    /**
     *  Retorna a tabela de probabilidades.
     *
     *@return    retorna a table (<code>JTable</code>)
     *@see       JTable
     */
    public JTable getTable() {
        return table;
    }

    /**
     *  Retorna o text field da descricao do no.
     *
     *@return    retorna a txtDescricao (<code>JTextField</code>)
     *@see       JTextField
     */
    public JTextField getTxtDescription() {
      return this.txtDescription;
    }

    public JTextField getTxtName() {
      return this.txtName;
    }
    
    public void setDistributionPane(JPanel distributionPane) {
    	// the center panel was forced to be a scroll pane since the edition pane may be larger than monitor...
    	JScrollPane scrollPane = new JScrollPane(distributionPane);
    	this.centerPanel.setTopComponent(scrollPane);
    	
    	this.fitCPFDividerLocationToComponent(distributionPane);
    }

    /**
     * Resizes (sets the divider location) of the split pane containing 
     * the current Conditional Probability Function panel
     * @param distributionPane : panel to fit
     */
    protected void fitCPFDividerLocationToComponent(JComponent distributionPane) {
    	// ajusting divider's location
//      int location = table.getPreferredSize().height + centerPanel.getDividerSize() + 2 + btnCPS.getPreferredSize().height;
        int location = 60;
    	if (distributionPane != null) {
    		location += distributionPane.getPreferredSize().height + centerPanel.getDividerSize();
    	}
        if (distributionPane instanceof JTable) {
        	if (((JTable)distributionPane).getTableHeader() != null) {
            	  location += ((JTable)distributionPane).getTableHeader().getPreferredSize().height;
             }
        	if (jspTable.getVisibleRect().width < distributionPane.getPreferredSize().width) {
        		jspTable.createHorizontalScrollBar();
        		location += jspTable.getHorizontalScrollBar().getHeight() + 2;
        	}
        }
        centerPanel.setDividerLocation(location);
	}

	/**
     *  Change the shown table to the given one.
     * {@link #setTableOwner(Node)} must be called before this method...
     * @param table The new table to show.
     * @param tableOwner : the node owning table
     */
    public void setTable(JTable table, Node tableOwner) {
        
    	this.table = table;
        
        // the below line is for backward compatibility
        this.setTableOwner(tableOwner);
        
        // the below code was moved into unbbayes.cps.gui.extension.CPSPanelBuilder
        //young2010 
//        JButton btnCPS   = new JButton(this.resource.getString("editCPS"));
//        btnCPS.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent ae) { 
//            	setCursor(new Cursor(Cursor.WAIT_CURSOR)); 
//            	new CPSController(controller, getTableOwner());
//                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
//            }
//
//        });
//        JPanel tPanel = new JPanel();
//        tPanel.setLayout(new BoxLayout(tPanel, BoxLayout.Y_AXIS)); 
//        btnCPS.setAlignmentX(Component.LEFT_ALIGNMENT);
//		tPanel.add ( btnCPS ) ;
        
        jspTable.setViewportView(table);
        jspTable.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        cpfPane = this.buildCPFPaneFromPlugin(tableOwner);
		
        // the line below was moved into buildCPFPaneFromPlugin
//		cpfPane.add(jspTable);

		 		 
		//this.centerPanel.setTopComponent(jspTable);
        this.centerPanel.setTopComponent(cpfPane);
        
        // ajusting divider's locationint location = table.getPreferredSize().height + centerPanel.getDividerSize() + 30;
        this.fitCPFDividerLocationToComponent(table);
    }

    /**
     * Builds a JTabbedPane containing panels to edit conditional probability functions, from
     * plugins.
     * @param tableOwner : owner of the table
     * @return A JTabbedPane containing all loaded plugins
     */
    protected JComponent buildCPFPaneFromPlugin(Node tableOwner) {
		JTabbedPane ret = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
		
		// there is nothing to do if no table owner is set
		if (tableOwner == null) {
			return ret;
		}
		
		// initializing pane with the basic discrete node's JTable
		ret.addTab(
					this.resource.getString("CPFTableTitle"), 
					iconController.getTableIcon(), 
					this.getCPTPane(), 
					this.resource.getString("CPFTableToolTip")
				);
		
		// getting singleton instance of plugin manager
		CoreCPFPluginManager cpfManager = CoreCPFPluginManager.newInstance();
		try {
			cpfManager.loadPlugin();
		} catch (IOException e) {
			e.printStackTrace();
			Debug.println(this.getClass(), "Could not load plugin for CPF pane");
		}
		
		// adding plugin tabs
		for (INodeClassDataTransferObject dto : cpfManager.getPluginInformation(tableOwner.getClass().getName())) {
			
			// extract panel builder
			IProbabilityFunctionPanelBuilder builder = dto.getProbabilityFunctionPanelBuilder();
			if (builder == null) {
				// if no builder is set, there is something wrong... But we just ignore wrong plugins
				Debug.println(this.getClass(), "A DTO with no panel builder was found for " + tableOwner.toString());
				continue;
			}
			
			// initialize panel builder
			builder.setProbabilityFunctionOwner(tableOwner);
			
			// add panel as tab
			try {
				ret.addTab(
						dto.getName(), 
						dto.getIcon(), 
						new JScrollPane(builder.buildProbabilityFunctionEditionPanel()), 
						dto.getDescription()
					);
			} catch (Exception e) {
				e.printStackTrace();
				Debug.println(this.getClass(), "A panel builder is not building panel for " + tableOwner.toString());
				continue;
			}
		}
		
		ret.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				try{
					fitCPFDividerLocationToComponent((JComponent)((JTabbedPane)centerPanel.getTopComponent()).getSelectedComponent());
				} catch (Exception exc) {
					exc.printStackTrace();
				}
			}
		});
		
		return ret;
	}
    
    private int selectedFunction = 0;
    
    public JPanel getCPTPane() {
    	final JPanel pane = new JPanel(new BorderLayout());
    	// Only add the combo box with table functions for nodes with probabilistic tables
    	if (getTableOwner() instanceof IRandomVariable) {
    		if (((IRandomVariable) getTableOwner()).getProbabilityFunction() instanceof ProbabilisticTable) {
    			final ProbabilisticTable table = (ProbabilisticTable)((IRandomVariable) getTableOwner()).getProbabilityFunction();
    			
    			// TODO Add an extension point to allow the implementation of new functions as plugins
    			// Add existing functions to combo box
    			Set<ITableFunction> functions = new TreeSet<ITableFunction>();
    			ITableFunction function = new NormalizeTableFunction();
    			functions.add(function);
    			function = new UniformTableFunction();
    			functions.add(function);
    			function = new FillUniformTableFunction();
    			functions.add(function);
    			
    			// Create the combo box based on the list of functions
    			final JComboBox cmbFunction = new JComboBox(functions.toArray());
    			cmbFunction.setSelectedIndex(selectedFunction);
    			
    			JButton btnRun = new JButton(resource.getString("apply"));
    			
    			// Run function on selected table
    			btnRun.addActionListener(new ActionListener() {
    				public void actionPerformed(ActionEvent e) {
    					// Store selected function
    					selectedFunction = cmbFunction.getSelectedIndex();
    					// Apply selected function
    					((ITableFunction)cmbFunction.getSelectedItem()).applyFunction(table);
    					// Update the change made to the table
    					PNEditionPane.this.netWindow.getController().createTable(getTableOwner());
    				}
    			});
    			
    			JPanel functionPane = new JPanel();
    			
    			functionPane.add(cmbFunction);
    			functionPane.add(btnRun);
    			
    			pane.add(functionPane, BorderLayout.WEST);
    			
    		}
    	} 
    	
    	pane.add(getJspTable(), BorderLayout.CENTER);
    	
    	return pane;
    }

	public Node getTableOwner() {
        return tableOwner;
    }

    public void setTableOwner(Node node) {
        this.tableOwner = node;
    }

    /**
     * Seta o status exibido na barra de status.
     *
     * @param status mensagem de status.
     */
    public void setStatus(String status) {
        this.status.setText(status);
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

    public JButton getBtnCompile() {
        return this.btnCompile;
    }

    public JLabel getLblDescription() {
        return this.lblDescription;
    }

    public JButton getBtnGlobalOption() {
        return this.btnGlobalOption;
    }

    public JButton getBtnRemoveState() {
        return this.btnRemoveState;
    }

    public JButton getBtnAddState() {
        return this.btnAddState;
    }

    public JButton getBtnPreviewNet() {
        return this.btnPreviewNet;
    }

    public JButton getBtnPreviewTable() {
        return this.btnPreviewTable;
    }

    public JButton getBtnPrintNet() {
        return this.btnPrintNet;
    }

    public JButton getBtnPrintTable() {
        return this.btnPrintTable;
    }

    public JButton getBtnSaveNetImage() {
        return this.btnSaveNetImage;
    }

    public JButton getBtnSaveTableImage() {
        return this.btnSaveTableImage;
    }

    public JLabel getLblName() {
        return this.lblName;
    }

    public JButton getBtnHierarchy() {
        return this.btnHierarchy;
    }
    
    /**
     * This is the toolbar containing buttons in order to edit a network,
     * such as "new probabilistic node" button, "add edge" button, etc.
     * 
     * @version 2010/01/02 - added support for plugin nodes
     * @author Shou Matsumoto
     * @see #buildAddPluginSplitButtonMenu()
     * @see CorePluginNodeManager
     */
  	public class ToolBarEdition extends JToolBar{
  	    
  		private static final long serialVersionUID = 1L;
		
  		private final JToggleButton btnResetCursor; 
		private final JToggleButton btnAddEdge;
		// @deprecated Continuous node is no longer supported in UnBBayes core. It has 
		// now been replaced by the CPS plugin available at http://sourceforge.net/projects/prognos/.
//  	    private final JToggleButton btnAddContinuousNode;
  	    private final JToggleButton btnAddProbabilisticNode;
  	    private final JToggleButton btnAddDecisionNode;
  	    private final JToggleButton btnAddUtilityNode;
  	    private final JToggleButton btnDeleteSelectedItem; 
  	    //by young2  	    
  	    //private final JToggleButton btnSelectObject;
  	    
  	    private final ButtonGroup groupEditionButtons; 
  	    
  	    private SplitToggleButton btnAddPluginButton;
  	    
  	    public ToolBarEdition(){
  	        
  			super(); 
  			setFloatable(false); 
  			
  	        btnAddProbabilisticNode  = new JToggleButton(iconController.getEllipsisIcon());
  	        btnAddDecisionNode       = new JToggleButton(iconController.getDecisionNodeIcon());
  	        btnAddUtilityNode        = new JToggleButton(iconController.getUtilityNodeIcon());
  	        // @deprecated Continuous node is no longer supported in UnBBayes core. It has 
  			// now been replaced by the CPS plugin available at http://sourceforge.net/projects/prognos/.
//  	        btnAddContinuousNode     = new JToggleButton(iconController.getContinousNodeIcon());
  	        btnAddEdge               = new JToggleButton(iconController.getEdgeIcon());
     	    //by young2
  	        //btnSelectObject          = new JToggleButton(iconController.getSelectionIcon());
  	        btnResetCursor = new JToggleButton(iconController.getArrowIcon()); 
  	        btnDeleteSelectedItem = new JToggleButton(iconController.getEditDelete());
  			
  			btnAddEdge.setToolTipText(resource.getString("arcToolTip"));
  			// @deprecated Continuous node is no longer supported in UnBBayes core. It has 
  			// now been replaced by the CPS plugin available at http://sourceforge.net/projects/prognos/.
//  	        btnAddContinuousNode.setToolTipText(resource.getString("continuousNodeInsertToolTip"));
  	        btnAddProbabilisticNode.setToolTipText(resource.getString("probabilisticNodeInsertToolTip"));
  	        btnAddDecisionNode.setToolTipText(resource.getString("decisionNodeInsertToolTip"));
  	        btnAddUtilityNode.setToolTipText(resource.getString("utilityNodeInsertToolTip"));;
  	        //by young2
  	        //btnSelectObject.setToolTipText(resource.getString("selectToolTip"));
  	        btnResetCursor.setToolTipText(resource.getString("resetToolTip"));
  	        btnDeleteSelectedItem.setToolTipText(resource.getString("deleteSelectedItemToolTip"));
  	        
  	        // set of buttons (split buttons) for nodes implemented by plugin
  	        // @deprecated Continuous node is no longer supported in UnBBayes core. It has 
  			// now been replaced by the CPS plugin available at http://sourceforge.net/projects/prognos/.
//  	        this.setBtnAddPluginButton(new SplitToggleButton(btnAddContinuousNode, SwingConstants.SOUTH));
  	        this.setBtnAddPluginButton(new SplitToggleButton());
  	        this.getBtnAddPluginButton().setMenu(this.buildAddPluginSplitButtonMenu());
  	        
  	        // ajust size of plugin button
  	        this.ajustPluginButtonSize();
  	        
  	        // adding a listener to be called when a "reload plugin" event is dispatched (in order to refresh split button)
  	        UnBBayesPluginContextHolder.newInstance().addListener(new UnBBayesPluginContextHolder.OnReloadActionListener(){
				public void onReload(EventObject eventObject) {
					getBtnAddPluginButton().setMenu(buildAddPluginSplitButtonMenu());
				}
  	        });
  	        
  	        // show plugin node's button only if there are 1 or more node types.
  	        this.getBtnAddPluginButton().setVisible(this.getBtnAddPluginButton().getMenu().getComponentCount() > 0);
  	        
  	        groupEditionButtons = new ButtonGroup(); 
  	        groupEditionButtons.add(btnResetCursor);
  	        groupEditionButtons.add(btnAddEdge);
//  	        groupEditionButtons.add(btnAddContinuousNode);
  	        groupEditionButtons.add(this.getBtnAddPluginButton().getMainButton());
  	        groupEditionButtons.add(btnAddProbabilisticNode);
  	        groupEditionButtons.add(btnAddDecisionNode);
  	        groupEditionButtons.add(btnAddUtilityNode);
   	        //by young2
  	        //groupEditionButtons.add(btnSelectObject);
  	        groupEditionButtons.add(btnDeleteSelectedItem); 
	         
  	        add(btnResetCursor);
  	        add(btnAddProbabilisticNode); 
  	        add(btnAddDecisionNode); 
  	        add(btnAddUtilityNode); 
//  	        add(btnAddContinuousNode); 
  	        add(this.getBtnAddPluginButton()); 
  	        add(btnAddEdge); 
  	        add(btnDeleteSelectedItem);
  	        //by young2
  	        //add(btnSelectObject); 
  	        
  	        btnResetCursor.addActionListener(new ActionListener(){
  	  			public void actionPerformed(ActionEvent ae){
  	  		    	netWindow.getGraphPane().setAction(GraphAction.NONE);
  	  			}
  	  		});
  	        
  	        //ao clicar no bot�o btnAddEdge setamos as vari�veis booleanas e os estados dos but�es
  	        btnAddEdge.addActionListener(new ActionListener() {
  	            public void actionPerformed(ActionEvent ae) {
  	            	netWindow.getGraphPane().setAction(GraphAction.CREATE_EDGE);
  	            }
  	        });
  	        
  	        // @deprecated Continuous node is no longer supported in UnBBayes core. It has 
  			// now been replaced by the CPS plugin available at http://sourceforge.net/projects/prognos/.
  	        // Creates a continuous node
//  	        btnAddContinuousNode.addActionListener(new ActionListener() {
//  	            public void actionPerformed(ActionEvent ae) {
//  	            	netWindow.getGraphPane().setAction(GraphAction.CREATE_CONTINUOUS_NODE);
//  	            }
//  	        });

  	        //ao clicar no bot�o node setamos as vari�veis booleanas e os estados dos but�es
  	        btnAddProbabilisticNode.addActionListener(new ActionListener() {
  	            public void actionPerformed(ActionEvent ae) {
  	            	netWindow.getGraphPane().setAction(GraphAction.CREATE_PROBABILISTIC_NODE);
  	            }
  	        });


  	        //ao clicar no bot�o btnAddDecisionNode setamos as vari�veis booleanas e os estados dos but�es
  	        btnAddDecisionNode.addActionListener(new ActionListener() {
  	            public void actionPerformed(ActionEvent ae) {
  	            	netWindow.getGraphPane().setAction(GraphAction.CREATE_DECISION_NODE);
  	            }
  	        });

  	        //ao clicar no bot�o btnAddUtilityNode setamos as vari�veis booleanas e os estados dos but�es
  	        btnAddUtilityNode.addActionListener(new ActionListener() {
  	            public void actionPerformed(ActionEvent ae) {
  	            	netWindow.getGraphPane().setAction(GraphAction.CREATE_UTILITY_NODE);
  	            }
  	        });


  	      	//by young2
  	        /*
  	        //ao clicar no bot�o node setamos as vari�veis booleanas e os estados dos but�es
  	        btnSelectObject.addActionListener(new ActionListener() {
  	            public void actionPerformed(ActionEvent ae) {
  	            	netWindow.getGraphPane().setAction(GraphAction.SELECT_MANY_OBJECTS);
  	            }
  	        });*/
  	        

  	  		btnDeleteSelectedItem.addActionListener(new ActionListener() {
  	  			public void actionPerformed(ActionEvent ae) {
  	  				controller.getSENController().deleteSelectedItem(); 
  	  			}
  	  		});
  		}

  	    /**
  	     * This method is called at constructor in order to ajust plugin node's
  	     * split button's size.
  	     */
  		protected void ajustPluginButtonSize() {
  			// adds an horizontal gap at left and right, measuring 5 px each.
  			// That would need 10px extra for width
  			this.getBtnAddPluginButton().setPreferredSize(new Dimension(55, this.getBtnAddPluginButton().getHeight()));
  			this.getBtnAddPluginButton().setSize(this.getBtnAddPluginButton().getPreferredSize());
  			this.getBtnAddPluginButton().setMaximumSize(this.getBtnAddPluginButton().getPreferredSize());
  			this.getBtnAddPluginButton().setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
//			this.getBtnAddPluginButton().updateUI();
//			this.updateUI();
//			this.repaint();
		}

		/**
  		 * Builds up the menu to be shown (and its action listeners)
  		 * when a {@link #getBtnAddPluginButton()}'s right side arrow is
  		 * pressed (which contents should reflect nodes loaded from plugins).
  		 * @return a JPopupMenu containing all plugin nodes
  		 */
		public JPopupMenu buildAddPluginSplitButtonMenu() {
			
			JPopupMenu ret = new JPopupMenu(resource.getString("selectNodeType"));
			
			// @deprecated Continuous node is no longer supported in UnBBayes core. It has 
			// now been replaced by the CPS plugin available at http://sourceforge.net/projects/prognos/.
//			JMenuItem continuousNodeItem = new JMenuItem(resource.getString("continuousNodeLabel"),iconController.getContinousNodeIcon());
//			continuousNodeItem.setToolTipText(resource.getString("continuousNodeInsertToolTip"));
//			continuousNodeItem.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent e) {
//					getBtnAddPluginButton().setMainButton(getBtnAddContinuousNode());
//					groupEditionButtons.remove(getBtnAddContinuousNode());
//					groupEditionButtons.add(getBtnAddContinuousNode());
//					getBtnAddPluginButton().getMenu().setVisible(false);
//					updateUI();
//					getBtnAddPluginButton().getMainButton().doClick();
//					Debug.println(this.getClass(), "Plugin button set to " + getBtnAddPluginButton().getMainButton().getToolTipText());
//				}
//			});
//			
//			ret.add(continuousNodeItem);
			
			// load plugin and add buttons into the plugin node's split button
			for (INodeClassDataTransferObject dto : netWindow.getGraphPane().getPluginNodeManager().getAllLoadedPluginNodes()) {
				JMenuItem stubItem = new JMenuItem(dto.getName() , ((dto.getIcon()==null)?(iconController.getChangeNodeTypeIcon()):(dto.getIcon())));
				stubItem.setToolTipText(dto.getDescription());
				// the action listener below just updates the split button and the button group
				stubItem.addActionListener(new DtoAwareListItemActionListener(dto));
				ret.add(stubItem);
			}
			
			return ret;
		}

		public JToggleButton getBtnAddEdge() {
			return btnAddEdge;
		}

		// @deprecated Continuous node is no longer supported in UnBBayes core. It has 
		// now been replaced by the CPS plugin available at http://sourceforge.net/projects/prognos/.
//		public JToggleButton getBtnAddContinuousNode() {
//			return btnAddContinuousNode;
//		}

		public JToggleButton getBtnAddProbabilisticNode() {
			return btnAddProbabilisticNode;
		}

		public JToggleButton getBtnAddDecisionNode() {
			return btnAddDecisionNode;
		}

		public JToggleButton getBtnAddUtilityNode() {
			return btnAddUtilityNode;
		}

		//by young2
		/*
		public JToggleButton getBtnSelectObject() {
			return btnSelectObject;
		}*/
		
		

		/**
		 * @return the btnResetCursor
		 */
		public JToggleButton getBtnResetCursor() {
			return btnResetCursor;
		}

		/**
		 * @return the btnDeleteSelectedItem
		 */
		public JToggleButton getBtnDeleteSelectedItem() {
			return btnDeleteSelectedItem;
		}

		public ButtonGroup getGroupEditionButtons() {
			return groupEditionButtons;
		}

		/**
		 * This button is a split button which responsible for
		 * nodes implemented by plugins.
		 * @return the btnAddPluginButton
		 */
		public SplitToggleButton getBtnAddPluginButton() {
			return btnAddPluginButton;
		}

		/**
		 * This button is a split button which responsible for
		 * nodes implemented by plugins.
		 * @param btnAddPluginButton the btnAddPluginButton to set
		 */
		public void setBtnAddPluginButton(SplitToggleButton btnAddPluginButton) {
			this.btnAddPluginButton = btnAddPluginButton;
		}
		
		/**
		 * This is an action listener for a list item, which is listed when
		 * we press the arrow at the right side of the plugin node's split button.
		 * @author Shou Matsumoto
		 *
		 */
		protected class DtoAwareListItemActionListener implements ActionListener{
			private INodeClassDataTransferObject dto;
			/** Default constructor initializing the dto. @param dto */
			public DtoAwareListItemActionListener (INodeClassDataTransferObject dto) {
				this.dto = dto;
			}
			public void actionPerformed(ActionEvent e) {
				// this is the button to be shown when we press the list item
				JToggleButton button = new JToggleButton(((dto.getIcon()==null)?(iconController.getChangeNodeTypeIcon()):(dto.getIcon())));
				button.setToolTipText(dto.getDescription());
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						netWindow.getGraphPane().setAction(GraphAction.ADD_PLUGIN_NODE, dto);
					}
				});
				
				// update button group, avoiding duplicate buttons. 
				// This is in order to unselect another button within same group after clicking this new button
				groupEditionButtons.remove(button);
				groupEditionButtons.add(button);
				
				// change the main button in the split button
				getBtnAddPluginButton().setMainButton(button);
				
				// hide menu, since user has pressed
				getBtnAddPluginButton().getMenu().setVisible(false);
				updateUI();
				
				// click the button automatically 
				getBtnAddPluginButton().getMainButton().doClick();
				
				Debug.println(this.getClass(), "Plugin button set to " + getBtnAddPluginButton().getMainButton().getToolTipText());
			}
		}
  	}

	public ToolBarEdition getTbEdition() {
		return tbEdition;
	}

	/**
	 * @return the btnEvaluate
	 */
	public JButton getBtnEvaluate() {
		return btnEvaluate;
	}

	/**
	 * @return the jtbState
	 */
	public JToolBar getJtbState() {
		return jtbState;
	}

	/**
	 * @return the jspTable
	 */
	public JScrollPane getJspTable() {
		return jspTable;
	}

	/**
	 * This is a pane to edit conditional probability functions
	 * @return the cpfPane
	 */
	public JComponent getCpfPane() {
		return cpfPane;
	}

	/**
	 * This is a pane to edit conditional probability functions
	 * @param cpfPane the cpfPane to set
	 */
	public void setCpfPane(JComponent cpfPane) {
		this.cpfPane = cpfPane;
	}

	

}
