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

import unbbayes.draw.extension.IPluginUShape;
import unbbayes.draw.extension.IPluginUShapeBuilder;
import unbbayes.draw.extension.impl.ClassInstantiationPluginUShapeBuilder;
import unbbayes.gui.table.extension.IProbabilityFunctionPanelBuilder;
import unbbayes.prs.builder.INodeBuilder;
import unbbayes.prs.builder.extension.PluginNodeBuilder;
import unbbayes.prs.builder.extension.impl.ClassInstantiationPluginNodeBuilder;
import unbbayes.prs.extension.IPluginNode;
import unbbayes.util.ApplicationPropertyHolder;
import unbbayes.util.Debug;
import unbbayes.util.extension.dto.INodeClassDataTransferObject;
import unbbayes.util.extension.dto.impl.NodeDto;


/**
 * This class manages the plugin nodes
 * (nodes loaded by plugins)
 * for the UnBBayes' core module.
 * @author Shou Matsumoto
 *
 */
public class CorePluginNodeManager {
	
	// this is a map to store the informations associated to classes extending IPluginNode (which are plugin nodes)
	private Map<Class, INodeClassDataTransferObject> nodeClassToDtoMap = new HashMap<Class, INodeClassDataTransferObject>();
	
	private UnBBayesPluginContextHolder unbbayesPluginContextHolder = UnBBayesPluginContextHolder.newInstance();
	
	// ID of the plugin where we can find new node declarations
	private String pluginNodeExtensionPointID;
//	static {
//		pluginNodeExtensionPointID = ApplicationPropertyHolder.getProperty().getProperty("unbbayes.util.extension.node.CorePluginNodeManager.pluginNodeExtensionPointID");
//		if (pluginNodeExtensionPointID == null) {
//			System.err.println("Error reading PluginNode extension point from application.properties. Using default.");
//			pluginNodeExtensionPointID = "PluginNode";
//		}
//	}
	
	/** Name of the extension point parameter for node's class {@link IPluginNode} or its builder {@link PluginNodeBuilder} */
	public static final String PARAMETER_CLASS = "class";
	/** Name of the extension point parameter for shape {@link IPluginUShape} or its builder {@link IPluginUShapeBuilder} */
	public static final String PARAMETER_SHAPE = "shapeClass";
	/** Name of the extension point parameter for the name of this new node type (e.g. "Continuous Node", "Decision Node") */
	public static final String PARAMETER_NAME = "name";
	/** Name of the extension point parameter for this new node type's description (used as tool tip text) */
	public static final String PARAMETER_DESCRIPTION = "description";
	/** Name of the extension point parameter for icons of the split button */
	public static final String PARAMETER_ICON = "icon";
	/** Name of the extension point parameter for a cursor displayed when a user selects this node at split button */
	public static final String PARAMETER_CURSOR = "cursor";
	/** Name of the extension point parameter for a subclass/implementation of {@link IProbabilityFunctionPanelBuilder} @see {@link IProbabilityFunctionPanelBuilder} */
	public static final String PARAMETER_PANEL_BUILDER = "panelBuilder";

	/**
     * SingletonHolder is loaded on the first execution of Singleton.getInstance() 
     * or the first access to SingletonHolder.INSTANCE, not before.
     * This is used for creating singleton instances of compiler
     */
    private static class SingletonHolder { 
    	private static final CorePluginNodeManager INSTANCE = new CorePluginNodeManager();
    }
	
	/**
	 * the default constructor is made protected in order to make
	 * it easy to extend
	 */
	protected CorePluginNodeManager() {
		this.setNodeClassToDtoMap(new HashMap<Class, INodeClassDataTransferObject>());
		
		// loads config from application.properties
		pluginNodeExtensionPointID = ApplicationPropertyHolder.getProperty().getProperty("unbbayes.util.extension.node.CorePluginNodeManager.pluginNodeExtensionPointID");
		if (pluginNodeExtensionPointID == null) {
			System.err.println("Error reading PluginNode extension point from application.properties. Using default.");
			pluginNodeExtensionPointID = "PluginNode";
		}
		
		// adds a listener to be called when a "reload plugin" event is dispatched
//		this.getUnbbayesPluginContextHolder().addListener(new UnBBayesPluginContextHolder.OnReloadActionListener() {
//			public void onReload(EventObject eventObject) {
//				try {
//					reloadPluginNode();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		});
		// the above code is done at PNEditionPane#ToolBarEdition() too, so, I'm removing the redundancy
	}
	
	/**
	 * Obtains a singleton instance of CorePluginNodeManager
	 * @return a instance of CorePluginNodeManager
	 */
	public static CorePluginNodeManager newInstance() {
		return SingletonHolder.INSTANCE;
	}

	/**
	 * this is a map to store the informations associated to classes extending IPluginNode (which are plugin nodes)
	 * @return the nodeClassToDtoMap
	 */
	protected Map<Class, INodeClassDataTransferObject> getNodeClassToDtoMap() {
		return nodeClassToDtoMap;
	}

	/**
	 * this is a map to store the informations associated to classes extending IPluginNode (which are plugin nodes)
	 * @param nodeClassToDtoMap the nodeClassToDtoMap to set
	 */
	protected void setNodeClassToDtoMap(
			Map<Class, INodeClassDataTransferObject> nodeClassToDtoMap) {
		this.nodeClassToDtoMap = nodeClassToDtoMap;
	}
	
	/**
	 * Associates a plugin node's class to its set of aditional information.
	 * This information can be restored by calling {@link #getPluginNodeInformation(Class)}
	 * @param nodeClass
	 * @param dto
	 * @see #getPluginNodeInformation(Class)
	 */
	public void registerNodeClass(Class nodeClass, INodeClassDataTransferObject dto) {
		this.getNodeClassToDtoMap().put(nodeClass, dto);
	}
	
	/**
	 * Obtains a plugin node information registered by {@link #registerNodeClass(Class, INodeClassDataTransferObject)}.
	 * If not found, the plugins will be reloaded once and tried again.
	 * @param nodeClass
	 * @return
	 */
	public INodeClassDataTransferObject getPluginNodeInformation(Class nodeClass) {
		INodeClassDataTransferObject ret = this.getNodeClassToDtoMap().get(nodeClass);
		if (ret == null) {
			// retry reloading plugins
			try {
				this.reloadPlugin();	
			} catch (Exception e) {
				Debug.println(this.getClass(), "Error reloading plugin at getPluginNodeInformation", e);
			}
			ret = this.getNodeClassToDtoMap().get(nodeClass);
		}
		return ret;
	}
	
	/**
	 * {@link UnBBayesPluginContextHolder} will be used to load the plugin nodes
	 * and fill the plugin informations that can be obtained by
	 * {@link #getPluginNodeInformation(Class)}.
	 * @throws IOException : when {@link UnBBayesPluginContextHolder#publishPlugins()} fails
	 */
	public void reloadPlugin() throws IOException {
		
		// reset the registered DTOs
		this.setNodeClassToDtoMap(new HashMap<Class, INodeClassDataTransferObject>());
		
		// we assume the plugins are already published at UnBBayesFrame#loadPlugins(), but let's just be precautious...
		if (!this.getUnbbayesPluginContextHolder().isInitialized()) {
			// this method may throw a IOException
			this.getUnbbayesPluginContextHolder().publishPlugins();
		}

	    // loads the "core" plugin, which declares general extension points for core 
	    PluginDescriptor core = this.getUnbbayesPluginContextHolder().getPluginManager().getRegistry().getPluginDescriptor(
	    		this.getUnbbayesPluginContextHolder().getPluginCoreID()
	    		);
        
	    // load the extension point for new nodes.
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
				Parameter cursorParam = ext.getParameter(PARAMETER_CURSOR);
				Parameter descParam = ext.getParameter(PARAMETER_DESCRIPTION);
				Parameter iconParam = ext.getParameter(PARAMETER_ICON);
				Parameter nameParam = ext.getParameter(PARAMETER_NAME);
				Parameter panelParam = ext.getParameter(PARAMETER_PANEL_BUILDER);
				Parameter shapeParam = ext.getParameter(PARAMETER_SHAPE);
				
				// extracting class of the node or its builder
	            Class nodeClass = null;	// class for the node or its builder
	            nodeClass = classLoader.loadClass(classParam.valueAsString());
				
	            // generating node or its builder from extracted class
		    	INodeBuilder nodeBuilder = null;
		    	if (INodeBuilder.class.isAssignableFrom(nodeClass)) {
		    		// the provided node class was a builder itself
		    		nodeBuilder = (INodeBuilder)nodeClass.newInstance();
		    	} else {
		    		// the provided node class was pointing directly to a node, so let's create a default builder
		    		nodeBuilder = new ClassInstantiationPluginNodeBuilder(nodeClass);
		    		if (nameParam != null && nameParam.valueAsString() != null && nameParam.valueAsString().length() > 0) {
		    			// the name of the nodes must contain the nameParam as prefix
		    			((ClassInstantiationPluginNodeBuilder)nodeBuilder).setNamePrefix(nameParam.valueAsString());
		    		} else {
		    			// the name of the nodes contains the class name as prefix
		    			((ClassInstantiationPluginNodeBuilder)nodeBuilder).setNamePrefix(nodeClass.getName().substring(nodeClass.getName().lastIndexOf('.')));
		    		}
		    	}
				
				// extracting class of the shape or its builder
	            Class shapeClass = null;	// class for the shape or its builder
	            shapeClass = classLoader.loadClass(shapeParam.valueAsString());
				
				// generating node or its builder from extracted class
				IPluginUShapeBuilder shapeBuilder = null;
				if (IPluginUShapeBuilder.class.isAssignableFrom(shapeClass)) {
					// the provided shape class was a builder itself
					shapeBuilder = (IPluginUShapeBuilder)shapeClass.newInstance();
				} else {
					// the provided shape class was pointing directly to a shape, so let's create a default builder
					shapeBuilder = new ClassInstantiationPluginUShapeBuilder(shapeClass);
				}
				
				// extracting class for panel builder
	            Class panelClass = null;	// class for the panel builder
	            panelClass = classLoader.loadClass(panelParam.valueAsString());
				
				// generating panel builder from extracted class
				IProbabilityFunctionPanelBuilder panelBuilder = null;
				panelBuilder = (IProbabilityFunctionPanelBuilder)panelClass.newInstance();

				// extracting cursor 
				ImageIcon cursor = null;
				if (cursorParam != null) {
					// cursor was provided. Let's use it
					URL url = this.getUnbbayesPluginContextHolder().getPluginManager().getPluginClassLoader(ext.getDeclaringPluginDescriptor()).getResource(cursorParam.valueAsString());
					cursor = (url != null) ? new ImageIcon(url) : null;
				}
				
				// extracting icon
				ImageIcon icon = null;
				if (iconParam != null) {
					// cursor was provided. Let's use it
					URL url = this.getUnbbayesPluginContextHolder().getPluginManager().getPluginClassLoader(ext.getDeclaringPluginDescriptor()).getResource(iconParam.valueAsString());
					icon = (url != null) ? new ImageIcon(url) : null;
				}
				
				
				/*
				Parameter descParam = ext.getParameter(PARAMETER_DESCRIPTION);
				Parameter nameParam = ext.getParameter(PARAMETER_NAME);
				 */
				
		    	
				// filling the DTO of plugin node
				INodeClassDataTransferObject nodeDto = NodeDto.newInstance();
				nodeDto.setIcon(icon);
				nodeDto.setNodeBuilder(nodeBuilder);
				nodeDto.setShapeBuilder(shapeBuilder);
				nodeDto.setProbabilityFunctionPanelBuilder(panelBuilder);
				nodeDto.setCursorIcon(cursor);
				if (nameParam != null) {
					nodeDto.setName(nameParam.valueAsString());
				}
				if (descParam != null) {
					nodeDto.setDescription(descParam.valueAsString());
				}

				// registering the loaded class and its dto, so that users of CorePluginNodeManager can call them by using getPluginNodeInformation
				this.registerNodeClass(	nodeDto.getNodeBuilder().getNodeClass(), nodeDto);
				
			} catch (Throwable e) {
				e.printStackTrace();
				continue;
			}
		}
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
	 * Calls {@link #reloadPlugin()} and then returns
	 * a set of all loaded plugin node's informations.
	 * @return a collection of all loaded plugin nodes. A change
	 * in this collection should not affect the plugin manager's state.
	 */
	public Collection<INodeClassDataTransferObject> getAllLoadedPluginNodes() {
		try {
			this.reloadPlugin();
		} catch (Exception e) {
			Debug.println(this.getClass(), "Failed to reload plugin node while getting all loaded plugin nodes", e);
		}
		
		if (this.getNodeClassToDtoMap() == null) {
			System.err.println("Unexpected situation at " + this.getClass().getName() + ": nodeClassToDtoMap == null.");
			// returning an empty collection
			return Collections.EMPTY_SET;
		}
		
		// returning as a hash set in order to avoid duplicate items
		return new HashSet<INodeClassDataTransferObject>(this.getNodeClassToDtoMap().values());
	}

	/**
	 * Gets the static value of pluginNodeExtensionPointID, which is 
	 * the ID of PluginNode extension point.
	 * @return the pluginNodeExtensionPointID
	 */
	protected String getMainExtensionPointID() {
		return pluginNodeExtensionPointID;
	}

	/**
	 * Sets the static value of pluginNodeExtensionPointID, which is 
	 * the ID of PluginNode extension point.
	 * @param pluginNodeExtensionPointID the pluginNodeExtensionPointID to set
	 */
	protected void setMainExtensionPointID(String pluginNodeID) {
		this.pluginNodeExtensionPointID = pluginNodeID;
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

}
