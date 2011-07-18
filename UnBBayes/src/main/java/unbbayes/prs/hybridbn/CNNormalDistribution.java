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
package unbbayes.prs.hybridbn;

import java.util.ArrayList;
import java.util.List;

import unbbayes.prs.Node;
import unbbayes.util.SortUtil;

/**
 * This class represents the continuous node normal distribution. 
 * @author Rommel Carvalho (rommel.carvalho@gmail.com)
 * @deprecated Continuous node is no longer supported in UnBBayes core. It has 
 * now been replaced by the CPS plugin available at http://sourceforge.net/projects/prognos/.
 */
public class CNNormalDistribution {
	
	protected ContinuousNode cNode;
	protected NormalDistributionFunction[] ndfList;
	
	protected List<Node> discreteParentList;
	
	public List<Node> getDiscreteParentList() {
		return discreteParentList;
	}

	protected List<Node> continuousParentList;
	
	public List<Node> getContinuousParentList() {
		return continuousParentList;
	}

	protected int[] factors;
	
	public CNNormalDistribution(ContinuousNode cNode) {
		this.cNode = cNode;
		refreshParents();
	}
	
	/**
	 * Must be called when there is some change in the parents.
	 * The order of discrete parents list and continuous parents list is
	 * always ascendent by its name. This is important to know to which parent 
	 * the respective index refers. 
	 */
	public void refreshParents() {
		discreteParentList = new ArrayList<Node>();
		continuousParentList = new ArrayList<Node>();
		for (Node node : cNode.getParents()) {
			if (node.getType() == Node.CONTINUOUS_NODE_TYPE) {
				continuousParentList.add(node);
			} else {
				discreteParentList.add(node);
			}
		} 
		SortUtil.sortNodeListByName(discreteParentList);
		SortUtil.sortNodeListByName(continuousParentList);
		
		calculateFactors();
		if (discreteParentList.size() > 0) {
			int lastNode = factors.length - 1;
			int ndfListSize = factors[lastNode] * discreteParentList.get(lastNode).getStatesSize();
			ndfList = new NormalDistributionFunction[ndfListSize];
			for (int i = 0; i < ndfList.length; i++) {
				ndfList[i] = new NormalDistributionFunction();
			}
		} else {
			// There is just one normal distribution
			ndfList = new NormalDistributionFunction[1];
			ndfList[0] = new NormalDistributionFunction();
		}
	}
	
	/**
	 * Set the constant that multiplies the continuous parent node at index to the 
	 * given value for the given combination of discrete parent node's states, 
	 * which is the multidimensional coordinate.
	 * @param constantIndex The continuous parent node index.
	 * @param value The new value for the constant.
	 * @param multidimensionalCoord The multidimensional coordinate which is the 
	 * state associated with each possible discrete parent node.
	 */
	public void setConstantAt(int constantIndex, double value, int[] multidimensionalCoord) {
		ndfList[getLinearCoord(multidimensionalCoord)].setConstantAt(constantIndex, value);
	}
	
	/**
	 * Set the constant that multiplies the continuous parent node at index to the 
	 * given value for the given combination of discrete parent node's states, 
	 * which is the multidimensional coordinate.
	 * @param constantIndex The continuous parent node index.
	 * @param value The new value for the constant.
	 * @param index The index which is the state associated with each possible 
	 * discrete parent node.
	 */
	public void setConstantAt(int constantIndex, double value, int index) {
		ndfList[index].setConstantAt(constantIndex, value);
	}
	
	public double getConstantAt(int constantIndex, int[] multidimensionalCoord) {
		return ndfList[getLinearCoord(multidimensionalCoord)].getConstantAt(constantIndex);
	}
	
	public double getConstantAt(int constantIndex, int index) {
		return ndfList[index].getConstantAt(constantIndex);
	}
	
	public int getConstantListSize() {
		return continuousParentList.size();
	}
	
	/**
	 * Set the normal distribution mean for the given combination of discrete 
	 * parent node's states, which is the multidimensional coordinate.
	 * @param mean The normal distribution mean.
	 * @param multidimensionalCoord The multidimensional coordinate which is the 
	 * state associated with each possible discrete parent node.
	 */
	public void setMean(double mean, int[] multidimensionalCoord) {
		ndfList[getLinearCoord(multidimensionalCoord)].setMean(mean);
	}
	
	/**
	 * Set the normal distribution mean for the given combination of discrete 
	 * parent node's states, which is represented by the given index.
	 * @param mean The normal distribution mean.
	 * @param index The index which is the state associated with each possible 
	 * discrete parent node.
	 */
	public void setMean(double mean, int index) {
		ndfList[index].setMean(mean);
	}
	
	public double getMean(int[] multidimensionalCoord) {
		return ndfList[getLinearCoord(multidimensionalCoord)].getMean();
	}
	
	public double getMean(int index) {
		return ndfList[index].getMean();
	}

	/**
	 * Set the normal distribution variance for the given combination of discrete 
	 * parent node's states, which is the multidimensional coordinate.
	 * @param variance The normal distribution variance.
	 * @param multidimensionalCoord The multidimensional coordinate which is the 
	 * state associated with each possible discrete parent node.
	 */
	public void setVariance(double variance, int[] multidimensionalCoord) {
		ndfList[getLinearCoord(multidimensionalCoord)].setVariance(variance);
	}
	
	/**
	 * Set the normal distribution variance for the given combination of discrete 
	 * parent node's states, which is represented by the given index.
	 * @param variance The normal distribution variance.
	 * @param index The index which is the state associated with each possible 
	 * discrete parent node.
	 */
	public void setVariance(double variance, int index) {
		ndfList[index].setVariance(variance);
	}
	
	public double getVariance(int[] multidimensionalCoord) {
		return ndfList[getLinearCoord(multidimensionalCoord)].getVariance();
	}
	
	public double getVariance(int index) {
		return ndfList[index].getVariance();
	}
	
	/**
	 * Calculate the factors necessary to transform the linear coordinate into a multidimensional 
	 * one (which is the the state for each possible discrete parent node).
	 * FactorForNode[i + 1] = ProductOf(NumberOfStates[i]), for all previous discrete 
	 * parent nodes (i).
	 */
	protected void calculateFactors() {
		int size = discreteParentList.size();
		if (factors == null || factors.length != size) {
		   factors = new int[size];
		}
		// If there is no discrete parent, then there is only one normal distribution
		if (size == 0) {
			factors = new int[1];
		}
	
		factors[0] = 1;
		Node node;
		for (int i = 1; i < size; i++) {
			node = discreteParentList.get(i-1);
			factors[i] = factors[i-1] * node.getStatesSize();
		}
	}
	
	/**
	 * Get the linear coordinate from the multidimensional one.
	 * LinearCoord = SumOf(StateOf[i] * FactorOf[i]), for all 
	 * possible discrete parent nodes (i).
	 * 
	 * @param multidimensionalCoord Multidimensional coordinate (represented by the state for
	 * each discrete parent node).
	 * @return The corresponding linear coordinate.
	 */
	public  int getLinearCoord(int multidimensionalCoord[]) {
		calculateFactors();
		int coordLinear = 0;
		int size = discreteParentList.size();
		for (int v = 0; v < size; v++) {
			coordLinear += multidimensionalCoord[v] * factors[v];
		}
		return coordLinear;
	}

	/**
	 * Get the multidimensional coordinate from the linear one.
	 * 
	 * @param linearCoord The linear coordinate.
	 * @return The corresponding multidimensional coordinate.
	 */
	public  int[] getMultidimensionalCoord(int linearCoord) {
		calculateFactors();
		int factorI;
		int size = discreteParentList.size();
		int multidimensionalCoord[] = new int[size];
		int i = size - 1;
		while (linearCoord != 0) {
			factorI = factors[i];
			multidimensionalCoord[i--] = linearCoord / factorI;
			linearCoord %= factorI;
		}
		return multidimensionalCoord;
	}
	
	/**
	 * Returns the number of normal distribution functions.
	 * @return The number of normal distribution functions.
	 */
	public int functionSize() {
		return ndfList.length;
	}
	
	/**
	 * Follows SumOf(k[i] * CPND[i]) + N(mean, variance), for all continuous node (i). 
	 * CPND is the continuous parent node distribution. 
	 * @author Rommel Carvalho (rommel.carvalho@gmail.com)
	 *
	 */
	protected class NormalDistributionFunction {
		// Constant used to multiply the continuous parent distribution.
		protected double[] constantList;
		// The normal distribution associated with this function.
		protected NormalDistribution normalDistribution;
		
		public NormalDistributionFunction() {
			constantList = new double[continuousParentList.size()];
			for (int i = 0; i < constantList.length; i++) {
				constantList[i] = 0;
			}
			normalDistribution = new NormalDistribution();
		}
		
		/**
		 * Set the normal distribution mean.
		 * @param mean The normal distribution mean.
		 */
		public void setMean(double mean) {
			normalDistribution.setMean(mean);
		}
		
		public double getMean() {
			return normalDistribution.getMean();
		}
		
		/**
		 * Set the normal distribution variance.
		 * @param variance The normal distribution variance.
		 */
		public void setVariance(double variance) {
			normalDistribution.setVariance(variance);
		}
		
		public double getVariance() {
			return normalDistribution.getVariance();
		}
		
		/**
		 * Set the given constant for the given value. 
		 * @param index The constant index.
		 * @param value The new constant value. 
		 */
		public void setConstantAt(int index, double value) {
			constantList[index] = value;
		}
		
		public double getConstantAt(int index) {
			return constantList[index];
		}
		
	}

}
