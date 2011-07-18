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

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import unbbayes.prs.bn.ExplanationPhrase;
import unbbayes.prs.bn.IRandomVariable;
import unbbayes.prs.bn.ProbabilisticNode;
import unbbayes.prs.exception.InvalidParentException;
import unbbayes.util.ArrayMap;
import unbbayes.util.Debug;
import unbbayes.util.SerializablePoint2D;

import unbbayes.simdecs.*;

import java.util.Vector;

/**
 * A class representing a generic node containing graphical/visual information.
 * @author Michael 
 * @author Rommel Carvalho (rommel.caralho@gmail.com)
 * 
 * @version 04/18/2009
 * @author Shou Matsumoto
 * 		   Refactor: interface extraction -> INode
 */
public abstract class Node implements Serializable, 
                                      Comparable<Node>, INode{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6852804931629660275L;
	
	private String description;
	protected String name;
	protected String label;

	//by young	
	public static Point DEFAULT_SIZE = new Point(80,60); 
	
	protected SerializablePoint2D position;
	//by young	
	protected Color backColor;
	//by young
	protected SerializablePoint2D size = new SerializablePoint2D(DEFAULT_SIZE.getX(), DEFAULT_SIZE.getY());
	protected SerializablePoint2D sizeVariable = new SerializablePoint2D();
	protected boolean sizeIsVariable = false;

	protected ArrayList<Node> parents;
	private ArrayList<Node> children;
	protected List<String> states;
	private ArrayList<Node> adjacents;
	private boolean bSelected;
	private String explanationDescription;
	private ArrayMap<String, ExplanationPhrase> phrasesMap;
	private int informationType;
	public int infoestados[];

    //by young
 	public static final String DISPLAY_MODE_ELLIPSE		= "Display Mode Ellipse";
 	public static final String DISPLAY_MODE_BAR			= "Display Mode BAR";
 	private String m_displayMode = DISPLAY_MODE_ELLIPSE;
  

	public static final int PROBABILISTIC_NODE_TYPE = 0;
	public static final int UTILITY_NODE_TYPE = 1;
	public static final int DECISION_NODE_TYPE = 2;

	public static final int DESCRIPTION_TYPE = 3;
	public static final int EXPLANATION_TYPE = 4;
	
	public static final int CONTINUOUS_NODE_TYPE = 5;

	/**
	 * Holds the mean of the values for each class if this is a numeric
	 * attribute node
	 */
	protected double[] mean;

	/**
	 * Holds the standard deviation of the values for each class if this is a
	 * numeric attribute node.
	 */
	protected double[] standardDeviation;
	
	
	/**
	 * Atributos específicos para o projeto SimDeCS
	 */
	private double simdecsTempoEtapa;
	private double simdecsCustoEtapa;
	private boolean simdecsIsBogus = false;
	private Vector<PerguntaNodo> simdecsPerguntasEtapa;
	

	/**
	 * Builds a new node and makes the expected
	 * initializations.
	 */
	public Node() {
		name = "";
		label = ""; // text inside node
		description = "";
		explanationDescription = "";
		adjacents = new ArrayList<Node>();
		parents = new SetList<Node>();
		children = new SetList<Node>();
		states = new ArrayList<String>();

		// width
		size.x = DEFAULT_SIZE.getX();
		// height
		size.y = DEFAULT_SIZE.getY();

		position = new SerializablePoint2D();
		bSelected = false;
		phrasesMap = new ArrayMap<String, ExplanationPhrase>();
		informationType = DESCRIPTION_TYPE;
		
		//by young
		setColor(Color.white); 
		
		//Simdecs
		simdecsPerguntasEtapa = new Vector<PerguntaNodo>();
	}

	public static Point getDefaultSize(){
		return DEFAULT_SIZE;
	}
	
	

	/**
	 * Returns the type of information of this node.
	 * 
	 * @return Type of the information.
	 */
	public int getInformationType() {
		return informationType;
	}

	/**
	 * Modify the node's type of information.
	 * The types can be:
	 * 		DESCRIPTION_TYPE: for description nodes.
	 * 		EXPLANATION_TYPE: for explanation nodes.
	 * @param informationType
	 *            type of information
	 * @throws Exception
	 *            if the type of information is invalid
	 */
	public void setInformationType(int informationType) /* throws Exception */{
		if ((informationType > 2) && (informationType < 5)) {
			this.informationType = informationType;
			// TODO - Rommel think of a better way of doing this!
			if (getColor() == ProbabilisticNode.getDescriptionColor() && informationType == Node.EXPLANATION_TYPE) {
				this.setColor(ProbabilisticNode.getExplanationColor());
			}
		}
			
		/*
		 * else { throw new Exception("Valor de infroma��o inv�lido"); }
		 */
	}

	public void addExplanationPhrase(ExplanationPhrase explanationPhrase) {
		phrasesMap.put(explanationPhrase.getNode(), explanationPhrase);
	}

	public ExplanationPhrase getExplanationPhrase(String node) throws Exception {
		ExplanationPhrase ep = phrasesMap.get(node);
		if (ep == null) {
			throw new Exception("N� n�o encontrado.");
		} else {
			return (ExplanationPhrase) ep;
		}
	}

	/**
	 * Changes node's description.
	 * 
	 * @param texto
	 *            node's description.
	 */
	public void setDescription(String texto) {
		this.description = texto;
	}

	/**
	 * Set the node's name. It also causes the nodeNameChanged event to be fired.
	 * 
	 * @param name
	 *            Node's name.
	 */
	public void setName(String name) {
		NodeNameChangedEvent event = new NodeNameChangedEvent(this.name, name);
		this.name = name;
		this.nameChanged(event);
	}

	/**
	 * Set the node's label (text of the node).
	 * 
	 * @param label
	 *            Node's label.
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Return the node's label (text of the node).
	 * 
	 */
	public String getLabel() {
		return label;
	}
	
	//by young
	public String updateLabel()
	{
		return label;
	}

	/**
	 * Sets a new list of children.
	 * @param children
	 * 			List of nodes representing the children.
	 */
	public void setChildren(ArrayList<Node> children) {
		this.children = children;
	}
	
	

	/**
	 * Sets a new list of parents.
	 * @param parents
	 * 			List of nodes representing the parents.
	 */
	public void setParents(ArrayList<Node> parents) {
		this.parents = parents;
	}
	
	public void addChild(Node child) throws InvalidParentException{
		if (this.children.contains(child)) {
			Debug.println(this.getClass(), "Attempt to insert duplicate child: " + child);
			return;
		}
		this.children.add(child);
	}
	
	public void removeChild(Node child) {
		this.children.remove(child);
	}
	
	public void addParent(Node parent) throws InvalidParentException{
		if (this.parents.contains(parent)) {
			Debug.println(this.getClass(), "Attempt to insert duplicate parent: " + parent);
			return;
		}

		this.parents.add(parent);
	}
	
	public void removeParent(Node parent) {
		this.parents.remove(parent);
	}

	public boolean isParentOf(Node child) {
		// boolean result=children.contains(child);
		boolean result = false;
		int j = children.size();
		try {
			for (int i = 0; i < j; i++) {
				result = ((result) || ((child.getName()) == (children.get(i)
						.getName())));
			}
		} catch (Exception ee) {
		}
		return result;
	}

	public boolean isChildOf(Node parent) {
		// boolean result=parents.contains(parent);
		boolean result = false;
		int j = parents.size();
		for (int i = 0; i < j; i++) {
			result = ((result) || ((parent.getName()) == (parents.get(i)
					.getName())));
		}
		return result;
	}

	/**
	 * Modifica a descri��o da explana��o do n�.
	 * Modifies the description of the explanation of the node.
	 * @param texto
	 * 			A text representing the node's explanation's description.
	 *            descri��o da explana��o do n�.
	 */
	public void setExplanationDescription(String text) {
		this.explanationDescription = text;
	}

	/**
	 * Modifies the ArrayMap with the phrases
	 * @param phrasesMap
	 *            a new ArrayMap to be set
	 * @return the old phrasesMap.
	 */
	public ArrayMap<String, ExplanationPhrase> setPhrasesMap(
			ArrayMap<String, ExplanationPhrase> phrasesMap) {
		ArrayMap<String, ExplanationPhrase> old = this.phrasesMap;
		this.phrasesMap = phrasesMap;
		return old;
	}

	/**
	 * Obtains the name of this node.
	 * @return node's description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Obtains a list of adjacents.
	 * @return reference for this node's adjacents.
	 */
	public ArrayList<Node> getAdjacents() {
		return adjacents;
	}

	/**
	 * Returns the node's literal symbol.
	 * 
	 * @return node's literal abbreviation.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Obtains a list of children.
	 * @return list of children.
	 */
	public ArrayList<Node> getChildren() {
		return children;
	}

	/**
	 * Obtains a list of parents.
	 * @return list of parents.
	 */
	public ArrayList<Node> getParents() {
		return parents;
	}

	/**
	 * Obtains the description of the explanation of the node.
	 * @return description of the explanation of the node
	 */
	public String getExplanationDescription() {
		return explanationDescription;
	}

	/**
	 * Obtains the ArrayMap with the phrases.
	 * @return ArrayMap with the phrases.
	 */
	public ArrayMap<String, ExplanationPhrase> getPhrasesMap() {
		return this.phrasesMap;
	}

	/**
	 * Used within dalgo2
	 */
	public void atualizatamanhoinfoestados() {
		int i = states.size();
		infoestados = new int[i];

		/*
		 * nao precisa, pois o array eh criado sempre com o valor 0 for (int j =
		 * 0; j < i; j++) infoestados[j] = 0;
		 */
	}
	
	// by young2010
	/**
	 * Verifies if the node has a given state.
	 * @param state The state name to look for.
	 * @return true if the node has the given state, false otherwise.
	 */
	public boolean hasState(String state) {
		for (int i = 0; i < states.size(); i++) {
			if (states.get(i).equals(state))
				return true;
		}
		return false;
	}

	/**
	 * Inserts a state with the specified name at the end of the list.
	 * @param state
	 *            Name of the state to be added.
	 */
	public void appendState(String state) {
		updateTables();
		states.add(state);
	}

	/**
	 * Deletes the node's last inserted state (i.e the last element inside the list of states).
	 */
	public void removeLastState() {
		if (states.size() > 1) {
			updateTables();
			states.remove(states.size() - 1);
		}
	}

	/**
	 * Used within dalgo2. It should not be used with nodes having potential table's informations.
	 */
	public void removeStateAt(int index) {
		states.remove(index);
		this.atualizatamanhoinfoestados();
	}

	/**
	 * Replaces a state at given position to the specified position.
	 * @param state
	 * 				Name of the new state.
	 * @param index
	 * 				Position of the state being substituted. Starts with 0.
	 */
	public void setStateAt(String state, int index) {
		states.set(index, state);
	}

	/*
	 * public boolean existState(String state) { int size = states.size(); for
	 * (int i=0; i<size; i++) { if (states.get(i).equals(state)) return true; }
	 * return false; }
	 */

	/**
	 * It returns the node's quantity of states.
	 * @return How many states the node has.
	 */
	public int getStatesSize() {
		return states.size();
	}

	/**
	 * Returns the state of the position given by <code>index</code>
	 * @param index
	 *            position of the state to be read.
	 * @return Name of the state at <code>index</code>
	 */
	public String getStateAt(int index) {
		return (String) (states.get(index));
	}

	/**
	 * Builds the list of adjacent nodes.
	 * (the parents and children of this node)
	 */
	public void makeAdjacents() {
		adjacents.addAll(parents);
		adjacents.addAll(children);
	}

	/**
	 * Clears the list of adjacent nodes.
	 */
	public void clearAdjacents() {
		adjacents.clear();
	}

	/**
	 * This should be used to notify the tables which this variable is part of that
	 * there were some modification at this variable's internal structure.
	 */
	private void updateTables() {
		IRandomVariable aux;
		if (this instanceof IRandomVariable) {
			aux = (IRandomVariable) this;
			aux.getProbabilityFunction().notifyModification();
		}

		for (int i = children.size() - 1; i >= 0; i--) {
			if (children.get(i) instanceof IRandomVariable) {
				aux = (IRandomVariable) children.get(i);
				aux.getProbabilityFunction().notifyModification();
			}
		}
	}
	
	 //by young
    public void setDisplayMode(String s) 
	{  
    	m_displayMode = s;
	}	
	
	public String getDisplayMode() 
	{
		return m_displayMode;
	}

	/**
	 * Sets the adjacents.
	 * 
	 * @param adjacents
	 *            The adjacents to set
	 */
	public void setAdjacents(ArrayList<Node> adjacents) {
		this.adjacents = new ArrayList<Node>(adjacents);
	}

	/**
	 * Sets the states.
	 * 
	 * @param states
	 *            The states to set
	 */
	public void setStates(List<String> states) {
		this.states = states;
	}

	/**
	 * Prints a description of the node using "description (name)" format (without
	 * the quotes). It is used by the Interface's JTree when net is compiled.
	 * 
	 * @return formatted node description.
	 */
	public String toString() {
		return description + " (" + name + ")";
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		
		if (obj == this) {
			return true;
		}
		
		if((obj != null)&&(obj instanceof Node)){
		   Node node = (Node) obj;
		   return (node.name.equals(this.name));
		}
		
		return false; //obj == null && this != null 
		
	}

	public int compareTo(Node arg0) {
		return this.getName().compareTo(((Node)arg0).getName());	
	}
	
	public boolean isPointInDrawableArea(int x, int y) {
		double x1 = position.x;
		double y1 = position.y;
		double width = size.x / 2;
		double height = size.y / 2;

		if ((x >= x1 - width) && (x <= x1 + width) && (y >= y1 - height)
				&& (y <= y1 + height)) {
			return true;
		}

		return false;
	}

	public boolean isSelected() {
		return bSelected;
	}

	public void setSelected(boolean b) {
		bSelected = b;
	}

	public Point2D.Double getPosition() {
		return position;
	}

	public void setPosition(double x, double y) {
		position.setLocation(x, y);
	}

	//by young
	public Color getColor() {
		return backColor;
	}
 
	//by young
	public void setColor(Color c) {
		backColor = c;
	}
	
	/**
	 * Get the node's width.
	 * 
	 * @return Node's width.
	 */
	//by young
	public int getWidth() {
		return (int) size.x;
	}

	/**
	 * Get the node's height.
	 * 
	 * @return The node's height.
	 */
	//by young
	public int getHeight() {
		return (int) size.y;
	}
	
	/**
	 * Get the node's width.
	 * 
	 * @return Node's width.
	 */
	//by young
	public static int getDefaultWidth() {
		return (int) DEFAULT_SIZE.x;
	}

	/**
	 * Get the node's height.
	 * 
	 * @return The node's height.
	 */
	//by young
	public static int getDefaultHeight() {
		return (int) DEFAULT_SIZE.y;
	}

	/**
	 * Returns the node's size (x,y) where x = width and y = height.
	 * 
	 * @return The node's size.
	 */
	//by young
	public Point2D.Double getSize() {

		return size;

	}

	/**
	 * Set the node's size.
	 * 
	 * @param width
	 *            The node's width.
	 * @param height
	 *            The node's height.
	 */
	//by young
	public void setSize(double width, double height) {
		size.x = width;
		size.y = height;
	}

	public void setSizeVariable(double width, double height) {
		sizeVariable.setLocation(width, height);
	}

	public void setSizeIsVariable(boolean is) {
		sizeIsVariable = is;
	}

	/**
	 * Set the mean of the values if this is a numeric attribute node.
	 * 
	 * @param mean
	 */
	public void setMean(double[] mean) {
		this.mean = mean;
	}

	/**
	 * Set the mean of the values if this is a numeric attribute node.
	 * 
	 * @param mean
	 */
	public void setStandardDeviation(double[] standardDeviation) {
		this.standardDeviation = standardDeviation;
	}

	/**
	 * Get the mean of the values if this is a numeric attribute node.
	 * 
	 * @param mean
	 */
	public double[] getMean() {
		return mean;
	}

	/**
	 * Get the mean of the values if this is a numeric attribute node.
	 * 
	 * @param mean
	 */
	public double[] getStandardDeviation() {
		return standardDeviation;
	}
	
	/*********** NAME CHANGE LISTENER ***************/
	
	public class NodeNameChangedEvent {
		
		private String oldName;
		private String newName;
		
		public NodeNameChangedEvent(String oldName, String newName) {
			this.oldName = oldName;
			this.newName = newName;
		}

		public String getOldName() {
			return oldName;
		}

		public String getNewName() {
			return newName;
		}
	}
	
	public interface NodeNameChangedListener {
		public void nodeNameChanged(NodeNameChangedEvent event);
	}
	
	protected List<NodeNameChangedListener> nodeNameChangedListenerList = new ArrayList<NodeNameChangedListener>();
	
	public void addNodeNameChangedListener(NodeNameChangedListener listener) {
		nodeNameChangedListenerList.add(listener);
	}
	
	public void removeNodeNameChangedListener(NodeNameChangedListener listener) {
		nodeNameChangedListenerList.remove(listener);
	}
	
	protected void nameChanged(NodeNameChangedEvent event) {
		for (NodeNameChangedListener listener : nodeNameChangedListenerList) {
			listener.nodeNameChanged(event);
		}
	}

	/* (non-Javadoc)
	 * @see unbbayes.prs.INode#addChildNode(unbbayes.prs.INode)
	 */
	public void addChildNode(INode child) throws InvalidParentException {
		this.addChild((Node)child);
	}

	/* (non-Javadoc)
	 * @see unbbayes.prs.INode#addParentNode(unbbayes.prs.INode)
	 */
	public void addParentNode(INode parent) throws InvalidParentException {
		this.addParent((Node)parent);
	}

	/* (non-Javadoc)
	 * @see unbbayes.prs.INode#getAdjacentNodeList()
	 */
	public List<INode> getAdjacentNodes() {
		this.makeAdjacents();
		return (List)this.getAdjacents();
	}

	/* (non-Javadoc)
	 * @see unbbayes.prs.INode#getChildrenNodeList()
	 */
	public List<INode> getChildNodes() {
		return (List)this.getChildren();
	}

	/* (non-Javadoc)
	 * @see unbbayes.prs.INode#getParentNodeList()
	 */
	public List<INode> getParentNodes() {
		return (List)this.getParents();
	}

	/* (non-Javadoc)
	 * @see unbbayes.prs.INode#removeChildNode(unbbayes.prs.INode)
	 */
	public void removeChildNode(INode child) {
		this.removeChild((Node)child);
	}

	/* (non-Javadoc)
	 * @see unbbayes.prs.INode#removeParentNode(unbbayes.prs.INode)
	 */
	public void removeParentNode(INode parent) {
		this.removeParent((Node)parent);
	}

	/* (non-Javadoc)
	 * @see unbbayes.prs.INode#setChildrenNodeList(java.util.List)
	 */
	public void setChildNodes(List<INode> children) {
		this.setChildren (new SetList(children));
	}

	/* (non-Javadoc)
	 * @see unbbayes.prs.INode#setParentNodeList(java.util.List)
	 */
	public void setParentNodes(List<INode> parents) {
		this.setParents(new SetList(parents));
	}
	
	/**
	 * This is just an ArrayList which does not allow duplicate elements
	 * @author Shou Matsumoto
	 *
	 */
	public class SetList<E> extends ArrayList<E> {

		 /**
	     * Constructs an empty list with an initial capacity of ten.
	     */
		public SetList() {
			super();
		}

		 /**
	     * Constructs a list containing the elements of the specified
	     * collection, in the order they are returned by the collection's
	     * iterator.  The <tt>ArrayList</tt> instance has an initial capacity of
	     * 110% the size of the specified collection.
	     *
	     * @param c the collection whose elements are to be placed into this list.
	     * @throws NullPointerException if the specified collection is null.
	     */
		public SetList(Collection c) {
			super(c);
		}

		 /**
	     * Constructs an empty list with the specified initial capacity.
	     *
	     * @param   initialCapacity   the initial capacity of the list.
	     * @exception IllegalArgumentException if the specified initial capacity
	     *            is negative
	     */
		public SetList(int initialCapacity) {
			super(initialCapacity);
		}

		/* (non-Javadoc)
		 * @see java.util.ArrayList#add(java.lang.Object)
		 */
		@Override
		public boolean add(E o) {
			if (this.contains(o)) {
				return false;
			}
			return super.add(o);
		}

		/* (non-Javadoc)
		 * @see java.util.ArrayList#add(int, java.lang.Object)
		 */
		@Override
		public void add(int index, E element) {
			if (this.contains(element)) {
				return;
			}
			super.add(index, element);
		}

		/* (non-Javadoc)
		 * @see java.util.ArrayList#addAll(java.util.Collection)
		 */
		@Override
		public boolean addAll(Collection<? extends E> c) {
			// TODO optimize
			boolean ret = false;
			for (E e : c) {
				ret = this.add(e) || ret;
			}
			return ret;
		}

		/* (non-Javadoc)
		 * @see java.util.ArrayList#addAll(int, java.util.Collection)
		 */
		@Override
		public boolean addAll(int index, Collection<? extends E> c) {
			// TODO optimize
			int addedCount = 0;
			for (E e : c) {
				if (!this.contains(e)) {
					super.add(index + addedCount, e);
					addedCount++;
				}
			}
			return true;
		}
	}
	
	/**
	 * Métodos utilizados pelo SimDeCS para set e get de métodos específicos
	 */

	public double getSimdecsTempoEtapa() {
		return simdecsTempoEtapa;
	}

	public void setSimdecsTempoEtapa(double simdecsTempoEtapa) {
		this.simdecsTempoEtapa = simdecsTempoEtapa;
	}

	public double getSimdecsCustoEtapa() {
		return simdecsCustoEtapa;
	}

	public void setSimdecsCustoEtapa(double simdecsCustoEtapa) {
		this.simdecsCustoEtapa = simdecsCustoEtapa;
	}

	public Vector<PerguntaNodo> getSimdecsPerguntasEtapa() {
		return simdecsPerguntasEtapa;
	}

	public void adicionarPerguntaEtapa(PerguntaNodo pergunta) {
		this.simdecsPerguntasEtapa.add(pergunta);
	}
	
	public void setPerguntasEtapa(Vector<PerguntaNodo> vetor) {
		this.simdecsPerguntasEtapa = vetor;
	}

	public boolean getSimdecsIsBogus() {
		return simdecsIsBogus;
	}

	public void setSimdecsIsBogus(boolean isBogus) {
		this.simdecsIsBogus = isBogus;
	}
}