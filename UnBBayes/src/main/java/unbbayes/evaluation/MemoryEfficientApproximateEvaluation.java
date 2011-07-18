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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;

import unbbayes.evaluation.exception.EvaluationException;
import unbbayes.prs.Node;
import unbbayes.prs.bn.TreeVariable;
import unbbayes.simulation.montecarlo.sampling.IMonteCarloSampling;
import unbbayes.simulation.montecarlo.sampling.MapMonteCarloSampling;
import unbbayes.util.Debug;

public class MemoryEfficientApproximateEvaluation extends AEvaluation {

	// ------- VALUES THAT DO NOT CHANGE --------//

	private int sampleSize;

	IMonteCarloSampling mc;
	
	Set<Map.Entry<Integer,Integer>> sampledSet;
	
	// It will change when more than one target node is allowed
	private TreeVariable targetNode;

	// ------- VALUES THAT DO CHANGE FOR EACH SUB-EVALUATION --------//

	private List<Node> positionNodeList;

	private int[] positionTargetNodeList;

	private int[] positionEvidenceNodeList;

	// -------- OUTPUT VALUES - EVALUATION --------//

	public float getError() {
		return (float) (1/Math.sqrt(this.sampleSize));
	}
	
	public MemoryEfficientApproximateEvaluation(int sampleSize) {
		this.sampleSize = sampleSize;
	}
	
	protected void evaluate(List<String> targetNodeNameList,
			List<String> evidenceNodeNameList, boolean onlyGCM) throws EvaluationException {
		
		// 1. Generate the MC sample from the network file
		// Trial# StateIndexForNode1 StateIndexForNode2 StateIndexForNodeJ NumerTimesSampled
		// 001 0 1 0 10
		// 002 2 0 1 35
		// ...
		// i x y z nTimes
		mc = new MapMonteCarloSampling();
		long init = System.currentTimeMillis();
		mc.start(net, sampleSize);
		long end = System.currentTimeMillis();
		Debug.println("Time elapsed for sampling: " + (float)(end-init)/1000);
		sampledSet = mc.getSampledStatesMap().entrySet();
		Debug.println("Sample size: " + sampleSize);
		Debug.println("Sample map size: " + sampledSet.size());
//		show(sampleMatrix);

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
		positionNodeList = mc.getSamplingNodeOrderQueue();

		for (int i = 0; i < positionTargetNodeList.length; i++) {
			positionTargetNodeList[i] = positionNodeList.indexOf((Node)targetNodeList[i]);
		}

		for (int i = 0; i < positionEvidenceNodeList.length; i++) {
			positionEvidenceNodeList[i] = positionNodeList.indexOf((Node)evidenceNodeList[i]);
		}
		
		// 2. Count # of occurrences of evidence nodes given target nodes
		Map<Integer, Integer> frequencyEvidenceGivenTargetMap = new HashMap<Integer, Integer>();
		Map<Integer, Integer> frequencyTargetMap = new HashMap<Integer, Integer>();
		
		for (Entry<Integer, Integer> entry : sampledSet) {
			int value = entry.getValue(); 
			int[] dim = getMultidimensionalCoord(mc.getMultidimensionalCoord(entry.getKey()));
			Integer previousValue = frequencyTargetMap.get(dim[0]);
			if (previousValue != null) {
				frequencyTargetMap.put(dim[0], previousValue + value);
			} else {
				frequencyTargetMap.put(dim[0], value);
			}
			int key = getLinearCoord(dim);
			previousValue = frequencyEvidenceGivenTargetMap.get(key);
			if (previousValue != null) {
				frequencyEvidenceGivenTargetMap.put(key, previousValue + value);
			} else {
				frequencyEvidenceGivenTargetMap.put(key, value);
			}
			
		}

		// 3. Compute probabilities for evidence nodes given target nodes
		Map<Integer, Float> postProbEvidenceGivenTargetMap = new HashMap<Integer, Float>();
		Set<Map.Entry<Integer, Integer>> frequencyEvidenceGivenTargetSet = frequencyEvidenceGivenTargetMap.entrySet();
		float[][] postProbEvidenceGivenTarget = new float[evidenceStatesProduct][targetNode.getStatesSize()];
		for (Entry<Integer, Integer> entry : frequencyEvidenceGivenTargetSet) {
			float value = entry.getValue(); 
			int key = entry.getKey();
			int[] dim = getMultidimensionalCoord(key);
			float prob = value/frequencyTargetMap.get(dim[0]);
			postProbEvidenceGivenTargetMap.put(key, prob);
			postProbEvidenceGivenTarget[getEvidenceLinearCoord(dim)][dim[0]] = prob;
		}
//		show(postProbEvidenceGivenTarget);
		
		// 4. Compute probabilities for target given evidence using evidence
		// given target
		// P(T|E) = P(E|T)P(T)
		Map<Integer, Float> postProbTargetGivenEvidenceMap = new HashMap<Integer, Float>();
		Map<Integer, Float> normalizationMap = new HashMap<Integer, Float>();
		Set<Map.Entry<Integer, Float>> postProbEvidenceGivenTargetSet = postProbEvidenceGivenTargetMap.entrySet();
		try {
			net.compile();
		} catch (Exception e) {
			throw new EvaluationException(e.getMessage());
		}
		for (Entry<Integer, Float> entry : postProbEvidenceGivenTargetSet) {
			int key = entry.getKey();
			int[] dim = getMultidimensionalCoord(key);
			float prob = entry.getValue() * targetNode.getMarginalAt(dim[0]);
			postProbTargetGivenEvidenceMap.put(getLinearCoord(dim), prob);
			Float previousProb = normalizationMap.get(getEvidenceLinearCoord(dim));
			if (previousProb != null) {
				normalizationMap.put(getEvidenceLinearCoord(dim), previousProb + prob);
			} else {
				normalizationMap.put(getEvidenceLinearCoord(dim), prob);
			}
		}
		
		Set<Map.Entry<Integer, Float>> postProbTargetGivenEvidenceSet = postProbTargetGivenEvidenceMap.entrySet();
		float[][] postProbTargetGivenEvidence = new float[targetNode.getStatesSize()][evidenceStatesProduct];
		for (Entry<Integer, Float> entry : postProbTargetGivenEvidenceSet) {
			int key = entry.getKey();
			int[] dim = getMultidimensionalCoord(key);
			float prob = entry.getValue() / normalizationMap.get(getEvidenceLinearCoord(dim));
			entry.setValue(prob);
			postProbTargetGivenEvidence[dim[0]][getEvidenceLinearCoord(dim)] = prob;
		}
//		show(postProbTargetGivenEvidence);
		
		// 5. Compute probabilities for target given target and set as CM
		// P(T|T) = P(T|E)P(E|T)
		int N = targetNode.getStatesSize();
		float[][] CM = new float[N][N];
		// ikj pure row
        //long start = System.currentTimeMillis(); 
        for (int i = 0; i < N; i++) {
            float[] arowi = postProbTargetGivenEvidence[i];
            float[] crowi = CM[i];
            for (int k = 0; k < evidenceStatesProduct; k++) {
                float[] browk = postProbEvidenceGivenTarget[k];
                float aik = arowi[k];
                for (int j = 0; j < N; j++) {
                    crowi[j] += aik * browk[j];
                }
            }
        }

        long end = System.currentTimeMillis();
        Debug.println("Time elapsed for computing CM: " + (float)(end-init)/1000);

		return CM;
	}

	
	/**
	 * Get the multidimensional evaluation coordinate from the monte carlo
	 * multidimensional coordinate.
	 * 
	 * @param dim Monte carlo multidimensional coordinate.
	 * @return The corresponding multidimensional coordinate.
	 */
	protected int[] getMultidimensionalCoord(byte[] dim) {
		int size = targetNodeList.length + evidenceNodeList.length;
		int multidimensionalCoord[] = new int[size];
		for (int i = 0; i < positionTargetNodeList.length; i++) {
			multidimensionalCoord[i] = dim[positionTargetNodeList[i]];
		}
		for (int i = 0; i < positionEvidenceNodeList.length; i++) {
			multidimensionalCoord[i + positionTargetNodeList.length] = dim[positionEvidenceNodeList[i]];
		}
		return multidimensionalCoord;
	}

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
			sampleSize = 50000000;
		}

		MemoryEfficientApproximateEvaluation evaluationApproximate = new MemoryEfficientApproximateEvaluation(sampleSize);
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
