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
import java.awt.Graphics2D;
import java.util.ResourceBundle;

import unbbayes.prs.Node;
import unbbayes.prs.bn.IRandomVariable;
import unbbayes.prs.bn.PotentialTable;

/**
 *  This class represents the utility node.
 *
 *@author Michael Onishi 
 *@author Rommel Carvalho
 */
public class UtilityNode extends Node implements IRandomVariable, java.io.Serializable {

	/** Serialization runtime version number */
	private static final long serialVersionUID = 0;
	
    private PotentialTable utilTable;

    private static Color color = Color.cyan;
    
    /** Load resource file from this package */
  	private static ResourceBundle resource = unbbayes.util.ResourceController.newInstance().getBundle(
  			unbbayes.prs.bn.resources.BnResources.class.getName());

  	/**
     * Constructs a UtilityNode with an initialized table and 
     * an incremented DrawElement.
     */
    public UtilityNode() {
    	
	  	//by young
		setColor(Color.cyan);
		
        utilTable = new UtilityTable();
        states.add(resource.getString("utilityName"));
    }
    
    
    public int getType() {
    	return UTILITY_NODE_TYPE;    	
    }

    /**
     * N�o faz nada ao se tentar inserir um estado, pois
     * vari�veis de utilidade s� aceitam 1 estado.
     */
    public void appendState(String state) { }

    /**
     * N�o faz nada ao se tentar inserir um estado, pois
     * vari�veis de utilidade s� aceitam 1 estado.
     */
    public void removeLastState() { }

    /**
     *  Gets the tabelaPot attribute of the TVU object
     *
     *@return    The tabelaPot value
     */
    public PotentialTable getProbabilityFunction() {
        return this.utilTable;
    }

    
    /**
     *  Get the node's color.
     *	@return The node's color.
     */
    //by young
     
    public static Color getStaticColor() {
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
    
}