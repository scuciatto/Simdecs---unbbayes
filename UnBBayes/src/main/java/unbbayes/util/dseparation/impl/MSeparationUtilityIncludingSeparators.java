/**
 * 
 */
package unbbayes.util.dseparation.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import unbbayes.prs.INode;

/**
 * This is an extension of {@link MSeparationUtility} which
 * tests if the separators are d-separated from the query nodes
 * as well.
 * This is slightly different from the original idea of m-separation,
 * since in that case the separators are a distinct set of nodes which
 * are "out" of scope.
 * @author Shou Matsumoto
 *
 */
public class MSeparationUtilityIncludingSeparators extends MSeparationUtility {

	/**
	 * Default constructor.
	 * It's protected in order to simplify inheritance.
	 */
	protected MSeparationUtilityIncludingSeparators() {
		super();
	}

	/**
	 * Default constructor method.
	 * @return a new instance of MSeparationUtilityIncludingSeparators
	 */
	public static MSeparationUtilityIncludingSeparators newInstance() {
		MSeparationUtilityIncludingSeparators ret = new MSeparationUtilityIncludingSeparators();
		return ret;
	}

	/* (non-Javadoc)
	 * @see unbbayes.util.dseparation.impl.MSeparationUtility#getAllDSeparatedNodes(java.util.Set, java.util.Set, java.util.Set)
	 */
	@Override
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
		
		// it is obvious that there is no self-d-separation, so, lets remove "from" from consideredNodes
		consideredNodes.removeAll(from);
		
		// the difference between this class and the superclass is that here we do not remove the separators
		
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

	/* (non-Javadoc)
	 * @see unbbayes.util.dseparation.impl.MSeparationUtility#isDSeparated(java.util.Set, java.util.Set, java.util.Set, java.util.Set)
	 */
	@Override
	public boolean isDSeparated(Set<INode> consideredNodes, Set<INode> from,
			Set<INode> to, Set<INode> separators) {
		// TODO Auto-generated method stub
		return super.isDSeparated(consideredNodes, from, to, separators);
	}
	
	
	
}
