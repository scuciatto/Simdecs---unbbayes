/**
 * 
 */
package unbbayes.util.extension.manager;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.EventObject;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import javax.swing.ImageIcon;

import org.java.plugin.PluginLifecycleException;
import org.java.plugin.registry.Extension;
import org.java.plugin.registry.ExtensionPoint;
import org.java.plugin.registry.PluginDescriptor;
import org.java.plugin.registry.Extension.Parameter;

import unbbayes.gui.table.extension.IProbabilityFunctionPanelBuilder;
import unbbayes.prs.bn.ProbabilisticNode;
import unbbayes.prs.hybridbn.ContinuousNode;
import unbbayes.prs.id.DecisionNode;
import unbbayes.prs.id.UtilityNode;
import unbbayes.util.ApplicationPropertyHolder;
import unbbayes.util.extension.dto.INodeClassDataTransferObject;
import unbbayes.util.extension.dto.impl.NodeDto;

/**
 * This plugin manager manipulates the panels for
 * Conditional Probability Function. This is done virtually
 * by doing the same of {@link CorePluginNodeManager}, but
 * only for pre-existing nodes.
 * @author Shou Matsumoto
 *
 */
public class CoreCPFPluginManager  {

	// this is a map to store the informations associated to nodes and its CPF
	private Map<String, Collection<INodeClassDataTransferObject>> nodeNameToDtoMap = new HashMap<String, Collection<INodeClassDataTransferObject>>();
	
	private UnBBayesPluginContextHolder unbbayesPluginContextHolder = UnBBayesPluginContextHolder.newInstance();
	
	private String mainExtensionPointID;
	
	/** Name of the extension point parameter for node's class (e.g. {@link ProbabilisticNode}, {@link DecisionNode}, {@link UtilityNode}, {@link ContinuousNode})... */
	public static final String PARAMETER_CLASS = "class";
	/** Name of the extension point parameter for the name of this new node type (e.g. "Continuous Node", "Decision Node") */
	public static final String PARAMETER_NAME = "name";
	/** Name of the extension point parameter for this new node type's description (used as tool tip text) */
	public static final String PARAMETER_DESCRIPTION = "description";
	/** Name of the extension point parameter for icons of the split button */
	public static final String PARAMETER_ICON = "icon";
	/** Name of the extension point parameter for a subclass/implementation of {@link IProbabilityFunctionPanelBuilder} @see {@link IProbabilityFunctionPanelBuilder}*/
	public static final String PARAMETER_PANEL_BUILDER = "panelBuilder";
	
	/**
     * SingletonHolder is loaded on the first execution of Singleton.getInstance() 
     * or the first access to SingletonHolder.INSTANCE, not before.
     * This is used for creating singleton instances of compiler
     */
    private static class SingletonHolder { 
    	private static final CoreCPFPluginManager INSTANCE = new CoreCPFPluginManager();
    }
	
	/**
	 * Default constructor is protected, so that it is easier to extend.
	 * Use {@link #newInstance()} in order to create a new instance.
	 */
	protected CoreCPFPluginManager() {
		super();
		// sets the ID of this plugin
		this.setMainExtensionPointID(ApplicationPropertyHolder.getProperty().getProperty("unbbayes.util.extension.manager.CoreCPFPluginManager.cpfExtensionPointID"));
		if (this.getMainExtensionPointID() == null) {
			System.err.println("Error reading CPF extension point from application.properties. Using default.");
			this.setMainExtensionPointID("ProbabilityFunctionPanel");
		}
		// adds a listener to be called when a "reload plugin" event is dispatched
		this.getUnbbayesPluginContextHolder().addListener(new UnBBayesPluginContextHolder.OnReloadActionListener() {
			public void onReload(EventObject eventObject) {
				try {
					reloadPlugin();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
    /**
     * Obtains a singleton instance of {@link CoreCPFPluginManager}
     * @return
     */
	public static CoreCPFPluginManager newInstance() {
		return SingletonHolder.INSTANCE;
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

	/* (non-Javadoc)
	 * @see unbbayes.util.extension.manager.CorePluginNodeManager#getAllLoadedPluginNodes()
	 */
	public Collection<INodeClassDataTransferObject> getAllLoadedPluginNodes() {
		Collection<INodeClassDataTransferObject>  ret = new HashSet<INodeClassDataTransferObject>();
		for (Collection<INodeClassDataTransferObject> collection : this.getNodeNameToDtoMap().values()) {
			ret.addAll(collection);
		}
		return ret;
	}


	/**
	 * Obtains all plugin informations associated with nodeClassName
	 * @param nodeClassName
	 * @return a non-null collection
	 * @see #getNodeNameToDtoMap()
	 */
	public Collection<INodeClassDataTransferObject> getPluginInformation(String nodeClassName) {
		Collection<INodeClassDataTransferObject> ret = this.getNodeNameToDtoMap().get(nodeClassName);
		if (ret == null) {
			return Collections.EMPTY_LIST;
		}
		return ret;
	}

	/**
	 * This is equivalent to {@link #reloadPlugin()}
	 * @throws IOException : thrown by {@link #reloadPlugin()} 
	 * @see #reloadPlugin()
	 */
	public void loadPlugin() throws IOException {
		this.reloadPlugin();
	}


	/**
	 * {@link UnBBayesPluginContextHolder} will be used to load the plugins
	 * and fill the plugin informations that can be obtained by
	 * {@link #getPluginInformation(String)
	 * @throws IOException : when {@link UnBBayesPluginContextHolder#publishPlugins()} fails
	 */
	public void reloadPlugin() throws IOException {
		// reset the registered DTOs
		this.setNodeNameToDtoMap(new HashMap<String, Collection<INodeClassDataTransferObject>>());
		
		// we assume the plugins are already published at UnBBayesFrame#loadPlugins(), but let's just be precautious...
		if (!this.getUnbbayesPluginContextHolder().isInitialized()) {
			// this method may throw a IOException
			this.getUnbbayesPluginContextHolder().publishPlugins();
		}

	    // loads the "core" plugin, which declares general extension points for core 
	    PluginDescriptor core = this.getUnbbayesPluginContextHolder().getPluginManager().getRegistry().getPluginDescriptor(
	    		this.getUnbbayesPluginContextHolder().getPluginCoreID()
	    		);
        
	    // load the extension point for nodes' panel builders.
	    ExtensionPoint point = this.getUnbbayesPluginContextHolder().getPluginManager().getRegistry().getExtensionPoint(
	    			core.getId(), this.getMainExtensionPointID()
	    		);
    	
	    // iterate over the connected extension points
	    for (Iterator<Extension> it = point.getConnectedExtensions().iterator(); it.hasNext();) {
			try {
				Extension ext = it.next();
	            PluginDescriptor descr = ext.getDeclaringPluginDescriptor();
	            
	            this.getUnbbayesPluginContextHolder().getPluginManager().activatePlugin(descr.getId());
				
				// extracting class loader
				ClassLoader classLoader = this.getUnbbayesPluginContextHolder().getPluginManager().getPluginClassLoader(descr);
				
				// extracting parameters
				Parameter classParam = ext.getParameter(PARAMETER_CLASS);
				Parameter descParam = ext.getParameter(PARAMETER_DESCRIPTION);
				Parameter iconParam = ext.getParameter(PARAMETER_ICON);
				Parameter nameParam = ext.getParameter(PARAMETER_NAME);
				Parameter panelParam = ext.getParameter(PARAMETER_PANEL_BUILDER);
				
				
				// extracting class for panel builder
	            Class panelClass = null;	// class for the panel builder
	            panelClass = classLoader.loadClass(panelParam.valueAsString());
				
				// generating panel builder from extracted class
				IProbabilityFunctionPanelBuilder panelBuilder = null;
				panelBuilder = (IProbabilityFunctionPanelBuilder)panelClass.newInstance();

				
				// extracting icon
				ImageIcon icon = null;
				if (iconParam != null) {
					// cursor was provided. Let's use it
					URL url = this.getUnbbayesPluginContextHolder().getPluginManager().getPluginClassLoader(ext.getDeclaringPluginDescriptor()).getResource(iconParam.valueAsString());
					icon = (url != null) ? new ImageIcon(url) : null;
				}
				
				// filling the DTO of plugin 
				INodeClassDataTransferObject dto = NodeDto.newInstance();
				dto.setIcon(icon);
				dto.setProbabilityFunctionPanelBuilder(panelBuilder);
				if (nameParam != null) {
					dto.setName(nameParam.valueAsString());
				}
				if (descParam != null) {
					dto.setDescription(descParam.valueAsString());
				}

				// registering the loaded dto, so that users of this class can call them by using getPluginNodeInformation
				Collection<INodeClassDataTransferObject> collection = this.getNodeNameToDtoMap().get(classParam.valueAsString());
				if (collection == null) {
					collection = new HashSet<INodeClassDataTransferObject>();
					this.getNodeNameToDtoMap().put(classParam.valueAsString(), collection);
				}
				collection.add(dto);
			} catch (Throwable e) {
				e.printStackTrace();
				continue;
			} 
		}
	}


	/**
	 * ID of the plugin where we can find panel declarations
	 * @param mainExtensionPointID
	 */
	protected void setMainExtensionPointID(String mainExtensionPointID) {
		this.mainExtensionPointID = mainExtensionPointID;
	}

	/**
	 * this is a map to store the informations associated to nodes and its CPF
	 * @return the nodeNameToDtoMap
	 */
	protected Map<String, Collection<INodeClassDataTransferObject>> getNodeNameToDtoMap() {
		return nodeNameToDtoMap;
	}

	/**
	 * this is a map to store the informations associated to nodes and its CPF
	 * @param nodeNameToDtoMap the nodeNameToDtoMap to set
	 */
	protected void setNodeNameToDtoMap(Map<String, Collection<INodeClassDataTransferObject>> nodeNameToDtoMap) {
		this.nodeNameToDtoMap = nodeNameToDtoMap;
	}



	/**
	 * ID of the plugin where we can find panel declarations
	 * @return the mainExtensionPointID
	 */
	public String getMainExtensionPointID() {
		return mainExtensionPointID;
	}
	
	
	


}
