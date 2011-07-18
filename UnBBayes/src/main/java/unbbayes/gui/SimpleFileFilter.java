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

import java.io.File;
import java.util.ResourceBundle;

import javax.swing.filechooser.FileFilter;

/**
 *  Class responsible for filtering the type of files to show.
 *
 *@author   Rommel N. Carvalho (rommel.carvalho@gmail.com)
 *@author 	Michael S. Onishi (michael.onishi@gmail.com)
 *@see      FileFilter
 */
public class SimpleFileFilter extends FileFilter {

    private String[] extensions;
    private String description;

	/** Load resource file from this package */
  	private static ResourceBundle resource = unbbayes.util.ResourceController.newInstance().getBundle(
  			unbbayes.gui.resources.GuiResources.class.getName());


    /**
     *  Creates a <code>FileFilter</code> with the desired file extension.
     *
     *@param  ext  Extension of the files to be shown.
     *@see         FileFilter
     */
    public SimpleFileFilter(String ext) {
        this(new String[]{ext}, null);
    }


    /**
     *  Creates a <code>FileFilter</code> with the desired file extension and description.
     *
     *@param  exts   Extensions of the files to be shown.
     *@param  descr  Descriptions of the files to be shown.
     *@see	FileFilter
     */
    public SimpleFileFilter(String[] exts, String descr) {
        //clona e coloca em lowercase as extens�es
        extensions = new String[exts.length];
        for (int i = exts.length - 1; i >= 0; i--) {
            extensions[i] = exts[i].toLowerCase();
        }
        //verificar se temos uma descri��o v�lida
        description = (descr == null ? exts[0] + resource.getString("filesText") : descr);
    }


    /**
     *  Returns a generic description.
     *
     *@return    A generic description.
     */
    public String getDescription() {
        return description;
    }


    /**
     *  Verify if the file has one of the valid extensions.
     *
     *@param  f  File to be checked for valid extension.
     *@return    True if it has a valid extension, false otherwise.
     */
    public boolean accept(File f) {
        // We will always allow directories
        if (f.isDirectory()) {
            return true;
        }

        // Check for valid extension
        String name = f.getName().toLowerCase();
        for (int i = extensions.length - 1; i >= 0; i--) {
            if (name.endsWith(extensions[i])) {
                return true;
            }
        }
        return false;
    }
}

