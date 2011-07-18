package unbbayes.io.log;

import java.io.IOException;

/**
 * Interface for a log manager (responsible for generate the log of a operation). 
 * 
 * @author Laecio Lima dos Santos (laecio@gmail.com)
 */
public interface ILogManager {

	/**
	 * Clear the log information. 
	 */
    public void clear(); 
	
    /**
     * Get the log how a String. 
     */
    public String getLog(); 
    
    /**
     * Write the log in a file. 
     */
    public void writeToDisk(String fileName, boolean append) throws IOException; 
    
    /**
     * Add the title of the log 
     */
    public void addTitle(String text); 
    
    /**
     * Append the text 
     */
    public void append(String text); 
    
    /**
     * Append the text and make new line. 
     */
    public void appendln(String text); 
    
    /**
     * Append separator line. 
     */
    public void appendSeparator(); 
    
    /**
     * Append a section title 
     */
    public void appendSectionTitle(String text); 
    
    /**
     * Append a section title 
     */
    public void appendSpecialTitle(String text); 
    
}
