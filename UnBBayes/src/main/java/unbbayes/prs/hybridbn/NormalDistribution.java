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

import unbbayes.util.ProbabilityMath;

/**
 * 
 * @author Rommel Carvalho (rommel.carvalho@gmail.com)
 * @deprecated Continuous node is no longer supported in UnBBayes core. It has 
 * now been replaced by the CPS plugin available at http://sourceforge.net/projects/prognos/.
 */
public class NormalDistribution {
	
	private double mean;
	private double variance;
	
	public NormalDistribution() {
		this.mean = 0;
		this.variance = 1;
	}
	
	public NormalDistribution(double mean, double variance) {
		this.mean = mean;
		this.variance = variance;
	}

	/**
	 * Calculate x using the standard normal distribution from the P(Z <= z) 
	 * where z = (x - mean)/(standard variance) and P(Z <= z) = P(X <= x).
	 * 
	 * @param probability P(X <= x).
	 * @return The CDF upper bound (x).
	 */
	public double getCDFUpperBound(double probability) {
		double z = ProbabilityMath.inverseNormal(probability);
		double x = Math.sqrt(variance) * z + mean;
		return x;
	}
	
	/**
	 * Calculate P(X <= x) using the standard normal distribution P(Z <= z) 
	 * where z = (x - mean)/(standard variance) and P(Z <= z) = P(X <= x).
	 * @param x The CDF upper bound.
	 * @return P(X <= x).
	 */
	public double getCDF(double x) {
		double z = (x - mean) / Math.sqrt(variance);
		double probability = ProbabilityMath.normalCdf(z);
		return probability;
	}
	
	/**
	 * Calculate probability for an interval from CDF. 
	 * P(ini <= X <= end) = P(X <= end) - P(X <= ini).
	 * @param ini The interval initial value.
	 * @param end The interval end value.
	 * @return The probability for the interval from initial to end value.
	 */
	public double getProbability(double ini, double end) {
		return getCDF(end) - getCDF(ini);
	}
	
	/**
	 * Returns the probability of a given value x for this normal distribution.
	 * @param x The value we want to know the probability of.
	 * @return The probability of x for this normal distribution.
	 */
	public double getProbability(double x) {
		return ProbabilityMath.getNormalPDF(x, this.mean, this.variance);
	}
	
	/**
	 * Get the normal distribution mean.
	 * @return The normal distribution mean.
	 */
	public double getMean() {
		return mean;
	}
	
	/**
	 * Set the normal distribution mean.
	 * @param mean The normal distribution mean.
	 */
	public void setMean(double mean) {
		this.mean = mean;
	}
	
	/**
	 * Get the normal distribution variance.
	 * @return The normal distribution variance.
	 */
	public double getVariance() {
		return variance;
	}
	
	/**
	 * Set the normal distribution variance.
	 * @param variance The normal distribution variance.
	 */
	public void setVariance(double variance) {
		this.variance = variance;
	}
	
	public static void main(String[] args) {
		NormalDistribution nd = new NormalDistribution(700, 300);
		
		nd.getCDFUpperBound(.6);
	}
}
