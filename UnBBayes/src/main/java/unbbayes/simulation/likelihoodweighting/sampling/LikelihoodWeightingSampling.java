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
package unbbayes.simulation.likelihoodweighting.sampling;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import unbbayes.prs.Node;
import unbbayes.prs.bn.ProbabilisticNetwork;
import unbbayes.prs.bn.ProbabilisticNode;
import unbbayes.prs.bn.TreeVariable;
import unbbayes.simulation.likelihoodweighting.ILikelihoodWeightingSampling;
import unbbayes.simulation.montecarlo.sampling.MatrixMonteCarloSampling;

/**
 * 
 * Likelihood Weighting sampling based on MC sampling. However, it does not 
 * sample for the evidence nodes, it just sets as the given state, and it 
 * calculates P(E|Par(E)) for each trial. 
 * 
 * @author Rommel Carvalho (rommel.carvalho@gmail.com)
 *
 */
// TODO ROMMEL - CREATE ONE SAMPLING THAT USES MAPMCSAMPLING
// TODO since evaluation classes are using sampling classes, I could not move this sampling class into a plugin folder.
public class LikelihoodWeightingSampling extends MatrixMonteCarloSampling implements ILikelihoodWeightingSampling {
	
	protected List<Node> evidenceNodeList;
	// P(E|Par(E)) = ProductOf[P(Ei|Par(Ei))] for all evidences (findings). There is
	// one probability associated with each trial.
	protected float [] probabilityEvidenceGivenParentList;

	/**
	 * Return P(E|Par(E)) = ProductOf[P(Ei|Par(Ei))] for all evidences (findings). 
	 * There is one probability associated with each trial.
	 * @return P(E|Par(E)) for each trial.
	 */
	public float[] getFullStatesSetWeight() {
		return probabilityEvidenceGivenParentList;
	}

	@Override
	/**
	 * Responsible for setting the initial variables for Likelihood Weighting.
	 * Besides sampling (like MC), it calculates P(E|Par(E)) for each trial.
	 * @param pn Probabilistic network that will be used for sampling.
	 * @param nTrials Number of trials to generate.
	 */
	public void start(ProbabilisticNetwork pn , int nTrials){
		this.probabilityEvidenceGivenParentList = new float[nTrials];
		// Init all values as 1, because we are going to multiply these numbers for 
		// P(E|Par(E)) = ProductOf[P(Ei|Par(Ei))] for all evidences (findings).
		for (int i = 0; i < this.probabilityEvidenceGivenParentList.length; i++) {
			probabilityEvidenceGivenParentList[i] = 1;
		}
		super.start(pn, nTrials);
	}
	
	@Override
	/**
	 * It does the same thing as the MC sampling, but now it calculates P(E|Par(E)) \
	 * for each trial. 
	 */
	protected void simulate(int nTrial) {
		List<Integer> parentsIndexes = new ArrayList<Integer>();
		double[] pmf;
		int[] sampledStates = new int[samplingNodeOrderQueue.size()];
		for(int i = 0 ; i < samplingNodeOrderQueue.size(); i++){			
			ProbabilisticNode node = (ProbabilisticNode)samplingNodeOrderQueue.get(i);									
			parentsIndexes = getParentsIndexesInQueue(node);
			pmf = getProbabilityMassFunction(sampledStates, parentsIndexes, node);
			// If it is an evidence node, then we do not need to sample it.
			if (((TreeVariable)node).hasEvidence()) {
				sampledStates[i] = ((TreeVariable)node).getEvidence();
				// P(E|Par(E)) = ProductOf[P(Ei|Par(Ei))] for all evidences (findings)
				// We are now multiplying the evidence i with all previous evidences (or 1, otherwise).
				probabilityEvidenceGivenParentList[nTrial] *= pmf[sampledStates[i]];
			} else {
				sampledStates[i] = getState(pmf);
			}
			sampledStatesMatrix[nTrial][i] = (byte)sampledStates[i];
		}	
	}

	/**
	 * Not implemented.
	 * @return null.
	 */
	public float[] getCompactStatesSetWeight() {
		return null;
	}

	/**
	 * Not implemented.
	 * @return null.
	 */
	public Map<Integer, Float> getMapStatesSetWeight() {
		return null;
	}
	
}
