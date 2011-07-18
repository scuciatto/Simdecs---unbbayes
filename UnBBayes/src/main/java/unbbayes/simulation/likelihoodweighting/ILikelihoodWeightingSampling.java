package unbbayes.simulation.likelihoodweighting;

import java.util.Map;

import unbbayes.simulation.montecarlo.sampling.IMonteCarloSampling;

public interface ILikelihoodWeightingSampling extends IMonteCarloSampling {
	
	/**
	 * The probability/weight associated with the ith set of states on the normal/full matrix. 
	 * @return The probability/weight associated with the ith set of states on the normal/full matrix.
	 */
	public float[] getFullStatesSetWeight();
	
	/**
	 * The probability/weight associated with the ith set of states on the compact matrix. 
	 * @return The probability/weight associated with the ith set of states on the compact matrix.
	 */
	public float[] getCompactStatesSetWeight();
	
	/**
	 * The probability/weight associated with a set of states. Where key = linear coord (representing the sates sampled) and 
	 * value = probability/weight associated with the key.
	 * @return The probability/weight associated with a set of states.
	 */
	public Map<Integer,Float> getMapStatesSetWeight();

}
