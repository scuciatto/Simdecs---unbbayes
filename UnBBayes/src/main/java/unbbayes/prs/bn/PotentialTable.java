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
import java.util.List;
import java.util.ResourceBundle;

import unbbayes.prs.INode;
import unbbayes.prs.Node;
import unbbayes.util.FloatCollection;
import unbbayes.util.SetToolkit;


/**
 * This is a conditional potential table
 * 
 * @author Michael, Rommel
 * @version September, 21th, 2001
 */
public abstract class PotentialTable implements Cloneable, java.io.Serializable, IProbabilityFunction {
	public static final int PRODUCT_OPERATOR = 0;
	public static final int DIVISION_OPERATOR = 1;
	public static final int PLUS_OPERATOR = 2;
	public static final int MINUS_OPERATOR = 3;

	private boolean modified;
	
	/** Load resource file from this package */
  	private static ResourceBundle resource = unbbayes.util.ResourceController.newInstance().getBundle(
  			unbbayes.prs.bn.resources.BnResources.class.getName());

	/**
	 * RVs that are in the table, including the variable that owns the table.
	 */
	protected List<Node> variableList;

	/**
	 * The data from the table as a list of floats. The use of this data 
	 * is done by using coordinates and linear coordinates.
	 */
	protected FloatCollection dataPT;
	
	/**
	 * Copy of the data from the data.
	 */
	protected FloatCollection dataCopy;
	
	/**
	 * The data from the table as a list of floats. Usually used to compute
	 * the marginal probabilities "manually", i.e. by removing each and every
	 * parent from the potential table. The use of this data is done by using  
	 * coordinates and linear coordinates for the marginal data.
	 */
	protected FloatCollection dataMarginal;

	/**
	 * Factors used to convert linear coordinates in multidimensional ones. 
	 * It stores the position that starts the table of each variable, for the
	 * potential table data.
	 */
	protected int[] factorsPT;
	
	/**
	 * Factors used to convert linear coordinates in multidimensional ones. 
	 * It stores the position that starts the table of each variable, for the
	 * marginal data.
	 */
	protected int[] factorsMarginal;

	/**
	 * Initialize data and variables.
	 */
	public PotentialTable() {
		modified = true;
		dataPT = new FloatCollection();
		dataCopy = new FloatCollection();
		dataMarginal = new FloatCollection();
		variableList = new ArrayList<Node>();
	}
	
	/**
	 * Creates a copy of the data from the table.
	 */
	public void copyData() {
		int dataSize = dataPT.size;
		for (int i = 0; i < dataSize; i++) {
			dataCopy.add(dataPT.data[i]);
		}
	}
	
	/**
	 * Restores the data from the table using its stored copy.
	 */
	public void restoreData() {
		int dataSize = dataPT.size;
		for (int i = 0; i < dataSize; i++) {
			dataPT.data[i] = dataCopy.data[i];
		}
	}

	/*
	 * (non-Javadoc)
	 * @see unbbayes.prs.bn.IProbabilityFunction#notifyModification()
	 */
	public void notifyModification() {
	   modified = true;
	}

	/**
	 * Returns a copy of the variables in this table.
	 * 
	 * @return A copy of the variables in this table.
	 */
	public List<Node> cloneVariables() {
		return SetToolkit.clone(variableList);
	}

	public  int indexOfVariable(Node node) {
		return variableList.indexOf(node);
	}
	
	//by young2010
	public  int indexOfVariable(String nodeName){
		for(int i = 0; i < variableList.size(); i++){
			if(variableList.get(i).getName().equals(nodeName) ){
				return i; 
			}
		}
		return -1;
	}

	/*
	 * (non-Javadoc)
	 * @see unbbayes.prs.bn.IProbabilityFunction#variableCount()
	 */
	public int variableCount() {
		return variableList.size();
	}

	public void setVariableAt(int index, INode node) {
		notifyModification();
		variableList.set(index, (Node)node);
	}

	/*
	 * (non-Javadoc)
	 * @see unbbayes.prs.bn.IProbabilityFunction#getVariableAt(int)
	 */
	public INode getVariableAt(int index) {
		return variableList.get(index);
	}
	
	public  int getVariableIndex(Node variable){
		for(int i = 0; i < variableList.size(); i++){
			if(variableList.get(i) == variable){
				return i; 
			}
		}
		return -1; 
	}

	public void addValueAt(int index, float value) {
		dataPT.add(index, value);
	}
	
	public  void removeValueAt(int index) {
		dataPT.remove(index);
	}

	public int tableSize() {
	   return dataPT.size;
	}
	
	// FIXME ROMMEL - THIS SHOULD NOT BE HERE!! FIX BUG REPORTED ABOUT ERROR WHEN REMOVING STATE FROM NODE
	public void setTableSize(int size) {
		dataPT.size = size;
	}

	/**
	 * Returns a copy of the data from the table.
	 * 
	 * @return A copy of the data from the table.
	 */
	public Object clone() {
		PotentialTable auxTab = newInstance();
		auxTab.variableList = SetToolkit.clone(variableList);
		int sizeDados = dataPT.size;
		for (int c = 0; c < sizeDados; c++) {
			auxTab.dataPT.add(dataPT.data[c]);
		}
		return auxTab;
	}
	
	/**
	 * Returns a copy of the data from the table associated with the new 
	 * node, instead of the original node. Useful when cloning a node.
	 * This method assumes that: 
	 *   1. The size of the new node table is the same as this node table being copied. 
	 *   2. If you want an exact copy, the parents in the new node have to be in the same
	 *      order they were in this node being copied, i.e., the variableList order is the same.
	 * 
	 * @return A copy of the data from the table associated with the new node.
	 */
	public Object clone(ProbabilisticNode newNode) {
		PotentialTable auxTab = newInstance();
		auxTab.addVariable(newNode);
		for (Node node : newNode.getParents()) {
			auxTab.addVariable(node);
		}
		int sizeDados = dataPT.size;
		for (int c = 0; c < sizeDados; c++) {
			auxTab.dataPT.add(dataPT.data[c]);
		}
		return auxTab;
	}

	/**
	 * Set a value in the table using the multidimensional coordinate, 
	 * which is a list containing the state of each variable in the table.
	 * 
	 * @param coord
	 *            The multidimensional coordinate, which is a list containing 
	 *            the state of each variable in the table.
	 * @param value
	 *            The value to be set in the table.
	 */
	public void setValue(int[] coord, float value) {
		dataPT.data[getLinearCoord(coord)] = value;
	}

	/**
	 * Set a value in the table using the linear coordinate, 
	 * which corresponds to the state of each variable in the table.
	 * 
	 * @param index
	 *            The linear coordinate, which corresponds to the state 
	 *            of each variable in the table.
	 * @param value
	 *            The value to be set in the table.
	 */
	public  void setValue(int index, float value) {
		dataPT.data[index] = value;
	}

	/**
	 * It returns the value of a cell identified by an index
	 * 
	 * @param index
	 *            linear index of a cell
	 * @return a value found in the specified cell .
	 */
	public  float getValue(int index) {
		return dataPT.data[index];
	}

	/**
	 * It returns a value in this table from a vector of coordinates.
	 * 
	 * @param coordinate
	 * 			the coordinates of the value to be returned.
	 * @return a value of a cell specified by coordinate.
	 */
	public  float getValue(int[] coordinate) {
		return dataPT.data[getLinearCoord(coordinate)];
	}

	/* (non-Javadoc)
	 * @see unbbayes.prs.bn.IProbabilityFunction#addVariable(unbbayes.prs.Node)
	 */
	public void addVariable(INode variavel) {
		/** @TODO reimplement it using correct format. */
		notifyModification();
		int noEstados = variavel.getStatesSize();
		int noCelBasica = this.dataPT.size;
		if (variableList.size() == 0) {
			for (int i = 0; i < noEstados; i++) {
				dataPT.add(0);
			}
		} else {
			while (noEstados > 1) {
				noEstados--;
				for (int i = 0; i < noCelBasica; i++) {
					dataPT.add(dataPT.data[i]);
				}
			}
		}
		variableList.add((Node)variavel);
	}
	
	/**
	 * Move a variable of position. Only the variables are moved... the data of
	 * the table aren't changed. 
	 * 
	 * ATENTION: Use this method only if you need to fill the table manually and want choice
	 * the order of the variables. Use before to fill the data. 
	 * 
	 * Pre-requisites: 
	 *     - The table has size > initialPosition and size > destinationPosition
	 * 
	 * @param initialPosition
	 * @param destinationPosition
	 */
	public void moveVariableWithoutMoveData(int initialPosition, int destinationPosition){
		
		Node nodeToMove = variableList.remove(initialPosition); 
		variableList.add(destinationPosition, nodeToMove); 
		
	}
	
	public int getVariablesSize(){
		return variableList.size(); 
	}

	
	
	/**
	 * Remove the variable of the table. 
	 * 
	 * Note: 
	 * Substitute the previous method removeVariable(Node variable)
	 *
	 * @param variable  Variable to be removed
	 * @param normalize True if is to normalize the cpt after the node remotion
	 */	
	public abstract void removeVariable(INode variable, boolean normalize); 
	
	/*
	 * (non-Javadoc)
	 * @see unbbayes.prs.bn.IProbabilityFunction#removeVariable(unbbayes.prs.INode)
	 */
	public abstract void removeVariable(INode variable);
	
	/**
	 * Returns a new instance of a PotentialTable of the current implemented
	 * sub-class.
	 * 
	 * @return a new instance of a PotentialTable of the current implemented
	 *         sub-class.
	 */
	public abstract PotentialTable newInstance();

	protected void sum(int index) {
		boolean marked[]  = new boolean[dataPT.size];		
		sumAux(variableList.size() - 1, index, 0, 0, marked);
		
		int j = 0;
		for (int i = 0; i < dataPT.size; i++) {
			if (marked[i]) {
				continue;				
			}
			dataPT.data[j++] = dataPT.data[i];
		}
		
		dataPT.size = j;
	}
	
	
	/**
	 * Auxiliary method for sum().
	 * Recursively sums all the values of the variable with the index specified. 
	 * 
	 * @param control
	 *            Control index for the recursion. Call with the value
	 *            'variaveis.size - 1'
	 * @param index
	 *            Index of the variable to delete from the table
	 * @param coord
	 *            The current iterated linear coordinate of the table. 
	 *            Call with 0
	 * @param base
	 * 			  Represents the number that needs to be subtracted from coord to reach the first coordinate of the variable.
	 *            Call with 0.
	 * @param marked
	 * 			  The removed cells will be marked as 'true'.
	 *            Call with an array of falses.
	 */
	private void sumAux(int control, int index, int coord, int base, boolean[] marked) {
		if (control == -1) {
			// concentrate the sum on the first cell. 
			int linearCoordDestination = coord - base;
			float value = dataPT.data[linearCoordDestination] + dataPT.data[coord];
			dataPT.data[linearCoordDestination] = value;
			marked[coord] = true;
			return;
		}
		
		Node node = variableList.get(control);
		if (control == index) {
			// if the current iterated variable is the one we want to delete, then iterate only until 1,
			// because the position 0 will hold the sum. 
			for (int i = node.getStatesSize()-1; i >= 1; i--) {
				sumAux(control-1, index, coord + i*factorsPT[control], i*factorsPT[index], marked);
			}	
		} else {
			for (int i = node.getStatesSize()-1; i >= 0; i--) {
				sumAux(control-1, index, coord + i*factorsPT[control], base, marked);
			}
		}
	}
	

	protected void finding(int control, int index, int coord[], int state) {
		if (control == -1) {
			int linearCoordToKill = getLinearCoord(coord);
			if (coord[index] == state) {
				int linearCoordDestination = linearCoordToKill - coord[index]*factorsPT[index];
				float value = dataPT.data[linearCoordToKill];
				dataPT.data[linearCoordDestination] = value;
			}
			dataPT.remove(linearCoordToKill);
			return;
		}

		int fim = (index == control) ? 1 : 0;
		Node node = variableList.get(control);
		for (int i = node.getStatesSize()-1; i >= fim; i--) {
			coord[control] = i;
			finding(control-1, index, coord, state);
		}
	}


	/**
	 * Get the linear coordinate from the multidimensional one.
	 * LinearCoord = SumOf(StateOf[i] * FactorOf[i]), for all 
	 * possible nodes (i), which are the nodes in the table.
	 * 
	 * @param multidimensionalCoord Multidimensional coordinate (represented by the state for
	 * each node).
	 * @return The corresponding linear coordinate.
	 */
	public  int getLinearCoord(int multidimensionalCoord[]) {
		computeFactors();
		int coordLinear = 0;
		int sizeVariaveis = variableList.size();
		for (int v = 0; v < sizeVariaveis; v++) {
			coordLinear += multidimensionalCoord[v] * factorsPT[v];
		}
		return coordLinear;
	}


	/**
	 * Calculate the factors necessary to transform the linear coordinate into a multidimensional 
	 * one, which is a list containing the state of each variable in the table.
	 */
	protected void computeFactors() {
		if (! modified) {
			return;
		}
		modified = false;
		int sizeVariaveis = variableList.size();
		if (factorsPT == null || factorsPT.length < sizeVariaveis) {
		   factorsPT = new int[sizeVariaveis];
		}
		factorsPT[0] = 1;
		Node auxNo;
		for (int i = 1; i < sizeVariaveis; i++) {
			auxNo = variableList.get(i-1);
			factorsPT[i] = factorsPT[i-1] * auxNo.getStatesSize();
		}
	}



	/**
	 * Get the multidimensional coordinate from the linear one.
	 * 
	 * @param linearCoord The linear coordinate.
	 * @return The corresponding multidimensional coordinate.
	 */
	public  int[] getMultidimensionalCoord(int linearCoord) {
		computeFactors();
		int fatorI;
		int sizeVariaveis = variableList.size();
		int coord[] = new int[sizeVariaveis];
		int i = sizeVariaveis - 1;
		while (linearCoord != 0) {
			fatorI = factorsPT[i];
			coord[i--] = linearCoord / fatorI;
			linearCoord %= fatorI;
		}
		return coord;
	}
	
	/**
	 * Get the linear coordinate from the multidimensional one.
	 * LinearCoord = SumOf(StateOf[i] * FactorOf[i]), for all 
	 * possible nodes (i), which are the nodes in the table.
	 * 
	 * @param multidimensionalCoord Multidimensional coordinate (represented by the state for
	 * each node).
	 * @return The corresponding linear coordinate.
	 */
	public  int getLinearCoordMarginal(int multidimensionalCoord[]) {
		computeFactorsMarginal();
		int coordLinear = 0;
		int sizeVariaveis = variableList.size();
		for (int v = 0; v < sizeVariaveis; v++) {
			coordLinear += multidimensionalCoord[v] * factorsPT[v];
		}
		return coordLinear;
	}


	/**
	 * Calculate the factors necessary to transform the linear coordinate into a multidimensional 
	 * one, which is a list containing the state of each variable in the table.
	 */
	protected void computeFactorsMarginal() {
		if (! modified) {
			return;
		}
		modified = false;
		int sizeVariaveis = variableList.size();
		if (factorsPT == null || factorsPT.length < sizeVariaveis) {
		   factorsPT = new int[sizeVariaveis];
		}
		factorsPT[0] = 1;
		Node auxNo;
		for (int i = 1; i < sizeVariaveis; i++) {
			auxNo = variableList.get(i-1);
			factorsPT[i] = factorsPT[i-1] * auxNo.getStatesSize();
		}
	}



	/**
	 * Get the multidimensional coordinate from the linear one.
	 * 
	 * @param linearCoord The linear coordinate.
	 * @return The corresponding multidimensional coordinate.
	 */
	public  int[] getMultidimensionalCoordMarginal(int linearCoord) {
		computeFactors();
		int fatorI;
		int sizeVariaveis = variableList.size();
		int coord[] = new int[sizeVariaveis];
		int i = sizeVariaveis - 1;
		while (linearCoord != 0) {
			fatorI = factorsPT[i];
			coord[i--] = linearCoord / fatorI;
			linearCoord %= fatorI;
		}
		return coord;
	}

	/**
	 * Operates with the argument table directly.
	 * 
	 * @param tab
	 *            table to operate.
	 * @param operator
	 *            operator to use, defined in this class constants.
	 */
	public  void directOpTab(PotentialTable tab, int operator) {
		if (tableSize() != tab.tableSize()) {
			throw new RuntimeException(resource.getString("TableSizeException") + ": " + tableSize() + " " + tab.tableSize());
		}
		
		switch (operator) {
			case PRODUCT_OPERATOR:
				for (int k = tableSize()-1; k >= 0; k--) {
					dataPT.data[k] *= tab.dataPT.data[k];
				}
				break;
			 
			case DIVISION_OPERATOR:
				for (int k = tableSize()-1; k >= 0; k--) {
					if (tab.dataPT.data[k] != 0) {
						dataPT.data[k] /= tab.dataPT.data[k];
					} else {
						dataPT.data[k] = 0;						
					}
				}
				break;
			
			case MINUS_OPERATOR:
				for (int k = tableSize()-1; k >= 0; k--) {
					dataPT.data[k] -= tab.dataPT.data[k];
				}
				break;
		}
	}


	/**
	 * Performs a operation between this table and another
	 * 
	 * @param tab
	 *            the table to be operated with this one.
	 * @param operator
	 *            an identifier of the operation to be used. 
	 *            		E.g.
	 *            {@link PotentialTable#PRODUCT_OPERATOR},
	 *            {@link PotentialTable#PLUS_OPERATOR},
	 *            {@link PotentialTable#DIVISION_OPERATOR}
	 *  @see PotentialTable#PRODUCT_OPERATOR
	 *  @see PotentialTable#PLUS_OPERATOR
	 *  @see PotentialTable#DIVISION_OPERATOR
	 *  
	 */
	public  void opTab(PotentialTable tab, int operator) {		
		int[] index = new int[variableList.size()];
		for (int c = variableList.size()-1; c >= 0; c--) {
			index[c] = tab.variableList.indexOf(variableList.get(c));
		}
		computeFactors();
		tab.computeFactors();
		
		switch (operator) {
			case PRODUCT_OPERATOR:
				fastOpTabProd(0, 0, 0, index, tab);
				break;
				
			case PLUS_OPERATOR:
				fastOpTabPlus(0, 0, 0, index, tab);
				break;
			
			case DIVISION_OPERATOR:
				fastOpTabDiv(0, 0, 0, index, tab);
				break;
				
			default:
		}
	}
	
	
	private void fastOpTabPlus(int c, int linearA, int linearB, int index[], PotentialTable tab) {
		if (c >= variableList.size()) {			
			dataPT.data[linearA] += tab.dataPT.data[linearB];
			return;						
		}
		if (index[c] == -1) {
			for (int i = variableList.get(c).getStatesSize() - 1; i >= 0; i--) {						
				fastOpTabPlus(c+1, linearA + i*factorsPT[c] , linearB, index, tab);
			}
		} else {
			for (int i = variableList.get(c).getStatesSize() - 1; i >= 0; i--) {						
				fastOpTabPlus(c+1, linearA + i*factorsPT[c] , linearB + i*tab.factorsPT[index[c]], index, tab);
			}
		}
	}

	private void fastOpTabProd(int c, int linearA, int linearB, int index[], PotentialTable tab) {
		if (c >= variableList.size()) {
			dataPT.data[linearA] *= tab.dataPT.data[linearB];
			return;						
		}
		if (index[c] == -1) {
			for (int i = variableList.get(c).getStatesSize() - 1; i >= 0; i--) {						
				fastOpTabProd(c+1, linearA + i*factorsPT[c] , linearB, index, tab);
			}
		} else {
			for (int i = variableList.get(c).getStatesSize() - 1; i >= 0; i--) {						
				fastOpTabProd(c+1, linearA + i*factorsPT[c] , linearB + i*tab.factorsPT[index[c]], index, tab);
			}
		}
	}
	
	private void fastOpTabDiv(int c, int linearA, int linearB, int index[], PotentialTable tab) {
		if (c >= variableList.size()) {
			dataPT.data[linearA] /= tab.dataPT.data[linearB];
			return;						
		}
		if (index[c] == -1) {
			for (int i = variableList.get(c).getStatesSize() - 1; i >= 0; i--) {						
				fastOpTabDiv(c+1, linearA + i*factorsPT[c] , linearB, index, tab);
			}
		} else {
			for (int i = variableList.get(c).getStatesSize() - 1; i >= 0; i--) {						
				fastOpTabDiv(c+1, linearA + i*factorsPT[c] , linearB + i*tab.factorsPT[index[c]], index, tab);
			}
		}
	}
	
	
}