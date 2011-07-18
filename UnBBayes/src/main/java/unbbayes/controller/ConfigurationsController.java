package unbbayes.controller;

import java.io.File;

import unbbayes.gui.Configurations;

/**
 * Manages recently opened files.
 * @author Laecio
 */
public class ConfigurationsController {

	private boolean altered;
	private boolean fileOpenedSucessfull; 
	private Configurations configurations; 

	private static final int MAX_RECENT_FILE_LIST = 10;  
	
	private String fileConfigurations = "config.cfg"; 
	
	private static ConfigurationsController singleton; 
	
	private ConfigurationsController(){
		configurations = new Configurations(); 
	}
	
	public static ConfigurationsController getInstance(){
		if(singleton == null){
			singleton = new ConfigurationsController(); 
		}
		return singleton; 
	}
	
	public String getFileConfigurationsPath(){
		return fileConfigurations; 
	}
	
	public boolean isAltered() {
		return altered;
	}

	public void setAltered(boolean altered) {
		this.altered = altered;
	}

	public Configurations getConfigurations() {
		return configurations;
	}

	public void setConfigurations(Configurations configurations) {
		this.configurations = configurations;
	}

	public boolean isFileOpenedSucessfull() {
		return fileOpenedSucessfull;
	}

	public void setFileOpenedSucessfull(boolean fileOpenedSucessfull) {
		this.fileOpenedSucessfull = fileOpenedSucessfull;
	} 
	
	public void addFileToListRecentFiles(File file){
		
		this.configurations.addPreviewFile(file.toString()); 
		
	}
	
}
