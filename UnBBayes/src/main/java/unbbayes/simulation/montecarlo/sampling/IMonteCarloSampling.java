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

import java.util.List;
import java.util.Map;

import unbbayes.prs.Node;
import unbbayes.prs.bn.ProbabilisticNetwork;
import unbbayes.util.longtask.ILongTaskProgressObservable;

//TODO ROMMEL - BREAK INTO DIFFERENT INTERFACES - MC - MCCOMPACT - LW
/*
 * TODO : since unbbayes.evaluation package is using 
 * unbbayes.simulation.montecarlo.sampling package,
 * we could not refactor these classes as plugins yet (this
 * is because evaluation has no plugin support yet).
 */
public interface IMonteCarloSampling extends ILongTaskProgressObservable {
	
	/**
	 * Returns the generated sample matrix. The row represents the ith trial and the column 
	 * represents the jth node from the sampled order. The value matrix[i][j] 
	 * represents the sampled state index (respecting the node's states order) for the 
	 * jth node in the ith trial.
	 * @return The generated sample matrix.
	 */
	public byte[][] getSampledStatesMatrix();
	
	/**
	 * Returns the generated compact sample matrix. The row represents the ith sampled state set
	 * and the column represents the jth node from the sampled order. The value matrix[i][j] 
	 * represents the sampled state index (respecting the node's states order) for the 
	 * jth node in the ith sampled state set. To get the number of times the 
	 * ith set of states was sampled use <code>getStatesSetTimesSampled()</code>.
	 * @return The generated compact sample matrix.
	 */
	public byte[][] getSampledStatesCompactMatrix();
	
	/**
	 * The number of times the ith set of states was sampled. 
	 * @return The number of times the ith set of states was sampled.
	 */
	public int[] getStatesSetTimesSampled();
	
	/**
	 * Returns the generated sample map, with key = linear coord (representing the sates sampled) and 
	 * value = number of times this key was sampled.
	 * @return The generated sample map.
	 */
	public Map<Integer,Integer> getSampledStatesMap();
	
	/**
	 * Return the order the nodes are in the sampled matrix.
	 * @return The order the nodes are in the sampled matrix.
	 */
	public List<Node> getSamplingNodeOrderQueue();
	
	/**
	 * Generates the MC sample with the given size for the given probabilistic network.
	 * @param pn Probabilistic network that will be used for sampling.
	 * @param nTrials Number of trials to generate.
	 */
	public void start(ProbabilisticNetwork pn , int nTrials);
	
	/**
	 * Get the linear coordinate from the multidimensional one.
	 * LinearCoord = SumOf(StateOf[i] * FactorOf[i]), for all 
	 * possible nodes (i).
	 * 
	 * @param multidimensionalCoord Multidimensional coordinate (represented by the state for
	 * each node).
	 * @return The corresponding linear coordinate.
	 */
	public int getLinearCoord(int multidimensionalCoord[]);

	/**
	 * Get the multidimensional coordinate from the linear one.
	 * 
	 * @param linearCoord The linear coordinate.
	 * @return The corresponding multidimensional coordinate.
	 */
	public byte[] getMultidimensionalCoord(int linearCoord);

}
