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
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.help.HelpSet;
import javax.help.JHelp;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JPopupMenu.Separator;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;

import org.java.plugin.PluginManager;
import org.java.plugin.registry.Extension;
import org.java.plugin.registry.ExtensionPoint;
import org.java.plugin.registry.PluginDescriptor;
import org.java.plugin.registry.Extension.Parameter;

import unbbayes.controller.ConfigurationsController;
import unbbayes.controller.FileHistoryController;
import unbbayes.controller.IconController;
import unbbayes.controller.JavaHelperController;
import unbbayes.controller.MainController;
import unbbayes.gui.util.SplitToggleButton;
import unbbayes.gui.util.TextAreaDialog;
import unbbayes.io.BaseIO;
import unbbayes.io.FileExtensionIODelegator;
import unbbayes.util.Debug;
import unbbayes.util.extension.PluginCore;
import unbbayes.util.extension.UnBBayesModule;
import unbbayes.util.extension.UnBBayesModuleBuilder;
import unbbayes.util.extension.builder.NetworkWindowBuilder;
import unbbayes.util.extension.manager.UnBBayesPluginContextHolder;

/**
 * This class extends <code>JFrame</code> and it is responsible for 
 * the UnBBayes main panel.
 * 
 * @author Rommel Novaes Carvalho (rommel.carvalho@gmail.com)
 * @author Michael S. Onishi (mso@gmail.com)
 * @author Laecio Lima dos Santos (laecio@gmail.com)
 * @author Shou Matsumoto (cardialfly@yahoo.com)
 * @created 06/27/2001
 * 
 * @see JFrame
 */
public class UnBBayesFrame extends JFrame {

	/** Serialization runtime version number */
	private static final long serialVersionUID = 0;

	private MDIDesktopPane desktop;
	private JPanel topPanel;
	private JPanel bottomPanel;
	private JToolBar jtbFile;
	private JToolBar jtbView;
	private JToolBar jtbTools;
	private JToolBar jtbWindow;
	// TODO CHANGE HELP
	private JToolBar jtbHelp;
	private MainController controller;

	private JButton newNet;
//	private JButton newMsbn;
//	private JButton newMebn;
//	private JButton newOobn;
	private JButton openNet;
	private JButton saveNet;

//	private JButton learn;
	private JButton metal;
	private JButton motif;
	private JButton homeSystem;
	private JButton tile;
	private JButton cascade;
	private JButton help;
	private JButton about; 
	
	private URL helpSetURL;
	private HelpSet set;
	private JHelp jHelp;
	private ActionListener alNewBN;
	
//	private ActionListener alTAN;
//	private ActionListener alBAN;
//	private ActionListener alNewMSBN;
//	private ActionListener alNewMEBN;
//	private ActionListener alNewOOBN;
	private ActionListener alOpen;
	private ActionListener alSave;
	private ActionListener alExit;
	private ActionListener alTbFile;
	private ActionListener alTbView;
	private ActionListener alTbTools;
	private ActionListener alTbWindow;
	private ActionListener alTbHelp;
	private ActionListener alMetal;
	private ActionListener alMotif;
	private ActionListener alHomeSystem;
//	private ActionListener alLearn;
//	private ActionListener alIL;
//	private ActionListener alMetaphor;
//	private ActionListener alMedicalMetaphor;
//	private ActionListener alUnBMiner;
	private ActionListener alCascade;
	private ActionListener alTile;
	private ActionListener alHelp;
	private ActionListener alAbout;
//	private ActionListener alLogic;
//	private ActionListener alLW;
//	private ActionListener alGibbs;
	

	private JFileChooser chooser;
	private FileHistoryController fileHistoryController;

	protected IconController iconController = IconController.getInstance();

	private static UnBBayesFrame singleton = null;

	/** Load resource file from this package */
	private static ResourceBundle resource = unbbayes.util.ResourceController.newInstance().getBundle(
			unbbayes.gui.resources.GuiResources.class.getName());
	
	
	private JMenuBar menu;
	
	// Adding plugin support
	private JToolBar pluginToolBar;
	private SplitToggleButton pluginSplitButton;
	private JMenu pluginMenu;
	private UnBBayesPluginContextHolder unbbayesPluginContextHolder = UnBBayesPluginContextHolder.newInstance();
//	private String pluginDirectory = "plugins";
	private String pluginModuleExtensionPoint = "Module";
	
	/** Map: ID -> {@link UnBBayesModule} or {@link UnBBayesModuleBuilder} */
	private Map<String, Class> pluginMap = null;
	
	// this is the menu item for reloading a set of plugins at plugin folder
	private JMenuItem reloadPluginsMenuItem;
	
	// these are menus also accessed by plugins
	private JMenu newMenu;
	private JMenu toolsMenu;
	private JMenu samplingMenu;
	
	// This is a split button for tools
	private SplitToggleButton toolsSplitButton;
	
	// It maps a module category into a menu
	private Map<String, List<JComponent>> moduleCategoryToComponentsMap = new HashMap<String, List<JComponent>>();

	private Separator pluginMenuSeparator;
	
	private Map<JComponent, JComponent> mapOfComponentsToBeRemovedAtPluginReload = new HashMap<JComponent, JComponent>();

	private JToolBar jtbNew;

	/**
	 * Build the main program frame, adjusting the layouts and building the 
	 * buttons, labels, and others elements of the interface. Create too the 
	 * listeners. 
	 * 
	 * @see ActionListener
	 * @see MouseListener
	 * @see KeyListener
	 */
	public UnBBayesFrame(MainController _controller) {
		super(resource.getString("unbbayesTitle"));
		this.controller = _controller;
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new ListenerCloserFrame()); 
		
		fileHistoryController = FileHistoryController.getInstance();

		Container contentPane = getContentPane();

		// instantiate panels
		desktop = new MDIDesktopPane();
		topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 1));

		createActionListeners();
		createMenu();
		createToolBars();
		assignActionListeners();

		// add panels to the content pane
		contentPane.add(topPanel, BorderLayout.NORTH);
		contentPane.add(new JScrollPane(desktop), BorderLayout.CENTER);
		contentPane.add(bottomPanel, BorderLayout.SOUTH);

		this.setIconImage(iconController.getUnBBayesIcon().getImage());
		
		setLocationByPlatform(true); 
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setVisible(true);

		singleton = this;
		
		// fills up the moduleCategoryToJMenuMap
		this.setModuleCategoryToComponentsMap(this.buildModuleCategoryToComponentsMap());
		
		// plugin support
		this.loadPlugins();
		
		// hide menu item if empty
		updatePluginMenuVisibility();
	}
	
	/**
	 * Hide plugin menu or tool bar if it is empty
	 */
	protected void updatePluginMenuVisibility() {
		
		// if we have no plugins, we should not show the tool bar or the menu
		if ( ( this.getPluginSplitButton().getMenu().getComponents() == null )
		  || ( this.getPluginSplitButton().getMenu().getComponents().length == 0 ) ) {
			this.getPluginToolBar().setVisible(false);
		} else {
			this.getPluginToolBar().setVisible(true);
		}
		
		if ( ( this.getPluginMenu().getPopupMenu().getComponents() == null )
		  || ( this.getPluginMenu().getPopupMenu().getComponents().length <= 2 ) ) { // at least "reload and split"
			this.getPluginMenuSeparator().setVisible(false);
		} else {
			this.getPluginMenuSeparator().setVisible(true);
		}
		
		// hide tool JTB or menu if the split button is empty
		if ( ( this.getToolsSplitButton().getMenu().getComponents() == null )
		  || ( this.getToolsSplitButton().getMenu().getComponents().length == 0 ) ) {
			this.getJtbTools().setVisible(false);
		} else {
			this.getJtbTools().setVisible(true);
		}
		if ( ( this.getToolsMenu().getPopupMenu().getComponents() == null )
		  || ( this.getToolsMenu().getPopupMenu().getComponents().length == 0 ) ) { 
			this.getToolsMenu().setVisible(false);
		} else {
			this.getToolsMenu().setVisible(true);
		}
		
		// hide sampling if empty
		if ( ( this.getSamplingMenu().getPopupMenu().getComponents() == null ) 
		  || ( this.getSamplingMenu().getPopupMenu().getComponents().length == 0 ) ) {
			this.getSamplingMenu().setVisible(false);
		} else {
			this.getSamplingMenu().setVisible(true);
		}
		
		this.repaint();
	}

	private void setLnF(String lnfName) {
		try {
			if (lnfName.equals("system")) {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} else {
				UIManager.setLookAndFeel(lnfName);
			}
			SwingUtilities.updateComponentTreeUI(this);
		} catch (UnsupportedLookAndFeelException ex1) {
			System.err.println(resource
					.getString("LookAndFeelUnsupportedException")
					+ lnfName);
		} catch (ClassNotFoundException ex2) {
			System.err.println(resource
					.getString("LookAndFeelClassNotFoundException")
					+ lnfName);
		} catch (InstantiationException ex3) {
			System.err.println(resource
					.getString("LookAndFeelInstantiationException")
					+ lnfName);
		} catch (IllegalAccessException ex4) {
			System.err.println(resource
					.getString("LookAndFeelIllegalAccessException")
					+ lnfName);
		}
	}

	/**
	 * Returns the selected window.
	 * 
	 * @return the selected window.
	 */
	public JInternalFrame getSelectedWindow() {
		return desktop.getSelectedFrame();
	}

	/**
	 * Adds a new window.
	 * 
	 * @param newWindow
	 *            nova janela.
	 */
	public void addWindow(JInternalFrame newWindow) {
		desktop.add(newWindow);
	}
	
	/**
	 * Adds a new window/module.
	 * 
	 * @param newWindow
	 *            new module to add.
	 */
	public void addWindow(UnBBayesModule newModule) {
		desktop.add(newModule);
		newModule.setUnbbayesFrame(this);
	}

	/**
	 * Method responsible for creating all ActionListeners needed.
	 */
	public void createActionListeners() {

		// create an ActionListener for opening new window for BN
		alNewBN = new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				setCursor(new Cursor(Cursor.WAIT_CURSOR));
				controller.newPN();
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		};

		// create an ActionListener for opening new window for MSBN
//		alNewMSBN = new ActionListener() {
//			public void actionPerformed(ActionEvent ae) {
//				setCursor(new Cursor(Cursor.WAIT_CURSOR));
//				controller.newMSBN();
//				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
//			}
//		};

		// create an ActionListener for opening new window for MEBN
//		alNewMEBN = new ActionListener() {
//			public void actionPerformed(ActionEvent ae) {
//				setCursor(new Cursor(Cursor.WAIT_CURSOR));
//				controller.newMEBN();
//				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
//			}
//		};
		
		// create an ActionListener for opening new window for OOBN
//		alNewOOBN = new ActionListener() {
//			public void actionPerformed(ActionEvent ae) {
//				setCursor(new Cursor(Cursor.WAIT_CURSOR));
//				controller.newOOBN();
//				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
//			}
//		};

//		alTAN = new ActionListener() {
//			public void actionPerformed(ActionEvent ae) {
//				String[] nets = new String[] { "txt" };
//				int classe = 0;
//				chooser = new JFileChooser(fileHistoryController.getCurrentDirectory());
//				chooser.setMultiSelectionEnabled(false);
//				chooser.addChoosableFileFilter(new SimpleFileFilter(nets,
//						resource.getString("textFileFilter")));
//				int option = chooser.showOpenDialog(UnBBayesFrame.this);
//				File file;
//				if (option == JFileChooser.APPROVE_OPTION) {
//					file = chooser.getSelectedFile();
//					fileHistoryController.setCurrentDirectory(chooser
//							.getCurrentDirectory());
//					try {
//						new ConstructionController(file, controller, classe);
//					} catch (InvalidParentException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//
//			}
//		};		
//		alBAN = new ActionListener() {
//			public void actionPerformed(ActionEvent ae) {
//				String[] nets = new String[] { "txt" };
//				int classe = 0;
//				chooser = new JFileChooser(fileHistoryController.getCurrentDirectory());
//				chooser.setMultiSelectionEnabled(false);
//				chooser.addChoosableFileFilter(new SimpleFileFilter(nets,
//						resource.getString("textFileFilter")));
//				int option = chooser.showOpenDialog(UnBBayesFrame.this);
//				File file;
//				if (option == JFileChooser.APPROVE_OPTION) {
//					file = chooser.getSelectedFile();
//					fileHistoryController.setCurrentDirectory(chooser
//							.getCurrentDirectory());
//					try {
//						new ConstructionController(file, controller, classe, true);
//					} catch (InvalidParentException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//
//			}
//		};

		

		// create an ActionListener for loading
		alOpen = new OpenFileUsingModulesActionListener(null);

		// create an ActionListener for saving
		alSave = new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				setCursor(new Cursor(Cursor.WAIT_CURSOR));
				
				// Select filter using currently focused window
				String[] nets = null;			// file filter content
				String filterMessage = null;	// message to be added to file filter
				String dialogueTitle = null;	// title of the file chooser
				JInternalFrame focusedInnerFrame = desktop.getSelectedFrame(); 	// currently focused window
				UnBBayesModule currentWindow = null;
				if (focusedInnerFrame != null) {
					if (focusedInnerFrame instanceof UnBBayesModule) {
						currentWindow = ((UnBBayesModule)focusedInnerFrame);
						nets = currentWindow.getSupportedFileExtensions(false);
						filterMessage = currentWindow.getSupportedFilesDescription(false);
						dialogueTitle = currentWindow.getSavingMessage();
					} else {
						// unsupported window type...
						setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
						return;
					}
				} else {
					// no window is focused
					// so, nothing is to be stored by now
					Debug.println(this.getClass(), "No focused window to save.");
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					return;
				}

				chooser = new JFileChooser(fileHistoryController.getCurrentDirectory());
				chooser.setMultiSelectionEnabled(false);
				chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				chooser.setDialogTitle(dialogueTitle); 
				
				chooser.setFileView(new FileIcon(UnBBayesFrame.this));
				chooser.addChoosableFileFilter(new SimpleFileFilter(nets, filterMessage));
				int option = chooser.showSaveDialog(null);
				if (option == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					if (file != null) {
						if (file.isFile()) {
							// String name = file.getName();
							/*
							 * if (! name.endsWith(".net")) { file = new
							 * File(file.getAbsoluteFile() + ".net");
							 * fileHistoryController.setCurrentDirectory(chooser.getCurrentDirectory()); }
							 */
						}
						try {
							if(controller.saveNet(file, currentWindow)){
								// successful
								JOptionPane.showMessageDialog(UnBBayesFrame.this, 
										resource.getString("saveSucess"), 
										resource.getString("sucess"), 
										JOptionPane.WARNING_MESSAGE); 
							}
						} catch (FileExtensionIODelegator.MoreThanOneCompatibleIOException more) {
							// OBS. FileExtensionIODelegator.MoreThanOneCompatibleIOException is managed
							// differently by load and save. This is because load is done by the module and the
							// save is done by IO (on load, we are not sure what module to use, but on save we
							// are sure that we should use the currently active module/window).
							// Thats why MoreThanOneCompatibleIOException must be treated by modules on load,
							// but treated by UnBBayesFrame on save.
							
							// More than one I/O was found to be compatible. Ask user to select one.
							String[] possibleValues = FileExtensionIODelegator.getNamesFromIOs(more.getIOs());
					    	String selectedValue = (String)JOptionPane.showInputDialog(
					    			UnBBayesFrame.this, 
					    			resource.getString("IOConflictMessage"), 
					    			resource.getString("IOConflictTitle"),
					    			JOptionPane.INFORMATION_MESSAGE, 
					    			null,
					    			possibleValues, 
					    			possibleValues[0]);
					    	// if user has selected something, use it to save
					    	if (selectedValue != null) {
					    		BaseIO io = FileExtensionIODelegator.findIOByName(more.getIOs(), selectedValue);
					    		try {
					    			if(controller.saveNet(file, io, currentWindow.getPersistingGraph())){
										// save successful
					    				JOptionPane.showMessageDialog(UnBBayesFrame.this, 
												resource.getString("saveSucess"), 
												resource.getString("sucess"), 
												JOptionPane.WARNING_MESSAGE); 
									}
								} catch (Exception e) {
									e.printStackTrace();
									JOptionPane.showMessageDialog(UnBBayesFrame.this, 
											e.getMessage(), 
											resource.getString("error"), 
											JOptionPane.ERROR_MESSAGE); 
								}
					    	} 
						} catch (Exception e) {
							e.printStackTrace();
							JOptionPane.showMessageDialog(UnBBayesFrame.this, 
									e.getMessage(), 
									resource.getString("error"), 
									JOptionPane.ERROR_MESSAGE); 
						}
					}
				}
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		};

		// create an ActionListener for exiting
		alExit = new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					controller.saveConfigurations();
				} catch (IOException e) {
					e.printStackTrace();
				}
				setVisible(false);
				dispose();
				System.exit(0);
			}
		};

		// create an ActionListener for opening new window for learning
//		alLearn = new ActionListener() {
//			public void actionPerformed(ActionEvent ae) {
//				String[] nets = new String[] { "txt" };
//				chooser = new JFileChooser(fileHistoryController.getCurrentDirectory());
//				chooser.setMultiSelectionEnabled(false);
//				chooser.addChoosableFileFilter(new SimpleFileFilter(nets,
//						resource.getString("textFileFilter")));
//				int option = chooser.showOpenDialog(UnBBayesFrame.this);
//				File file;
//				if (option == JFileChooser.APPROVE_OPTION) {
//					file = chooser.getSelectedFile();
//					fileHistoryController.setCurrentDirectory(chooser
//							.getCurrentDirectory());
//					new ConstructionController(file, controller);
//				}
//			}
//		};

//		alIL = new ActionListener() {
//			public void actionPerformed(ActionEvent ae) {
//				new ILBridge(controller);
//			}
//		};
		
//		alMetaphor = new ActionListener() {
//			public void actionPerformed(ActionEvent ae) {
//				AFINMetaphorFrame frame = new AFINMetaphorFrame(); 
//				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//				frame.setVisible(true);
//			}
//		};
		
//		alMedicalMetaphor = new ActionListener() {
//			public void actionPerformed(ActionEvent ae) {
//				MetaphorFrame frame = new MetaphorFrame();
//				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//				frame.setVisible(true);
//			}
//		};
		
//		alUnBMiner = new ActionListener() {
//			public void actionPerformed(ActionEvent ae) {
//				UnBMinerFrame frame = new UnBMinerFrame();
//				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//				frame.setVisible(true);
//			}
//		};

//		alLogic = new ActionListener() {
//			public void actionPerformed(ActionEvent ae) {
//				// ControladorPrincipal cp = new ControladorPrincipal();
//				new MCMainController(new MatrixMonteCarloSampling());
//			}
//		};
		
//		alLW = new ActionListener() {
//			public void actionPerformed(ActionEvent ae) {
//				// ControladorPrincipal cp = new ControladorPrincipal();
//				new MCMainController(new LikelihoodWeightingSampling());
//			}
//		};
//
//		alGibbs = new ActionListener() {
//			public void actionPerformed(ActionEvent ae) {
//				new MCMainController(new GibbsSampling());
//			}
//		};

		// create an ActionListener for showing the File Tool Bar
		alTbFile = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCursor(new Cursor(Cursor.WAIT_CURSOR));
				if (((JCheckBoxMenuItem) e.getSource()).getState()) {
					topPanel.add(jtbFile);
				} else {
					topPanel.remove(jtbFile);
				}
				// lay out its subcomponents again after an container has been
				// added, removed or modified
				validate();
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

		};

		// create an ActionListener for showing the View Tool Bar
		alTbView = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCursor(new Cursor(Cursor.WAIT_CURSOR));
				if (((JCheckBoxMenuItem) e.getSource()).getState()) {
					topPanel.add(jtbView);
				} else {
					topPanel.remove(jtbView);
				}
				// lay out its subcomponents again after an container has been
				// added, removed or modified
				validate();
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

		};

		// create an ActionListener for showing the Tools Tool Bar
		alTbTools = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCursor(new Cursor(Cursor.WAIT_CURSOR));
				if (((JCheckBoxMenuItem) e.getSource()).getState()) {
					topPanel.add(jtbTools);
				} else {
					topPanel.remove(jtbTools);
				}
				// lay out its subcomponents again after an container has been
				// added, removed or modified
				validate();
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

		};

		// create an ActionListener for showing the Window Tool Bar
		alTbWindow = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCursor(new Cursor(Cursor.WAIT_CURSOR));
				if (((JCheckBoxMenuItem) e.getSource()).getState()) {
					topPanel.add(jtbWindow);
				} else {
					topPanel.remove(jtbWindow);
				}
				// lay out its subcomponents again after an container has been
				// added, removed or modified
				validate();
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

		};

		// create an ActionListener for showing the Help Tool Bar
	   alTbHelp = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCursor(new Cursor(Cursor.WAIT_CURSOR));
				if (((JCheckBoxMenuItem) e.getSource()).getState()) {
					topPanel.add(jtbHelp);
				} else {
					topPanel.remove(jtbHelp);
				}
				// lay out its subcomponents again after an container has been
				// added, removed or modified
				validate();
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

		};

		// create an ActionListener for choosing Metal Look and Feel
		alMetal = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCursor(new Cursor(Cursor.WAIT_CURSOR));
				setLnF("javax.swing.plaf.metal.MetalLookAndFeel");
				MetalLookAndFeel.setCurrentTheme(new OceanTheme());
				//MetalLookAndFeel.setCurrentTheme(new DefaultMetalTheme());
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

		};

		// create an ActionListener for choosing Motif Look and Feel
		alMotif = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCursor(new Cursor(Cursor.WAIT_CURSOR));
				setLnF("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

		};

		// create an ActionListener for choosing the Home System Look and Feel
		alHomeSystem = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCursor(new Cursor(Cursor.WAIT_CURSOR));
				setLnF("system");
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

		};

		// create an ActionListener for ordering windows as cascade
		alCascade = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desktop.cascadeFrames();
			}
		};

		// create an ActionListener for ordering windows as tile
		alTile = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				desktop.tileFrames();
			}
		};

		// create an ActionListener for opening the Help window
		alHelp = new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					JavaHelperController.getInstance().openHelp(singleton);
				} catch (Exception evt) {
					System.out.println("Error= " + evt.getMessage() + " "
							+ this.getClass().getName());
					evt.printStackTrace();
				}
			}
		};
		
		alAbout = new ActionListener(){
			public void actionPerformed(ActionEvent ae) {
    			
		        javax.swing.SwingUtilities.invokeLater(new Runnable() {
		            public void run() {
		            	showAboutPane();
		            }
		        });

			}
		};
	}
	

	private void showAboutPane(){
		AboutPane abourPane = new AboutPane();
		abourPane.pack();
		abourPane.setVisible(true);
	}
	
	/**
	 * Method responsible for creating the menu used in this class, JFrame.
	 */
	public void createMenu() {
		menu = new JMenuBar();

		// create menus and set their mnemonic
		JMenu fileMenu = new JMenu(resource.getString("fileMenu"));
		JMenu recentFilesMenu = new JMenu(resource.getString("recentFilesMenu"));
		JMenu lafMenu = new JMenu(resource.getString("lafMenu"));
		JMenu viewMenu = new JMenu(resource.getString("viewMenu"));
		JMenu tbMenu = new JMenu(resource.getString("tbMenu"));
		newMenu = new JMenu(resource.getString("newMenu"));
		toolsMenu = new JMenu(resource.getString("toolsMenu"));
		samplingMenu = new JMenu(resource.getString("samplingMenu"));
		JMenu windowMenu = new JMenu(resource.getString("windowMenu"));
		JMenu helpMenu = new JMenu(resource.getString("helpMenu"));
		
		
		
		fileMenu.setMnemonic(resource.getString("fileMenuMn").charAt(0));
		recentFilesMenu.setMnemonic(resource.getString("recentFilesMn").charAt(0));
		newMenu.setMnemonic(resource.getString("newMenuMn").charAt(0)); 
		lafMenu.setMnemonic(resource.getString("lafMenuMn").charAt(0));
		viewMenu.setMnemonic(resource.getString("viewMenuMn").charAt(0));
		tbMenu.setMnemonic(resource.getString("tbMenuMn").charAt(0));
		toolsMenu.setMnemonic(resource.getString("toolsMenuMn").charAt(0));
		windowMenu.setMnemonic(resource.getString("windowMenuMn").charAt(0));
		helpMenu.setMnemonic(resource.getString("helpMenuMn").charAt(0));
		
		

		// create menu items, set their mnemonic and their key accelerator
		JMenuItem newBN = new JMenuItem(resource.getString("newBN"),
				iconController.getNewBNIcon());
//		JMenuItem newMSBN = new JMenuItem(resource.getString("newMSBN"),
//				iconController.getNewIcon());
//		JMenuItem newMEBN = new JMenuItem(resource.getString("newMEBN"),
//				iconController.getNewIcon());
		
//		JMenuItem newOOBN = new JMenuItem(resource.getString("newOOBN"),
//				iconController.getNewIcon());

		newBN.setMnemonic(resource.getString("newBNMn").charAt(0));
//		newMSBN.setMnemonic(resource.getString("newMSBNMn").charAt(0));
//		newMEBN.setMnemonic(resource.getString("newMEBNMn").charAt(0));
//		newOOBN.setMnemonic(resource.getString("newOOBNMn").charAt(0));
		
		JMenuItem openItem = new JMenuItem(resource.getString("openItem"),
				iconController.getOpenIcon());
		JMenuItem saveItem = new JMenuItem(resource.getString("saveItem"),
				iconController.getSaveIcon());
		JMenuItem exitItem = new JMenuItem(resource.getString("exitItem"), 'X');
		JMenuItem tbFile = new JCheckBoxMenuItem(resource.getString("tbFile"),
				true);
		JMenuItem tbNewProject = new JCheckBoxMenuItem(resource.getString("tbNewProject"),
				true);
		JMenuItem tbView = new JCheckBoxMenuItem(resource.getString("tbView"),
				true);
		JMenuItem tbTools = new JCheckBoxMenuItem(
				resource.getString("tbTools"), true);
		JMenuItem tbWindow = new JCheckBoxMenuItem(resource
				.getString("tbWindow"), true);
		JMenuItem tbHelp = new JCheckBoxMenuItem(resource.getString("tbHelp"),
				true);
		
		// Check box item for plugin toolbar
		JMenuItem tbPlugin = new JCheckBoxMenuItem(resource.getString("tbPlugin"),
				true);

		
		JMenuItem metalItem = new JMenuItem(resource.getString("metalItem"),
				iconController.getMetalIcon());
		JMenuItem motifItem = new JMenuItem(resource.getString("motifItem"),
				iconController.getMotifIcon());
		JMenuItem homeItem = new JMenuItem(
				resource.getString("windowsItem"), iconController
						.getHomeIcon());
		JMenuItem cascadeItem = new JMenuItem(
				resource.getString("cascadeItem"), iconController
						.getCascadeIcon());
		JMenuItem tileItem = new JMenuItem(resource.getString("tileItem"),
				iconController.getTileIcon());
		JMenuItem helpItem = new JMenuItem(resource.getString("helpItem"),
				iconController.getHelpIcon());
		JMenuItem aboutItem = new JMenuItem(resource.getString("aboutItem"));

		
		openItem.setMnemonic(resource.getString("openItemMn").charAt(0));
		saveItem.setMnemonic(resource.getString("saveItemMn").charAt(0));
		exitItem.setMnemonic(resource.getString("exitItemMn").charAt(0));
		metalItem.setMnemonic(resource.getString("metalItemMn").charAt(0));
		motifItem.setMnemonic(resource.getString("motifItemMn").charAt(0));
		homeItem.setMnemonic(resource.getString("windowsItemMn").charAt(0));
		cascadeItem.setMnemonic(resource.getString("cascadeItemMn").charAt(0));
		tileItem.setMnemonic(resource.getString("tileItemMn").charAt(0));
		helpItem.setMnemonic(resource.getString("helpItemMn").charAt(0));
		aboutItem.setMnemonic(resource.getString("aboutItemMn").charAt(0));

		newBN.setAccelerator(KeyStroke.getKeyStroke(resource.getString(
		"newItemMn").charAt(0), Event.CTRL_MASK, false));
		openItem.setAccelerator(KeyStroke.getKeyStroke(resource.getString(
		"openItemMn").charAt(0), Event.CTRL_MASK, false));
		saveItem.setAccelerator(KeyStroke.getKeyStroke(resource.getString(
		"saveItemMn").charAt(0), Event.CTRL_MASK, false));
		helpItem.setAccelerator(KeyStroke.getKeyStroke(resource.getString(
		"helpItemMn").charAt(0), Event.CTRL_MASK, false));

		
		//Learning menu itens
//		JMenuItem learningItem = new JMenuItem(resource.getString("learningItem"), iconController.getLearningIcon());
//		JMenuItem tanItem = new JMenuItem(resource.getString("tanItem"));
//		JMenuItem banItem = new JMenuItem(resource.getString("banItem"));
//		JMenuItem logicItem = new JMenuItem(resource.getString("logicItem"));
//		JMenuItem lwItem = new JMenuItem(resource.getString("likelihoodWeightingItem"));
//		JMenuItem gibbsItem = new JMenuItem(resource.getString("gibbsItem"));
//		JMenuItem iLearningItem = new JMenuItem(resource.getString("ILearningItem"));
//		JMenuItem metaphorItem = new JMenuItem(resource.getString("MetaphorItem"));
//		JMenuItem medicalMetaphorItem = new JMenuItem(resource.getString("MedicalMetaphorItem"));
//		JMenuItem unbMinerItem = new JMenuItem(resource.getString("UnBMinerItem"));
		
//		learningItem.setMnemonic(resource.getString("learningItemMn").charAt(0));
//		tanItem.setMnemonic(resource.getString("tanItemMn").charAt(0));
//		banItem.setMnemonic(resource.getString("banItemMn").charAt(0));
//		iLearningItem.setMnemonic(resource.getString("ILearningItemMn").charAt(0));
		
//		logicItem.setMnemonic(resource.getString("logicItemMn").charAt(0));
//		lwItem.setMnemonic(resource.getString("likelihoodWeightingItemMn").charAt(0));
//		gibbsItem.setMnemonic(resource.getString("gibbsItemMn").charAt(0));
		
//		learningItem.setAccelerator(KeyStroke.getKeyStroke(resource.getString(
//				"learningItemMn").charAt(0), Event.CTRL_MASK, false));
		

		// add ActionListener to all menu items
		newBN.addActionListener(alNewBN);
//		newMSBN.addActionListener(alNewMSBN);
//		newMEBN.addActionListener(alNewMEBN);
//		newOOBN.addActionListener(alNewOOBN);
		openItem.addActionListener(alOpen);
		saveItem.addActionListener(alSave);
		exitItem.addActionListener(alExit);
		tbFile.addActionListener(alTbFile);
		tbView.addActionListener(alTbView);
		tbTools.addActionListener(alTbTools);
		tbWindow.addActionListener(alTbWindow);
		tbHelp.addActionListener(alTbHelp);
		tbNewProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCursor(new Cursor(Cursor.WAIT_CURSOR));
				if (((JCheckBoxMenuItem) e.getSource()).getState()) {
					topPanel.add(getJtbNew());
				} else {
					topPanel.remove(getJtbNew());
				}
				// lay out its subcomponents again after an container has been
				// added, removed or modified
				validate();
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		
		// action litener for enable/disable option for plugin toolbar
		tbPlugin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCursor(new Cursor(Cursor.WAIT_CURSOR));
				if (((JCheckBoxMenuItem) e.getSource()).getState()) {
					topPanel.add(getPluginToolBar());
				} else {
					topPanel.remove(getPluginToolBar());
				}
				// lay out its subcomponents again after an container has been
				// added, removed or modified
				validate();
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		
		metalItem.addActionListener(alMetal);
		motifItem.addActionListener(alMotif);
		homeItem.addActionListener(alHomeSystem);
//		learningItem.addActionListener(alLearn);
//		iLearningItem.addActionListener(alIL);
//		metaphorItem.addActionListener(alMetaphor);
//		medicalMetaphorItem.addActionListener(alMedicalMetaphor);
//		unbMinerItem.addActionListener(alUnBMiner);
		cascadeItem.addActionListener(alCascade);
		tileItem.addActionListener(alTile);
		
//		logicItem.addActionListener(alLogic);
//		lwItem.addActionListener(alLW);
//		gibbsItem.addActionListener(alGibbs);
		
		helpItem.addActionListener(alHelp);
		aboutItem.addActionListener(alAbout);

		// add menu items to their respective menu
		
//		tanItem.addActionListener(alTAN);
//		banItem.addActionListener(alBAN);
		
		newMenu.add(newBN);
//		newMenu.add(newMSBN);
//		newMenu.add(newMEBN);
//		newMenu.add(newOOBN);
		fileMenu.add(newMenu);
		fileMenu.add(openItem);
		fileMenu.add(saveItem);
		fileMenu.addSeparator();
		
		
		fileMenu.add(recentFilesMenu); 
		
		
		ConfigurationsController configController = ConfigurationsController.getInstance(); 
		for(String nameOfFile: configController.getConfigurations().getPreviewOpenFiles()){
			File file = new File(nameOfFile); 
			JMenuItem fileMenuItem = new JMenuItem(nameOfFile);  
			fileMenuItem.addActionListener(new OpenFileUsingModulesActionListener(file)); 
			
			recentFilesMenu.add(fileMenuItem); 
		}
		

		fileMenu.addSeparator(); 
		fileMenu.add(exitItem);
		
		lafMenu.add(metalItem);
		lafMenu.add(motifItem);
		lafMenu.add(homeItem);
		
		tbMenu.add(tbNewProject);
		tbMenu.add(tbFile);
		tbMenu.add(tbView);
		tbMenu.add(tbTools);
		tbMenu.add(tbWindow);
		tbMenu.add(tbHelp);
		
		// Adding the enable/disable option for plugin toolbar
		tbMenu.add(tbPlugin);
		
		viewMenu.add(tbMenu);
		viewMenu.addSeparator();
		viewMenu.add(lafMenu);
		
//		toolsMenu.add(learningItem);
//		toolsMenu.add(tanItem);
//		toolsMenu.add(banItem);
//		toolsMenu.add(iLearningItem);
//		toolsMenu.add(metaphorItem);
//		toolsMenu.add(medicalMetaphorItem);
//		toolsMenu.add(unbMinerItem);
		
//		samplingMenu.add(logicItem);
//		samplingMenu.add(lwItem);
//		samplingMenu.add(gibbsItem);
		
		windowMenu.add(cascadeItem);
		windowMenu.add(tileItem);
		helpMenu.add(helpItem);
		helpMenu.add(aboutItem);

		menu.add(fileMenu);
		menu.add(viewMenu);
		menu.add(toolsMenu);
		menu.add(samplingMenu);
		menu.add(windowMenu);
		menu.add(helpMenu);
		

		this.setJMenuBar(menu);
		
	}
	
	/**
	 * This method is called inside the constructor in order
	 * to initialize the map, which is to be stored
	 * at {@link #getModuleCategoryToComponentsMap()}.
	 * It uses the default menus ("new", "sampling", "tool" and "plugins")
	 * @see #getModuleCategoryToComponentsMap()
	 * @see #setModuleCategoryToComponentsMap(Map).
	 * @return the builded map. The caller must use this map to set {@link #setModuleCategoryToComponentsMap(Map)}
	 */
	protected Map<String, List<JComponent>> buildModuleCategoryToComponentsMap() {
		Map<String, List<JComponent>>newMap = new HashMap<String, List<JComponent>>();
		
		// filling components for "new" (e.g. "new bn", "new msbn")
		List<JComponent> componentList = new ArrayList<JComponent>();
		componentList.add(this.getNewMenu());
		componentList.add(this.getJtbNew());
		newMap.put(PluginCore.PARAMETER_CATEGORY_VALUE_BN, componentList);
		
		// filling components for "new" (e.g. "new bn", "new msbn")
		componentList = new ArrayList<JComponent>();
		componentList.add(this.getSamplingMenu());
		newMap.put(PluginCore.PARAMETER_CATEGORY_VALUE_SAMPLING, componentList);

		// filling components for "tools" 
		componentList = new ArrayList<JComponent>();
		componentList.add(this.getToolsMenu());
		componentList.add(this.getToolsSplitButton());
		newMap.put(PluginCore.PARAMETER_CATEGORY_VALUE_TOOL, componentList);
		
		return newMap;
	}
	
	/**
	 * This method is used whithin {@link #loadPlugins()} in order to instantiate the "Plugin" tool bars.
	 * It does not actually fill the tool bar, since the plugins must be loaded after the creation of the
	 * toolbar.
	 * It is only a initializer. 
	 * The contents are created at {@link #fillCorePluginMenuAndButtons(PluginManager, ExtensionPoint)}
	 */
	protected void createPluginToolBars() {
		if (pluginToolBar != null) {
			topPanel.remove(pluginToolBar);
		}
		pluginToolBar = new JToolBar();
		pluginToolBar.setSize(pluginToolBar.getWidth(), jtbFile.getHeight());
		pluginToolBar.setToolTipText(resource.getString("pluginMenu"));
		
		// instantiate new Split button
		this.setPluginSplitButton(new SplitToggleButton());
		pluginToolBar.add(this.getPluginSplitButton());
		this.getPluginSplitButton().setToolTipText(resource.getString("pluginMenu"));
		
		topPanel.add(pluginToolBar);
	}
	
	/**
	 * This method is used whithin {@link #loadPlugins()} in order to create the "Plugin" menu.
	 * It does not actually fill the menu with plugins, since the plugins must be loaded after the creation of the
	 * menu.
	 * It is only a initializer. 
	 * The contents are created at {@link #fillCorePluginMenuAndButtons(PluginManager, ExtensionPoint)}
	 */
	protected void createPluginMenu(){
		if (pluginMenu != null) {
			menu.remove(pluginMenu);
		}
		// create menu
		pluginMenu = new JMenu(resource.getString("pluginMenu"));
		pluginMenu.setMnemonic(resource.getString("pluginMenu").charAt(0));
		
		reloadPluginsMenuItem = new JMenuItem(this.resource.getString("reloadPlugin"));
		reloadPluginsMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				reloadPlugins();
			}
		});
		reloadPluginsMenuItem.setToolTipText(this.resource.getString("reloadPluginToolTip"));
		
		pluginMenu.add(reloadPluginsMenuItem);
		
		pluginMenuSeparator = new JPopupMenu.Separator();
		pluginMenu.add(pluginMenuSeparator);
		
		menu.add(pluginMenu);

		// filling components for "plugins"  (default)
		List<JComponent> componentList = new ArrayList<JComponent>();
		componentList.add(pluginMenu);
		this.getModuleCategoryToComponentsMap().put(PluginCore.PARAMETER_CATEGORY_VALUE_PLUGIN, componentList);
	}
	
	/**
	 * This method is called when we press the "reload plugin" menu.
	 * By overwriting this method, you can customize the plugin's reload action.
	 * This method basically resets the components obtained from {@link #getMapOfComponentsToBeRemovedAtPluginReload()},
	 * resets the menu bar, refills the {@link #getModuleCategoryToComponentsMap()},
	 * reloads plugins, updates the menu's visibility (if no plugin is present, some menus and toolbars are hidden),
	 * and finally notifies reload action to {@link UnBBayesPluginContextHolder#notifyReload(Object)} using
	 * this UnBBayesFrame as parameter.
	 */
	protected void reloadPlugins() {
		// reset components
		this.createMenu();	//reset menu
		this.removePluginButtonsFromOtherComponents(this.getMapOfComponentsToBeRemovedAtPluginReload()); // remove buttons from other JTBs
		this.setModuleCategoryToComponentsMap(this.buildModuleCategoryToComponentsMap()); // fills up the moduleCategoryToJMenuMap
		this.loadPlugins(); // reload plugins
		this.updatePluginMenuVisibility();	// show menus if it is not empty again
		
		// reloads every plugin managed by UnBBayesPluginContextHolder by notifying it
		this.getUnbbayesPluginContextHolder().notifyReload(this);
		this.repaint();
	}
	
	/**
	 * Obtains the classes of modules' builders (UnBBayesModuleBuilder) which are part of core implementation of unbbayes.
	 * Core implementation modules are instances of UnBBayesModule that was not loaded
	 * by plugin mechanism.
	 * @return a map of basic modules' classes' builders (or the classes itself): e.g. PN.
	 * The keys are the main ID of the plugin/module
	 */
	public Map<String, Class> getCoreUnBBayesModulesClasses() {
		Map<String, Class> ret = new HashMap<String, Class>();
		
		// adding PN module
		try{
			ret.put(this.resource.getString("PNModuleName"),NetworkWindowBuilder.class);
		} catch (Exception e) {
			throw new RuntimeException(this.resource.getString("moduleLoadingError") 
					+ this.resource.getString("PNModuleName"));
		}
		
		return ret;
	}
	
	/**
	 * This method displays a dialog if a
	 * plugin with dependency error is detected.
	 */
	public void showPluginDependencyError() {
		try {
			
			// retrieve erroneous ids and dependencies
			Map<String, Set<String>> errorMap = this.getUnbbayesPluginContextHolder().getErroneousPluginIDDependencyMap();
			if (!errorMap.isEmpty()) {
				// if there are errors, report using text logs
				TextAreaDialog dialog = new TextAreaDialog(this, this.resource.getString("pluginDependencyLogTitle"), false);
				
				// prepare dialog content's header
				String dialogContent = "=============================================================\n";
				dialogContent 		+= this.resource.getString("pluginDependencyLogKeyMessage");
				dialogContent 		+= "\n=============================================================\n";
				
				//  build content
				for (String pluginID : errorMap.keySet()) {
					dialogContent += "\n\n";
					dialogContent += "-> " + this.resource.getString("pluginDependencyLogID");
					dialogContent += " \"" + pluginID + "\"";
					dialogContent += "\n     " + this.resource.getString("pluginDependencyLogDependencies");
					for (String dependency : errorMap.get(pluginID)) {
						dialogContent += "\n\t - \"" + dependency + "\";";
					}
					dialogContent += "\n\n";
				}
				
				// footer
				dialogContent 		+= "\n=============================================================\n";
				dialogContent 		+= this.resource.getString("pluginDependencyLogPleaseCheck");
				dialogContent 		+= "\n=============================================================\n";
				
				// update dialog's content
				dialog.setTextContent(dialogContent);
				
				// display dialog
				dialog.pack();
				dialog.setLocationRelativeTo(this); 
				dialog.setVisible(true);
			}
		} catch (Throwable e) {
			System.err.println("Could not retrieve plugin dependency errors.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates the plugin manager, loads UnBBayes' core plugins using JPF, 
	 * fills the menu items and tool bar's buttons and their listeners.
	 */
	public void loadPlugins(){
		
		// initialize loaded plugins
		this.setPluginList(this.getCoreUnBBayesModulesClasses());
		
		// initialize plugins' menu and tool bars
		this.createPluginMenu();
		this.createPluginToolBars();
		
		// create plugin manager and load (publish) plugins
		try {
			this.getUnbbayesPluginContextHolder().publishPlugins();
		} catch (IOException e) {
			// could not load plugins, but we shall continue
	        e.printStackTrace();
	        this.getPluginToolBar().setVisible(false);
//			this.getPluginMenu().setVisible(false);
	        return;
		}
		
		// loads the "core" plugin, which is a stub that we use to declare extension points for core
		PluginDescriptor core = this.getUnbbayesPluginContextHolder().getPluginManager().getRegistry().getPluginDescriptor(
				this.getUnbbayesPluginContextHolder().getPluginCoreID()
    		);
		
	    // load the extension point for new modules (functionalities).
	    ExtensionPoint point = this.getUnbbayesPluginContextHolder().getPluginManager().getRegistry().getExtensionPoint(
	    			core.getId(), 
	    			this.getPluginModuleExtensionPoint()
	    		);

	    // create menu/buttons that activates a plugin and stores plugin classes to a list
	    this.getPluginMap().putAll(this.fillCorePluginMenuAndButtons(this.getUnbbayesPluginContextHolder().getPluginManager(), point));
	    
	    // show dependency error if detected:
	    this.showPluginDependencyError();
	}
	
	/**
	 * Fills up the menu and tool tip buttons using {@link #getPluginManager()}.
	 * @param pluginManager : manager to use
	 * @param point: extension point to be read in order to fill menu and buttons.
	 * @return a map of connected and activated extensions. The keys are the module's IDs.
	 */
	protected Map<String,Class> fillCorePluginMenuAndButtons(PluginManager pluginManager, ExtensionPoint point) {
		
		Map<String,Class> ret = new HashMap<String, Class>();
		
		for (Extension ext : point.getConnectedExtensions()) {
            try {
            	PluginDescriptor descr = ext.getDeclaringPluginDescriptor();
                
            	pluginManager.activatePlugin(descr.getId());
    			
    			// extracting parameters
    			Parameter nameParam = ext.getParameter(PluginCore.PARAMETER_NAME);
    			Parameter classParam = ext.getParameter(PluginCore.PARAMETER_CLASS);
    			Parameter descriptionParam = ext.getParameter(PluginCore.PARAMETER_DESCRIPTION);
    			Parameter iconParam = ext.getParameter(PluginCore.PARAMETER_ICON);
    			Parameter builderParam = ext.getParameter(PluginCore.PARAMETER_BUILDER);
    			Parameter categoryParam = ext.getParameter(PluginCore.PARAMETER_CATEGORY);
    			
    			// extracting plugin class or builder clas
    			ClassLoader classLoader = pluginManager.getPluginClassLoader(descr);
                Class pluginOrBuilderCls = null;	// class for the plugin or its builder (UnBBayesModuleBuilder)
                if (builderParam != null) {
                	pluginOrBuilderCls = classLoader.loadClass(builderParam.valueAsString());
                } else {
                	pluginOrBuilderCls = classLoader.loadClass(classParam.valueAsString());
                }
    			
    			// filling tool bar's button
    			ImageIcon icon = null;
    			if (iconParam != null) {
    				URL iconUrl = pluginManager.getPluginClassLoader(ext.getDeclaringPluginDescriptor()).getResource(iconParam.valueAsString());
    				icon = (iconUrl != null) ? new ImageIcon(iconUrl) : null;
    			}
    			
    			// use default icon if the icon is not found or invalid
    			if (icon == null) {
    				icon = IconController.getInstance().getNewIcon();
    			}
    			
    			// instantiate the button
    			JButton button = new JButton(icon);
    			button.setToolTipText(descriptionParam.valueAsString());
    			
    			// extracting the module's name
    			String moduleName = "";
    			if (nameParam != null) {
    				moduleName = nameParam.valueAsString();
    			} else {
    				moduleName = getUnBBayesModuleNameByPluginClass(pluginOrBuilderCls);
    				if (moduleName == null) {
    					// no name was extracted... Use default...
						moduleName = "UnBBayesModule";
					}
    			}
    			
    			// filling menu item
    			JMenuItem menuItem = new JMenuItem(moduleName,icon);
    			menuItem.setToolTipText(descriptionParam.valueAsString());
    			
    			// creating action listener
    			ActionListener listener = new ActionListenerSupportingConstructorParam(pluginOrBuilderCls) {
    				public void actionPerformed(ActionEvent e) {
    					setCursor(new Cursor(Cursor.WAIT_CURSOR));
    					UnBBayesModule module = null;
    					try {
    						module = getUnBBayesModuleByPluginClass((Class)this.getParam());
    						addWindow(module);
    					} catch (Throwable t) {
    						setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    						throw new RuntimeException(t);
    					}
    					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    				}
    			};
    			
    			// adding action listener 
    			button.addActionListener(listener);
    			menuItem.addActionListener(listener);
    			
    			// adding menu item into menu, depending on the category of the module
    			List<JComponent> mappedElements = this.getModuleCategoryToComponentsMap().get(categoryParam.valueAsString());
    			if (mappedElements == null || mappedElements.isEmpty()) {
    				// if the mapping was empty because categoryParam.valueAsString() was not found, then use default
    				if (categoryParam.valueAsString() == null || (categoryParam.valueAsString().length() == 0)) {
    					mappedElements = new ArrayList<JComponent>();
    					mappedElements.add(this.getPluginMenu());	// use default plugin menu
    				} else {
    					// the module category was not mapped before. Create and Map it.
    					mappedElements = new ArrayList<JComponent>();
    					JMenu newMenu = new JMenu(categoryParam.valueAsString());	// create new menu using name
    					mappedElements.add(newMenu);
    					this.getMenu().add(newMenu);
    				}
    			}
    			
    			// flag that tells us if we should add a menu item into the default plugin menu.
    			boolean addToPluginMenuFlag = true;
    			
    			// flag that tell us if we should add a button into default plugin split button
    			boolean addToSplitButton = true;
    			
    			// add menuItem into mapped JComponent
    			for (JComponent comp : mappedElements) {
    				if (comp instanceof JMenu) {
						((JMenu)comp).add(menuItem);
						addToPluginMenuFlag = false;
						// OBS. a menu item can be added into only one menu
					} else if (comp instanceof SplitToggleButton) {
						SplitToggleButton spButton = ((SplitToggleButton)comp);
						// creating a menu item for a split button
	        			JMenuItem splitButtonMenuItem = new JMenuItem(moduleName,icon);
	        			splitButtonMenuItem.setToolTipText(descriptionParam.valueAsString());
	        			splitButtonMenuItem.addActionListener(new SplitButtonMenuActionListener(spButton, button));
	        			
	        			// adding the plugin button into split button instead of the tool bar itself
	        			spButton.add(button, splitButtonMenuItem);// the last loaded plugin will be at top
	        			spButton.repaint();		
						addToSplitButton = false;				
						
						// by storing the component and the container, we can remove them at "reload" operation
						this.getMapOfComponentsToBeRemovedAtPluginReload().put(splitButtonMenuItem, spButton);
    			     } else {
						// if we are not adding to a JMenu, add a button instead of menu item
						comp.add(button);
						addToSplitButton = false;
						
						// by storing the components and its container, we can remove them at "reload" operation
						this.getMapOfComponentsToBeRemovedAtPluginReload().put(button, comp);
					}
				}
    			
    			// also, fill plugin menu if flag is on
    			if (addToPluginMenuFlag) {
    				this.getPluginMenu().add(menuItem);
    			}
    			// fill split button if flag is on
    			if (addToSplitButton) {
    				// creating a menu item for split button, only if the flag is on
        			JMenuItem splitButtonMenuItem = new JMenuItem(moduleName,icon);
        			splitButtonMenuItem.setToolTipText(descriptionParam.valueAsString());
        			splitButtonMenuItem.addActionListener(new SplitButtonMenuActionListener(this.getPluginSplitButton(), button));
        			
        			// adding the plugin button into split button instead of the tool bar itself
        			this.getPluginSplitButton().add(button, splitButtonMenuItem); // the last loaded plugin will be at top
        			this.getPluginSplitButton().repaint();
    			}
    			
    			// filling the return
    			ret.put(moduleName, pluginOrBuilderCls);
			} catch (Throwable e) {
				e.printStackTrace();
				continue;
			} 
		}
		
		return ret;
	}
	
	/**
	 * Extracts the module's name from its class (the class can be a builder or the module itself).
	 * Usually, we use {@link UnBBayesModule#getModuleName()} or
	 * {@link UnBBayesModuleBuilder#getName()} to do so.
	 * @param pluginOrBuilderCls a class of {@link UnBBayesModule} or {@link UnBBayesModuleBuilder}
	 * @return name or null if not found
	 */
	protected String getUnBBayesModuleNameByPluginClass(
			Class pluginOrBuilderCls) {
		try {
			if (UnBBayesModuleBuilder.class.isAssignableFrom(pluginOrBuilderCls)) {
				UnBBayesModuleBuilder builder = (UnBBayesModuleBuilder) pluginOrBuilderCls.newInstance();
				return builder.getName();
			} else {
				return ((UnBBayesModule) pluginOrBuilderCls.newInstance()).getName();
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Use the parameter "clazz" in order to instantiate a new UnBBayesModule (in this case, "clazz"
	 * must provide a default constructor) or as a UnBBayesModuleBuilder (which by 
	 * {@link UnBBayesModuleBuilder#buildUnBBayesModule()} we get the proper UnBBayesModule).
	 * @param clazz : class extending {@link UnBBayesModule} or implementing {@link UnBBayesModuleBuilder}
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @see #addWindow(JInternalFrame)
	 */
	public UnBBayesModule getUnBBayesModuleByPluginClass(Class clazz) throws InstantiationException, IllegalAccessException {
		UnBBayesModule ret = null;
		if (clazz == null) {
			return ret;
		}
		if (UnBBayesModuleBuilder.class.isAssignableFrom(clazz)) {
			UnBBayesModuleBuilder builder = (UnBBayesModuleBuilder) clazz.newInstance();
			ret = builder.buildUnBBayesModule();
		} else {
			ret = (UnBBayesModule) clazz.newInstance();
		}
		
		return ret;
	}
	
	

	/**
	 * Call the method for creating the needed buttons and then create the tool
	 * bars and add the buttons to them and finally to the topPanel.
	 * TODO fix the help toolbar
	 */
	public void createToolBars() {

		createButtons();

		
		// create tool bars
		jtbNew = new JToolBar();
		jtbFile = new JToolBar();
		jtbView = new JToolBar();
		jtbTools = new JToolBar();
		jtbWindow = new JToolBar();
		jtbHelp = new JToolBar();
		
		// instantiate tool's Split button
		this.setToolsSplitButton(new SplitToggleButton());
		jtbTools.add(this.getToolsSplitButton());
		this.getToolsSplitButton().setToolTipText(resource.getString("toolsMenu"));

		// add their buttons
		jtbNew.add(newNet);
//		jtbNew.add(newMsbn);
//		jtbNew.add(newMebn);
//		jtbNew.add(newOobn);
		jtbFile.add(openNet);
		jtbFile.add(saveNet);
//		jtbTools.add(learn);
		jtbView.add(metal);
		jtbView.add(motif);
		jtbView.add(homeSystem);
		jtbWindow.add(cascade);
		jtbWindow.add(tile);
		jtbHelp.add(help);

		// add the tool bars to the topPanel
		topPanel.add(jtbNew);
		topPanel.add(jtbFile);
		topPanel.add(jtbView);
		topPanel.add(jtbTools);
		topPanel.add(jtbWindow);
		topPanel.add(jtbHelp);
		
	}
	
	/**
	 * This method will use map in order to remove all keys from its mapped object.
	 * In other words, if a button was once added into a jcomponent within map, the 
	 * button will be removed from the component and the map.
	 * @param map
	 */
	protected void removePluginButtonsFromOtherComponents(Map<JComponent, JComponent> map) {
		
		// stores removed keys
		List<JComponent> removedKeys = new ArrayList<JComponent>();

		// remove all module buttons once added to components
		for (JComponent key : map.keySet()) {
			JComponent comp = map.get(key);
			comp.remove(key);
			removedKeys.add(key);
		}
		
		// remove keys from the map
		for (JComponent key : removedKeys) {
			map.remove(key);
		}
	}


	/**
	 * Create the needed buttons and add their respectinve tool tip.
	 */
	public void createButtons() {

		// create the buttons
		newNet = new JButton(iconController.getNewBNIcon());
//		newMsbn = new JButton(iconController.getNewMSBNIcon());
//		newMebn = new JButton(iconController.getNewMEBNIcon());
//		newOobn = new JButton(iconController.getNewOOBNIcon());
		openNet = new JButton(iconController.getOpenIcon());
		saveNet = new JButton(iconController.getSaveIcon());
//		learn = new JButton(iconController.getLearningIcon());
		metal = new JButton(iconController.getMetalIcon());
		motif = new JButton(iconController.getMotifIcon());
		homeSystem = new JButton(iconController.getHomeIcon());
		tile = new JButton(iconController.getTileIcon());
		cascade = new JButton(iconController.getCascadeIcon());
		help = new JButton(iconController.getHelpIcon());

		// add their tool tip
		help.setToolTipText(resource.getString("helpToolTip"));
		newNet.setToolTipText(resource.getString("newToolTip"));
//		newMsbn.setToolTipText(resource.getString("newMsbnToolTip"));
//		newMebn.setToolTipText(resource.getString("newMebnToolTip"));
		openNet.setToolTipText(resource.getString("openToolTip"));
		saveNet.setToolTipText(resource.getString("saveToolTip"));
//		learn.setToolTipText(resource.getString("learningToolTip"));
		metal.setToolTipText(resource.getString("metalToolTip"));
		motif.setToolTipText(resource.getString("motifToolTip"));
		homeSystem.setToolTipText(resource.getString("homeSystemToolTip"));
		tile.setToolTipText(resource.getString("tileToolTip"));
		cascade.setToolTipText(resource.getString("cascadeToolTip"));
	}

	/**
	 * Method responsible for assigning ActionListeners to all buttons
	 * available.
	 */
	public void assignActionListeners() {

		newNet.addActionListener(alNewBN);
//		newMsbn.addActionListener(alNewMSBN);
//		newMebn.addActionListener(alNewMEBN);
//		newOobn.addActionListener(alNewOOBN);
		openNet.addActionListener(alOpen);
		saveNet.addActionListener(alSave);
		metal.addActionListener(alMetal);
		motif.addActionListener(alMotif);
		homeSystem.addActionListener(alHomeSystem);
//		learn.addActionListener(alLearn);
		tile.addActionListener(alTile);
		cascade.addActionListener(alCascade);
		help.addActionListener(alHelp);

	}

	public static UnBBayesFrame getIUnBBayes() {
		return UnBBayesFrame.singleton;
	}
	
	public class ListenerCloserFrame implements WindowListener{
		  /**
	     * Invoked the first time a window is made visible.
	     */
	    public void windowOpened(WindowEvent e){
	    	
	    }

	    /**
	     * Invoked when the user attempts to close the window
	     * from the window's system menu.
	     */
	    public void windowClosing(WindowEvent e){
	    	try {
				controller.saveConfigurations();
			} catch (IOException e1) {
				//invisible for the user. 
				e1.printStackTrace();
			} 
	    }

	    /**
	     * Invoked when a window has been closed as the result
	     * of calling dispose on the window.
	     */
	    public void windowClosed(WindowEvent e){
	    	
	    }

	    /**
	     * Invoked when a window is changed from a normal to a
	     * minimized state. For many platforms, a minimized window 
	     * is displayed as the icon specified in the window's 
	     * iconImage property.
	     * @see java.awt.Frame#setIconImage
	     */
	    public void windowIconified(WindowEvent e){
	    	
	    }

	    /**
	     * Invoked when a window is changed from a minimized
	     * to a normal state.
	     */
	    public void windowDeiconified(WindowEvent e){
	    	
	    }

	    /**
	     * Invoked when the Window is set to be the active Window. Only a Frame or
	     * a Dialog can be the active Window. The native windowing system may
	     * denote the active Window or its children with special decorations, such
	     * as a highlighted title bar. The active Window is always either the
	     * focused Window, or the first Frame or Dialog that is an owner of the
	     * focused Window.
	     */
	    public void windowActivated(WindowEvent e){
	    	
	    }

	    /**
	     * Invoked when a Window is no longer the active Window. Only a Frame or a
	     * Dialog can be the active Window. The native windowing system may denote
	     * the active Window or its children with special decorations, such as a
	     * highlighted title bar. The active Window is always either the focused
	     * Window, or the first Frame or Dialog that is an owner of the focused
	     * Window.
	     */
	    public void windowDeactivated(WindowEvent e){
	    	
	    }
	}

	/**
	 * @return the pluginToolBar
	 */
	public JToolBar getPluginToolBar() {
		return pluginToolBar;
	}

	/**
	 * @param pluginToolBar the pluginToolBar to set
	 */
	public void setPluginToolBar(JToolBar pluginToolBar) {
		this.pluginToolBar = pluginToolBar;
	}

	/**
	 * @return the pluginMenu
	 */
	public JMenu getPluginMenu() {
		return pluginMenu;
	}

	/**
	 * @param pluginMenu the pluginMenu to set
	 */
	public void setPluginMenu(JMenu pluginMenu) {
		this.pluginMenu = pluginMenu;
	}


//	/**
//	 * 
//	 * The directory where UnBBayes will search for plugins.
//	 * @param pluginDirectory the pluginDirectory to set
//	 */
//	public void setPluginDirectory(String pluginDirectory) {
//		this.pluginDirectory = pluginDirectory;
//	}

	/**
	 * The main extension point of the core plugin (Module).
	 * @return the pluginModuleExtensionPoint
	 */
	public String getPluginModuleExtensionPoint() {
		return pluginModuleExtensionPoint;
	}

	/**
	 * The main extension point of the core plugin (Module).
	 * @param pluginModuleExtensionPoint the module's extension ID to set
	 */
	public void setPluginModuleExtensionPoint(String pluginModuleExtensionPoint) {
		this.pluginModuleExtensionPoint = pluginModuleExtensionPoint;
	}

	
	/**
	 * Inner class for action listener, supporting constructor's param.
	 * @author Shou Matsumoto
	 *
	 */
	protected abstract class ActionListenerSupportingConstructorParam implements ActionListener {
		private Object param;
		public ActionListenerSupportingConstructorParam(Object param) {
			this.param = param;
		}
		/**
		 * @return the param
		 */
		public Object getParam() {
			return param;
		}
		/**
		 * @param param the param to set
		 */
		public void setParam(Object param) {
			this.param = param;
		}
	}

	/**
	 * Connected, loaded and activated extensions.
	 * This map associates an ID to a {@link UnBBayesModule} or {@link UnBBayesModuleBuilder}
	 * @return the pluginMap
	 */
	public Map<String,Class> getPluginMap() {
		if (this.pluginMap == null) {
			this.pluginMap = new HashMap<String,Class>();
		}
		return pluginMap;
	}

	/**
	 * Connected, loaded and activated extensions.
	 * This map associates an ID to a {@link UnBBayesModule} or {@link UnBBayesModuleBuilder}
	 * @param pluginMap the pluginMap to set
	 */
	public void setPluginList(Map<String,Class> pluginMap) {
		this.pluginMap = pluginMap;
	}

	/**
	 * @return the menu
	 */
	public JMenuBar getMenu() {
		return menu;
	}

	/**
	 * @param menu the menu to set
	 */
	public void setMenu(JMenuBar menu) {
		this.menu = menu;
	}

	/**
	 * @return the reloadPluginsMenuItem
	 */
	public JMenuItem getReloadPluginsItem() {
		return reloadPluginsMenuItem;
	}

	/**
	 * @param reloadPluginsMenuItem the reloadPluginsMenuItem to set
	 */
	public void setReloadPluginsItem(JMenuItem reloadPluginsItem) {
		this.reloadPluginsMenuItem = reloadPluginsItem;
	}
	
	/**
	 * Obtains all modules that can treat a specific file (it looks mainly at file extension)
	 * at loading time. We do not need this method at saving time since {@link IPersistenceAwareWindow#getIO()}
	 * can do the job by using {@link BaseIO#save(File, unbbayes.prs.Graph)}.
	 * @param file : file to be loaded.
	 * @param plugins : a map of classes extending UnBBayesModule. The keys are the plugin/module IDs.
	 * @return a map of instantiated UnBBayesModule, with setVisible(false). The keys are the plugin/module IDs.
	 */
	public Map<String, UnBBayesModule> getUnBBayesModulesByFile(File file, Map<String,Class> plugins) {
		
		Map<String,UnBBayesModule> ret = new HashMap<String,UnBBayesModule>();
	
		if (plugins != null) {
			for (String id : plugins.keySet()) {
				try {
					UnBBayesModule module = this.getUnBBayesModuleByPluginClass(plugins.get(id));
					module.setVisible(false);
					if (module.getIO() != null && module.getIO().supports(file, true)) {
						ret.put(id,module);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return ret;
	}
	
	/**
	 * Obtains all file extensions (with no dots) that a list of plugins can handle.
	 * @param plugins : a list of classes extending UnBBayesModule or its builder (UnBBayesModuleBuilder)
	 * @return array of string containing all file extensions supported currently by the modules.
	 * @see BaseIO#getSupportedFileExtensions(boolean)
	 */
	public String[] getAllSupportedFileExtensions(Collection<Class> plugins) {
		Set<String> ret = new HashSet<String>();
		
		if (plugins != null) {
			for (Class clazz : plugins) {
				try {
					UnBBayesModule module = this.getUnBBayesModuleByPluginClass(clazz);
					module.setVisible(false);
					String[] extensions = module.getSupportedFileExtensions(true);
					if (extensions != null) {
						for (String ext : extensions) {
							if (ext != null && (ext.trim().length() > 0)) {
								ret.add(ext);
							}
						}
					}
					module.dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return ret.toArray(new String[ret.size()]);
	}

	/**
	 * A listener for opening an existing file using plugins/modules.
	 * Can be used in 2 ways: 
	 * 		1 - a file is not specified (in this case a file chooser will be shown to user);
	 * 		2 - a file is specified (in this case the listener will just open the file)
	 * @author Shou Matsumoto
	 *
	 */
	public class OpenFileUsingModulesActionListener implements ActionListener {
		private File file = null; 
		
		/**
		 * Create a listener in order to open a file treating modules' conflicts.
		 * @param file : if set to null, a file chooser will be opened to select
		 * a new file.
		 */
		public OpenFileUsingModulesActionListener(File file){
			this.file = file; 
		}
		
		/**
		 * Shows a file chooser to the user, using filters provided
		 * by plugins/modules.
		 * @return the selected file. Null if no file was selected.
		 * @see JFileChooser#showOpenDialog(java.awt.Component)
		 */
		public File showFileChooser() {
			chooser = new JFileChooser(fileHistoryController.getCurrentDirectory());
			chooser.setDialogTitle(resource.getString("openTitle")); 
			chooser.setMultiSelectionEnabled(false);
			chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

			chooser.setFileView(new FileIcon(UnBBayesFrame.this));
			
			// fills the file filter for each plugin/module
			for (Class clazz : getPluginMap().values()) {
				UnBBayesModule mod = null;
				try {
					mod = getUnBBayesModuleByPluginClass(clazz);
					if (mod == null) {
						continue;
					}
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
				
				chooser.addChoosableFileFilter(
						new SimpleFileFilter(
								mod.getSupportedFileExtensions(true),
								mod.getSupportedFilesDescription(true)));
				
				mod.dispose();
			}
			
			// filter for all supported files
			chooser.addChoosableFileFilter(new SimpleFileFilter(
					getAllSupportedFileExtensions(getPluginMap().values()),
					resource.getString("allNetFileFilter")));
			
			int option = chooser.showOpenDialog(UnBBayesFrame.this);
			if (option == JFileChooser.APPROVE_OPTION) {
				fileHistoryController.setCurrentDirectory(chooser
						.getCurrentDirectory());
				chooser.setVisible(false);
				chooser.setEnabled(false);
				return chooser.getSelectedFile();
			}
			
			chooser.setVisible(false);
			chooser.setEnabled(false);
			return null;
		}
		
		/**
		 * If a file is specified, opens it using an appropriate UnBBayesModule.
		 * I not, a file chooser will be displayed to user and that file will
		 * be opened using an appropriate UnBBayesModule.
		 */
		public void actionPerformed(ActionEvent ae) {
			setCursor(new Cursor(Cursor.WAIT_CURSOR));
			
			// use a default file or choose one if no default file was provided
			File currentFile = this.file;
			if (currentFile == null) {
				currentFile = this.showFileChooser();
			}

			if (currentFile != null) {
				// create a instance of File
				UnBBayesFrame.this.repaint(); 
				
			    // load module that can handle the file. Map from id to module.
			    Map<String, UnBBayesModule> moduleMap = getUnBBayesModulesByFile(currentFile, getPluginMap());
			    
			    // choose the best module to load the file
			    UnBBayesModule selectedModule = null;
			    if (moduleMap.size() == 1) {
			    	// if there is only one module handling the file, choose it
			    	selectedModule = moduleMap.values().iterator().next();
			    } else if (moduleMap.size() > 1) {
			    	// if there are more than one module to handle the file, the user shall choose one
			    	String[] possibleValues = moduleMap.keySet().toArray(new String[moduleMap.keySet().size()]);
			    	Object selectedValue = JOptionPane.showInputDialog(
			    			UnBBayesFrame.this, 
			    			resource.getString("moduleConflictMessage"), 
			    			resource.getString("moduleConflict"),
			    			JOptionPane.INFORMATION_MESSAGE, 
			    			null,
			    			possibleValues, 
			    			possibleValues[0]);
			    	if (selectedValue != null) {
			    		selectedModule = moduleMap.get(selectedValue.toString());
			    	} else {
			    		// user appears to have cancelled
			    		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			    		
				    	return;
			    	}
			    }
			    
			    // if we reached this code and no module was selected, report error and stop
			    if (selectedModule == null) {
			    	JOptionPane.showMessageDialog(UnBBayesFrame.this, 
			    			resource.getString("unsupportedGraphFormat"), 
							resource.getString("loadNetException"), 
							JOptionPane.ERROR_MESSAGE);
			    	setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			    	return;
			    }
			    
				try {
					// loading network
					UnBBayesModule mod = controller.loadNet(currentFile, selectedModule);
					if (mod != null) {
						addWindow(mod);
					}
				} catch (Throwable e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(UnBBayesFrame.this, 
							e.getMessage(), 
							resource.getString("loadNetException"), 
							JOptionPane.ERROR_MESSAGE); 
				} 
				
			}
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
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
	 * This is the split button containing buttons to start plugin-based modules.
	 * This is usually inserted inside {@link #getPluginToolBar()}
	 * @return the pluginSplitButton
	 */
	public SplitToggleButton getPluginSplitButton() {
		return pluginSplitButton;
	}

	/**
	 * This is the split button containing buttons to start plugin-based modules.
	 * This is usually inserted inside {@link #getPluginToolBar()}
	 * @param pluginSplitButton the pluginSplitButton to set
	 */
	public void setPluginSplitButton(SplitToggleButton pluginSplitButton) {
		this.pluginSplitButton = pluginSplitButton;
	}
	
	/**
	 * This is just an action listener for menu items inserted within the {@link UnBBayesFrame#getPluginSplitButton()}
	 * or {@link UnBBayesFrame#getToolsSplitButton()}.
	 * @author Shou Matsumoto
	 *
	 */
	protected class SplitButtonMenuActionListener implements ActionListener{
		
		// the button to be set as the split button's main button, after this action listener is called
		private JButton mainButton;
		// the split button to use this action listener
		private SplitToggleButton splitButton;
		
		/**
		 * Constructor setting the button to be set as main button at {@link #actionPerformed(ActionEvent)}
		 * @param mainButton : the button to be set as the split button's main button, 
		 * after this action listener is called
		 */
		public SplitButtonMenuActionListener (SplitToggleButton splitButton, JButton mainButton) {
			this.mainButton = mainButton;
			this.splitButton = splitButton;
		}
		
		/*
		 * (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent e) {
			this.splitButton.setMainButton(this.mainButton);
			this.splitButton.getMenu().setVisible(false);
			this.splitButton.updateUI();
			this.splitButton.repaint();
			
			// press the button
			this.splitButton.getMainButton().doClick();
		}
		
	}

	/**
	 * This is a sub menu where functionalities like "new BN" or "new MSBN" resides.
	 * @return newMenu
	 */
	public JMenu getNewMenu() {
		return newMenu;
	}

	/**
	 * This is a menu where tools (e.g. learning modules) resides.
	 * @return toolsMenu
	 */
	public JMenu getToolsMenu() {
		return toolsMenu;
	}

	/**
	 * This is a sub menu where sampling modules resides.
	 * @return samplingMenu
	 */
	public JMenu getSamplingMenu() {
		return samplingMenu;
	}

	/**
	 * @return the jtbFile
	 */
	public JToolBar getJtbFile() {
		return jtbFile;
	}

	/**
	 * It maps a module category into a menu
	 * @return the moduleCategoryToComponentsMap
	 */
	public Map<String, List<JComponent>> getModuleCategoryToComponentsMap() {
		return moduleCategoryToComponentsMap;
	}

	/**
	 * It maps a module category into a menu
	 * @param moduleCategoryToComponentsMap the moduleCategoryToJMenuMap to set
	 */
	public void setModuleCategoryToComponentsMap(
			Map<String, List<JComponent>> moduleCategoryToComponentsMap) {
		this.moduleCategoryToComponentsMap = moduleCategoryToComponentsMap;
	}

	/**
	 * This separator separates the "reload plugin" option from other plugins
	 * @return the pluginMenuSeparator
	 */
	public Separator getPluginMenuSeparator() {
		return pluginMenuSeparator;
	}

	/**
	 * This separator separates the "reload plugin" option from other plugins
	 * @param pluginMenuSeparator the pluginMenuSeparator to set
	 */
	public void setPluginMenuSeparator(Separator pluginMenuSeparator) {
		this.pluginMenuSeparator = pluginMenuSeparator;
	}

	/**
	 * It stores all modules' components which was not added into
	 * the default plugin split button. They need special attention, since
	 * they are usually situated at other JTB, and must be removed at plugin reload.
	 * The buttons are mapped to the component where they were added.
	 * @return the mapOfComponentsToBeRemovedAtPluginReload
	 */
	public Map<JComponent, JComponent> getMapOfComponentsToBeRemovedAtPluginReload() {
		return mapOfComponentsToBeRemovedAtPluginReload;
	}

	/**
	 * It stores all modules' components which was not added into
	 * the default plugin split button. They need special attention, since
	 * they are usually situated at other JTB, and must be removed at plugin reload.
	 * The buttons are mapped to the component where they were added.
	 * @param mapOfComponentsToBeRemovedAtPluginReload the mapOfComponentsToBeRemovedAtPluginReload to set
	 * @see #getJtbFile()
	 */
	public void setMapOfComponentsToBeRemovedAtPluginReload(
			Map<JComponent, JComponent> listOfBNModulesAtFileJTB) {
		this.mapOfComponentsToBeRemovedAtPluginReload = listOfBNModulesAtFileJTB;
	}

	/**
	 * @return the jtbNew
	 */
	public JToolBar getJtbNew() {
		return jtbNew;
	}

	/**
	 * @param jtbNew the jtbNew to set
	 */
	public void setJtbNew(JToolBar jtbNew) {
		this.jtbNew = jtbNew;
	}

	/**
	 * @return the controller
	 */
	public MainController getController() {
		return controller;
	}

	/**
	 * @param controller the controller to set
	 */
	public void setController(MainController controller) {
		this.controller = controller;
	}

	/**
	 * @return the desktop
	 */
	public MDIDesktopPane getDesktop() {
		return desktop;
	}

	/**
	 * @param desktop the desktop to set
	 */
	public void setDesktop(MDIDesktopPane desktop) {
		this.desktop = desktop;
	}

	/**
	 * @return the jtbTools
	 */
	public JToolBar getJtbTools() {
		return jtbTools;
	}

	/**
	 * @param jtbTools the jtbTools to set
	 */
	public void setJtbTools(JToolBar jtbTools) {
		this.jtbTools = jtbTools;
	}

	/**
	 * @return the toolsSplitButton
	 */
	public SplitToggleButton getToolsSplitButton() {
		return toolsSplitButton;
	}

	/**
	 * @param toolsSplitButton the toolsSplitButton to set
	 */
	public void setToolsSplitButton(SplitToggleButton toolsSplitButton) {
		this.toolsSplitButton = toolsSplitButton;
	}

}