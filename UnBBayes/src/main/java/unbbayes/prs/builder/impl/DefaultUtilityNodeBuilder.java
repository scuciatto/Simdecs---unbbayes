/**
 * 
 */
package unbbayes.prs.builder.impl;

import unbbayes.prs.Node;
import unbbayes.prs.builder.INodeBuilder;
import unbbayes.prs.builder.IProbabilisticNetworkBuilder;
import unbbayes.prs.id.DecisionNode;
import unbbayes.prs.id.UtilityNode;

/**
 * @author Shou Matsumoto
 * @see IProbabilisticNetworkBuilder
 *
 */
public class DefaultUtilityNodeBuilder implements INodeBuilder {

	/**
	 * Default implementation of builder for utility nodes
	 * @see IProbabilisticNetworkBuilder
	 * @see INodeBuilder
	 */
	protected DefaultUtilityNodeBuilder() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Construction method for 
	 * default implementation of builder for utility nodes
	 * @see IProbabilisticNetworkBuilder
	 * @see INodeBuilder
	 */
	public static DefaultUtilityNodeBuilder newInstance() {
		return new DefaultUtilityNodeBuilder();
	}

	/* (non-Javadoc)
	 * @see unbbayes.io.builder.INodeBuilder#buildNode()
	 */
	public Node buildNode() {
		return new UtilityNode();
	}

	/*
	 * (non-Javadoc)
	 * @see unbbayes.prs.builder.INodeBuilder#getNodeClass()
	 */
	public Class getNodeClass() {
		return UtilityNode.class;
	}
	
	

}
