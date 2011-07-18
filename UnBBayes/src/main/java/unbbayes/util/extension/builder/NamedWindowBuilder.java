/**
 * 
 */
package unbbayes.util.extension.builder;

import unbbayes.util.extension.UnBBayesModuleBuilder;

/**
 * Builder for windows having a name
 * @author Shou Matsumoto
 *
 */
public abstract class NamedWindowBuilder implements UnBBayesModuleBuilder {

	private String name = "Default name";

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}
