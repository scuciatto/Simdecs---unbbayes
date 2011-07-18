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
package unbbayes.controller;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import unbbayes.gui.ExplanationProperties;
import unbbayes.gui.NetworkWindow;
import unbbayes.gui.continuous.ContinuousNormalDistributionPane;
import unbbayes.gui.table.ExcelAdapter;
import unbbayes.gui.table.GUIPotentialTable;
import unbbayes.gui.table.ReplaceTextCellEditor;
import unbbayes.prs.Edge;
import unbbayes.prs.Node;
import unbbayes.prs.bn.IProbabilityFunction;
import unbbayes.prs.bn.IRandomVariable;
import unbbayes.prs.bn.JunctionTreeAlgorithm;
import unbbayes.prs.bn.PotentialTable;
import unbbayes.prs.bn.ProbabilisticNode;
import unbbayes.prs.bn.SingleEntityNetwork;
import unbbayes.prs.bn.TreeVariable;
import unbbayes.prs.exception.InvalidParentException;
import unbbayes.prs.hybridbn.CNNormalDistribution;
import unbbayes.prs.hybridbn.ContinuousNode;
import unbbayes.prs.hybridbn.GaussianMixture;
import unbbayes.prs.id.DecisionNode;
import unbbayes.prs.id.UtilityNode;
import unbbayes.util.Debug;
import unbbayes.util.extension.bn.inference.IInferenceAlgorithm;

public class SENController {

	private NetworkWindow screen;

	private SingleEntityNetwork singleEntityNetwork;

	private NumberFormat df;

	public enum InferenceAlgorithmEnum {
    	JUNCTION_TREE,
    	LIKELIHOOD_WEIGHTING,
    	GIBBS,
    	GAUSSIAN_MIXTURE
    }
	
    // TODO ROMMEL - CHANGE THIS!! NEW MODELING!!
	private IInferenceAlgorithm inferenceAlgorithm = new JunctionTreeAlgorithm();

	public IInferenceAlgorithm getInferenceAlgorithm() {
		return inferenceAlgorithm;
	}

	public void setInferenceAlgorithm(IInferenceAlgorithm inferenceAlgorithm) {
		this.inferenceAlgorithm = inferenceAlgorithm;
	}

	/** Load resource file from this package */
	private static ResourceBundle resource = unbbayes.util.ResourceController.newInstance().getBundle(
			unbbayes.controller.resources.ControllerResources.class.getName());

	/**
	 * Constructs a controller for SingleEntityNetwork.
	 * 
	 */
	public SENController(SingleEntityNetwork singleEntityNetwork,
			NetworkWindow screen) {
		this.singleEntityNetwork = singleEntityNetwork;
		this.screen = screen;
		df = NumberFormat.getInstance(Locale.getDefault());
		df.setMaximumFractionDigits(4);
	}

	/**
	 * Inserts a new state for a selected node
	 * @param node
	 *            The selected <code>Object <code>.
	 * @since
	 * @see		Object
	 */
	public void insertState(Node node) {
		if (node instanceof ProbabilisticNode) {
			node.appendState(resource.getString("stateProbabilisticName")
					+ node.getStatesSize());
			// TODO ROMMEL - The node class should have a listener to its change.
			// The code below is just temporary (implement NodeChangeListener)
			for (Node child : node.getChildren()) {
				if (child.getType() == Node.CONTINUOUS_NODE_TYPE) {
					((ContinuousNode)child).getCnNormalDistribution().refreshParents();
				}
			}
		} else if (node instanceof DecisionNode) {
			node.appendState(resource.getString("stateDecisionName")
					+ node.getStatesSize());
		}
		screen.setTable(makeTable(node), node);
	}

	/**
	 * Deletes the last state of a selected node
	 * 
	 * @param node
	 *            selected <code>Object <code>.
	 * @since
	 * @see		Object
	 */
	public void removeState(Node node) {
		node.removeLastState();
		// TODO ROMMEL - The node class should have a listener to its change.
		// The code below is just temporary (implement NodeChangeListener)
		for (Node child : node.getChildren()) {
			if (child.getType() == Node.CONTINUOUS_NODE_TYPE) {
				((ContinuousNode)child).getCnNormalDistribution().refreshParents();
			}
		}
		screen.setTable(makeTable(node), node);
	}
	
	/**
	 * Reset the beliefs for the prior probabilities.
	 */
	public void initialize() {
		try {
			this.getInferenceAlgorithm().reset();
			//by young2 (true:update, false:complie)
			screen.getEvidenceTree().updateTree(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Reset the belief of the selected node.
	 */
	public void removeEvidence(Node node) {
		try {
			if (node != null) {
				if (node instanceof TreeVariable) {
					TreeVariable n = (TreeVariable)node;
	        		n.resetLikelihood();
	        		n.resetEvidence();
	        		propagate();
	        	}
				screen.getEvidenceTree().updateTree(true);
			} else {
				JOptionPane.showMessageDialog(screen, resource.getString("noNodeSelectedError"), resource
						.getString("statusError"), JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Propagates the network's evidences.
	 */
	public void propagate() {
		//by young4
		boolean bReset = false;
		screen.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		try {
			// The change bellow is to adhere to feature request #3314855
			// Save the list of evidence entered
			Map<String, Integer> evidenceMap = new HashMap<String, Integer>();
			// Mapping likelihood also to fix bug #3316285
			Map<String, Float[]> likelihoodMap = new HashMap<String, Float[]>();
			for (Node n : singleEntityNetwork.getNodes()) {
				if (n instanceof TreeVariable) {
					TreeVariable node = (TreeVariable)n;
					if (node.hasEvidence()) {
						if (node.hasLikelihood()) {
							Float[] likelihood = new Float[node.getStatesSize()];
							for (int i = 0; i < node.getStatesSize(); i++) {
					            likelihood[i] = node.getMarginalAt(i);
					        }
							likelihoodMap.put(node.getName(), likelihood);
						} else {
							evidenceMap.put(node.getName(), node.getEvidence());
						}
					}
				}
			}
			// Reset evidence in order to allow changes in node which already had a different evidence set
			this.getInferenceAlgorithm().reset();
			// Enter the list of evidence again
			for (String name : evidenceMap.keySet()) {
				((TreeVariable)singleEntityNetwork.getNode(name)).addFinding(evidenceMap.get(name));
			}
			// Enter the likelihood also to fix bug #3316285
			for (String name : likelihoodMap.keySet()) {
				float[] likelihood = new float[likelihoodMap.get(name).length];
				for (int i = 0; i < likelihood.length; i++) {
					likelihood[i] = likelihoodMap.get(name)[i];
				}
				((TreeVariable)singleEntityNetwork.getNode(name)).addLikeliHood(likelihood);
			}
			// Finally propage evidence
			this.getInferenceAlgorithm().propagate();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(screen, e.getMessage(), resource
					.getString("statusError"), JOptionPane.ERROR_MESSAGE);
			bReset = true;
		}
		
		//by young2 (true:update, false:complie)
		screen.getEvidenceTree().updateTree(bReset);
		screen.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	/**
	 * Compiles the network. If there was any problem during compilation, the error
	 * message will be shown as a <code>JOptionPane</code> .
	 * 
	 * @return true if the net was compiled without any problem, false if there was a problem
	 * @since
	 * @see JOptionPane
	 */
	public boolean compileNetwork() {
		long ini = System.currentTimeMillis();
		screen.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		
		// Make sure the copy of the nodes is updated due to changes in the network. (only possible in the edition mode).
		singleEntityNetwork.resetNodesCopy();
		// Also used to make sure the tree is updated with the network changes, if there is any. The expanded nodes are also 
		// gone to its default (all expanded).
		screen.getEvidenceTree().resetTree();
		
		
		try {
			singleEntityNetwork.resetEvidences();
			this.getInferenceAlgorithm().setNetwork(singleEntityNetwork);
			this.getInferenceAlgorithm().run();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(), resource
					.getString("statusError"), JOptionPane.ERROR_MESSAGE);
			screen.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return false;
		}

		//screen.getEvidenceTree().updateTree();

		screen.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

		screen.setStatus(resource.getString("statusTotalTime")
				+ df.format(((System.currentTimeMillis() - ini)) / 1000.0)
				+ resource.getString("statusSeconds"));
		return true;
	}
	
	/**
	 * Inserts the desired node inside the network creating default state, symbol and description
	 * @param x The x position.
	 * @param y The y position. 
	 * @deprecated Continuous node is no longer supported in UnBBayes core. It has 
	 * now been replaced by the CPS plugin available at http://sourceforge.net/projects/prognos/.
	 */
	public Node insertContinuousNode(double x, double y) {
		ContinuousNode node = new ContinuousNode();
		node.setPosition(x, y);
		node.setName(resource.getString("probabilisticNodeName")
				+ singleEntityNetwork.getNodeCount());
		node.setDescription(node.getName());
		
		singleEntityNetwork.addNode(node);
		
		return node;
	}

	/**
	 * Inserts the desired node inside the network creating default state, symbol and description
	 * @param x The x position.
	 * @param y The y position.
	 */
	public Node insertProbabilisticNode(double x, double y) {
		ProbabilisticNode node = new ProbabilisticNode();
		node.setPosition(x, y);
		node.appendState(resource.getString("firstStateProbabilisticName"));
		node.setName(resource.getString("probabilisticNodeName")
				+ singleEntityNetwork.getNodeCount());
		node.setDescription(node.getName());
		PotentialTable auxTabProb = (PotentialTable)(node)
				.getProbabilityFunction();
		auxTabProb.addVariable(node);
		auxTabProb.setValue(0, 1);
		singleEntityNetwork.addNode(node);
		
		return node;
	}

	/**
	 * Inserts the desired node inside a network creating a default state, symbol and description.
	 * @param x The x position.
	 * @param y The y position.
	 */
	public Node insertDecisionNode(double x, double y) {
		DecisionNode node = new DecisionNode();
		node.setPosition(x, y);
		node.appendState(resource.getString("firstStateDecisionName"));
		node.setName(resource.getString("decisionNodeName")
				+ singleEntityNetwork.getNodeCount());
		node.setDescription(node.getName());
		singleEntityNetwork.addNode(node);
		
		return node;
	}

	/**
	 * Inserts the desired node inside a network creating a default state, symbol and description.
	 * @param x The x position.
	 * @param y The y position. 
	 */
	public Node insertUtilityNode(double x, double y) {
		UtilityNode node = new UtilityNode();
		node.setPosition(x, y);
		node.setName(resource.getString("utilityNodeName")
				+ singleEntityNetwork.getNodeCount());
		node.setDescription(node.getName());
		IProbabilityFunction auxTab = ((IRandomVariable) node).getProbabilityFunction();
		auxTab.addVariable(node);
		singleEntityNetwork.addNode(node);
		
		return node;
	}

	/**
	 * Links an edge connecting parents and children.
	 * 
	 * @param edge An <code>Edge</code> representing a linking edge
	 */
	//by young
	public boolean insertEdge(Edge edge) {
		try {
			singleEntityNetwork.addEdge(edge);
		} catch (InvalidParentException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), resource
					.getString("statusError"), JOptionPane.ERROR_MESSAGE);
			
			return false;
		}
		
		return true;
	}
	
	/**
	 * Delete the selected item of the graph (a node or a edge)
	 */
	public void deleteSelectedItem(){
		
		Object selected = screen.getGraphPane().getSelected(); 
		if(selected != null){
			deleteSelected(selected); 
		}
		//by young	
		screen.getGraphPane().update(); 
	}
	
	/**
	 * Creates and shows the panel where the user can edit the 
	 * continuous node normal distribution.
	 * @param node The continuous node to create the distribution p`ane for.
	 * @deprecated Continuous node is no longer supported in UnBBayes core. It has 
	 * now been replaced by the CPS plugin available at http://sourceforge.net/projects/prognos/.
	 */
	public void createContinuousDistribution(final ContinuousNode node) {
		// Separate continuous from discrete parent nodes
		final List<Node> discreteNodeList = new ArrayList<Node>();
		final List<String> discreteNodeNameList = new ArrayList<String>();
		final List<String> continuousNodeNameList = new ArrayList<String>();
		for (Node n : node.getParents()) {
			if (n.getType() ==  Node.PROBABILISTIC_NODE_TYPE) {
				discreteNodeList.add(n);
				discreteNodeNameList.add(n.getName());
			} else if (n.getType() == Node.CONTINUOUS_NODE_TYPE) {
				continuousNodeNameList.add(n.getName());
			}
		}
		
		// Create the distribution pane
		final ContinuousNormalDistributionPane distributionPane = new ContinuousNormalDistributionPane(discreteNodeNameList, continuousNodeNameList);
		screen.setDistributionPane(distributionPane);
		screen.setTableOwner(node);
		
		// Fill discrete parent node states
		for (Node n : discreteNodeList) {
			List<String> stateList = new ArrayList<String>(n.getStatesSize());
			for (int i = 0; i < n.getStatesSize(); i++) {
				stateList.add(n.getStateAt(i));
			}
			distributionPane.fillDiscreteParentStateSelection(n.getName(), stateList);
		}
		
		loadContinuousDistributionPaneValues(distributionPane, node.getCnNormalDistribution());
		
		// Create the confirm action listener. Responsible for setting the distribution values.
		ActionListener confirmAL = new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				setContinuousDistributionValues(distributionPane, node.getCnNormalDistribution());
			}
		};
		
		// Responsible for loading the values from the normal distribution into the distribution pane.
		// Used in cancel and in parent state change listeners.
		ActionListener restoreValuesFromDistributionAL = new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				loadContinuousDistributionPaneValues(distributionPane, node.getCnNormalDistribution());
			}
		};
		
		distributionPane.addConfirmButtonActionListener(confirmAL);
		distributionPane.addCancelButtonActionListener(restoreValuesFromDistributionAL);
		distributionPane.addParentStateChangeActionListener(restoreValuesFromDistributionAL);
		
		
		// moved into #setDistributionPane(JPanel)
//		screen.setAddRemoveStateButtonVisible(false);
	}
	
	/**
	 * Get the values from the normal distribution and set them into the distribution pane.
	 * @param distributionPane The pane to set the values from the normal distribution.
	 * @param distribution The normal distribution to get the values from. 
	 */
	private void loadContinuousDistributionPaneValues(ContinuousNormalDistributionPane distributionPane, CNNormalDistribution distribution) {
		// Get the multidimensional coordinate (state associated with each discrete parent) 
		int[] mCoord = distributionPane.getDiscreteParentNodeStateSelectedList();					
		if (mCoord.length == 0) {
			mCoord = new int[1];
			mCoord[0] = 0;
		}
		
		// Get the parameters values from the distribution 
		String mean = String.valueOf(distribution.getMean(mCoord));
		String variance = String.valueOf(distribution.getVariance(mCoord));
		List<String> constantList = new ArrayList<String>(distribution.getConstantListSize());
		for (int i = 0; i < distribution.getConstantListSize(); i++) {
			constantList.add(String.valueOf(distribution.getConstantAt(i, mCoord)));
		}
		
		// Set the parameters values in the distribution pane
		distributionPane.setMeanText(mean);
		distributionPane.setVarianceText(variance);
		distributionPane.setConstantTextList(constantList);
	}
	
	/**
	 * Get the values from the distribution pane and set them into the normal distribution.
	 * @param distributionPane The pane to get the values from.
	 * @param distribution The normal distribution to set the values into.
	 */
	private void setContinuousDistributionValues(ContinuousNormalDistributionPane distributionPane, CNNormalDistribution distribution) {
		try {
			// Get all parameter to set in continuous node distribution
			double mean = Double.parseDouble(distributionPane.getMeanText());
			double variance = Double.parseDouble(distributionPane.getVarianceText());
			List<String> constantTextList = distributionPane.getConstantTextList();
			List<Double> constantList = new ArrayList<Double>(constantTextList.size());
			for (String constantText : constantTextList) {
				constantList.add(Double.parseDouble(constantText));
			}
			
			// Get the multidimensional coordinate (state associated with each discrete parent)
			int[] multidimensionalCoord = distributionPane.getDiscreteParentNodeStateSelectedList();					
			if (multidimensionalCoord.length == 0) {
				multidimensionalCoord = new int[1];
				multidimensionalCoord[0] = 0;
			}
			
			// Set the parameters values in the distribution 
			distribution.setMean(mean, multidimensionalCoord);
			distribution.setVariance(variance, multidimensionalCoord);
			for (int i = 0; i < constantList.size(); i++) {
				distribution.setConstantAt(i, constantList.get(i), multidimensionalCoord);
			}
			
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, resource.getString("continuousNormalDistributionParamError"), resource
					.getString("statusError"), JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Creates and shows the panel where the user can edit the discrete 
	 * node table.
	 * @param node The discrete node to create the table pan for.
	 */
	public void createDiscreteTable(Node node) {
		screen.setTable(makeTable(node), node);
		// Show the selected node
		if (screen.isCompiled()) {
			for (int i = 0; i < screen.getEvidenceTree().getRowCount(); i++) {
				if (screen.getEvidenceTree().getPathForRow(i).getLastPathComponent().toString().equals(node.toString())) {
					if (screen.getEvidenceTree().isExpanded(screen.getEvidenceTree().getPathForRow(i))) {
						screen.getEvidenceTree().collapsePath(screen.getEvidenceTree().getPathForRow(i));
					}
					else {
						screen.getEvidenceTree().expandPath(screen.getEvidenceTree().getPathForRow(i));
					}
					break;
				}
			}
		}
		screen.setAddRemoveStateButtonVisible(true);
	}

	/**
	 * This method is responsible to represent the potential table as a JTable, 
	 * including its table model.
	 * 
	 * @param no The node to get its probabilistic table as JTable.
	 * @return Returns the JTable representing the node's probabilistic table.
	 */
	public JTable makeTable(final Node node) {
		screen.getTxtDescription().setEnabled(true);
		screen.getTxtName().setEnabled(true);
		screen.getTxtDescription().setText(node.getDescription());
		screen.getTxtName().setText(node.getName());

		final JTable table;
		final PotentialTable potTab;

		/* Check if the node represents a numeric attribute */
		if (node.getStatesSize() == 0) {
			Node parent = node.getParents().get(0);
			int numClasses = parent.getStatesSize();
			double[] mean = node.getMean();
			double[] stdDev = node.getStandardDeviation();

			table = new JTable(3, numClasses + 1);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.setTableHeader(null);

			/* First column */
			table.setValueAt(parent.getName(), 0, 0);
			table.setValueAt(resource.getString("mean"), 1, 0);
			table.setValueAt(resource.getString("stdDev"), 2, 0);

			/* Other columns */
			for (int i = 0; i < numClasses; i++) {
				table.setValueAt(parent.getStateAt(i), 0, i + 1);
				table.setValueAt(mean[i], 1, i + 1);
				table.setValueAt(stdDev[i], 2, i + 1);
			}

			return table;
		}

		if (node instanceof IRandomVariable) {
			potTab = (PotentialTable)((IRandomVariable) node).getProbabilityFunction();

			table = new GUIPotentialTable(potTab).makeTable();
			
			// (feature:3315773) Allow copy/paste between JTable and Excel
			new ExcelAdapter(table);
			
			// (feature:3315761) Allow the selection of a single cell
			table.setCellSelectionEnabled(true);
			table.setRowSelectionAllowed(true);

		} else {
			// decision

			// the number of rows in this case is the number of states of the
			// node and the number of columns is always 1.
			// int rows = node.getStatesSize();
			// int columns = 1;

			// there is no potential table and the number of variables is the
			// number of parents this node has.
			potTab = null;

			table = new JTable(node.getStatesSize(), 1);
			// put the name of each state in the first and only column.
			for (int i = 0; i < node.getStatesSize(); i++) {
				table.setValueAt(node.getStateAt(i), i, 0);
			}
			
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.setTableHeader(null);

		}
		// TODO MIGRATE TO A DIFFERENT CLASS - GUI.TABLE.PROBABILISTICTABLEMODEL
		table.getModel().addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent e) {
				// Change state name or reset to its previous value.
				if (e.getColumn() == 0) {
					if (!table.getValueAt(e.getLastRow(), e.getColumn()).toString().trim().equals("")) {
						node.setStateAt(table.getValueAt(e.getLastRow(),
								e.getColumn()).toString(), e.getLastRow()
								- (table.getRowCount() - node.getStatesSize()));
					} else {
						table.revalidate();
						table.setValueAt(node.getStateAt(e.getLastRow()
								- (table.getRowCount() - node.getStatesSize())), e.getLastRow(),
								e.getColumn());
					}
				// Change the CPT cell or reset to its previous value.
				} else if (potTab != null) {
					String valueText = table.getValueAt(e.getLastRow(),
							e.getColumn()).toString().replace(',', '.');
					try {
						float value = Float.parseFloat(valueText);
						potTab.setValue((e.getColumn() - 1) * node.getStatesSize() + e.getLastRow(), value);
					} catch (NumberFormatException nfe) {
						// Just shows the error message if the value is not empty.
						if (!valueText.trim().equals("")) {
							JOptionPane.showMessageDialog(null, 
									resource.getString("numberFormatError"), 
									resource.getString("error"),
									JOptionPane.ERROR_MESSAGE);
						}
						table.revalidate();
						table.setValueAt(""
								+ potTab.getValue((e.getColumn() - 1) * node.getStatesSize() + e.getLastRow()),
								e.getLastRow(), e.getColumn());
					}
				}
			}
		});
		
		// Change the text cell editor to replace text instead of appending it for all columns.
		ReplaceTextCellEditor cellEditor = new ReplaceTextCellEditor();
		for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellEditor(cellEditor);
		}
			
		// Shows the caret while editing cell.
		table.setSurrendersFocusOnKeystroke(true);
		
		

		return table;
	}
	
	/**
	 * Shows the potential table of a desired node
	 * 
	 * @param no
	 *            a <code>Node</code> representing a node which we wanto to see the table
	 * @since
	 * @see unbbayes.prs.Node
	 * @deprecated
	 */
	public JTable makeTableOld(final Node node) {
		screen.getTxtDescription().setEnabled(true);
		screen.getTxtName().setEnabled(true);
		screen.getTxtDescription().setText(node.getDescription());
		screen.getTxtName().setText(node.getName());

		final JTable table;
		final PotentialTable potTab;
		final int variables;

		/* Check if the node represents a numeric attribute */
		if (node.getStatesSize() == 0) {
			Node parent = node.getParents().get(0);
			int numClasses = parent.getStatesSize();
			double[] mean = node.getMean();
			double[] stdDev = node.getStandardDeviation();

			table = new JTable(3, numClasses + 1);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.setTableHeader(null);

			/* First column */
			table.setValueAt(parent.getName(), 0, 0);
			table.setValueAt(resource.getString("mean"), 1, 0);
			table.setValueAt(resource.getString("stdDev"), 2, 0);

			/* Other columns */
			for (int i = 0; i < numClasses; i++) {
				table.setValueAt(parent.getStateAt(i), 0, i + 1);
				table.setValueAt(mean[i], 1, i + 1);
				table.setValueAt(stdDev[i], 2, i + 1);
			}

			return table;
		}

		if (node instanceof IRandomVariable) {
			potTab = (PotentialTable)((IRandomVariable) node).getProbabilityFunction();

			int states = 1;
			variables = potTab.variableCount();

			// calculate the number of states by multiplying the number of
			// states that each father (variables) has. Where variable 0 is the
			// node itself. That is why it starts at 1.
			/*
			 * Ex: states = 2 * 2;
			 * 
			 * |------------------------------------------------------| | Father
			 * 2 | State 1 | State 2 |
			 * |--------------|-------------------|-------------------| | Father
			 * 1 | State 1 | State 2 | State 1 | State 2 |
			 * |------------------------------------------------------| | Node
			 * State 1 | 1 | 1 | 1 | 1 | | Node State 2 | 0 | 0 | 0 | 0 |
			 * 
			 */
			states = potTab.tableSize() / node.getStatesSize();
			/*
			 * for (int count = 1; count < variables; count++) { states *=
			 * potTab.getVariableAt(count).getStatesSize(); }
			 */

			// the number of rows is the number of states the node has plus the
			// number of fathers (variables - 1, because one of the variables
			// is the node itself).
			int rows = node.getStatesSize() + variables - 1;

			// the number of columns is the number of states that we calculate
			// before plus one that is the column where the fathers names and
			// the states of the node itself will be placed.
			int columns = states + 1;

			table = new JTable(rows, columns);

			// put the name of the states of the node in the first column
			// starting in the (variables - 1)th row (number of fathers). That
			// is because on the rows before that there will be placed the
			// name of the fathers.
			for (int k = variables - 1, l = 0; k < table.getRowCount(); k++, l++) {
				table.setValueAt(node.getStateAt(l), k, 0);
			}

			// put the name of the father and its states' name in the right
			// place.
			for (int k = variables - 1, l = 0; k >= 1; k--, l++) {
				Node variable = (Node)potTab.getVariableAt(k);

				// the number of states is the multiplication of the number of
				// states of the other fathers above this one.
				states /= variable.getStatesSize();

				// put the name of the father in the first column.
				table.setValueAt(variable.getName(), l, 0);

				// put the name of the states of this father in the lth row
				// and ith column, repeating the name if necessary (for each
				// state of the father above).
				for (int i = 0; i < table.getColumnCount() - 1; i++) {
					table.setValueAt(variable.getStateAt((i / states)
							% variable.getStatesSize()), l, i + 1);
				}
			}

			// now states is the number of states that the node has.
			states = node.getStatesSize();

			// put the values of the probabilistic table in the jth row and ith
			// column, picking up the values in a double collection in potTab.
			for (int i = 1, k = 0; i < table.getColumnCount(); i++, k += states) {
				for (int j = variables - 1, l = 0; j < table.getRowCount(); j++, l++) {
					table.setValueAt("" + df.format(potTab.getValue(k + l)), j,
							i);
				}
			}

		} else {
			// decision

			// the number of rows in this case is the number of states of the
			// node and the number of columns is always 1.
			// int rows = node.getStatesSize();
			// int columns = 1;

			// there is no potential table and the number of variables is the
			// number of parents this node has.
			potTab = null;
			variables = node.getParents().size();

			table = new JTable(node.getStatesSize(), 1);
			// put the name of each state in the first and only column.
			for (int i = 0; i < node.getStatesSize(); i++) {
				table.setValueAt(node.getStateAt(i), i, 0);
			}

		}

		table.setTableHeader(null);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getModel().addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent e) {
				if (e.getLastRow() < variables - 1) {
					return;
				}
				if (e.getColumn() == 0) {
					if (!table.getValueAt(e.getLastRow(), e.getColumn())
							.equals("")) {
						node.setStateAt(table.getValueAt(e.getLastRow(),
								e.getColumn()).toString(), e.getLastRow()
								- (table.getRowCount() - node.getStatesSize()));
					}
				} else {
					String temp = table.getValueAt(e.getLastRow(),
							e.getColumn()).toString().replace(',', '.');
					try {
						float valor = Float.parseFloat(temp);
						potTab.setValue((e.getColumn() - 1) * node.getStatesSize() + e.getLastRow(), valor);
					} catch (NumberFormatException nfe) {
						JOptionPane.showMessageDialog(null, 
								resource.getString("error"), 
								resource.getString("realNumberError"),
								JOptionPane.ERROR_MESSAGE);
						table.revalidate();
						table.setValueAt(""
								+ potTab.getValue((e.getColumn() - 1) * node.getStatesSize() + e.getLastRow()),
								e.getLastRow(), e.getColumn());
					}
				}
			}
		});

		return table;
	}

	public void deleteSelected(Object selecionado) {
		
		if (selecionado instanceof Edge) {
			singleEntityNetwork.removeEdge((Edge) selecionado);
		} else if (selecionado instanceof Node) {
			singleEntityNetwork.removeNode((Node) selecionado);
		}
		
		
	}

	public void showExplanationProperties(ProbabilisticNode node) {
		screen.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		ExplanationProperties explanation = new ExplanationProperties(screen,
				singleEntityNetwork);
		explanation.setProbabilisticNode(node);
		explanation.setVisible(true);
		screen.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

}
