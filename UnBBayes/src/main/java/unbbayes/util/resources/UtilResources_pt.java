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
package unbbayes.util.resources;

import java.util.*;

/**
 * <p>Title: UnBBayes</p>
 * <p>Description: Arquivo de recurso para o pacote unbbayes.util. Localization = portuguese.</p>
 * <p>Copyright: Copyright (c) 2001</p>
 * <p>Company: UnB</p>
 * @author Rommel Novaes Carvalho
 * @author Michael Onishi
 * @version 1.0
 * @since 02/05/2002
 */

public class UtilResources_pt extends ListResourceBundle {

    /**
	 *  Sobrescreve getContents e retorna um array, onde cada item no array �
	 *	um par de objetos. O primeiro elemento do par � uma String chave, e o
	 *	segundo � o valor associado a essa chave.
	 *
	 * @return O conte�do dos recursos
	 */
	public Object[][] getContents() {
		return contents;
	}

	/**
	 * Os recursos
	 */
	static final Object[][] contents =
	{	{"IllegalCapacityException","Capacidade Ilegal: "},
		{"IllegalAccessException","Erro na rotina clone. Sem acesso"},
		{"InstantiationException","Erro na rotina clone. Instanciação"},
		
		// algoritmos de inferencia
		{"junctionTreeAlgorithmName" , "Árvore de Junção"},
		{"likelihoodWeightingAlgorithmName" , "Likelihood"},
		{"gibbsAlgorithmName" , "Gibbs"},
		{"gaussianMixtureAlgorithmName" , "Gaussian Mixture"},
		{"junctionTreeAlgorithmDescription" , "Compila a rede usando árvores de junção (padrão)"},
		{"likelihoodWeightingAlgorithmDescription" , "Compila a rede usando pesos"},
		{"gibbsAlgorithmDescription" , "Compila a rede usando algoritmo Gibbs"},
		{"gaussianMixtureAlgorithmDescription" , "Compila a rede usando algoritmo Gaussian Mixture"},
		
	};
}