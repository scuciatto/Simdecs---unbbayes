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
import java.util.List;
import java.util.ResourceBundle;

import unbbayes.prs.Node;
import unbbayes.prs.id.DecisionNode;
import unbbayes.prs.id.UtilityTable;


/**
 *  Classe que representa um Clique na �rvore de Jun��o (JunctionTree).
 *
 *@author    Michael e Rommel
 *@version   27 de Junho de 2001
 */
public class Clique implements IRandomVariable, java.io.Serializable {

	/** Serialization runtime version number */
	private static final long serialVersionUID = 0;	
	
	/** Load resource file from this package */
  	private static ResourceBundle resource = unbbayes.util.ResourceController.newInstance().getBundle(
  			unbbayes.prs.bn.resources.BnResources.class.getName());

    /**
     *  Identifica unicamente o n�.
     */
    private int index;

    /**
     *  Refer�ncia para o clique pai.
     */
    private Clique parent;

    /**
     *  Lista de n�s filhos.
     */
    private List<Clique> children;

    /**
     *  Tabela de Potencial Associada ao Clique.
     */
    private PotentialTable potentialTable;
    
    /**
     *  Tabela de Utilidade Associada ao Clique.
     */
    private PotentialTable utilityTable;    

    /**
     *  Lista de N�s Clusterizados.
     */
    private ArrayList<Node> nos;

    /**
     *  Lista de N�s Probabil�sticos associados ao Clique.
     */
    private ArrayList<Node> nosAssociados;

    /**
     *  Lista de N�s de Utilidade associados ao Clique.
     */
    private ArrayList<Node> associatedUtilNodes;    


    /**
     *  Constr�i um novo clique. Inicializa o vetor de filhos, de n�s clusterizados
     *  e de n�s associados. Inicializa o status associado para false.
     */
    public Clique() {
        children = new ArrayList<Clique>();
        nos = new ArrayList<Node>();
        nosAssociados = new ArrayList<Node>();
        associatedUtilNodes = new ArrayList<Node>();
        potentialTable = new ProbabilisticTable();
        utilityTable = new UtilityTable();
    }
    

    /**
     *  Normaliza um clique da �rvore.
     *
     *@param  ok      vetor boolean de tamanho 1 para passar parametro por refer�ncia.
     *@return         constante de normaliza��o.
     */
    public float normalize() throws Exception {
        boolean fixo[] = new boolean[nos.size()];
        ArrayList<Node> decisoes = new ArrayList<Node>();
        for (int i = 0; i < nos.size(); i++) {        	
            if (nos.get(i).getType() == Node.DECISION_NODE_TYPE) {
                decisoes.add(nos.get(i));
                fixo[i] = true;
            }
        }

        if (decisoes.size() == 0) {
            return normalizeBN();
        }

        int index[] = new int[decisoes.size()];
        for (int i = 0; i < index.length; i++) {
            index[i] = nos.indexOf(decisoes.get(i));
        }
        normalizeID(0, decisoes, fixo, index, new int[nos.size()]);
        /** @todo retornar a constante de normalizacao correta */
        return 0;
    }

    private float normalizeBN() throws Exception {
        float n = 0;
        float valor;

        int sizeDados = potentialTable.tableSize();
        for (int c = 0; c < sizeDados; c++) {
            n += potentialTable.getValue(c);
        }
        if (Math.abs(n - 1.0) > 0.001) {
            for (int c = 0; c < sizeDados; c++) {
                valor = potentialTable.getValue(c);
                if (n == 0.0) {
                    throw new Exception(resource.getString("InconsistencyUnderflowException"));
                }
                valor /= n;
                potentialTable.setValue(c, valor);
            }
        }
        return n;
    }


    private void normalizeID (int control,
    		ArrayList<Node> decisoes,
                             boolean fixo[],
                             int index[],
                             int coord[]) throws Exception {

        if (control == decisoes.size()) {
            float soma = sum(0, fixo, coord);
            if (soma == 0.0) {
            	for (int k = 0; k < decisoes.size(); k++) {
					System.out.println(decisoes.get(k) + " - " + decisoes.get(k).getStateAt(coord[index[k]]));
            	}
            	return;
//                throw new Exception(resource.getString("InconsistencyUnderflowException"));
            }
            div(0, fixo, coord, soma);            
            return;
        }

        DecisionNode node = (DecisionNode) decisoes.get(control);
        
        if (node.hasEvidence()) {
        	coord[index[control]] = node.getEvidence();
            normalizeID(control+1, decisoes, fixo, index, coord);
        	return;        	
        }
                
        for (int i = 0; i < node.getStatesSize(); i++) {        	
            coord[index[control]] = i;
			normalizeID(control+1, decisoes, fixo, index, coord);
        }
    }

    private float sum(int control, boolean fixo[], int coord[]) {
        if (control == nos.size()) {
            return potentialTable.getValue(coord);
        }

        if (fixo[control]) {
            return sum(control+1, fixo, coord);
        }

        Node node = nos.get(control);
        float retorno = 0;
        for (int i = 0; i < node.getStatesSize(); i++) {
            coord[control] = i;
            retorno += sum(control+1, fixo, coord);
        }
        return retorno;
    }

    private void div(int control, boolean fixo[], int coord[], float soma) {
        if (control == nos.size()) {
            int cLinear = potentialTable.getLinearCoord(coord);
            potentialTable.setValue(cLinear, potentialTable.getValue(cLinear) / soma);
            return;
        }

        if (fixo[control]) {
            div(control+1, fixo, coord, soma);
            return;
        }

        Node node = nos.get(control);
        for (int i = 0; i < node.getStatesSize(); i++) {
            coord[control] = i;
            div(control+1, fixo, coord, soma);
        }
    }


    /**
     *  Seta o pai deste clique
     *
     *@param  pai  o pai do clique
     */
    public void setParent(Clique pai) {
        this.parent = pai;
    }


    /**
     * Retorna o �ndice associado ao clique.
     *
     * @return �ndice associado ao clique.
     */
    public int getIndex() {
        return index;
    }

    /**
     * Muda o �ndice do clique.
     *
     * @param indice frag que indica o status de associa��o
     */
    public void setIndex(int indice) {
        this.index = indice;
    }


    /**
     *  Retorna o tamanho da lista de  filhos.
     *
     *@return    vetor de filhos.
     */
    public int getChildrenSize() {
        return children.size();
    }

    /**
     *  Adiciona um filho no final da lista de filhos.
     *
     *@param  child filho a ser inserido.
     */
    public void addChild(Clique child) {
        children.add(child);
    }
    
    /**
     * Remove the specified children
     * @param c the clique to remove from the children's list
     */
    public void removeChild(Clique c) {
    	children.remove(c);
    }

    /**
     *  Retorna o filho na posi��o especificada.
     *
     *@return    filho na posi��o especificada
     */
    public Clique getChildAt(int index) {
        return children.get(index);
    }


    /**
     *  Retorna o vetor de n�s clusterizados.
     *
     *@return    vetor de n�s clusterizados.
     */
    public ArrayList<Node> getNodes() {
        return nos;
    }


    /**
     *  Retorna o vetor de n�s probabil�sticos associados.
     *
     *@return    vetor de n�s probabil�sticos associados.
     */
    public ArrayList<Node> getAssociatedProbabilisticNodes() {
        return nosAssociados;
    }

    /**
     *  Retorna o vetor de n�s de utilidade associados.
     *
     *@return    vetor de n�s de utilidade associados.
     */
    public ArrayList<Node> getAssociatedUtilityNodes() {
        return associatedUtilNodes;
    }

    /**
     *  Retorna a tabela de potencial.
     *
     *@return    tabela de potencial.
     */
    public PotentialTable getProbabilityFunction() {
        return potentialTable;
    }

    /**
     *  Retorna a tabela de utilidade associada ao clique.
     *
     *@return    tabela de utilidade associada ao clique
     */
    public PotentialTable getUtilityTable() {
        return utilityTable;
    }
    
	/**
	 * Returns the parent.
	 * @return Clique
	 */
	public Clique getParent() {
		return parent;
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (int j = nos.size()-1; j>=0;j--) {
			sb.append(nos.get(j) + " ");				
		}
		return sb.toString();
	}

}