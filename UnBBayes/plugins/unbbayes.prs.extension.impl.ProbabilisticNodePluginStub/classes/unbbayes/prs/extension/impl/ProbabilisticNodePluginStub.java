package unbbayes.prs.extension.impl;

import unbbayes.prs.Node;
import unbbayes.prs.bn.PotentialTable;
import unbbayes.prs.bn.ProbabilisticNode;
import unbbayes.prs.extension.IPluginNode;
/**
 * This is just a stub to test plugin nodes.
 * This is a boolean random variable
 * @author Shou Matsumoto
 *
 */
public class ProbabilisticNodePluginStub extends ProbabilisticNode implements
		IPluginNode {

	public ProbabilisticNodePluginStub() {
		super();
		this.appendState("true");
		this.appendState("false");
		PotentialTable table = this.getProbabilityFunction();
		table.addVariable(this);
		table.setValue(0, .5f);
		table.setValue(1, .5f);
	}
	
	/*
	 * (non-Javadoc)
	 * @see unbbayes.prs.extension.IPluginNode#getNode()
	 */
	public Node getNode() {
		return this;
	}

}
