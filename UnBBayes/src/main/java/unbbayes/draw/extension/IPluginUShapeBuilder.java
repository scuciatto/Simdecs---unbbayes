/**
 * 
 */
package unbbayes.draw.extension;

/**
 * This is a builder for instances of IPluginShape
 * @author Shou Matsumoto
 *
 */
public interface IPluginUShapeBuilder {

	/***
	 * Obtains an instance of IPluginShape
	 * @return
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public IPluginUShape build() throws IllegalAccessException, InstantiationException;
	
}
