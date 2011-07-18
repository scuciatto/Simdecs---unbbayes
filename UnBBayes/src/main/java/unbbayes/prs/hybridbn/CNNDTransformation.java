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

import unbbayes.prs.Node;

/**
 * This class is responsible for the Continuous Node Normal Distribution transformations.
 * @author Rommel Carvalho (rommel.carvalho@gmail.com)
 * @deprecated Continuous node is no longer supported in UnBBayes core. It has 
 * now been replaced by the CPS plugin available at http://sourceforge.net/projects/prognos/.
 */
public class CNNDTransformation {
	
	/**
	 * http://mathworld.wolfram.com/NormalSumDistribution.html
	 * @param cNode
	 * @return
	 * @throws Exception
	 */
	public static double getMean(ContinuousNode cNode) throws Exception {
		CNNormalDistribution cDistribution = cNode.getCnNormalDistribution();
		double mean = 0.0;
		if (cDistribution.getDiscreteParentList().size() > 0) {
			throw new Exception("Not implemented yet!");
		}
		
		Node node;
		// Since we are supposing we just have continuous nodes as parents.
		int ndfIndex = 0;
		mean += cDistribution.getMean(ndfIndex);
		for (int i = 0; i < cDistribution.getContinuousParentList().size(); i++) {
			node = cDistribution.getContinuousParentList().get(i);
			mean += cDistribution.getConstantAt(i, ndfIndex) * CNNDTransformation.getMean((ContinuousNode)node);
		}
		
		return mean;
	}
	
	/**
	 * http://mathworld.wolfram.com/NormalSumDistribution.html
	 * @param cNode
	 * @return
	 * @throws Exception
	 */
	public static double getVariance(ContinuousNode cNode) throws Exception {
		CNNormalDistribution cDistribution = cNode.getCnNormalDistribution();
		double variance = 0.0;
		if (cDistribution.getDiscreteParentList().size() > 0) {
			throw new Exception("Not implemented yet!");
		}
		
		Node node;
		// Since we are supposing we just have continuous nodes as parents.
		int ndfIndex = 0;
		variance += cDistribution.getVariance(ndfIndex);
		for (int i = 0; i < cDistribution.getContinuousParentList().size(); i++) {
			node = cDistribution.getContinuousParentList().get(i);
			variance += Math.pow(cDistribution.getConstantAt(i, ndfIndex), 2) * CNNDTransformation.getVariance((ContinuousNode)node);
		}
		
		return variance;
	}

}
