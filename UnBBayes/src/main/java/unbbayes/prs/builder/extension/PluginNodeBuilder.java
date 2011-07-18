/**
 * 
 */
package unbbayes.prs.builder.extension;

import unbbayes.prs.Node;
import unbbayes.prs.builder.INodeBuilder;
import unbbayes.prs.extension.IPluginNode;

/**
 * This is a node builder for a special purpose: build
 * plugin based nodes.
 * A plugin developer may extend this class or implement
 * {@link INodeBuilder} in order to provide a node builder
 * for UnBBayes.
 * @author Shou Matsumoto
 *
 */
public abstract class PluginNodeBuilder implements INodeBuilder {

	/**
	 * A plugin node builder must have its default constructor public.
	 */
	public PluginNodeBuilder() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * This method is eqivalent to {@link #buildPluginNode().getNode()}
	 */
	public Node buildNode() {
		try {
			return buildPluginNode().getNode();
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Obtains an instance of plugin node
	 * @return
	 */
	public abstract IPluginNode buildPluginNode() 
	throws InstantiationException, IllegalAccessException;

}
