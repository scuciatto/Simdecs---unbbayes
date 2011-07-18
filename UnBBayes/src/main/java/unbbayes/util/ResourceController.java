package unbbayes.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.EventObject;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.java.plugin.PluginLifecycleException;
import org.java.plugin.registry.Extension;
import org.java.plugin.registry.ExtensionPoint;
import org.java.plugin.registry.PluginDescriptor;

import unbbayes.gui.resources.GuiResources;
import unbbayes.util.extension.manager.UnBBayesPluginContextHolder;

/**
 * This class is used to facilite the use of the 
 * diverses files of resources of the project. It
 * contains methods to get this resources. 
 * 
 * Altered this class in order to use singleton pattern and 
 * make it possible to change the classloader/locales dinamically (e.g. plugins).
 * If any classloader modification is needed, you may extend/implement
 * a new ClassLoader and set it as this class' default classloader
 * (as a example, you may implement a network classloader and set it as
 * this singleton instance's default classloader).
 * The plugin support is implemented by setting the default classloader
 * as the plugin classloader.
 * 
 * @author Laecio 
 * @author Shou Matsumoto
 * 
 * @version 19/12/2009
 */
public class ResourceController {
	
	/** 
	 * Call to ResourceController.newInstance().getBundle(GuiResources.class.getName())
	 */
	public static ResourceBundle RS_GUI = ResourceController.newInstance().getBundle(
			GuiResources.class.getName());
	
	private UnBBayesPluginContextHolder unbbayesPluginContextHolder = UnBBayesPluginContextHolder.newInstance();
	
	
	
	
	
	private ClassLoader defaultClassLoader = this.getClass().getClassLoader();
	private Locale defaultLocale = Locale.getDefault();
	private String extensionPointID = "ResourceBundle";
	
	/**
     * SingletonHolder is loaded on the first execution of Singleton.getInstance() 
     * or the first access to SingletonHolder.INSTANCE, not before.
     * This is used for creating singleton instances of Plugin manager
     */
    private static class SingletonHolder { 
    	private static final ResourceController INSTANCE = new ResourceController();
    }
    
    /**
     * Default constructor is made protected just to make it easy to extend.
     * Usually, you must use #newInstance().
     * This method just uses {@link UnBBayesPluginContextHolder} in order
     * to obtain the plugin classloader and sets it as the default classloader
     * using #loadPluginClassLoader()
     */
    protected ResourceController() {
    	this.setDefaultClassLoader(this.loadPluginClassLoader());
    	
    	// Adding pseudo hot plug functionality.
    	// This is not actually a hot plug, since the already loaded resource files will not be replaced.
    	UnBBayesPluginContextHolder.newInstance().addListener(new UnBBayesPluginContextHolder.OnReloadActionListener() {
			public void onReload(EventObject eventObject) {
				ResourceController.newInstance().setDefaultClassLoader(
						ResourceController.newInstance().loadPluginClassLoader()
					);
			}
    	});
    }
    
    /**
     * Obtains a ClassLoader which loads classes bound by plugins.
     * @return
     */
    protected synchronized ClassLoader loadPluginClassLoader() {
    	try {
    		// the below code fixes incidents that happens when this method is called before plugin initialization
        	if (!this.getUnBBayesPluginContextHolder().isInitialized()) {
        		this.getUnBBayesPluginContextHolder().publishPlugins();
        	}
        	
        	// loads the "core" plugin, which is a stub that we use to declare extension points for core
    	    PluginDescriptor core = this.getUnBBayesPluginContextHolder().getPluginManager().getRegistry().getPluginDescriptor(
    	    		this.getUnBBayesPluginContextHolder().getPluginCoreID()
    	    		);
            
    	    // load the resource extension point for PN.
//    	    ExtensionPoint point = this.getUnBBayesPluginContextHolder().getPluginManager().getRegistry().getExtensionPoint(
//    	    			core.getId(), 
//    	    			this.getExtensionPointID()
//    	    		);
    	    
        	// prepare return
    	    ListClassLoaderDelegator ret = new ListClassLoaderDelegator(new ArrayList<ClassLoader>());

    	    // iterate over all extension points for core plugin
    	    for (ExtensionPoint point : core.getExtensionPoints()) {
    	    	try {
    	    		for (Extension ext : point.getConnectedExtensions()) {
    	    			PluginDescriptor descr = null;
        	    		try {
        	    			descr = ext.getDeclaringPluginDescriptor();
        	    			this.getUnBBayesPluginContextHolder().getPluginManager().activatePlugin(descr.getId());
                			ClassLoader loader = this.getUnBBayesPluginContextHolder().getPluginManager().getPluginClassLoader(descr);
                			if (loader != null) {
                				ret.getListOfLoaders().add(loader);
                			}
    					} catch (Throwable e) {
    						e.printStackTrace();
    						continue;
    					}
                	}
				} catch (Throwable e) {
					e.printStackTrace();
					continue;
				}
			}
    	    // fill ret with default value if no classloader was used
    	    if (ret.getListOfLoaders().isEmpty()) {
    	    	ret.getListOfLoaders().add(this.getClass().getClassLoader());
    	    }
        	return ret;
		} catch (Throwable e) {
			Debug.println(this.getClass(), "Exception loading plugin resources", e);
			return this.getDefaultClassLoader();
		} 
    	
    }
    
    
    /**
     * @return a singleton instance of this class.
     */
    public static ResourceController newInstance() {
    	return SingletonHolder.INSTANCE;
    }

	/**
	 * The default class loader to be used in order to load the resources.
	 * @return the defaultClassLoader
	 */
	public ClassLoader getDefaultClassLoader() {
		return defaultClassLoader;
	}

	/**
	 * The default class loader to be used in order to load the resources.
	 * @param defaultClassLoader the defaultClassLoader to set
	 */
	public void setDefaultClassLoader(ClassLoader defaultClassLoader) {
		this.defaultClassLoader = defaultClassLoader;
	}

	/**
	 * This is the locale to be used in order to load the resource file.
	 * The initial value is Locale.getDefault()
	 * @return the defaultLocale
	 */
	public Locale getDefaultLocale() {
		return defaultLocale;
	}

	/**
	 * This is the locale to be used in order to load the resource file.
	 * The initial value is Locale.getDefault()
	 * @param defaultLocale the defaultLocale to set
	 */
	public void setDefaultLocale(Locale defaultLocale) {
		this.defaultLocale = defaultLocale;
	}
	
	
	/**
	 * Obtains a ResourceBundle using current configuration of locale and classloader.
	 * It calls ResourceBundle.getBundle(baseName, this.getDefaultLocale(), this.getDefaultClassLoader()).
	 * @param baseName : base name of the class to be loaded.
	 * @return a instance of ResourceBundle
	 * @deprecated use {@link #getBundle(String, Locale, ClassLoader)}, because plugins
	 * cannot work correctly using the default parameters.
	 */
	public ResourceBundle getBundle(String baseName) {
		return ResourceBundle.getBundle(baseName, this.getDefaultLocale(), this.getDefaultClassLoader());
	}
	
	/**
	 * Delegator to ResourceBundle.getBundle(baseName, locale, classLoader)
	 * @param baseName
	 * @param locale
	 * @param classLoader
	 * @return
	 */
	public ResourceBundle getBundle(
				String baseName,
				Locale locale,
				ClassLoader classLoader ) {
		return ResourceBundle.getBundle(baseName, locale, classLoader);
	}
	

	/**
	 * ID of the extension point, used by plugin loader to load Resources plugins.
	 * The default value is usually "ResourceBundle"
	 * @return the extensionPointID
	 */
	public String getExtensionPointID() {
		return extensionPointID;
	}

	/**
	 * ID of the extension point, used by plugin loader to load Resources plugins.
	 * The default value is usually "ResourceBundle"
	 * @param extensionPointID the extensionPointID to set
	 */
	public void setExtensionPointID(String extensionPointID) {
		this.extensionPointID = extensionPointID;
	}
	
	/**
	 * A class loader which contains a list of class loader.
	 * It basically delegates method calls into this list, in order.
	 * By default, its public methods acts as if the last element of the
	 * list were this.getClass().getClassLoader().
	 * @author Shou Matsumoto
	 *
	 */
	class ListClassLoaderDelegator extends ClassLoader {
		private List<ClassLoader> listOfLoaders;
		public ListClassLoaderDelegator(List<ClassLoader> listOfLoaders) {
			this.setListOfLoaders(listOfLoaders);
		}
		public List<ClassLoader> getListOfLoaders() {
			return listOfLoaders;
		}
		public void setListOfLoaders(List<ClassLoader> listOfLoaders) {
			this.listOfLoaders = listOfLoaders;
		}
		/* (non-Javadoc)
		 * @see java.lang.ClassLoader#clearAssertionStatus()
		 */
		@Override
		public synchronized void clearAssertionStatus() {
			for (ClassLoader loader : this.getListOfLoaders()) {
				loader.clearAssertionStatus();
			}
			this.getClass().getClassLoader().clearAssertionStatus();
		}
		/* (non-Javadoc)
		 * @see java.lang.ClassLoader#getResource(java.lang.String)
		 */
		@Override
		public URL getResource(String name) {
			for (ClassLoader loader : this.getListOfLoaders()) {
				try {
					URL ret = loader.getResource(name);
					if (ret != null) {
						return ret;
					}
				} catch (Exception e) {
					Debug.println(this.getClass(), "Error at getResource()", e);
					continue;
				}
			}
			return this.getClass().getClassLoader().getResource(name);
		}
		/* (non-Javadoc)
		 * @see java.lang.ClassLoader#getResourceAsStream(java.lang.String)
		 */
		@Override
		public InputStream getResourceAsStream(String name) {
			for (ClassLoader loader : this.getListOfLoaders()) {
				try {
					InputStream ret = loader.getResourceAsStream(name);
					if (ret != null) {
						return ret;
					}
				} catch (Exception e) {
					Debug.println(this.getClass(), "Error at getResourceAsStream", e);
					continue;
				}
			}
			return this.getClass().getClassLoader().getResourceAsStream(name);
		}
		/* (non-Javadoc)
		 * @see java.lang.ClassLoader#getResources(java.lang.String)
		 */
		@Override
		public Enumeration<URL> getResources(String name) throws IOException {
			for (ClassLoader loader : this.getListOfLoaders()) {
				try {
					Enumeration<URL> ret = loader.getResources(name);
					if (ret != null && ret.hasMoreElements()) {
						return ret;
					}
				} catch (Exception e) {
					Debug.println(this.getClass(), "Error at getResources", e);
					continue;
				}
			}
			return this.getClass().getClassLoader().getResources(name);
		}
		/* (non-Javadoc)
		 * @see java.lang.ClassLoader#loadClass(java.lang.String)
		 */
		@Override
		public Class<?> loadClass(String name) throws ClassNotFoundException {
			for (ClassLoader loader : this.getListOfLoaders()) {
				try {
					Class<?>  ret = loader.loadClass(name);
					if (ret != null) {
						return ret;
					}
				} catch (Exception e) {
//					Debug.println(this.getClass(), "Error at loadClass", e);
					continue;
				}
			}
			return this.getClass().getClassLoader().loadClass(name);
		}
		
		/* (non-Javadoc)
		 * @see java.lang.ClassLoader#setClassAssertionStatus(java.lang.String, boolean)
		 */
		@Override
		public synchronized void setClassAssertionStatus(String className,
				boolean enabled) {
			for (ClassLoader loader : this.getListOfLoaders()) {
				loader.setClassAssertionStatus(className, enabled);
			}
			this.getClass().getClassLoader().setClassAssertionStatus(className, enabled);
		}
		/* (non-Javadoc)
		 * @see java.lang.ClassLoader#setDefaultAssertionStatus(boolean)
		 */
		@Override
		public synchronized void setDefaultAssertionStatus(boolean enabled) {
			for (ClassLoader loader : this.getListOfLoaders()) {
				loader.setDefaultAssertionStatus(enabled);
			}
			this.getClass().getClassLoader().setDefaultAssertionStatus(enabled);
		}
		/* (non-Javadoc)
		 * @see java.lang.ClassLoader#setPackageAssertionStatus(java.lang.String, boolean)
		 */
		@Override
		public synchronized void setPackageAssertionStatus(String packageName,
				boolean enabled) {
			for (ClassLoader loader : this.getListOfLoaders()) {
				loader.setPackageAssertionStatus(packageName, enabled);
			}
			this.getClass().getClassLoader().setPackageAssertionStatus(packageName, enabled);
		}
		
	}

	/**
	 * @return the unbbayesPluginContextHolder
	 */
	public UnBBayesPluginContextHolder getUnBBayesPluginContextHolder() {
		return unbbayesPluginContextHolder;
	}

	/**
	 * @param unbbayesPluginContextHolder the unbbayesPluginContextHolder to set
	 */
	public void setUnBBayesPluginContextHolder(
			UnBBayesPluginContextHolder unbbayesPluginContextHolder) {
		this.unbbayesPluginContextHolder = unbbayesPluginContextHolder;
	}
	 
}
