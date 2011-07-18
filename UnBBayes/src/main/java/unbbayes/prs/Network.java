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
package unbbayes.prs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import unbbayes.prs.Node.NodeNameChangedEvent;
import unbbayes.prs.Node.NodeNameChangedListener;
import unbbayes.prs.bn.IProbabilityFunction;
import unbbayes.prs.bn.IRandomVariable;
import unbbayes.prs.bn.PotentialTable;
import unbbayes.prs.exception.InvalidParentException;
import unbbayes.util.Debug;

public class Network implements Graph{

	protected String id;
	protected String name;
	
	/**
	 *  List of nodes that this network has.
	 */
	protected ArrayList<Node> nodeList;
	/**
	 *  List of edges that this network has.
	 */
	protected List<Edge> edgeList;
	
	protected Map<String,Integer> nodeIndexes;
	
	protected NodeNameChangedListener nodeNameChangedListener;

	public Network(String name) {
		this.id = this.name = name;
		nodeList = new ArrayList<Node>();
        edgeList = new ArrayList<Edge>();
        nodeIndexes = new HashMap<String,Integer>();
        
        // Event responsible for updating the index for the node that just changed its name.
        nodeNameChangedListener = new NodeNameChangedListener() {
        	public void nodeNameChanged(NodeNameChangedEvent event) {
        		Integer index = nodeIndexes.get(event.getOldName());
        		
        		if(index!=null){
        			nodeIndexes.remove(event.getOldName());
        			nodeIndexes.put(event.getNewName(), index);
        		}
        		
        	}
        };
	}

	/**
	 *  Retorna os edgeList do grafo.
	 *
	 *@return    edgeList do grafo.
	 */
	public List<Edge> getEdges() {
	    return this.edgeList;
	}

	/**
	 *  Retorna os n�s do grafo.
	 *
	 *@return    n�s do grafo.
	 * 
	 * @todo Eliminar esse metodo! eh utilizado na classe NetWindow
	 */
	public ArrayList<Node> getNodes() {
	    return this.nodeList;
	}

	/**
	 *  Returna o n�mero de vari�veis da rede.
	 *
	 *@return    n�mero de vari�veis da rede.
	 */
	public int getNodeCount() {
		return nodeList.size();
	}

	/**
	 *  Retorna o n� do grafo com o respectivo �ndice.
	 *
	 *@param  index  �ndice do n�.
	 *@return	n� com respectivo �ndice no List.
	 */
	public Node getNodeAt(int index) {
	    return nodeList.get(index);
	}

	/**
	 *  Returns the node that has the given name.
	 *
	 *@param  name  node name.
	 *@return the node that has the given name.
	 */
	public Node getNode(String name) {
		int index = getNodeIndex(name);
		if (index == -1) return null;
		return nodeList.get(index);
	}

	/**
	 * Return the index for the node with the given name.
	 * @return the index for the node with the given name.
	 */
	public int getNodeIndex(String name) {
		Integer index = nodeIndexes.get(name);
		if (index == null) {
			return -1;    		
		}
		return index.intValue();
	}

	/**
	 *  Remove the edge.
	 *
	 *@param  edge  the edge to be removed.
	 */
	public void removeEdge(Edge edge) {
	    edge.getOriginNode().removeChild(edge.getDestinationNode());
	    edge.getDestinationNode().removeParent(edge.getOriginNode());
	    removeArc(edge);
	}

	/**
	 *  Add the node.
	 *
	 *@param  node  node to be added.
	 */
	public void addNode(Node node) {
	    nodeList.add(node);
	    // Set its index and add the listener to make sure it is always updated.
	    nodeIndexes.put(node.getName(), new Integer(nodeList.size()-1));
	    node.addNodeNameChangedListener(nodeNameChangedListener);
	}

	/**
	 *  Adds an edge into the net.
	 *  
	 *  - The table of the destination node will be updated with the new Variable
	 *
	 * @param  edge  An edge to be inserted.
	 * @throws InvalidParentException 
	 */
	public void addEdge(Edge edge) throws InvalidParentException {
		if (this.getEdges().contains(edge)) {
			// avoid duplicate edges
			Debug.println(this.getClass(), "Attempt to insert duplicate edge: " + edge.getOriginNode() + " -> " + edge.getDestinationNode());
			return;
		}
		edge.getOriginNode().addChild(edge.getDestinationNode());
		edge.getDestinationNode().addParent(edge.getOriginNode());
	    edgeList.add(edge);
	    if (edge.getDestinationNode() instanceof IRandomVariable) {
			IRandomVariable v2 = (IRandomVariable) edge.getDestinationNode();
			IProbabilityFunction auxTab = v2.getProbabilityFunction();
			auxTab.addVariable(edge.getOriginNode());
		}
	}

	/**
	 *  Remove node.
	 *
	 *@param  element  node to be removed.
	 */
	public void removeNode(Node element) {
	    int c;
	    Node node;
	    Edge edge;
	    
	    nodeList.remove(element);
	    
	    //nodeIndexes.remove(elemento.getName());
	    nodeIndexes.clear();
	    for (c = 0; c < nodeList.size(); c++) {
	        node = nodeList.get(c);
	        node.removeParent(element);
	        node.removeChild(element);
	        nodeIndexes.put(node.getName(), new Integer(c));
	    }
	    if (!edgeList.isEmpty()) {
	        edge = edgeList.get(0);
	        c = 0;
	        while (edge != edgeList.get(edgeList.size() - 1)) {
	            if ((edge.getOriginNode() == element) || (edge.getDestinationNode() == element)) {
	                removeArc(edge);
	            }
	            else {
	                c++;
	            }
	            edge = edgeList.get(c);
	        }
	        if ((edge.getOriginNode() == element) || (edge.getDestinationNode() == element)) {
	            removeArc(edge);
	        }
	    }
	    
	    
	}

	/**
	 * Remove edge of the network
	 *
	 *@param  edge  edge to be removed
	 */
	private void removeArc(Edge edge) {
	    Node auxNo;
	    IRandomVariable auxTabledVariable;
	    PotentialTable auxPotentialTable;
	
	    edgeList.remove(edge);
	
	    auxNo = edge.getDestinationNode();
	    if (auxNo instanceof IRandomVariable) {
	        auxTabledVariable = (IRandomVariable)auxNo;
	        auxPotentialTable = (PotentialTable)auxTabledVariable.getProbabilityFunction();
	        auxPotentialTable.removeVariable(edge.getOriginNode(), true);
	    }
	}

	/**
	 *  Clean the node list
	 */
	protected void clearNodes() {
	    nodeList.clear();
	}

	/**
	 *  Clean the edge list
	 */
	protected void clearEdges() {
	    edgeList.clear();
	}

	/**
	 *  Verifica exist�ncia de determinado arco.
	 *
	 *@param  no1  n� origem.
	 *@param  no2  n� destino.
	 *@return      posi��o do arco no vetor ou -1 caso n�o exista tal arco.
	 */
	public int hasEdge(Node no1, Node no2) {
		return hasEdge(no1, no2, edgeList);
	}

	protected int hasEdge(Node no1, Node no2, List<Edge> vetArcos) {
		if (no1 == no2) {
			return 1;
		}
	
		int sizeArcos = vetArcos.size();
		Edge auxA;
		for (int i = 0; i < sizeArcos; i++) {
			auxA = (Edge) vetArcos.get(i);
			if ((auxA.getOriginNode() == no1)
				&& (auxA.getDestinationNode() == no2)
				|| (auxA.getOriginNode() == no2)
				&& (auxA.getDestinationNode() == no1)) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Retorna o arco entre dois nós caso ele exista
	 * 
	 * @param no1 Nó origem
	 * @param no2 Nó destino
	 * @return o arco entre no1 e no 2 caso ele exista ou null cc. 
	 */
	public Edge getEdge(Node no1, Node no2){
		
		List<Edge> vetArcos = edgeList; 
		
		if (no1 == no2) {
			return null;
		}
	
		int sizeArcos = vetArcos.size();
		Edge auxA;
		for (int i = 0; i < sizeArcos; i++) {
			auxA = (Edge) vetArcos.get(i);
			if ((auxA.getOriginNode() == no1)
				&& (auxA.getDestinationNode() == no2)
				|| (auxA.getOriginNode() == no2)
				&& (auxA.getDestinationNode() == no1)) {
				return (Edge) vetArcos.get(i);
			}
		}
		return null;
	}

	/**
	 * Seta o nome da rede.
	 *
	 * @param name nome da rede.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Retorna o nome da rede.
	 *
	 * @return nome da rede.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the id.
	 * @return String
	 */
	public String getId() {
		return id;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return ((name != null) ? name : "") + '(' + id + ')';
	}

	/**
	 * @return the nodeIndexes
	 */
	public Map<String, Integer> getNodeIndexes() {
		return nodeIndexes;
	}

	/**
	 * @param nodeIndexes the nodeIndexes to set
	 */
	public void setNodeIndexes(Map<String, Integer> nodeIndexes) {
		this.nodeIndexes = nodeIndexes;
	}

}