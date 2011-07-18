/**
 * 
 */
package unbbayes.io.extension.jpf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EventObject;

import org.java.plugin.registry.Extension;
import org.java.plugin.registry.ExtensionPoint;
import org.java.plugin.registry.PluginDescriptor;
import org.java.plugin.registry.Extension.Parameter;

import unbbayes.io.BaseIO;
import unbbayes.io.DneIO;
import unbbayes.io.FileExtensionIODelegator;
import unbbayes.io.NetIO;
import unbbayes.io.XMLBIFIO;
import unbbayes.util.extension.manager.UnBBayesPluginContextHolder;

/**
 * This is an extension of {@link FileExtensionIODelegator}
 * which uses JPF in order to load IO classes as plugins.
 * @author Shou Matsumoto
 *
 */
public class PluginAwareFileExtensionIODelegator extends
		FileExtensionIODelegator {

	/**
	 * This is the ID from where this class will going to find the declared extension points.
	 */
	private String corePluginID;
	
	/** 
	 * The default value of the extension point ID expected by the plugin manager 
	 * in order to find plugins for PN's IO routines.
	 */
	private String extensionPointID = "PNIO";
	
	/** The default name of the "class" parameter of PNIO extension point */
	private String extensionPointClassParam = "class";
	
	/** The default name of the "name" parameter of PNIO extension point */
	private String extensionPointNameParam = "name";
	
	private UnBBayesPluginContextHolder unbbayesPluginContextHolder = UnBBayesPluginContextHolder.newInstance();
	
	/**
	 * Default constructor is public for plugin support.
	 * If you want to use this directly as ordinal java class, use {@link #newInstance()} instead.
	 * 
	 * Initializes the {@link #getDelegators()} using the following IO classes:
	 * 		- {@link NetIO};
	 * 		- {@link XMLBIFIO};
	 * 		- {@link DneIO};
	 * 		- contents from {@link #loadIOAsPlugins(getExtensionPointID())};
	 * 
	 * @deprecated use {@link #newInstance()} instead
	 */
	public PluginAwareFileExtensionIODelegator() {
		super();
		
		// initializes the default core plugin ID (where we are going to look for extension points)
		this.setCorePluginID(this.getUnbbayesPluginContextHolder().getPluginCoreID());
		
		// adding a listener, so that reloading plugins would load new I/O plugins into this object
		UnBBayesPluginContextHolder.newInstance().addListener(new UnBBayesPluginContextHolder.OnReloadActionListener() {
			public void onReload(EventObject eventObject) {
				try {
					reloadPlugins();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Constructor method.
	 * Initializes the {@link #getDelegators()} using the following IO classes:
	 * 		- contents from {@link #loadIOAsPlugins(getExtensionPointID())};
	 * @return a new instance of PluginAwareFileExtensionIODelegator.
	 */
	public static PluginAwareFileExtensionIODelegator newInstance() {
		return PluginAwareFileExtensionIODelegator.newInstance(true);
	}
	
	/**
	 * Constructor method.
	 * @param if true, it loads plugins. If false, it does not load plugins.
	 * @return a new instance of PluginAwareFileExtensionIODelegator.
	 */
	public static PluginAwareFileExtensionIODelegator newInstance(boolean loadPlugins) {
		PluginAwareFileExtensionIODelegator ret = new PluginAwareFileExtensionIODelegator();
		ret.setDelegators(new ArrayList<BaseIO>());
		// now, the below 3 I/O classes are also loaded as plugins
//		ret.getDelegators().add(new NetIO());
//		ret.getDelegators().add(new XMLBIFIO());
//		ret.getDelegators().add(new DneIO());
		if (loadPlugins) {
			ret.getDelegators().addAll(ret.loadIOAsPlugins());
		}
		return ret;
	}
	
	/**
	 * Reloads the plugins.
	 */
	public void reloadPlugins() {
		this.setDelegators(new ArrayList<BaseIO>());
		this.getDelegators().addAll(this.loadIOAsPlugins());
	}
	
	/**
	 * Obtains IO classes using plugins loaded from plugin folder, using {@link #getExtensionPointID()}
	 * as extension point ID (which is usually "PNIO").
	 * 		  (see plugin.xml with ID unbbayes.util.extension.core) for declaration.
	 * The plugins are loaded using {@link UnBBayesPluginContextHolder#getPluginManager()}
	 * @return a collection of BaseIO instances loaded as plugins.
	 */
	protected Collection<BaseIO> loadIOAsPlugins() {
		
		Collection<BaseIO> ret = new ArrayList<BaseIO>();
		
		try {
			// loads the "core" plugin, which is a stub that we use to declare extension points for core
		    PluginDescriptor core = this.getUnbbayesPluginContextHolder().getPluginManager().getRegistry().getPluginDescriptor(
		    		this.getCorePluginID()
		    		);
	        
		    // load the IO extension point for PN.
		    ExtensionPoint point = this.getUnbbayesPluginContextHolder().getPluginManager().getRegistry().getExtensionPoint(
		    			core.getId(), 
		    			this.getExtensionPointID()
		    		);

			// initializes the extension point and loads IO classes
		    for (Extension ext : point.getConnectedExtensions()) {
		    	try {
		    		PluginDescriptor descr = ext.getDeclaringPluginDescriptor();
		    		
			    	this.getUnbbayesPluginContextHolder().getPluginManager().activatePlugin(descr.getId());
					
					// extracting parameters
					Parameter classParam = ext.getParameter(this.getExtensionPointClassParam());
					Parameter nameParam = ext.getParameter(this.getExtensionPointNameParam());
					
					// extracting plugin class 
					ClassLoader classLoader = this.getUnbbayesPluginContextHolder().getPluginManager().getPluginClassLoader(descr);
		            Class pluginCls = null;	// class for the plugin or its builder (UnBBayesModuleBuilder)
		            pluginCls = classLoader.loadClass(classParam.valueAsString());
					
		            BaseIO pluginIOObject = (BaseIO)pluginCls.newInstance();
		            pluginIOObject.setName(nameParam.valueAsString());
		            ret.add(pluginIOObject);
		            
				} catch (Throwable e) {
					// it is OK to ignore a plugin failure, since it is not fatal.
					e.printStackTrace();
					continue;
				}
							
			}
		    
		} catch (Throwable e) {
			e.printStackTrace();
		} 
		
		return ret;
	}

	/**
	 * The default value of the extension point ID expected by the plugin manager 
	 * in order to find plugins for PN's IO routines.
	 * @return the extensionPointID
	 */
	public String getExtensionPointID() {
		return extensionPointID;
	}

	/**
	 * The default value of the extension point ID expected by the plugin manager 
	 * in order to find plugins for PN's IO routines.
	 * @param extensionPointID the extensionPointID to set
	 */
	public void setExtensionPointID(String extensionPointID) {
		this.extensionPointID = extensionPointID;
	}

	/**
	 * The default name of the "class" parameter of PNIO extension point
	 * @return the extensionPointClassParam
	 */
	public String getExtensionPointClassParam() {
		return extensionPointClassParam;
	}

	/**
	 * The default name of the "class" parameter of PNIO extension point
	 * @param extensionPointClassParam the extensionPointClassParam to set
	 */
	public void setExtensionPointClassParam(String extensionPointClassParam) {
		this.extensionPointClassParam = extensionPointClassParam;
	}

	/**
	 * @return the unbbayesPluginContextHolder
	 */
	public UnBBayesPluginContextHolder getUnbbayesPluginContextHolder() {
		return unbbayesPluginContextHolder;
	}

	/**
	 * @param unbbayesPluginContextHolder the unbbayesPluginContextHolder to set
	 */
	public void setUnbbayesPluginContextHolder(
			UnBBayesPluginContextHolder unbbayesPluginContextHolder) {
		this.unbbayesPluginContextHolder = unbbayesPluginContextHolder;
	}

	/**
	 * @return the extensionPointNameParam
	 */
	public String getExtensionPointNameParam() {
		return extensionPointNameParam;
	}

	/**
	 * @param extensionPointNameParam the extensionPointNameParam to set
	 */
	public void setExtensionPointNameParam(String extensionPointNameParam) {
		this.extensionPointNameParam = extensionPointNameParam;
	}

	/**
	 * This is the ID from where this class will going to find the declared extension points.
	 * @return the corePluginID
	 */
	public String getCorePluginID() {
		return corePluginID;
	}

	/**
	 * This is the ID from where this class will going to find the declared extension points.
	 * @param corePluginID the corePluginID to set
	 */
	public void setCorePluginID(String corePluginID) {
		this.corePluginID = corePluginID;
	}

	
	
}
