/**
 * 
 */
package unbbayes.util.extension;

/**
 * A factory to create a instance of UnBBayesModule.
 * @author Shou Matsumoto
 *
 */
public interface UnBBayesModuleBuilder {

	/**
	 * Obtains a new instance of UnBBayesModule
	 * @return
	 */
	public UnBBayesModule buildUnBBayesModule();
	
	/**
	 * A name for the generated module's instance. Usually, we can use it for
	 * network's name.
	 * @param name
	 */
	public void setName(String name);
	
	/**
	 *  A name for the generated module's instance. Usually, we can use it for
	 * network's name.
	 * @return
	 */
	public String getName();
	
}
