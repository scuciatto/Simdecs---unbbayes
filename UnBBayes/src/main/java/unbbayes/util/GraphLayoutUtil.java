package unbbayes.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import unbbayes.prs.Network;
import unbbayes.prs.Node;

public class GraphLayoutUtil {
	
	protected Network network;
	protected List<List<Node>> graphLevelMatrix;
	
	protected HashMap<Node, Integer> nodeIndexMap;
	protected boolean[] nodeAddedList;
	protected int[] nodeLevelList;
	
	public GraphLayoutUtil(Network network) {
		this.network = network;
		graphLevelMatrix = new ArrayList<List<Node>>();
		createGraphLevelMatrix();
	}
	
	public void doLayout() {
		double x;
		double y;
		for (int i = 0; i < graphLevelMatrix.size(); i++) {
			List<Node> nodeList = graphLevelMatrix.get(i);
			y = i * 150 + 20;
			for (int j = 0; j < nodeList.size(); j++) {
				x = j * 300 + 50;
				nodeList.get(j).setPosition(x, y);
			}
		}
	}

	/**
	 * Creates the queue of the nodes that are going to be analyzed.
	 */
	protected void createGraphLevelMatrix() {
		// Keeps track of the nodes that have already been added to the queue (nodeAddedList[nodeIndex]=true). 
		nodeIndexMap = new HashMap<Node, Integer>();
		nodeAddedList = new boolean[network.getNodeCount()];
		nodeLevelList = new int[network.getNodeCount()];
		initGraphLevelMatrix();
		// For every root node, add its children
		do {
			List<Node> currentLevelList = graphLevelMatrix.get(graphLevelMatrix.size() - 1);
			List<Node> nextLevelList = new ArrayList<Node>();
			for(int i = 0; i < currentLevelList.size(); i++) {
				Node node = currentLevelList.get(i);
				addChildrenToNextLevel(node.getChildren(), nextLevelList);
			}
			// If at least one node was added, then add next level
			if (nextLevelList.size() > 0) {
				graphLevelMatrix.add(nextLevelList);
			}
		} while (!wereAllNodesAdded());
	}
	
	protected boolean wereAllNodesAdded() {
		for (int i = 0 ; i < network.getNodeCount(); i++) {
			if (!nodeAddedList[i]) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Initializes the queue with the nodes that are root. In other words. 
	 * It will put in the queue the nodes that do not have parents.
	 * @param nodeAddedList Keeps track of the nodes that have already been added to the queue (nodeAddedList[nodeIndex]=true).
	 */
	protected void initGraphLevelMatrix() {
		// Add root nodes
		List<Node> nodeList = new ArrayList<Node>();
		for(int i = 0 ; i < network.getNodeCount(); i++){
			Node node = network.getNodeAt(i);
			nodeIndexMap.put(node, i);
			if(node.getParents().size() == 0 ){
				nodeAddedList[i] = true;	
				nodeLevelList[i] = 0;
				nodeList.add(network.getNodeAt(i));
			}
		}
		graphLevelMatrix.add(nodeList);
	}

	/**
	 * Take the children of a node that have already been added to the queue. Analyze them
	 * one by one and add the child that is not in the queue yet. 
	 * @param children Children of a node that is already in the queue.
	 * @param nodeAddedList Nodes that have already been added to the queue.
	 */
	protected boolean addChildrenToNextLevel(ArrayList<Node> children, List<Node> nodeList) {
		boolean wasNodeAdded = false;
		int currentLevel = graphLevelMatrix.size();
		for (int i = 0 ; i < children.size(); i++) {
			Node child = children.get(i);
			int childIndex = nodeIndexMap.get(child);
			if (!nodeAddedList[childIndex]) {
				// Check if we can add this node
				List<Node> allParents = child.getParents();
				boolean allParentsAdded = true;
				boolean allParentsInDifferentLevels = true;
				for (int j = 0; j < allParents.size(); j++) {
					int parentIndex = nodeIndexMap.get(allParents.get(j));
					// If at least one parent was not added, then stop
					if (!nodeAddedList[parentIndex]) {
						allParentsAdded = false;
						break;
					}
					// If this parent was added, but it is on the same level, then stop
					if (nodeLevelList[parentIndex] == currentLevel) {
						allParentsInDifferentLevels = false;
						break;
					}
				}
				// If all parents have been added and if there is no parent on the same level
				// then we can add this node
				if (allParentsAdded && allParentsInDifferentLevels) {
					nodeList.add(child);						
					nodeAddedList[childIndex] = true;
					nodeLevelList[childIndex] = currentLevel;
					wasNodeAdded = true;
				}
			}
		}
		return wasNodeAdded;
	}

}
