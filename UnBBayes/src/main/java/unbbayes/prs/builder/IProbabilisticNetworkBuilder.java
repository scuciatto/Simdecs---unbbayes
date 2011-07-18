/**
 * 
 */
package unbbayes.prs.builder;

import unbbayes.prs.Network;
import unbbayes.prs.bn.ProbabilisticNetwork;

/**
 * 
 * A builder for networks, used by the I/O classes in order to generate
 * a new Network.
 * This is useful in order to re-use I/O classes to load specific subclasses of Nodes and Networks,
 * instead of hard-coding Node/Network types.
 * @author Shou Matsumoto
 *
 */
public interface IProbabilisticNetworkBuilder {
	/**
	 * Obtains the NodeBuilder to generate subclasses of ProbabilisticNode
	 * @return a Node builder to be used
	 */
	public INodeBuilder getProbabilisticNodeBuilder();
	
	/**
	 * Obtains the NodeBuilder to generate subclasses of DecisionNode
	 * @return a Node builder to be used
	 */
	public INodeBuilder getDecisionNodeBuilder();
	
	/**
	 * Obtains the NodeBuilder to generate subclasses of UtilityNode
	 * @return a Node builder to be used
	 */
	public INodeBuilder getUtilityNodeBuilder();
	
	/**
	 * Obtains the NodeBuilder to generate subclasses of ContinuousNode
	 * @return a Node builder to be used
	 * @deprecated Continuous node is no longer supported in UnBBayes core. It has 
	 * now been replaced by the CPS plugin available at http://sourceforge.net/projects/prognos/.
	 */
	public INodeBuilder getContinuousNodeBuilder();
	
	/**
	 * Setter for builder of ProbabilisticNode
	 * @param builder
	 */
	public void setProbabilisticNodeBuilder(INodeBuilder builder);
	
	/**
	 * Setter for builder of DecisionNode
	 * @param builder
	 */
	public void setDecisionNodeBuilder(INodeBuilder builder);
	
	/**
	 * Setter for builder of UtilityNode
	 * @param builder
	 */
	public void setUtilityNodeBuilder(INodeBuilder builder);
	
	/**
	 * Setter for builder of ContinuousNode
	 * @param builder
	 * @deprecated Continuous node is no longer supported in UnBBayes core. It has 
	 * now been replaced by the CPS plugin available at http://sourceforge.net/projects/prognos/.
	 */
	public void setContinuousNodeBuilder(INodeBuilder builder);
	
	/**
	 * Generates a new instance of network
	 * @param name: the name/id of network
	 * @return a new instance of network
	 */
	public ProbabilisticNetwork buildNetwork(String name);
}
