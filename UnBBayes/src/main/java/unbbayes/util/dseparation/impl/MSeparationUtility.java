/**
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
package unbbayes.util.dseparation.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import unbbayes.prs.INode;
import unbbayes.util.Debug;
import unbbayes.util.dseparation.IDSeparationUtility;


/**
 * @author Shou Matsumoto
 * Checks d-separation criterion using moral separation (m-separation) criterion,
 * proposed by Lauritzen, Dawid, Larsen and Leimer (1990), which is proven to be
 * equivalent to d-separation criteria.
 * 
 * Basic steps (given sets X, Y and the separator S):
 * 
 * 1 - generate a graph containing only X, Y, Z and their ancestors.
 * 2 - turn the graph moral (connect/marry nodes having a common child)
 * 3 - eliminate edge orientation
 * 4 - if every single path connecting X to Y is blocked by S, then S d-separates X to Y
 * 
 */
public class MSeparationUtility implements IDSeparationUtility {
	
	
  	
	
	/**
	 * Default constructor.
	 * It's protected in order to simplify inheritance.
	 */
	protected MSeparationUtility() {
		super();		
	}
	
	/**
	 * Default constructor method.
	 * @return a new instance of MSeparationutility
	 */
	public static MSeparationUtility newInstance() {
		MSeparationUtility ret = new MSeparationUtility();
		return ret;
	}
	
	/**
  	 * Builds a map containing a temporally reference to all adjacent nodes, given a key node.
  	 * It is used to represent a undirected graph.
  	 * 
  	 * Mapping model:
  	 * 		node (key) --<<mapped to>>--> set of all adjacent nodes obtained by {@link Node#getAdjacents()}
  	 * 
  	 * It also guarantees that the map is "closed" (destination nodes are also keys). 
  	 * I.E. if "key" is mapped to "node", then "node" must be a key in this map too.
  	 * 
  	 * It does not go down/up recursively (so that it guarantees only the nodes within
  	 * the parameter is mapped).
  	 * 
  	 * @param allKeys
  	 * 
  	 * @see {@link Node#getAdjacents()}
  	 * @see {@link Node#makeAdjacents()}
  	 */
	public Map<INode, Set<INode>> buildClosedAdjacentNodeMap(Set<INode> allKeys) {
		Map<INode, Set<INode>> ret = new HashMap<INode, Set<INode>>();
		
		for (INode key : allKeys) {
			
			// build up the adjacent set
			Set<INode> adjacents = new HashSet<INode>();
			for (INode node : key.getAdjacentNodes()) {
				// make sure only those nodes within allKeys are mapped
				if (allKeys.contains(node)) {
					adjacents.add(node);
				}
			}
			ret.put(key, adjacents);			
		}
		return ret;
	}
	
	/**
	 * If a node has a common child, make them adjacent (marry them - this is something moral).
	 * 
  	 * It also guarantees that the map is "closed" (destination nodes are also keys). 
  	 * I.E. if "key" is mapped to "node", then "node" must be a key in this map too.
  	 * 
	 * @param closedAdjacentNodeMap : OBS. in/out parameter. 
	 * @return same as closedAdjacentNodeMap
	 */
	public Map<INode, Set<INode>> makeItMoral(Map<INode, Set<INode>> closedAdjacentNodeMap) {
		
		// for each key (all nodes inside this "closed" map), marry the parents
		for (INode key : closedAdjacentNodeMap.keySet()) {
			
			// obtain all parents (Note: they might not be mapped by closedAdjacentNodeMap)
			List<INode> parents = key.getParentNodes();
			
			// marries the parents 2-by-2
			for (int i = 0; i < parents.size() - 1; i++) {
				// extracts one of the parents and its mapping
				INode parent1 = parents.get(i);
				Set<INode> setForParent1 = closedAdjacentNodeMap.get(parent1);
				if (setForParent1 == null) {
					// if this parent is not mapped, no need to marry it (we must retain the map "closed")
					continue;
				}
				// get the other pair
				for (int j = i + 1; j < parents.size(); j++) {
					// extracts the other the parent and its mapping
					INode parent2 = parents.get(j);
					Set<INode> setForParent2 = closedAdjacentNodeMap.get(parent2);
					if (setForParent2 == null) {
						// if this parent is not mapped, no need to marry
						continue;
					}
					// we are sure now that both parents are mapped, so the map remains "closed"
					// make them adjacent each other
					setForParent1.add(parent2);
					setForParent2.add(parent1);
				}
			}
		}
		
		return closedAdjacentNodeMap;
	}
	
	/**
	 * Does the {@link #getRoutes(INode, INode, Map)} recursively.
	 * This is implemented here because I don't want public or protected methods to be recursive...
	 * Don't ask me why.
	 * Make sure from != to
	 * @param processedPath (input): registers the already visited nodes, in order to prevent cycle. Not null.
	 * @param nodesNotToContain : nodes that a returned path should not contain. The algorithm will ignore 
	 * It is concatenated to the return list, as a prefix.
	 * @param maxRoutes : maximum number of routes to obtain.
	 * @param deadNodes : nodes that we are certain that there is no route to setTo, for the upper scope.
	 */
	private Set<List<INode>> getRoutesRec(INode from, Set<INode> setTo, Map<INode, Set<INode>> closedAdjacentNodeMap, List<INode> processedPath, 
			Set<INode> nodesNotToContain, int maxRoutes, Set<INode> deadNodes) {
		
		Set<List<INode>> ret = new HashSet<List<INode>>(); // Initialize the return value
		
		if (maxRoutes <= 0) {
			return ret;
		}
		
		
		// mark the current node as "evaluated", but don't use processedPath directly, since we don't want it to be an output parameter
		List<INode> processingPath = new ArrayList<INode>(processedPath);
		processingPath.add(from);
		
		// initialize the set of adjacent nodes
		Set<INode> adjacentSet = null;
		if (closedAdjacentNodeMap != null) {
			// if we have a pre-defined adjacency map, use it
			adjacentSet = closedAdjacentNodeMap.get(from);
		} else {
			// since we don't have an adjacency map, assume as a directed graph (use only children).
			adjacentSet = new HashSet<INode>(from.getChildNodes());
		}
		
		// initialize a set of dead nodes for my scope
		// note that if a node is dead for my scope (currently processing path), it may not be dead for my upper scope (another path)
		// that's why I must create deadNodes for my scope
		Set<INode> deadNodesForMyScope = new HashSet<INode>(deadNodes);
		
		for (INode adjacent : adjacentSet) {
			
			if (deadNodes.contains(adjacent)) {
				// we know dead nodes have no path to the setTo...
				continue;
			}
			if (processingPath.contains(adjacent)) {
				// this is a cicle. Ignore this sub-path
				continue;
			}
			if (nodesNotToContain.contains(adjacent)) {
//				Debug.println(this.getClass(),"\nFinding found: " + adjacent.toString());
//				for (INode node : processingPath) {
//					Debug.print(" | " + node.toString());
//				}
//				Debug.println("");
				// since no path should contain nodes within nodesNotToContain, we should ignore such adjacent nodes
				continue;
			}
			if (setTo.contains(adjacent)) {
				// path found!
				List<INode> path = new ArrayList<INode>(processingPath);
				path.add(adjacent);
				ret.add(path);
				maxRoutes--;
				
//				Debug.println(this.getClass(),"\n!!Found path!!");
//				for (INode node : path) {
//					Debug.print(" | " + node.toString());
//				}
//				Debug.println("");
				
				if (maxRoutes > 0 ) {
					// if we should find more routes, lets continue
					continue;	
				} else {
					// maxRoutes has exceeded
					break;
				}
			}
			
			// recursive call
			Set<List<INode>> rec = this.getRoutesRec(adjacent, setTo, closedAdjacentNodeMap, processingPath, nodesNotToContain, maxRoutes, deadNodesForMyScope);
			if (rec.size() <= 0) {
				// we recursively know that there is no path from adjacent to setTo, so, it is dead
				deadNodesForMyScope.add(adjacent);
			}else {
				// we found some path
				maxRoutes -= rec.size();
			}
			ret.addAll(rec);
		}
		
		return ret;
	}
	
	/**
	 * Obtains a set of path/routes between two nodes, including themselves.
	 * Cycles and auto-relationships are not counted as new routes.
	 * @param from : a node to start from
	 * @param to : a destination node
	 * @param closedAdjacentNodeMap : a map indicating all node's adjacency. 
	 * @param nodesNotToContain : nodes that a returned path should not contain. The algorithm will ignore
	 * a path if it contains any node within it.
	 * If set to null, this method will start using {@link INode#getChildren()} to build a directed path.
	 * @param maxRoutes : maximum number of routes to obtain.
	 * @return : a set of all path from "from" to "to". The path is represented as a list containing all
	 * nodes included in the path. Since it is a list, it stores the visit order as well.
	 */
	public Set<List<INode>> getRoutes(INode from, INode to, Map<INode, Set<INode>> closedAdjacentNodeMap, Set<INode> nodesNotToContain, int maxRoutes) {
		Set<INode> singleSet = new HashSet<INode>(1);
		singleSet.add(to);
		return this.getRoutes(from, singleSet, closedAdjacentNodeMap, nodesNotToContain, maxRoutes);
	}
	
	
	/**
	 * Obtains a set of path/routes between two nodes, including themselves.
	 * Cycles are not counted as new routes.
	 * @param from : a node to start from
	 * @param setTo : a set of possible destination node. A returning path will contain (only) one node within setTo at the end of path.
	 * @param closedAdjacentNodeMap : a map indicating all node's adjacency. 
	 * If set to null, this method will start using {@link INode#getChildren()} to build a directed path.
	 * @param nodesNotToContain : nodes that a returned path should not contain. The algorithm will ignore
	 * a path if it contains any node within it.
	 * @param maxRoutes : maximum number of routes to obtain.
	 * @return : a set of all path from "from" to "to". The path is represented as a list containing all
	 * nodes included in the path. Since it is a list, it stores the visit order as well.
	 */
	public Set<List<INode>> getRoutes(INode from, Set<INode> setTo, Map<INode, Set<INode>> closedAdjacentNodeMap, Set<INode> nodesNotToContain, int maxRoutes) {
		
		
		
		// treating special case: auto-relationship ("from" is adjacent to itself and from == to)
		if (setTo.contains(from)) {
			throw new RuntimeException("from == to");
			
//			Set<List<INode>> ret = new HashSet<List<INode>>();	// returning set		
//			// initialize the set of adjacent nodes
//			Set<INode> adjacentSet = null;
//			if (closedAdjacentNodeMap != null) {
//				// if we have a pre-defined adjacency map, use it
//				adjacentSet = closedAdjacentNodeMap.get(from);
//			} else {
//				// since we don't have an adjacency map, assume as a directed graph (use only children).
//				adjacentSet = new HashSet<INode>(from.getChildNodes());
//			}
//			
//			// if "from" was adjacent to itself, we shall consider from -> from a correct path
//			if (adjacentSet.contains(from)) {
//				List<INode> autoRelationPath = new ArrayList<INode>();
//				autoRelationPath.add(from);
//				autoRelationPath.add(from);				
//				ret.add(autoRelationPath);
//			} else {
//				// just return an empty set below
//			}
		}
		
		// the method should not throw null pointer exception because of nodes to be ignored
		// so, initialize it
		if (nodesNotToContain == null) {
			nodesNotToContain = new HashSet<INode>();
		}
		
		// normal case: recursive call considering the "current path" as a empty list of nodes
		return this.getRoutesRec(from, setTo, closedAdjacentNodeMap, new ArrayList<INode>(), nodesNotToContain, maxRoutes, new HashSet<INode>());
	}
	
	
	/**
	 * Obtains a set of path/routes between two nodes, including themselves.
	 * Cycles are not counted as new routes.
	 * This is equals to {@link #getRoutesRec(INode, INode, Map, List, null)}
	 * @param from : a node to start from
	 * @param to : a destination node
	 * @param closedAdjacentNodeMap : a map indicating all node's adjacency. 
	 * a path if it contains any node within it.
	 * If set to null, this method will start using {@link INode#getChildren()} to build a directed path.
	 * @return : a set of all path from "from" to "to". The path is represented as a list containing all
	 * nodes included in the path. Since it is a list, it stores the visit order as well.
	 */
	public Set<List<INode>> getRoutes(INode from, INode to, Map<INode, Set<INode>> closedAdjacentNodeMap) {
		return this.getRoutes(from, to, closedAdjacentNodeMap, null, Integer.MAX_VALUE);
	}
	
	/**
	 * Obtains a set of path/routes between two nodes, including themselves.
	 * This is the same as calling {@link #getRoutes(INode, INode, Map)} setting the Map as null.
	 * Cycles are not counted as new routes.
	 * @param from : a node to start from
	 * @param to : a destination node
	 * If set to null, this method will start using {@link INode#getChildren()} to build a directed path.
	 * @return : a set of all path from "from" to "to". The path is represented as a list containing all
	 * nodes included in the path. Since it is a list, it stores the visit order as well.
	 * @see #getRoutes(INode, INode, Map)
	 */
	public Set<List<INode>> getRoutes (INode from, INode to) {
		return this.getRoutes(from, to, null, null, Integer.MAX_VALUE);
	}
	
	
	/**
	 * Obtains recursively all ancestors of a given node.
	 * I just don't want a public method to be recursive... Don't ask me why.
	 * @param node
	 * @param nodesToStop : the recursive search for further ancestors will stop if any of those nodes are detected as direct ancestor.
	 *                      Only the current recursion will stop, so, only the nodes which are exclusively ancestors
	 *                      of nodesToStop will be ignored.
	 * @return set of nodes
	 */
	private Set<INode> getAllAncestorsRec(INode node, Set<INode> nodesToStop) {
		Set<INode> ret = new HashSet<INode>();		
		try {
			for (INode parent : node.getParentNodes()) {
				if (!nodesToStop.contains(parent)) {
					ret.add(parent);
					ret.addAll(this.getAllAncestorsRec(parent, nodesToStop));
				}
			}
		} catch (NullPointerException npe) {
			// we can assume that there is no parent...
			Debug.println(this.getClass(), npe.getMessage(), npe);
		}		
		return ret;
	}
	
	/**
	 * Obtains recursively all descendants of a given node.
	 * I just don't want a public method to be recursive... Don't ask me why.
	 * @param node
	 * @param nodesToStop : the recursive search for further descendants will stop if any of those nodes are detected.
	 *                      Only the current recursion will stop, so, only the nodes which are exclusively descendants
	 *                      of nodesToStop will be ignored.
	 * @param visitor : visitor to be executed when any of nodesToStop is detected. A node inside nodesToStop
	 *                  will be passed as argument. The returned value will be added to return. Set it to null if nothing must be done.
	 * @return set of nodes
	 */
	private Set<INode> getAllDescendantsRec(INode node, Set<INode> nodesToStop, MSeparationUtilityNodeVisitor visitor) {
		Set<INode> ret = new HashSet<INode>();		
		try {
			for (INode child : node.getChildNodes()) {
				if (!nodesToStop.contains(child)) {
					ret.add(child);
					ret.addAll(this.getAllDescendantsRec(child, nodesToStop, visitor));
				} else if (visitor != null) {
					Collection c = visitor.visit(child);
					if (c != null) {
						ret.addAll(c);
					}
				}
			}
		} catch (NullPointerException npe) {
			// we can assume that there is no parent...
			Debug.println(this.getClass(), npe.getMessage(), npe);
		}		
		return ret;
	}
	
	/**
	 * Method to get all ancestors of a given set of nodes.
	 * It is equivalent to {@link #getAllAncestors(Set, null)}
	 * @see #getAllAncestors(Set, Set)
	 * @param nodes : nodes to be analyzed.
	 * @return : a set of nodes
	 */
	public Set<INode> getAllAncestors(Set<INode> nodes) {
		return this.getAllAncestors(nodes, null);
	}
	
	
	/**
	 * Method to get all ancestors of a given set of nodes.
	 * @param nodes : nodes to be analyzed.
	 * @param nodesToStop : the recursive search for further ancestors will stop if any of those nodes are detected as direct ancestor.
	 *                      Only the current recursion will stop, so, only the nodes which are exclusively ancestors
	 *                      of nodesToStop will be ignored.
	 * @return : a set of nodes not including nodesToStop and their unique ancestors (nodes which are
	 *           ancestors of only nodesToStop)
	 */
	protected Set<INode> getAllAncestors(Set<INode> nodes, Set<INode> nodesToStop) {
		Set<INode> ret = new HashSet<INode>();	
		if (nodesToStop == null) {
			nodesToStop = new HashSet<INode>(0);
		}
		for (INode node : nodes) {
			// searches recursivelly
			ret.addAll(this.getAllAncestorsRec(node, nodesToStop));	
		}
		return ret;
	}
	
	
	/**
	 * Method to get all descendants of a given set of nodes.
	 * @param nodes : nodes to be analyzed.
	 * @return : a set of nodes not including nodesToStop and their unique descendants (nodes which are
	 *           descendants of only nodesToStop)
	 */
	public Set<INode> getAllDescendants(Set<INode> nodes) {
		return this.getAllDescendants(nodes, null, null);
	}
	
	
	/**
	 * Method to get all descendants of a given set of nodes.
	 * @param nodes : nodes to be analyzed.
	 * @param nodesToStop : the recursive search for further descendants will stop if any of those nodes are detected.
	 *                      Only the current recursion will stop, so, only the nodes which are exclusively descendants
	 *                      of nodesToStop will be ignored.
	 *                      Using null to this param will make this method to find all descendants
	 * @param visitor : visitor to be executed when any of nodesToStop is detected. A node inside nodesToStop
	 *                  will be passed as argument and its return will be added to return. Set it to null if nothing must be done.
	 * @return : a set of nodes not including nodesToStop and their unique descendants (nodes which are
	 *           descendants of only nodesToStop)
	 */
	protected Set<INode> getAllDescendants(Set<INode> nodes, Set<INode> nodesToStop, MSeparationUtilityNodeVisitor visitor) {
		Set<INode> ret = new HashSet<INode>();	
		if (nodesToStop == null) {
			nodesToStop = new HashSet<INode>(0);
		}
		for (INode node : nodes) {
			// searches recursivelly
			ret.addAll(this.getAllDescendantsRec(node, nodesToStop, visitor));	
		}
		return ret;
	}
	
	/**
	 * Checks if container contains at least one element in contents.
	 * @param container
	 * @param content
	 * @return true if exists a element in contents which is in container. False otherwise.
	 */
	private boolean containsAtLeastOneOf(Collection container, Collection contents) {
		for (Object content : contents) {
			if (container.contains(content)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks if the nodes within "from" are m-separated from nodes from "to", given a set
	 * of "separators".
	 * 
	 * Lauritzen, Dawid, Larsen and Leimer (1990), says that m-separation is equivalent to d-separation.
	 * 
	 * @param graph : NOT USED BY THIS IMPLEMENTATION (since it uses only the parents/children of the nodes)
	 * @param from : set 1 of nodes which m-separation is going to be tested
	 * @param to : set 2 of nodes which m-separation is going to be tested
	 * @param separators : set of separators.
	 * @return : true if "from" is m-separated with "to" given "separators". False otherwise.
	 */
	public boolean isDSeparated(Set<INode> consideredNodes, Set<INode> from, Set<INode> to, Set<INode> separators) {
		
		// lets guarantee that no input parameters are changed after method execution
		if (from != null) {
			from = new HashSet<INode>(from);
		} else {
			from = new HashSet<INode>(0);
		}
		if (to != null) {
			to = new HashSet<INode>(to);
		} else {
			to = new HashSet<INode>(0);
		}
		if (consideredNodes != null) {
			consideredNodes = new HashSet<INode>(consideredNodes);
		} else {
			consideredNodes = new HashSet<INode>(0);
		}
		if (separators != null) {
			separators = new HashSet<INode>(separators);
		} else {
			separators = new HashSet<INode>(0);
		}
		
		// initial check
		if (from.isEmpty()) {
			return false;
		}
		if (to.isEmpty()) {
			return false;
		}
		
		// start m-separation algorithm
		
		// step 1 - use only nodes within "from", "to", "separators" and their ancestors.
		Set<INode> usedNodes = new HashSet<INode>();	// we assume a Set will prevent adding same nodes
		usedNodes.addAll(from);
		usedNodes.addAll(to);
		usedNodes.addAll(separators);
		usedNodes.addAll(this.getAllAncestors(from));
		usedNodes.addAll(this.getAllAncestors(to));
		usedNodes.addAll(this.getAllAncestors(separators));
		
		// step 2 & 3 - create a non-oriented representation of this graph (containing only usedNodes)
		Map<INode, Set<INode>> mapRepresentingNonOrientedGraph = this.buildClosedAdjacentNodeMap(usedNodes);
		
		// step 2 & 3 - make it moral: propose parents' marriage...
		mapRepresentingNonOrientedGraph = this.makeItMoral(mapRepresentingNonOrientedGraph);
		
		
		/*
		 * Optimization attempts:
		 * 	#1 - If a node n has the i-th adjacent node A(n,i) with no active path to query node, no adjacent node
		 *      A(n,j) as j != i has an active path to query node passing by A(n,i). So, there is no need to 
		 *      test such path again.
		 *  #2 - Ancestors and descendants of query nodes are not d-separated if they are not blocked by a finding.
		 * 		So, we do not have to test d-separation for (a) ancestors of queries which are not
		 * 		ancestors of findings; (b) descendants of queries which are not descendants of findings.
		 *  #3 - If a child node is a query or a finding, its parent's moralyzed path will exist at any testing
		 *      context (no matter what nodes are being tested; those nodes would be moralyzed).
		 *  #4 - Since we are testing path existence (path with no findings blocking its way), we can assume
		 *  	that if a node n has an active path to query, any node having an active path to n has also an
		 *  	active path to query. In that case, n may be considered as query node as well (having a path
		 *  	to n means directly that there is a path to query too).
		 */
		
		// optimization attempt #2 and #4
		to.addAll(this.getAllAncestors(to, separators));
		
		// optimization attempt #2 and #4 and some initializations for attempt #3
		Set<INode> reachableSeparators = new HashSet<INode>();
		to.addAll(
				this.getAllDescendants(
						to, 
						separators, 
						new MSeparationUtilityNodeVisitor (reachableSeparators) {
							public Collection visit(Object ... param) {
								// we are just adding to set reachableSeparators the findings that are reachable from query.
								// we assume param[0] is the finding which getAllDescendants above has detected.
								// reachableSeparators will be used after this in order to do optimization attempt #3
								((Set)this.attribute).add(param[0]); // attribute is reachableSeparators
								return null;	// do nothing more
							}
						}
				)
		);
		
		/* 
		 * optimization attempt #3
		 * Since param is a finding which is a descendant of query, this means that this finding has at least 1 parent
		 * which has an active path to query. Since every ancestors of findings are present for every possible
		 * contexts at m-separation algorithm, and all parents of this finding are moralyzed (connected), 
		 * all ancestors of this finding has an active path to query as well if they have an active path to any parent
		 * of this finding.
		 */
		to.addAll(this.getAllAncestors(reachableSeparators, separators));
		
		
		// we should not test a path going to itself. If there is, it is obviously not d-separated
		if (this.containsAtLeastOneOf(to, from)) {
			return false;
		}
		
		
		// step 4 - if each path between "from" and "to" are blocked by any "separator", the graph is m-separated
		// TODO optimize this, since this is becoming a very heavy procedure...
		for (INode nodeFrom : from) {
			if (this.getRoutes(nodeFrom, to, mapRepresentingNonOrientedGraph, separators, 1).size() > 0) {
				return false;
			}
		}
		
		// all paths between "from" and "to" are blocked by "separators", so, it is m-separated
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see unbbayes.util.dseparation.IDSeparationUtility#getAllDSeparatedNodes(java.util.Set, java.util.Set, java.util.Set)
	 */
	public Set<INode> getAllDSeparatedNodes(Set<INode> consideredNodes,
			Set<INode> from, Set<INode> separators) {
		
		Set<INode> ret = new HashSet<INode>();
		
		// lets guarantee that no input parameters are changed after method execution
		if (from != null) {
			from = new HashSet<INode>(from);
		} else {
			from = new HashSet<INode>(0);
		}
		if (consideredNodes != null) {
			consideredNodes = new HashSet<INode>(consideredNodes);
		} else {
			consideredNodes = new HashSet<INode>(0);
		}
		if (separators != null) {
			separators = new HashSet<INode>(separators);
		} else {
			separators = new HashSet<INode>(0);
		}
		
		
		// initial check
		if (from.isEmpty()) {
			return ret;
		}
		if (consideredNodes.isEmpty()) {
			return ret;
		}
		
		// start m-separation algorithm
		
		// step 1 - use only nodes within "from", "consideredNodes", "separators" and their ancestors.
		Set<INode> usedNodes = new HashSet<INode>();	// we assume a Set will prevent adding same nodes
		usedNodes.addAll(from);
		usedNodes.addAll(consideredNodes);
		usedNodes.addAll(separators);
		usedNodes.addAll(this.getAllAncestors(from));
		usedNodes.addAll(this.getAllAncestors(consideredNodes));
		usedNodes.addAll(this.getAllAncestors(separators));
		
		// step 2 & 3 - create a non-oriented representation of this graph (containing only usedNodes)
		Map<INode, Set<INode>> mapRepresentingNonOrientedGraph = this.buildClosedAdjacentNodeMap(usedNodes);
		
		// step 2 & 3 - make it moral: propose parents' marriage...
		mapRepresentingNonOrientedGraph = this.makeItMoral(mapRepresentingNonOrientedGraph);
		
		
		
		/*
		 * Optimization attempts:
		 * 	#1 - If a node n has the i-th adjacent node A(n,i) with no active path to query node, no adjacent node
		 *      A(n,j) as j != i has an active path to query node passing by A(n,i). So, there is no need to 
		 *      test such path again.
		 *  #2 - Ancestors and descendants of query nodes are not d-separated if they are not blocked by a finding.
		 * 		So, we do not have to test d-separation for (a) ancestors of queries which are not
		 * 		ancestors of findings; (b) descendants of queries which are not descendants of findings.
		 *  #3 - If a child node is a query or a finding, its parent's moralyzed path will exist at any testing
		 *      context (no matter what nodes are being tested; those nodes would be moralyzed).
		 *  #4 - Since we are testing path existence (path with no findings blocking its way), we can assume
		 *  	that if a node n has an active path to query, any node having an active path to n has also an
		 *  	active path to query. In that case, n may be considered as query node as well (having a path
		 *  	to n means directly that there is a path to query too).
		 */
		
		// optimization attempt #2 and #4
		from.addAll(this.getAllAncestors(from, separators));
		
		// optimization attempt #2 and #4 and some initializations for attempt #3
		Set<INode> reachableSeparators = new HashSet<INode>();
		from.addAll(
				this.getAllDescendants(
						from, 
						separators, 
						new MSeparationUtilityNodeVisitor (reachableSeparators) {
							public Collection visit(Object ... param) {
								// we are just adding to set reachableSeparators the findings that are reachable from query.
								// we assume param[0] is the finding which getAllDescendants above has detected.
								// reachableSeparators will be used after this in order to do optimization attempt #3
								((Set)this.attribute).add(param[0]); // attribute is reachableSeparators
								return null;	// do nothing more
							}
						}
				)
		);
		
		/* 
		 * optimization attempt #3 and #4
		 * Since param is a finding which is a descendant of query, this means that this finding has at least 1 parent
		 * which has an active path to query. Since every ancestors of findings are present for every possible
		 * contexts at m-separation algorithm, and all parents of this finding are moralyzed (connected), 
		 * all ancestors of this finding has an active path to query as well if they have an active path to any parent
		 * of this finding.
		 */
		from.addAll(this.getAllAncestors(reachableSeparators, separators));
		
		// it is obvious that there is no self-d-separation, so, lets remove from and separators from consideredNodes
		consideredNodes.removeAll(from);
		consideredNodes.removeAll(separators);
		
		// step 4 - if there is no path from the considered node to "from", not-blocked by separator, 
		// the considered node is m-separated from "from"
		for (INode consideredNode : consideredNodes) {
			// optimization attempt #1 is implemented by getRoutesRec within this method.
			if (this.getRoutes(consideredNode, from, mapRepresentingNonOrientedGraph, separators, 1).size() <= 0) {
				ret.add(consideredNode);
			} else {
				// optimization attempt #4
				from.add(consideredNode);
			}
		}
		
		return ret;
	}
	
	
	/**
	 * Visitor Pattern.
	 * Does some action triggered by some event during m-separation. 
	 * @author Shou Matsumoto
	 *
	 */
	public abstract class MSeparationUtilityNodeVisitor {
		protected Object attribute;
		public MSeparationUtilityNodeVisitor(){super();};
		public MSeparationUtilityNodeVisitor(Object attribute){
			this.attribute = attribute;
		}
		public abstract Collection visit(Object ... param);
	}

}
