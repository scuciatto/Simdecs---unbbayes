/**
 * 
 */
package unbbayes.prs.builder.extension.impl;

import unbbayes.prs.builder.extension.PluginNodeBuilder;
import unbbayes.prs.extension.IPluginNode;

/**
 * This is a plugin node builder which basically uses
 * {@link Class#newInstance()} in order to build a node.
 * It generates a name using a static numeric field
 * @author Shou Matsumoto
 *
 */
public class ClassInstantiationPluginNodeBuilder extends PluginNodeBuilder {

	private static int counter = 0;
	private String namePrefix = "PluginNode";
	
	private Class clazz;
	
	/**
	 * The default constructor must be public for plugin support
	 */
	public ClassInstantiationPluginNodeBuilder() {
		super();
	}
	
	/**
	 * This is equivalent to calling {@link #ClassInstantiationPluginNodeBuilder()}
	 * and then {@link #setNodeClass(Class)}
	 * @param clazz
	 */
	public ClassInstantiationPluginNodeBuilder(Class clazz) {
		this();
		this.setNodeClass(clazz);
	}

	

	/* (non-Javadoc)
	 * @see unbbayes.prs.builder.extension.PluginNodeBuilder#buildPluginNode()
	 */
	public IPluginNode buildPluginNode() throws InstantiationException,
			IllegalAccessException {
		if (this.getNodeClass() == null) {
			throw new IllegalStateException("A node class must be set by calling setNodeClass(Class)");
		}
		if (!IPluginNode.class.isAssignableFrom(this.getNodeClass())) {
			throw new IllegalArgumentException("The class set by setNodeClass(Class) is not a IPluginNode");
		}
		IPluginNode ret = (IPluginNode)this.getNodeClass().newInstance();
		
		ret.setName(this.getNamePrefix() + counter++);
		ret.setDescription(ret.getName());
		
		return ret;
	}

	/**
	 * This is the class (implementing IPluginNode) which
	 * will be instantiated by this builder
	 * @return the clazz
	 */
	public Class getNodeClass() {
		return clazz;
	}

	/**
	 * This is the class (implementing IPluginNode) which
	 * will be instantiated by this builder
	 * @param clazz the clazz to set
	 */
	public void setNodeClass(Class clazz) {
		if (!IPluginNode.class.isAssignableFrom(clazz)) {
			throw new IllegalArgumentException("The class set by setNodeClass(Class) is not a IPluginNode");
		}
		this.clazz = clazz;
	}

	/**
	 * @return the counter
	 */
	public static int getCounter() {
		return counter;
	}

	/**
	 * @param counter the counter to set
	 */
	public static void setCounter(int counter) {
		ClassInstantiationPluginNodeBuilder.counter = counter;
	}

	/**
	 * @return the namePrefix
	 */
	public String getNamePrefix() {
		return namePrefix;
	}

	/**
	 * @param namePrefix the namePrefix to set
	 */
	public void setNamePrefix(String namePrefix) {
		this.namePrefix = namePrefix;
	}

}
