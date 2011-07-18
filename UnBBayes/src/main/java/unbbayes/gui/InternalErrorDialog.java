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

import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

/**
 * This dialog indicates that there was an internal error.
 * For example, when a condition assumed to be true was in fact false.
 * This dialog may also be used in order to e-mail notifications to developers
 * with a description of the error.
 * @author Laecio Lima dos Santos
 * @version 1.0 06/05/07
 *
 */
public class InternalErrorDialog {

	/** Load resource file from this package */
  	private static ResourceBundle resource = unbbayes.util.ResourceController.newInstance().getBundle(
  			unbbayes.gui.resources.GuiResources.class.getName(),
  			Locale.getDefault(),
  			InternalErrorDialog.class.getClassLoader());
	
	//TODO conclude the panel, inserting options for error report... 
	public InternalErrorDialog(){
		JOptionPane.showMessageDialog(
				null, 
				resource.getString("internalError"), 
				resource.getString("error"), 
				JOptionPane.ERROR_MESSAGE);        	
	}
	
}
