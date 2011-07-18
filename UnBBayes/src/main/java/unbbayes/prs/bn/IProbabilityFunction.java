package unbbayes.prs.bn;

import unbbayes.prs.INode;

/**
 * This class represents a probability function (e.g. probability table if a variable is a tabled
 * variable) associated with a random variable.
 * @author Shou Matsumoto
 *
 */
public interface IProbabilityFunction {

	/**
	 * Inserts a variable into function.
	 * This is usually called by the program when we insert
	 * an edge into a node.
	 * The first variable is usually the owner of the table
	 * @param variable : variable to be injected.
	 */
	public abstract void addVariable(INode variable);
	
	/**
	 * Obtains the number of associated variables.
	 * @return the quantity of variables inserted into
	 * this probability function
	 */
	public int variableCount();
	
	/**
	 * Obtains a variable (node) associated with this function
	 * @param index
	 * @return a node/variable identified by index
	 */
	public INode getVariableAt(int index);
	
	/**
	 * Remove the variable from this function.
	 * You should implement general marginalization as well
	 * 
	 * @param variable
	 *            variable to be removed.
	 */
	public abstract void removeVariable(INode variable);
	
	/**
	 * Remove the variable from this function.
	 * You should implement general marginalization as well
	 * 
	 * @param variable
	 *            variable to be removed.
	 * @param update
	 * 			  this parameter should be set to true/false if an active update to some values of 
	 * 			  probability function is required (or not).
	 */
	public abstract void removeVariable(INode variable, boolean update);
	
	/**
	 * Inserts a variable into position
	 * @param index : position to insert variable
	 * @param node : variable to be inserted
	 */
	public void setVariableAt(int index, INode node);

	
	/**
	 * This method has to be called when there is a change in any of the
	 * variables in this function.
	 */
	public void notifyModification();
}