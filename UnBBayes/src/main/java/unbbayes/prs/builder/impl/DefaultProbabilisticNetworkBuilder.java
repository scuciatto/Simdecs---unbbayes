/**
 * 
 */
package unbbayes.prs.builder.impl;

import unbbayes.prs.Network;
import unbbayes.prs.bn.ProbabilisticNetwork;
import unbbayes.prs.builder.INodeBuilder;
import unbbayes.prs.builder.IProbabilisticNetworkBuilder;

/**
 * @author Shou Matsumoto
 * @see IProbabilisticNetworkBuilder
 */
public class DefaultProbabilisticNetworkBuilder implements
		IProbabilisticNetworkBuilder {

	/**
	 * Builder for probabilistic nodes
	 */
	private INodeBuilder probabilisticNodeBuilder = null;
	
	/**
	 * Builder for decision nodes
	 */
	private INodeBuilder decisionNodeBuilder = null;
	
	/**
	 * Builder for utility nodes
	 */
	private INodeBuilder utilityNodeBuilder = null;
	
	/**
	 * Builder for continuous nodes
	 * @deprecated Continuous node is no longer supported in UnBBayes core. It has 
	 * now been replaced by the CPS plugin available at http://sourceforge.net/projects/prognos/.
	 */
	private INodeBuilder continuousNodeBuilder = null;
	
	/**
	 * Default implementation of builder for probabilistic networks
	 * @see IProbabilisticNetworkBuilder
	 * @see INodeBuilder
	 */
	protected DefaultProbabilisticNetworkBuilder() {
		this.probabilisticNodeBuilder = DefaultProbabilisticNodeBuilder.newInstance();
		this.decisionNodeBuilder = DefaultDecisionNodeBuilder.newInstance();
		this.utilityNodeBuilder = DefaultUtilityNodeBuilder.newInstance();	
//		this.continuousNodeBuilder = DefaultContinuousNodeBuilder.newInstance();
	}
	
	/**
	 * Constructor method for DefaultProbabilisticNetworkBuilder.
	 * Sets the node builders to their default implementations
	 * @return a new instance of this builder
	 */
	public static DefaultProbabilisticNetworkBuilder newInstance() {
		return new DefaultProbabilisticNetworkBuilder();
	}
	
	
	/**
	 * Constructor method for DefaultProbabilisticNetworkBuilder,
	 * specifying the node builders to use.
	 * If parameter is set to null, it uses default implementation.
	 * @param probabilisticNodeBuilder: builder for probabilistic nodes
	 * @param decisionNodeBuilder: builder for decision nodes
	 * @param utilityNodeBuilder: builder for utility nodes
	 * @return a new instance of this builder
	 */
	public static DefaultProbabilisticNetworkBuilder newInstance(
					INodeBuilder probabilisticNodeBuilder 
				 ,  INodeBuilder decisionNodeBuilder
				 ,  INodeBuilder utilityNodeBuilder ) {
		DefaultProbabilisticNetworkBuilder ret = new DefaultProbabilisticNetworkBuilder();
		if (decisionNodeBuilder != null) {
			ret.setDecisionNodeBuilder(decisionNodeBuilder);
		}
		if (probabilisticNodeBuilder != null) {
			ret.setProbabilisticNodeBuilder(probabilisticNodeBuilder);
		}
		if (utilityNodeBuilder != null) {
			ret.setUtilityNodeBuilder(utilityNodeBuilder);
		}
		return ret;
	}

	

	/* (non-Javadoc)
	 * @see unbbayes.io.builder.IProbabilisticNetworkBuilder#buildNetwork(java.lang.String)
	 */
	public ProbabilisticNetwork buildNetwork(String name) {
		return new ProbabilisticNetwork(name);
	}

	/* (non-Javadoc)
	 * @see unbbayes.io.builder.IProbabilisticNetworkBuilder#getDecisionNodeBuilder()
	 */
	public INodeBuilder getDecisionNodeBuilder() {
		return this.decisionNodeBuilder;
	}

	/* (non-Javadoc)
	 * @see unbbayes.io.builder.IProbabilisticNetworkBuilder#getProbabilisticNodeBuilder()
	 */
	public INodeBuilder getProbabilisticNodeBuilder() {
		
		return this.probabilisticNodeBuilder;
	}

	/* (non-Javadoc)
	 * @see unbbayes.io.builder.IProbabilisticNetworkBuilder#getUtilityNodeBuilder()
	 */
	public INodeBuilder getUtilityNodeBuilder() {
		return this.utilityNodeBuilder;
	}

	/* (non-Javadoc)
	 * @see unbbayes.io.builder.IProbabilisticNetworkBuilder#setDecisionNodeBuilder(unbbayes.io.builder.INodeBuilder)
	 */
	public void setDecisionNodeBuilder(INodeBuilder builder) {
		this.decisionNodeBuilder = builder;
	}

	/* (non-Javadoc)
	 * @see unbbayes.io.builder.IProbabilisticNetworkBuilder#setProbabilisticNodeBuilder(unbbayes.io.builder.INodeBuilder)
	 */
	public void setProbabilisticNodeBuilder(INodeBuilder builder) {
		this.probabilisticNodeBuilder = builder;
	}

	/* (non-Javadoc)
	 * @see unbbayes.io.builder.IProbabilisticNetworkBuilder#setUtilityNodeBuilder(unbbayes.io.builder.INodeBuilder)
	 */
	public void setUtilityNodeBuilder(INodeBuilder builder) {
		this.utilityNodeBuilder = builder;
	}

	/* (non-Javadoc)
	 * @see unbbayes.io.builder.IProbabilisticNetworkBuilder#getContinuousNodeBuilder()
	 */
	/**
	 * @deprecated Continuous node is no longer supported in UnBBayes core. It has 
	 * now been replaced by the CPS plugin available at http://sourceforge.net/projects/prognos/.
	 */
	public INodeBuilder getContinuousNodeBuilder() {
		return this.continuousNodeBuilder;
	}

	/* (non-Javadoc)
	 * @see unbbayes.io.builder.IProbabilisticNetworkBuilder#setContinuousNodeBuilder(unbbayes.io.builder.INodeBuilder)
	 */
	/**
	 * @deprecated Continuous node is no longer supported in UnBBayes core. It has 
	 * now been replaced by the CPS plugin available at http://sourceforge.net/projects/prognos/.
	 */
	public void setContinuousNodeBuilder(INodeBuilder builder) {
		this.continuousNodeBuilder = builder;
	}
	
	
	

}
