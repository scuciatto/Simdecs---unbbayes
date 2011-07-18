/**
 * 
 */
package unbbayes.util.extension.bn.inference;

import javax.swing.JPanel;

/**
 * JPanel containing some forms that a user 
 * can fill up. By extending this panel, we can initialize some 
 * attributes for inference algorithm.
 * This panel is usually implemented
 * in a way that any change on the fields reflects 
 * directly the attributes of the algorithm.
 * 
 * @author Shou Matsumoto
 *
 */
public abstract class InferenceAlgorithmOptionPanel extends JPanel {
	
	private static final long serialVersionUID = 1377907003286358357L;
	

	/**
	 * Default constructor for plugin support
	 */
	public InferenceAlgorithmOptionPanel() {
		super();
	}

	/**
	 * Obtains the algorithm to run.
	 * @return
	 */
	public abstract IInferenceAlgorithm getInferenceAlgorithm();
	
	/**
	 * Commit the changes of the parameters given by the user.
	 */
	public abstract void commitChanges();
	
	/**
	 * Revert the changes of the parameters given by the user.
	 */
	public abstract void revertChanges();
	
}
