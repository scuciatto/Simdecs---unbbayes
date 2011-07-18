/**
 * 
 */
package unbbayes.gui.option;

import unbbayes.prs.hybridbn.GaussianMixture;
import unbbayes.util.extension.bn.inference.IInferenceAlgorithm;
import unbbayes.util.extension.bn.inference.InferenceAlgorithmOptionPanel;

/**
 * Option panel for gaussian mixture algorithm.
 * Gaussian mixture does not require paramater initialization, so
 * this panel is a empty panel.
 * @author Shou Matsumoto
 * @deprecated Continuous node is no longer supported in UnBBayes core. It has 
 * now been replaced by the CPS plugin available at http://sourceforge.net/projects/prognos/.
 */
public class GaussianMixtureOptionPanel extends InferenceAlgorithmOptionPanel {
	
	private static final long serialVersionUID = -5184529487472642979L;
	private IInferenceAlgorithm inferenceAlgorithm;

	/**
	 * Default constructor for plugin support.
	 * Initializes the value of {@link #getInferenceAlgorithm()}
	 * to {@link GaussianMixture}
	 */
	public GaussianMixtureOptionPanel() {
		super();
		this.setInferenceAlgorithm(new GaussianMixture());
	}

	/* (non-Javadoc)
	 * @see unbbayes.util.extension.bn.inference.InferenceAlgorithmOptionPanel#getInferenceAlgorithm()
	 */
	public IInferenceAlgorithm getInferenceAlgorithm() {
		return this.inferenceAlgorithm;
	}

	/*
	 * (non-Javadoc)
	 * @see unbbayes.util.extension.bn.inference.InferenceAlgorithmOptionPanel#setInferenceAlgorithm(unbbayes.util.extension.bn.inference.IInferenceAlgorithm)
	 */
	public void setInferenceAlgorithm(IInferenceAlgorithm inferenceAlgorithm) {
		this.inferenceAlgorithm = inferenceAlgorithm;
	}

	/*
	 * (non-Javadoc)
	 * @see unbbayes.util.extension.bn.inference.InferenceAlgorithmOptionPanel#commitChanges()
	 */
	public void commitChanges() {
		// no change is going to be commited
	}

	/*
	 * (non-Javadoc)
	 * @see unbbayes.util.extension.bn.inference.InferenceAlgorithmOptionPanel#revertChanges()
	 */
	public void revertChanges() {
		// no changes are done
	}

}
