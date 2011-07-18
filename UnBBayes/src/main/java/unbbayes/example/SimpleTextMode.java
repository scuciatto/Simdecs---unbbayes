package unbbayes.example;

import java.io.File;

import unbbayes.io.NetIO;
import unbbayes.prs.Edge;
import unbbayes.prs.Node;
import unbbayes.prs.bn.JunctionTreeAlgorithm;
import unbbayes.prs.bn.PotentialTable;
import unbbayes.prs.bn.ProbabilisticNetwork;
import unbbayes.prs.bn.ProbabilisticNode;

public class SimpleTextMode {
	public static void main(String[] args) throws Exception {
		   // open a .net file
		   ProbabilisticNetwork net 
		      = (ProbabilisticNetwork)new NetIO().load(new File("./examples/bn/net/asia.net"));
		   // adding a new node manually
		   ProbabilisticNode newNode = new ProbabilisticNode();
		   newNode.setName("K");
		   newNode.setDescription("A test node");
		   newNode.appendState("State 0");
		   newNode.appendState("State 1");
		   PotentialTable auxCPT = newNode.getProbabilityFunction();
		   auxCPT.addVariable(newNode);
		   net.addNode(newNode);
		   // search for "HasVisitedAsia"
		   ProbabilisticNode asiaNode = (ProbabilisticNode)net.getNode("A"); 
		   // adding a new edge from "HasVisitedAsia" to new node
		   net.addEdge(new Edge(asiaNode, newNode));
		   // filling CPT of new node
		   auxCPT.addValueAt(0, 0.99f); auxCPT.addValueAt(1, 0.01f); 
		   auxCPT.addValueAt(2, 0.1f) ; auxCPT.addValueAt(3, 0.9f);
		   // prepare the algorithm to compile network
		   JunctionTreeAlgorithm alg = new JunctionTreeAlgorithm();
		   alg.setNetwork(net);
		   alg.run();
		   // print node's initial states
		   for (Node node : net.getNodes()) {
		      System.out.println(node.getDescription());
		      for (int i = 0; i < node.getStatesSize(); i++) {
		         System.out.println(node.getStateAt(i) + " : " 
		            + ((ProbabilisticNode)node).getMarginalAt(i));
		      }
		   }
		   // insert evidence (finding) to the 1st node of "net"
		   ProbabilisticNode findingNode = (ProbabilisticNode)net.getNodes().get(0);
		   findingNode.addFinding(0); // the 1st state is now 100%
		   // insert likelihood
		   float likelihood[] = new float[newNode.getStatesSize()];
		   likelihood[0] = 1f;
		   likelihood[1] = 0.8f;
		   newNode.addLikeliHood(likelihood);
			// propagate evidence
		   net.updateEvidences();
		   // print updated node's states
		   System.out.println("****Updated****");
		   for (Node node : net.getNodes()) {
		      System.out.println(node.getDescription());
		      for (int i = 0; i < node.getStatesSize(); i++) {
		         System.out.println(node.getStateAt(i) + " : " 
		            + ((ProbabilisticNode)node).getMarginalAt(i));
		      }
		   }
		}
}
