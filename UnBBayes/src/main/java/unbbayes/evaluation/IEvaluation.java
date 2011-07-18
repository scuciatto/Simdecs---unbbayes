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

import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;

import unbbayes.evaluation.exception.EvaluationException;
import unbbayes.io.exception.LoadException;
import unbbayes.prs.bn.ProbabilisticNetwork;
import unbbayes.util.longtask.ILongTaskProgressObservable;

public interface IEvaluation extends ILongTaskProgressObservable {

	public float[][] getEvidenceSetCM();
	
	public float getError();

	public float getEvidenceSetPCC() throws EvaluationException;
	
	public List<EvidenceEvaluation> getEvidenceEvaluationList();
	
	public List<EvidenceEvaluation> getBestIndividualPCC() throws EvaluationException;
	
	public List<EvidenceEvaluation> getBestIndividualCostRate() throws EvaluationException;
	
	public List<EvidenceEvaluation> getBestMarginalImprovement() throws EvaluationException;
	
	public void evaluate(String netFileName, List<String> targetNodeNameList,
			List<String> evidenceNodeNameList, boolean onlyGCM)  throws LoadException, IOException, JAXBException, EvaluationException;

	public void evaluate(ProbabilisticNetwork net,
			List<String> targetNodeNameList, List<String> evidenceNodeNameList, boolean onlyGCM)  throws EvaluationException;


}
