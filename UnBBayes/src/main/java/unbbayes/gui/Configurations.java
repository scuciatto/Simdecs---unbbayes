package unbbayes.gui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Configurations implements Serializable{

	/**
	 * Number of the version of the configurations... Be carefull
	 */
	private static final long serialVersionUID = 1L;

	//Eagle Loader
	private boolean eagleLoaderEnable; 
	
	private boolean preLoaderPowerloom; 
	private boolean preloaderProtege; 

	private static final int MAX_RECENT_FILE_LIST = 10;  
	
	
	//Files controller
	private List<String> previewOpenFiles; 

	public Configurations(){
	}

	public boolean isEagleLoaderEnable() {
		return eagleLoaderEnable;
	}

	public void setEagleLoaderEnable(boolean eagleLoaderEnable) {
		this.eagleLoaderEnable = eagleLoaderEnable;
	}

	public boolean isPreLoaderPowerloom() {
		return preLoaderPowerloom;
	}

	public void setPreLoaderPowerloom(boolean preLoaderPowerloom) {
		this.preLoaderPowerloom = preLoaderPowerloom;
	}

	public boolean isPreloaderProtege() {
		return preloaderProtege;
	}

	public void setPreloaderProtege(boolean preloaderProtege) {
		this.preloaderProtege = preloaderProtege;
	}

	public List<String> getPreviewOpenFiles() {
		if(previewOpenFiles==null){
		    previewOpenFiles = new ArrayList();
		}
		return previewOpenFiles; 
	}
	
	public void addPreviewFile(String name){
		
		if(!getPreviewOpenFiles().contains(name)){
			if(getPreviewOpenFiles().size() == MAX_RECENT_FILE_LIST){
				getPreviewOpenFiles().remove(0); 
			}	
			getPreviewOpenFiles().add(name); 
		}
	}

	public void setPreviewOpenFiles(List<String> previewOpenFiles) {
		this.previewOpenFiles = previewOpenFiles;
	}
	
}
