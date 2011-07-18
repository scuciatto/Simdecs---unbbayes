/**
 * 
 */
package unbbayes.gui;

import javax.swing.JInternalFrame;

import unbbayes.io.BaseIO;
import unbbayes.prs.Graph;

/**
 * 
 * Interface for JInternalWindow's subclasses (like NetworkWindow) 
 * which are aware of what file extension is to be used and what extensions should be used
 * for file chooser's filter.
 * 
 * @author Shou Matsumoto
 *
 * @version 1.2 : changed this interface in order to represent JInternalWindows which are aware of 
 * persistence level implementation.
 *
 */
public interface IPersistenceAwareWindow {
	/**
	 * Obtains an array of file extensions supported by this network window.
	 * The file extensions should come without the dot
	 * @param isLoadOnly :  if set to true, it should consider file extensions for file loading (input).
	 * If set to false, it should consider both saving and loading. Note
	 * that not every module/plugin can implement both loading and saving, and this parameter may separate such
	 * special behaviors.
	 * @return
	 * @deprecated use {@link BaseIO#getSupportedFileExtensions(boolean)}
	 * @see #getIO()
	 */
	public String[] getSupportedFileExtensions(boolean isLoadOnly);
	
	/**
	 * Gets a description of supported file extensions,
	 * which may be shown to the user through file chooser's file filter to explain what
	 * file format are supported.
	 * E.g. "Net (.net), XMLBIF(.xml), UnBBayes File (.ubf)"
	 * @param isLoadOnly :  if set to true, it should consider file extensions for file loading (input).
	 * If set to false, it should consider both saving and loading. Note
	 * that not every module/plugin can implement both loading and saving, and this parameter may separate such
	 * special behaviors.
	 * @deprecated use {@link BaseIO#getSupportedFilesDescription(boolean)}
	 * @see #getIO()
	 */
	public String getSupportedFilesDescription(boolean isLoadOnly);
	
	
	/**
	 * Obtains a message to be shown to user while saving a net editted by a window implementing this
	 * interface.
	 * For example, you may want a FileChooser to show personalized title depending on what you are saving.
	 * @return
	 */
	public String getSavingMessage();
	
	
	
	
	/**
	 * Obtains the internal frame to be created by UnBBayes Swing core 
	 * when a file listed within {@link #getSupportedFileExtensions()}
	 * is opened by the user.
	 * @return a instance of {@link JInternalFrame}
	 */
	public JInternalFrame getInternalFrame();
	
	/**
	 * Returns the I/O class that is responsible to open/save files
	 * of a extension declared in {@link #getSupportedFileExtensions()}
	 * @return instance of BaseIO. BaseIO#supportsExtension(String) must return true in this case.
	 * @see BaseIO#supportsExtension(String)
	 */
	public BaseIO getIO();
	
	/**
	 * Obtains the graph to be persisted or is being edited by this window.
	 * @return
	 */
	public Graph getPersistingGraph();
	
	
	
}
