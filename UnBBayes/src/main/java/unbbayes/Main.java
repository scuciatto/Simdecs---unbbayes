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
package unbbayes;

import unbbayes.controller.MainController;
import unbbayes.util.Debug;

/**
 *  This class starts UnBBayes
 *
 *@author   Michael S. Onishi (mso@gmail.com)
 *@author 	Rommel N. Carvalho (rommel.carvalho@gmail.com)
 *@version    24 de Junho de 2001
 *
 *@author Shou Matsumoto
 *@version 13-08-2009
 *			Added console mode.
 */

public class Main {
	
    /**
     *  Starts UnBBayes.
     */
    public static void main(String[] args) {
    	// TextModeRunner was moved to MEBN plugin
//    	// extract -t param, expecting at first place
//    	if (args.length > 0 && args[0].equalsIgnoreCase("-t")) {
//    		String[] newArgs = new String[args.length-1];
//    		System.arraycopy(args, 1, newArgs, 0, newArgs.length);
//    		// start in text mode
//    		TextModeRunner.main(newArgs);    		
//    	} else {
//    		// normal mode
//    		new MainController();
//    	}
    	// debug mode
    	for (String arg : args) {
			if (arg.equalsIgnoreCase("-d")) {
				Debug.setDebug(true);
				Debug.println("Debug mode is on.");
			}
		}
    	new MainController();
    }
    
}
