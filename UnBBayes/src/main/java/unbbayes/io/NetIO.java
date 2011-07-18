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
package unbbayes.io;

//by young 
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Stack;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;

import unbbayes.gui.HierarchicTree;
import unbbayes.io.exception.LoadException;
import unbbayes.prs.Edge;
import unbbayes.prs.Graph;
import unbbayes.prs.Node;
import unbbayes.prs.bn.ExplanationPhrase;
import unbbayes.prs.bn.IRandomVariable;
import unbbayes.prs.bn.PotentialTable;
import unbbayes.prs.bn.ProbabilisticNetwork;
import unbbayes.prs.bn.ProbabilisticNode;
import unbbayes.prs.bn.SingleEntityNetwork;
import unbbayes.prs.builder.IProbabilisticNetworkBuilder;
import unbbayes.prs.builder.impl.DefaultProbabilisticNetworkBuilder;
import unbbayes.prs.builder.impl.DefaultProbabilisticNodeBuilder;
import unbbayes.prs.exception.InvalidParentException;
import unbbayes.prs.hybridbn.ContinuousNode;
import unbbayes.util.ArrayMap;
import unbbayes.util.Debug;

/**
 * Manipulates input/output of NET files.
 * @author Rommel N. Carvalho
 * @author Michael S. Onishi
 * @author Mario Henrique Paes Vieira (mariohpv@bol.com.br)
 * @author Shou Matsumoto
 * @version 2.0
 */
public class NetIO implements BaseIO {

	/** Load resource file from this package */
	private static ResourceBundle resource =
		unbbayes.util.ResourceController.newInstance().getBundle(unbbayes.io.resources.IoResources.class.getName());

	private static final String ERROR_NET = resource.getString("errorNet");
	
	// since the StreamTokenizer is not counting linenumber depending on the configuration,
	// let's count it by ourselves
	protected long lineno = 1;
	
	/** Single array containing "net" */
	public static final String[] SUPPORTED_EXTENSIONS = {"net"};
	
	private String name = "NET";
	
	/**
	 *  Loads a NET format file using default node/network builder.
	 *  In other words, this method returns exactly instances of ProbabilisticNetwork filled
	 *  by DecisionNode, ProbabilisticNode and UtilityNode.
	 * @see DefaultProbabilisticNodeBuilder
	 * @param  input  file to be read.
	 * @return loaded net.
	 * @throws LoadException when there were errors loading the network
	 * @throws IOException in case there were errors when manipulating files.
	 */
	public Graph load(File input)
		throws LoadException, IOException {
		
		String id = input.getName();
		int index = input.getName().lastIndexOf('.');
		if (index > 0) {
			id = id.substring(0, index);
		}
		
		// create network using default builder
		IProbabilisticNetworkBuilder networkBuilder = DefaultProbabilisticNetworkBuilder.newInstance();
		
		ProbabilisticNetwork net = networkBuilder.buildNetwork(id);
		
		load(input, net, networkBuilder);
		return net;		
	}
	
	/**
	 *  Loads a NET format file using network builder
	 * @see IProbabilisticNetworkBuilder
	 * @param  input  file to be read.
	 * @param  networkBuilder: builder to be used in order to generate expected instances
	 * of probabilistic network, probabilistic nodes, decision nodes and utility nodes. This
	 * is useful if you want to reuse NetIO for networks/nodes which extends ProbabilisticNetwork,
	 * ProbabilisticNode, DecisionNode and UtilityNode (or else NetIO will be bound to those 
	 * superclasses only).
	 * @return loaded net.
	 * @throws LoadException when there were errors loading the network
	 * @throws IOException in case there were errors when manipulating files.
	 */
	public ProbabilisticNetwork load(File input, IProbabilisticNetworkBuilder networkBuilder)
		throws LoadException, IOException {
		
		int index = input.getName().lastIndexOf('.');
		String id = input.getName().substring(0, index);
		ProbabilisticNetwork net = networkBuilder.buildNetwork(id);
		load(input, net, networkBuilder);
		return net;		
	}

	/**
	 * Saves a network in basic NET file format.
	 *
	 * @param  output file where the net should be saved.
	 * @param graph network to be saved.
	 */
	public void save(File output, Graph graph) throws FileNotFoundException {
		
		
		
		PrintStream stream = new PrintStream(new FileOutputStream(output));
		
		SingleEntityNetwork net = (SingleEntityNetwork) graph;
		
		this.saveNetHeader(stream, net);

		
		// start filling node/decision/utility declaration
		
		int nodeSize = net.getNodeCount();
		Node auxNode1;
		for (int c1 = 0; c1 < nodeSize; c1++) {
			auxNode1 =  net.getNodeAt(c1);
			
			this.saveNodeDeclaration(stream, auxNode1, net);
			
		}
		/* 
		 * end of variable writing
		 * let's start writing potenciais!
		 */
		for (int c1 = 0; c1 < net.getNodeCount(); c1++) {
			auxNode1 = (Node) net.getNodeAt(c1);

			this.savePotentialDeclaration(stream, auxNode1, net);
		}
		stream.close();
	}
	
	
	protected void load(File input, SingleEntityNetwork net, IProbabilisticNetworkBuilder networkBuilder) 
				throws IOException, LoadException {
		
		BufferedReader r = new BufferedReader(new FileReader(input));
		StreamTokenizer st = new StreamTokenizer(r);
		this.setUpStreamTokenizer(st);

		// treat header
		
		getNext(st);
		if (st.sval.equals("net")) {
			this.loadNetHeader(st, net);
		} else {
			throw new LoadException(
				ERROR_NET + resource.getString("LoadException"));
		}

		// start treating body
		while (getNext(st) != StreamTokenizer.TT_EOF) {
			
			// if declaration is "node" type, treat it
			this.loadNodeDeclaration(st, net, networkBuilder);
			
			// if declaration is "continuous node" type, treat it
			this.loadContinuousNodeDeclaration(st, net, networkBuilder);
			
			// if declaration is "potential" type, treat it
			this.loadPotentialDeclaration(st, net);
			
			// ignore other declarations
		}
		
		r.close();
		
		this.setUpHierarchicTree(net);
	}	

	protected int getNext(StreamTokenizer st) throws IOException {
		do {
			st.nextToken();
			if (st.ttype == StreamTokenizer.TT_EOL) {
				this.lineno++;
			}
		} while (
			(st.ttype != StreamTokenizer.TT_WORD)
				&& (st.ttype != '"')
				&& (st.ttype != StreamTokenizer.TT_EOF));
		return st.ttype;
	}

	protected void loadHierarchicTree(
		StringBuffer sb,
		DefaultMutableTreeNode root) {
		int size = sb.length();
		DefaultMutableTreeNode nextRoot = null;
		Stack<DefaultMutableTreeNode> stack = new Stack<DefaultMutableTreeNode>();
		for (int i = 0; i < size; i++) {
			char c = sb.charAt(i);
			if (c == '(') {
				if (nextRoot != null) {
					stack.push(root);
					root = nextRoot;
				}
			} else if (c == ')') {
				if (stack.size() > 0) {
					root = (DefaultMutableTreeNode) stack.pop();
				}
			} else if (c == ',') {
			} else {
				StringBuffer newWord = new StringBuffer();
				while ((c != '(') && (c != ')') && (c != ',')) {
					newWord.append(c);
					i++;
					c = sb.charAt(i);
				}
				i--;
				DefaultMutableTreeNode newNode =
					new DefaultMutableTreeNode(newWord);
				nextRoot = newNode;
				root.add(newNode);
			}
		}
	}

	protected String saveHierarchicTree(HierarchicTree hierarchicTree) {
		TreeModel model = hierarchicTree.getModel();
		StringBuffer sb = new StringBuffer();
		TreeNode root = (TreeNode) model.getRoot();
		int childCount = model.getChildCount(root);
		if (childCount == 0) {
			return null;
		} else {
			sb.append('(');
			for (int i = 0; i < childCount; i++) {
				processTreeNode((TreeNode) model.getChild(root, i), sb, model);
				if (i != (childCount - 1)) {
					sb.append(',');
				}
			}
			sb.append(')');
			return sb.toString();
		}
	}

	protected void processTreeNode(
		TreeNode node,
		StringBuffer sb,
		TreeModel model) {
		sb.append(node.toString());
		if (!node.isLeaf()) {
			sb.append('(');
			int childCount = model.getChildCount(node);
			for (int i = 0; i < childCount; i++) {
				processTreeNode((TreeNode) model.getChild(node, i), sb, model);
				if (i != (childCount - 1)) {
					sb.append(',');
				}
			}
			sb.append(')');
		}
	}

	/**
	* Reads and skips all tokens before next end of line token.
	*
	* @param tokenizer Stream tokenizer
	* @throws IOException EOF not found
	*/
	protected void readTillEOL(StreamTokenizer tokenizer) throws IOException {
		while (tokenizer.nextToken() != StreamTokenizer.TT_EOL) {
		};
		tokenizer.pushBack();
	}

	protected String formatString(String string) {
		return string.replace('\n', '#');
	}

	protected String unformatString(String string) {
		return string.replace('#', '\n');
	}
	
	/**
	 * Configures valid/invalid character ranges of stream tokenizer.
	 * By setting up using protected method, it becomes easier to extend this class
	 * @param st: StreamTokenizer to set up
	 */
	protected void setUpStreamTokenizer(StreamTokenizer st) throws IOException {
		
		st.resetSyntax();
		
		st.wordChars('A', 'Z');
		st.wordChars('a', '}');
		st.wordChars('\u00A0', '\u00FF'); // characters with accents
		st.wordChars('_', '_');
		st.wordChars('-', '-');
		st.wordChars('0', '9');
		st.wordChars('.', '.');
		st.wordChars('%', '%');
		st.ordinaryChars('(', ')');
		st.eolIsSignificant(false);
		st.quoteChar('"');
		//st.commentChar('%');
		
	}

	/**
	 * Start loading net{} header from .net specification file; iterating under 
	 * {@link NetIO#loadNetHeaderBody(StreamTokenizer, SingleEntityNetwork)}
	 * in order to treat each declaration.
	 * {@link NetIO#loadNetHeaderBody(StreamTokenizer, SingleEntityNetwork)}
	 * must ignore incompatible declaration.
	 * @param st
	 * @param net
	 * @throws IOException
	 */
	protected void loadNetHeader(StreamTokenizer st, SingleEntityNetwork net)
								throws IOException {
		
		getNext(st);
		if (st.sval.equals("{")) {
			getNext(st);
			while (!st.sval.equals("}")) {
				this.loadNetHeaderBody(st, net);
				getNext(st);
			}			
		}
	}
	
	/**
	 * If a declaration inside net header is treatable, treat it.
	 * The currently compatible declarations are:
	 * 			name, node_size, tree, UnBBayes_Color_Utility, UnBBayes_Color_Decision,
	 * 			UnBBayes_Color_Probabilistic_Description, UnBBayes_Color_Probabilistic_Explanation.
	 * If declaration is not compatible, it will be ignored.
	 * @param st
	 * @param net
	 * @throws IOException
	 */
	protected void loadNetHeaderBody(StreamTokenizer st, SingleEntityNetwork net)
													throws IOException {
		
		if (st.sval.equals("name")) {
			getNext(st);
			net.setName(st.sval);
		} else if (st.sval.equals("node_size")) {
			getNext(st);
			getNext(st);
			net.setRadius(Double.parseDouble(st.sval) / 2);
		} else if (st.sval.equals("tree")) {
			getNext(st);
			StringBuffer sb = new StringBuffer(st.sval);
			DefaultMutableTreeNode root =
				new DefaultMutableTreeNode("Information Variable");
			loadHierarchicTree(sb, root);

			// construct tree
			DefaultTreeModel model = new DefaultTreeModel(root);
			HierarchicTree hierarchicTree =
				new HierarchicTree(model);

			net.setHierarchicTree(hierarchicTree);
		} else if (st.sval.equals("UnBBayes_Color_Utility")) {
			getNext(st);
			//by young
			//UtilityNode.setColor(Integer.parseInt(st.sval));
			Integer.parseInt(st.sval);
			//by young end
		} else if (st.sval.equals("UnBBayes_Color_Decision")) {
			getNext(st);
			//by young
			//DecisionNode.setColor(Integer.parseInt(st.sval));
			Integer.parseInt(st.sval);
			//by young end
		} else if (st.sval.equals("UnBBayes_Color_Probabilistic_Description")) {
            getNext(st);
            //by young
            Integer.parseInt(st.sval);
            //ProbabilisticNode.setDescriptionColor(Integer.parseInt(st.sval));
            //by young end
        } else if (st.sval.equals("UnBBayes_Color_Probabilistic_Explanation")) {
            getNext(st);
            //by young
            Integer.parseInt(st.sval);
            //ProbabilisticNode.setExplanationColor(Integer.parseInt(st.sval));
            //by young end
        }
		
	}
	
	/**
	 * If the current declaration is of type "node" (or "decision" or "utility"), loads
	 * that node (creating new instances using networkBuilder) and adds it to net.
	 * If declaration is not "node" (or "decision" or "utility"), it will not move the
	 * token index from st.
	 * @param st
	 * @param net
	 * @param networkBuilder
	 * @throws IOException
	 * @throws LoadException
	 */
	protected void loadNodeDeclaration (StreamTokenizer st, SingleEntityNetwork net, IProbabilisticNetworkBuilder networkBuilder)
										throws IOException , LoadException{
		if (st.sval.equals("node")
				|| st.sval.equals("decision")
				|| st.sval.equals("utility")) {
				Node auxNode = null;
				if (st.sval.equals("node")) {
//					auxNo = new ProbabilisticNode();
					auxNode = networkBuilder.getProbabilisticNodeBuilder().buildNode();
				} else if (st.sval.equals("decision")) {
//					auxNo = new DecisionNode();
					auxNode = networkBuilder.getDecisionNodeBuilder().buildNode();
				} else { // utility
//					auxNo = new UtilityNode();
					auxNode = networkBuilder.getUtilityNodeBuilder().buildNode();
				}

				getNext(st);
				auxNode.setName(st.sval);
				getNext(st);
				if (st.sval.equals("{")) {
					getNext(st);
					while (!st.sval.equals("}")) {
						this.loadNodeDeclarationBody(st, auxNode);
					}
					net.addNode(auxNode);
				} else {
					throw new LoadException(
						ERROR_NET
							+ " l."
							+ ((st.lineno() < this.lineno)?this.lineno:st.lineno())
							+ resource.getString("LoadException3"));
				}
			}
	}
	
	
	/**
	 * If the current declaration is of type "continuous node", loads
	 * that node (creating new instances using networkBuilder) and adds it to net.
	 * If declaration is not "continuous node", it will not move the
	 * token index from st.
	 * @param st
	 * @param net
	 * @param networkBuilder
	 * @throws IOException
	 * @throws LoadException
	 * @deprecated Continuous node is no longer supported in UnBBayes core. It has 
	 * now been replaced by the CPS plugin available at http://sourceforge.net/projects/prognos/.
	 */
	protected void loadContinuousNodeDeclaration (StreamTokenizer st, SingleEntityNetwork net, IProbabilisticNetworkBuilder networkBuilder)
										throws IOException , LoadException{
		if (st.sval.equals("continuous")) {
			this.getNext(st);
			if (st.sval.equals("node")) {
				Node continuousNode = networkBuilder.getContinuousNodeBuilder().buildNode();;
				
				getNext(st);
				continuousNode.setName(st.sval);
				getNext(st);
				if (st.sval.equals("{")) {
					getNext(st);
					while (!st.sval.equals("}")) {
						this.loadNodeDeclarationBody(st, continuousNode);
					}
					net.addNode(continuousNode);
				} else {
					throw new LoadException(
						ERROR_NET
							+ " l."
							+ ((st.lineno() < this.lineno)?this.lineno:st.lineno())
							+ resource.getString("LoadException3"));
				}
			}
		}
	}
	
	
	/**
	 * If the current declaration is "potential", treat that declaration and adds it
	 * to a node contained within net.
	 * If current declaration is not "potential", this method ignores it and remains the
	 * st untouched
	 * @param st
	 * @param net
	 * @throws IOException
	 * @throws LoadException
	 */
	protected void loadPotentialDeclaration(StreamTokenizer st, SingleEntityNetwork net) 
											throws IOException , LoadException {
		
		if (st.sval.equals("potential")) {
			
			IRandomVariable auxTableVar = null;
			PotentialTable auxPotentialTable = null;
			
			getNext(st);	// node name
			Node auxNode1 = net.getNode(st.sval);

			if (auxNode1 instanceof IRandomVariable) {
				auxTableVar = (IRandomVariable) auxNode1;
				// TODO find a way to implement without assumption of potential table
				auxPotentialTable = (PotentialTable)auxTableVar.getProbabilityFunction();
				auxPotentialTable.addVariable(auxNode1);
			}

			getNext(st);
			if (st.sval.equals("|")) {
				getNext(st);	// parent names
			}

			Node auxNo2;
			Edge auxArco;
			while (!st.sval.startsWith("{")) {
				auxNo2 = net.getNode(st.sval);
				auxArco = new Edge(auxNo2, auxNode1);
				try {
					net.addEdge(auxArco);
				} catch (InvalidParentException e) {
					throw new LoadException(e.getMessage());
				}
				getNext(st);
			}
			
			/*
			 * Invert the parents in the table, to
			 * mantain consistency in the program.
			 * Internal pre-requisite.
			 */
			if (auxNode1 instanceof IRandomVariable) {
				int sizeVetor = auxPotentialTable.variableCount() / 2;
				for (int k = 1; k <= sizeVetor; k++) {
					Object temp = auxPotentialTable.getVariableAt(k);
					auxPotentialTable.setVariableAt(
						k,
						auxPotentialTable.getVariableAt(
							auxPotentialTable.variableCount() - k));
					auxPotentialTable.setVariableAt(
						auxPotentialTable.variableCount() - k,
						(Node) temp);
				}
			}
			
			if (st.sval.length() == 1) {
				getNext(st);
			}
			
			if (st.sval.endsWith("}")) {
				// there were nothing declared
				Debug.println(this.getClass(), "Empty potential declaration found for " + auxNode1.getName());
			}

			while (!st.sval.endsWith("}")) {
				if (st.sval.equals("data")) {
					getNext(st);	// extract "normal"
					if (st.sval.equals("normal")) {
						// this is a continuous node
						this.loadPotentialDataContinuous(st, auxNode1);
					} else {
						// this is a ordinal node
						this.loadPotentialDataOrdinal(st, auxNode1);
					}
					
				} else {
					throw new LoadException(
						ERROR_NET
							+ " l."
							+ ((st.lineno() < this.lineno)?this.lineno:st.lineno())
							+ resource.getString("LoadException5"));
				}
			}
		}
	}
	
	
	/**
	 * Sets up the hierarchic tree after the network is completely loaded
	 * @param net
	 */
	protected void setUpHierarchicTree(SingleEntityNetwork net) {
		HierarchicTree tree = net.getHierarchicTree();
		if (tree != null) {
			tree.setProbabilisticNetwork(net,HierarchicTree.DESCRIPTION_TYPE);
		}
	}
	
	
	/**
	 * Reads inside the node declaration.
	 * 			node {[READS THIS CONTENT]}
	 * @param st
	 * @param node: node to be filled
	 * @throws IOException
	 */
	protected void loadNodeDeclarationBody(StreamTokenizer st , Node node) throws IOException, LoadException {
		if (st.sval.equals("label")) {
			getNext(st);
			node.setDescription(st.sval);
			getNext(st);
		} else if (st.sval.equals("position")) {
			getNext(st);
			int x = Integer.parseInt(st.sval);
			getNext(st);
			if (x <= 0) {
				//by young
				x = node.getWidth();
				//by young end
			}
			int y = Integer.parseInt(st.sval);
			if (y <= 0) {
				//by young
				y = node.getHeight();
				//by young end
			}
			
//			node.getPosition().setLocation(x, y);
			node.setPosition(x, y);
			
			getNext(st);
		}  //by young
		else if (st.sval.equals("color")) {
			getNext(st);
			int c = Integer.parseInt(st.sval);
		 
			node.setColor(new Color(c));
			
			getNext(st);
		} //by young end
		else if (st.sval.equals("states")) {
			while (getNext(st) == '"') {
				node.appendState(st.sval);
			}
		} else if (st.sval.equals("meanPerClass")) {
			ArrayList<String> array = new ArrayList<String>();
			while (getNext(st) == '"') {
				array.add(st.sval);
			}
			int size = array.size();
			double[] mean = new double[size];
			for (int i = 0; i < size; i++) {
				mean[i] = Double.parseDouble(array.get(i));
			}
			node.setMean(mean);
		} else if (st.sval.equals("stdDevPerClass")) {
			ArrayList<String> array = new ArrayList<String>();
			while (getNext(st) == '"') {
				array.add(st.sval);
			}
			int size = array.size();
			double[] stdDev = new double[size];
			for (int i = 0; i < size; i++) {
				stdDev[i] = Double.parseDouble(array.get(i));
			}
			node.setStandardDeviation(stdDev);
		} else if (st.sval.equals("%descricao")) {
			getNext(st);
			node.setExplanationDescription(
				unformatString(st.sval));
			node.setInformationType(Node.EXPLANATION_TYPE);
			readTillEOL(st);
			getNext(st);
		} else if (st.sval.equals("%frase")) {
			getNext(st);
			ExplanationPhrase explanationPhrase =
				new ExplanationPhrase();
			explanationPhrase.setNode(st.sval);
			getNext(st);
			try {
				explanationPhrase.setEvidenceType(
					Integer.parseInt(st.sval));
			} catch (Exception ex) {
				throw new LoadException(
					ERROR_NET
						+ " l."
						+ ((st.lineno() < this.lineno)?this.lineno:st.lineno())
						+ resource.getString("LoadException2")
						+ st.sval);
			}
			getNext(st);
			explanationPhrase.setPhrase(
				unformatString(st.sval));
			node.addExplanationPhrase(explanationPhrase);
			readTillEOL(st);
			getNext(st);
		} else if (st.sval.contains("HR_")) {
			// this is a HUGIN specific declaration.
			// let's ignore it
			Debug.println(this.getClass(), "Ignoring HR declaration: " + st.sval);
			while (getNext(st) == '"');
		} else {
			throw new LoadException(
				ERROR_NET
					+ " l."
					+ ((st.lineno() < this.lineno)?this.lineno:st.lineno())
					+ resource.getString("LoadException2")
					+ st.sval);
		}
	}
	
	/**
	 * Loads potential declaration's content assuming it is declaring
	 * ordinal "stateful" probability declaration
	 * @param st
	 * @param node
	 * @throws LoadException
	 * @throws IOException
	 */
	protected void loadPotentialDataOrdinal(StreamTokenizer st, Node node)
								throws LoadException , IOException {
		
		PotentialTable auxPotentialTable = (PotentialTable)((IRandomVariable)node).getProbabilityFunction();
		
		if (node.getType() == Node.DECISION_NODE_TYPE) {
			throw new LoadException(
				ERROR_NET
					+ " l."
					+ ((st.lineno() < this.lineno)?this.lineno:st.lineno())
					+ resource.getString("LoadException4"));
		}
//		this.getNext(st);
		int nDim = 0;
		while (!st.sval.equals("}")) {
			if (st.sval.equals("%")) {
				readTillEOL(st);
			} else {
				auxPotentialTable.setValue(
					nDim++,
					Float.parseFloat(st.sval));
			}
			getNext(st);
		}
	}
	
	/**
	 * Loads potential declaration assuming it is declaring continuous distribution
	 * @param st
	 * @param node
	 */
	protected void loadPotentialDataContinuous (StreamTokenizer st, Node node)
												throws LoadException , IOException {
		// TODO finish implementing this
		Debug.println(this.getClass(), "Continuous potential loading is not implemented yet: " + node.getName());
		while (!st.sval.equals("}")) {
			// just skip it...
			getNext(st);
		}
	}
	
	
	/**
	 * Fills the PrintStream with net{} header, starting with "net {" declaration and closing with "}"
	 * @param stream: stream to write to
	 * @param net: network to be saved to stream
	 */
	protected void saveNetHeader(PrintStream stream, SingleEntityNetwork net) {
		
		stream.println("net");
		stream.println("{");
		
		this.saveNetHeaderBody(stream, net);
		
		
		stream.println("}");
		stream.println();
		
	}
	
	/**
	 * Stores the content of net{[CONTENT]} header to a stream.
	 * the informations are: 
	 * 			node_size, name, tree, UnBBayes_Color_Probabilistic_Description, 
	 * 			UnBBayes_Color_Probabilistic_Explanation, UnBBayes_Color_Utility,
	 * 			UnBBayes_Color_Decision.
	 * @param stream
	 * @param net
	 */
	protected void saveNetHeaderBody(PrintStream stream, SingleEntityNetwork net) {
		stream.println(
				"     node_size = ("
					+ (int) (net.getRadius() * 2)
					+ " "
					+ (int) (net.getRadius() * 2)
					+ ");");
		stream.println("     name = \"" + net.getName() + "\";");
		String tree = saveHierarchicTree(net.getHierarchicTree());
		if (tree != null)
			stream.println("     tree = \"" + tree + "\";");
        stream.println("     UnBBayes_Color_Probabilistic_Description = \"" + ProbabilisticNode.getDescriptionColor().getRGB() + "\";");
        stream.println("     UnBBayes_Color_Probabilistic_Explanation = \"" + ProbabilisticNode.getExplanationColor().getRGB() + "\";");
		
        //by young
//      stream.println("     UnBBayes_Color_Utility = \"" + "1" + "\";");
//		stream.println("     UnBBayes_Color_Decision = \"" + "1" + "\";");
        //stream.println("     UnBBayes_Color_Utility = \"" + UtilityNode.getColor().getRGB() + "\";");
		//stream.println("     UnBBayes_Color_Decision = \"" + DecisionNode.getColor().getRGB() + "\";");
		//by young end
	}
	
	
	/**
	 * Writes to a PrintStream a node/decision/utility{} declaration.
	 * 						node|decision|utility [NAME] {[CONTENT]}
	 * @param stream
	 * @param node
	 * @param net
	 */
	protected void saveNodeDeclaration(PrintStream stream, Node node, SingleEntityNetwork net) {
		if (node instanceof ContinuousNode) {
			stream.print("continuous node");
		} else if (node.getType() == Node.PROBABILISTIC_NODE_TYPE) {
			stream.print("node");
		} else if (node.getType() == Node.DECISION_NODE_TYPE) {
			stream.print("decision");
		} else { // TVU
			stream.print("utility");
		}

		stream.println(" " + node.getName());
		stream.println("{");
		
		this.saveNodeDeclarationBody(stream, node, net);
		
		stream.println("}");
		stream.println();
	}
	
	/**
	 * Writes to a PrintStream the body of node/decision/utility{} declaration.
	 * 						node|decision|utility [NAME] {[THIS_CONTENT_IS_WRITTEN]}
	 * @param stream
	 * @param node
	 * @param net
	 */
	protected void saveNodeDeclarationBody(PrintStream stream, Node node, SingleEntityNetwork net) {
			
			this.saveNodeLabelAndPosition(stream, node);
		

			if (node instanceof ContinuousNode) {
				// a continuous node only needs to save label and position				
			} else {
				if (!(node.getType() == Node.UTILITY_NODE_TYPE)) {
					/* Check if the node represents a numeric attribute */
					if (node.getStatesSize() == 0) {
						/* The node represents a numeric attribute */
						double[] mean = node.getMean();
						double[] stdDev = node.getStandardDeviation();
						StringBuffer auxString = new StringBuffer();
						
						/* Mean per class */
						auxString.append("\"" + mean[0] + "\"");
						for (int i = 1; i < mean.length; i++) {
							auxString.append(" \"" + mean[i] + "\"");
						}
						stream.println(
								"     meanPerClass = (" + auxString.toString() + ");");
						
						/* Standard deviation per class */
						auxString = new StringBuffer();
						auxString.append("\"" + stdDev[0] + "\"");
						for (int i = 1; i < mean.length; i++) {
							auxString.append(" \"" + stdDev[i] + "\"");
						}
						stream.println(
								"     stdDevPerClass = (" + auxString.toString() + ");");
					} else {
						/* The node represents a nominal attribute */
						StringBuffer auxString =
							new StringBuffer("\"" + node.getStateAt(0) + "\"");

						int sizeEstados = node.getStatesSize();
						for (int c2 = 1; c2 < sizeEstados; c2++) {
							auxString.append(" \"" + node.getStateAt(c2) + "\"");
						}
						stream.println(
							"     states = (" + auxString.toString() + ");");
					}
				}
				
				if (node.getInformationType() == Node.EXPLANATION_TYPE)
		                        {
		                          String explanationDescription = formatString(node.getExplanationDescription());
		                          stream.println("     %descricao \"" + explanationDescription + "\"");
		                          ArrayMap arrayMap = node.getPhrasesMap();
		                          int size = arrayMap.size();
		                          ArrayList keys = arrayMap.getKeys();
		                          for (int i = 0; i < size; i++)
		                          {
		                            Object key = keys.get(i);
		                            ExplanationPhrase explanationPhrase = (ExplanationPhrase) arrayMap.get(key);
		                            stream.println("     %frase \""+ explanationPhrase.getNode()+ "\" "+ "\""
		                                + explanationPhrase.getEvidenceType()+ "\" "+ "\""
		                                + formatString(explanationPhrase.getPhrase())+ "\"");
		                          }
				}
			}
	}
	
	
	/**
	 * Stores to PrintStream the potential{} declaration
	 * @param stream
	 * @param node
	 * @param net
	 */
	protected void savePotentialDeclaration(PrintStream stream, Node node, SingleEntityNetwork net) {
		ArrayList<Node> auxParentList = node.getParents();

		stream.print("potential (" + node.getName());

		int sizeVa = auxParentList.size();
		if (sizeVa > 0) {
			stream.print(" |");
			for (int c2 = 0; c2 < sizeVa; c2++) {
				Node auxNo2 = null;
				// If this is a random variable, the order of the parents 
				// has to follow (be consistent) the order in the CPT
				if (node instanceof IRandomVariable) {
					PotentialTable auxTabPot =
						(PotentialTable)((IRandomVariable) node).getProbabilityFunction();
					// The order of the parents in the CPT is the inverse of the order it is added
					auxNo2 = (Node) auxTabPot.getVariableAt(sizeVa - c2);
				} else {
					auxNo2 = (Node) auxParentList.get(c2);
				}
				stream.print(" " + auxNo2.getName());
			}
		}
		
		stream.println(")");
		stream.println("{");
		
		this.savePotentialDeclarationBody(stream, node, net);

		stream.println("}");
		stream.println();
	}
	
	
	/**
	 * Stores to PrintStream the [BODY] of potential {[BODY]} declaration
	 * @param stream
	 * @param node
	 * @param net
	 */
	protected void savePotentialDeclarationBody(PrintStream stream, Node node, SingleEntityNetwork net) {
		if (node instanceof ContinuousNode) {
			// TODO stub!!
			// TODO implement continuous node's potential treatment
			Debug.println(this.getClass(), "TODO implement continuous node's potential treatment: " + node.getName());
			
			ContinuousNode continuous = (ContinuousNode)node;
			stream.print(" data = normal ( ");
			stream.print(continuous.getCnNormalDistribution().getMean(0));
			for (int i = 0; i < continuous.getParents().size(); i++) {
				// TODO implement continuous node's parent treatment. This is stub
				Debug.println(this.getClass(), "TODO implement continuous node's parent treatment: " + continuous.getParents().get(i).getName());
				stream.print(" + " + continuous.getCnNormalDistribution().getConstantAt(i, 0));
				stream.print(" * " + continuous.getParents().get(i).getName());
			}

			stream.print(", " + continuous.getCnNormalDistribution().getVariance(0));
			stream.println(" );");
		
		} else if (node instanceof IRandomVariable) {
			PotentialTable auxTabPot =
				(PotentialTable)((IRandomVariable) node).getProbabilityFunction();
			int sizeVa1 = auxTabPot.variableCount();

			stream.print(" data = ");
			int[] coord;
			boolean[] paren = new boolean[sizeVa1];

			int sizeDados = auxTabPot.tableSize();
			for (int c2 = 0; c2 < sizeDados; c2++) {
				coord = auxTabPot.getMultidimensionalCoord(c2);

				for (int c3 = 0; c3 < sizeVa1; c3++) {
					if ((coord[c3] == 0) && (!paren[c3])) {
						stream.print("(");
						paren[c3] = true;
					}
				}
				stream.print(" " + auxTabPot.getValue(c2));
				if ((c2 % node.getStatesSize())
					== node.getStatesSize() - 1) {
					stream.print(" ");
				}

				int celulas = 1;

				Node auxNo2;
				for (int c3 = 0; c3 < sizeVa1; c3++) {
					auxNo2 = (Node)auxTabPot.getVariableAt(c3);
					celulas *= auxNo2.getStatesSize();
					if (((c2 + 1) % celulas) == 0) {
						stream.print(")");
						if (c3 == sizeVa1 - 1) {
							stream.print(";");
						}
						paren[c3] = false;
					}
				}

				if (((c2 + 1) % node.getStatesSize()) == 0) {
					stream.println();
				}
			}
		}
	}
	
	/**
	 * Stores node's label = "[LABEL]"; and position = ([X], [Y]); declarations 
	 * inside "node" declaration's body
	 * @param stream
	 * @param node
	 */
	protected void saveNodeLabelAndPosition(PrintStream stream, Node node) {
		stream.println(
				"     label = \"" + node.getDescription() + "\";");
		stream.println(
			"     position = ("
				+ (int) node.getPosition().getX()
				+ " "
				+ (int) node.getPosition().getY()
				+ ");");
		
		//by young
		stream.println(
				"     color = ("
					+ (int)node.getColor().getRGB()
					+ ");");
		//by young end
	}

	/**
	 * Checks if file extension is compatible to what this i/o expects.
	 * @see #supports(File, boolean)
	 * @param extension
	 * @param isLoadOnly
	 * @return
	 */
	public boolean supports(String extension, boolean isLoadOnly) {
		return SUPPORTED_EXTENSIONS[0].equalsIgnoreCase(extension);
	}

	/*
	 * (non-Javadoc)
	 * @see unbbayes.io.BaseIO#getSupportedFileExtensions(boolean)
	 */
	public String[] getSupportedFileExtensions(boolean isLoadOnly) {
		return SUPPORTED_EXTENSIONS;
	}

	/*
	 * (non-Javadoc)
	 * @see unbbayes.io.BaseIO#getSupportedFilesDescription(boolean)
	 */
	public String getSupportedFilesDescription(boolean isLoadOnly) {
		return "Net (.net)";
	}
	
	/*
	 * (non-Javadoc)
	 * @see unbbayes.io.BaseIO#supports(java.io.File, boolean)
	 */
	public boolean supports(File file, boolean isLoadOnly) {
		String fileExtension = null;
		try {
			int index = file.getName().lastIndexOf(".");
			if (index >= 0) {
				fileExtension = file.getName().substring(index + 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return this.supports(fileExtension, isLoadOnly);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}