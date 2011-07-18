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

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import unbbayes.evaluation.exception.EvaluationException;

public class AirIDExactEvaluationTest {
	
	private static IEvaluation evaluationExact;
	
	private final float DELTA = .0001f;
	
	@BeforeClass
	public static void oneTimeSetUp() throws Exception {
		boolean computeTime = true;
		long time = 0;
		
		List<String> targetNodeNameList = new ArrayList<String>();
		targetNodeNameList.add("TargetType");

		List<String> evidenceNodeNameList = new ArrayList<String>();
		evidenceNodeNameList.add("UHRR_Confusion");
		evidenceNodeNameList.add("ModulationFrequency");
		evidenceNodeNameList.add("CenterFrequency");
		evidenceNodeNameList.add("PRI");
		evidenceNodeNameList.add("PRF");

		String netFileName = "src/test/resources/testCases/evaluation/AirID.xml";

		evaluationExact = new ExactEvaluation();
		if (computeTime) {
			time = System.currentTimeMillis();
		}
		evaluationExact.evaluate(netFileName, targetNodeNameList,
				evidenceNodeNameList, true);
		if (computeTime) {
			time = System.currentTimeMillis() - time;
			System.out.println("Time to compute evaluation: " + (float)(time)/1000 + " seconds");
		}
		
	}
	
	@AfterClass
	public static void oneTimeTearDown() throws Exception {
		evaluationExact = null;
	}
	
	@Test
	public void testCM() {
		float[][] cm = evaluationExact.getEvidenceSetCM();
		
		// CM
		// [0.7655 0.1087 0.0353 0.0353 0.0517 0.0586] 
		// [0.1087 0.7680 0.0348 0.0348 0.0503 0.0571] 
		// [0.0353 0.0348 0.7661 0.1087 0.0517 0.0586] 
		// [0.0353 0.0348 0.1087 0.7661 0.0517 0.0586] 
		// [0.0258 0.0252 0.0258 0.0258 0.6094 0.1851] 
		// [0.0293 0.0286 0.0293 0.0293 0.1851 0.5818] 
		
		// Columns add to one
		assertEquals(.7655, cm[0][0], DELTA);
		assertEquals(.1087, cm[1][0], DELTA);
		assertEquals(.0353, cm[2][0], DELTA);
		assertEquals(.0353, cm[3][0], DELTA);
		assertEquals(.0258, cm[4][0], DELTA);
		assertEquals(.0293, cm[5][0], DELTA);
		
		assertEquals(.1087, cm[0][1], DELTA);
		assertEquals(.7680, cm[1][1], DELTA);
		assertEquals(.0348, cm[2][1], DELTA);
		assertEquals(.0348, cm[3][1], DELTA);
		assertEquals(.0252, cm[4][1], DELTA);
		assertEquals(.0286, cm[5][1], DELTA);
		
		assertEquals(.0353, cm[0][2], DELTA);
		assertEquals(.0348, cm[1][2], DELTA);
		assertEquals(.7661, cm[2][2], DELTA);
		assertEquals(.1087, cm[3][2], DELTA);
		assertEquals(.0258, cm[4][2], DELTA);
		assertEquals(.0293, cm[5][2], DELTA);
		
		assertEquals(.0353, cm[0][3], DELTA);
		assertEquals(.0348, cm[1][3], DELTA);
		assertEquals(.1087, cm[2][3], DELTA);
		assertEquals(.7661, cm[3][3], DELTA);
		assertEquals(.0258, cm[4][3], DELTA);
		assertEquals(.0293, cm[5][3], DELTA);
		
		assertEquals(.0517, cm[0][4], DELTA);
		assertEquals(.0503, cm[1][4], DELTA);
		assertEquals(.0517, cm[2][4], DELTA);
		assertEquals(.0517, cm[3][4], DELTA);
		assertEquals(.6094, cm[4][4], DELTA);
		assertEquals(.1851, cm[5][4], DELTA);
		
		assertEquals(.0586, cm[0][5], DELTA);
		assertEquals(.0571, cm[1][5], DELTA);
		assertEquals(.0586, cm[2][5], DELTA);
		assertEquals(.0586, cm[3][5], DELTA);
		assertEquals(.1851, cm[4][5], DELTA);
		assertEquals(.5818, cm[5][5], DELTA);
	}
	
	@Test
	public void testPcc() throws EvaluationException {
		
		float pcc = evaluationExact.getEvidenceSetPCC();
		
		// Pcc = 70.95
		assertEquals(.7095, pcc, DELTA);
	}
	
}
