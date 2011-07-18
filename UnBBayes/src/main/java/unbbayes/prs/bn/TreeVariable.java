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

import unbbayes.prs.Node;

/**
 * Abstract class for variables that will be shown in the tree of nodes and states with 
 * their probabilities in the compilation panel.
 */
public abstract class TreeVariable extends Node implements java.io.Serializable {

    // Clique associated to this variable.
    protected IRandomVariable cliqueAssociado;

    // Store the marginal list (they add to 1).
    protected float[] marginalList;
    
    private float[] marginalCopy;

    private int evidence = -1;
    private boolean hasLikelihood = false;

    /**
     * This method has to be overwritten in order to 
     * show the correct marginal list in the tree.
     */
    protected abstract void marginal();
    
    public void initMarginalList() {
    	marginalList = new float[getStatesSize()];
    }
    
    //by young
	public boolean isMarginalList() 
	{  				
		if( marginalList != null )
			return true;
		
		return false;		
	}
   //by young end
	
    void copyMarginal() {
    	int size = marginalList.length;
    	marginalCopy = new float[size];
    	System.arraycopy(marginalList, 0, marginalCopy, 0, size);
    }
    
    void restoreMarginal() {
    	int size = marginalList.length;
    	System.arraycopy(marginalCopy, 0, marginalList, 0, size);
    }
    
    /**
     *  Return the marginal probability associated to a specific index/state.
     *
     *@param index the state of the node.
     *@return the marginal probability associated to the state defined by index.
     */
    public float getMarginalAt(int index) {
	//by young
    	if( marginalList != null )
    		return marginalList[index];
    	return -0.0f;
    //by young end
    }

    void setMarginalAt(int index, float value) {
        marginalList[index] = value;
    }
    
    public void setMarginalProbabilities(float marginalProbabilities[]) {
        for (int i = 0; i < getStatesSize(); i++) {
            setMarginalAt(i, marginalProbabilities[i]);
        }
    }

    /**
     * Reset the value of the evidence, which by default is -1 when there is no evidence.
     */
    public void resetEvidence() {
        evidence = -1;
    }


    /**
     * Returns true if there is evidence associated to the node, false otherwise.
     *
     * @return true if there is evidence associated to the node, false otherwise.
     */
    public boolean hasEvidence() {
        return (evidence != -1);
    }


    /**
     * Returns the index associated to the node that is the evidence of this node.
     * @return the index associated to the node that is the evidence of this node.
     */
    public int getEvidence() {
        return evidence;
    }
    
    public void resetLikelihood() {
    	if (hasLikelihood) {
    		evidence = -1;
    		hasLikelihood = false;
    	}
    }
    
    public boolean hasLikelihood() {
    	return hasLikelihood;
    }


    /**
     * Add the state associated to the given index as the evidence.
     *
     * @param stateIndex the index of the state to be set as evidence.
     */
    public void addFinding(int stateIndex) {
        float[] likelihood = new float[getStatesSize()];
        evidence = stateIndex;
        likelihood[stateIndex] = 1;
        setMarginalProbabilities(likelihood);
    }

    /**
     * Add likelihood to the variable.
     *
     * @param likelihood probabilities associated to every state of this node.
     */
    public void addLikeliHood(float likelihood[]) {
    	hasLikelihood = true;
    	// Does it matter which state is set as evidence?
    	// For now we are choosing the one with the highest probability.
    	float largestProb = likelihood[0];
    	evidence = 0;
        for (int i = 0; i < getStatesSize(); i++) {
//            setMarginalAt(i, likelihood[i]);
            if (likelihood[i] > largestProb) {
            	largestProb = likelihood[i];
            	evidence = i;
            }
        }
        
        for (int i = 0; i < getStatesSize(); i++) {
            setMarginalAt(i, likelihood[i]/largestProb);
        }
        
    }

    /**
     *  Returns the clique associated to this variable.
     *
     *@return the clique associated to this variable.
     */
    protected IRandomVariable getAssociatedClique() {
        return this.cliqueAssociado;
    }

    /**
     *  Associate this variable to the given clique.
     *
     *@param  clique the clique to associate to this variable.
     */
    protected void setAssociatedClique(IRandomVariable clique) {
        this.cliqueAssociado = clique;
    }
	

	protected void updateEvidences() {
		if (evidence != -1) {						
			PotentialTable auxTab = (PotentialTable)cliqueAssociado.getProbabilityFunction();
			int index = auxTab.indexOfVariable(this);
			auxTab.computeFactors();
			updateRecursive(auxTab, 0, 0, index, 0);			
		}
	}
	
	private void updateRecursive(PotentialTable tab, int c, int linear, int index, int state) {
    	if (c >= tab.variableList.size()) {
    		tab.dataPT.data[linear] *= marginalList[state];
    		return;    		    		
    	}
    	
    	if (index == c) {
    		for (int i = tab.variableList.get(c).getStatesSize() - 1; i >= 0; i--) {    		    		
	    		updateRecursive(tab, c+1, linear + i*tab.factorsPT[c] , index, i);
    		}
    	} else {
	    	for (int i = tab.variableList.get(c).getStatesSize() - 1; i >= 0; i--) {    		    		
	    		updateRecursive(tab, c+1, linear + i*tab.factorsPT[c] , index, state);
    		}
    	}
    }
	
}