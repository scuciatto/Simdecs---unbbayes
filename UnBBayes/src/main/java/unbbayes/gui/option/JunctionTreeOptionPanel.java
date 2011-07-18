/**
 * 
 */
package unbbayes.gui.option;

import unbbayes.prs.bn.JunctionTreeAlgorithm;
import unbbayes.util.extension.bn.inference.IInferenceAlgorithm;
import unbbayes.util.extension.bn.inference.InferenceAlgorithmOptionPanel;

/**
 * @author Shou Matsumoto
 *
 */
public class JunctionTreeOptionPanel extends InferenceAlgorithmOptionPanel {

	private static final long serialVersionUID = 7734979988420335938L;
	
	private IInferenceAlgorithm inferenceAlgorithm;
	
	public JunctionTreeOptionPanel() {
		super();
		this.setInferenceAlgorithm(new JunctionTreeAlgorithm());
	}

	/* (non-Javadoc)
	 * @see unbbayes.util.extension.bn.inference.InferenceAlgorithmOptionPanel#commitChanges()
	 */
	public void commitChanges() {
		// nothing to change
	}

	/* (non-Javadoc)
	 * @see unbbayes.util.extension.bn.inference.InferenceAlgorithmOptionPanel#getInferenceAlgorithm()
	 */
	public IInferenceAlgorithm getInferenceAlgorithm() {
		return this.inferenceAlgorithm;
	}

	/**
	 * @param inferenceAlgorithm the inferenceAlgorithm to set
	 */
	public void setInferenceAlgorithm(IInferenceAlgorithm inferenceAlgorithm) {
		this.inferenceAlgorithm = inferenceAlgorithm;
	}

	/*
	 * (non-Javadoc)
	 * @see unbbayes.util.extension.bn.inference.InferenceAlgorithmOptionPanel#revertChanges()
	 */
	public void revertChanges() {
		// nothing to change
	}

}
