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
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ResourceBundle;

import javax.xml.bind.JAXBException;

import unbbayes.io.exception.LoadException;
import unbbayes.io.exception.SaveException;
import unbbayes.io.exception.UBIOException;
import unbbayes.prs.Graph;
import unbbayes.prs.bn.ProbabilisticNetwork;
import unbbayes.prs.bn.SingleEntityNetwork;
import unbbayes.util.Debug;

/** 
 * Manipulates I/O of XMLBIF file format.
 * @author Rommel Carvalho (rommel.carvalho@gmail.com)
 * @author Laecio Lima dos Santos (laecio@gmail.com) (version 2.0)
 * @author Shigeki (version 1.0)
 * 
 * @author Shou Matsumoto (version 3.1)
 * @version 3.1, 02/14/2010 - Moved MSBN-specific routines into XMLBIFMSBNIO
 */

public class XMLBIFIO implements BaseIO{
	
	/** Load resource file from this package */
	private static ResourceBundle resource =
		unbbayes.util.ResourceController.newInstance().getBundle(unbbayes.io.resources.IoResources.class.getName());
	
	/** Supported file extension with no dots. The value is {"xml"}  */
	public static final String[] SUPPORTED_EXTENSIONS = {"xml"};
	
	private String name = "Core XMLBIF";
	
	/**
	 * Loads a new Probabilistic network from the input file.
	 * 
	 * @param input the input file for the network
	 * @return The loaded network
	 * @throws LoadException If the file doesn't describes a network.
	 * @throws IOException	If an IO error occurs
	 */
	public ProbabilisticNetwork load(File input) throws LoadException, IOException{
		
		int index = input.getName().lastIndexOf('.');
		String id = input.getName().substring(0, index);
		ProbabilisticNetwork pn = new ProbabilisticNetwork(id);	
		try { 
			unbbayes.io.xmlbif.version6.XMLBIFIO.loadXML(input, pn);
		} catch (Exception e) {
			try {
				// Try version 0.5.
//				e.printStackTrace();
				Debug.println(this.getClass(), "Fiz a v5.");
				unbbayes.io.xmlbif.version5.XMLBIFIO.loadXML(input, pn);
			} catch (Exception e2) {
				// Try version 0.4.
				try {
//					e2.printStackTrace();
					Debug.println(this.getClass(), "Fiz a v4.");
					unbbayes.io.xmlbif.version4.XMLBIFIO.loadXML(input, pn);
				} catch (Exception e3) {
					e3.printStackTrace();
					Debug.println(this.getClass(), "Fiz a v3.");
					throw new LoadException(resource.getString("UnsupportedError"));
				}
			}
		}
		return pn; 
	}
	
	
	
	/**
	 * Saves a network to the output file.
	 * @param output	The output file to save
	 * @param graph		The network to save.
	 */
	
	public void save(File output, Graph graph) throws IOException{
		
		SingleEntityNetwork net = (SingleEntityNetwork) graph;
		
		FileWriter outputxml = new FileWriter(output);

		// Saving in older version is not supported.
		// unbbayes.io.xmlbif.version4.XMLBIFIO.saveXML(outputxml, net); 
		// unbbayes.io.xmlbif.version5.XMLBIFIO.saveXML(outputxml, net);
		try {
			unbbayes.io.xmlbif.version6.XMLBIFIO.saveXML(outputxml, net);
		} catch (JAXBException e) {
			throw new UBIOException(e);
		}

		outputxml.flush();
		outputxml.close();
	}
	
	

	/**
	 * Checks if file extension is compatible to what this i/o expects.
	 * @see #supports(File, boolean)
	 * @param extension
	 * @param isLoadOnly
	 * @return
	 */
	public boolean supports(String extension, boolean isLoadOnly) {
		return SUPPORTED_EXTENSIONS[0].equalsIgnoreCase(extension);
	}

	/*
	 * (non-Javadoc)
	 * @see unbbayes.io.BaseIO#getSupportedFileExtensions(boolean)
	 */
	public String[] getSupportedFileExtensions(boolean isLoadOnly) {
		return SUPPORTED_EXTENSIONS;
	}

	/*
	 * (non-Javadoc)
	 * @see unbbayes.io.BaseIO#getSupportedFilesDescription(boolean)
	 */
	public String getSupportedFilesDescription(boolean isLoadOnly) {
		return "XMLBIF (.xml)";
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see unbbayes.io.BaseIO#supports(java.io.File, boolean)
	 */
	public boolean supports(File file, boolean isLoadOnly) {
		String fileExtension = null;
		try {
			int index = file.getName().lastIndexOf(".");
			if (index >= 0) {
				fileExtension = file.getName().substring(index + 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return this.supports(fileExtension, isLoadOnly);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
//	----------------------------------------------------------------------------------------
	
}