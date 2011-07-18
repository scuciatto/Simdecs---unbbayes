/**
 * 
 */
package unbbayes.util.extension.manager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EventListener;
import java.util.EventObject;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.java.plugin.ObjectFactory;
import org.java.plugin.PluginLifecycleException;
import org.java.plugin.PluginManager;
import org.java.plugin.PluginManager.PluginLocation;
import org.java.plugin.registry.Extension;
import org.java.plugin.registry.ExtensionPoint;
import org.java.plugin.registry.Identity;
import org.java.plugin.registry.PluginDescriptor;
import org.java.plugin.registry.PluginPrerequisite;
import org.java.plugin.standard.StandardPluginLocation;

import unbbayes.io.exception.UBIOException;
import unbbayes.util.ApplicationPropertyHolder;


/**
 * This is a class that holds a virtually static (singleton) instance
 * of plugin manager used by UnBBayes core.
 * This class can be used to access plugin utility.
 * @author Shou Matsumoto
 *
 */
public class UnBBayesPluginContextHolder {
	
	private String pluginsDirectoryName = null;
	private String pluginCoreID = null;
//	static {
//		pluginsDirectoryName = ApplicationPropertyHolder.getProperty().getProperty("unbbayes.util.extension.manager.UnBBayesPluginContextHolder.pluginsDirectoryName");
//		if (pluginsDirectoryName == null) {
//			pluginsDirectoryName = "plugins";
//		}
//		pluginCoreID = ApplicationPropertyHolder.getProperty().getProperty("unbbayes.util.extension.manager.UnBBayesPluginContextHolder.pluginCoreID");
//		if (pluginCoreID == null) {
//			pluginCoreID = "unbbayes.util.extension.core";
//		}
//	}
	
	/** Tells us if the plugin infrastructure is already initialized (published) */
	private boolean initialized = false;
	

	private List<OnReloadActionListener> onReloadListeners = new ArrayList<OnReloadActionListener>();
	
	private PluginManager pluginManager;
	
	/**
     * SingletonHolder is loaded on the first execution of Singleton.getInstance() 
     * or the first access to SingletonHolder.INSTANCE, not before.
     * This is used for creating singleton instances of Plugin context holder
     */
    private static class SingletonHolder { 
    	private static final UnBBayesPluginContextHolder INSTANCE = new UnBBayesPluginContextHolder();
    }
	
	/**
	 * Default constructor is protected in order to help
	 * subclasses.
	 * This constructor initializes the values of some attributes, such as
	 * pluginsDirectoryName, plugin manager or pluginCoreID
	 */
	protected UnBBayesPluginContextHolder() {
		this.setPluginsDirectoryName(ApplicationPropertyHolder.getProperty().getProperty("unbbayes.util.extension.manager.UnBBayesPluginContextHolder.pluginsDirectoryName"));
		if (this.getPluginsDirectoryName() == null) {
			this.setPluginsDirectoryName("plugins");
		}
		this.setPluginCoreID(ApplicationPropertyHolder.getProperty().getProperty("unbbayes.util.extension.manager.UnBBayesPluginContextHolder.pluginCoreID"));
		if (this.getPluginCoreID() == null) {
			this.setPluginCoreID("unbbayes.util.extension.core");
		}
		this.setPluginManager(ObjectFactory.newInstance().createManager());
	}
	
	/**
	 * Obtains a singleton instance of {@link UnBBayesPluginContextHolder}
	 * @return
	 */
	public static UnBBayesPluginContextHolder newInstance() {
		return SingletonHolder.INSTANCE;
	}
	
	/**
	 * Return a singleton instance of plugin manager used
	 * by UnBBayes
	 * @return a singleton instance of plugin manager.
	 */
	public PluginManager getPluginManager() {
		return this.pluginManager;
	}
	
	/**
	 * Loads plugins situated at {@link #getPluginsDirectoryName()} folder
	 * and publish them (make them usable).
	 * @throws IOException
	 */
	public synchronized void publishPlugins() throws IOException {
		// search for files inside plugin directory
		File pluginsDir = new File(getPluginsDirectoryName());
		File[] plugins = pluginsDir.listFiles();

		// publish (load) plugins
		try {
	        Set<PluginLocation> locations = new HashSet<PluginLocation>(plugins.length);
	        for (File file : plugins) {
				try {
					PluginLocation location = StandardPluginLocation.create(file);
					if (location != null) {
						locations.add(location);
					}
				} catch (Throwable e) {
					e.printStackTrace();
					continue;
				}
			}
	        
	        // enable plugin
	        getPluginManager().publishPlugins(locations.toArray(new PluginLocation[locations.size()]));
	    } catch (Throwable e) {
	    	throw new UBIOException(e);
		}
	    
	    // if we published the plugins, they are initialized.
	    initialized = true;
	}
	
//	/**
//	 * Activates all plugins connected to a given extension point.
//	 * Basically, it does {@link #getPluginManager()}.activatePlugin() for
//	 * every connected extensions of a given extension point.
//	 * @param point : extension point to activate.
//	 */
//	public static void activateAllExtensionPoint(ExtensionPoint point) {
//		for (Extension extension : point.getConnectedExtensions()) {
//			PluginDescriptor descr = extension.getDeclaringPluginDescriptor();
//            try {
//				getPluginManager().activatePlugin(descr.getId());
//			} catch (PluginLifecycleException e) {
//				e.printStackTrace();
//				// we could not load this plugin, but we shall continue
//				continue;
//			}
//		}
//	}

	/**
	 * @return the pluginsDirectoryName
	 */
	public String getPluginsDirectoryName() {
		return pluginsDirectoryName;
	}

	/**
	 * @param pluginsDirectoryName the pluginsDirectoryName to set
	 */
	public void setPluginsDirectoryName(String pluginsDirectoryName) {
		this.pluginsDirectoryName = pluginsDirectoryName;
	}
	
	/**
	 * The ID of the core plugin.
	 * @return the pluginCoreID
	 */
	public String getPluginCoreID() {
		return pluginCoreID;
	}

	/**
	 * The ID of the core plugin.
	 * @param pluginCoreID the pluginCoreID to set
	 */
	public void setPluginCoreID(String newPluginCoreID) {
		pluginCoreID = newPluginCoreID;
	}

	/**
	 * Tells us if the plugin infrastructure is already initialized (published).
	 * {@link #publishPlugins()} initializes the plugins.
	 * @return true if the plugins were published. False otherwise.
	 */
	public boolean isInitialized() {
		return initialized;
	}

	/**
	 * Triggers every listeners at {@link #getOnReloadListeners()}.
	 * @param origin : object that originated the notify event.
	 */
	public void notifyReload(Object origin) {
		for (OnReloadActionListener listener : this.getOnReloadListeners()) {
			try{
				listener.onReload(new EventObject(origin));
			}catch (Throwable t) {
				t.printStackTrace();
			}
		}
	}
	
	/**
	 * Adds a new {@link OnReloadActionListener} into this plugin holder.
	 * These listeners will be trigged by {@link #notifyReload(Object)}
	 * @param listener
	 * @see #notifyReload(Object)
	 */
	public void addListener(OnReloadActionListener listener) {
		this.getOnReloadListeners().add(listener);
	}

	/**
	 * @return the onReloadListeners
	 */
	public List<OnReloadActionListener> getOnReloadListeners() {
		return onReloadListeners;
	}

	/**
	 * @param onReloadListeners the onReloadListeners to set
	 */
	public void setOnReloadListeners(List<OnReloadActionListener> onReloadListeners) {
		this.onReloadListeners = onReloadListeners;
	}
	
	/**
	 * An interface to represent a listener, containing {@link #onReload(EventObject)}
	 * which shall be called on every plugin reload events (e.g. by pressing
	 * "reload plugins" button).
	 * This is useful if you implement hotplugging, which needs to 
	 * reload something when an event happens.
	 * @author Shou Matsumoto
	 *
	 */
	public interface OnReloadActionListener extends EventListener {
		/**
		 * This method will be called by UnBBayes when a plugin reload
		 * action is called.
		 * @param eventObject
		 */
		public abstract void onReload(EventObject eventObject);
	}

	/**
	 * @param pluginManager the pluginManager to set
	 */
	protected void setPluginManager(PluginManager pluginManager) {
		this.pluginManager = pluginManager;
	}

	/**
	 * This method checks if a given plugin descriptor is a valid plugin.
	 * If not valid, it returns a collection containing all plugin IDs
	 * that caused the descr to fail.
	 * 
	 * @param descr : descriptor of the plugin to perform sanity check.
	 * 
	 * @return non null collection. This is a collection containing all plugin IDs
	 * that caused the descr to fail.
	 */
	public Collection<String> getErroneousRequisiteID(PluginDescriptor descr) {
		// prepare return as non-null value
		Collection<String> ret = new ArrayList<String>();
		
		// checking what plugin dependency was erroneous, if something is wrong
		if (this.getPluginManager().isBadPlugin(descr)) {
			for (PluginPrerequisite requisite : descr.getPrerequisites()) {
				try {
					// TODO avoid this kind of exception driven test
					this.getPluginManager().getRegistry().getPluginDescriptor(requisite.getPluginId());
				} catch (Exception e) {
					ret.add(requisite.getPluginId());
				}
			}
		} else if (!this.getPluginManager().isPluginActivated(descr)) {
			try {
				// if the plugin was not already activated, activate it and try again.
				this.getPluginManager().activatePlugin(descr.getId());
			} catch (PluginLifecycleException e) {
				// if plugin activation was erroneous, that means the plugin itself was erroneous.
				// TODO avoid this kind of exception driven test
				ret.addAll(this.getErroneousRequisiteID(descr));
			}
		}
		
		return ret;
	}
	
	/**
	 * This method obtains a map containing an erroneous plugin ID as a key, mapped
	 * to a set of plugin ID's of the dependencies causing its erroneous state.
	 * You may use {@link #getPluginManager()}'s methods to extract the plugin
	 * descriptor from these IDs.
	 * @return : non-null map from plugin ID to all its erroneous dependencies (also, plugin IDs)
	 * @throws IOException : when a I/O access or plugin publish cannot be done.
	 */
	public Map<String, Set<String>> getErroneousPluginIDDependencyMap() throws IOException {
		// preparing  return
		Map<String, Set<String>> ret = new HashMap<String, Set<String>>();
		
		try {
	        // iterate over all IDs
	        for (PluginDescriptor desc : getPluginManager().getRegistry().getPluginDescriptors()) {
	        	try {
	        		ret.putAll(this.getErroneousPluginIDDependencyMap(desc.getId()));
	        	} catch (Throwable e) {
	    	    	continue;
	    	    } 
			}
	    } catch (Throwable e) {
	    	e.printStackTrace();
	    } 
		
		return ret;
	}
	
	/**
	 * This method obtains a map containing an erroneous plugin ID as a key, mapped
	 * to a set of plugin ID's of the dependencies causing its erroneous state.
	 * You may use {@link #getPluginManager()}'s methods to extract the plugin
	 * descriptor from these IDs.
	 * @param extensionPointPluginID : this is the plugin ID from where this method will look for
	 * connected extension points. If null, the {@link #getPluginCoreID()} will
	 * be used. All extension points connected to this extensionPointPluginID will be checked
	 * @return : non-null map from plugin ID to all its erroneous dependencies (also, plugin IDs)
	 * @throws IOException : when a I/O access or plugin publish cannot be done.
	 */
	public Map<String, Set<String>> getErroneousPluginIDDependencyMap(String extensionPointPluginID) throws IOException {
		
		// preparing return
		Map<String, Set<String>> ret = new HashMap<String, Set<String>>();
		
		// the below code fixes incidents that happens when this method is called before plugin initialization
    	if (!this.isInitialized()) {
    		this.publishPlugins();
    	}
    	
    	// loads the "core" plugin, which is a stub that we use to declare extension points for core
	    PluginDescriptor core;
	    
	    try {
	    	if (extensionPointPluginID == null) {
	    		// no ID was informed. Use the default one
	    		core = this.getPluginManager().getRegistry().getPluginDescriptor(
	    					this.getPluginCoreID()
	    				);
	    	} else {
	    		// core id was informed. Use it to find connected extension points
	    		core = this.getPluginManager().getRegistry().getPluginDescriptor(
	    				extensionPointPluginID
	    				);
	    	}
		} catch (Throwable t) {
			// even the core plugin was erroneous... This may be serious, but let's report it as erroneous
			t.printStackTrace();
			ret.put(this.getPluginCoreID(), new HashSet<String>());	// assume core does not have dependencies
			return ret;
		}
        
	    // iterate over all extension points for core plugin
	    for (ExtensionPoint point : core.getExtensionPoints()) {
	    	try {
	    		for (Extension ext : point.getConnectedExtensions()) {
		    		try {
		    			PluginDescriptor descriptor = ext.getDeclaringPluginDescriptor();
		    			if (!this.getPluginManager().isPluginActivated(descriptor)) {
		    				// if the plugin is not active yet, lets test if it is valid by trying to activate it
		    				// TODO stop using this kind of exception based test
		    				try {
		    					this.getPluginManager().activatePlugin(descriptor.getId());
							} catch (Throwable e) {
								ret.put(descriptor.getId(), new HashSet<String>(this.getErroneousRequisiteID(descriptor)));
							}
		    			} else if (this.getPluginManager().isBadPlugin(descriptor)) {
		    				// if erroneous, fill the return map with the bad requisites
		    				ret.put(descriptor.getId(), new HashSet<String>(this.getErroneousRequisiteID(descriptor)));
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
	    
	    return ret;

	}
}
