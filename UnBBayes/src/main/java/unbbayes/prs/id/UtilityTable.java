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

import unbbayes.prs.INode;
import unbbayes.prs.Node;
import unbbayes.prs.bn.PotentialTable;

/**
 * Utility Potential Table
 * @author Michael
 */
public class UtilityTable extends PotentialTable implements java.io.Serializable {

	/** Serialization runtime version number */
	private static final long serialVersionUID = 0;	
	
    public UtilityTable() {
    }

    /**
     * Returns a new instance of UtilityTable. Implements the abstract method from PotentialTable.
     * @return a new instance of UtilityTable.
     */
    public PotentialTable newInstance() {
        return new UtilityTable();
    }

    /**
     *  Retira a vari�vel da tabela. Utilizado tamb�m para marginaliza��o generalizada.
     *
     *@param  variavel  Variavel a ser retirada da tabela.
     */
    public void removeVariable(INode variavel) {
    	computeFactors();
        int index = variableList.indexOf(variavel);
        if (variavel.getType() == Node.PROBABILISTIC_NODE_TYPE) {
//			sum(variaveis.size()-1, index, 0, 0);			
			sum(index);
        } else {        	
            DecisionNode decision = (DecisionNode) variavel;
            if (decision.hasEvidence()) {
                finding(variableList.size()-1, index, new int[variableList.size()], decision.getEvidence());
            } else {
                argMax(variableList.size()-1, index, new int[variableList.size()]);
            }
        }
        notifyModification();
        variableList.remove(index);
    }

	@Override
	public void removeVariable(INode variable, boolean normalize) {
		removeVariable(variable); 
	}
    
    protected void argMax(int control, int index, int coord[]) {
        if (control == -1) {
            int linearCoordToKill = getLinearCoord(coord);
            int linearCoordDestination = linearCoordToKill - coord[index]*factorsPT[index];
            float value = Math.max(dataPT.data[linearCoordDestination], dataPT.data[linearCoordToKill]);
            dataPT.data[linearCoordDestination] = value;
            dataPT.remove(linearCoordToKill);
            return;
        }

        int fim = (index == control) ? 1 : 0;
        Node node = variableList.get(control);
        for (int i = node.getStatesSize()-1; i >= fim; i--) {
            coord[control] = i;
            argMax(control-1, index, coord);
        }
    }

}