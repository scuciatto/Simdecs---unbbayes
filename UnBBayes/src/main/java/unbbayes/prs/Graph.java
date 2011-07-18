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
package unbbayes.prs;

import java.util.ArrayList;
import java.util.List;

/** 
 * Interface for a graph building of Node's and Edge's
 */

public interface Graph {

		/**
		 *  Retorna os edgeList do grafo.
		 *
		 *@return    edgeList do grafo.
		 */
		public List<Edge> getEdges();

		/**
		 *  Retorna os n�s do grafo.
		 *
		 *@return    n�s do grafo.
		 * 
		 * @todo Eliminar esse metodo! eh utilizado na classe NetWindow
		 */
		public ArrayList<Node> getNodes();

		/**
		 *  Returna o n�mero de vari�veis da rede.
		 *
		 *@return    n�mero de vari�veis da rede.
		 */
		public int getNodeCount();


		/**
		 *  Retira do grafo o arco especificado.
		 *
		 *@param  arco  arco a ser retirado.
		 */
		public void removeEdge(Edge arco) ;

		/**
		 *  Adiciona novo n� ao grafo.
		 *
		 *@param  no  n� a ser inserido.
		 */
		public void addNode(Node no);

		/**
		 *  Adiciona o arco � rede.
		 *
		 *@param  arco  arco a ser inserido.
		 */
		public void addEdge(Edge arco) throws Exception;

		/**
		 *  Remove n� do grafo.
		 *
		 *@param  elemento  no a ser removido.
		 */
		public void removeNode(Node elemento);

		/**
		 *  Verifica exist�ncia de determinado arco.
		 *
		 *@param  no1  n� origem.
		 *@param  no2  n� destino.
		 *@return      posi��o do arco no vetor ou -1 caso n�o exista tal arco.
		 */
		public int hasEdge(Node no1, Node no2);

}
