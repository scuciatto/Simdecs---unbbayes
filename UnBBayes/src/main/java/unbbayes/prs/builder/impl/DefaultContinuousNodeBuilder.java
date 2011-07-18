/**
 * 
 */
package unbbayes.prs.builder.impl;

import unbbayes.prs.Node;
import unbbayes.prs.builder.INodeBuilder;
import unbbayes.prs.builder.IProbabilisticNetworkBuilder;
import unbbayes.prs.hybridbn.ContinuousNode;

/**
 * @author Shou Matsumoto
 * @deprecated Continuous node is no longer supported in UnBBayes core. It has 
 * now been replaced by the CPS plugin available at http://sourceforge.net/projects/prognos/.
 */
public class DefaultContinuousNodeBuilder implements INodeBuilder {

	/**
	 * Default implementation of builder for continuous nodes
	 * 
	 * @see IProbabilisticNetworkBuilder
	 * @see INodeBuilder
	 */
	protected DefaultContinuousNodeBuilder() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Construction method for 
	 * default implementation of builder for continuous nodes
	 * 
	 * @see IProbabilisticNetworkBuilder
	 * @see INodeBuilder
	 */
	public static DefaultContinuousNodeBuilder newInstance(){
		return new DefaultContinuousNodeBuilder();
	}

	/* (non-Javadoc)
	 * @see unbbayes.io.builder.INodeBuilder#buildNode()
	 */
	public Node buildNode() {
		return new ContinuousNode();
	}

	/*
	 * (non-Javadoc)
	 * @see unbbayes.prs.builder.INodeBuilder#getNodeClass()
	 */
	public Class getNodeClass() {
		return ContinuousNode.class;
	}

}
