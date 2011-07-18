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
package unbbayes.simulation.montecarlo.sampling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import unbbayes.prs.Node;
import unbbayes.prs.bn.ProbabilisticNetwork;
import unbbayes.prs.bn.ProbabilisticNode;

/*
 * TODO : since unbbayes.evaluation package is using 
 * unbbayes.simulation.montecarlo.sampling package,
 * we could not refactor these classes as plugins yet (this
 * is because evaluation has no plugin support yet).
 */
/**
 * 
 * Class that implements the Monte Carlo simulation.
 * It uses forward sampling to calculate a RV's probability mass function. Based on its pmf, it then 
 * calculates the cumulative density function. Finally, a random number between 0 and 1 is generated 
 * and the sampled state is defined by the state the random number relates based on its cdf. 
 * 
 * @author Danilo Custodio
 * @author Rommel Carvalho
 *
 */
public class MapMonteCarloSampling extends AMonteCarloSampling {
	
	// TODO - ROMMEL - Actually use observable methods stuff to update the progress!!!
    
	/**
	 * Returns the generated sample matrix. The row represents the ith trial and the column 
	 * represents the jth node from the samplingNodeOrderQueue. The value matrix[i][j] 
	 * represents the sampled state index (respecting the node's states order) for the 
	 * jth node in the ith trial.
	 * @return The generated sample matrix.
	 */
	public byte[][] getSampledStatesMatrix() {
		byte [][] sampledStatesMatrix = new byte[nTrials][samplingNodeOrderQueue.size()];
		int index = 0;
		Set<Integer> keySet = sampledStatesMap.keySet();
		byte[] sampledStates;
		int value;
		for (Integer key : keySet) {
			sampledStates = getMultidimensionalCoord(key);
			value = sampledStatesMap.get(key);
			for (int i = 0; i < value; i++) {
				for (int j = 0; j < sampledStates.length; j++) {
					sampledStatesMatrix[index][j] = (byte)sampledStates[j];
				}
				index++;
			}
		}
		return sampledStatesMatrix;
	}
	
	/**
	 * Returns the generated compact sample matrix. The row represents the ith sampled state set
	 * and the column represents the jth node from the samplingNodeOrderQueue. The value matrix[i][j] 
	 * represents the sampled state index (respecting the node's states order) for the 
	 * jth node in the ith sampled state set. To get the number of times the 
	 * ith set of states was sampled use <code>getStatesSetTimesSampled()</code>.
	 * @return The generated compact sample matrix.
	 */
	public byte[][] getSampledStatesCompactMatrix() {
		if (sampledStatesMatrix == null) {
			sampledStatesMatrix = new byte[sampledStatesMap.size()][samplingNodeOrderQueue.size()];
			timesSampled = new int[sampledStatesMap.size()];
			int index = 0;
			Set<Integer> keySet = sampledStatesMap.keySet();
			byte[] sampledStates;
			int value;
			for (Integer key : keySet) {
				sampledStates = getMultidimensionalCoord(key);
				value = sampledStatesMap.get(key);
				for (int j = 0; j < sampledStates.length; j++) {
					sampledStatesMatrix[index][j] = sampledStates[j];
				}
				timesSampled[index++] = value;
			}
		}
		return sampledStatesMatrix;
	}
	
	/**
	 * The number of times the ith set of states was sampled. 
	 * @return The number of times the ith set of states was sampled.
	 */
	public int[] getStatesSetTimesSampled() {
		if (sampledStatesMatrix == null) {
			getSampledStatesCompactMatrix();
		}
		return timesSampled;
	}
	
	/**
	 * Returns the generated sample map, with key = linear coord (representing the sates sampled) and 
	 * value = number of times this key was sampled.
	 * @return The generated sample map.
	 */
	public Map<Integer,Integer> getSampledStatesMap() {
		return sampledStatesMap;
	}
	
	/**
	 * Generates the MC sample with the given size for the given probabilistic network.
	 * @param pn Probabilistic network that will be used for sampling.
	 * @param nTrials Number of trials to generate.
	 */
	public void start(ProbabilisticNetwork pn , int nTrials){
		this.pn = pn;
		this.nTrials = nTrials;
		sampledStatesMap = new HashMap<Integer, Integer>();
		samplingNodeOrderQueue = new ArrayList<Node>();		
		createSamplingOrderQueue();
		int[] sampledStates = null;
		for(int i = 0; i < nTrials; i++){						
			sampledStates = simulate();
			int key = getLinearCoord(sampledStates);
			Integer value = sampledStatesMap.get(key);
			if (value != null) {
				sampledStatesMap.put(key, ++value);
			} else {
				sampledStatesMap.put(key, 1);
			}
		}
	}
	
	/**
	 * Responsible for simulating MC for sampling.
	 * @param sampledStatesMatrix The matrix containing the sampled states for every trial. 
	 * @param nTrial The trial number to simulate.
	 */
	protected int[] simulate(){
		List<Integer> parentsIndexes = new ArrayList<Integer>();
		double[] pmf;
		int[] sampledStates = new int[samplingNodeOrderQueue.size()];
		for(int i = 0 ; i < samplingNodeOrderQueue.size(); i++){			
			ProbabilisticNode node = (ProbabilisticNode)samplingNodeOrderQueue.get(i);									
			parentsIndexes = getParentsIndexesInQueue(node);
			pmf = getProbabilityMassFunction(sampledStates, parentsIndexes, node);													
			sampledStates[i] = getState(pmf);
		}	
		return sampledStates;
	}
	
}
