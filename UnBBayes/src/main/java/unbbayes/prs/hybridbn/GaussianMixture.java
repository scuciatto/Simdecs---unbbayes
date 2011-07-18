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
package unbbayes.prs.hybridbn;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import unbbayes.io.XMLBIFIO;
import unbbayes.io.exception.LoadException;
import unbbayes.prs.Graph;
import unbbayes.prs.Node;
import unbbayes.prs.bn.ProbabilisticNetwork;
import unbbayes.prs.bn.ProbabilisticNode;
import unbbayes.prs.bn.TreeVariable;
import unbbayes.util.SortUtil;
import unbbayes.util.extension.bn.inference.IInferenceAlgorithm;

/**
 * Gaussian Mixture inference algorithm. It calculates the 
 * prior (compilation) for the discrete nodes and the mean and variance for the continuous nodes.
 * The propagation is not yet implemented.
 * 
 * @author Rommel Carvalho
 * @deprecated Continuous node is no longer supported in UnBBayes core. It has 
 * now been replaced by the CPS plugin available at http://sourceforge.net/projects/prognos/.
 */
public class GaussianMixture implements IInferenceAlgorithm {
	
	protected ProbabilisticNetwork pn;
	protected ProbabilisticNetwork clonedPN;
	protected List<Node> nodeOrderQueue;
	private List<Node> discreteNodeList;
	private List<Node> continuousNodeList;
	

	/** Load resource file from util */
  	private static ResourceBundle resource = unbbayes.util.ResourceController.newInstance().getBundle(
  			unbbayes.util.resources.UtilResources.class.getName());
	
	/**
	 * Return the order the nodes are in the sampled matrix.
	 * @return The order the nodes are in the sampled matrix.
	 */
	public List<Node> getNodeOrderQueue() {
		return nodeOrderQueue;
	}
	
	/**
	 * Default constructor - created for plugin support
	 */
	public GaussianMixture(){
		super();
	}
	
	public GaussianMixture(ProbabilisticNetwork pn){		
		this.setNetwork(pn);
		// the code below was migrated into init()
//		this.clonedPN = clonePN(pn);
//		discreteNodeList = new ArrayList<Node>();
//		continuousNodeList = new ArrayList<Node>();
//		for (Node node : pn.getNodes()) {
//			if (node.getType() == Node.CONTINUOUS_NODE_TYPE) {
//				continuousNodeList.add(node);
//			} else {
//				discreteNodeList.add(node);
//			}
//		}
		// init was migrated into setNetwork
//		init();
	}
	
	protected void init(){
		this.clonedPN = clonePN(pn);
		discreteNodeList = new ArrayList<Node>();
		continuousNodeList = new ArrayList<Node>();
		for (Node node : pn.getNodes()) {
			if (node.getType() == Node.CONTINUOUS_NODE_TYPE) {
				continuousNodeList.add(node);
			} else {
				discreteNodeList.add(node);
			}
		}
		nodeOrderQueue = new ArrayList<Node>();		
		createOrderQueue();
	}
	
	/**
	 * Creates the queue of the nodes that are going to be analyzed.
	 */
	protected void createOrderQueue(){
		// Keeps track of the nodes that have already been added to the queue (nodeAddedList[nodeIndex]=true).
		boolean[] nodeAddedList = new boolean[continuousNodeList.size()];
		initOrderQueue(nodeAddedList);											
		for(int i = 0; i < nodeOrderQueue.size(); i++){
			// All children of continuous nodes are also continuous.
			Node node = nodeOrderQueue.get(i);
			addToOrderQueue(node.getChildren(), nodeAddedList);			
		}		
	}
	
	/**
	 * Initializes the queue with the nodes that are root. In other words. 
	 * It will put in the queue the nodes that do not have parents.
	 * @param nodeAddedList Keeps track of the nodes that have already been added to the queue (nodeAddedList[nodeIndex]=true).
	 */
	protected void initOrderQueue(boolean[] nodeAddedList){
		for (int i = 0; i < continuousNodeList.size(); i++) {
			Node node = continuousNodeList.get(i);
			boolean hasContinuousParent = false;
			for (Node parentNode : node.getParents()) {
				if (parentNode.getType() == Node.CONTINUOUS_NODE_TYPE) {
					hasContinuousParent = true;
					break;
				}
			}
			if(!hasContinuousParent) {
				nodeAddedList[i]= true;					
				nodeOrderQueue.add(node);
			}
		}			
	}
	
	/**
	 * Take the children of a node that have already been added to the queue. Analyze them
	 * one by one and add the child that is not in the queue yet. 
	 * @param children Children of a node that is already in the queue.
	 * @param nodeAddedList Nodes that have already been added to the queue.
	 */
	protected void addToOrderQueue(ArrayList<Node> children, boolean[] nodeAddedList){
		for(int i = 0 ; i < children.size(); i++){
			Node n1 = children.get(i);
			for(int j = 0 ; j < continuousNodeList.size(); j++){
				Node n2 = continuousNodeList.get(j);
				if(n1.getName().equals(n2.getName())){
					if(!nodeAddedList[j]){
						nodeOrderQueue.add(n1);						
						nodeAddedList[j] = true;						
						break;						
					}										
				}				
			}	
		}	
	}
	

	/*
	 * (non-Javadoc)
	 * @see unbbayes.util.extension.bn.inference.IInferenceAlgorithm#run()
	 */
	public void run() throws IllegalStateException {
		for(int i = 0; i < nodeOrderQueue.size(); i++) {
			
			Node node = nodeOrderQueue.get(i);
			List<Node> discreteParentList = new ArrayList<Node>();
			List<Node> continuousParentList = new ArrayList<Node>();
			for (Node parentNode : clonedPN.getNode(node.getName()).getParents()) {
				if (parentNode.getType() == Node.PROBABILISTIC_NODE_TYPE) {
					discreteParentList.add(parentNode);
				} else if (parentNode.getType() == Node.CONTINUOUS_NODE_TYPE) {
					continuousParentList.add(parentNode);
				}
			}
			SortUtil.sortNodeListByName(discreteParentList);
			SortUtil.sortNodeListByName(continuousParentList);
			
			// The max of possible networks to be compiled to get its posterior is the
			// number of discrete parents this continuous node has.
			// But there might be two parent nodes in the same PN, in that case the network used
			// will be the same.
			// The purpose of creating a network with all non-continuous nodes connected to a 
			// parent of the current continuous node is to come up with its posterior distribution.
			// This is a hybrid approach. We use Junction Tree where possible (discrete nodes) and
			// Weighted Gaussian Sum for the rest (continuous nodes).
			// Initializes all discrete nodes as not visited.
			Map<String, Boolean> nodeVisitedBeforeMap = new HashMap<String, Boolean>();
			for (Node discreteNode : pn.getNodes()) {
				if (discreteNode.getType() == Node.PROBABILISTIC_NODE_TYPE) {
					nodeVisitedBeforeMap.put(discreteNode.getName(), false);
				}
			}
			List<Node> nodeInNetworkList;
			boolean nodeVisitedBefore;
			for (int j = 0; j < discreteParentList.size(); j++) {
				nodeVisitedBefore = nodeVisitedBeforeMap.get(discreteParentList.get(j).getName());
				if (!nodeVisitedBefore) {
					nodeInNetworkList = new ArrayList<Node>();
					addAdjacentNodes(clonedPN.getNode(discreteParentList.get(j).getName()), nodeInNetworkList);
					List<Node> nodeToRemoveList = new ArrayList<Node>();
					for (Node nodeToRemove : clonedPN.getNodes()) {
						if (!nodeInNetworkList.contains(nodeToRemove)) {
							nodeToRemoveList.add(nodeToRemove);
						}
					}
					for (Node nodeToRemove : nodeToRemoveList) {
						clonedPN.removeNode(nodeToRemove);
					}
					// Add the calculated marginal to the initial network (pn).
					// We already know that every node here is discrete.
					try {
						clonedPN.compile();
					} catch (Exception e) {
						throw new IllegalStateException(e);
					}
					for (Node nodeToGetMarginal : clonedPN.getNodes()) {
						TreeVariable variableToGetMarginal = (TreeVariable)nodeToGetMarginal;
						TreeVariable variable = (TreeVariable)pn.getNode(nodeToGetMarginal.getName());
						float[] values = new float[variable.getStatesSize()];
						for (int stateIndex = 0; stateIndex < variable.getStatesSize(); stateIndex++) {
							values[stateIndex] = variableToGetMarginal.getMarginalAt(stateIndex);
						}
						variable.initMarginalList();
						variable.setMarginalProbabilities(values);
						
						// Add its name to the list of already visited nodes.
						nodeVisitedBeforeMap.put(nodeToGetMarginal.getName(), true);
					}
					clonedPN = clonePN(this.pn);
				}
			}
			
			// Now we have the posterior of all parents of the current continuous node.
			// Calculate Weighted Gaussian Sum (from Symbolic Probabilistic Inference with both 
			// Discrete and Continuous Variables, appendix C)
			// First lets calculate the mean SumOf(Prob[Parents(node)] * PartialMean), for every 
			// normal distribution function possible (combination of parents' states).
			CNNormalDistribution cDistribution = ((ContinuousNode)node).getCnNormalDistribution();
			double[] partialMeanList = new double[cDistribution.functionSize()];
			double[] partialVarianceList = new double[cDistribution.functionSize()];
			double[] probabilityList = new double[cDistribution.functionSize()];
			double weightedMean = 0.0;
			for (int ndfIndex = 0; ndfIndex < cDistribution.functionSize(); ndfIndex++) {
				// Each normal distribution function has the mean SumOf(PartialMean), for every normal
				// distribution in the function (one for each continuous parent and one for the noise
				// normal distribution). As each continuous parent distribution is multiplied by a 
				// constant, its PartialMean = constant * MeanWithoutConstant.
				// First we add the mean of the noise normal distribution.
				partialMeanList[ndfIndex] = cDistribution.getMean(ndfIndex);
				// Each normal distribution function has the variance SumOf(PartialVariance), for every normal
				// distribution in the function (one for each continuous parent and one for the noise
				// normal distribution). As each continuous parent distribution is multiplied by a 
				// constant, its PartialVariance = constant^2 * VarianceWithoutConstant.
				// For the variance, we first add the variance of the noise normal distribution.
				partialVarianceList[ndfIndex] = cDistribution.getVariance(ndfIndex);
				// Then, for each continuous parent we add constant * MeanWithoutConstant for the PartialMean 
				// and constant^2 * VarianceWithoutConstant for the PartialVariance.
				double meanWithoutConstant;
				double varianceWithoutConstant;
				for (int parentIndex = 0; parentIndex < cDistribution.getContinuousParentList().size(); parentIndex++) {
					TreeVariable variable = (TreeVariable)cDistribution.getContinuousParentList().get(parentIndex);
					// By the time we get here, the continuous parent already calculated its mean and variance previously.
					meanWithoutConstant = variable.getMarginalAt(ContinuousNode.MEAN_MARGINAL_INDEX);
					varianceWithoutConstant = variable.getMarginalAt(ContinuousNode.VARIANCE_MARGINAL_INDEX);
					partialMeanList[ndfIndex] += cDistribution.getConstantAt(parentIndex, ndfIndex) * meanWithoutConstant;
					partialVarianceList[ndfIndex] += Math.pow(cDistribution.getConstantAt(parentIndex, ndfIndex), 2) * varianceWithoutConstant;
				}
				
				// Now we get the configuration of its parents states to calculate its probability.
				int[] parentsStatesConfiguration = cDistribution.getMultidimensionalCoord(ndfIndex);
				probabilityList[ndfIndex] = 1.0;
				for (int parentIndex = 0; parentIndex < parentsStatesConfiguration.length; parentIndex++) {
					probabilityList[ndfIndex] *= ((TreeVariable)pn.getNode(discreteParentList.get(parentIndex).getName())).getMarginalAt(parentsStatesConfiguration[parentIndex]);
				}
				
				// Finally, calculate the weighted gaussian sum SumOf(Prob[Parents(node)] * PartialMean).
				weightedMean += probabilityList[ndfIndex] * partialMeanList[ndfIndex];
				
				// We can only calculate the weightedVariance after we have the final result 
				// for the weightedMean.
			}
			
			// Now that we have the final weightedMean, we can calculate the weightedVariance.
			// WeightedVariance = SumOf(Prob[Parents(node)] * (PartialVariance + PartialMean^2 - WeightedMean^2))
			double weightedVariance = 0.0;
			for (int ndfIndex = 0; ndfIndex < cDistribution.functionSize(); ndfIndex++) {
				weightedVariance += probabilityList[ndfIndex] * (partialVarianceList[ndfIndex] + Math.pow(partialMeanList[ndfIndex], 2) - Math.pow(weightedMean, 2));
			}
			
			// Add the mean and variance as its marginal in the TreeVariable.
			float[] values = new float[node.getStatesSize()];
			values[ContinuousNode.MEAN_MARGINAL_INDEX] = (float)weightedMean;
			values[ContinuousNode.VARIANCE_MARGINAL_INDEX] = (float)weightedVariance;
			((TreeVariable)node).initMarginalList();
			((TreeVariable)node).setMarginalProbabilities(values);
		}
	}
	
	protected void addAdjacentNodes(Node node, List<Node> nodeInNetwork) {
		nodeInNetwork.add(node);
		for (Node child : node.getChildren()) {
			if (child instanceof ProbabilisticNode && !nodeInNetwork.contains(child)) {
				addAdjacentNodes(child, nodeInNetwork);
			}
		}
		for (Node parent : node.getParents()) {
			if (parent instanceof ProbabilisticNode && !nodeInNetwork.contains(parent)) {
				addAdjacentNodes(parent, nodeInNetwork);
			}
		}
	}

	/**
	 * Return the indexes (sampling order) in the queue for the parents of a given node. 
	 * @param node The node to retrieve the parents for finding the indexes.
	 * @return List of indexes (sampling order) of a node's parents in the queue.
	 */
	protected List<Integer> getParentsIndexesInQueue(ProbabilisticNode node){
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
	protected Integer getIndexInQueue(Node node){
		for(int i = 0 ; i <nodeOrderQueue.size();i++){
			if(node.getName().equals(nodeOrderQueue.get(i).getName())){				
				return i;				
			}			
		}	
		return null;	
	}
	
	/**
	 * As I am not sure if the clone methods are corrected. I decided to clone the network by
	 * saving it in a file and loading again as another network.
	 * @param network
	 * @return
	 */
	protected ProbabilisticNetwork clonePN(ProbabilisticNetwork network) {
		ProbabilisticNetwork clone = null;
		
		XMLBIFIO io = new XMLBIFIO();
		try {
			// TODO ROMMEL - change this name to a default one but that will not cause concurrent problems.
			File file = new File("clone.xml");
			io.save(file, network);
			clone = (ProbabilisticNetwork)io.load(file);
			file.delete();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (ClassCastException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return clone;
	}


	/*
	 * (non-Javadoc)
	 * @see unbbayes.util.extension.bn.inference.IInferenceAlgorithm#setNetwork(unbbayes.prs.Graph)
	 */
	public void setNetwork(Graph g) throws IllegalArgumentException {
		this.pn = (ProbabilisticNetwork)g;
		this.pn.resetEvidences();
		this.init();
	}
	
	/*
	 * (non-Javadoc)
	 * @see unbbayes.util.extension.bn.inference.IInferenceAlgorithm#getNetwork()
	 */
	public Graph getNetwork() {
		return this.pn;
	}

	/* (non-Javadoc)
	 * @see unbbayes.util.extension.bn.inference.IInferenceAlgorithm#getDescription()
	 */
	public String getDescription() {
		return this.resource.getString("gaussianMixtureAlgorithmDescription");
	}

	/* (non-Javadoc)
	 * @see unbbayes.util.extension.bn.inference.IInferenceAlgorithm#getName()
	 */
	public String getName() {
		return this.resource.getString("gaussianMixtureAlgorithmName");
	}

	/*
	 * (non-Javadoc)
	 * @see unbbayes.util.extension.bn.inference.IInferenceAlgorithm#reset()
	 */
	public void reset() {
		this.pn.resetEvidences();
		this.run();
	}

	/* (non-Javadoc)
	 * @see unbbayes.util.extension.bn.inference.IInferenceAlgorithm#propagate()
	 */
	public void propagate() {
		// TODO ROMMEL - Implement propagation
		throw new RuntimeException("Not implemented yet.");
	}
	
	
	
}
