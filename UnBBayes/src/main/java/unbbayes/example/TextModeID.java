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
package unbbayes.example;

import unbbayes.prs.Edge;
import unbbayes.prs.Node;
import unbbayes.prs.bn.JunctionTreeAlgorithm;
import unbbayes.prs.bn.PotentialTable;
import unbbayes.prs.bn.ProbabilisticNetwork;
import unbbayes.prs.bn.ProbabilisticNode;
import unbbayes.prs.bn.TreeVariable;
import unbbayes.prs.id.DecisionNode;
import unbbayes.prs.id.UtilityNode;

/**
 * Title: Sample code for using UnBBayes' API at text mode.
 * Description: This class, written in JAVA, creates from scratch an extremely simple ID with 3 nodes (decision,
 * probabilistic and utility) and then compiles it. This class is provided only as
 * a sample, in order to show how to work out using this API, developed for ID manipulation.
 * 
 * Copyright:   Copyright (c) 2010
 * Company:     UnB - Universidade de Brasilia
 * @author      Shou Matsumoto
 */
public class TextModeID {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception  {
		// create a network from scratch
		ProbabilisticNetwork net = new ProbabilisticNetwork("IDSample");
		// adding a new decision node 
		DecisionNode decision = new DecisionNode();
		decision.setName("MyDecision");
		decision.setDescription("A decision node");
		decision.appendState("Action 0");
		decision.appendState("Action 1");;
		net.addNode(decision);
		// adding a new probabilistic node
		ProbabilisticNode probNode = new ProbabilisticNode();
		probNode.setName("MyProbNode");
		probNode.setDescription("A probabilstic node");
		probNode.appendState("State 0");
		probNode.appendState("State 1");
		PotentialTable auxCPT = probNode.getProbabilityFunction();
		auxCPT.addVariable(probNode);
		net.addNode(probNode);
		// adding an edge from decision node to probabilistic node
		net.addEdge(new Edge(decision,probNode));
		// adapting CPT
		auxCPT.addValueAt(0, 0.9f);
		auxCPT.addValueAt(1, 0.1f);
		auxCPT.addValueAt(2, 0.2f);
		auxCPT.addValueAt(3, 0.8f);
		// adding a new utility node
		UtilityNode utility = new UtilityNode();
		utility.setName("MyUtilityNode");
		utility.setDescription("An utility node");
		utility.getProbabilityFunction().addVariable(utility);
		net.addNode(utility);
		// adding an edge from probabilistic node to utility node
		net.addEdge(new Edge(probNode, utility));
		// editing utility function
		utility.getProbabilityFunction().setValue(0, 50);
		utility.getProbabilityFunction().setValue(1, -100);
		// prepare the algorithm to compile network
		JunctionTreeAlgorithm alg = new JunctionTreeAlgorithm();
		alg.setNetwork(net);
		alg.run();
		// print node's initial states
		for (Node node : net.getNodes()) {
			System.out.println(node.getDescription());
			for (int i = 0; i < node.getStatesSize(); i++) {
				if (node instanceof TreeVariable) {
					System.out.println(node.getStateAt(i) + " : " 
							+ ((TreeVariable)node).getMarginalAt(i));
				}
			}
		}
		// insert decision (take the 1st action)
		((TreeVariable)net.getNode("MyDecision")).addFinding(0);
		// propagate evidence
		net.updateEvidences();
        // print updated node's states
		System.out.println("***Propagation***");
		for (Node node : net.getNodes()) {
			System.out.println(node.getDescription());
			for (int i = 0; i < node.getStatesSize(); i++) {
				if (node instanceof TreeVariable) {
					System.out.println(node.getStateAt(i) + " : " 
							+ ((TreeVariable)node).getMarginalAt(i));
				}
			}
		}
	}

}
