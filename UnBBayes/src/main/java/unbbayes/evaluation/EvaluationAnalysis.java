package unbbayes.evaluation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import unbbayes.io.BaseIO;
import unbbayes.io.NetIO;
import unbbayes.io.XMLBIFIO;
import unbbayes.io.exception.LoadException;
import unbbayes.prs.Node;
import unbbayes.prs.bn.ProbabilisticNetwork;
import unbbayes.prs.bn.TreeVariable;
import unbbayes.util.Debug;

public class EvaluationAnalysis {
	
	protected ProbabilisticNetwork net;
	protected int statesProduct;
	protected IEvaluation evaluation;
	
	public void computeErrorBySampleSize(String netFileName, List<String> targetNodeNameList,
			List<String> evidenceNodeNameList, float exactPcc) throws Exception {
		
		loadNetwork(netFileName);
		computeStatesProduct(targetNodeNameList, evidenceNodeNameList);
		
		// As the error is a function of P(E|T) it only depends on the size of E and not T
		statesProduct /= targetNodeNameList.get(0).length();
		
		int sampleSize = 0;
		if (evidenceNodeNameList.size() == 2) {
			sampleSize = 500;
		} else if (evidenceNodeNameList.size() == 3) {
			sampleSize = 5000;
		} else if (evidenceNodeNameList.size() == 4) {
			sampleSize = 50000;
		} else if (evidenceNodeNameList.size() == 5) {
			sampleSize = 500000;
		} else {
			return;
		}
		
//		Debug.setDebug(true);
//		Debug.println("\n****************************************\n");
//		Debug.print("\n%80s , %20s , %20s , %20s , %20s , %20s , %20s , %20s\n", "Evidences", "States Size", "Exact Pcc", "Sample Size", "Sample / States", "Mean", "Variance", "Time");
//		Debug.print("%20s %15s", "Target: ", targetNodeNameList.get(0));
//		Debug.print("\n%20s", "Evidences: ");
//		for (String evidence : evidenceNodeNameList) {
//			Debug.print("%20s ", evidence);
//		}
//		Debug.print("\n%20s %15d", "States product: ", statesProduct);
//		Debug.print(" , %20d", statesProduct);
//		Debug.print("\n%20s %10.5f", "Exact Pcc: ", exactPcc);
//		Debug.print(" , %1.19f", exactPcc);
		
		// pcc[i][j][k], where i is the approximate error 
		// (i = 0 => .05 => startSampleSize*5, i = 1 => .02 => startSampleSize*10, i = 2 => .005 => startSampleSize*50)
		// j for different sample size (j = 0 => sampleSize/10, i = 1 => sampleSize, i = 2 => sampleSize*10)
		// k is each of the 100 pcc we are going to compute.
		float approximatePcc[][][] = new float[3][3][100];
		long init;
		long end;
		for (int i = 0; i < approximatePcc.length; i++) {
			if (i == 0) {
				sampleSize *= 5;
			} else if (i == 1) {
				sampleSize *= 10;
			} else if (i == 2) {
				sampleSize *= 50;
			}
			for (int j = 0; j < approximatePcc[0].length; j++) {
				if (j == 0) {
					sampleSize /= 10;
				} else if (j == 1) {
				} else if (j == 2) {
					sampleSize *= 10;
				}
				Debug.setDebug(true);
				Debug.println("");
				for (int b = 0; b < 4 - evidenceNodeNameList.size(); b++) {
					Debug.print("%20s", "");
				}
				for (String evidence : evidenceNodeNameList) {
					Debug.print("%20s", evidence);
				}
				Debug.print(" , %20d", statesProduct);
				Debug.print(" , %1.19f", exactPcc);
				Debug.print(" , %20d , %1.19f", sampleSize, (float)sampleSize / statesProduct);
				init = System.currentTimeMillis();
				for (int k = 0; k < approximatePcc[0][0].length && sampleSize < 10000000; k++) {
					Debug.setDebug(false);
					
					evaluation = new MemoryEfficientApproximateEvaluation(sampleSize);
					
					evaluation.evaluate(net, targetNodeNameList, evidenceNodeNameList, true);
					
//					Debug.setDebug(true);
//					Debug.println("Sample size: " + (times-1) + " * " + statesProduct + " = " + ((times-1)*statesProduct));
//					Debug.println("Time elapsed for evaluating: " + (float)(end-init)/1000);
//					Debug.setDebug(false);
					approximatePcc[i][j][k] = evaluation.getEvidenceSetPCC();
					System.gc();
					
					Debug.setDebug(true);
					//Debug.print("\n%20s %10.5f", "Approximate Pcc: ", approximatePcc[i][j][k]);
//					Debug.print("%10.5f", approximatePcc[i][j][k] - exactPcc);
				}
				end = System.currentTimeMillis();
				Debug.print(" , %1.19f , %1.19f", mean(approximatePcc[i][j]) - exactPcc, variance(approximatePcc[i][j]));
//				Debug.print("\n%20s %10.5f %15s\n", "Time spent: ", (float)(end-init)/1000, " seconds");
				Debug.print(" , %1.19f", (float)(end-init)/1000);
				if (j == 0) {
					sampleSize *= 10;
				} else if (j == 2) {
					sampleSize /= 10;
				}
			}
			if (i == 0) {
				sampleSize /= 5;
			} else if (i == 1) {
				sampleSize /= 10;
			} else if (i == 2) {
				sampleSize /= 50;
			}
		}
//		Debug.println("\n****************************************\n");
		Debug.setDebug(false);
	}
	
	public float mean(float[] values) {
		float total = 0;
		for (int i = 0; i < values.length; i++) {
			total += values[i];
		}
		return total / values.length;
	}
	
	public float variance(float[] values) {
		float total = 0;
		float total2 = 0;
		for (int i = 0; i < values.length; i++) {
			total += values[i];
			total2 += Math.pow(values[i], 2);
		}
		return (total2 / values.length) - (float)Math.pow((total / values.length), 2); 
	}
	
	public void computeSampleSizeByErrorVariance(String netFileName, List<String> targetNodeNameList,
			List<String> evidenceNodeNameList, float error, float exactPcc) throws Exception {
		
		loadNetwork(netFileName);
		computeStatesProduct(targetNodeNameList, evidenceNodeNameList);
		
		int times = 10;
		float approximatePcc;
		long init;
		long end;
		do {
			evaluation = new MemoryEfficientApproximateEvaluation(times*statesProduct);
			init = System.currentTimeMillis();
			evaluation.evaluate(net, targetNodeNameList, evidenceNodeNameList, true);
			end = System.currentTimeMillis();
//			Debug.setDebug(true);
//			Debug.println("Sample size: " + (times-1) + " * " + statesProduct + " = " + ((times-1)*statesProduct));
//			Debug.println("Time elapsed for evaluating: " + (float)(end-init)/1000);
//			Debug.setDebug(false);
			approximatePcc = evaluation.getEvidenceSetPCC();
			times *= 10;
			System.gc();
		} while (exactPcc + error < approximatePcc || approximatePcc < exactPcc - error);
		
		Debug.setDebug(true);
		Debug.println("Target: "+ targetNodeNameList.get(0));
		Debug.print("Evidences: ");
		for (String evidence : evidenceNodeNameList) {
			Debug.print(evidence + " ");
		}
		Debug.println("");
		Debug.println("States product: " + statesProduct);
		Debug.println("Sample size: " + (times/10) + " * " + statesProduct + " = " + ((times/10)*statesProduct));
		Debug.println("Time elapsed for evaluating: " + (float)(end-init)/1000 + " seconds");
		Debug.println("Exact Pcc: " + exactPcc);
		Debug.println("Approximate Pcc: " + approximatePcc);
		Debug.println("Error Pcc: " + error);
		Debug.println("");
		Debug.setDebug(false);
	}
	
	public void computeExactSampleSize(String netFileName, List<String> targetNodeNameList,
			List<String> evidenceNodeNameList) throws Exception {
		
		loadNetwork(netFileName);
		computeStatesProduct(targetNodeNameList, evidenceNodeNameList);
		
		float exactPcc;
		long init;
		long end;
		evaluation = new ExactEvaluation();
		init = System.currentTimeMillis();
		evaluation.evaluate(net, targetNodeNameList, evidenceNodeNameList, true);
		end = System.currentTimeMillis();
//			Debug.setDebug(true);
//			Debug.println("Sample size: " + (times-1) + " * " + statesProduct + " = " + ((times-1)*statesProduct));
//			Debug.println("Time elapsed for evaluating: " + (float)(end-init)/1000);
//			Debug.setDebug(false);
		exactPcc = evaluation.getEvidenceSetPCC();
		System.gc();
		
		Debug.setDebug(true);
		Debug.println("Target: "+ targetNodeNameList.get(0));
		Debug.print("Evidences: ");
		for (String evidence : evidenceNodeNameList) {
			Debug.print(evidence + " ");
		}
		Debug.println("");
		Debug.println("States product: " + statesProduct);
		Debug.println("Time elapsed for evaluating: " + (float)(end-init)/1000 + " seconds");
		Debug.println("Exact Pcc: " + exactPcc);
		Debug.println("");
		Debug.setDebug(false);
	}
	
	/**
	 * Computes the product of all states.
	 */
	protected void computeStatesProduct(List<String> targetNodeNameList,
			List<String> evidenceNodeNameList) {
		TreeVariable[] targetNodeList = new TreeVariable[targetNodeNameList.size()];
		TreeVariable[] evidenceNodeList = new TreeVariable[evidenceNodeNameList.size()];
		statesProduct = 1;
		int targetStatesProduct = 1;
		int evidenceStatesProduct = 1;

		// Create list of target TreeVariable
		int count = 0;
		for (String targetNodeName : targetNodeNameList) {
			Node targetNode = net.getNode(targetNodeName);

			targetNodeList[count] = (TreeVariable) targetNode;

			targetStatesProduct *= targetNode.getStatesSize();
			count++;
		}

		// Create list of evidence TreeVariable
		count = 0;
		for (String evidenceNodeName : evidenceNodeNameList) {
			Node evidenceNode = net.getNode(evidenceNodeName);

			evidenceNodeList[count] = (TreeVariable) evidenceNode;

			evidenceStatesProduct *= evidenceNode.getStatesSize();
			count++;
		}

		statesProduct = targetStatesProduct * evidenceStatesProduct;
	}
	
	protected void loadNetwork(String netFileName) throws LoadException, IOException {
		File netFile = new File(netFileName);
		String fileExt = netFileName.substring(netFileName.length() - 3);

		BaseIO io = null;
		if (fileExt.equalsIgnoreCase("xml")) {
			io = new XMLBIFIO();
		} else if (fileExt.equalsIgnoreCase("net")) {
			io = new NetIO();
		} else {
			throw new LoadException(
					"The network must be in XMLBIF 0.5 or NET format!");
		}
		net = (ProbabilisticNetwork)io.load(netFile);
	}
	
	public enum EvaluationAnalysisOption {
		EXACT,
		SAMPLE_SIZE,
		ERROR;
	}
	
	public static void runCorrectAirIDModel() throws Exception {
		
		String netFileName = "src/test/resources/testCases/evaluation/AirID.xml";
		
		List<String> targetNodeNameList = new ArrayList<String>();
		
		List<String> evidenceNodeNameList = new ArrayList<String>();
		
		targetNodeNameList = new ArrayList<String>();
		targetNodeNameList.add("TargetType");

		evidenceNodeNameList = new ArrayList<String>();
//		evidenceNodeNameList.add("UHRR_Confusion");
//		evidenceNodeNameList.add("ModulationFrequency");
		evidenceNodeNameList.add("CenterFrequency");
//		evidenceNodeNameList.add("PRI");
//		evidenceNodeNameList.add("PRF");

		run(targetNodeNameList, evidenceNodeNameList, .2228f, netFileName);
	
		
		evidenceNodeNameList = new ArrayList<String>();
//		evidenceNodeNameList.add("UHRR_Confusion");
//		evidenceNodeNameList.add("ModulationFrequency");
//		evidenceNodeNameList.add("CenterFrequency");
//		evidenceNodeNameList.add("PRI");
		evidenceNodeNameList.add("PRF");
		
		run(targetNodeNameList, evidenceNodeNameList, .2373f, netFileName);
		
		
		evidenceNodeNameList = new ArrayList<String>();
//		evidenceNodeNameList.add("UHRR_Confusion");
//		evidenceNodeNameList.add("ModulationFrequency");
//		evidenceNodeNameList.add("CenterFrequency");
		evidenceNodeNameList.add("PRI");
//		evidenceNodeNameList.add("PRF");
		
		run(targetNodeNameList, evidenceNodeNameList, .2382f, netFileName);

		
		evidenceNodeNameList = new ArrayList<String>();
//		evidenceNodeNameList.add("UHRR_Confusion");
		evidenceNodeNameList.add("ModulationFrequency");
//		evidenceNodeNameList.add("CenterFrequency");
//		evidenceNodeNameList.add("PRI");
//		evidenceNodeNameList.add("PRF");
		
		run(targetNodeNameList, evidenceNodeNameList, .2867f, netFileName);
		
		
		evidenceNodeNameList = new ArrayList<String>();
		evidenceNodeNameList.add("UHRR_Confusion");
//		evidenceNodeNameList.add("ModulationFrequency");
//		evidenceNodeNameList.add("CenterFrequency");
//		evidenceNodeNameList.add("PRI");
//		evidenceNodeNameList.add("PRF");
		
		run(targetNodeNameList, evidenceNodeNameList, .6102f, netFileName);
		
		
		evidenceNodeNameList = new ArrayList<String>();
//		evidenceNodeNameList.add("UHRR_Confusion");
//		evidenceNodeNameList.add("ModulationFrequency");
		evidenceNodeNameList.add("CenterFrequency");
		evidenceNodeNameList.add("PRI");
//		evidenceNodeNameList.add("PRF");
		
		run(targetNodeNameList, evidenceNodeNameList, .2785f, netFileName);
		
		
		evidenceNodeNameList = new ArrayList<String>();
//		evidenceNodeNameList.add("UHRR_Confusion");
//		evidenceNodeNameList.add("ModulationFrequency");
		evidenceNodeNameList.add("CenterFrequency");
//		evidenceNodeNameList.add("PRI");
		evidenceNodeNameList.add("PRF");
		
		run(targetNodeNameList, evidenceNodeNameList, .2781f, netFileName);
		
		
		evidenceNodeNameList = new ArrayList<String>();
//		evidenceNodeNameList.add("UHRR_Confusion");
//		evidenceNodeNameList.add("ModulationFrequency");
//		evidenceNodeNameList.add("CenterFrequency");
		evidenceNodeNameList.add("PRI");
		evidenceNodeNameList.add("PRF");
		
		run(targetNodeNameList, evidenceNodeNameList, .2891f, netFileName);
		
		
		evidenceNodeNameList = new ArrayList<String>();
		evidenceNodeNameList.add("UHRR_Confusion");
		evidenceNodeNameList.add("ModulationFrequency");
//		evidenceNodeNameList.add("CenterFrequency");
//		evidenceNodeNameList.add("PRI");
//		evidenceNodeNameList.add("PRF");
		
		run(targetNodeNameList, evidenceNodeNameList, .6548f, netFileName);
		
		
		evidenceNodeNameList = new ArrayList<String>();
//		evidenceNodeNameList.add("UHRR_Confusion");
//		evidenceNodeNameList.add("ModulationFrequency");
		evidenceNodeNameList.add("CenterFrequency");
		evidenceNodeNameList.add("PRI");
		evidenceNodeNameList.add("PRF");
		
		run(targetNodeNameList, evidenceNodeNameList, .3172f, netFileName);
		
		
		evidenceNodeNameList = new ArrayList<String>();
//		evidenceNodeNameList.add("UHRR_Confusion");
		evidenceNodeNameList.add("ModulationFrequency");
		evidenceNodeNameList.add("CenterFrequency");
		evidenceNodeNameList.add("PRI");
//		evidenceNodeNameList.add("PRF");
		
		run(targetNodeNameList, evidenceNodeNameList, .3876f, netFileName);
		
		
		evidenceNodeNameList = new ArrayList<String>();
//		evidenceNodeNameList.add("UHRR_Confusion");
		evidenceNodeNameList.add("ModulationFrequency");
		evidenceNodeNameList.add("CenterFrequency");
//		evidenceNodeNameList.add("PRI");
		evidenceNodeNameList.add("PRF");
		
		run(targetNodeNameList, evidenceNodeNameList, .3872f, netFileName);
		
		
		evidenceNodeNameList = new ArrayList<String>();
//		evidenceNodeNameList.add("UHRR_Confusion");
		evidenceNodeNameList.add("ModulationFrequency");
//		evidenceNodeNameList.add("CenterFrequency");
		evidenceNodeNameList.add("PRI");
		evidenceNodeNameList.add("PRF");
		
		run(targetNodeNameList, evidenceNodeNameList, .3967f, netFileName);
		
		
		evidenceNodeNameList = new ArrayList<String>();
		evidenceNodeNameList.add("UHRR_Confusion");
//		evidenceNodeNameList.add("ModulationFrequency");
		evidenceNodeNameList.add("CenterFrequency");
		evidenceNodeNameList.add("PRI");
//		evidenceNodeNameList.add("PRF");
		
		run(targetNodeNameList, evidenceNodeNameList, .6561f, netFileName);
		
		
		evidenceNodeNameList = new ArrayList<String>();
		evidenceNodeNameList.add("UHRR_Confusion");
//		evidenceNodeNameList.add("ModulationFrequency");
		evidenceNodeNameList.add("CenterFrequency");
//		evidenceNodeNameList.add("PRI");
		evidenceNodeNameList.add("PRF");
		
		run(targetNodeNameList, evidenceNodeNameList, .6558f, netFileName);
		
		
		evidenceNodeNameList = new ArrayList<String>();
		evidenceNodeNameList.add("UHRR_Confusion");
//		evidenceNodeNameList.add("ModulationFrequency");
//		evidenceNodeNameList.add("CenterFrequency");
		evidenceNodeNameList.add("PRI");
		evidenceNodeNameList.add("PRF");
		
		run(targetNodeNameList, evidenceNodeNameList, .6616f, netFileName);
		
		
		evidenceNodeNameList = new ArrayList<String>();
//		evidenceNodeNameList.add("UHRR_Confusion");
		evidenceNodeNameList.add("ModulationFrequency");
		evidenceNodeNameList.add("CenterFrequency");
		evidenceNodeNameList.add("PRI");
		evidenceNodeNameList.add("PRF");
		
		run(targetNodeNameList, evidenceNodeNameList, .4214f, netFileName);
		
		
		evidenceNodeNameList = new ArrayList<String>();
		evidenceNodeNameList.add("UHRR_Confusion");
//		evidenceNodeNameList.add("ModulationFrequency");
		evidenceNodeNameList.add("CenterFrequency");
		evidenceNodeNameList.add("PRI");
		evidenceNodeNameList.add("PRF");
		
		run(targetNodeNameList, evidenceNodeNameList, .6755f, netFileName);
		
		
		evidenceNodeNameList = new ArrayList<String>();
		evidenceNodeNameList.add("UHRR_Confusion");
		evidenceNodeNameList.add("ModulationFrequency");
		evidenceNodeNameList.add("CenterFrequency");
		evidenceNodeNameList.add("PRI");
//		evidenceNodeNameList.add("PRF");
		
		run(targetNodeNameList, evidenceNodeNameList, .6932f, netFileName);
		
		
		evidenceNodeNameList = new ArrayList<String>();
		evidenceNodeNameList.add("UHRR_Confusion");
		evidenceNodeNameList.add("ModulationFrequency");
		evidenceNodeNameList.add("CenterFrequency");
//		evidenceNodeNameList.add("PRI");
		evidenceNodeNameList.add("PRF");
		
		run(targetNodeNameList, evidenceNodeNameList, .6931f, netFileName);
		
		
		evidenceNodeNameList = new ArrayList<String>();
		evidenceNodeNameList.add("UHRR_Confusion");
		evidenceNodeNameList.add("ModulationFrequency");
//		evidenceNodeNameList.add("CenterFrequency");
		evidenceNodeNameList.add("PRI");
		evidenceNodeNameList.add("PRF");
		
		run(targetNodeNameList, evidenceNodeNameList, .6978f, netFileName);
		
	
		evidenceNodeNameList = new ArrayList<String>();
		evidenceNodeNameList.add("UHRR_Confusion");
		evidenceNodeNameList.add("ModulationFrequency");
		evidenceNodeNameList.add("CenterFrequency");
		evidenceNodeNameList.add("PRI");
		evidenceNodeNameList.add("PRF");
		
		run(targetNodeNameList, evidenceNodeNameList, .7095f, netFileName);
	}
	
	public static void runWrongAirIDModel() throws Exception {
		
		String netFileName;
		
		List<String> targetNodeNameList = new ArrayList<String>();
		targetNodeNameList.add("TargetType");
		
		List<String> evidenceNodeNameList = new ArrayList<String>();
		evidenceNodeNameList.add("UHRR_Confusion");
		evidenceNodeNameList.add("ModulationFrequency");
		evidenceNodeNameList.add("CenterFrequency");
		evidenceNodeNameList.add("PRI");
		evidenceNodeNameList.add("PRF");
		
		ExactEvaluation evaluationExact = new ExactEvaluation();
		
		// Model with 5% error
		netFileName = "src/test/resources/testCases/evaluation/AirID_05%.xml";
		evaluationExact.evaluate(netFileName, targetNodeNameList,
				evidenceNodeNameList, true);
		
		run(targetNodeNameList, evidenceNodeNameList, evaluationExact.getEvidenceSetPCC(), netFileName);
		
		// Model with 10% error
		netFileName = "src/test/resources/testCases/evaluation/AirID_10%.xml";
		evaluationExact.evaluate(netFileName, targetNodeNameList,
				evidenceNodeNameList, true);
		
		run(targetNodeNameList, evidenceNodeNameList, evaluationExact.getEvidenceSetPCC(), netFileName);
		
		// Model with 20% error
		netFileName = "src/test/resources/testCases/evaluation/AirID_20%.xml";
		evaluationExact.evaluate(netFileName, targetNodeNameList,
				evidenceNodeNameList, true);
		
		run(targetNodeNameList, evidenceNodeNameList, evaluationExact.getEvidenceSetPCC(), netFileName);
	}
	
	public static void main(String[] args) throws Exception {
		
		Debug.setDebug(true);
		Debug.print("\n%80s , %20s , %20s , %20s , %20s , %20s , %20s , %20s", "Evidences", "States Size", "Exact Pcc", "Sample Size", "Sample / States", "Mean", "Variance", "Time");
		Debug.setDebug(false);
		
		runWrongAirIDModel();
		
	}
	
	public static void run(List<String> targetNodeNameList, List<String> evidenceNodeNameList, float exactPcc, String netFileName) throws Exception {
		
		EvaluationAnalysis an = new EvaluationAnalysis();
		
		EvaluationAnalysisOption option = EvaluationAnalysisOption.ERROR;
		
		float error = .0025f;
		
		switch (option) {
		case EXACT:
			an.computeExactSampleSize(netFileName, targetNodeNameList, evidenceNodeNameList);
			break;
	
		case SAMPLE_SIZE:
			an.computeSampleSizeByErrorVariance(netFileName, targetNodeNameList, evidenceNodeNameList, error, exactPcc);
			break;
		
		case ERROR:
			an.computeErrorBySampleSize(netFileName, targetNodeNameList, evidenceNodeNameList, exactPcc);
			break;
		}
	}

}
