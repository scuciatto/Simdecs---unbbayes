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
package unbbayes.util.dseparation;

import java.util.Set;

import unbbayes.prs.Graph;
import unbbayes.prs.INode;

/**
 * @author Shou Matsumoto
 * Utility class to check D-Separation criteria.
 * D-Separation indicates conditional independency between two sets of nodes
 * in a network, given a set of separator nodes.
 */
public interface IDSeparationUtility {
	
	/**
	 * Checks if the nodes within "from" are d-separated from nodes from "to", given a set
	 * of "separators".
	 * @param consideredNodes : represent a network containing the nodes within from, to and separators sets.
	 * @param from : set 1 of nodes which d-separation is going to be tested
	 * @param to : set 2 of nodes which d-separation is going to be tested
	 * @param separators : set of separators.
	 * @return : true if "from" is d-separated with "to" given "separators". False otherwise.
	 * @throws IllegalArgumentException : when there are some inconsistency between "graph" and the
	 * "from", "to" and "separators" sets.
	 */
	boolean isDSeparated(Set<INode> consideredNodes, Set<INode> from, Set<INode> to, Set<INode> separators );
	
	/**
	 * Obtains all nodes within graph which is d-separated from the set "from" given the separators.
	 * @param consideredNodes : nodes to be tested. They must reside in the same network containing the nodes within from, and separators sets.
	 * @param from set of nodes which d-separation is going to be tested
	 * @param separators : set of separators.
	 * @return set of all d-separated nodes within graph
	 */
	Set<INode> getAllDSeparatedNodes(Set<INode> consideredNodes, Set<INode> from, Set<INode> separators);
	
}
