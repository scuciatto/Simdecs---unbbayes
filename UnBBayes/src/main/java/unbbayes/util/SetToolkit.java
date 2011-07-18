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
package unbbayes.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import unbbayes.prs.Node;

/**
 *  Classe que fornece m�todos est�ticos para opera��es (uni�o e intersecao)
 *  entre conjuntos (List). A opera��o de subtra��o e de pertin�ncia s�o
 *  feitas utilizando m�todos do pr�prio List.
 *
 *@author     Michael e Rommel
 */
public class SetToolkit {
	
	/** Load resource file from this package */
  	private static ResourceBundle resource = unbbayes.util.ResourceController.newInstance().getBundle(
  			unbbayes.util.resources.UtilResources.class.getName());

    /**
     *  Realiza a uni�o entre dois conjuntos.
     *
     *@param  conjuntoA  conjunto A
     *@param  conjuntoB  conjunto B
     *@return            A uni�o B
     */
    public static List union(List<?> conjuntoA, List<?> conjuntoB) {
        List<Object> result = (ArrayList<Object>)clone(conjuntoA);
        for (int c1 = 0; c1 < conjuntoB.size(); c1++) {
            if (! conjuntoA.contains(conjuntoB.get(c1))) {
                result.add(conjuntoB.get(c1));
            }
        }

        return result;
    }

    /**
     *  Make the union between two sets
     *
     *@param  conjuntoA  conjunto A
     *@param  conjuntoB  conjunto B
     *@return            A uni�o B
     */
    public static ArrayList<Node> union(ArrayList<Node> conjuntoA, ArrayList<Node> conjuntoB) {
    	ArrayList<Node> result = new ArrayList<Node>(conjuntoA.size() + conjuntoB.size());
        result.addAll(conjuntoA);
        for (int c1 = 0; c1 < conjuntoB.size(); c1++) {
            if (! conjuntoA.contains(conjuntoB.get(c1))) {
                result.add(conjuntoB.get(c1));
            }
        }

        return result;
    }

    /**
     *  Realiza a interse��o entre dois conjuntos.
     *
     *@param  conjuntoA  conjunto A
     *@param  conjuntoB  conjunto B
     *@return            A interse��o B
     */
    public static List intersection(List<?> conjuntoA, List<?> conjuntoB) {
        List<Object> result = (ArrayList<Object>)clone(conjuntoA);
        result.retainAll(conjuntoB);
        return result;
    }
    
    
	/**
     *  Realiza a interse��o entre dois conjuntos.
     *
     *@param  conjuntoA  conjunto A
     *@param  conjuntoB  conjunto B
     *@return            A interse��o B
     */
    public static  ArrayList<Node> intersection( ArrayList<Node> conjuntoA,  ArrayList<Node> conjuntoB) {
    	 ArrayList<Node> result = clone(conjuntoA);
        result.retainAll(conjuntoB);
        return result;
    }

    /**
     * 
     * @param conjunto
     * @return
     * @deprecated do not use this method anymore, because this does not implement type safety
     */
    public static List clone(Collection conjunto) {
        try {
            Class classe = conjunto.getClass();
            List result = (List)classe.newInstance();
            result.addAll(conjunto);
            return result;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(resource.getString("IllegalAccessException"));
        } catch (InstantiationException e) {
            throw new RuntimeException(resource.getString("InstantiationException"));
        }
    }
    
    public static ArrayList<Node> clone(ArrayList<Node> conjunto) {
    	ArrayList<Node> result = new ArrayList<Node>();
    	result.addAll(conjunto);
    	return result;
    }
}

