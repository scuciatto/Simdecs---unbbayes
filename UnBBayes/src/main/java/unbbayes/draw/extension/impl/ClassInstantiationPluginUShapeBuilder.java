/**
 * 
 */
package unbbayes.draw.extension.impl;

import unbbayes.draw.extension.IPluginUShape;
import unbbayes.draw.extension.IPluginUShapeBuilder;

/**
 * This is a PluginUSHape builder using {@link Class#newInstance()}
 * @author Shou Matsumoto
 *
 */
public class ClassInstantiationPluginUShapeBuilder implements
		IPluginUShapeBuilder {

	private Class pluginUShapeClass;
	
	/**
	 * default constructor is made public for easy extending and
	 * plugin support.
	 */
	public ClassInstantiationPluginUShapeBuilder() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * This is equivalent to calling {@link #ClassInstantiationPluginUShapeBuilder()}
	 * and then {@link #setPluginUShapeClass(Class)}
	 * @param clazz
	 */
	public ClassInstantiationPluginUShapeBuilder(Class clazz) {
		this();
		this.setPluginUShapeClass(clazz);
	}

	/* (non-Javadoc)
	 * @see unbbayes.draw.extension.IPluginUShapeBuilder#build()
	 */
	public IPluginUShape build() throws IllegalAccessException,
			InstantiationException {
		
		if (this.getPluginUShapeClass() == null) {
			throw new NullPointerException("getPluginUShapeClass must not return null");
		}
		
		if (!IPluginUShape.class.isAssignableFrom(this.getPluginUShapeClass())) {
			throw new IllegalArgumentException("this.getPluginUShapeClass() must return an instance of unbbayes.draw.extension.IPluginUShape");
		}
		
		return (IPluginUShape)this.getPluginUShapeClass().newInstance();
	}

	/**
	 * This is the class in which {@link Class#newInstance()} is going to
	 * be called.
	 * @return the pluginUShapeClass
	 */
	public Class getPluginUShapeClass() {
		return pluginUShapeClass;
	}

	/**
	 * This is the class in which {@link Class#newInstance()} is going to
	 * be called.
	 * @param pluginUShapeClass the pluginUShapeClass to set
	 */
	public void setPluginUShapeClass(Class pluginUShapeClass) {
		if (!IPluginUShape.class.isAssignableFrom(pluginUShapeClass)) {
			throw new IllegalArgumentException("this.getPluginUShapeClass() must return an instance of unbbayes.draw.extension.IPluginUShape");
		}
		this.pluginUShapeClass = pluginUShapeClass;
	}

}
