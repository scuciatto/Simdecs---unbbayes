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

import java.util.List;

import unbbayes.prs.exception.InvalidParentException;

/**
 * Interface representing a generic node, containing only references and state
 * information (it does not contain visual/graphical data)
 * @version 04/18/2009
 * @author Shou Matsumoto
 * 		   Refactor: interface extraction Node -> INode
 * 
 */
public interface INode {

	/**
	 * Changes node's description.
	 * 
	 * @param text
	 *            node's description.
	 */
	public abstract void setDescription(String text);

	/**
	 * Set the node's name, which in most cases it is the node's ID.
	 * 
	 * @param name
	 *            Node's name.
	 */
	public abstract void setName(String name);

	/**
	 * Sets a new list of children.
	 * @param children
	 * 			List of nodes representing the children.
	 */
	public abstract void setChildNodes(List<INode> children);

	/**
	 * Sets a new list of parents.
	 * @param parents
	 * 			List of nodes representing the parents.
	 */
	public abstract void setParentNodes(List<INode> parents);

	/**
	 * Adds a child to this node
	 * @param child
	 * @throws InvalidParentException
	 */
	public abstract void addChildNode(INode child) throws InvalidParentException;

	/**
	 * Removes a child from this node
	 * @param child
	 */
	public abstract void removeChildNode(INode child);

	/**
	 * Add a parent to this node
	 * @param parent
	 * @throws InvalidParentException
	 */
	public abstract void addParentNode(INode parent) throws InvalidParentException;

	/**
	 * Removes a parent from this node
	 * @param parent
	 */
	public abstract void removeParentNode(INode parent);

	

	/**
	 * Obtains the name of this node.
	 * @return node's description.
	 */
	public abstract String getDescription();

	/**
	 * Obtains a list of adjacent nodes.
	 * @return reference for this node's adjacents.
	 */
	public abstract List<INode> getAdjacentNodes();

	/**
	 * Returns the node's literal symbol.
	 * 
	 * @return node's literal abbreviation.
	 */
	public abstract String getName();

	/**
	 * Obtains a list of children.
	 * @return list of children.
	 */
	public abstract List<INode> getChildNodes();

	/**
	 * Obtains a list of parents.
	 * @return list of parents.
	 */
	public abstract List<INode> getParentNodes();

	/**
	 * Inserts a state with the specified name at the end of the list.
	 * @param state
	 *            Name of the state to be added.
	 */
	public abstract void appendState(String state);

	/**
	 * Deletes the node's last inserted state (i.e the last element inside the list of states).
	 */
	public abstract void removeLastState();

	/**
	 * Used within dalgo2. It should not be used with nodes having potential table's informations.
	 */
	public abstract void removeStateAt(int index);

	/**
	 * Replaces a state at given position to the specified position.
	 * @param state
	 * 				Name of the new state.
	 * @param index
	 * 				Position of the state being substituted. Starts with 0.
	 */
	public abstract void setStateAt(String state, int index);

	/**
	 * It returns the node's quantity of states.
	 * @return How many states the node has.
	 */
	public abstract int getStatesSize();

	/**
	 * Returns the state of the position given by <code>index</code>
	 * @param index
	 *            position of the state to be read.
	 * @return Name of the state at <code>index</code>
	 */
	public abstract String getStateAt(int index);

	

	/**
	 * Sets the states.
	 * 
	 * @param states
	 *            The states to set
	 */
	public abstract void setStates(List<String> states);
	
	/**
	 * Method to extract node's type.
	 * It is free to use any value as any meaning.
	 * @return
	 */
	public abstract int getType();


}