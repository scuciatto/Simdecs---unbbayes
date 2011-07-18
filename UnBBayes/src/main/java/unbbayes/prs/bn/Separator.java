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
import unbbayes.prs.id.UtilityTable;

/**
 *  Representa um separador na �rvore de Jun��o (JunctionTree) entre cliques.
 *
 *@author     Michael e Rommel
 */
public class Separator implements IRandomVariable, java.io.Serializable {

	/** Serialization runtime version number */
	private static final long serialVersionUID = 0;
	
    private PotentialTable tabelaPot;
    private PotentialTable utilityTable;
    private ArrayList<Node> nos;

    /**
     *  Guarda o primeiro clique, quando h� orienta��o assume sem�ntica como origem.
     */
    private Clique clique1;

    /**
     *  Guarda o segundo clique, quando h� orienta��o assume sem�ntica como destino.
     */
    private Clique clique2;
    
    private Separator() {
    	nos = new ArrayList<Node>();
        tabelaPot = new ProbabilisticTable();
        utilityTable = new UtilityTable();
    }
    
    /**
     *  Constructor for the Separator. It updates the cliques, 
     * adding clique2 to the child list of clique1 and setting 
     * clique1 as the parent of clique2. 
     *
     * @param clique1 the origin clique
     * @param clique2 the destination clique
     */
    public Separator(Clique clique1, Clique clique2) {
    	this(clique1, clique2, true);        
    }
    
    /**
     *  Constructor for the Separator.
     *  
     * @param c1
     * @param c2
     * @param updateCliques
     */
    public Separator(Clique clique1, Clique clique2, boolean updateCliques) {
    	this();    	
        this.clique1 = clique1;
        this.clique2 = clique2;
        if (updateCliques) {
	        clique2.setParent(clique1);
	        clique1.addChild(clique2);
        }
    }


    /**
     *  Insere uma nova lista de n�s clusterizados.
     *
     *@param  nodeList  lista de n�s clusterizados.
     */
    public void setNodes(ArrayList<Node> nos) {
        this.nos = nos;
    }


    /**
     *  Retorna a tabela de potencial associada ao separador.
     *
     *@return    tabela de potencial associada ao separador
     */
    public PotentialTable getProbabilityFunction() {
        return tabelaPot;
    }

    /**
     *  Retorna a tabela de utilidade associada ao separador.
     *
     *@return    tabela de utilidade associada ao separador
     */
    public PotentialTable getUtilityTable() {
        return utilityTable;
    }


    /**
     *  Retorna a lista de n�s clusterizados.
     *
     *@return    n�s clusterizados
     */
    public ArrayList<Node> getNodes() {
        return nos;
    }


    /**
     *  Retorna o primeiro n�.
     *
     *@return    n� 1
     */
    public Clique getClique1() {
        return clique1;
    }


    /**
     *  Retorna o segundo n�.
     *
     *@return    n� 2
     */
    public Clique getClique2() {
        return clique2;
    }

}

