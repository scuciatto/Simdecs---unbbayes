/*
 *  UnBBayes
 *  Copyright (C) 2002, 2009 Universidade de Brasilia - http://www.unb.br
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
package unbbayes.evaluation.controller;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import unbbayes.evaluation.EvidenceEvaluation;
import unbbayes.evaluation.FastLWApproximateEvaluation;
import unbbayes.evaluation.IEvaluation;
import unbbayes.evaluation.exception.EvaluationException;
import unbbayes.evaluation.gui.EvaluationPane;
import unbbayes.gui.LongTaskProgressBar;
import unbbayes.prs.Node;
import unbbayes.prs.bn.ProbabilisticNetwork;
import unbbayes.prs.bn.ProbabilisticNode;

public class EvaluationController {
	
	private EvaluationPane evaluationPane;
	private ProbabilisticNetwork network;
	private IEvaluation evaluation;
	
	public EvaluationController(ProbabilisticNetwork network) {
		this.network = network;
		this.evaluationPane = new EvaluationPane();
		setUpEvaluation();
	}
	
	public void setUpEvaluation() {
		List<Node> nodeList = network.getNodes();
		
		Map<String, List<String>> nodeFindingMap = new HashMap<String, List<String>>();
		List<String> findingList;
		for (Node node : nodeList) {
			findingList = new ArrayList<String>(node.getStatesSize());
			for (int i = 0; i < node.getStatesSize(); i++) {
				findingList.add(node.getStateAt(i));
			}
			nodeFindingMap.put(node.getName(), findingList);
		}
		
		evaluationPane.addInputValues(nodeFindingMap);
		
		evaluationPane.setRunButtonActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				new EvaluationThread(EvaluationController.this);  // thread is called here
			}

		});
	}
	
	private void validateData() throws EvaluationException {
		StringBuffer errorMsg = new StringBuffer();
		
		try {
			Integer sampleSize = evaluationPane.getSampleSizeValue();
			if (sampleSize <= 0) {
				errorMsg.append("Sample size has to be greater than 0. \n");
			}
		} catch (NumberFormatException e) {
			errorMsg.append("Sample size has to be greater than 0. \n");
		}
		
		if (evaluationPane.getTargetNodeNameList().size() == 0) {
			errorMsg.append("There must be at least one target node selected. \n");
		}
		
		if (evaluationPane.getEvidenceNodeNameList().size() == 0) {
			errorMsg.append("There must be at least one evidence node selected. \n");
		}
		
		if (errorMsg.toString().length() > 0) {
			throw new EvaluationException(errorMsg.toString());
		}
			
	}
	
	void runEvaluation() {
		evaluationPane.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		
		try {
			validateData();
		} catch (EvaluationException e) {
			JOptionPane.showMessageDialog(evaluationPane, e.getMessage(), "Validation Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		List<String> targetNodeNameList = evaluationPane.getTargetNodeNameList();
		List<String> evidenceNodeNameList = evaluationPane.getEvidenceNodeNameList();
		int sampleSize = evaluationPane.getSampleSizeValue();
		
		evaluation = new FastLWApproximateEvaluation(sampleSize);
		// Start progress bar
		LongTaskProgressBar progress = new LongTaskProgressBar(false);
		// Set up what to do once the long task is canceled
		progress.getCancelButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishEvaluation();
			}
		});
		// Register progress bar as observer of the long task
		evaluation.registerObserver(progress);
		// Add thread to progress bar to allow canceling the operation
		progress.setThread(EvaluationThread.t);
		// Update the pane with the progress bar just created
		evaluationPane.updateProgressBarPane(progress);

		Map<String, String> nodeFindingMap = evaluationPane.getNodeConditionMap();
		ProbabilisticNode node;
		for (String nodeName : nodeFindingMap.keySet()) {
			node = (ProbabilisticNode)network.getNode(nodeName);
			for (int i = 0; i < node.getStatesSize(); i++) {
				if (nodeFindingMap.get(nodeName).equals(node.getStateAt(i))) {
					node.initMarginalList();
					node.addFinding(i);
				}
			}
		}
		
		try {
			// Run the long task
			evaluation.evaluate(network, targetNodeNameList, evidenceNodeNameList, false);
			// Set long task as done on the progress bar (100%)
			progress.setProgressBar(10000);
			
			List<EvidenceEvaluation> evidenceEvaluationList = evaluation.getBestMarginalImprovement();
			
			for (EvidenceEvaluation evidenceEvaluation : evidenceEvaluationList) {
				evidenceEvaluation.setCost(evaluationPane.getCost(evidenceEvaluation.getName()));
			}
			
			evaluationPane.setPccValue(evaluation.getEvidenceSetPCC());
			
			evaluationPane.setErrorValue(evaluation.getError());
			
			evaluationPane.addOutputValues(evidenceEvaluationList);
			
			node = (ProbabilisticNode)network.getNode(targetNodeNameList.get(0));
			
			String[] header = new String[node.getStatesSize()];
			for (int i = 0; i < node.getStatesSize(); i++) {
				header[i] = node.getStateAt(i);
			}
			
			float[][] data = evaluation.getEvidenceSetCM();
			Float[][] rowData = new Float[data.length][data[0].length];
			for (int i = 0; i < rowData.length; i++) {
				for (int j = 0; j < rowData[0].length; j++) {
					rowData[i][j] = data[i][j];
				}
			}
			
			evaluationPane.setUpOutputCM(rowData, header);
			
			evaluationPane.revalidate();
			evaluationPane.repaint();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(evaluationPane, e.getMessage(), "Evaluation Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		// Reset evidences to all finding/conditional nodes, which means they do not have findings any more.  
		network.resetEvidences();
		
		finishEvaluation();
	}
	
	private void finishEvaluation() {
		evaluationPane.resetProgressBar();
		evaluationPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	
	public JPanel getView() {
		return evaluationPane;
	}

}
