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
package unbbayes.gui.resources;

import java.util.ListResourceBundle;

/**
 * <p>Title: UnBBayes</p>
 * <p>Description: Resources file for unbbayes.gui package. Localization = english.</p>
 * <p>Copyright: Copyright (c) 2001</p>
 * <p>Company: UnB</p>
 * @author Rommel Novaes Carvalho, Michael Onishi
 * @version 1.0
 * @since 06/04/2002
 */

public class GuiResources extends ListResourceBundle {
 
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
	{	
		//Types of files
		{"fileDirectoryType","Directory"},
		{"fileARFFType","Arff file"},
		{"fileTXTType","TXT file"},
		{"fileNETType","Baysian Netwotk File NET"},
		{"fileGenericType","Generic File"},
		
		{"allNetFileFilter","All Supported Network Files"},
		{"textFileFilter","Text (.txt)"},
		{"fileUntitled","Untitled.txt"},
		
		//Titles of the file choosers
		{"saveTitle","Save"}, 
		{"openTitle","Open"}, 
		
		{"unbbayesTitle","UnBBayes"},
		
		//main toll bar tips 
		{"newToolTip","New net"},
		{"newOobnToolTip", "New OOBN"}, 
		{"openToolTip","Open net"},
		{"saveToolTip","Save net"},
		{"learningToolTip","Learning mode"},
		{"metalToolTip","Metal Look And Feel"},
		{"motifToolTip","Motif Look And Feel"},
		{"windowsToolTip","Windows Look And Feel"},
		{"homeSystemToolTip","Home System Look And Feel"},
		{"tileToolTip","Organize windows in tile"},
		{"cascadeToolTip","Organize windows in cascade"},
		
		{"globalOptionTitle","Global Option"},
		{"hierarchyToolTip","Hierarchy definition"},
        
		{"usaName","USA"},
		{"chinaName","China"},
		{"japanName","Japan"},
		{"ukName","UK"},
		{"koreaName","Korea"},
		{"italyName","Italy"},
		{"canadaName","Canada"},
		{"brazilName","Brazil"},
		
		{"nodeName","Node: "},
		{"radiusLabel","Radius:"},
		{"radiusToolTip","Node radius"},
		{"netLabel","Net"},
		{"netToolTip","Net size"},
		{"probabilisticDescriptionNodeColorLabel","Description"},
		{"probabilisticDescriptionNodeColorToolTip","Select probabilistic description node color"},
		{"probabilisticExplanationNodeColorLabel","Explanation"},
		{"probabilisticExplanationNodeColorToolTip","Select probabilistic explanation node color"},
		{"decisionNodeColorLabel","Decision"},
		{"decisionNodeColorToolTip","Select decision node color"},
		{"utilityNodeColorLabel","Utility"},
		{"utilityNodeColorToolTip","Select utility node color"},
		{"nodeColorLabel","Node Color"},
                {"arcColor","Arc Color"},
                {"selectionColor","Selection Color"},
                {"backGroundColor","Background Color"},
                {"arcColorLabel","Arc"},
		{"arcColorToolTip","Select arc color"},
		{"selectionColorLabel","Selection"},
		{"selectionColorToolTip","Select selection color"},
		{"backgroundColorLabel","Background"},
		{"backgroundColorToolTip","Select background color"},
		{"edgeColor","Edge's color"},
		
		{"confirmLabel","Confirm"},
		{"confirmToolTip","Corfim modifications"},
		{"cancelLabel","Cancel"},
		{"cancelToolTip","Cancel modifications"},
		{"resetLabel","Reset"},
		{"resetToolTip","Reset default values"},
		{"decimalPatternTab","Decimal Pattern"},
		{"colorControllerTab","Color Controller"},
		{"sizeControllerTab","Size Controller"},
		{"logTab","Log"},
		{"createLogLabel","Create Log"},
		{"nodeGraphName","Node"},
		{"closeButton", "Close"},
		
		{"likelihoodName", "Likelihood"},
		
		{"LookAndFeelUnsupportedException","It does not support this LookAndFeel: "},
		{"LookAndFeelClassNotFoundException","This LookAndFeel class was not found: "},
		{"LookAndFeelInstantiationException","It was not possible to load this LookAndFeel: "},
		{"LookAndFeelIllegalAccessException","This LookAndFeel can not be used: "},
		{"nameError","Inacceptable name"},	
		{"operationFail","Operation was not accepted"},
		{"nameAlreadyExists","An object with this name already exists."},	
		{"objectEntityHasInstance","The selected entity contains instances. Remove them and try again."},
		{"error","Error..."},	
		{"argumentMissing","Missing arguments"},	
		{"stateUnmarked","State is not marked"},	
		
		{"loadNetException","Error to load net file"},
		{"JAXBExceptionFound", "Sintax error..."},
		{"SaveNetException", "Error trying to save the net"},
		{"saveSucess", "File saved"},
		
		{"helpToolTip","UnBBayes help"},
		{"propagateToolTip","Propagate evidences"},
		{"expandToolTip","Expand evidences tree"},
		{"collapseToolTip","Collapse evidences tree"},
		{"editToolTip","Return to edit mode"},
		{"logToolTip","Information about Compilation (Log)"},
		{"resetBeliefsToolTip","Reset beliefs"},
		{"removeEvidenceToolTip","Remove evidence of selected node"},
		{"printNetToolTip","Print graph"},
		{"previewNetToolTip","Print graph preview"},
		{"saveNetImageToolTip","Save graph as an image"},
		{"nameLabel", "Name:"}, 
		{"typeLabel", "Type:"}, 
		{"descriptionLabel","Description:"},
		{"ordereableLabel", "Is Ordenable"}, 
		{"evaluateToolTip","Evaluate probabilistic network"},
		{"compileToolTip","Compile junction tree"},
		{"moreToolTip","Add state"},
		{"lessToolTip","Remove state"},
		{"arcToolTip","Insert edge"},
		{"continuousNodeLabel","Continuous variable"},
		{"continuousNodeInsertToolTip","Insert continuous variable"},
		{"probabilisticNodeInsertToolTip","Insert probabilistic variable"},
		{"decisionNodeInsertToolTip","Insert decision variable"},
		{"utilityNodeInsertToolTip","Insert utility variable"},
		{"contextNodeInsertToolTip","Insert Context Node"},
		{"inputNodeInsertToolTip","Insert Input Node"},
		{"residentNodeInsertToolTip","Insert Resident Node"},
		{"mFragInsertToolTip","Insert MFrag"},
		{"selectObjectToolTip","Select Object"},
		{"ordinaryVariableInsertToolTip","Insert Ordinary Variable"},
		{"inputActiveToolTip","Input Node Selected"},  
		{"mFragActiveToolTip","MFrag Selected"}, 		
		{"contextActiveToolTip","Context Node Selected"}, 
		{"residentActiveToolTip","Resident Node Selected"}, 		
		{"addArgumentToolTip","Add Argumment"}, 
		{"editFormulaToolTip","Edit formula"},		
		{"selectToolTip","Select various nodes and edges"},
		{"printTableToolTip","Print table"},
		{"previewTableToolTip","Print table preview"},
		{"saveTableImageToolTip","Save table as an image"},
		{"newEntityToolTip","Create new entity"},		
		{"delEntityToolTip","Delete entity"},
		{"newOVariableToolTip","Create new ord. variable"},
		{"delOVariableToolTip", "Delete ord. variable"}, 	
		{"newArgumentToolTip","Add new ord. variable to argument list"},
		{"delArgumentToolTip", "Remove ord. variable from argument list"}, 	
		{"downArgumentToolTip", "Add to argument list ord. variable selected"}, 
		{"mTheoryEditionTip", "Edit the MTheory"}, 	
		{"isGloballyExclusive", "Globally exclusive"},		
		{"resetToolTip", "Reset"},	
		{"deleteSelectedItemToolTip", "Delete Item"},	
		{"menuOpen", "Open"},	
		
		{"showMTheoryToolTip","Show MTheory tree"},	
		{"showEntitiesToolTip","Show entities of the MTheory"},
		{"showOVariablesToolTip","Show ord. variables of the MFrag"},			
		{"showEntityInstancesToolTip","Show entity instance edition pane"},			
		{"showFingingsToolTip","Show findings edition pane"},		
		
		{"executeQueryToolTip","Execute Query"},
		{"turnToSSBNModeToolTip","Turn to SSBN Mode"},
		{"clearKBToolTip","Clear Knowledge Base"},
		{"loadKBToolTip","Load Knowledge Base"},
		{"saveKBToolTip","Save Knowledge Base"},
		
		{"formula","Formula:"},	
		{"inputOf","Input of:"},	
		{"arguments", "Args: "}, 
		{"statusReadyLabel","Ready"},
		
		{"andToolTip", "'and' operator"}, 
		{"orToolTip", "'or' operator"},
		{"notToolTip", "'not' operator"},
		{"equalToToolTip", "'equal to' operator"},
		{"impliesToolTip", "'implies' operator"},
		{"iffToolTip", "'iff' operator"},
		{"forallToolTip", "'for all' quantifier"},
		{"existsToolTip", "'exists' quantifier"},		
		
		
		
		//Titles for tab panel
		{"ResidentTabTitle", "Resident Node"}, 
		{"InputTabTitle", "Input Node"}, 
		{"ContextTabTitle", "Context Node"}, 
		{"MTheoryTreeTitle", "MTheory Tree"}, 
		{"EntityTitle", "Entity"}, 
		{"OVariableTitle", "Ord. Variable"}, 
		{"ArgumentTitle", "Arguments"}, 
		{"StatesTitle", "States"}, 	
		{"FathersTitle", "Fathers Nodes"}, 		
		{"AddFinding", "Finding"}, 	
		
		//Label for buttons of tab selection
		/* Don't use names with more than fifteen letters */
		{"MTheoryButton", "MTheory"}, 
		{"ResidentButton", "Resident"}, 
		{"NodesTitle", "Nodes"}, 	
		{"InputButton", "Input"}, 
		{"ContextButton", "Context"}, 
		{"MFragButton", "MFrag"}, 	
		{"ArgumentsButton", "Arguments"}, 			
		{"OrdVariableButton", "Variable"}, 
		
		{"whithotMFragActive","No active MFrag"},			
		{"previewTitle","Preview"},
		{"filesText"," files"},
		{"aprendizagemTitle","Net Learning Edition"},
		{"calculateProbabilitiesFromLearningToEditMode","Rebuild net structure and return to edit mode"},
        {"fileMenu","File"},
        {"lafMenu","Look and Feel"},
        {"viewMenu","View"},
        {"recentFilesMenu","Recent Files"},
        {"tbMenu","Toolbars"},
        {"toolsMenu","Tools"},
        {"samplingMenu","Sampling"},
        {"windowMenu","Window"},
        {"helpMenu","Help"},
        {"newMenu","New..."},
        {"newBN", "BN"},
        {"newOOBN","New OOBN"},
        {"openItem","Open..."},
        {"saveItem","Save as..."},
        {"exitItem","Exit"},
        {"tbNewProject","New Project Toolbar"},
        {"tbFile","File Toolbar"},
        {"tbView","View Toolbar"},
        {"tbTools","Tools Toolbar"},
        {"tbWindow","Window Toolbar"},
        {"tbHelp","Help Toolbar"},
        {"tbPlugin","Plugin Toolbar"},
        {"metalItem","Metal"},
        {"motifItem","Motif"},
        {"windowsItem","Home"},
        {"learningItem","Learning"},
        {"tanItem","TAN"},
        {"banItem","BAN"},
        {"logicItem","Logic"},
        {"likelihoodWeightingItem","Likelihood Weighting"},
        {"gibbsItem","Gibbs"},
        {"ILearningItem","Incremental Learning"},
        {"MetaphorItem","Metaphor"},
        {"MedicalMetaphorItem","Medical Metaphor"},
        {"UnBMinerItem","UnBMiner"},
        {"cascadeItem","Cascade"},
        {"tileItem","Tile"},
        {"helpItem","Help"},
        {"aboutItem","About UnBBayes"},
        
        {"recentFilesMn", "R"},
        {"fileMenuMn","F"},
        {"newMenuMn","N"},
        {"lafMenuMn","L"},
        {"viewMenuMn","V"},
        {"tbMenuMn","T"},
        {"toolsMenuMn","T"},
        {"windowMenuMn","W"},
        {"helpMenuMn","H"},
        {"newItemMn","N"},
        {"openItemMn","O"},
        {"saveItemMn","S"},
        {"exitItemMn","X"},
        {"metalItemMn","M"},
        {"motifItemMn","O"},
        {"windowsItemMn","H"},
        {"cascadeItemMn","C"},
        {"tileItemMn","T"},
        {"helpItemMn","H"},
        {"aboutItemMn","A"},
        {"newBNMn","B"},
        {"newOOBNMn","O"},
        
        {"learningItemMn","L"},
        {"tanItemMn","T"},
        {"banItemMn","B"},
        {"ILearningItemMn","I"},
        
        {"logicItemMn","L"},
        {"likelihoodWeightingItemMn","W"},
        {"gibbsItemMn","G"},
 
        {"operationError","Operation Error"},        
        {"oVariableAlreadyIsArgumentError","Ord. Variable is already an argument of this node!"},
        {"properties","Properties..."},
        {"nameException","Name Error"},
        {"nameDuplicated", "The name already exists..."}, 
        {"nameReserved", "Name is a reserved word."},
        {"nameEmpty","The name cannot be empty"},
        {"nameError","The name must have only letters, numbers, parentheses, and commas."},
        {"descriptionError","The description must have only letters, numbers, parentheses, commas, and spaces."}, 
        
        /* Resident Panel */
        {"stateEditionTip", "Edit States"}, 
        {"argumentEditionTip", "Edit Arguments"}, 
        {"tableEditionTip", "Edit Table"}, 
        {"existentStatesDialogTip", "Existent States"}, 
        
        {"addSelectedStatesTip", "Add selected states"}, 
        
        /* Input Panel */
        {"inputOf", "Origem node"}, 
        
        /* Arguments Typed Pane */
        {"nodeLabel", "Node"}, 
        {"openTip", "Open"}, 
        
        /* Formula Pane */
        {"addOVariableTip", "Add ordinary variable"}, 
        {"addNodeTip", "Add node"}, 
        {"addEntityTip", "Add entity"}, 
        {"addSkolenTip", "Add skolen"}, 
        
        /* Query Panel*/
        {"queryPanelTitle","Query"}, 
        {"queryBtnBack","Back"}, 
        {"queryBtnSelect","Select"},
        {"queryBtnExecute","Execute"},
        
        {"argumentFault","Incomplete arguments. Query should not be executed."}, 
        {"inconsistentArgument","Inconsistent arguments. Query should not be executed."},         
        {"selectOneVariable","Select a random variable:"}, 
        {"selectArgsValues","Select the arguments' values:"}, 
        
        /* Findings Panel */
        {"stateLabel","State:"}, 
        {"booleanLabel","Boolean"}, 
        {"categoricalLabel","Categorical"}, 
        
        /* Randon Variable Finding Panel */
        {"editNodeFindingTip","Edit findings of selected node"}, 
        {"removeFindingTip","Remove selected finding"}, 
        {"backToNodeSelectionTip","Back to node selection pane"}, 
        {"addFindingTip","Add finding"}, 
        
        /* FormulaTreeConstructionException */
        {"notOperator", "Operator is not acceptable at this position"},  
		
		{"sucess", "Sucess"}, 
		{"error", "Error"},
        
		/* Tips for buttons of the table edition */
		{"clear", "clear"}, 
		{"ifAny", "if any"}, 
		{"ifAll", "if all"}, 
		{"else", "else"}, 
		{"default", "default"}, 
		{"equal", " = "}, 
		{"and", " & "}, 
		{"or", " | "}, 
		{"not", " ~ "}, 
		{"card", "card"}, 
		{"max", "max"}, 
		{"min", "min"}, 
		
		{"deleteTip", "Delete selected text"}, 
		{"anyTip", "Insert statement \"If any\""}, 
		{"allTip", "Insert statement \"If all\""}, 
		{"defaultTip", "Insert statement \"default probability\""}, 
		{"elseTip", "Insert \"else\""}, 
		{"equalTip", "Insert equal operator"}, 
		{"andTip", "Insert statement AND"}, 
		{"orTip", "Insert statement OR"}, 
		{"notTip", "Insert statement NOT"}, 
		{"cadinalityTip", "Insert statement CARDINALITY"}, 
		{"maxTip", "Insert statement MAX"}, 
		{"minTip", "Insert statement MIN"}, 
		{"saveTip", "Save the table"}, 
		{"statesTip", "Show states of the node"}, 
		{"fatherTip", "Show parents of the node"}, 
		{"argTip", "Show arguments of the node"}, 
		{"exitTip", "Exit whithout save"}, 
		{"compileCPTTip", "Compile table"},
		{"saveCPTTip", "Save table"},
		{"exitCPTTip", "Close table"},
		{"fatherCPTTip", "Open list of fathers/fathers' states"},
		{"argumentCPTTip", "Open list of arguments"},
		{"statesCPTTip", "Open list of states of this node"},
		
		{"position", "Position"},
		
		/* CPT Edition messages */
		{"compileCPT", "Compile"},
		{"saveCPT", "Save"},
		{"exitCPT", "Exit"},
		{"fatherCPT", "Fathers"},
		{"argumentCPT", "Arguments"},
		{"statesCPT", "States"},
		
		{"CptSaveOK", "Table save sucessfull"},
		{"CptCompileOK", "Table compile sucessfull"},
		
		{"apply", "Apply"},
		
		
		/* Edition of states */
		{"insertBooleanStates", "Insert boolean states"}, 
		{"categoryStatesTip", "Insert category states"}, 
		{"objectStatesTip", "Insert object entity states"}, 
		{"booleanStatesTip", "Insert boolean states"}, 
		{"addStateTip", "Add state(s)"}, 
		{"removeState", "Remove state(s)"}, 
		{"addPreDefinedState", "Add a pre-defined state"}, 
		{"confirmation", "Confirmation"}, 
		{"warningDeletStates", "The previous states will be removed. Are you sure you want to proceed with this operation?"},
		
		
		
		/* Aboult pane */
		{"AboultPane" , "About"},			
		
		{"ReadLicense" , "License"},
		{"Features" , "Features"},
		{"VersionHistory" , "History"},
		{"CloseAboultPane" , "Close"},
		
		{"Collaborators" , "Collaborators"},
		{"Version" , "Version"},	
		{"Buildid" , "Build ID"},	
		
		{"stableStatusAlpha" , "Alpha"},	
		{"stableStatusBeta" , "Beta"},	
		{"stableStatusStable" , "Stable"},	
	
		//Warning panel
		{"warningFound" , "Warnings"},	
		{"openWarningDialogToolTip" , "Open warning list"},
		{"nodeCause" , "Warning during evaluation of node"},
		
		//SSBN panel
		{"saveSSBNToolTip" , "Save generated SSBN"},
		{"openWarningDialogToolTip" , "Open warning list"},
		
        //Splash loader */
		{"loading" , "loading"},
		
		/* Inference Algorithm Option Pane */
		{"algorithmTab" , "Inference Algorithm"},
		{"junctionTreeAlgorithmName" , "Junction Tree"},
		{"likelihoodWeightingAlgorithmName" , "Likelihood Weighting"},
		{"gibbsAlgorithmName" , "Gibbs"},
		{"gaussianMixtureAlgorithmName" , "Gaussian Mixture"},
		
		/* Continuous Normal Distribution Pane */
		{"meanLabel" , "Mean"},
		{"varianceLabel" , "Variance"},
		{"constantLabel" , "k"},
		{"followsLabel" , "~"},
		{"normalFunctionLabel" , "N(mean,variance)"},
		{"continuousNormalDistributionParamError" , "The values must be real numbers."},
		

		/* Plugin Support */
		{"pluginMenu" , "Plugins"},
		{"newPlugin" , "New "},
		{"reloadPlugin" , "Reload Plugins"},
		{"reloadPluginToolTip" , "Refresh loaded plugins, located at \"plugins\" folder."},
		{"noModuleDesktop" , "This module/plugin has no associated java desktop environment."},
		{"unsupportedGraphFormat" , "This format is not supported by this module/plugin."},
		{"moduleLoadingError" , "An error has occurred while loading a module/plugin. "},
		{"moduleConflict" , "There was a conflict between modules/plugins"},
		{"moduleConflictMessage" , "More than one module/plugin can handle this element. Choose one."},
		
		// list of modules/plugins which are already incorporated into core
		{"PNModuleName" , "Probabilistic Network"},
		
		
		{"sampleSizeInputMessage", "Please enter the sample size (number of trials)."},
//		{"sampleSizeInputTitle", "Sample size"}, 
		{"algorithmParameters", "Parameters for the algorithm"},
		{"availableAlgorithms", "Available algorithms"},
		
		{"selectNodeType", "Select a type of node"},
		
		{"CPFTableTitle", "Table"},
		{"CPFTableToolTip", "Edit the conditional probability using a table"},
		

		{"IOConflictTitle", "I/O extension conflict"},
		{"IOConflictMessage", "More than one I/O component can handle this file extension. Please, choose one."},
		
		// log dialogue
		{"printLogToolTip", "Print this log"},
		{"previewLogToolTip", "Preview the log"},
		{"closeButtonLabel", "Close the log"},
		{"logDialogTitle", "System Log"},
		{"printException", "Error while printing"},
		
		// plugin dependency logs
		{"pluginDependencyLogTitle", "Plugin dependency error"},
		{"pluginDependencyLogKeyMessage", "Could not solve the following plugins' dependencies:"},
		{"pluginDependencyLogID", "Plugin ID: "},
		{"pluginDependencyLogDependencies", "Unsolved dependencies: "},
		{"pluginDependencyLogPleaseCheck", "Please, add these dependencies into your plugins folder."},
	};
}