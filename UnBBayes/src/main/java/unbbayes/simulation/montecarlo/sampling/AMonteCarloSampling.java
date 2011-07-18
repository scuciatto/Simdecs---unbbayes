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
import unbbayes.prs.bn.PotentialTable;
import unbbayes.prs.bn.ProbabilisticNetwork;
import unbbayes.prs.bn.ProbabilisticNode;
import unbbayes.util.longtask.ILongTaskProgressObserver;
import unbbayes.util.longtask.LongTaskProgressChangedEvent;

/*
 * TODO : since unbbayes.evaluation package is using 
 * unbbayes.simulation.montecarlo.sampling package,
 * we could not refactor these classes as plugins yet (this
 * is because evaluation has no plugin support yet).
 */
public abstract class AMonteCarloSampling implements IMonteCarloSampling {
	
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
	
	protected void updateProgress(int progress, String progressStatus) {
		currentProgress = progress;
		currentProgressStatus = progressStatus;
		// Avoid updating too much. Just update every third of one percent (maxProgress * 0.01/3)
		int step = (int)(maxProgress * 0.01/3);
		if (step > 0 && currentProgress % step == 0 && getPercentageDone() < 9800) {
			LongTaskProgressChangedEvent event = new LongTaskProgressChangedEvent(getCurrentProgressStatus(), getPercentageDone()); 
			notityObservers(event); 
		}
	}
	
	protected void updateProgress(int progress){
		updateProgress(progress, ""); 
	}
	
	/* LONG TASK END */
	
	/**
	 * Returns the generated sample matrix. The row represents the ith trial and the column 
	 * represents the jth node from the sampled order. The value matrix[i][j] 
	 * represents the sampled state index (respecting the node's states order) for the 
	 * jth node in the ith trial.
	 * @return The generated sample matrix.
	 */
	public abstract byte[][] getSampledStatesMatrix();
	
	/**
	 * Returns the generated compact sample matrix. The row represents the ith sampled state set
	 * and the column represents the jth node from the sampled order. The value matrix[i][j] 
	 * represents the sampled state index (respecting the node's states order) for the 
	 * jth node in the ith sampled state set. To get the number of times the 
	 * ith set of states was sampled use <code>getStatesSetTimesSampled()</code>.
	 * @return The generated compact sample matrix.
	 */
	public abstract byte[][] getSampledStatesCompactMatrix();
	
	/**
	 * The number of times the ith set of states was sampled. 
	 * @return The number of times the ith set of states was sampled.
	 */
	public abstract int[] getStatesSetTimesSampled();
	
	/**
	 * Returns the generated sample map, with key = linear coord (representing the sates sampled) and 
	 * value = number of times this key was sampled.
	 * @return The generated sample map.
	 */
	public abstract Map<Integer,Integer> getSampledStatesMap();
	
	/**
	 * Generates the MC sample with the given size for the given probabilistic network.
	 * @param pn Probabilistic network that will be used for sampling.
	 * @param nTrials Number of trials to generate.
	 */
	
	public abstract void start(ProbabilisticNetwork pn , int nTrials);

	protected ProbabilisticNetwork pn;
	protected int nTrials;
	protected List<Node> samplingNodeOrderQueue;
	protected Map<Integer,Integer> sampledStatesMap;

	/**
	 * Return the order the nodes are in the sampled matrix.
	 * @return The order the nodes are in the sampled matrix.
	 */
	public List<Node> getSamplingNodeOrderQueue() {
		return samplingNodeOrderQueue;
	}

	protected byte[][] sampledStatesMatrix = null;
	protected int [] timesSampled = null;
	protected int[] factors;

	/**
	 * Creates the queue of the nodes that are going to be analyzed.
	 */
	protected void createSamplingOrderQueue() {
		// Keeps track of the nodes that have already been added to the queue (nodeAddedList[nodeIndex]=true). 
		boolean[] nodeAddedList = new boolean[pn.getNodeCount()];
		initSamplingOrderQueue(nodeAddedList);											
		for(int i = 0; i < samplingNodeOrderQueue.size(); i++){
			Node node = samplingNodeOrderQueue.get(i);
			addToSamplingOrderQueue(node.getChildren(), nodeAddedList);			
		}		
	}

	/**
	 * Initializes the queue with the nodes that are root. In other words. 
	 * It will put in the queue the nodes that do not have parents.
	 * @param nodeAddedList Keeps track of the nodes that have already been added to the queue (nodeAddedList[nodeIndex]=true).
	 */
	protected void initSamplingOrderQueue(boolean[] nodeAddedList) {
		for(int i = 0 ; i < pn.getNodeCount(); i++){
			if(pn.getNodeAt(i).getParents().size() == 0 ){
				nodeAddedList[i]= true;					
				samplingNodeOrderQueue.add(pn.getNodeAt(i));
			}
		}			
	}

	/**
	 * Take the children of a node that have already been added to the queue. Analyze them
	 * one by one and add the child that is not in the queue yet. 
	 * @param children Children of a node that is already in the queue.
	 * @param nodeAddedList Nodes that have already been added to the queue.
	 */
	protected void addToSamplingOrderQueue(ArrayList<Node> children, boolean[] nodeAddedList) {
		for(int i = 0 ; i < children.size(); i++){
			Node n1 = children.get(i);
			for(int j = 0 ; j < pn.getNodeCount(); j++){
				Node n2 = pn.getNodeAt(j);
				if(n1.getName().equals(n2.getName())){
					if(!nodeAddedList[j]){
						samplingNodeOrderQueue.add(n1);						
						nodeAddedList[j] = true;						
						break;						
					}										
				}				
			}	
		}	
	}

	/**
	 * Return the indexes (sampling order) in the queue for the parents of a given node. 
	 * @param node The node to retrieve the parents for finding the indexes.
	 * @return List of indexes (sampling order) of a node's parents in the queue.
	 */
	protected List<Integer> getParentsIndexesInQueue(ProbabilisticNode node) {
		List<Integer> indexes = new ArrayList<Integer>();
		ArrayList<Node> parents = node.getParents();		
		for(int i = 0 ; i < parents.size();i++){
			Node parentNode = parents.get(i);
			indexes.add(getIndexInQueue(parentNode));						
		}	
		return indexes;		
	}

	/**
	 * Retrieves the node's index in the queue.  
	 * @param node
	 * @return
	 */
	protected Integer getIndexInQueue(Node node) {
		for(int i = 0 ; i <samplingNodeOrderQueue.size();i++){
			if(node.getName().equals(samplingNodeOrderQueue.get(i).getName())){				
				return i;				
			}			
		}	
		return null;	
	}

	/**
	 * Uses the pmf to retrieve the cdf to choose a state from a random generated number (between 0 and 1).
	 * @param pmf The probability mass function for the node RV that we want to sample the state for.
	 * @return The sampled state for a given RV (based on its pmf).
	 */
	protected byte getState(double[] pmf) {
		// Cumulative distribution function
		double[][] cdf;
		double numero = Math.random();		
		cdf = getCumulativeDistributionFunction(pmf);
		for(byte i = 0; i < cdf.length; i++) {
			if(i == 0) {				
				if (numero <= cdf[i][1]) {
					return i;							
				}
				continue;  				
			} else if (i == cdf.length - 1) {				
				if (numero > cdf[i][0]) {
					return i;	
				}				
			} else {				
				if (numero <= cdf[i][1] && numero > cdf[i][0]) {
					return i;	
				}				
			}			
		}
		return -1;				
	}

	/**
	 * Creates the cumulative distribution function (cdf) based on the node RV's pmf.
	 * @param pmf The probability mass function of the RV to calculate the cdf.
	 * @return The cumulative distribution function (cdf) for the given pmf.
	 */
	protected double[][] getCumulativeDistributionFunction(double[] pmf) {
		// Instead of using [statesSize][2] we could only use [statesSize]
		// and the upper value for the interval would be the lower value of 
		// the following state. In the last state the upper value would be 1.
		double[][] cdf = new double[pmf.length][2];		
		double atual = 0.0d;
		for(int i = 0 ; i < pmf.length; i++){
			// Lower value
			cdf[i][0] = atual;
			// Upper value
			cdf[i][1] = pmf[i] + atual;
			
			// Next lower value is equal to the previous upper value
			atual = cdf[i][1];
		}
		return cdf;
	}

	/**
	 * Creates the probability mass function based on the states sampled for the parents.
	 * @param sampledStates The states (sampledStates[nodeIndex]) sampled for the nodes (nodeIndex).
	 * @param parentsIndexes The nodeIndex for each parent.
	 * @param node The node/RV to calculate the pmf.
	 * @return The probability mass function (pmf) of the node RV.
	 */
	protected double[] getProbabilityMassFunction(int[] sampledStates, List<Integer> parentsIndexes,
			ProbabilisticNode node) {
				PotentialTable pt = node.getProbabilityFunction();
				int statesSize = node.getStatesSize();
				int nodeIndex;
				double[] pmf = new double[statesSize];
				int[] coordinates = new int[parentsIndexes.size() + 1];
				for(int i = 0; i < node.getStatesSize(); i++){				
					coordinates[0] = i;
					if(i == 0){
						for(int j = 0 ; j < parentsIndexes.size(); j++){				
							nodeIndex = parentsIndexes.get(j);
							coordinates[pt.indexOfVariable(samplingNodeOrderQueue.get(nodeIndex))] = sampledStates[nodeIndex];								
						}
					}
					pmf[i] = pt.getValue(coordinates);
				}
				/*System.out.println("Node " + node.getName());
				for (int i = 0; i < pmf.length; i++) {
					System.out.print(node.getStateAt(i) + " = " + pmf[i] + " ");
				}
				System.out.println();*/
				return pmf;
			}

	/**
	 * Calculate the factors necessary to transform the linear coordinate into a multidimensional 
	 * one (which is the the state for each possible node - target and evidence).
	 * FactorForNode[i + 1] = ProductOf(NumberOfStates[i]), for all previous nodes (i).
	 */
	protected void computeFactors() {
		int size = samplingNodeOrderQueue.size();
		if (factors == null || factors.length != size) {
		   factors = new int[size];
		}
		
		factors[0] = 1;
		Node node;
		for (int i = 1; i < size; i++) {
			node = samplingNodeOrderQueue.get(i-1);
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
	public  int getLinearCoord(int multidimensionalCoord[]) {
		computeFactors();
		int coordLinear = 0;
		int size = samplingNodeOrderQueue.size();
		for (int v = 0; v < size; v++) {
			coordLinear += multidimensionalCoord[v] * factors[v];
		}
		return coordLinear;
	}

	/**
	 * Get the multidimensional coordinate from the linear one.
	 * 
	 * @param linearCoord The linear coordinate.
	 * @return The corresponding multidimensional coordinate.
	 */
	public  byte[] getMultidimensionalCoord(int linearCoord) {
		computeFactors();
		int factorI;
		int size = samplingNodeOrderQueue.size();
		byte multidimensionalCoord[] = new byte[size];
		int i = size - 1;
		while (linearCoord != 0) {
			factorI = factors[i];
			multidimensionalCoord[i--] = (byte)(linearCoord / factorI);
			linearCoord %= factorI;
		}
		return multidimensionalCoord;
	}

}