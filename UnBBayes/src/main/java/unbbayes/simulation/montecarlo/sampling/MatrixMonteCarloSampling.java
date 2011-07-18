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
import java.util.List;
import java.util.Map;

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
public class MatrixMonteCarloSampling extends AMonteCarloSampling {
	
	public byte[][] getSampledStatesCompactMatrix() {
		return null;
	}
	
	/**
	 * Not implemented. 
	 * @return null.
	 */
	public int[] getStatesSetTimesSampled() {
		return null;
	}
	
	/**
	 * Not implemented. 
	 * @return null.
	 */
	public Map<Integer,Integer> getSampledStatesMap() {
		return null;
	}
	
	
	/**
	 * Returns the generated sample matrix.
	 * @return The generated sample matrix.
	 */
	public byte[][] getSampledStatesMatrix() {
		return sampledStatesMatrix;
	}
	
	/**
	 * Generates the MC sample with the given size for the given probabilistic network.
	 * @param pn Probabilistic network that will be used for sampling.
	 * @param nTrials Number of trials to generate.
	 */
	public void start(ProbabilisticNetwork pn , int nTrials){
		this.pn = pn;
		this.nTrials = nTrials;
		// set max value allowed for the progress
		this.maxProgress = nTrials; 
		samplingNodeOrderQueue = new ArrayList<Node>();		
		createSamplingOrderQueue();
		sampledStatesMatrix = new byte[nTrials][pn.getNodeCount()];		
		for(int i = 0; i < nTrials; i++){	
			// update the current value of the progress
			updateProgress(i);
			simulate(i);
		}
	}
	
	/**
	 * Responsible for simulating MC for sampling.
	 * @param nTrial The trial number to simulate.
	 */
	protected void simulate(int nTrial){
		List<Integer> parentsIndexes = new ArrayList<Integer>();
		double[] pmf;
		int[] sampledStates = new int[samplingNodeOrderQueue.size()];
		for(int i = 0 ; i < samplingNodeOrderQueue.size(); i++){			
			ProbabilisticNode node = (ProbabilisticNode)samplingNodeOrderQueue.get(i);									
			parentsIndexes = getParentsIndexesInQueue(node);
			pmf = getProbabilityMassFunction(sampledStates, parentsIndexes, node);													
			sampledStates[i] = getState(pmf);
			sampledStatesMatrix[nTrial][i] = (byte)sampledStates[i];
		}				
	}
	
}
