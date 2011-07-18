/*
 *  UnBBayes
 *  Copyright (C) 2002, 2008 Universidade de Brasilia - http://www.unb.br
 *
 *  This file is part of UnBBayes.
 *
 *  UnBBayes is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  UnBBayes is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with UnBBayes.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package unbbayes.gui;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.java.plugin.PluginManager;
import org.java.plugin.registry.Extension;
import org.java.plugin.registry.ExtensionPoint;
import org.java.plugin.registry.PluginDescriptor;
import org.java.plugin.registry.Extension.Parameter;

import unbbayes.controller.NetworkController;
import unbbayes.gui.option.GaussianMixtureOptionPanel;
import unbbayes.gui.option.JunctionTreeOptionPanel;
import unbbayes.prs.Node;
import unbbayes.prs.bn.ProbabilisticNode;
import unbbayes.util.Debug;
import unbbayes.util.extension.PluginCore;
import unbbayes.util.extension.bn.inference.InferenceAlgorithmOptionPanel;
import unbbayes.util.extension.manager.UnBBayesPluginContextHolder;

/**
 *  Class responsible for general configurations, like node color, size, algorithm to 
 *  use for compilation, etc.
 *
 *@author Rommel N. Carvalho (rommel.carvalho@gmail.com)
 *@author Michael S. Onishi
 *@created 27 of June 2001
 *@see JDialog
 */
public class GlobalOptionsDialog extends JDialog {

	/** Serialization runtime version number */
	private static final long serialVersionUID = 0;	
	
    private NetworkController controller;
	private JTabbedPane jtp;
    private JPanel confirmationPanel;
    private JPanel logPanel;
    private JComponent algorithmMainPanel;
    private JPanel algorithmRadioPanel;
    private JButton confirm;
    private JButton restore;
    private JButton cancel;
    private JCheckBox createLog;
    private boolean createLogBoolean;
    private ButtonGroup algorithmGroup;

    private JComponent algorithmOptionPane;
    private Map<JRadioButtonMenuItem, InferenceAlgorithmOptionPanel> algorithmToOptionMap = new HashMap<JRadioButtonMenuItem, InferenceAlgorithmOptionPanel>();
    
    private String pluginDirectory = "plugins";
//    private PluginManager pluginManager;
    private String pluginCoreID = "unbbayes.util.extension.core";
    private String algorithmExtensionPoint = "InferenceAlgorithm";
    
	/** Load resource file from this package */
  	private static ResourceBundle resource = unbbayes.util.ResourceController.newInstance().getBundle(
  			unbbayes.gui.resources.GuiResources.class.getName());

  	private UnBBayesPluginContextHolder unbbayesPluginContextHolder = UnBBayesPluginContextHolder.newInstance();

    /**
     *  Constroi a estrutura da janela que mostra as opcoes globais
     *
     *@param  a rede a ser configurada (<code>TDesenhaRede</code>)
     */
    public GlobalOptionsDialog(NetworkController con) {
        super(new Frame(), resource.getString("globalOptionTitle"), true);
        Container contentPane = getContentPane();
        setSize(550, 470);
        setResizable(true);
        this.controller = con;

        createLog = new JCheckBox(resource.getString("createLogLabel"));

        algorithmGroup = new ButtonGroup();
        
        // set up plugins (algorithms) and fill map (this map associates radio button, its additional options and algorithm)
        this.setAlgorithmToOptionMap(this.loadAlgorithmsAsPlugins());
        
        // create log
        createLogBoolean       = controller.getSingleEntityNetwork().isCreateLog();
      
		jtp                       = new JTabbedPane();
        confirmationPanel         = new JPanel(new FlowLayout(FlowLayout.CENTER));
        logPanel                  = new JPanel();
        algorithmMainPanel        = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        
        
        algorithmRadioPanel       = new JPanel(new GridLayout(0,3));
        algorithmRadioPanel.setBorder(new TitledBorder(this.resource.getString("availableAlgorithms")));
        
        algorithmOptionPane = new JPanel(new FlowLayout(FlowLayout.LEADING));
        algorithmOptionPane.setBorder(new TitledBorder(this.resource.getString("algorithmParameters")));

		confirm = new JButton(resource.getString("confirmLabel"));
		confirm.setToolTipText(resource.getString("confirmToolTip"));
        confirmationPanel.add(confirm);
        confirm.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	
                    controller.getSingleEntityNetwork().setCreateLog(createLog.isSelected());
                    
                    // commit changes (made at each option panel) on inference algorithm
                    InferenceAlgorithmOptionPanel currentPanel = getSelectedAlgorithmOptionPanel();
                    if (currentPanel != null) {
                    	currentPanel.commitChanges();
                    	
                    	// updating the inference algorithm referenced by controller
                        controller.setInferenceAlgorithm(currentPanel.getInferenceAlgorithm());
                    }
                    
                    setVisible(false);
                    dispose();
                }
            });

		restore = new JButton(resource.getString("resetLabel"));
		restore.setToolTipText(resource.getString("resetToolTip"));
        confirmationPanel.add(restore);
        restore.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // reverting changes on algorithm plugins
                    InferenceAlgorithmOptionPanel selectedAlgorithmOptionPanel = getSelectedAlgorithmOptionPanel();
                    if (selectedAlgorithmOptionPanel != null) {
                    	selectedAlgorithmOptionPanel.revertChanges();
                    }
                    
                    controller.getSingleEntityNetwork().setCreateLog(createLogBoolean);
                    repaint();
                }
            });

		cancel = new JButton(resource.getString("cancelLabel"));
		cancel.setToolTipText(resource.getString("cancelToolTip"));
        confirmationPanel.add(cancel);
        cancel.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                	// reverting changes on algorithm plugins
                    InferenceAlgorithmOptionPanel selectedAlgorithmOptionPanel = getSelectedAlgorithmOptionPanel();
                    if (selectedAlgorithmOptionPanel != null) {
                    	selectedAlgorithmOptionPanel.revertChanges();
                    }
                    
                    setVisible(false);
                    dispose();
                }
            });

		logPanel.add(createLog);
		
        // adding radio buttons to the same radio button group (algorithmGroup) and same panel (algorithmRadioPanel)
		for (JRadioButtonMenuItem radioItem : this.getAlgorithmToOptionMap().keySet()) {
			algorithmGroup.add(radioItem);
			algorithmRadioPanel.add(radioItem);
		}
		
        // adding radio buttons panel to the top of algorithm tab
//        algorithmMainPanel.add(algorithmRadioPanel, BorderLayout.NORTH);
        algorithmMainPanel.add(algorithmRadioPanel);
        
        // adding the option pane for the selected algorithm.
        Component currentOptionPanel = this.getSelectedAlgorithmOptionPanel();
        if (currentOptionPanel != null) {
        	algorithmOptionPane.add(currentOptionPanel);
        }
//        algorithmMainPanel.add(algorithmOptionPane, BorderLayout.CENTER);
        algorithmMainPanel.add(algorithmOptionPane);
        
        // registers the "reload action" to the UnBBayesPluginContextHolder, so that when we press the
	    // "reload plugin" button, inference algorithm's plugins are reloaded as well
	    UnBBayesPluginContextHolder.newInstance().addListener(new UnBBayesPluginContextHolder.OnReloadActionListener() {
			public void onReload(EventObject eventObject) {
				reloadPlugins();
			}
	    });
        
		jtp.addTab(resource.getString("algorithmTab"), algorithmMainPanel);
		jtp.addTab(resource.getString("logTab"), logPanel);
        contentPane.add(jtp, BorderLayout.CENTER);
        contentPane.add(confirmationPanel, BorderLayout.SOUTH);
    }

    /**
     * Reloads plugin algorithms
     */
    protected void reloadPlugins() {
    	// load new algorithms as new map
    	Map<JRadioButtonMenuItem, InferenceAlgorithmOptionPanel> newMap = loadAlgorithmsAsPlugins();

    	// adding the plugins if they were not already added
		for (JRadioButtonMenuItem radioItem : newMap.keySet()) {
			if (!this.isAlreadyLoaded(newMap.get(radioItem))) {
				// if this plugin was not loaded before, add it
				this.algorithmGroup.add(radioItem);
				this.getAlgorithmRadioPanel().add(radioItem);
			}
		}
		
		// update the map (we use putAll because newMap may be smaller than the old one)
		this.getAlgorithmToOptionMap().putAll(newMap);
    }
    
    /**
     * Tells us if the option panel is already loaded as plugin
     * @param optionPanel
     * @return 
     */
    private boolean isAlreadyLoaded(InferenceAlgorithmOptionPanel optionPanel) {
    	if (this.getAlgorithmToOptionMap().containsValue(optionPanel)) {
    		// if the panel instance itself was loaded, return true
    		return true;
    	}
    	// perform class equivalency to determine if an algorithm with same class was already loaded
    	for (InferenceAlgorithmOptionPanel loadedPanels : this.getAlgorithmToOptionMap().values()) {
			if (loadedPanels.getClass().equals(optionPanel.getClass())) {
				// a panel using same class was loaded
				return true;
			}
		}
    	// this panel was never loaded
    	return false;
    }
    
    /**
     * Changes the {@link #getAlgorithmOptionPane()}'s content to
     * currentOptionPanel.
     * @param currentOptionPanel
     */
    protected void setCurrentAlgorithmOptionPanel(Component currentOptionPanel) {
    	// clear algorithm scroll pane and refills it with the current option panel
    	this.getAlgorithmOptionPane().removeAll();
    	this.getAlgorithmOptionPane().add(currentOptionPanel);
    	currentOptionPanel.setVisible(true);
    	this.repaint();
    }
    
    /**
     * Use the plugin framework to load algorithms and fill radio button and its option panel
     * @return
     */
    protected Map<JRadioButtonMenuItem, InferenceAlgorithmOptionPanel> loadAlgorithmsAsPlugins() {
    	
    	Map<JRadioButtonMenuItem, InferenceAlgorithmOptionPanel> ret = new HashMap<JRadioButtonMenuItem, InferenceAlgorithmOptionPanel>();
		
    	try {
    		// initialize default algorithms (those which are not actually "plugins")
        	ret.putAll(this.getDefaultAlgorithms());
        	
        	// we assume the plugins are already published at UnBBayesFrame#loadPlugins(), so, we do not have to republish them.

    	    // loads the "core" plugin, which declares general extension points for core (including algorithms)
    	    PluginDescriptor core = this.getPluginManager().getRegistry().getPluginDescriptor(this.getPluginCoreID());
            
    	    // load the extension point for new algorithms (functionalities).
    	    ExtensionPoint point = this.getPluginManager().getRegistry().getExtensionPoint(core.getId(), this.getAlgorithmExtensionPoint());
        	
    	    // iterate over the connected extension points
    	    for (Iterator<Extension> it = point.getConnectedExtensions().iterator(); it.hasNext();) {
    			try {
    				Extension ext = it.next();
    	            PluginDescriptor descr = ext.getDeclaringPluginDescriptor();
    	            
    	            this.getPluginManager().activatePlugin(descr.getId());
    				
    				// extracting parameters
    				Parameter classParam = ext.getParameter(PluginCore.PARAMETER_CLASS);
    				
    				// extracting plugin class or builder clas
    				ClassLoader classLoader = this.getPluginManager().getPluginClassLoader(descr);
    	            Class pluginClass = null;	// class for the plugin (InferenceAlgorithmOptionPanel)
    	            pluginClass = classLoader.loadClass(classParam.valueAsString());
    				
    	            // intantiating plugin object
    		    	InferenceAlgorithmOptionPanel algorithmOptionPanel = null;
    		    	algorithmOptionPanel = (InferenceAlgorithmOptionPanel)pluginClass.newInstance();
    		    	
    				// creating the radio buttons
    		    	// we assume algorithm equality as class equality (if 2 algorithms uses the same class, we assume they are the same algorithm)
    				JRadioButtonMenuItem radio =  new JRadioButtonMenuItem(algorithmOptionPanel.getInferenceAlgorithm().getName(), 
    						(controller.getInferenceAlgorithm()!= null) && (controller.getInferenceAlgorithm().getClass().equals(algorithmOptionPanel.getInferenceAlgorithm().getClass())));
    				radio.setToolTipText(algorithmOptionPanel.getInferenceAlgorithm().getDescription());
    				
    				
    				// filling the return
    				ret.put(radio, algorithmOptionPanel);
    			} catch (Throwable e) {
    				e.printStackTrace();
    				continue;
    			} 
    		}
    	    
    	    // creating action listener for each radio buttons in order to open the option panel when a radio button is choosen
    	    for (JRadioButtonMenuItem radio : ret.keySet()) {
    			radio.addActionListener(new PluginRadioButtonListener(ret.get(radio)));
    		}
		} catch (Throwable t) {
			Debug.println(this.getClass(), "Error filling Inference Algorithm's plugins", t);
		}
	    
    	return ret;
	}

    /**
     * Returns a map (radio button -> respective InferenceAlgorithmOptionPanel) for those "default" algorithms,
     * Those algorithms are treated like plugins, but they are not actually inside plugins directory.
     * 		Contents: {Junction Tree, Likelihood, gibbs, gaussian mixture}.
     * @return map of default plugins (usually, the default plugins are core classes).
     */
	protected Map<JRadioButtonMenuItem, InferenceAlgorithmOptionPanel> getDefaultAlgorithms() {
		
		Map<JRadioButtonMenuItem, InferenceAlgorithmOptionPanel> ret = new HashMap<JRadioButtonMenuItem, InferenceAlgorithmOptionPanel>();
		JRadioButtonMenuItem radio = null;	// key of the map
		InferenceAlgorithmOptionPanel algorithmOptionPanel = null;	// the mapped object
		
		// we assume algorithm equality as class equality (if 2 algorithms uses the same class, we assume they are the same algorithm)
    	
    	// junction tree
    	algorithmOptionPanel = new JunctionTreeOptionPanel();
    	radio = new JRadioButtonMenuItem(algorithmOptionPanel.getInferenceAlgorithm().getName(), 
				(controller.getInferenceAlgorithm()!= null) && (controller.getInferenceAlgorithm().getClass().equals(algorithmOptionPanel.getInferenceAlgorithm().getClass())));
		radio.setToolTipText(algorithmOptionPanel.getInferenceAlgorithm().getDescription());
		ret.put(radio, algorithmOptionPanel);

    	// likelihood weighting
//    	algorithmOptionPanel = new LikelihoodWeightingOptionPanel();
//    	radio = new JRadioButtonMenuItem(algorithmOptionPanel.getInferenceAlgorithm().getName(), 
//				(controller.getInferenceAlgorithm()!= null) && (controller.getInferenceAlgorithm().getClass().equals(algorithmOptionPanel.getInferenceAlgorithm().getClass())));
//		radio.setToolTipText(algorithmOptionPanel.getInferenceAlgorithm().getDescription());
//		ret.put(radio, algorithmOptionPanel);
    	
    	// gibbs
//    	algorithmOptionPanel = new GibbsSamplingOptionPanel();
//    	radio = new JRadioButtonMenuItem(algorithmOptionPanel.getInferenceAlgorithm().getName(), 
//				(controller.getInferenceAlgorithm()!= null) && (controller.getInferenceAlgorithm().getClass().equals(algorithmOptionPanel.getInferenceAlgorithm().getClass())));
//		radio.setToolTipText(algorithmOptionPanel.getInferenceAlgorithm().getDescription());
//		ret.put(radio, algorithmOptionPanel);
    	
        // gaussian mixture algorithm
//		@deprecated Continuous node is no longer supported in UnBBayes core. It has 
//		now been replaced by the CPS plugin available at http://sourceforge.net/projects/prognos/.
//    	algorithmOptionPanel = new GaussianMixtureOptionPanel();
//    	radio = new JRadioButtonMenuItem(algorithmOptionPanel.getInferenceAlgorithm().getName(), 
//				(controller.getInferenceAlgorithm()!= null) && (controller.getInferenceAlgorithm().getClass().equals(algorithmOptionPanel.getInferenceAlgorithm().getClass())));
//		radio.setToolTipText(algorithmOptionPanel.getInferenceAlgorithm().getDescription());
//		ret.put(radio, algorithmOptionPanel);
        
		return ret;
	}

	/**
     * Obtains the currently selected (by j option radio button) panel for algorithm options
     * @return
     */
    private InferenceAlgorithmOptionPanel getSelectedAlgorithmOptionPanel() {
    	if (this.getAlgorithmToOptionMap() == null) {
    		return null;
    	}
		for (JRadioButtonMenuItem option : this.getAlgorithmToOptionMap().keySet()) {
			if (option.isSelected()) {
				return this.getAlgorithmToOptionMap().get(option);
			}
		}
		return null;
	}

	/**
	 * @return the algorithmRadioPanel
	 */
	public JPanel getAlgorithmRadioPanel() {
		return algorithmRadioPanel;
	}


	/**
	 * @param algorithmRadioPanel the algorithmRadioPanel to set
	 */
	public void setAlgorithmRadioPanel(JPanel algorithmRadioPanel) {
		this.algorithmRadioPanel = algorithmRadioPanel;
	}


	/**
	 * @return the algorithmToOptionMap
	 */
	public Map<JRadioButtonMenuItem, InferenceAlgorithmOptionPanel> getAlgorithmToOptionMap() {
		return algorithmToOptionMap;
	}


	/**
	 * @param algorithmToOptionMap the algorithmToOptionMap to set
	 */
	public void setAlgorithmToOptionMap(
			Map<JRadioButtonMenuItem, InferenceAlgorithmOptionPanel> algorithmToOptionMap) {
		this.algorithmToOptionMap = algorithmToOptionMap;
	}


	/**
	 * @return the algorithmOptionPane
	 */
	public JComponent getAlgorithmOptionPane() {
		return algorithmOptionPane;
	}


	/**
	 * @param algorithmOptionPane the algorithmOptionPane to set
	 */
	public void setAlgorithmOptionPane(JComponent algorithmOptionPane) {
		this.algorithmOptionPane = algorithmOptionPane;
	}

	/**
	 * The directory where this class will search for plugins.
	 * 
	 * @return the pluginDirectory
	 */
	public String getPluginDirectory() {
		return pluginDirectory;
	}

	/**
	 * 
	 * The directory where this class will search for plugins.
	 * @param pluginDirectory the pluginDirectory to set
	 */
	public void setPluginDirectory(String pluginDirectory) {
		this.pluginDirectory = pluginDirectory;
	}

	
	/**
	 * @return the pluginManager
	 */
	public PluginManager getPluginManager() {
		return this.getUnBBayesPluginContextHolder().getPluginManager();
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
	public void setPluginCoreID(String pluginCoreID) {
		this.pluginCoreID = pluginCoreID;
	}

	/**
	 * This is the extension point id for InferenceAlgorithm.
	 * @return the algorithmExtensionPoint
	 */
	public String getAlgorithmExtensionPoint() {
		return algorithmExtensionPoint;
	}

	/**
	 * This is the extension point id for InferenceAlgorithm.
	 * @param algorithmExtensionPoint the algorithmExtensionPoint to set
	 */
	public void setAlgorithmExtensionPoint(String algorithmExtensionPoint) {
		this.algorithmExtensionPoint = algorithmExtensionPoint;
	}

	
	/**
	 * A component aware listener for Plugin's radio buttons.
	 * It simply updates GlobalOptionsDialog depending on what
	 * "algorithms" option is called.
	 * @author Shou Matsumoto
	 *
	 */
	protected class PluginRadioButtonListener implements ActionListener {
		Component component;
		
		public PluginRadioButtonListener(Component component) {
			super();
			this.component = component;
		}

		public void actionPerformed(ActionEvent e) {
			setCurrentAlgorithmOptionPanel(this.component);
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

