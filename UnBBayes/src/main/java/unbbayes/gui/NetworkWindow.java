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
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.JViewport;

import unbbayes.controller.ConfigurationsController;
import unbbayes.controller.IconController;
import unbbayes.controller.NetworkController;
import unbbayes.gui.table.extension.IProbabilityFunctionPanelBuilder;
import unbbayes.io.BaseIO;
import unbbayes.io.FileExtensionIODelegator;
import unbbayes.io.extension.jpf.PluginAwareFileExtensionIODelegator;
import unbbayes.prs.Graph;
import unbbayes.prs.Network;
import unbbayes.prs.Node;
import unbbayes.prs.bn.ProbabilisticNetwork;
import unbbayes.prs.bn.SingleEntityNetwork;
import unbbayes.util.extension.UnBBayesModule;

/**
 * Class responsible for representing the network window.
 * 
 * @author Michael
 * @author Rommel
 * @author Laecio Lima dos Santos
 * @author Shou Matsumoto
 * 
 * @version 02/13/2010 - MEBN code was moved to unbbayes.gui.mebn.MEBNNetworkWindow
 * 
 */
public class NetworkWindow extends UnBBayesModule {
	
	// since this implements IPersistenceAwareWindow, let's store them
//	private static final String[] SUPPORTED_FILE_EXTENSIONS = { "net", "xml"};
//	private static final String[] SUPPORTED_FILE_EXTENSIONS_LOAD = { "net", "xml", "dne"};
	
	
	/** Serialization runtime version number */
	private static final long serialVersionUID = 0;

	private JViewport graphViewport = null;

	private  GraphPane graphPane = null;

	private  NetworkController controller = null;

	private  Network net = null; 
	
	private String fileName = null; 
	
	private JScrollPane jspGraph = null;

	private JLabel status = null;

	private boolean bCompiled = false;

	private CardLayout card;

	private PNEditionPane pnEditionPane = null;

	private PNCompilationPane pnCompilationPane = null;


	private HierarchicDefinitionPane hierarchyPanel = null;

	private EditNet editNet = null;


	public static final Integer PN_MODE = 0;

	private Integer mode = null;

	private static final String PN_PANE_PN_EDITION_PANE = "pnEditionPane";

	private static final String PN_PANE_PN_EDIT_NET = "editNet";

	private static final String PN_PANE_PN_COMPILATION_PANE = "pnCompilationPane";

	private static final String PN_PANE_HIERARCHY_PANE = "hierarchy";
	
	private static final String PN_PANE_EVALUATION_PANE = "pnEvaluation";

	
	
	// variables that identifies a module 
	private String moduleName;

	/** Load resource file from this package */
	private static ResourceBundle resource = unbbayes.util.ResourceController.newInstance().getBundle(
			unbbayes.gui.resources.GuiResources.class.getName());

	
	/**
	 * Default constructor useful for extended classes
	 */
	protected NetworkWindow() {
//		super("", true, true, true, true);
		super("");		
		this.setModuleName(this.resource.getString("PNModuleName"));
		status = new JLabel(resource.getString("statusReadyLabel"));
	}
	
	public NetworkWindow(Network net) {
//		super(net.getName(), true, true, true, true);
		super(net.getName());
				
		this.net = net; 
		fileName = null; 
		
		Container contentPane = getContentPane();
		card = new CardLayout();
		contentPane.setLayout(card);
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);

		// instancia variaveis de instancia
		graphViewport = new JViewport();
		if (net instanceof SingleEntityNetwork) {
			controller = new NetworkController((SingleEntityNetwork) net, this);
			this.setModuleName(this.resource.getString("PNModuleName"));
		} else {
			throw new IllegalArgumentException("net != SingleEntityNetwork");
		}

		graphPane = new GraphPane(controller, graphViewport);

		jspGraph = new JScrollPane(graphViewport);
		status = new JLabel(resource.getString("statusReadyLabel"));
		bCompiled = false;
		

		 // TODO I think the following code must be migrated to GraphPane, including changes on graphViewport (since both are visible to GraphPane)
		
		//by young
		long width = (long)Node.getDefaultSize().getX();
		long height = (long)Node.getDefaultSize().getY();
		graphPane.getGraphViewport().reshape(0, 0,
				(int) (graphPane.getBiggestPoint().getX() + width),
				(int) (graphPane.getBiggestPoint().getY() + height));
		graphPane.getGraphViewport().setViewSize(
				new Dimension(
						(int) (graphPane.getBiggestPoint().getX() + width),
						(int) (graphPane.getBiggestPoint().getY() + height)));

		// setar o conteudo e o tamanho do graphViewport
		graphViewport.setView(graphPane);
		graphViewport.setSize(800, 600);

		jspGraph.getHorizontalScrollBar().addAdjustmentListener(
				new AdjustmentListener() {
					public void adjustmentValueChanged(AdjustmentEvent e) {
						graphPane.update();
					}
				});

		jspGraph.getVerticalScrollBar().addAdjustmentListener(
				new AdjustmentListener() {
					public void adjustmentValueChanged(AdjustmentEvent e) {
						graphPane.update();
					}
				});

		// setar defaults para jspGraph
		jspGraph.setHorizontalScrollBar(jspGraph.createHorizontalScrollBar());
		jspGraph.setVerticalScrollBar(jspGraph.createVerticalScrollBar());
		jspGraph
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		jspGraph
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		if (net instanceof SingleEntityNetwork) {
			mode = NetworkWindow.PN_MODE;
			pnEditionPane = new PNEditionPane(this, controller);
			editNet = new EditNet(this, controller);
			pnCompilationPane = new PNCompilationPane(this, controller);
			hierarchyPanel = new HierarchicDefinitionPane(
					(SingleEntityNetwork) net, this);

			contentPane.add(pnEditionPane, PN_PANE_PN_EDITION_PANE);
			contentPane.add(editNet, PN_PANE_PN_EDIT_NET);
			contentPane.add(pnCompilationPane, PN_PANE_PN_COMPILATION_PANE);
			contentPane.add(hierarchyPanel, PN_PANE_HIERARCHY_PANE);

			// inicia com a tela de edicao de rede(PNEditionPane)
			pnEditionPane.getCenterPanel().setBottomComponent(jspGraph);
			card.show(getContentPane(), PN_PANE_PN_EDITION_PANE);
		} else {
			throw new IllegalArgumentException("net != SingleEntityNetwork");
		}

		setVisible(true);
		graphPane.update();
	}

	public void updateTitle(){
		super.setTitle(net.getName() + " " + "[" + fileName + "]"); 
	}
	
	/**
	 * Retorna o grafo responsavel pela representacao grafica da rede.
	 * 
	 * @return retorna o (<code>GraphPane</code>)
	 * @see GraphPane
	 */
	public GraphPane getGraphPane() {
		return this.graphPane;
	}

	/**
	 * Retorna a arvore de evidencias.
	 * 
	 * @return retorna o evidenceTree (<code>JTree</code>)
	 * @see JTree
	 */
	public EvidenceTree getEvidenceTree() {
		if (pnCompilationPane != null) {
			return pnCompilationPane.getEvidenceTree();
		} 
		return null;
	}

	/**
	 * Retorna o container, graphViewport (<code>JViewport</code>), que
	 * contem o grafo responsavel pela representacao grafica da rede.
	 * 
	 * @return retorna o graphViewport(<code>JViewport</code>)
	 * @see JViewport
	 */
	public JViewport getGraphViewport() {
		return this.graphViewport;
	}

	/**
	 * Returns the CPT <code>JTable</code>.
	 * 
	 * @return the CPT <code>JTable</code>.
	 * @see JTable
	 */
	public JTable getTable() {
		return pnEditionPane.getTable();
	}

	/**
	 * Returns the <code>JTextField</code> that has the node's description.
	 * 
	 * @return the <code>JTextField</code> that has the node's description.
	 * @see JTextField
	 */
	public JTextField getTxtDescription() {
		if (pnEditionPane == null) {
			return null;
		}
		return pnEditionPane.getTxtDescription();
	}

	/**
	 * Returns the <code>JTextField</code> that has the node's name.
	 * 
	 * @return the <code>JTextField</code> that has the node's name.
	 * @see JTextField
	 */
	public JTextField getTxtName() {
		if (pnEditionPane == null) {
			return null;
		}
		return pnEditionPane.getTxtName();
	}
	
	public void setDistributionPane(JPanel distributionPane) {
		pnEditionPane.setDistributionPane(distributionPane);
		// moved from SENController#createContinuousDistribution to here because every JPanel-based distribution was needing this
		setAddRemoveStateButtonVisible(false);
	}

	/**
	 * Delegates to {@link #getPnEditionPane()#setTable(JTable, Node)}
	 * @param table
	 * @param tableOwner
	 * @see PNEditionPane#setTable(JTable, Node)
	 */
	public void setTable(JTable table, Node tableOwner) {
		pnEditionPane.setTable(table,tableOwner);
	}

	/**
	 * Retorna o painel do draw.
	 * 
	 * @return retorna o jspDraw (<code>JScrollPane</code>)
	 * @see JScrollPane
	 */
	public JScrollPane getJspGraph() {
		return this.jspGraph;
	}

	/**
	 * Retorna o painel da arvore.
	 * 
	 * @return retorna o jspTree (<code>JScrollPane</code>)
	 * @see JScrollPane
	 */
	public JScrollPane getJspTree() {
		return pnCompilationPane.getJspTree();
	}

	public Node getTableOwner() {
		return pnEditionPane.getTableOwner();
	}

	public void setTableOwner(Node node) {
		pnEditionPane.setTableOwner(node);
	}

	/**
	 * Retorna a rede probabil_stica <code>(ProbabilisticNetwork)</code>
	 * 
	 * @return a rede probabil_stica
	 * @see ProbabilisticNetwork
	 */
	public SingleEntityNetwork getSingleEntityNetwork() {
		return (SingleEntityNetwork) controller.getNetwork();
	}


	/**
	 * Seta o status exibido na barra de status.
	 * 
	 * @param status
	 *            mensagem de status.
	 */
	public void setStatus(String status) {
		if (pnCompilationPane != null) {
			pnCompilationPane.setStatus(status);
		}
		if (pnEditionPane != null) {
			pnEditionPane.setStatus(status);
		}
		this.status.setText(status);
	}

	/**
	 * M_todo respons_vel por fazer as altera__es necess_rias para a mudar da
	 * tela de edi__o para a de compila__o.
	 */
	public void changeToPNCompilationPane() {

		if (mode == NetworkWindow.PN_MODE) {
			graphPane.setAction(GraphAction.NONE);
			graphPane.removeKeyListener(controller);

			pnCompilationPane.getCenterPanel().setRightComponent(jspGraph);
			pnCompilationPane.setStatus(status.getText());
			pnCompilationPane.getEvidenceTree().setRootVisible(true);
			pnCompilationPane.getEvidenceTree().expandRow(0);
			pnCompilationPane.getEvidenceTree().setRootVisible(false);

			bCompiled = true;
			controller.getSingleEntityNetwork().setFirstInitialization(true);

			card.show(getContentPane(), PN_PANE_PN_COMPILATION_PANE);
			
			//by young2
			pnCompilationPane.getEvidenceTree().updateTree(false);
			
			pnCompilationPane.updateToPreferredSize();
		}

	}
	
	public void changeToPNEvaluationPane(JPanel evaluationPane) {

		if (mode == NetworkWindow.PN_MODE) {
			graphPane.setAction(GraphAction.NONE);
			graphPane.removeKeyListener(controller);
			
			
			// FIXME THINK MORE IN WHERE TO PUT THIS... DOES NOT LOOK GOOD...
			JPanel leftPane = new JPanel(new BorderLayout());
			JToolBar toolBar = new JToolBar();
			
			JButton editMode = new JButton(IconController.getInstance().getEditIcon());
			editMode.setToolTipText(resource.getString("editToolTip"));
				
			// Go back to editing mode 
	        editMode.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent ae) {
	                changeToPNEditionPane();
	            }
	        });
			toolBar.add(editMode);
			
			leftPane.add(toolBar, BorderLayout.NORTH);
			leftPane.add(evaluationPane, BorderLayout.CENTER);
			
			JSplitPane mainPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPane, jspGraph);
			
			mainPane.setDividerLocation(500);
			
			getContentPane().add(mainPane, PN_PANE_EVALUATION_PANE);

			card.show(getContentPane(), PN_PANE_EVALUATION_PANE);
		}

	}

	

	

	/**
	 * M_todo respons_vel por fazer as altera__es necess_rias para a mudar da
	 * tela de compila__o para a de edi__o.
	 */
	public void changeToPNEditionPane() {

		if (mode == NetworkWindow.PN_MODE) {
			graphPane.addKeyListener(controller);

			pnEditionPane.getCenterPanel().setBottomComponent(jspGraph);
			pnEditionPane.setStatus(status.getText());

			bCompiled = false;

			controller.getSingleEntityNetwork().setFirstInitialization(true);

			//by young
			graphPane.update();
			graphPane.resizeAllToFitText();
			
			card.show(getContentPane(), "pnEditionPane");
		}
	}

	/**
	 * M_todo respons_vel por fazer as altera__es necess_rias para a mudar da
	 * tela de edi__o para a tela de defini__o da hierarquia.
	 */
	public void changeToHierarchy() {

		hierarchyPanel.updateExplanationTree();
		card.show(getContentPane(), "hierarchy");

	}

	public void changeToEditNet() {
		card.show(getContentPane(), "editNet");
	}

	/**
	 * Retorna se a janela que esta aparecendo esta em modo de edicao(false) ou
	 * se ela esta em modo de compila__o(true).
	 * 
	 * @return true se estiver em modo de compila__o, e false caso contr_rio.
	 */
	public boolean isCompiled() {
		return this.bCompiled;
	}

	/**
	 * Retorna a tela de edi__o (<code>PNEditionPane</code>).
	 * 
	 * @return a tela de edi__o
	 * @see PNEditionPane
	 */
	public PNEditionPane getNetWindowEdition() {
		return this.pnEditionPane;
	}

	

	/**
	 * Retorna a tela de compila__o (<code>PNCompilationPane</code>).
	 * 
	 * @return a tela de compila__o
	 * @see PNCompilationPane
	 */
	public PNCompilationPane getNetWindowCompilation() {
		return this.pnCompilationPane;
	}

	public HierarchicDefinitionPane getHierarchicDefinitionPanel() {
		return this.hierarchyPanel;
	}

	public NetworkController getNetworkController() {
		return controller;
	}

	public EditNet getEditNet() {
		return editNet;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
		this.updateTitle(); 
	}
	
	public void setAddRemoveStateButtonVisible(boolean visible) {
		pnEditionPane.getBtnAddState().setVisible(visible);
		pnEditionPane.getBtnRemoveState().setVisible(visible);
	}

	/**
	 * @return the mode
	 */
	public Integer getMode() {
		return mode;
	}

	/**
	 * @param mode the mode to set
	 */
	public void setMode(Integer mode) {
		this.mode = mode;
	}


	
	/* (non-Javadoc)
	 * @see unbbayes.gui.IPersistenceAwareWindow#getSavingMessage()
	 */
	public String getSavingMessage() {
		return resource.getString("saveTitle");
	}


	/**
	 * @return the controller
	 */
	public NetworkController getController() {
		return controller;
	}

	/**
	 * @param controller the controller to set
	 */
	public void setController(NetworkController controller) {
		this.controller = controller;
	}

	/*
	 * (non-Javadoc)
	 * @see unbbayes.gui.IPersistenceAwareWindow#getInternalFrame()
	 */
	public JInternalFrame getInternalFrame() {
		return this;
	}

	/*
	 * (non-Javadoc)
	 * @see unbbayes.gui.IPersistenceAwareWindow#getIO()
	 */
	public BaseIO getIO() {
		return this.getController().getBaseIO();
	}

	/*
	 * (non-Javadoc)
	 * @see unbbayes.gui.IPersistenceAwareWindow#getPersistingGraph()
	 */
	public Graph getPersistingGraph() {
		return this.getController().getNetwork();
	}


	/*
	 * (non-Javadoc)
	 * @see unbbayes.util.extension.UnBBayesModule#getModuleName()
	 */
	@Override
	public String getModuleName() {
		return this.moduleName;
	}

	/**
	 * @param moduleName the moduleName to set
	 */
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	
	
	
	/**
	 * Opens a new desktop window into currently used java desktop
	 * @see unbbayes.util.extension.UnBBayesModule#openFile(java.io.File)
	 */
	@Override
	public UnBBayesModule openFile(File file) throws IOException {
		
		
		Graph g = null;
		
		// a IO that delegates to correct I/O depending on the file extension (NET, DNE and XMLBIF by default)
		FileExtensionIODelegator ioDelegator = PluginAwareFileExtensionIODelegator.newInstance();
//		ioDelegator.getDelegators().add(UbfIO.getInstance());	// adding UBF compatibility into delegator
		
		try {
			g = ioDelegator.load(file);
		} catch (FileExtensionIODelegator.MoreThanOneCompatibleIOException e) {
			// OBS. FileExtensionIODelegator.MoreThanOneCompatibleIOException is managed
			// differently by load and save. This is because load is done by the module and the
			// save is done by IO (on load, we are not sure what module to use, but on save we
			// are sure that we should use the currently active module/window).
			// Thats why MoreThanOneCompatibleIOException must be treated by modules on load,
			// but treated by UnBBayesFrame on save.
			
			// More than one I/O was found to be compatible. Ask user to select one.
			String[] possibleValues = FileExtensionIODelegator.getNamesFromIOs(e.getIOs());
	    	String selectedValue = (String)JOptionPane.showInputDialog(
	    			this, 
	    			resource.getString("IOConflictMessage"), 
	    			resource.getString("IOConflictTitle"),
	    			JOptionPane.INFORMATION_MESSAGE, 
	    			null,
	    			possibleValues, 
	    			possibleValues[0]);
	    	if (selectedValue != null) {
	    		g = FileExtensionIODelegator.findIOByName(e.getIOs(), selectedValue).load(file);
	    	} else {
	    		// user appears to have cancelled
	    		this.dispose();
		    	return null;
	    	}
		}
		
		NetworkWindow window = null;
		
		try {
			ConfigurationsController.getInstance().addFileToListRecentFiles(file); 
			window = new NetworkWindow((Network)g);	
			((NetworkWindow)window).setFileName(file.getName().toLowerCase()); 
		} catch (Exception e) {
			throw new RuntimeException(this.resource.getString("unsupportedGraphFormat"),e);
		}
		
		this.dispose();
		
		return window;
	}
	
	
	

	/**
	 * This method builds a customized probability distribution panel (e.g. a panel to
	 * edit a node's probability function, using plugin support).
	 * Other methods usually calls a method within controller to show a table, but in order
	 * to avoid dependency inversion at MVC model, I'm implementing this method at view layer.
	 * @param builder: a panel builder handling a panel and its associated node. The probability
	 * function panel's owner must be the currently selected node.
	 */
	public void showProbabilityDistributionPanel(
			IProbabilityFunctionPanelBuilder builder) {
		
		// building the panel using associated node
		this.setDistributionPane(builder.buildProbabilityFunctionEditionPanel());
		
		// the below line is a requisite from inherited code, so, it is not very important for plugin context
		this.setTableOwner(builder.getProbabilityFunctionOwner());
		
		// update the name/description text fields at tool box
		this.getTxtDescription().setText(builder.getProbabilityFunctionOwner().getDescription());
		this.getTxtName().setText(builder.getProbabilityFunctionOwner().getName());
	}

	/**
	 * @return the net
	 */
	public Network getNet() {
		return net;
	}

	/**
	 * @param net the net to set
	 */
	public void setNet(Network net) {
		this.net = net;
	}

	/**
	 * @return the card
	 */
	public CardLayout getCardLayout() {
		return card;
	}

	/**
	 * @param card the card to set
	 */
	public void setCardLayout(CardLayout card) {
		this.card = card;
	}

	/**
	 * @param graphViewport the graphViewport to set
	 */
	public void setGraphViewport(JViewport graphViewport) {
		this.graphViewport = graphViewport;
	}

	/**
	 * @param graphPane the graphPane to set
	 */
	public void setGraphPane(GraphPane graphPane) {
		this.graphPane = graphPane;
	}

	/**
	 * @param jspGraph the jspGraph to set
	 */
	public void setJspGraph(JScrollPane jspGraph) {
		this.jspGraph = jspGraph;
	}

	/**
	 * @return the bCompiled
	 */
	public boolean isBCompiled() {
		return bCompiled;
	}

	/**
	 * @param compiled the bCompiled to set
	 */
	public void setBCompiled(boolean compiled) {
		bCompiled = compiled;
	}

	/**
	 * @return the status
	 */
	public JLabel getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(JLabel status) {
		this.status = status;
	}

	
	
	
}
