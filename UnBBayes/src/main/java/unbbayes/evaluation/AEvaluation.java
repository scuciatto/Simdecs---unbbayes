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
package unbbayes.evaluation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import unbbayes.evaluation.exception.EvaluationException;
import unbbayes.io.BaseIO;
import unbbayes.io.NetIO;
import unbbayes.io.XMLBIFIO;
import unbbayes.io.exception.LoadException;
import unbbayes.prs.Node;
import unbbayes.prs.bn.ProbabilisticNetwork;
import unbbayes.prs.bn.TreeVariable;
import unbbayes.util.longtask.ILongTaskProgressObserver;
import unbbayes.util.longtask.LongTaskProgressChangedEvent;

public abstract class AEvaluation implements IEvaluation {
	
	// TODO ROMMEL - REPLACE SYSOUT TO DEBUG

	// ------- VALUES THAT DO NOT CHANGE --------//

	protected ProbabilisticNetwork net;
	
	// It will change when more than one target node is allowed
	protected int targetStatesProduct;
	
	protected TreeVariable[] targetNodeList;

	public static final float UNSET_VALUE = Float.NEGATIVE_INFINITY;

	// ------- VALUES THAT DO CHANGE FOR EACH SUB-EVALUATION --------//

	protected TreeVariable[] evidenceNodeList;

	protected int statesProduct;

	protected int evidenceStatesProduct;

	// -------- OUTPUT VALUES - EVALUATION --------//

	protected float[][] evidenceSetCM;

	protected float evidenceSetPCC = AEvaluation.UNSET_VALUE;

	protected List<EvidenceEvaluation> evidenceEvaluationList;
	
	public List<EvidenceEvaluation> getEvidenceEvaluationList() {
		return this.evidenceEvaluationList;
	}

	public float[][] getEvidenceSetCM() {
		return this.evidenceSetCM;
	}
	
	public abstract float getError();

	public float getEvidenceSetPCC() throws EvaluationException {
		if (evidenceSetPCC == AEvaluation.UNSET_VALUE) {
			if (evidenceSetCM == null) {
				throw new EvaluationException(
						"Must calculate evidence set LCM before computing evidence set PCC.");
			}
			evidenceSetPCC = 0;
			for (int i = 0; i < evidenceSetCM.length; i++) {
				evidenceSetPCC += evidenceSetCM[i][i];
			}
			evidenceSetPCC /= evidenceSetCM.length;
		}
		return this.evidenceSetPCC;
	}
	
	public List<EvidenceEvaluation> getBestIndividualPCC() throws EvaluationException {
		List<EvidenceEvaluation> sortedList = new ArrayList<EvidenceEvaluation>();
		sortedList.addAll(evidenceEvaluationList);
		boolean change = true;
		while (change) {
			change = false;
			for (int i = 0; i < sortedList.size() - 1; i++) {
				EvidenceEvaluation ev1 = sortedList.get(i);
				EvidenceEvaluation ev2 = sortedList.get(i + 1);
				if (ev1.getIndividualPCC() - ev2.getIndividualPCC() < 0) {
					sortedList.set(i + 1, ev1);
					sortedList.set(i, ev2);
					change = true;
				}
			}
		}
		return sortedList;
	}
	
	public List<EvidenceEvaluation> getBestIndividualCostRate() throws EvaluationException {
		List<EvidenceEvaluation> sortedList = new ArrayList<EvidenceEvaluation>();
		sortedList.addAll(evidenceEvaluationList);
		boolean change = true;
		while (change) {
			change = false;
			for (int i = 0; i < sortedList.size() - 1; i++) {
				EvidenceEvaluation ev1 = sortedList.get(i);
				EvidenceEvaluation ev2 = sortedList.get(i + 1);
				if (ev1.getMarginalCost() - ev2.getMarginalCost() < 0) {
					sortedList.set(i + 1, ev1);
					sortedList.set(i, ev2);
					change = true;
				}
			}
		}
		return sortedList;
	}
	
	public List<EvidenceEvaluation> getBestMarginalImprovement() throws EvaluationException {
		List<EvidenceEvaluation> sortedList = new ArrayList<EvidenceEvaluation>();
		sortedList.addAll(evidenceEvaluationList);
		boolean change = true;
		while (change) {
			change = false;
			for (int i = 0; i < sortedList.size() - 1; i++) {
				EvidenceEvaluation ev1 = sortedList.get(i);
				EvidenceEvaluation ev2 = sortedList.get(i + 1);
				if (ev1.getMarginalImprovement() - ev2.getMarginalImprovement() < 0) {
					sortedList.set(i + 1, ev1);
					sortedList.set(i, ev2);
					change = true;
				}
			}
		}
		return sortedList;
	}
	
	public void evaluate(String netFileName, List<String> targetNodeNameList,
			List<String> evidenceNodeNameList, boolean onlyGCM) throws LoadException, IOException, JAXBException, EvaluationException {
		loadNetwork(netFileName);
		evaluate(targetNodeNameList, evidenceNodeNameList, onlyGCM);
	}

	public void evaluate(ProbabilisticNetwork net,
			List<String> targetNodeNameList, List<String> evidenceNodeNameList, boolean onlyGCM) throws EvaluationException {
		this.net = net;
		evaluate(targetNodeNameList, evidenceNodeNameList, onlyGCM);
	}
	
	protected void evaluate(List<String> targetNodeNameList,
			List<String> evidenceNodeNameList, boolean onlyGCM) throws EvaluationException {
		
		evidenceSetCM = computeCM(targetNodeNameList, evidenceNodeNameList);
		// As the set LCM was computed, its PCC is now unset (lazy computing)
		evidenceSetPCC = AEvaluation.UNSET_VALUE;
		
		if (!onlyGCM) {
			evidenceEvaluationList = new ArrayList<EvidenceEvaluation>();
			for (String evidenceName : evidenceNodeNameList) {
				EvidenceEvaluation evidenceEvaluation = new EvidenceEvaluation(evidenceName, getEvidenceSetPCC());
				
				// Compute individual LCM
				List<String> tempList = new ArrayList<String>();
				tempList.add(evidenceName);
				evidenceEvaluation.setLCM(computeCM(targetNodeNameList, tempList));
				
				// Compute marginal LCM
				tempList.clear();
				tempList.addAll(evidenceNodeNameList);
				tempList.remove(evidenceName);
				evidenceEvaluation.setMarginalCM(computeCM(targetNodeNameList, tempList));
				
				evidenceEvaluationList.add(evidenceEvaluation);
			}
		}

	}

	protected abstract float[][] computeCM(List<String> targetNodeNameList,
			List<String> evidenceNodeNameList) throws EvaluationException;

	protected int[] factors;
	
	/**
	 * Calculate the factors necessary to transform the linear coordinate into a multidimensional 
	 * one (which is the the state for each possible node - target and evidence).
	 * FactorForNode[i + 1] = ProductOf(NumberOfStates[i]), for all previous nodes (i).
	 */
	protected void computeFactors() {
		int size = targetNodeList.length + evidenceNodeList.length;
		if (factors == null || factors.length != size) {
		   factors = new int[size];
		}
		
		// Create one list of all nodes
		TreeVariable[] nodes = new TreeVariable[size];
		int nodeIndex = 0;
		for (int i = 0; i < targetNodeList.length; i++) {
			nodes[nodeIndex++] = targetNodeList[i];
		}
		for (int i = 0; i < evidenceNodeList.length; i++) {
			nodes[nodeIndex++] = evidenceNodeList[i];
		}
		
		factors[0] = 1;
		Node node;
		for (int i = 1; i < size; i++) {
			node = nodes[i-1];
			factors[i] = factors[i-1] * node.getStatesSize();
		}
	}
	
	/**
	 * Get the linear coordinate from the multidimensional one.
	 * LinearCoord = SumOf(StateOf[i] * FactorOf[i]), for all 
	 * possible nodes (i).
	 * 
	 * @param multidimensionalCoord Multidimensional coordinate (represented by the state for
	 * each node).
	 * @return The corresponding linear coordinate.
	 */
	protected  int getLinearCoord(int multidimensionalCoord[]) {
		computeFactors();
		int coordLinear = 0;
		int size = targetNodeList.length + evidenceNodeList.length;
		for (int v = 0; v < size; v++) {
			coordLinear += multidimensionalCoord[v] * factors[v];
		}
		return coordLinear;
	}
	
	/**
	 * Get the evidence linear coordinate from the multidimensional one. 
	 * It basically ignores the target node (for now just one).
	 * LinearCoord = SumOf(StateOf[i] * FactorOf[i]), for all 
	 * possible evidence nodes (i).
	 * 
	 * @param multidimensionalCoord Multidimensional coordinate (represented by the state for
	 * each node).
	 * @return The corresponding evidence linear coordinate.
	 */
	protected  int getEvidenceLinearCoord(int multidimensionalCoord[]) {
		computeFactors();
		int coordLinear = 0;
		int size = targetNodeList.length + evidenceNodeList.length;
		for (int v = 1; v < size; v++) {
			coordLinear += multidimensionalCoord[v] * factors[v]/factors[1];
		}
		return coordLinear;
	}

	/**
	 * Get the multidimensional coordinate from the linear one.
	 * 
	 * @param linearCoord The linear coordinate.
	 * @return The corresponding multidimensional coordinate.
	 */
	protected  int[] getMultidimensionalCoord(int linearCoord) {
		computeFactors();
		int factorI;
		int size = targetNodeList.length + evidenceNodeList.length;
		int multidimensionalCoord[] = new int[size];
		int i = size - 1;
		while (linearCoord != 0) {
			factorI = factors[i];
			multidimensionalCoord[i--] = linearCoord / factorI;
			linearCoord %= factorI;
		}
		return multidimensionalCoord;
	}

	/**
	 * It creates the target and evidence node list of <code>TreeVariable</code> and the 
	 * product of the target, evidence and total states.
	 * 
	 * @param targetNodeNameList List of the target nodes name.
	 * @param evidenceNodeNameList
	 */
	protected void init(List<String> targetNodeNameList,
			List<String> evidenceNodeNameList) {
		targetNodeList = new TreeVariable[targetNodeNameList.size()];
		evidenceNodeList = new TreeVariable[evidenceNodeNameList.size()];
		statesProduct = 1;
		targetStatesProduct = 1;
		evidenceStatesProduct = 1;

		// Create list of target TreeVariable
		int count = 0;
		for (String targetNodeName : targetNodeNameList) {
			Node targetNode = net.getNode(targetNodeName);

			targetNodeList[count] = (TreeVariable) targetNode;

			targetStatesProduct *= targetNode.getStatesSize();
			count++;
		}

		// Create list of evidence TreeVariable
		count = 0;
		for (String evidenceNodeName : evidenceNodeNameList) {
			Node evidenceNode = net.getNode(evidenceNodeName);

			evidenceNodeList[count] = (TreeVariable) evidenceNode;

			evidenceStatesProduct *= evidenceNode.getStatesSize();
			count++;
		}

		statesProduct = targetStatesProduct * evidenceStatesProduct;
	}

	protected void loadNetwork(String netFileName) throws LoadException, IOException {
		File netFile = new File(netFileName);
		String fileExt = netFileName.substring(netFileName.length() - 3);

		BaseIO io = null;
		if (fileExt.equalsIgnoreCase("xml")) {
			io = new XMLBIFIO();
		} else if (fileExt.equalsIgnoreCase("net")) {
			io = new NetIO();
		} else {
			throw new LoadException(
					"The network must be in XMLBIF 0.5 or NET format!");
		}
		net = (ProbabilisticNetwork)io.load(netFile);
	}
	
	protected static void show(float[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                System.out.printf("%6.4f ", a[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
	
	/* LONG TASK BEGIN */

	private List<ILongTaskProgressObserver> observers = new ArrayList<ILongTaskProgressObserver>();
	
	public void registerObserver(ILongTaskProgressObserver observer) {
		observers.add(observer); 
	}

	public void removeObserver(ILongTaskProgressObserver observer) {
		observers.remove(observer); 
	}

	public void notityObservers(LongTaskProgressChangedEvent event) {
		for(ILongTaskProgressObserver observer: observers){
			observer.update(event); 
		}
	}
	
	protected int maxProgress = 100;
	
	public int getMaxProgress() {
		return maxProgress;
	}
	
	protected int currentProgress = 0;
	
	public int getCurrentProgress() {
		return currentProgress;
	}
	
	public int getPercentageDone() {
		return Math.round((float)currentProgress / maxProgress * 10000);
	}
	
	protected String currentProgressStatus = "";
	
	public String getCurrentProgressStatus() {
		return currentProgressStatus;
	}
	
	protected int lastProgressUpdated = 0;
	
	/**
	 * As this progress is an approximation, it is going to be updated 
	 * until it reaches 98%. Then the progress is not going to be updated anymore 
	 * to avoid getting to 100% before the task is done.
	 */
	protected void updateProgress(int progress, String progressStatus){
		currentProgress = progress;
		currentProgressStatus = progressStatus;
		
		// Avoid updating too much.
		// Just update if the delta is greater than step (currentProgress - lastProgressUpdated > step)
		// or every third of one percent (currentProgress % step == 0)
		int step = (int)(maxProgress * 0.01/3);
		boolean update = progress - lastProgressUpdated > step || currentProgress % step == 0;
		if (update && getPercentageDone() < 9800) {
			lastProgressUpdated = currentProgress;
			LongTaskProgressChangedEvent event = new LongTaskProgressChangedEvent(getCurrentProgressStatus(), getPercentageDone()); 
			notityObservers(event); 
		}
	}
	
	protected void updateProgress(int progress){
		updateProgress(progress, ""); 
	}
	
	/* LONG TASK END */

}
