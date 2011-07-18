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
package unbbayes.prs.id;

import java.awt.Color;

import unbbayes.prs.bn.Clique;
import unbbayes.prs.bn.PotentialTable;
import unbbayes.prs.bn.TreeVariable;
import unbbayes.util.SetToolkit;

/**
 *  Variavel de decisao
 *
 *@author     Michael e Rommel
 */
public class DecisionNode extends TreeVariable implements java.io.Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -4746720812779139329L;
	
	private static Color color = Color.orange;
	
	/**
     * Constructs a DecisionNode with an incremented DrawElement.
     */
    public DecisionNode() {
      	//by young
		setColor(Color.orange);
    }
    
    public Object clone() {
    	DecisionNode cloned = new DecisionNode();
    	//by young
		cloned.setDescription(this.getDescription());
		cloned.setName(this.getName());
		cloned.setPosition(this.getPosition().getX(), this.getPosition().getY());
		cloned.setParents(SetToolkit.clone(parents));
		cloned.setChildren(SetToolkit.clone(this.getChildren()));
		cloned.setStates(SetToolkit.clone(states));
		cloned.setAdjacents(SetToolkit.clone(this.getAdjacents()));
		cloned.setSelected(this.isSelected());
        cloned.setExplanationDescription(this.getExplanationDescription());
        cloned.setPhrasesMap(this.getPhrasesMap());
        cloned.setInformationType(this.getInformationType());
        float[] marginais = new float[super.marginalList.length];
        System.arraycopy(super.marginalList, 0, marginais, 0, marginais.length);
        cloned.marginalList = marginais;
        
        return cloned;
    }
    
    
    public int getType() {
    	return DECISION_NODE_TYPE;    	
    }

    /**
     *  Get the node's color.
     *	@return The node's color.
     */
    //by young
     public static Color getStaticColor() 
     {
         return color;
     }
    
    /**
     *  Set the node's color.
     *
     *@param rgb The node's RGB color.
     */
    //by young
   	 public static void setStaticColor(int rgb) {
   		 color = new Color(rgb);
    }
   	 
    protected void marginal() {
        marginalList = new float[getStatesSize()];
        PotentialTable auxTab = (PotentialTable)((Clique)cliqueAssociado).getUtilityTable().clone();
        auxTab.directOpTab((PotentialTable)cliqueAssociado.getProbabilityFunction(), PotentialTable.PRODUCT_OPERATOR);
        int index = auxTab.indexOfVariable(this);
        for (int i = 0; i < cliqueAssociado.getProbabilityFunction().variableCount(); i++) {
            if (i != index) {
                auxTab.removeVariable(((PotentialTable)cliqueAssociado.getProbabilityFunction()).getVariableAt(i));
            }
        }

        if (hasEvidence()) {
            marginalList[getEvidence()] = auxTab.getValue(getEvidence());
        } else {
            int tableSize = auxTab.tableSize();
            for (int i = 0; i < tableSize; i++) {
                marginalList[i] = auxTab.getValue(i);
            }
        }
    }
    
}