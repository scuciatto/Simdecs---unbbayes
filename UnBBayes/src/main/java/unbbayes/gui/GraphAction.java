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

/** 
 * Actions possibles in the graph pane.
 * @see GraphPane 
 */

public enum GraphAction {
		
		NONE,
		CREATE_EDGE,
		
		CREATE_CONTINUOUS_NODE,
		CREATE_PROBABILISTIC_NODE,
		CREATE_DECISION_NODE,
		CREATE_UTILITY_NODE,
		
		CREATE_CONTEXT_NODE,
		CREATE_INPUT_NODE,
		CREATE_RESIDENT_NODE,
		CREATE_ORDINARYVARIABLE_NODE, 
		
		CREATE_DOMAIN_MFRAG, 
		//by young4
		SELECT_MANY_OBJECTS,
		
		// by shou
		ADD_PLUGIN_NODE
	
}
