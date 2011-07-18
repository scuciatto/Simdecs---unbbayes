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

//TODO ROMMEL - CREATE TEST CASE FOR OTHER EVALUATION ALGORITHMS AND FOR LIKELIHOOD INFERENCE
public class AirIDEvaluationTest {
	
	private static IEvaluation evaluationApproximate;
	
	private final float DELTA = .01f;
	
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

		int sampleSize = 500000;

		evaluationApproximate = new FastMCApproximateEvaluation(sampleSize);
		if (computeTime) {
			time = System.currentTimeMillis();
		}
		evaluationApproximate.evaluate(netFileName, targetNodeNameList,
				evidenceNodeNameList, true);
		if (computeTime) {
			time = System.currentTimeMillis() - time;
			System.out.println("Time to compute evaluation: " + (float)(time)/1000 + " seconds");
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
		// [80.86 9.44 2.71 2.74 4.03 4.47 ]
		// [9.44 80.92 2.70 2.73 3.97 4.43 ]
		// [2.71 2.70 80.76 9.57 3.96 4.54 ]
		// [2.74 2.73 9.57 80.70 4.01 4.52 ]
		// [2.02 1.99 1.98 2.01 67.34 16.69]
		// [2.23 2.21 2.27 2.26 16.69 65.35]
		
		// Columns add to one
		assertEquals(.8086, cm[0][0], DELTA);
		assertEquals(.0944, cm[1][0], DELTA);
		assertEquals(.0271, cm[2][0], DELTA);
		assertEquals(.0274, cm[3][0], DELTA);
		assertEquals(.0202, cm[4][0], DELTA);
		assertEquals(.0223, cm[5][0], DELTA);
		
		assertEquals(.0944, cm[0][1], DELTA);
		assertEquals(.8092, cm[1][1], DELTA);
		assertEquals(.0270, cm[2][1], DELTA);
		assertEquals(.0273, cm[3][1], DELTA);
		assertEquals(.0199, cm[4][1], DELTA);
		assertEquals(.0221, cm[5][1], DELTA);
		
		assertEquals(.0271, cm[0][2], DELTA);
		assertEquals(.0270, cm[1][2], DELTA);
		assertEquals(.8076, cm[2][2], DELTA);
		assertEquals(.0957, cm[3][2], DELTA);
		assertEquals(.0198, cm[4][2], DELTA);
		assertEquals(.0227, cm[5][2], DELTA);
		
		assertEquals(.0274, cm[0][3], DELTA);
		assertEquals(.0273, cm[1][3], DELTA);
		assertEquals(.0957, cm[2][3], DELTA);
		assertEquals(.8070, cm[3][3], DELTA);
		assertEquals(.0201, cm[4][3], DELTA);
		assertEquals(.0226, cm[5][3], DELTA);
		
		assertEquals(.0403, cm[0][4], DELTA);
		assertEquals(.0397, cm[1][4], DELTA);
		assertEquals(.0396, cm[2][4], DELTA);
		assertEquals(.0401, cm[3][4], DELTA);
		assertEquals(.6734, cm[4][4], DELTA);
		assertEquals(.1669, cm[5][4], DELTA);
		
		assertEquals(.0447, cm[0][5], DELTA);
		assertEquals(.0443, cm[1][5], DELTA);
		assertEquals(.0454, cm[2][5], DELTA);
		assertEquals(.0452, cm[3][5], DELTA);
		assertEquals(.1669, cm[4][5], DELTA);
		assertEquals(.6535, cm[5][5], DELTA);
	}
	
	@Test
	public void testPcc() throws EvaluationException {
		
		float pcc = evaluationApproximate.getEvidenceSetPCC();
		
		// Pcc = 75.99
		assertEquals(.7599, pcc, DELTA);
	}
	
}
