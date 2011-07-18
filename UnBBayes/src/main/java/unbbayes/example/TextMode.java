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

import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import unbbayes.io.BaseIO;
import unbbayes.io.NetIO;
import unbbayes.prs.Edge;
import unbbayes.prs.Node;
import unbbayes.prs.bn.JunctionTreeAlgorithm;
import unbbayes.prs.bn.PotentialTable;
import unbbayes.prs.bn.ProbabilisticNetwork;
import unbbayes.prs.bn.ProbabilisticNode;
import unbbayes.util.extension.bn.inference.IInferenceAlgorithm;

/**
 * Title: Sample code for using UnBBayes' API at text mode.
 * Description: This class, written in JAVA, opens a ".net" file ("asia.net"), and, after that,
 * 				modifies some parts and then compiles it. This class is provided only as
 * 				a sample, in order to show how to work out using this API, developed for bayesian
 * 				network manipulation.
 * 
 * Copyright:   Copyright (c) 2001
 * Company:     UnB - Universidade de Brasilia
 * @author      Rommel Novaes Carvalho
 * @author      Michael S. Onishi
 */
public class TextMode {
	
	/** Load resource file from this package */
  	private static ResourceBundle resource = unbbayes.util.ResourceController.newInstance().getBundle(
  			unbbayes.example.resources.ExampleResources.class.getName());

	public static void main(String[] args) throws Exception {

		ProbabilisticNetwork net = null;

		try {
			BaseIO io = new NetIO(); // open a .net file
			net = (ProbabilisticNetwork)io.load(new File("./examples/bn/net/asia.net"));
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		// adding a new node manually
		ProbabilisticNode auxProbVariable = new ProbabilisticNode();
		auxProbVariable.setName(resource.getString("nodeName1"));
		auxProbVariable.setDescription(resource.getString("nodeDescription"));
		auxProbVariable.appendState(resource.getString("stateName0"));
		auxProbVariable.appendState(resource.getString("stateName1"));
		PotentialTable auxCPT = auxProbVariable.getProbabilityFunction();
		auxCPT.addVariable(auxProbVariable);
		auxCPT.addValueAt(0, 0.99f);
		auxCPT.addValueAt(1, 0.01f);
		net.addNode(auxProbVariable);

		// adding a new edge manually
		ProbabilisticNode auxProbVariable2 = (ProbabilisticNode) net.getNode(resource.getString("nodeName2"));
		Edge auxEdge = new Edge(auxProbVariable, auxProbVariable2);
		net.addEdge(auxEdge);

		// prepare the algorithm to compile network
		IInferenceAlgorithm algorithm = new JunctionTreeAlgorithm();
		algorithm.setNetwork(net);
		algorithm.run();
		
		// print node's states
		List<Node> nodeList = net.getNodes();
		for (Node node : nodeList) {
			System.out.println(node.getDescription());
			for (int i = 0; i < node.getStatesSize(); i++) {
				System.out.println(node.getStateAt(i) + " : " + ((ProbabilisticNode)node).getMarginalAt(i));
			}
		}
		
		// insert evidence (finding)
		int indexFirstNode = 0;
		ProbabilisticNode findingNode = (ProbabilisticNode)nodeList.get(indexFirstNode);
		int indexFirstState = 0;
		findingNode.addFinding(indexFirstState);
		
		System.out.println();
		
		// propagate evidence
		try {
        	net.updateEvidences();
        } catch (Exception exc) {
        	System.out.println(exc.getMessage());               	
        }
        
        //@print updated node's states
		for (Node node : nodeList) {
			System.out.println(node.getDescription());
			for (int i = 0; i < node.getStatesSize(); i++) {
				System.out.println(node.getStateAt(i) + " : " + ((ProbabilisticNode)node).getMarginalAt(i));
			}
		}
	}
}