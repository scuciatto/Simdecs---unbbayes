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
package unbbayes.prs.bn.resources;


import java.util.ListResourceBundle;

/**
 * <p>Title: UnBBayes</p>
 * <p>Description: Resources file for unbbayes.prs.bn package. Localization = english.</p>
 * <p>Copyright: Copyright (c) 2001</p>
 * <p>Company: UnB</p>
 * @author Rommel Novaes Carvalho
 * @author Michael Onishi
 * @version 1.0
 * @since 02/05/2002
 */

public class BnResources extends ListResourceBundle {

    /**
	 *  Override getContents and provide an array, where each item in the array is a pair
	 *	of objects. The first element of each pair is a String key,
	 *	and the second is the value associated with that key.
	 *
	 * @return The resources' contents
	 */
	public Object[][] getContents() {
		return contents;
	}

	/**
	 * The resources
	 */
	static final Object[][] contents =
	{	{"CicleNetException","Net with cicle:"},
		{"DisconectedNetException","Net disconected"},
		{"TableSizeException","Table size's differs"},
		{"OperatorException","Unknown operator"},
		{"moralizeLabel","Moralized with arcs:\n"},
		{"triangulateLabel","\nTriangulation and Elimination Order (links):\n"},
		{"EmptyNetException","The net is empty!"},		
		{"DecisionOrderException","There is no ordenation of the decision variables"},
		{"variableName","Variable "},
		{"hasChildName"," has child(s)"},
		{"linkedName"," linked to "},
		{"copyName","Copy of "},
		{"variableTableName","Variable Table "},
		{"inconsistencyName"," inconsistency -> "},
		{"utilityName","Utility"},
		{"InconsistencyUnderflowException","Inconsistency or Underflow found."}
	};
}