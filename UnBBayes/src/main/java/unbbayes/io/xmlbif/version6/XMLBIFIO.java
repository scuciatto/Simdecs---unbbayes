package unbbayes.io.xmlbif.version6;

//by young 
import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.xml.sax.InputSource;

import unbbayes.gui.HierarchicTree;
import unbbayes.io.exception.LoadException;
import unbbayes.io.xmlbif.version5.VariableType;
import unbbayes.io.xmlbif.version6.xmlclasses.ObjectFactory;
import unbbayes.io.xmlbif.version6.xmlclasses.XMLBIF;
import unbbayes.io.xmlbif.version6.xmlclasses.XMLBIFType;
import unbbayes.prs.Edge;
import unbbayes.prs.Node;
import unbbayes.prs.bn.ExplanationPhrase;
import unbbayes.prs.bn.IProbabilityFunction;
import unbbayes.prs.bn.IRandomVariable;
import unbbayes.prs.bn.PotentialTable;
import unbbayes.prs.bn.ProbabilisticNode;
import unbbayes.prs.bn.SingleEntityNetwork;
import unbbayes.prs.exception.InvalidParentException;
import unbbayes.prs.hybridbn.CNNormalDistribution;
import unbbayes.prs.hybridbn.ContinuousNode;
import unbbayes.prs.id.DecisionNode;
import unbbayes.prs.id.UtilityNode;
import unbbayes.simdecs.PerguntaNodo;
import unbbayes.util.ArrayMap;
import unbbayes.util.Debug;

public class XMLBIFIO {
	
	/* 
	 * Recuperates a net from a XML file
	 * input: file where the net resides
	 * pn: net where the load should be done (note:the net should be already created)
	 * */ 
	
	public static void loadXML(File input, SingleEntityNetwork pn) throws LoadException, IOException, JAXBException{
		
		InputSource isource = new InputSource(new FileInputStream(input));
	
		JAXBContext context = JAXBContext.newInstance("unbbayes.io.xmlbif.version6.xmlclasses");    	
		Unmarshaller unmarshaller = context.createUnmarshaller();
		
		unmarshaller.setValidating(true);
		

			XMLBIF XMLBIF = (XMLBIF) unmarshaller.unmarshal(isource); 
			XMLBIFType.HeaderType header = XMLBIF.getHeader(); 
			pn.setName(header.getName()); 
			
			// --------------- Static Property -------------------------------------------------
			
			XMLBIFType.StaticPropertyType staticProperty = XMLBIF.getStaticProperty();  
			
			pn.setRadius(staticProperty.getNodeSize() / 2); 		    
			// TODO -----> setNODEFONTNAME();
			// TODO -----> setNODEFONTSIZE();
			//by young 
			//UtilityNode.setColor(staticProperty.getColorUtilityNode());
			//DecisionNode.setColor(staticProperty.getColorDecisionNode());
			//ProbabilisticNode.setDescriptionColor(staticProperty.getColorDiscreteProbabilisticNode());
			//ProbabilisticNode.setExplanationColor(staticProperty.getColorExplanationNode());
			//ContinuousNode.setColor(staticProperty.getColorContinuousProbilisticNode());
			//by young end
			// ------------------- Hierarchy -------------------------------------------------------
			
			XMLBIFType.HierarchyType hierarchy = XMLBIF.getHierarchy(); 
			
			if((hierarchy.getRoot()).size() > 0){ 
				DefaultMutableTreeNode root; 
				root = loadHierarchicTree( hierarchy );

				// construct tree
				DefaultTreeModel model = new DefaultTreeModel(root);
				HierarchicTree hierarchicTree =
					new HierarchicTree(model);

				pn.setHierarchicTree(hierarchicTree);
			}
			
			// ------------------- Variables -------------------------------------------------------
			
			XMLBIFType.NetworkType network = XMLBIF.getNetwork();
			
			XMLBIFType.NetworkType.VariablesType variables = network.getVariables(); 
			
			List variableList = variables.getVariable(); 
			
			for(int i = 0; i < variableList.size(); i++){
				pn.addNode(makeNode((XMLBIFType.NetworkType.VariablesType.VariableType)variableList.get(i)));
			}
			
			// ------------------- Structure (Edges) -------------------------------------------------------
			
			XMLBIFType.NetworkType.StructureType structure = (XMLBIFType.NetworkType.StructureType)network.getStructure();
			
			List edgeList = structure.getEdge();
			
			for(int i = 0; i < edgeList.size(); i++){
				XMLBIFType.NetworkType.StructureType.EdgeType edge = (XMLBIFType.NetworkType.StructureType.EdgeType)edgeList.get(i); 
				try {
					pn.addEdge(new Edge(pn.getNode(edge.getParent()), pn.getNode(edge.getChild())));
				} catch (InvalidParentException e) {
					throw new LoadException(e.getMessage());
				} 
			}
			
			// ------------------- Distribution -------------------------------------------------------
			
			XMLBIFType.NetworkType.ConditionalDistributionSetType conditionalDistributionSet = (XMLBIFType.NetworkType.ConditionalDistributionSetType)network.getConditionalDistributionSet();
			
			List distributionList = conditionalDistributionSet.getConditionalDistribution();
			
			for(int i = 0; i < distributionList.size(); i++){
				XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType distribution = (XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType)distributionList.get(i);
				
				Node childNode = pn.getNode(distribution.getOwner().getName());
				
				XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.ParentsType parents = distribution.getParents();
				

				
				XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.CPTType cpt = distribution.getCPT();
				XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType normal = distribution.getNormal();
				
				if (cpt != null) {
					
					List dpiList = cpt.getDependentParentIndex();
					
					PotentialTable table = (PotentialTable)((IRandomVariable)childNode).getProbabilityFunction();
					
					if (parents != null) {
						List parentList = parents.getParent();
						
						for (int j = 0; j < parentList.size(); j++) {
							XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.ParentsType.ParentType parent = (XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.ParentsType.ParentType)parentList.get(j);
							// Force the change in the parent list order on the CPT to make sure the values in the distribution will be correct.
							table.setVariableAt(parent.getIndex() + 1, pn.getNode(parent.getName()));
						}
					}
					
					for (int dpiIndex = 0; dpiIndex < dpiList.size(); dpiIndex++) {
						
						XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.CPTType.DependentParentIndexType dpi = (XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.CPTType.DependentParentIndexType)dpiList.get(dpiIndex);			
						table.setValue(dpi.getIndex(), (float)dpi.getValue());
						
					}
					
				} else if (normal != null) {
					CNNormalDistribution normalDistribution = ((ContinuousNode)childNode).getCnNormalDistribution();
					
					// Make sure all the structure is created and that the parents are in the right order.
					normalDistribution.refreshParents();
					
					List functionList = normal.getFunction();
					
					for (int functionIndex = 0; functionIndex < functionList.size(); functionIndex++) {
						XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType.FunctionType function = (XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType.FunctionType)functionList.get(functionIndex);
						
						normalDistribution.setMean(function.getMean(), function.getIndex());
						normalDistribution.setVariance(function.getVariance(), function.getIndex());
						
						XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType.FunctionType.ConstantsType constants = function.getConstants();
						
						if (constants != null) {
							List constantList = constants.getConstant();
							
							for (int constantIndex = 0; constantIndex < constantList.size(); constantIndex++) {
								XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType.FunctionType.ConstantsType.ConstantType constant = (XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType.FunctionType.ConstantsType.ConstantType)constantList.get(constantIndex);
								normalDistribution.setConstantAt(constant.getIndex(), constant.getValue(), function.getIndex());
							}
						}
					}
				}
			}	
	}
	
	/* Saves the net in XML file
	 * net: net to be saved
	 */
	public static void saveXML(FileWriter arqoutput, SingleEntityNetwork net) throws JAXBException {
		
		PrintWriter arq = new PrintWriter(arqoutput);
		
		ObjectFactory of = new ObjectFactory();
		
		XMLBIF xmlBif = of.createXMLBIF(); 
		
		xmlBif.setVersion(.6);
		
		/*---------------- HEADER ---------------------*/
		
		XMLBIFType.HeaderType header = of.createXMLBIFTypeHeaderType(); 
		
		header.setName(net.getName());
		// TODO Create possibility of versions in UnBBayes
		header.setVersion(1); 
		header.setCreator("UnBBayes");
		
		xmlBif.setHeader(header);
		
		/*---------------- STATICPROPERTY ---------------------*/
		
		XMLBIFType.StaticPropertyType staticProperty = of.createXMLBIFTypeStaticPropertyType(); 
		
		staticProperty.setNodeSize((int) net.getRadius() * 2);
		
		// TODO -----> setNODEFONTNAME();
		// TODO -----> setNODEFONTSIZE();
		
		//by young
		//staticProperty.setColorUtilityNode(UtilityNode.getColor().getRGB());
		//staticProperty.setColorDecisionNode(DecisionNode.getColor().getRGB());
		//staticProperty.setColorDiscreteProbabilisticNode(ProbabilisticNode.getDescriptionColor().getRGB()); 
		//staticProperty.setColorExplanationNode(ProbabilisticNode.getExplanationColor().getRGB());
		//staticProperty.setColorContinuousProbilisticNode(ContinuousNode.getColor().getRGB());
		//by young end
		
		xmlBif.setStaticProperty(staticProperty);
				
		/*------------------HIERARCHY---------------------------------------*/
		HierarchicTree ht = net.getHierarchicTree();
		
		XMLBIFType.HierarchyType hierarchy = of.createXMLBIFTypeHierarchyType();  	
		
		TreeModel model = ht.getModel();	    	
		TreeNode root = (TreeNode)model.getRoot();
		
		processTreeNode(root, model, hierarchy); 
		
		xmlBif.setHierarchy(hierarchy);
		
		/*------------------NETWORK---------------------------------------*/
		
		XMLBIFType.NetworkType network = of.createXMLBIFTypeNetworkType(); 
		
		writeXMLBIFNetwork(network, net); 
		
		xmlBif.setNetwork(network); 
		
		
		JAXBContext context = JAXBContext.newInstance("unbbayes.io.xmlbif.version6.xmlclasses");    	
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "US-ASCII");
		marshaller.marshal(xmlBif , arq); 	
	}    
	
	
	private static void writeXMLBIFNetwork(XMLBIFType.NetworkType XMLBIFNetwork, SingleEntityNetwork net) throws JAXBException{
		
		ObjectFactory of = new ObjectFactory();
		
		// ------------------- Variables -------------------------------------------------------
		
		XMLBIFType.NetworkType.VariablesType networkVariables = of.createXMLBIFTypeNetworkTypeVariablesType();    	 
		
		for (int i = 0; i < net.getNodeCount(); i++) {
			
			Node node = net.getNodeAt(i);
			
			XMLBIFType.NetworkType.VariablesType.VariableType variable = of.createXMLBIFTypeNetworkTypeVariablesTypeVariableType();
			
			variable.setName(node.getName());
			if (node.getType() == Node.CONTINUOUS_NODE_TYPE) {
				variable.setType(VariableType.CONTINUOUSPROBABILISTIC.toString()); 
			} else if (node.getType() == Node.UTILITY_NODE_TYPE) {
				variable.setType(VariableType.UTILITY.toString());
			} else if (node.getType() == Node.DECISION_NODE_TYPE) {
				variable.setType(VariableType.DECISION.toString());
			} else {
				variable.setType(VariableType.DISCRETEPROBABILISTIC.toString());
			}
			variable.setXPos((int) node.getPosition().getX());
			variable.setYPos((int) node.getPosition().getY());
			variable.setWidth(node.getWidth());
			variable.setHeight(node.getHeight());
			variable.setRgbColor((int) node.getColor().getRGB());
			
			variable.setDescription(node.getDescription());
			// TODO set variable mmedia (UnBBayes does not support yet)
			
			//Atributos do Simdecs
			variable.setValorEtapa((float) node.getSimdecsCustoEtapa());
			variable.setTempoEtapa((float) node.getSimdecsTempoEtapa());
			variable.setBogus(node.getSimdecsIsBogus());
			
			//Vetor de perguntas
			Vector<PerguntaNodo> perguntas = node.getSimdecsPerguntasEtapa();
			PerguntaNodo tmpPergunta;
			String csv = "";
			
			for (i = 0; i < perguntas.size(); i++) {
				tmpPergunta = perguntas.get(i);
				csv = csv+tmpPergunta.getPergunta()+";";
			}
	        variable.setPerguntas(csv);
			
			for (int j = 0; j < node.getStatesSize(); j++) {
				XMLBIFType.NetworkType.VariablesType.VariableType.StateType state = of.createXMLBIFTypeNetworkTypeVariablesTypeVariableTypeStateType();
				state.setName(node.getStateAt(j));
				// TODO set state description and mmedia (UnBBayes does not support yet) 
				variable.getState().add(state);					
			}
			
			
			// Metaphore
			
			if (node.getInformationType() == Node.EXPLANATION_TYPE){
				XMLBIFType.NetworkType.VariablesType.VariableType.MetaphoreType metaphore = of.createXMLBIFTypeNetworkTypeVariablesTypeVariableTypeMetaphoreType(); 
				
				//Description
				String explanationDescription = node.getExplanationDescription();
				metaphore.setDescription(explanationDescription);
				
				// Explanation 
				ArrayMap<String,ExplanationPhrase> arrayMap = node.getPhrasesMap();
				int size = arrayMap.size();
				ArrayList keys = arrayMap.getKeys();
				for (int k = 0; k < size; k++)
				{
					Object key = keys.get(k);
					ExplanationPhrase explanationPhrase = arrayMap.get(key);
					
					XMLBIFType.NetworkType.VariablesType.VariableType.MetaphoreType.ExplanationType explanation = of.createXMLBIFTypeNetworkTypeVariablesTypeVariableTypeMetaphoreTypeExplanationType();
					explanation.setEvidence(explanationPhrase.getNode());
					explanation.setComments(explanationPhrase.getPhrase());
										
					switch(explanationPhrase.getEvidenceType()){
					case ExplanationPhrase.COMPLEMENTARY_EVIDENCE_TYPE:
						explanation.setEvidenceType(EvidenceType.COMPLEMENTARY.toString());
						break;
					case ExplanationPhrase.EXCLUSIVE_EVIDENCE_TYPE:
						explanation.setEvidenceType(EvidenceType.EXCLUSIVE.toString());
						break; 
					case ExplanationPhrase.NECESSARY_EVIDENCE_TYPE:
						explanation.setEvidenceType(EvidenceType.NECESSARY.toString());
						break; 
					case ExplanationPhrase.TRIGGER_EVIDENCE_TYPE:
						explanation.setEvidenceType(EvidenceType.TRIGGER.toString());
						break;
					case ExplanationPhrase.NA_EVIDENCE_TYPE:
						explanation.setEvidenceType(EvidenceType.NA.toString());
						break;         
					}  
					
					metaphore.getExplanation().add(explanation);
				}  
				
				variable.setMetaphore(metaphore); 
			}  
			
			networkVariables.getVariable().add(variable); 
		}
		
		XMLBIFNetwork.setVariables(networkVariables); 
		
		// ------------------- Structure (Edges) -------------------------------------------------------
		
		XMLBIFType.NetworkType.StructureType networkStructure = of.createXMLBIFTypeNetworkTypeStructureType();    	 
		
		for (Edge edge : net.getEdges()) {
			
			XMLBIFType.NetworkType.StructureType.EdgeType structureEdge = of.createXMLBIFTypeNetworkTypeStructureTypeEdgeType(); 
			
			structureEdge.setParent(edge.getOriginNode().getName());
			structureEdge.setChild(edge.getDestinationNode().getName());
			
			networkStructure.getEdge().add(structureEdge); 
		}
		
		XMLBIFNetwork.setStructure(networkStructure); 
		
		
		// ------------------- Distribution -------------------------------------------------------
		
		XMLBIFType.NetworkType.ConditionalDistributionSetType conditionalDistributionSet = of.createXMLBIFTypeNetworkTypeConditionalDistributionSetType();
		
		for (Node node : net.getNodes()) {
			
			// Decision node does not have a distribution!
			if (node instanceof DecisionNode) {
				continue;
			}
			
			XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType conditionalDistribution = of.createXMLBIFTypeNetworkTypeConditionalDistributionSetTypeConditionalDistributionType();
			
			// Add owner 
			XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.OwnerType owner = of.createXMLBIFTypeNetworkTypeConditionalDistributionSetTypeConditionalDistributionTypeOwnerType(); 
			owner.setName(node.getName());
			conditionalDistribution.setOwner(owner);
			
			// Add parents (for all kinds of nodes)
			XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.ParentsType parents = of.createXMLBIFTypeNetworkTypeConditionalDistributionSetTypeConditionalDistributionTypeParentsType();
			for (int i = 0; i < node.getParents().size(); i++) {
				XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.ParentsType.ParentType parent = of.createXMLBIFTypeNetworkTypeConditionalDistributionSetTypeConditionalDistributionTypeParentsTypeParentType();
				parent.setIndex(i);
				parent.setName(node.getParents().get(i).getName());
				parents.getParent().add(parent);
			}
			// Just add parents tag if there is a parent to be added.
			if (!parents.getParent().isEmpty()) {
				conditionalDistribution.setParents(parents);
			}
			
			// CPT
			if (node instanceof IRandomVariable) { 
				
				XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.CPTType cpt = of.createXMLBIFTypeNetworkTypeConditionalDistributionSetTypeConditionalDistributionTypeCPTType();
				PotentialTable potTable = (PotentialTable)((IRandomVariable) node).getProbabilityFunction();
				
				// Add parents in the right order, so the values in the CPT are correct (overwrites the previous parents added).
				parents = of.createXMLBIFTypeNetworkTypeConditionalDistributionSetTypeConditionalDistributionTypeParentsType();
				for (int i = 1; i < potTable.variableCount(); i++) {
					XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.ParentsType.ParentType parent = of.createXMLBIFTypeNetworkTypeConditionalDistributionSetTypeConditionalDistributionTypeParentsTypeParentType();
					Node parentNode = (Node)potTable.getVariableAt(i);
					parent.setIndex(i - 1);
					parent.setName(parentNode.getName());
					parents.getParent().add(parent);
				}
				// Just add parents tag if there is a parent to be added.
				if (!parents.getParent().isEmpty()) {
					conditionalDistribution.setParents(parents);
				}
				
				// CPT values - DPI
				for (int i = 0; i < potTable.tableSize(); i++) {
					XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.CPTType.DependentParentIndexType dpi = of.createXMLBIFTypeNetworkTypeConditionalDistributionSetTypeConditionalDistributionTypeCPTTypeDependentParentIndexType();
					dpi.setIndex(i);
					dpi.setValue(potTable.getValue(i));
					cpt.getDependentParentIndex().add(dpi);
				}
				
				conditionalDistribution.setCPT(cpt);
				conditionalDistribution.setType(DistributionType.DISCRETE.toString());
			// NormalDistribution
			} else if (node instanceof ContinuousNode) {
				XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType normal = of.createXMLBIFTypeNetworkTypeConditionalDistributionSetTypeConditionalDistributionTypeNormalType();
				CNNormalDistribution distribution = ((ContinuousNode)node).getCnNormalDistribution();
				
				for (int functionIndex = 0; functionIndex < distribution.functionSize(); functionIndex++) {
					XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType.FunctionType function = of.createXMLBIFTypeNetworkTypeConditionalDistributionSetTypeConditionalDistributionTypeNormalTypeFunctionType();
					function.setIndex(functionIndex);
					function.setMean(distribution.getMean(functionIndex));
					function.setVariance(distribution.getVariance(functionIndex));
					
					XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType.FunctionType.ConstantsType constants = of.createXMLBIFTypeNetworkTypeConditionalDistributionSetTypeConditionalDistributionTypeNormalTypeFunctionTypeConstantsType();
					for (int constantIndex = 0; constantIndex < distribution.getConstantListSize(); constantIndex++) {
						XMLBIFType.NetworkType.ConditionalDistributionSetType.ConditionalDistributionType.NormalType.FunctionType.ConstantsType.ConstantType constant = of.createXMLBIFTypeNetworkTypeConditionalDistributionSetTypeConditionalDistributionTypeNormalTypeFunctionTypeConstantsTypeConstantType();
						constant.setIndex(constantIndex);
						constant.setValue(distribution.getConstantAt(constantIndex, functionIndex));
						constants.getConstant().add(constant);
					}
					// Just add the constants tag if it has some constant to be added.
					if (!constants.getConstant().isEmpty()) {
						function.setConstants(constants);
					}
					
					normal.getFunction().add(function);
				}
				
				conditionalDistribution.setNormal(normal);
				conditionalDistribution.setType(DistributionType.CONTINUOUS.toString());
			}
			
			conditionalDistributionSet.getConditionalDistribution().add(conditionalDistribution);
			
		} //for

		XMLBIFNetwork.setConditionalDistributionSet(conditionalDistributionSet);
		
	} 
	
	private static Node makeNode(XMLBIFType.NetworkType.VariablesType.VariableType variable){
		
		Node node = null;
		VariableType nodeType = VariableType.valueOf(variable.getType().toUpperCase());
		
		if (nodeType == VariableType.DISCRETEPROBABILISTIC) {
			node = new ProbabilisticNode();
		} else if (nodeType == VariableType.CONTINUOUSPROBABILISTIC) { 
			node = new ContinuousNode();
		} else if (nodeType == VariableType.DECISION) {
			node = new DecisionNode();
		} else if (nodeType == VariableType.UTILITY) {
			node = new UtilityNode();
		}
		
		node.setName(variable.getName());
		node.setDescription(variable.getDescription());
		node.setPosition(variable.getXPos(), variable.getYPos());
		node.setSize(variable.getWidth(), variable.getHeight());
		node.setColor(new Color(variable.getRgbColor()));
		
		// Atributos do SimDeCs
		node.setSimdecsCustoEtapa(variable.getValorEtapa());
		node.setSimdecsTempoEtapa(variable.getTempoEtapa());
		node.setSimdecsIsBogus(variable.isBogus());
		String[] perguntas = variable.getPerguntas().split(";");
		
		for (int i=0; i < perguntas.length; i++) {
			PerguntaNodo pergunta = new PerguntaNodo();
			pergunta.setPergunta(perguntas[i]);
			node.adicionarPerguntaEtapa(pergunta);
		}
		
				
		// Only add states for non continuous node.
		if (nodeType != VariableType.CONTINUOUSPROBABILISTIC) {
			List stateList = variable.getState(); 
			
			for (int j = 0; j < stateList.size(); j++){
				XMLBIFType.NetworkType.VariablesType.VariableType.StateType state = (XMLBIFType.NetworkType.VariablesType.VariableType.StateType)stateList.get(j);
				node.appendState(state.getName());
				// TODO set description
				// TODO set mmedia
			}
		}
		
		// Fill Metaphore values if present
		XMLBIFType.NetworkType.VariablesType.VariableType.MetaphoreType metaphore = (XMLBIFType.NetworkType.VariablesType.VariableType.MetaphoreType)variable.getMetaphore(); 
		if (metaphore != null){
			
			node.setInformationType(Node.EXPLANATION_TYPE);
			
			node.setExplanationDescription(metaphore.getDescription());
			
			/* Explanation */
			List explanationList = metaphore.getExplanation(); 
			
			if(!(explanationList.isEmpty())){
				for (int i = 0; i < explanationList.size(); i++){
					XMLBIFType.NetworkType.VariablesType.VariableType.MetaphoreType.ExplanationType explanation = (XMLBIFType.NetworkType.VariablesType.VariableType.MetaphoreType.ExplanationType)explanationList.get(i); 
					ExplanationPhrase explanationPhrase = new ExplanationPhrase();
					explanationPhrase.setNode(explanation.getEvidence());
					explanationPhrase.setPhrase(explanation.getComments());
					
					EvidenceType evidenceType = EvidenceType.valueOf(explanation.getEvidenceType().toUpperCase());
					if (evidenceType == EvidenceType.TRIGGER) {
						explanationPhrase.setEvidenceType(ExplanationPhrase.TRIGGER_EVIDENCE_TYPE);
					} else if (evidenceType == EvidenceType.EXCLUSIVE) {
						explanationPhrase.setEvidenceType(ExplanationPhrase.EXCLUSIVE_EVIDENCE_TYPE);
					} else if (evidenceType == EvidenceType.NECESSARY) {
						explanationPhrase.setEvidenceType(ExplanationPhrase.NECESSARY_EVIDENCE_TYPE);
					} else if (evidenceType == EvidenceType.COMPLEMENTARY) {
						explanationPhrase.setEvidenceType(ExplanationPhrase.NA_EVIDENCE_TYPE);
					} else if (evidenceType == EvidenceType.NA) {
						
					}
					
					node.addExplanationPhrase(explanationPhrase);
				}	
			}
		}
		
		if (node instanceof IRandomVariable) {
			IProbabilityFunction potTable = ((IRandomVariable)node).getProbabilityFunction();
			potTable.addVariable(node);
		}
		
		return node; 
	}
	
	/* 
	 * fills the XML model a root element followed by all its children, and aps logos, recursevely
	 * it does the same for all its children. 
	 */
	private static void processTreeNode (
			TreeNode node,
			TreeModel model, 
			XMLBIFType.HierarchyType hierarchy) throws JAXBException{
		
		ObjectFactory of = new ObjectFactory(); 
		List<TreeNode> childList = new ArrayList<TreeNode>(); //armazena os filhos do nodo 
		
		XMLBIFType.HierarchyType.RootType root = of.createXMLBIFTypeHierarchyTypeRootType(); 
		
		root.setName(node.toString());
		
		//insert children as level and save'em to be searched after
		int childCount = model.getChildCount(node);
		if (!node.isLeaf()) {
			for (int i = 0; i < childCount; i++) {
				//Insercao
				XMLBIFType.HierarchyType.RootType.LevelType level = of.createXMLBIFTypeHierarchyTypeRootTypeLevelType(); 
				level.setName(((TreeNode)model.getChild(node,i)).toString()); 					
				
				//Armazenar para percorre-lo depois
				childList.add((TreeNode)model.getChild(node,i)); 
				root.getLevel().add(level); 
				
			}
			
			hierarchy.getRoot().add(root); 
			
			//percorrer filhos
			for (int i = 0; i < childCount; i++){
				//Verificar as hierarchias dos filhos
				processTreeNode((TreeNode) model.getChild(node, i), model, hierarchy);	
			}
		}
		
	}
	
	
	private static DefaultMutableTreeNode loadHierarchicTree(XMLBIFType.HierarchyType hierarchy){
		
		boolean out = false; 
		
		ArrayList rootList = (ArrayList)hierarchy.getRoot(); 
		XMLBIFType.HierarchyType.RootType rootXML = (XMLBIFType.HierarchyType.RootType) rootList.get(0); 
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(rootXML.getName());
		
		DefaultMutableTreeNode actualNode = root;
		
		ArrayList<DefaultMutableTreeNode> rootsTree = new ArrayList<DefaultMutableTreeNode>(); 
		
		rootsTree.add(actualNode); 
		
		for(int i = 0; i < rootList.size(); i++){
			
			XMLBIFType.HierarchyType.RootType actualRoot = (XMLBIFType.HierarchyType.RootType) rootList.get(i); 
			
			actualNode = getActualNode(rootsTree, actualRoot.getName());  
			
			ArrayList childList = (ArrayList) actualRoot.getLevel();
			
			// add children
			for(int j = 0; j < childList.size(); j++){
				XMLBIFType.HierarchyType.RootType.LevelType child = (XMLBIFType.HierarchyType.RootType.LevelType)childList.get(j); 
				DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child.getName()); 

				actualNode.add(childNode); 
				rootsTree.add(childNode); 
			}		
		}
		return root; 
	}
	
	/* returns a node with specified name and removes it from the list*/
	private static DefaultMutableTreeNode getActualNode(ArrayList<DefaultMutableTreeNode> rootsTree, String name){
	    boolean found = false;  
		int index = 0; 
	    
		DefaultMutableTreeNode node = null; 
		while (found == false){
			if (name.compareTo(rootsTree.get(index).toString()) != 0){
				index++; 
			}
			else{
				found = true;
				node = rootsTree.get(index); 
			}
		}
		rootsTree.remove(index); 
		
		return node; 	
	}

}