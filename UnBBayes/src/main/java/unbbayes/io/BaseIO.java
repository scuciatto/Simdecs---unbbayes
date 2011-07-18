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
package unbbayes.io;

import java.io.File;
import java.io.IOException;

import unbbayes.io.exception.LoadException;
import unbbayes.prs.Graph;

/**
 *  This is the most basic I/O interface for UnBBayes, which basically loads or stores a Graph from/into a file.
 * @author Rommel N. Carvalho
 * @author Michael S. Onishi
 * @author Shou Matsumoto
 * @version 2.0
 */
public interface BaseIO {
	
	/**
	 * Loads a new network from the input file.
	 * 
	 * @param input the input file for the network
	 * @return The loaded network
	 * @throws LoadException If the file doesn't describe a network.
	 * @throws IOException	If an IO error occurs
	 */
    public Graph load(File input) throws LoadException, IOException;
    
    
    /**
     * Saves a network to the output file.
     * @param output	The output file to save
     * @param net		The network to save.
     */
    public void save(File output, Graph net) throws IOException;
    
    
    /**
     * Returns true if the file is supported by this IO class. It may be implemented as simple extension check.
     * False otherwise.
     * @param file : the file to analyze extension.
	 * @param isLoadOnly : if set to true, it should consider file extensions for file loading (input).
	 * If set to false, it should consider both saving and loading. Note
	 * that not every I/O class can implement both loading and saving, and this parameter may separate such
	 * special behaviors.
	 * @return
     */
    public boolean supports(File file, boolean isLoadOnly);
    
    /**
	 * Obtains an array of file extensions supported by this network window.
	 * The file extensions should come without the dot
	 * @param isLoadOnly :  if set to true, it should consider file extensions for file loading (input).
	 * If set to false, it should consider both saving and loading. Note
	 * that not every module/plugin can implement both loading and saving, and this parameter may separate such
	 * special behaviors.
	 * @return
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
	 */
	public String getSupportedFilesDescription(boolean isLoadOnly);
	
	/**
	 * Sets the name of this I/O component.
	 * This name may be displayed to a user when there is a need to choose a
	 * specific I/O class to use.
	 * @param name
	 */
	public void setName(String name);
	
	/**
	 * Gets the name of this I/O component.
	 * This name may be displayed to a user when there is a need to choose a
	 * specific I/O class to use.
	 * @return a name
	 */
	public String getName();
	
   
}