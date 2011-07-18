package unbbayes.util.extension.builder;

import unbbayes.gui.NetworkWindow;
import unbbayes.prs.Network;
import unbbayes.prs.bn.ProbabilisticNetwork;
import unbbayes.util.extension.UnBBayesModule;
import unbbayes.util.extension.UnBBayesModuleBuilder;

/**
 * This class instantiates a Network window (single entity network) using most basic parameters.
 * @author Shou Matsumoto
 *
 */
public class NetworkWindowBuilder extends NamedWindowBuilder {

	/*
	 * (non-Javadoc)
	 * @see unbbayes.util.extension.UnBBayesModuleBuilder#buildUnBBayesModule()
	 */
	public UnBBayesModule buildUnBBayesModule() {
		NetworkWindow ret = new NetworkWindow(new ProbabilisticNetwork(this.getName()));
//		ret.setVisible(false);
		return ret;
	}

}
