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
package unbbayes.prs.bn;


import java.util.ArrayList;

import unbbayes.prs.Node;
import unbbayes.prs.id.JunctionTreeID;
import unbbayes.util.SetToolkit;

/**
 *  Representa uma rede probabil�ｽstica.
 *
 *@author     michael
 *@author     rommel
 */
public class ProbabilisticNetwork
	extends SingleEntityNetwork
	implements java.io.Serializable {

	  /** Serialization runtime version number */
	  private static final long serialVersionUID = 0;
			
	
	/**
	 *  Cria uma nova rede probabil�ｽstica. Limpa o arquivo de log e inicializa o
	 *  vetor da ordem de elimina�ｽ�ｽo.
	 */
	public ProbabilisticNetwork(String id) {
		super(id);							
		oe = new ArrayList<Node>();
		firstInitialization = true;
	}
	

	/**
	 *  Faz o processo de triangula�ｽ�ｽo da rede.
	 */
	private void triangula() {		
		Node aux;
		ArrayList<Node> auxNos;

		if (createLog) {
			logManager.append(resource.getString("triangulateLabel"));
		}
		auxNos = SetToolkit.clone(nodeList);
		removeUtilityNodes(auxNos);
		copiaNos = SetToolkit.clone(auxNos);
		int sizeDecisao = decisionNodes.size();
		for (int i = 0; i < sizeDecisao; i++) {
			aux = decisionNodes.get(i);
			auxNos.remove(aux);
			auxNos.removeAll(aux.getParents());
		}

		oe = new ArrayList<Node>(copiaNos.size());

		while (minimumWeightElimination(auxNos))
			;

		//        int index;
		for (int i = decisionNodes.size() - 1; i >= 0; i--) {
			aux = decisionNodes.get(i);
			oe.add(aux);
			int sizeAdjacentes = aux.getAdjacents().size();
			for (int j = 0; j < sizeAdjacentes; j++) {
				Node v = aux.getAdjacents().get(j);
				v.getAdjacents().remove(aux);
			}
			if (createLog) {
				logManager.append(
					"\t" + oe.size() + " " + aux.getName() + "\n");
			}

			auxNos = SetToolkit.clone(aux.getParents());
			auxNos.removeAll(decisionNodes);
			auxNos.removeAll(oe);
			for (int j = 0; j < i; j++) {
				Node decision = decisionNodes.get(j);
				auxNos.removeAll(decision.getParents());
			}

			while (minimumWeightElimination(auxNos)) 
				;
		}
		
		makeAdjacents();
	}

	private void removeUtilityNodes(ArrayList<Node> nodes) {
		for (int i = nodes.size() - 1; i >= 0; i--) {
			if (nodes.get(i).getType() == Node.UTILITY_NODE_TYPE) {
				nodes.remove(i);
			}
		}
	}


	/**
	 * It does all the steps to compile a network using junction tree method.
	 * The steps are: 
	 * 1. consistency check; 
	 * 2. Moralization; 
	 * 3. Triangulation; 
	 * 4. Compilation of Junction Tree
	 * 
	 * @deprecated use {@link JunctionTreeAlgorithm#run()}
	 */
	public void compile() throws Exception {
		// TODO remove double dispatch (JunctionTreeAlgorithm is calling this method) by migrating this code to JunctionTreeAlgorithm class
		if (nodeList.size() == 0) {
			throw new Exception(resource.getString("EmptyNetException"));
		}
		if (createLog) {
			logManager.reset();
		}
		verifyConsistency();
		moralize();
		triangula();		
		
		if (isID()) {
			compileJT(new JunctionTreeID());
		} else {
			compileJT(new JunctionTree());
		}
	}

}