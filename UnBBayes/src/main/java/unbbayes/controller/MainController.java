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
package unbbayes.controller;

import java.awt.Cursor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.xml.bind.JAXBException;

import unbbayes.controller.exception.InvalidFileNameException;
import unbbayes.controller.exception.NoObjectToBeSavedException;
import unbbayes.gui.Configurations;
import unbbayes.gui.NetworkWindow;
import unbbayes.gui.SplashScreen;
import unbbayes.gui.UnBBayesFrame;
import unbbayes.io.BaseIO;
import unbbayes.io.FileExtensionIODelegator;
import unbbayes.io.configurations.ConfigurationsIO;
import unbbayes.io.configurations.ConfigurationsIOInputStream;
import unbbayes.io.exception.UBIOException;
import unbbayes.prs.Edge;
import unbbayes.prs.Graph;
import unbbayes.prs.Node;
import unbbayes.prs.bn.ProbabilisticNetwork;
import unbbayes.util.Debug;
import unbbayes.util.extension.UnBBayesModule;
import unbbayes.util.extension.builder.NamedWindowBuilder;
import unbbayes.util.extension.builder.NetworkWindowBuilder;


/**
 *  This class is responsible for creating, loading and saving networks supported by UnBBayes.
 *
 * @author     Rommel Novaes Carvalho
 * @author     Michael S. Onishi
 * @created    June, 27th, 2001
 * @version    1.5 2006/09/14
 * 
 * 
 */
public class MainController {
	
	private UnBBayesFrame screen;
	
	private static boolean PRE_LOAD_PROTEGE; 
	private static boolean PRE_LOAD_POWERLOOM; 
	
	
	/** Load resource file from this package */
//	private static ResourceBundle resource = ResourceBundle.getBundle(
//			unbbayes.controller.resources.ControllerResources.class.getName());
	private static ResourceBundle resource = unbbayes.util.ResourceController.newInstance().getBundle(
			unbbayes.controller.resources.ControllerResources.class.getName());
	
	
	/**
	 *  Contructs the main controller with the UnBBayes main frame.
	 */
	public MainController() {
//		eagleLoader(); 
		
		try {
			loadConfigurations();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		screen = new UnBBayesFrame(this);
	}
	
	/**
	 * Load the configuration file and setting the configurations object in the 
	 * ConfigurationsController. 
	 * 
	 * @throws IOException Exception try to read the configurations file
	 */
	public void loadConfigurations() throws IOException{
		ConfigurationsController configController = ConfigurationsController.getInstance(); 
		ConfigurationsIO configurationsIO = new ConfigurationsIOInputStream(); 
		
		try {
			Configurations configurations = configurationsIO.load(new File(configController.getFileConfigurationsPath()));
			configController.setConfigurations(configurations); 
			configController.setFileOpenedSucessfull(true); 
		} catch (IOException e) { 
			configController.setFileOpenedSucessfull(false);
			throw e; 
		}
	}
	
	public void saveConfigurations() throws IOException{
		ConfigurationsController configController = ConfigurationsController.getInstance(); 
		ConfigurationsIO configurationsIO = new ConfigurationsIOInputStream(); 
		configurationsIO.save(new File(configController.getFileConfigurationsPath()), configController.getConfigurations());
	}

	/**
	 * Pre-loading of api's for a better performance
	 */
	private void eagleLoader(){
		
		SplashScreen splashScreen = new SplashScreen(); 
		
		splashScreen.pack(); 
		splashScreen.setVisible(true); 
		
		initializeLoadConfigurations(); 
		
		Debug.println("Init loader Protege"); 
		
		// Do not eager load MEBN content (which is plugin content)
//		//load Protege
//		if(PRE_LOAD_PROTEGE){
//		   ProtegeOWL.createJenaOWLModel();
//		}
//		
//		Debug.println("Init loader Powerloom"); 
//		
//		//load Powerloom
//		if(PRE_LOAD_POWERLOOM){
//		   PLI.initialize();
//		}
		
		Debug.println("Finish loader"); 
		
		splashScreen.dispose(); 
	}
	
	/**
	 * Initialize the load configurations of the plugins used by the UnBayes. 
	 * 
	 * TODO: This configurations will be in a external config file. 
	 */
	private void initializeLoadConfigurations(){
		PRE_LOAD_POWERLOOM = true; 
		PRE_LOAD_PROTEGE = true; 
	}
	
	
	
	/**
	 * This method is responsible for creating a new probabilistic network.
	 *
	 */
	public void newPN() {
		NamedWindowBuilder builder = new NetworkWindowBuilder();
		if (builder != null) {
			builder.setName(resource.getString("NewPNName"));
			if (screen != null) {
				UnBBayesModule module = builder.buildUnBBayesModule();
				screen.addWindow(module);
			}
		}
	}
	
	
	
	
	/**
	 *  Saves the probabilistic network in both .net and .xml format, depending
	 *  on the file's extension.
	 *
	 * @param file The file where to save the network.
	 * @param moduleToUse : what module/plugin we shall use in order to store the file
	 * @throws JAXBException 
	 * @throws IOException : it can throw {@link FileExtensionIODelegator.MoreThanOneCompatibleIOException}
	 * if the module uses {@link FileExtensionIODelegator} and when 2 or more IO class can manage the same file.
	 * @throws FileNotFoundException 
	 */
	public boolean saveNet(File file, UnBBayesModule moduleToUse) throws NoObjectToBeSavedException, 
	                    InvalidFileNameException, FileNotFoundException, 
	                    IOException, Exception{
		
		try {
			if (!moduleToUse.getIO().supports(file, false)) {
				throw new InvalidFileNameException(this.resource.getString("cannotHandleFileFormat"));
			}
			moduleToUse.getIO().save(file, moduleToUse.getPersistingGraph());
		} catch (NullPointerException e) {
			throw new NoObjectToBeSavedException(this.resource.getString("cannotHandleFileFormat"),e);
		} 
		
		// OBS. FileExtensionIODelegator may throw MoreThanOneCompatibleIOException. 
		// In such case, UnBBayesFrame will retry using saveNet(File file, BaseIO ioToUse, Graph graphToSave)
		
		// OBS. FileExtensionIODelegator.MoreThanOneCompatibleIOException is managed
		// differently by load and save. This is because load is done by the module and the
		// save is done by IO (on load, we are not sure what module to use, but on save we
		// are sure that we should use the currently active module/window).
		// thats why MoreThanOneCompatibleIOException must be treated by modules on load,
		// but treated by UnBBayesFrame on save.
		
		return true;
		
	}
	
	/**
	 * Saves the probabilistic network using the given IO class.
	 * 
	 * @param file The file where to save the network.
	 * @param ioToUse : this is the single IO class to be used
	 * @param graphToSave
	 * @throws IOException
	 */
	public boolean saveNet(File file, BaseIO ioToUse, Graph graphToSave) throws IOException {

		try {
			if (!ioToUse.supports(file, false)) {
				throw new IOException(this.resource.getString("cannotHandleFileFormat"));
			}
			ioToUse.save(file, graphToSave);
		} catch (NullPointerException e) {
			throw new UBIOException(this.resource.getString("cannotHandleFileFormat"),e);
		} 
		
		return true;
	
	}
	
	/**
	 *  Loads the probabilistic network from both .net and .xml format, depending
	 *  on the file's extension
	 * @param file : file to load
	 * @param moduleToUse : the module/plugin to use in order to load file.
	 * @return the new created module filled the network described by file
	 * @throws IOException 
	 */
	public UnBBayesModule loadNet(final File file, UnBBayesModule moduleToUse) throws IOException {
		
		UnBBayesModule mod = null;
		mod = moduleToUse.openFile(file);
		
		// OBS. FileExtensionIODelegator.MoreThanOneCompatibleIOException is managed
		// differently by load and save. This is because load is done by the module and the
		// save is done by IO (on load, we are not sure what module to use, but on save we
		// are sure that we should use the currently active module/window).
		// Thats why MoreThanOneCompatibleIOException must be treated by modules on load,
		// but treated by UnBBayesFrame on save.
		
		return mod;
		
	}
	
	
	/**
	 * Method responsible for creating a network based on its variables.
	 *
	 * @param nodeList List of nodes to create the network.
	 * @return The probabilistic network created.
	 */
	public ProbabilisticNetwork makeProbabilisticNetwork(ArrayList<Node> nodeList) {
		screen.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		ProbabilisticNetwork net = new ProbabilisticNetwork("learned net");
		Node noFilho = null;
		Node noPai = null;
		Edge arcoAux = null;
		Node aux;
		boolean direction = true;
		for (int i = 0; i < nodeList.size(); i++) {
			noFilho = nodeList.get(i);
			net.addNode(noFilho);
			for (int j = 0; j < noFilho.getParents().size(); j++) {
				noPai = (Node)noFilho.getParents().get(j);
				noPai.getChildren().add(noFilho);
				arcoAux = new Edge(noPai, noFilho);
				for(int k = 0 ; k < noPai.getParents().size() && direction; k++){
					aux = (Node)noPai.getParents().get(k);
					if(aux == noFilho){
						noPai.getParents().remove(k);
						direction = false;
					}                      		
				}                 
				arcoAux = new Edge(noPai, noFilho);                
				arcoAux.setDirection(direction);                	
				direction = true;
				net.getEdges().add(arcoAux);
			}
		}        		
		return net;
	}
	
	/**
	 * Shows the given probabilistic network in edition or compilation mode.
	 *  
	 * @param net
	 */
	public void showProbabilisticNetwork(ProbabilisticNetwork net){
		NetworkWindow netWindow = new NetworkWindow(net);
		if (! netWindow.getNetworkController().compileNetwork()) {
			netWindow.changeToPNEditionPane();            
			
		} else{
			netWindow.changeToPNCompilationPane();		
		}		
		screen.addWindow(netWindow);
		screen.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));    	
	}

	
	
}

