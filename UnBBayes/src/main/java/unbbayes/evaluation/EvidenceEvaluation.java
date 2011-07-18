/*
 *  UnBBayes
 *  Copyright (C) 2002, 2009 Universidade de Brasilia - http://www.unb.br
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
package unbbayes.evaluation;

import unbbayes.evaluation.exception.EvaluationException;

public class EvidenceEvaluation {

	private String name;

	private float cost = MemoryEfficientApproximateEvaluation.UNSET_VALUE;

	// Individual probability of correct classification
	private float individualPCC = MemoryEfficientApproximateEvaluation.UNSET_VALUE;

	// Individual local confusion matrix
	private float[][] LCM;

	// Probability of correct classification of the evidence set without
	// this evidence
	private float marginalPCC = MemoryEfficientApproximateEvaluation.UNSET_VALUE;

	// Local confusion matrix of the evidence set without this evidence
	private float[][] marginalCM;

	// The evidence set PCC minus the setPCC (PCC of the set without this
	// evidence)
	private float marginalImprovement = MemoryEfficientApproximateEvaluation.UNSET_VALUE;

	// Individual PCC divided by its cost
	private float marginalCost = MemoryEfficientApproximateEvaluation.UNSET_VALUE;

	// Evidence set PCC used to compute the marginal improvement
	private float evidenceSetPcc;

	public EvidenceEvaluation(String name, float evidenceSetPcc) {
		this.name = name;
		this.evidenceSetPcc = evidenceSetPcc;
	}

	public EvidenceEvaluation(String name, float evidenceSetPcc, float cost) {
		this(name, evidenceSetPcc);
		this.cost = cost;
	}

	public float getIndividualPCC() throws EvaluationException {
		if (individualPCC == MemoryEfficientApproximateEvaluation.UNSET_VALUE) {
			if (LCM == null) {
				throw new EvaluationException(
						"Must calculate individual LCM before computing individual PCC.");
			}
			individualPCC = 0;
			for (int i = 0; i < LCM.length; i++) {
				individualPCC += LCM[i][i];
			}
			individualPCC /= LCM.length;
		}
		return individualPCC;
	}

	public float getMarginalPCC() throws EvaluationException {
		if (marginalPCC == MemoryEfficientApproximateEvaluation.UNSET_VALUE) {
			if (marginalCM == null) {
				throw new EvaluationException(
						"Must calculate marginal LCM before computing marginal PCC.");
			}
			marginalPCC = 0;
			for (int i = 0; i < marginalCM.length; i++) {
				marginalPCC += marginalCM[i][i];
			}
			marginalPCC /= marginalCM.length;
		}
		return marginalPCC;
	}

	public float getMarginalImprovement() throws EvaluationException {
		if (marginalImprovement == MemoryEfficientApproximateEvaluation.UNSET_VALUE) {
			marginalImprovement = evidenceSetPcc - getMarginalPCC();
		}
		return marginalImprovement;
	}

	public float getMarginalCost() throws EvaluationException {
		if (marginalCost == MemoryEfficientApproximateEvaluation.UNSET_VALUE) {
			if (cost == MemoryEfficientApproximateEvaluation.UNSET_VALUE) {
				throw new EvaluationException(
						"Must set cost before computing cost rate.");
			}
			try {
				marginalCost =  cost / (getIndividualPCC() * 100);
			} catch(EvaluationException e) {
				throw new EvaluationException(
				"Must calculate individual Pcc before computing cost rate." + " " + e.getMessage());
			}
		}
		return marginalCost;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public float[][] getIndividualLCM() {
		return LCM;
	}

	public void setLCM(float[][] LCM) {
		this.LCM = LCM;
	}

	public float[][] getMarginalCM() {
		return marginalCM;
	}

	public void setMarginalCM(float[][] marginalCM) {
		this.marginalCM = marginalCM;
	}

	public String getName() {
		return name;
	}

}
