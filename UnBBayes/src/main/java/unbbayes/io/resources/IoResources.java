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
package unbbayes.io.resources;

import java.util.ListResourceBundle;

/**
 * <p>Title: UnBBayes</p>
 * <p>Description: Resources file for unbbayes.io package. Localization = english.</p>
 * <p>Copyright: Copyright (c) 2001</p>
 * <p>Company: UnB</p>
 * @author Rommel Novaes Carvalho, Michael Onishi
 * @version 1.0
 * @since 02/05/2002
 */
public class IoResources extends ListResourceBundle {

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
	{	{"logHeader","This description is made in the net's compilation process.\n" +
                     "It has the information of how a subjacent junction tree was\n" +
                     "created based on the junction tree technique with the minimum\n" +
                     "weight heuristic.\n\n"},
		{"cliqueHeader","******************* Cliques ******************\n"},
		{"cliqueName","Clique "},
		{"cliqueLabel"," Clique: "},
		{"potentialTableName","\nPotential Table\n"},
		{"utilityTableName","\nUtility Table\n"},
		{"separatorHeader","**************** Separators *****************\n"},
		{"separatorName","Separator "},
		{"betweenName","between "},
		{"andName"," and "},
		{"nodeName","Node(s): "},		
		{"potentialAssociatedHeader","************ Potentials associatedes to cliques **************\n"},
		{"errorNet","This file do not conform with the NET specification."},
		{"errorDne","This file do not conform with the DNE specification."},
		{"LoadException"," Missing 'net'"},
		{"LoadException2",": Unknown statement: "},
		{"LoadException3",": Missing '{'"},
		{"LoadException4",": Decision variable cannot have a table"},
		{"LoadException5",": Missing 'data'"},
		{"FileNotFoundException","It was not possible to load the file!"},
		{"IsNotDirectoryException", "The specified path must be a directory"}, 
		
		{"OpenFileError", "Error at open of a file"}, 
		{"CreationFileError", "Error at creation of a file"}, 
		{"WriteReaderFileError", "Error at write or read a file"},
		{"UnsupportedError", "This format is not supported."},
		

	};
}