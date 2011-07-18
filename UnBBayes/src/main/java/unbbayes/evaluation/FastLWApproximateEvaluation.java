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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import unbbayes.evaluation.exception.EvaluationException;
import unbbayes.prs.Node;
import unbbayes.prs.bn.TreeVariable;
import unbbayes.simulation.likelihoodweighting.ILikelihoodWeightingSampling;
import unbbayes.simulation.likelihoodweighting.sampling.LikelihoodWeightingSampling;
import unbbayes.util.longtask.ILongTaskProgressObserver;
import unbbayes.util.longtask.LongTaskProgressChangedEvent;

public class FastLWApproximateEvaluation extends AEvaluation implements ILongTaskProgressObserver {

	// ------- VALUES THAT DO NOT CHANGE --------//

	protected int sampleSize;
	
	protected ILikelihoodWeightingSampling lw;

	// It will change when more than one target node is allowed
	protected TreeVariable targetNode;

	// ------- VALUES THAT DO CHANGE FOR EACH SUB-EVALUATION --------//

	protected byte[][] sampleMatrix;
	protected float[] sampleWeight;
	
	protected List<Node> positionNodeList;

	protected int[] positionTargetNodeList;

	protected int[] positionEvidenceNodeList;
	
	// -------- OUTPUT VALUES - EVALUATION --------//

	public float getError() {
		return (float) (1/Math.sqrt(this.sampleSize));
	}
	
	public FastLWApproximateEvaluation(int sampleSize) {
		this.sampleSize = sampleSize;
	}
	
	protected void evaluate(List<String> targetNodeNameList,
			List<String> evidenceNodeNameList, boolean onlyGCM) throws EvaluationException {
		
		// Set the maximum progress for this long task
		setMaxProgress(evidenceNodeNameList.size());
		// Set the current progress as 1% of the maximum allowed.
		updateProgress((int)(maxProgress * 0.01)); 
		
		// 1. Generate the MC sample from the network file
		// Trial# StateIndexForNode1 StateIndexForNode2 StateIndexForNodeJ
		// 001 0 1 0
		// 002 2 0 1
		// ...
		// i x y z
		lw = new LikelihoodWeightingSampling();
		// Register this long task as observer of the sampling task to get a better approximation of the total progress
		lw.registerObserver(this);
//		long init = System.currentTimeMillis();
		lw.start(net, sampleSize);
//		long end = System.currentTimeMillis();
//		System.out.println("Time elapsed for sampling: " + (float)(end-init)/1000);
//		init = System.currentTimeMillis();
		sampleMatrix = lw.getSampledStatesMatrix();
		sampleWeight = lw.getFullStatesSetWeight();
//		end = System.currentTimeMillis();
//		System.out.println("Time elapsed for matrix: " + (float)(end-init)/1000);
//		System.out.println();

		super.evaluate(targetNodeNameList, evidenceNodeNameList, onlyGCM);

	}
	
	protected float[][] computeCM(List<String> targetNodeNameList,
			List<String> evidenceNodeNameList) throws EvaluationException {

		init(targetNodeNameList, evidenceNodeNameList);

		long init = System.currentTimeMillis();

		// FIXME For now let's just consider the simple case of having just one
		// target node!
		targetNode = targetNodeList[0];
		if (targetNodeList.length != 1) {
			throw new EvaluationException("For now, just one target node is accepted!");
		}

		// Get the position in the sampled matrix of target and evidence nodes
		positionTargetNodeList = new int[targetNodeList.length];
		positionEvidenceNodeList = new int[evidenceNodeList.length];

		// Position of the nodes in the sampled matrix.
		positionNodeList = lw.getSamplingNodeOrderQueue();

		for (int i = 0; i < positionTargetNodeList.length; i++) {
			positionTargetNodeList[i] = positionNodeList.indexOf((Node)targetNodeList[i]);
		}

		for (int i = 0; i < positionEvidenceNodeList.length; i++) {
			positionEvidenceNodeList[i] = positionNodeList.indexOf((Node)evidenceNodeList[i]);
		}

		// 2. Count weight of evidence nodes given target nodes
		float[] weightEvidenceGivenTargetList = new float[statesProduct];
		float[] weightEvidenceList = new float[targetStatesProduct];

		// Iterate over all cases in the LW sample
		for (int i = 0; i < sampleMatrix.length; i++) {
			updateProgress(currentProgress + 1);
			// Row to compute the weight
			int row = 0;
			int currentStatesProduct = evidenceStatesProduct;
			for (int j = positionTargetNodeList.length - 1; j >= 0; j--) {
				byte state = sampleMatrix[i][positionTargetNodeList[j]];
				row += state * currentStatesProduct;
				currentStatesProduct *= positionNodeList.get(positionTargetNodeList[j]).getStatesSize();
			}

			// Add to total weight for evidence nodes independent of state
			weightEvidenceList[(int) (row / evidenceStatesProduct)] += sampleWeight[i];

			currentStatesProduct = evidenceStatesProduct;
			for (int j = 0; j < positionEvidenceNodeList.length; j++) {
				currentStatesProduct /= positionNodeList.get(
						positionEvidenceNodeList[j]).getStatesSize();
				byte state = sampleMatrix[i][positionEvidenceNodeList[j]];
				row += state * currentStatesProduct;
			}

			// Add to total weight for specific evidences
			weightEvidenceGivenTargetList[row] += sampleWeight[i];
		}

		// 3. Compute probabilities for evidence nodes given target nodes
		float[] postProbEvidenceGivenTarget = new float[statesProduct];
		for (int i = 0; i < postProbEvidenceGivenTarget.length; i++) {
			float n = weightEvidenceList[(int) (i / evidenceStatesProduct)];
			if (n != 0) {
				postProbEvidenceGivenTarget[i] = (float) weightEvidenceGivenTargetList[i]
						/ n;
			}
		}

		// 4. Compute probabilities for target given evidence using evidence
		// given target
		// P(T|E) = P(E|T)P(T)
		float[] postProbTargetGivenEvidence = new float[statesProduct];
		int row = 0;
		float prob = 0.0f;
		float[] normalizationList = new float[evidenceStatesProduct];
		try {
			net.compile();
		} catch (Exception e) {
			throw new EvaluationException(e.getMessage());
		}
		for (int i = 0; i < targetNode.getStatesSize(); i++) {
			for (int j = 0; j < evidenceStatesProduct; j++) {
				row = j + i * evidenceStatesProduct;
				prob = postProbEvidenceGivenTarget[row]
						* targetNode.getMarginalAt(i);
				postProbTargetGivenEvidence[row] = prob;
				normalizationList[j] += prob;
			}
		}

		float norm = 0;
		for (int i = 0; i < postProbTargetGivenEvidence.length; i++) {
			norm = normalizationList[i % evidenceStatesProduct];
			if (norm != 0) {
				postProbTargetGivenEvidence[i] /= norm;
			}
		}

		// 5. Compute probabilities for target given target
		// P(T|T) = P(T|E)P(E|T)
		float[] postProbTargetGivenTarget = new float[(int) Math.pow(targetNode
				.getStatesSize(), 2)];
		int statesSize = targetNode.getStatesSize();
		row = 0;
		int index = 0;
		for (int i = 0; i < statesProduct; i++) {
			for (int j = 0; j < statesSize; j++) {
				row = ((int) (i / evidenceStatesProduct)) * statesSize + j;
				index = (i % evidenceStatesProduct) + j * evidenceStatesProduct;
				postProbTargetGivenTarget[row] += postProbTargetGivenEvidence[i]
						* postProbEvidenceGivenTarget[index];
			}
		}

		// 6. Set CM
		float[][] CM = new float[statesSize][statesSize];
		for (int i = 0; i < statesSize; i++) {
			for (int j = 0; j < statesSize; j++) {
				CM[i][j] = postProbTargetGivenTarget[i * statesSize + j];
			}
		}
		
		long end = System.currentTimeMillis();
		System.out.println("Time elapsed for computing CM: " + (float)(end-init)/1000);

		return CM;
	}
	
	/* LONG TASK BEGIN */
	
	private int previousSamplingProgress = 0;
	
	/**
	 * Compute an approximation of the maximum size for the progress of this long task and 
	 * set it as the value of maxProgress.
	 */
	protected void setMaxProgress(int evidenceNodeListSize) {
    	maxProgress = sampleSize * (2 * evidenceNodeListSize + 2);
    }
	
	/**
	 * To have a better approximation of the current progress, we have to keep track of the 
	 * sampling process used in this long task. 
	 */
	public void update(LongTaskProgressChangedEvent status) {
		
		int samplingProgress = (int)((float)status.getPercentageDone() / 10000 * sampleSize);
		// Add the delta sampling progress (samplingProgress - previousSamplingProgress) if delta > 0
		int delta = (samplingProgress - previousSamplingProgress);
		if (delta > 0) {
			updateProgress(currentProgress + delta);
			previousSamplingProgress = samplingProgress;
		}
	}
	
	/* LONG TASK END */


	public static void main(String[] args) throws Exception {
		
		boolean runSmallTest = false;
		boolean onlyGCM = true;

		List<String> targetNodeNameList = new ArrayList<String>();
		List<String> evidenceNodeNameList = new ArrayList<String>();
		String netFileName = "";
		int sampleSize = 0;
		if (runSmallTest) {
			targetNodeNameList = new ArrayList<String>();
			targetNodeNameList.add("Springler");
	
			evidenceNodeNameList = new ArrayList<String>();
			evidenceNodeNameList.add("Cloudy");
			evidenceNodeNameList.add("Rain");
			evidenceNodeNameList.add("Wet");
	
			netFileName = "src/test/resources/testCases/evaluation/WetGrass.xml";
			sampleSize = 100000;
		} else {
			targetNodeNameList = new ArrayList<String>();
			targetNodeNameList.add("TargetType");
	
			evidenceNodeNameList = new ArrayList<String>();
			evidenceNodeNameList.add("UHRR_Confusion");
			evidenceNodeNameList.add("ModulationFrequency");
			evidenceNodeNameList.add("CenterFrequency");
			evidenceNodeNameList.add("PRI");
			evidenceNodeNameList.add("PRF");
	
			netFileName = "src/test/resources/testCases/evaluation/AirID.xml";
			sampleSize = 500000;
		}

		// APPROXIMATE //
		FastLWApproximateEvaluation evaluationApproximate = new FastLWApproximateEvaluation(sampleSize);
		evaluationApproximate.evaluate(netFileName, targetNodeNameList,
				evidenceNodeNameList, onlyGCM);
		
		System.out.println("----TOTAL------");
		
		System.out.println("LCM:\n");
		show(evaluationApproximate.getEvidenceSetCM());
		System.out.println("\n");
		
		System.out.println("PCC: ");
		System.out.printf("%2.2f\n", evaluationApproximate.getEvidenceSetPCC() * 100);
		
		if (!onlyGCM) {
			System.out.println("\n\n\n");
			System.out.println("----MARGINAL------");
			System.out.println("\n\n");
			
			List<EvidenceEvaluation> list = evaluationApproximate.getBestMarginalImprovement();
			
			for (EvidenceEvaluation evidenceEvaluation : list) {
				
				System.out.println("-" + evidenceEvaluation.getName() + "-");
				System.out.println("\n\n");
				
				System.out.println("LCM:\n");
				show(evidenceEvaluation.getMarginalCM());
				
				System.out.println("\n");
				
				System.out.println("PCC: ");
				System.out.printf("%2.2f\n", evidenceEvaluation.getMarginalPCC() * 100);
				
				System.out.println("\n");
				
				System.out.println("Marginal Improvement: ");
				System.out.printf("%2.2f\n", evidenceEvaluation.getMarginalImprovement() * 100);
				
				System.out.println("\n\n");
			}
			
			System.out.println("\n");
			System.out.println("----INDIVIDUAL PCC------");
			System.out.println("\n\n");
			
			list = evaluationApproximate.getBestIndividualPCC();
			
			for (EvidenceEvaluation evidenceEvaluation : list) {
				
				System.out.println("-" + evidenceEvaluation.getName() + "-");
				System.out.println("\n\n");
				
				System.out.println("LCM:\n");
				show(evidenceEvaluation.getIndividualLCM());
				
				System.out.println("\n");
				
				System.out.println("PCC: ");
				System.out.printf("%2.2f\n", evidenceEvaluation.getIndividualPCC() * 100);
				
				System.out.println("\n\n");
				
				// Add random costs for each
				evidenceEvaluation.setCost((new Random()).nextFloat() * 1000);
			}
			
			System.out.println("\n");
			System.out.println("----INDIVIDUAL PCC------");
			System.out.println("\n\n");
			
			list = evaluationApproximate.getBestIndividualCostRate();
			
			for (EvidenceEvaluation evidenceEvaluation : list) {
				
				System.out.println("-" + evidenceEvaluation.getName() + "-");
				System.out.println("\n\n");
				
				System.out.println("PCC: ");
				System.out.printf("%2.2f\n", evidenceEvaluation.getIndividualPCC() * 100);
				
				System.out.println("\n");
				
				System.out.println("Cost: ");
				System.out.printf("%2.2f\n", evidenceEvaluation.getCost());
				
				System.out.println("\n");
				
				System.out.println("Cost Rate: ");
				System.out.printf("%2.2f\n", evidenceEvaluation.getMarginalCost() * 100);
				
				System.out.println("\n\n");
			}
		}
	}
}
