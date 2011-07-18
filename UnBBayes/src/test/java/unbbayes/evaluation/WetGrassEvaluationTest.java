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


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import unbbayes.evaluation.exception.EvaluationException;

public class WetGrassEvaluationTest {
	
	private static IEvaluation evaluationApproximate;
	
	private final float DELTA = .01f;
	private static final float COST_C = 10f;
	private static final float COST_R = 100f;
	private static final float COST_W = 1000f;
	
	@BeforeClass
	public static void oneTimeSetUp() throws Exception {
		boolean computeTime = true;
		long time = 0;
		
		List<String> targetNodeNameList = new ArrayList<String>();
		targetNodeNameList.add("Springler");

		List<String> evidenceNodeNameList = new ArrayList<String>();
		evidenceNodeNameList.add("Cloudy");
		evidenceNodeNameList.add("Rain");
		evidenceNodeNameList.add("Wet");

		String netFileName = "src/test/resources/testCases/evaluation/WetGrass.xml";

		int sampleSize = 100000;

		evaluationApproximate = new FastMCApproximateEvaluation(sampleSize);
		if (computeTime) {
			time = System.currentTimeMillis();
		}
		evaluationApproximate.evaluate(netFileName, targetNodeNameList,
				evidenceNodeNameList, false);
		if (computeTime) {
			time = System.currentTimeMillis() - time;
			System.out.println("Time to compute evaluation: " + (float)(time)/1000 + " seconds");
		}
		
		List<EvidenceEvaluation> list = evaluationApproximate.getEvidenceEvaluationList();
		for (EvidenceEvaluation evidenceEvaluation : list) {
			if (evidenceEvaluation.getName().equals("Cloudy")) {
				evidenceEvaluation.setCost(COST_C);
			} else if (evidenceEvaluation.getName().equals("Rain")) {
				evidenceEvaluation.setCost(COST_R);
			} else if (evidenceEvaluation.getName().equals("Wet")) {
				evidenceEvaluation.setCost(COST_W);
			}
		}
		
	}
	
	@AfterClass
	public static void oneTimeTearDown() throws Exception {
		evaluationApproximate = null;
	}
	
	@Test
	public void testCM() {
		float[][] cm = evaluationApproximate.getEvidenceSetCM();
		
		// CM
		// [73.75 11.27]
		// [26.25 88.73]
		
		// Columns add to one
		assertEquals(.7375, cm[0][0], DELTA);
		assertEquals(1-.7375, cm[1][0], DELTA);
		assertEquals(.8873, cm[1][1], DELTA);
		assertEquals(1-.8873, cm[0][1], DELTA);
	}
	
	@Test
	public void testPcc() throws EvaluationException {
		
		float pcc = evaluationApproximate.getEvidenceSetPCC();
		
		// Pcc = 81.24
		assertEquals(.8124, pcc, DELTA);
	}
	
	@Test
	public void testBestMarginalImprovement() throws EvaluationException {
		List<EvidenceEvaluation> list = evaluationApproximate.getBestMarginalImprovement();
		
		assertTrue(list.get(0).getMarginalImprovement() >= list.get(1).getMarginalImprovement());
		assertTrue(list.get(1).getMarginalImprovement() >= list.get(2).getMarginalImprovement());
	}
	
	@Test
	public void testBestIndividualPcc() throws EvaluationException {
		List<EvidenceEvaluation> list = evaluationApproximate.getBestIndividualPCC();
		
		assertTrue(list.get(0).getIndividualPCC() >= list.get(1).getIndividualPCC());
		assertTrue(list.get(1).getIndividualPCC() >= list.get(2).getIndividualPCC());
	}
	
	@Test
	public void testBestIndividualCostRate() throws EvaluationException {
		List<EvidenceEvaluation> list = evaluationApproximate.getBestIndividualCostRate();
		
		assertTrue(list.get(0).getMarginalCost() >= list.get(1).getMarginalCost());
		assertTrue(list.get(1).getMarginalCost() >= list.get(2).getMarginalCost());
	}
	
	@Test
	public void testMCM() throws EvaluationException {
		List<EvidenceEvaluation> list = evaluationApproximate.getBestMarginalImprovement();
		
		// ***Wet***
		EvidenceEvaluation evidenceEvaluation = list.get(0);
		
		assertEquals("Wet", evidenceEvaluation.getName());
		
		float[][] cm = evidenceEvaluation.getMarginalCM();
		
		// MCM
		// [43.5 24.5]
		// [56.5 75.5]
		
		// Columns add to one
		assertEquals(.435, cm[0][0], DELTA);
		assertEquals(1-.435, cm[1][0], DELTA);
		assertEquals(.755, cm[1][1], DELTA);
		assertEquals(1-.755, cm[0][1], DELTA);
		
		
		
		// ***Rain***
		evidenceEvaluation = list.get(1);
		
		assertEquals("Rain", evidenceEvaluation.getName());
		
		cm = evidenceEvaluation.getMarginalCM();
		
		// MCM
		// [66.5 14.5]
		// [33.5 85.5]
		
		// Columns add to one
		assertEquals(.665, cm[0][0], DELTA);
		assertEquals(1-.665, cm[1][0], DELTA);
		assertEquals(.855, cm[1][1], DELTA);
		assertEquals(1-.855, cm[0][1], DELTA);
		
		
		
		// ***Cloudy***
		evidenceEvaluation = list.get(2);
		
		assertEquals("Cloudy", evidenceEvaluation.getName());
		
		cm = evidenceEvaluation.getMarginalCM();
		
		// MCM
		// [69.0 13.5]
		// [31.0 86.5]
		
		// Columns add to one
		assertEquals(.69, cm[0][0], DELTA);
		assertEquals(1-.69, cm[1][0], DELTA);
		assertEquals(.865, cm[1][1], DELTA);
		assertEquals(1-.865, cm[0][1], DELTA);
		
	}
	
	@Test
	public void testMPcc() throws EvaluationException {
		List<EvidenceEvaluation> list = evaluationApproximate.getBestMarginalImprovement();
		
		// ***Wet***
		EvidenceEvaluation evidenceEvaluation = list.get(0);
		
		assertEquals("Wet", evidenceEvaluation.getName());
		
		float pcc = evidenceEvaluation.getMarginalPCC();
		
		// MPcc = 59.5
		assertEquals(.595, pcc, DELTA);
		
		
		
		// ***Rain***
		evidenceEvaluation = list.get(1);
		
		assertEquals("Rain", evidenceEvaluation.getName());
		
		pcc = evidenceEvaluation.getMarginalPCC();
		
		// MPcc = 76.5
		assertEquals(.765, pcc, DELTA);
		
		
		
		// ***Cloudy***
		evidenceEvaluation = list.get(2);
		
		assertEquals("Cloudy", evidenceEvaluation.getName());
		
		pcc = evidenceEvaluation.getMarginalPCC();
		
		// MPcc = 78.0
		assertEquals(.78, pcc, DELTA);
		
	}
	
	@Test
	public void testMI() throws EvaluationException {
		List<EvidenceEvaluation> list = evaluationApproximate.getBestMarginalImprovement();
		
		// ***Wet***
		EvidenceEvaluation evidenceEvaluation = list.get(0);
		
		assertEquals("Wet", evidenceEvaluation.getName());
		
		float mi = evidenceEvaluation.getMarginalImprovement();
		
		// MI = 21.5
		assertEquals(.215, mi, DELTA);
		
		
		
		// ***Rain***
		evidenceEvaluation = list.get(1);
		
		assertEquals("Rain", evidenceEvaluation.getName());
		
		mi = evidenceEvaluation.getMarginalImprovement();
		
		// MI = 5.0
		assertEquals(.05, mi, DELTA);
		
		
		
		// ***Cloudy***
		evidenceEvaluation = list.get(2);
		
		assertEquals("Cloudy", evidenceEvaluation.getName());
		
		mi = evidenceEvaluation.getMarginalImprovement();
		
		// MI = 3.5
		assertEquals(.035, mi, DELTA);
		
	}
	
	@Test
	public void testLCM() throws EvaluationException {
		List<EvidenceEvaluation> list = evaluationApproximate.getBestMarginalImprovement();
		
		// ***Wet***
		EvidenceEvaluation evidenceEvaluation = list.get(0);
		
		assertEquals("Wet", evidenceEvaluation.getName());
		
		float[][] cm = evidenceEvaluation.getIndividualLCM();
		
		// LCM
		// [40.5 25.5]
		// [59.5 74.5]
		
		// Columns add to one
		assertEquals(.405, cm[0][0], DELTA);
		assertEquals(1-.405, cm[1][0], DELTA);
		assertEquals(.745, cm[1][1], DELTA);
		assertEquals(1-.745, cm[0][1], DELTA);
		
		
		
		// ***Rain***
		evidenceEvaluation = list.get(1);
		
		assertEquals("Rain", evidenceEvaluation.getName());
		
		cm = evidenceEvaluation.getIndividualLCM();
		
		// LCM
		// [34.0 28.0]
		// [65.0 72.0]
		
		// Columns add to one
		assertEquals(.34, cm[0][0], DELTA);
		assertEquals(1-.34, cm[1][0], DELTA);
		assertEquals(.72, cm[1][1], DELTA);
		assertEquals(1-.72, cm[0][1], DELTA);
		
		
		
		// ***Cloudy***
		evidenceEvaluation = list.get(2);
		
		assertEquals("Cloudy", evidenceEvaluation.getName());
		
		cm = evidenceEvaluation.getIndividualLCM();
		
		// LCM
		// [43.0 24.5]
		// [57.0 75.5]
		
		// Columns add to one
		assertEquals(.43, cm[0][0], DELTA);
		assertEquals(1-.43, cm[1][0], DELTA);
		assertEquals(.755, cm[1][1], DELTA);
		assertEquals(1-.755, cm[0][1], DELTA);
		
	}
	
	@Test
	public void testLPcc() throws EvaluationException {
		List<EvidenceEvaluation> list = evaluationApproximate.getBestMarginalImprovement();
		
		// ***Wet***
		EvidenceEvaluation evidenceEvaluation = list.get(0);
		
		assertEquals("Wet", evidenceEvaluation.getName());
		
		float pcc = evidenceEvaluation.getIndividualPCC();
		
		// Pcc = 57.5
		assertEquals(.575, pcc, DELTA);
		
		
		
		// ***Rain***
		evidenceEvaluation = list.get(1);
		
		assertEquals("Rain", evidenceEvaluation.getName());
		
		pcc = evidenceEvaluation.getIndividualPCC();
		
		// Pcc = 53.5
		assertEquals(.535, pcc, DELTA);
		
		
		
		// ***Cloudy***
		evidenceEvaluation = list.get(2);
		
		assertEquals("Cloudy", evidenceEvaluation.getName());
		
		pcc = evidenceEvaluation.getIndividualPCC();
		
		// Pcc = 59.5
		assertEquals(.595, pcc, DELTA);
		
	}
	
	@Test
	public void testCostRatio() throws EvaluationException {
		List<EvidenceEvaluation> list = evaluationApproximate.getBestMarginalImprovement();
		
		// ***Wet***
		EvidenceEvaluation evidenceEvaluation = list.get(0);
		
		assertEquals("Wet", evidenceEvaluation.getName());
		
		float cost = COST_W;
		
		assertEquals(cost, evidenceEvaluation.getCost(), 0);
		
		assertEquals(.575/cost, evidenceEvaluation.getMarginalCost(), DELTA/cost);
		
		
		
		// ***Rain***
		evidenceEvaluation = list.get(1);
		
		assertEquals("Rain", evidenceEvaluation.getName());
		
		cost = COST_R;
		
		assertEquals(cost, evidenceEvaluation.getCost(), 0);
		
		assertEquals(.535/cost, evidenceEvaluation.getMarginalCost(), DELTA/cost);
		
		
		
		// ***Cloudy***
		evidenceEvaluation = list.get(2);
		
		assertEquals("Cloudy", evidenceEvaluation.getName());
		
		cost = COST_C;
		
		assertEquals(cost, evidenceEvaluation.getCost(), 0);
		
		assertEquals(.595/cost, evidenceEvaluation.getMarginalCost(), DELTA/cost);
		
	}

}
