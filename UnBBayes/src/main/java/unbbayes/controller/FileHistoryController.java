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

import java.io.File;

import javax.swing.JFileChooser;


/**
 * File Controller for some files of UnBBayes
 */
public class FileHistoryController
{	 /** A instance of this object */
	private static FileHistoryController singleton;
	private JFileChooser fileChooser = new JFileChooser(new File(System.getProperty("user.dir")));


	
	//--------------------------------------------------------------//

	public File getCurrentDirectory()
	{	 return fileChooser.getCurrentDirectory();
	}

	//--------------------------------------------------------------//

	public void setCurrentDirectory(File file)
	{	 fileChooser.setCurrentDirectory(file);
	}

	//--------------------------------------------------------------//

	/** 
	 * default constructor. S may be instanciated by getInstance method 
	 */
	protected FileHistoryController(){	 
	}

	//--------------------------------------------------------------//

	/**
	 * Returns an instance of this object. If the object is already instanciated once, it
	 * returns the current object, otherwise it returns a new object.
	 * @return Options object
	 */
	public static FileHistoryController getInstance()
	{	 if (singleton == null)
		{	 singleton = new FileHistoryController();
		}
		return singleton;
	}

	//--------------------------------------------------------------//

//	public InstanceSet getInstanceSet(File file, Component component) throws Exception {
//		/* Get the attributes' type information */
//		AttributeTypeChooserController attributeTypeChooser;
//		attributeTypeChooser = new AttributeTypeChooserController(file, component);
//		Loader loader = attributeTypeChooser.getLoader();
//		
//		/* Check if the user cancelled the opening operation */
//		if (loader == null) {
//			return null;
//		}
//		
//		/* Starts loading and shows a status screen */
//		String fileName = file.getName();
//		ProgressDialog progressDialog = new ProgressDialog (fileName, loader);
//		boolean successStatus = progressDialog.load();
//
//		InstanceSet inst = loader.getInstanceSet();
//
//		if (successStatus) {
//			return inst;
//		} else {
//			return null;
//		}
//	}

	//--------------------------------------------------------------//

//	public void saveInstanceSet(File output, InstanceSet instanceSet,
//			int[] selectedAttributes) throws IOException {
//		Saver saver;
//		String fileName = output.getName();
//		boolean compacted;
//		
//		if (instanceSet.counterIndex == -1) {
//			compacted = false;
//		} else {
//			compacted = true;
//		}
//		
//		if (fileName.regionMatches(true, fileName.length() - 5, ".arff", 0, 5)) {
//			saver = new ArffSaver(output, instanceSet, selectedAttributes, compacted);
//		} else if (fileName.regionMatches(true, fileName.length() - 4, ".txt", 0, 4)) {
//			saver = new TxtSaver(output, instanceSet, selectedAttributes, compacted);
//		} else {
//			throw new IOException(resourceNaiveBayesian.getString("fileExtensionException"));
//		}
//
//		//starts loading and shows a status screen
//		ProgressDialog progressDialog = new ProgressDialog (output.getName(), saver);
//		progressDialog.load();
//	}

}
