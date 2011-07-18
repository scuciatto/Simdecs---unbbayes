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
package unbbayes.prs.bn;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import unbbayes.gui.HierarchicTree;
import unbbayes.io.NetworkCompilationLogManager;
import unbbayes.io.log.TextLogManager;
import unbbayes.prs.Edge;
import unbbayes.prs.Network;
import unbbayes.prs.Node;
import unbbayes.prs.id.DecisionNode;
import unbbayes.util.SetToolkit;

/**
 *  Class that represents a generic network.
 *
 *@author Rommel Carvalho
 *@author Michael Onishi
 *@version 2006/09/11
 */
public class SingleEntityNetwork extends Network implements java.io.Serializable {

	/** Serialization runtime version number */
	private static final long serialVersionUID = 0;	
	
	/** Load resource file from this package */
  	protected static ResourceBundle resource = unbbayes.util.ResourceController.newInstance().getBundle(
  			unbbayes.prs.bn.resources.BnResources.class.getName());
  	
  	protected HierarchicTree hierarchicTree;
  	
  	protected boolean firstInitialization;
  	
  	/**
	 * N�s de decis�o utilizado no processo de transforma��o.
	 */
	protected ArrayList<Node> decisionNodes;
	
	protected double radius;

    /**
	 * Faz o processamento do log de compila��o.
	 */
	protected NetworkCompilationLogManager logManager;

	/**
	 *  Lista de edgeList utilizada no processo de transforma��o.
	 */
	protected List<Edge> arcosMarkov;
	
	/**
	 * Indica se o log deve ser criado ou n�o.
	 */
	protected boolean createLog;
           
    /**
	 *  Ordem de elimina��o dos n�s.
	 */
	protected ArrayList<Node> oe;

	/**
	 * C�pia dos n�s sem os n�s de utilidade. Utilizado no processo
	 * de transforma��o.
	 */
	protected ArrayList<Node> copiaNos;
	
	protected List<Edge> copiaArcos;

	/**
	 *  Armazena handle do objeto �rvore de Jun��o associado ao Grafo.
	 */
	protected JunctionTree junctionTree;	
    
    /**
     *  Constr�i um novo grafo sem n�s nem edgeList.
     */
    public SingleEntityNetwork(String name) {
    	super(name);
        arcosMarkov = new ArrayList<Edge>();
        logManager = new NetworkCompilationLogManager(); 
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("root");
		DefaultTreeModel model = new DefaultTreeModel(root);
        hierarchicTree = new HierarchicTree(model);
    }


    public ArrayList<Node> getDescriptionNodes()
    {   ArrayList<Node> descriptionNodes = new ArrayList<Node>();
        int size = nodeList.size();
        for (int i=0;i<size;i++)
        {   Node node = getNodeAt(i);
            if ((node.getType() == Node.PROBABILISTIC_NODE_TYPE) && (node.getInformationType() == Node.DESCRIPTION_TYPE))
            {   descriptionNodes.add(node);
            }
        }
        return descriptionNodes;
    }

    public ArrayList<Node> getExplanationNodes()
    {   ArrayList<Node> explanationNodes = new ArrayList<Node>();
        int size = nodeList.size();
        for (int i=0;i<size;i++)
        {   Node node = getNodeAt(i);
            if ((node.getType() == Node.PROBABILISTIC_NODE_TYPE) && (node.getInformationType() == Node.EXPLANATION_TYPE))
            {   explanationNodes.add(node);
            }
        }
        return explanationNodes;
    }

    /**
     *  Build the adjacent list of each node in nodeList (then adjacent list 
     *  contains all nodes that is or father or child of the origin node)_.
     */
    protected void makeAdjacentsListForNodeListElements() {
        this.clearAdjacents();
        for (int qnos = 0; qnos < nodeList.size(); qnos++) {
            nodeList.get(qnos).makeAdjacents();
        }
    }
    
    
    protected void makeAdjacents() {
    	clearAdjacents();
    	for (int z = arcosMarkov.size() - 1; z >= 0; z--) {
			Edge auxArco = arcosMarkov.get(z);
			auxArco.getOriginNode().getAdjacents().add(
				auxArco.getDestinationNode());
			auxArco.getDestinationNode().getAdjacents().add(
				auxArco.getOriginNode());
		}
	
		for (int z = copiaArcos.size() - 1; z >= 0; z--) {
			Edge auxArco = copiaArcos.get(z);
			if (auxArco.getDestinationNode().getType()
				== Node.UTILITY_NODE_TYPE) {
				copiaArcos.remove(z);
			} else {
				auxArco.getOriginNode().getAdjacents().add(
					auxArco.getDestinationNode());
				auxArco.getDestinationNode().getAdjacents().add(
					auxArco.getOriginNode());
			}
		}
//		arcosMarkov = SetToolkit.union(arcosMarkov, edges);    	
    }


    /**
     *  Destr�i a lista de adjacentes de cada n� do grafo.
     */
    protected void clearAdjacents() {
    	int size = nodeList.size();
        for (int qnos = 0; qnos < size; qnos++) {
            nodeList.get(qnos).clearAdjacents();
        }
    }


    /**
     *  Verify if this network has cycle.
     *
     *@throws Exception If this network has a cycle.
     */
    public void verifyCycles() throws Exception {
    	int nodeSize = nodeList.size();
    	char[] visited = new char[nodeSize];
    	int[] pi = new int[nodeSize];
    	
    	for (int i = 0; i < nodeSize; i++) {
    		dfsCycle(i, visited, pi);
    	}
    }
    
    /**
     * Depth first search to verify cycle.
     */
    private void dfsCycle(int nodeIndex, char[] visited, int[] pi) throws Exception {
    	if (visited[nodeIndex] != 0) { 			
 			// Back edge. Has cycle!
    		if (visited[nodeIndex] == 1) {
                throw new Exception(resource.getString("CicleNetException") 
                					 + " " + createPath(nodeIndex, nodeIndex, pi, true));
    		}
    		return;    		
    	}
    	
    	visited[nodeIndex] = 1;    	
    	Node node = nodeList.get(nodeIndex);    	
    	for (int i = node.getChildren().size()-1; i >= 0; i--) {
    		int newIndex = getNodeIndex(node.getChildren().get(i).getName());
    		pi[newIndex] = nodeIndex; 
    		dfsCycle(newIndex, visited, pi);
    	}
    	visited[nodeIndex] = 2;
    }
    
    /**
     * Auxiliary method for dfsCycle() to construct the path of the cycle detected. 
     */
    private String createPath(int currentIndex, int nodeIndex, int[] pi, boolean first) {
    	if (currentIndex == nodeIndex && ! first) {
			return nodeList.get(currentIndex).getName();
    	}
    	return createPath(pi[currentIndex], nodeIndex, pi, false) + " " + nodeList.get(currentIndex).getName();
    }
    

    /**
     * It verifies if the network is connected
     *
     *  @throws Exception if network is disconnected.
     */
    public void verifyConectivity() throws Exception {
        List<Node> visitados = new ArrayList<Node>(nodeList.size());
        if (nodeList.size() <= 1) {
            return;
        }
        makeAdjacentsListForNodeListElements();
        dfsConnectivity(nodeList.get(0), visitados);
        clearAdjacents();
        if (visitados.size() != nodeList.size()) {
            throw new Exception(resource.getString("DisconectedNetException"));
        }
    }

    /**
     * Depth first search to verify connectivity.
     */
    private void dfsConnectivity(Node no, List<Node> visitados) {
        visitados.add(no);
        for (int i = 0; i < no.getAdjacents().size(); i++) {
            Node aux = no.getAdjacents().get(i);
            if (! visitados.contains(aux)) {
                dfsConnectivity(aux, visitados);
            }
        }
    }

	/**
	 *  Performs moralization of the network.
	 */
	protected void moralize() {
		Node auxNo;
		Node auxPai1;
		Node auxPai2;
		Edge auxArco;
		
		clearAdjacents();
	
		if (createLog) {
			logManager.append(resource.getString("moralizeLabel"));
		}
		arcosMarkov.clear();
		copiaArcos = (ArrayList<Edge>)SetToolkit.clone(edgeList);
	
		//retira os edgeList de informa��o
		int sizeArcos = copiaArcos.size() - 1;
		for (int i = sizeArcos; i >= 0; i--) {
			auxArco = copiaArcos.get(i);
			if (auxArco.getDestinationNode().getType()
				== Node.DECISION_NODE_TYPE) {
				copiaArcos.remove(i);
			}
		}
	
		int sizeNos = nodeList.size();
		for (int n = 0; n < sizeNos; n++) {
			auxNo = nodeList.get(n);
			if (!(auxNo.getType() == Node.DECISION_NODE_TYPE)
				&& auxNo.getParents().size() > 1) {
				int sizePais = auxNo.getParents().size();
				for (int j = 0; j < sizePais - 1; j++) {
					auxPai1 = auxNo.getParents().get(j);
					for (int k = j + 1; k < sizePais; k++) {
						auxPai2 = auxNo.getParents().get(k);
						if ((hasEdge(auxPai1, auxPai2, copiaArcos) == -1)
							&& (hasEdge(auxPai1, auxPai2, arcosMarkov) == -1)) {
							auxArco = new Edge(auxPai1, auxPai2);
							if (createLog) {
								logManager.append(
									auxPai1.getName()
										+ " - "
										+ auxPai2.getName()
										+ "\n");
							}
							arcosMarkov.add(auxArco);
						}
					}
				}
			}
		}
		
		makeAdjacents();
		
		if (createLog) {
			logManager.append("\n");
		}
	}

	/**
	 * Sets the createLog.
	 * @param createLog The createLog to set
	 */
	public void setCreateLog(boolean createLog) {
		this.createLog = createLog;
	}

	/**
	 *  Monta �rvore de jun��o a partir do grafo.
	 */
	protected void compileJT(JunctionTree jt) throws Exception {
		int menor;
		Clique auxClique;
		Separator auxSep;
	
		resetEvidences();
		
		junctionTree = jt;
		
		this.cliques();
		this.arvoreForte();
		this.sortCliqueNodes();
		this.associateCliques();
		junctionTree.initBeliefs();
	
		int sizeNos = copiaNos.size();
		for (int c = 0; c < sizeNos; c++) {
			Node auxNode = copiaNos.get(c);
			menor = Integer.MAX_VALUE;
			if (auxNode.getType() == Node.PROBABILISTIC_NODE_TYPE) {
				int sizeSeparadores = junctionTree.getSeparatorsSize();
				for (int c2 = 0; c2 < sizeSeparadores; c2++) {
					auxSep = (Separator) junctionTree.getSeparatorAt(c2);
					if (auxSep.getNodes().contains(auxNode)
						&& (auxSep.getProbabilityFunction().tableSize() < menor)) {
						((ProbabilisticNode) auxNode).setAssociatedClique(
							auxSep);
						menor = auxSep.getProbabilityFunction().tableSize();
					}
				}
			}
	
			if (menor == Integer.MAX_VALUE) {
				int sizeCliques = junctionTree.getCliques().size();
				for (int c2 = 0; c2 < sizeCliques; c2++) {
					auxClique = (Clique) junctionTree.getCliques().get(c2);
					if (auxClique.getNodes().contains(auxNode)
						&& (auxClique.getProbabilityFunction().tableSize() < menor)) {
						if (auxNode.getType()
							== Node.PROBABILISTIC_NODE_TYPE) {
							((ProbabilisticNode) auxNode).setAssociatedClique(
								auxClique);
						} else {
							((DecisionNode) auxNode).setAssociatedClique(
								auxClique);
							break;
						}
						menor = auxClique.getProbabilityFunction().tableSize();
					}
				}
			}
		}
	
		updateMarginais();
	
		if (createLog) {
			Thread t = new Thread(new Runnable() {
				public void run() {
					makeLog();
					System.out.println("**Log ended**");
				}
			});
			t.setPriority(Thread.MIN_PRIORITY);
			t.start();
		}
	}

	public void resetEvidences() {
		for (Node node : this.getNodesCopy()) {
			if (node instanceof TreeVariable) {
				((TreeVariable)node).resetEvidence();
				// OBS utility nodes are not tree variables
			}
		}
	}
	
	public void resetLikelihoods() {
		for (Node node : this.getNodesCopy()) {
			if (node instanceof TreeVariable) {
				((TreeVariable)node).resetLikelihood();
				// OBS utility nodes are not tree variables
			}
		}
	}

	/**
	 * Returns true if this network is a Influence Diagram
	 * @return Returns true if this network is a Influence Diagram, false otherwise.
	 */
	public boolean isID() {
		for (int i = 0; i < nodeList.size(); i++) {
			if (nodeList.get(i).getType() == Node.DECISION_NODE_TYPE
				|| nodeList.get(i).getType() == Node.UTILITY_NODE_TYPE) {
	
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns true if this network is hybrid, in other words, if it has at least 
	 * one continuous node.
	 * @return Returns true if this network is hybrid (continuous and discrete nodes).
	 */
	public boolean isHybridBN() {
		for (int i = 0; i < nodeList.size(); i++) {
			if (nodeList.get(i).getType() == Node.CONTINUOUS_NODE_TYPE) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns true if this network has only discrete probabilistic nodes, i.e. simple BN.
	 * @return Returns true if this network has only discrete probabilistic nodes, i.e. simple BN.
	 */
	public boolean isBN() {
		for (int i = 0; i < nodeList.size(); i++) {
			if (nodeList.get(i).getType() != Node.PROBABILISTIC_NODE_TYPE) {
				return false;
			}
		}
		return true;
	}

	protected void updateMarginais() {
		for (int i = 0; i < copiaNos.size(); i++) {
			TreeVariable node = (TreeVariable) copiaNos.get(i);
			/* Check if the node represents a numeric attribute */
			if (node.getStatesSize() == 0) {
				/* 
				 * The node represents a numeric attribute which has no
				 * potential table. Just skip it.
				 */
				continue;
			}
			node.marginal();
		}
	}

	/**
	 *  Faz o processo de identifica��o dos Cliques
	 */
	protected void cliques() {
		int i;
		int j;
		Node auxNo;
		Node auxNo2;
		int e;
		Clique auxClique;
		Clique auxClique2;
		List<Clique> listaCliques = new ArrayList<Clique>();
	
		int sizeNos = copiaNos.size();
		for (i = 0; i < sizeNos; i++) {
			auxNo = copiaNos.get(i);
			e = oe.indexOf(auxNo);
			auxClique = new Clique();
			auxClique.getNodes().add(auxNo);
	
			int sizeAdjacentes = auxNo.getAdjacents().size();
			for (j = 0; j < sizeAdjacentes; j++) {
				auxNo2 = auxNo.getAdjacents().get(j);
				if (oe.indexOf(auxNo2) > e) {
					auxClique.getNodes().add(auxNo2);
				}
			}
			listaCliques.add(auxClique);
		}
	
		boolean haTroca = true;
		while (haTroca) {
			haTroca = false;
			for (i = 0; i < listaCliques.size() - 1; i++) {
				auxClique = listaCliques.get(i);
				auxClique2 = listaCliques.get(i + 1);
				if (auxClique.getNodes().size() > auxClique2.getNodes().size()) {
					listaCliques.set(i + 1, auxClique);
					listaCliques.set(i, auxClique2);
					haTroca = true;
				}
			}
		}
	
		int sizeCliques = listaCliques.size();
	
		for1 : for (i = 0; i < sizeCliques; i++) {
			auxClique = listaCliques.get(i);
			for (j = i + 1; j < sizeCliques; j++) {
				auxClique2 = listaCliques.get(j);
	
				if (auxClique2.getNodes().containsAll(auxClique.getNodes())) {
					continue for1;
				}
			}
			junctionTree.getCliques().add(auxClique);
		}
		listaCliques.clear();
	}

	/**
	 * Ordena os n�s dos cliques e dos separadores de acordo com a ordem de elimina��o.
	 */
	protected void sortCliqueNodes() {
		List listaCliques = junctionTree.getCliques();
		boolean isID = isID();
		for (int k = 0; k < listaCliques.size(); k++) {
			Clique clique = (Clique) listaCliques.get(k);
			ArrayList<Node> nosClique = clique.getNodes();
			boolean haTroca = true;
			while (haTroca) {
				haTroca = false;
				for (int i = 0; i < nosClique.size() - 1; i++) {
					Node node1 = nosClique.get(i);
					Node node2 = nosClique.get(i + 1);
					if (isID) {
						if (oe.indexOf(node1) > oe.indexOf(node2)) {
							nosClique.set(i + 1, node1);
							nosClique.set(i, node2);
							haTroca = true;
						}
					} else { 
						if (node1.getName().compareToIgnoreCase(node2.getName()) > 0 ) {
							nosClique.set(i + 1, node1);
							nosClique.set(i, node2);
							haTroca = true;
						}	
					}
				}
			}
		}
	
		for (int k = junctionTree.getSeparatorsSize() - 1; k >= 0; k--) {
			Separator separator = (Separator) junctionTree.getSeparatorAt(k);
			ArrayList<Node> nosSeparator = separator.getNodes();
			boolean haTroca = true;
			while (haTroca) {
				haTroca = false;
				for (int i = 0; i < nosSeparator.size() - 1; i++) {
					Node node1 = nosSeparator.get(i);
					Node node2 = nosSeparator.get(i + 1);
					if (node1.getName().compareToIgnoreCase(node2.getName()) > 0 ) {
						nosSeparator.set(i + 1, node1);
						nosSeparator.set(i, node2);
						haTroca = true;
					}
					/*
					if (oe.indexOf(node1) > oe.indexOf(node2)) {
						nosSeparator.set(i + 1, node1);
						nosSeparator.set(i, node2);
						haTroca = true;
					}
					*/
				}
			}
		}
	}

	protected void makeLog() {
		long in = System.currentTimeMillis();
		try {
			logManager.finishLog(junctionTree, nodeList);
			if (id != null) {
				logManager.writeToDisk(id + ".txt", false);
			} else {
				logManager.writeToDisk(TextLogManager.DEFAULT_FILENAME, false);				
			}
		} catch (java.io.IOException ioe) {
			System.err.println(ioe.getMessage());
		}
		System.out.println(
			"GERACAO DO ARQUIVO LOG em "
				+ (System.currentTimeMillis() - in)
				+ " ms");
	}

	/**
	 *  Faz a associa��o dos N�s a um �nico clique com menos espa�o de est. que
	 *  contenha sua fam�lia
	 */
	protected void associateCliques() {
		int min;
		Node auxNo;
		IProbabilityFunction auxTabPot, auxUtilTab;
		Clique auxClique;
		Clique cliqueMin = null;
	
		for (int i = junctionTree.getCliques().size() - 1; i >= 0; i--) {
			auxClique = (Clique) junctionTree.getCliques().get(i);
			auxTabPot = auxClique.getProbabilityFunction();
			auxUtilTab = auxClique.getUtilityTable();
	
			int sizeNos = auxClique.getNodes().size();
			for (int c = 0; c < sizeNos; c++) {
				auxTabPot.addVariable(auxClique.getNodes().get(c));
				auxUtilTab.addVariable(auxClique.getNodes().get(c));
			}
		}
	
		for (int k = junctionTree.getSeparatorsSize() - 1; k >= 0; k--) {
			Separator auxSep = (Separator) junctionTree.getSeparatorAt(k);
			auxTabPot = auxSep.getProbabilityFunction();
			auxUtilTab = auxSep.getUtilityTable();
			int sizeNos = auxSep.getNodes().size();
			for (int c = 0; c < sizeNos; c++) {
				auxTabPot.addVariable(auxSep.getNodes().get(c));
				auxUtilTab.addVariable(auxSep.getNodes().get(c));
			}
		}
	
		int sizeNos = nodeList.size();
		for (int n = 0; n < sizeNos; n++) {
			if (nodeList.get(n).getType() == Node.DECISION_NODE_TYPE) {
				continue;
			}
	
			min = Integer.MAX_VALUE;
			auxNo = nodeList.get(n);
	
			int sizeCliques = junctionTree.getCliques().size();
			for (int c = 0; c < sizeCliques; c++) {
				auxClique = (Clique) junctionTree.getCliques().get(c);
	
				if (auxClique.getProbabilityFunction().tableSize() < min
					&& auxClique.getNodes().containsAll(auxNo.getParents())) {
					if (auxNo.getType() == Node.PROBABILISTIC_NODE_TYPE
						&& !auxClique.getNodes().contains(auxNo)) {
						continue;
					}
					cliqueMin = auxClique;
					min = cliqueMin.getProbabilityFunction().tableSize();
				}
			}
	
			if (auxNo.getType() == Node.PROBABILISTIC_NODE_TYPE) {
				cliqueMin.getAssociatedProbabilisticNodes().add(auxNo);
			} else {
				cliqueMin.getAssociatedUtilityNodes().add(auxNo);
			}
		}
	}

	/**
	 *  Faz o processo de constitui��o da �rvore de jun��o - Frank Jensen
	 */
	protected void arvoreForte() {
		int ndx;
		Clique auxClique;
		Clique auxClique2;
		ArrayList<Node> uni;
		ArrayList<Node> inter;
		ArrayList<Node> auxList;
		ArrayList<Node> listaNos;
		Separator sep;
		ArrayList<Node> alpha = new ArrayList<Node>();
	
		for (int i = oe.size() - 1; i >= 0; i--) {
			alpha.add(oe.get(i));
		}
	
		if (copiaNos.size() > 1) {
			int sizeCliques = junctionTree.getCliques().size();
			for (int i = 0; i < sizeCliques; i++) {
				auxClique = (Clique) junctionTree.getCliques().get(i);
				listaNos = SetToolkit.clone(auxClique.getNodes());
	
				//calcula o �ndice
				while ((ndx = getCliqueIndex(listaNos, alpha)) <= 0
					&& listaNos.size() > 1);
				if (ndx < 0) {
					ndx = 0;
				}
				auxClique.setIndex(ndx);
				listaNos.clear();
			}
			alpha.clear();
	
			Comparator<Clique> comparador = new Comparator<Clique>() {
				public int compare(Clique o1, Clique o2) {
					Clique c1 = o1;
					Clique c2 = o2;
					if (c1.getIndex() > c2.getIndex()) {
						return 1;
					}
					if (c1.getIndex() < c2.getIndex()) {
						return -1;
					}
					return 0;
				}
			};
	
			Collections.sort(junctionTree.getCliques(), comparador);
	
			auxClique = (Clique) junctionTree.getCliques().get(0);
			uni = SetToolkit.clone(auxClique.getNodes());
	
			int sizeCliques1 = junctionTree.getCliques().size();
			for (int i = 1; i < sizeCliques1; i++) {
				auxClique = (Clique) junctionTree.getCliques().get(i);
				inter = SetToolkit.intersection(auxClique.getNodes(), uni);
	
				for (int j = 0; j < i; j++) {
					auxClique2 = (Clique) junctionTree.getCliques().get(j);
	
					if (!auxClique2.getNodes().containsAll(inter)) {
						continue;
					}
	
					sep = new Separator(auxClique2, auxClique);
					sep.setNodes(inter);
					junctionTree.addSeparator(sep);
	
					auxList = SetToolkit.union(auxClique.getNodes(), uni);
					uni.clear();
					uni = auxList;
					break;
				}
			}
		}
	}

	/**
	 *  SUB-FUN��O do m�todo arvoreForte
	 */
	protected int getCliqueIndex(ArrayList<Node> listaNos, ArrayList<Node> alpha) {
		int ndx;
		int mx;
		Node auxNo;
		Node noMax = null;
		ArrayList<Node> auxList = null;
		ArrayList<Node> vizinhos;
	
		// pega o n� de �ndice m�ximo na ordem alpha (inverso da ordem de elimini��o)
		mx = -1;
		int sizeNos = listaNos.size();
		for (int i = 0; i < sizeNos; i++) {
			auxNo = listaNos.get(i);
			ndx = alpha.indexOf(auxNo);
			if (mx < ndx) {
				mx = ndx;
				noMax = auxNo;
			}
		}
	
		// Retira esse n� do clique
		listaNos.remove(noMax);
	
		// Monta uma lista de vizinhos do clique
		auxNo = listaNos.get(0);
		vizinhos = SetToolkit.clone(auxNo.getAdjacents());
		int sizeNos1 = listaNos.size();
		for (int i = 1; i < sizeNos1; i++) {
			auxNo = listaNos.get(i);
			auxList = SetToolkit.intersection(vizinhos, auxNo.getAdjacents());
			vizinhos.clear();
			vizinhos = auxList;
		}
		vizinhos.remove(noMax);
	
		ndx = 0;
		int sizeVizinhos = vizinhos.size();
		for (int i = 0; i < sizeVizinhos; i++) {
			auxNo = vizinhos.get(i);
			if (listaNos.contains(auxNo) || (alpha.indexOf(auxNo) > mx)) {
				continue;
			}
			ndx = mx;
			break;
		}
	
		return ndx;
	}

	/**
	 *  Sub-rotina do m�todo triangula.
	 *  Elimina os n�s do grafo utilizando a heur�stica do peso m�nimo.
	 *  Primeiramente elimina os n�s cujos adjacentes est�o ligados dois a dois.
	 *  Depois se ainda houver n�s no grafo, elimina-os aplicando a heur�stica
	 *  do peso m�nimo.
	 *
	 * @param  auxNos  Vetor de n�s.
	 */
	protected boolean minimumWeightElimination(ArrayList<Node> auxNos) {
		boolean algum;
		
		algum = true;
		while (algum) {
			algum = false;
	
			for (int i = auxNos.size() - 1; i >= 0; i--) {
				Node auxNo = auxNos.get(i);
	
				if (cordas(auxNo)) {
					//N�o tem cordas necess�rias:teste pr�ximo.
					continue;
				}
	
				for (int j = auxNo.getAdjacents().size() - 1; j >= 0; j--) {
					Node v = auxNo.getAdjacents().get(j);
					//boolean removed = v.getAdjacents().remove(auxNo);				
					//assert removed;
					v.getAdjacents().remove(auxNo);
				}
				auxNos.remove(auxNo);
				algum = true;
				oe.add(auxNo);
				if (createLog) {
					logManager.append(
						"\t" + oe.size() + " " + auxNo.getName() + "\n");
				}
			}
		}
	
		if (auxNos.size() > 0) {
			Node auxNo = weight(auxNos); //auxNo: clique de peso m�nimo.
			oe.add(auxNo);
			if (createLog) {
				logManager.append(
					"\t" + oe.size() + " " + auxNo.getName() + "\n");
			}
			elimine(auxNo, auxNos); //Elimine no e reduza grafo.
			return true;
		}
		
		return false;
	}

	/**
	 *  SUB-FUN��O do procedimento triangula que elimina n� e reduz o grafo. Inclui
	 *  cordas necess�rias para eliminar n�. Retira-o e aos adjacentes.
	 *
	 *@param  no      n� a ser eliminado
	 *@param  auxNos  lista de n�s
	 */
	private void elimine(Node no, ArrayList<Node> auxNos) {	
		for (int i = no.getAdjacents().size()-1; i > 0; i--) {
			Node auxNo1 = no.getAdjacents().get(i);
	
			for (int j = i - 1; j >= 0; j--) {
				Node auxNo2 = no.getAdjacents().get(j);
				if (! auxNo2.getAdjacents().contains(auxNo1)) {
					Edge auxArco = new Edge(auxNo1, auxNo2);
					if (createLog) {
						logManager.append(
							auxNo1.getName()
								+ resource.getString("linkedName")
								+ auxNo2.getName()
								+ "\n");
					}
					arcosMarkov.add(auxArco);
					auxNo1.getAdjacents().add(auxNo2);
					auxNo2.getAdjacents().add(auxNo1);			
					
					System.out.println(auxArco);
				}
			}
		}
	
		for (int i = no.getAdjacents().size() - 1; i >= 0; i--) {
			Node auxNo1 = no.getAdjacents().get(i);
			//boolean removed = auxNo1.getAdjacents().remove(no);
			//assert removed;
			auxNo1.getAdjacents().remove(no);
		}
		auxNos.remove(no);
	}

	/**
	 *  SUB-FUN��O do m�todo pesoMinimo que utiliza a her�stica do peso m�nimo.
	 *
	 * @param  auxNos  n�s.
	 * @return         n� cujo conjunto formado por adjacentes possui peso m�nimo.
	 */
	private Node weight(ArrayList<Node> auxNos) {
		Node v;
		Node auxNo;
		double p;
	
		Node noMin = null;
		double pmin = Double.MAX_VALUE;

		for (int i = auxNos.size()-1; i >= 0; i--) {
			auxNo = auxNos.get(i);
			p = Math.log(auxNo.getStatesSize());
	
			for (int j = auxNo.getAdjacents().size()-1; j >= 0; j--) {
				v = auxNo.getAdjacents().get(j);
				p += Math.log(v.getStatesSize());
			}
			if (p < pmin) {
				pmin = p;
				noMin = auxNo;
			}
		}
		
//		assert noMin != null;
		return noMin;
	}

	/**
	 *  SUB-FUN��O do m�todo triangula.
	 *
	 *@param  no  n�.
	 *@return     true - caso haja necessidade de inserir corda para poder eliminar
	 *      o n�. false - caso contr�rio.
	 */
	private boolean cordas(Node no) {
		if (no.getAdjacents().size() < 2) {
			return false;
		}
	
		for (int i = no.getAdjacents().size()-1; i > 0; i--) {
			Node auxNo1 = no.getAdjacents().get(i);
	
			for (int j = i - 1; j >=0; j--) {
				Node auxNo2 = no.getAdjacents().get(j);
				if (! auxNo2.getAdjacents().contains(auxNo1)) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	/**
	 *  Verifica integridade como grafo direcionado ac�clico / conexo e coes�o.
	 *  Com a sa�da � poss�vel saber quais erros especificamente ocorreram caso algum ocorra.
	 */
	protected void verifyConsistency() throws Exception {
		if (nodeList.size() != 0) {
			
			nodeIndexes.clear();
			for (int i = nodeList.size()-1; i>=0; i--) {
				nodeIndexes.put(nodeList.get(i).getName(), new Integer(i));				
			}
			
			boolean erro = false;

			StringBuffer sb = new StringBuffer();

			try {
				verifyUtility();
			} catch (Exception e) {
				erro = true;
				sb.append(e.getMessage());
			}
			try {
				verifyCycles();
			} catch (Exception e) {
				erro = true;
				sb.append('\n' + e.getMessage());
			}
			try {
				verifyConectivity();
			} catch (Exception e) {
				erro = true;
				sb.append('\n' + e.getMessage());
			}
			try {
				verifyPotentialTables();
			} catch (Exception e) {
				erro = true;
				sb.append('\n' + e.getMessage());
			}
			try {
				sortDecisions();
			} catch (Exception e) {
				erro = true;
				sb.append('\n' + e.getMessage());
			}

			if (erro) {
				throw new Exception(sb.toString());
			}
		}
	}

	public HierarchicTree getHierarchicTree() {
		return hierarchicTree;
	}

	public void setHierarchicTree(HierarchicTree hierarchicTree) {
		this.hierarchicTree = hierarchicTree;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public double getRadius() {
		return radius;
	}

	/**
	 *  SUB-FUN��O do m�todo verificaConsist�ncia que verifica a consistencia
	 *  das tabelas de potenciais dos n�s do grafo.
	 */
	protected void verifyPotentialTables() throws Exception {
		ProbabilisticTable auxTabPot;
		int c;
		Node auxNo;
		ProbabilisticNode auxVP;
	
		int sizeNos = nodeList.size();
		for (c = 0; c < sizeNos; c++) {
			auxNo = nodeList.get(c);
			if (auxNo.getType() == Node.PROBABILISTIC_NODE_TYPE) {
				auxVP = (ProbabilisticNode) auxNo;
				auxTabPot = (ProbabilisticTable) auxVP.getProbabilityFunction();
				auxTabPot.verifyConsistency();
			}
		}
	}

	/**
	 *  SUB-FUN��O do m�todo verificaConsist�ncia que verifica se todos os
	 *  n�s de utilidade n�o cont�m filhos.
	 */
	protected void verifyUtility() throws Exception {
		Node aux;
	
		int sizeNos = nodeList.size();
		for (int i = 0; i < sizeNos; i++) {
			aux = (Node) nodeList.get(i);
			if (aux.getType() == Node.UTILITY_NODE_TYPE
				&& aux.getChildren().size() != 0) {
				throw new Exception(
					resource.getString("variableName")
						+ aux
						+ resource.getString("hasChildName"));
			}
		}
	}

	/**
	 *  SUB-FUN��O do m�todo verificaConsist�ncia que verifica se
	 *  existe uma ordena��o total das decis�es. Isto �, se existe
	 *  um caminho orientado entre as decis�es.
	 */
	protected void sortDecisions() throws Exception {
		decisionNodes = new ArrayList<Node>();
		int sizeNos = nodeList.size();
		for (int i = 0; i < sizeNos; i++) {
			if (nodeList.get(i).getType() == Node.DECISION_NODE_TYPE) {
				decisionNodes.add(nodeList.get(i));
			}
		}
	
		ArrayList<Node> fila = new ArrayList<Node>();
		fila.ensureCapacity(nodeList.size()); 
		Node aux, aux2, aux3;
	
		int sizeDecisao = decisionNodes.size();
		for (int i = 0; i < sizeDecisao; i++) {
			boolean visitados[] = new boolean[nodeList.size()];
			aux = (Node) decisionNodes.get(i);
			fila.clear();
			fila.add(aux);
	
			while (fila.size() != 0) {
				aux2 = fila.remove(0);
				visitados[nodeList.indexOf(aux2)] = true;
	
				int sizeFilhos = aux2.getChildren().size();
				for (int k = 0; k < sizeFilhos; k++) {
					aux3 = (Node) aux2.getChildren().get(k);
					if (!visitados[nodeList.indexOf(aux3)]) {
						if (aux3.getType() == Node.DECISION_NODE_TYPE
							&& !aux.getAdjacents().contains(aux3)) {
							aux.getAdjacents().add(aux3);
						}
						fila.add(aux3);
					}
				}
			}
		}
	
		boolean haTroca = true;
		while (haTroca) {
			haTroca = false;
			for (int i = 0; i < decisionNodes.size() - 1; i++) {
				Node node1 = decisionNodes.get(i);
				Node node2 = decisionNodes.get(i + 1);
				if (node1.getAdjacents().size()
					< node2.getAdjacents().size()) {
					decisionNodes.set(i + 1, node1);
					decisionNodes.set(i, node2);
					haTroca = true;
				}
			}
		}
	
//		int sizeDecisao1 = decisionNodes.size();
//		for (int i = 0; i < sizeDecisao1; i++) {
//			System.out.print(decisionNodes.get(i) + " ");
//		}
//		System.out.println();
	
		for (int i = 0; i < decisionNodes.size(); i++) {
			aux = decisionNodes.get(i);
			//            System.out.print(aux.getAdjacents().size() + " ");
			if (aux.getAdjacents().size() != decisionNodes.size() - i - 1) {
				throw new Exception(
					resource.getString("DecisionOrderException"));
			}
		}
	
		clearAdjacents();
	}

	/**
	 * Return a copy of the nodes (without utility nodes).
	 *
	 * @return A copy of the nodes (without utility nodes).
	 */
	public ArrayList<Node> getNodesCopy() {
		if (copiaNos == null) {
			copiaNos = (ArrayList<Node>)nodeList.clone();
		}
		return copiaNos;
	}
	
	/**
	 * Reset the copy of the nodes. It is usually due to changes 
	 * in the network.
	 */
	public void resetNodesCopy() {
		copiaNos = (ArrayList<Node>)nodeList.clone();
	}

	public String getLog() {
		return logManager.getLog();
	}

	/**
	 * Gets the createLog.
	 * @return Returns a boolean
	 */
	public boolean isCreateLog() {
		return createLog;
	}

	/**
	 *  Chama o m�todo da �rvore de jun��o para atualizar evid�ncias.
	 *  @return             consist�ncia da �rvore atualizada.
	 */
	public void updateEvidences() throws Exception {
		int sizeNos = copiaNos.size();
		for (int c = 0; c < sizeNos; c++) {
			TreeVariable node = (TreeVariable) copiaNos.get(c);
			node.updateEvidences();
		}

		try {
			junctionTree.consistency();
		} catch (Exception e) {
			initialize();
			throw e;
		}
		updateMarginais();
		resetLikelihoods();
	}
	
	/**
	 * Inicia as cren�as da �rvore de jun��o.
	 */
	public void initialize() throws Exception {
		resetEvidences();
		junctionTree.initBeliefs();
		if (firstInitialization) {
			updateMarginais();
			copyMarginal();
			firstInitialization = false;
		} else {
			restoreMarginais();
		}
	}

	protected void copyMarginal() {
		for (int i = 0; i < copiaNos.size(); i++) {
			TreeVariable node = (TreeVariable) copiaNos.get(i);
			node.copyMarginal();
		}
	}

	protected void restoreMarginais() {
		for (int i = 0; i < copiaNos.size(); i++) {
			TreeVariable node = (TreeVariable) copiaNos.get(i);
			node.restoreMarginal();
		}
	}

	/**
	 * Sets the firstInitialization.
	 * @param firstInitialization The firstInitialization to set
	 */
	public void setFirstInitialization(boolean firstInitialization) {
		this.firstInitialization = firstInitialization;
	}

	/**
	 *  Retorna a probabilidade estimada total da �rvore de jun��o associada.
	 *
	 *@return    probabilidade estimada total da �rvore de jun��o associada.
	 */
	public float PET() {
		return junctionTree.getN();
	}
}

