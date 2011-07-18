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
package unbbayes.controller.resources;

import java.util.ListResourceBundle;

/**
 * <p>Title: UnBBayes</p>
 * <p>Description: Resources file for unbbayes.controller package. Localization = english.</p>
 * <p>Copyright: Copyright (c) 2001</p>
 * <p>Company: UnB</p>
 * @author Rommel Novaes Carvalho, Michael Onishi
 * @version 1.0
 * @since 05/04/2002
 */

public class ControllerResources extends ListResourceBundle {
	
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
	{	{"imageFileFilter","PNG (.png), JPEG (.jpg), GIF (.gif), BMP (.bmp)"},
		{"likelihoodName","Likelihood"},
		{"likelihoodException","There are only zeros!"},
		{"statusEvidenceProbabilistic","Total Evidence Probabilistic: "},
		{"statusEvidenceException","Evidences are not consistancy or underflow"},
		{"statusError","Error!"},
		{"printLogToolTip","Print compilation log"},
		{"previewLogToolTip","Print preview"},
		{"okButtonLabel"," Ok "},
		{"closeButtonLabel","Close"},
		{"statusTotalTime","Total Time: "},
		{"statusSeconds"," seconds"},
		{"stateProbabilisticName","State "},
		{"stateDecisionName","Action "},
		{"stateUtilityName","Utility "},
		{"firstStateProbabilisticName","State 0"},
		{"firstStateDecisionName","Action 0"},
		{"nodeName","Node: "},
		
		// Error message
		{"noNodeSelectedError","A node must be selected."},
		
		//Barra de status
		{"statusLoadingKB","Loading knowledge base..."},
		{"statusSavingKB","Loading knowledge base..."},
		{"statusGeneratingSSBN","Generating SSBN..."},
		{"statusReady","Ready"},
		
		//MainController
		{"NewPNName","New BN"},
		{"NewMSBNName","New MSBN"},
		
		{"probabilisticNodeName","C"},
		{"decisionNodeName","D"},
		{"utilityNodeName","U"},
		{"contextNodeName","CX"},
		{"residentNodeName","RX"},
		{"inputNodeName","IX"},
		{"ordinaryVariableName", "OX"}, 
		{"entityName", "EX"}, 			
		
		{"domainMFragName","DMFrag"},	
		{"findingMFragName","FMFrag"},				
		
		{"copiedNodeName","Copy of"},
		{"askTitle","Type a title for the net"},
		{"informationText","Information"},
		{"printException","Print Error: "},
		{"loadNetException","Error to load net file"},
		{"cancelOption","Cancel"},
		{"printerStatus","Printer Status"},
		{"initializingPrinter","Initializing printer..."},
		{"printingPage","Printing page "},
		{"previewButtonLabel","Previous"},
		{"nextButtonLabel","Next"},
		{"fitToPageButtonLabel","Fit to Page"},
		{"loading","Loading "},
		{"cancel","Cancel"},
		{"of"," of "},
		{"numberFormatError","The value must be a real number."},
		

		{"JAXBExceptionFound", "Sintaxe error..."},
		
		/* Numeric attribute node */
		{"mean", "Mean"},
		{"stdDev", "Standard Dev"}, 
			
		/* Java helper */
		{"helperDialogTitle", "Help"},
		
		//Network Controller
		{"logDialogTitle", "Log"}, 
		
		//Result Dialog
		{"ResultDialog", "Result"}, 
		
		/* load/save */
		{"saveSucess", "File saved"},
		{"bnDontExists", "Failed: no active Bayesian Network"},
		{"msbnDontExists", "Failed: No active MSBN"},
		{"sucess", "Sucess"}, 
		{"error", "Error"},
		{"loadHasError", "The file has been loaded with some errors"},
		{"withoutPosfixe", "The type of the file wasn't provided"},

		/* Likelihood Weighting Inference */
		{"sampleSizeInputMessage", "Please enter the sample size (number of trials)."},
		{"sampleSizeInputTitle", "Sample size"}, 
		{"sampleSizeInputError", "The sample size must be an integer number greater than zero."},
		{"likelihoodWeightingNotApplicableError", 
			"The Likelihood Weighting algorithm can only be used with \n" +
			"a Bayesian network. Please choose another algorithm."},
		
		/* Continuous Inference */
		{"continuousInferenceNotApplicableError", 
			"The inference algorithm for networks with continuous \n" +
			"nodes can only be used with a hybrid Bayesian network \n" +
			"(with at least one continuous node). Please choose \n" +
			"another algorithm."},
		
		/* Junction Tree Inference */
		{"junctionTreeNotApplicableError", 
			"The Junction Tree algorithm can only be used with a Bayesian \n" +
			"network or an influence diagram. Please choose another algorithm."},
			
		// file managing	
		{"cannotHandleFileFormat", "This module cannot handle this file format."},
		
		// Saving graph as image
		{"useTransparencyTitle", "Transparency"},
		{"useTransparencyMessage", "Do you want transparency?"},
		{"useShadowTitle", "Shadow"},
		{"useShadowMessage", "Do you want shadow?"},
		{"shadowSizeInputMessage", "Please, enter shadow's size.\n(It must be an integer)"},
		{"shadowSizeInputErrorTitle", "Shadow Size Error"},
		{"shadowSizeInputErrorMessage", "Input must be an integer."},
		{"shadowOpacityInputMessage", "Please, enter shadow's opacity.\n(It must be a number between 0 and 1)"},
		{"shadowOpacityInputErrorTitle", "Shadow Opacity Error"},
		{"shadowOpacityInputErrorMessage", "Input must be a number between 0 and 1."},
				
		
	};
}
