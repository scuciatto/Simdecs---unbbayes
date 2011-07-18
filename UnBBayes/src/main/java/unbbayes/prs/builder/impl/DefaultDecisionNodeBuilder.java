/**
 * 
 */
package unbbayes.prs.builder.impl;

import unbbayes.prs.Node;
import unbbayes.prs.builder.INodeBuilder;
import unbbayes.prs.builder.IProbabilisticNetworkBuilder;
import unbbayes.prs.id.DecisionNode;

/**
 * @author Shou Matsumoto
 * @see IProbabilisticNetworkBuilder
 *
 */
public class DefaultDecisionNodeBuilder implements INodeBuilder {

	/**
	 * Default implementation of builder for decision nodes
	 * 
	 * @see IProbabilisticNetworkBuilder
	 * @see INodeBuilder
	 */
	protected DefaultDecisionNodeBuilder() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Construction method for 
	 * default implementation of builder for decision nodes
	 * 
	 * @see IProbabilisticNetworkBuilder
	 * @see INodeBuilder
	 */
	public static DefaultDecisionNodeBuilder newInstance(){
		return new DefaultDecisionNodeBuilder();
	}

	/* (non-Javadoc)
	 * @see unbbayes.io.builder.INodeBuilder#buildNode()
	 */
	public Node buildNode() {
		return new DecisionNode();
	}

	/*
	 * (non-Javadoc)
	 * @see unbbayes.prs.builder.INodeBuilder#getNodeClass()
	 */
	public Class getNodeClass() {
		return DecisionNode.class;
	}

}
