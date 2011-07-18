
package unbbayes.util.extension;

import java.io.File;
import java.io.IOException;

import javax.swing.JInternalFrame;

import unbbayes.gui.IPersistenceAwareWindow;
import unbbayes.gui.UnBBayesFrame;
import unbbayes.io.BaseIO;

/**
 * Plugins for UnBBayes core is expected to extend this class.
 * @author Shou Matsumoto
 * @version 16-10-2009
 *
 */
public abstract class UnBBayesModule extends JInternalFrame implements
		IPersistenceAwareWindow {
	
	private String moduleID = "UnBBayesModule";
	
	private UnBBayesFrame unbbayesFrame;

	/**
	 * It is equal to super("Plugin", true, true, true, true);
	 * @see JInternalFrame
	 * 
	 */
	public UnBBayesModule() {
		super("Plugin", true, true, true, true);
		this.setVisible(false);
	}
	
	/**
	 * @param title
	 * @see JInternalFrame
	 */
	public UnBBayesModule(String title) {
		super(title, true, true, true, true);
	}
	



	/* (non-Javadoc)
	 * @see unbbayes.gui.IPersistenceAwareWindow#getInternalFrame()
	 */
	public JInternalFrame getInternalFrame() {
		return this;
	}

	/* (non-Javadoc)
	 * @see unbbayes.gui.IPersistenceAwareWindow#getSavingMessage()
	 */
	public String getSavingMessage() {
		return "Save";
	}

	
	
	/**
	 * @see unbbayes.gui.IPersistenceAwareWindow#getSupportedFileExtensions(boolean)
	 * @deprecated use {@link BaseIO#getSupportedFileExtensions(boolean)} from 
	 * {@link IPersistenceAwareWindow#getIO()} instead.
	 */
	public String[] getSupportedFileExtensions(boolean isLoadOnly) {
		if (this.getIO() == null) {
			return new String[0];
		}
		return this.getIO().getSupportedFileExtensions(isLoadOnly);
	}

	/** (non-Javadoc)
	 * @see unbbayes.gui.IPersistenceAwareWindow#getSupportedFilesDescription(boolean)
	 * @deprecated use {@link BaseIO##getSupportedFilesDescription(boolean)} from 
	 * {@link IPersistenceAwareWindow#getIO()} instead.
	 */
	public String getSupportedFilesDescription(boolean isLoadOnly) {
		if (this.getIO() == null) {
			return "";
		}
		return this.getIO().getSupportedFilesDescription(isLoadOnly);
	}

	/**
	 * Obtains the name of this module.
	 * @return the name of this module.
	 */
	public abstract String getModuleName();
	
	/**
	 * Loads a file into this window.
	 * Please, note that the file must be compatible to this module. File compatibility is
	 * checked by 2 possible ways: by IPersistenceAwareWindow#getSupportedFileExtensions() and/or
	 * by getting the I/O class (IPersistenceAwareWindow#getIO()) and 
	 * checking directly using BaseIO#supportsExtension(String).
	 * @param file : the file to be opened
	 * @return the UnBBayesModule which has the new opened graph. If null, no internal frame 
	 * will be displayed to user.
	 * @throws IOException
	 * @see IPersistenceAwareWindow#getSupportedFileExtensions()
	 * @see IPersistenceAwareWindow#getIO()
	 * @see BaseIO#supportsExtension(String)
	 */
	public abstract UnBBayesModule openFile(File file) throws java.io.IOException;

	/**
	 * @return the moduleID
	 */
	public String getModuleID() {
		return moduleID;
	}

	/**
	 * @param moduleID the moduleID to set
	 */
	public void setModuleID(String moduleID) {
		this.moduleID = moduleID;
	}

	/**
	 * This is the top frame of UnBBayes, where all inner frames resides.
	 * By accessing this, you can virtually control everything of UnBBayes' GUI.
	 * @return the unbbayesFrame
	 */
	public UnBBayesFrame getUnbbayesFrame() {
		return unbbayesFrame;
	}

	/**
	 * This is the top frame of UnBBayes, where all inner frames resides.
	 * By accessing this, you can virtually control everything of UnBBayes' GUI.
	 * @param unbbayesFrame the unbbayesFrame to set
	 */
	public void setUnbbayesFrame(UnBBayesFrame unbbayesFrame) {
		this.unbbayesFrame = unbbayesFrame;
	}

	/* (non-Javadoc)
	 * @see java.awt.Component#setName(java.lang.String)
	 */
	public void setName(String name) {
		super.setName(name);
		this.setTitle(name);
	}
	
}
