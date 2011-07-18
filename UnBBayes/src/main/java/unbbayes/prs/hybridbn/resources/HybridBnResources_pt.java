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
package unbbayes.prs.hybridbn.resources;

import java.util.ListResourceBundle;

/**
 * <p>Title: UnBBayes</p>
 * <p>Description: Arquivo de recurso para o pacote unbbayes.prs.hybridbn. Localization = portuguese.</p>
 * <p>Company: UnB</p>
 * @author Rommel Novaes Carvalho (rommel.carvalho@gmail.com)
 * @deprecated Continuous node is no longer supported in UnBBayes core. It has 
 * now been replaced by the CPS plugin available at http://sourceforge.net/projects/prognos/.
 * 
 */

public class HybridBnResources_pt extends ListResourceBundle {

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
	{			
		/* Continuous Node */
		{"continuousNodeInvalidParentException","Um nó contínuo apenas permite:\nPai:\n-Nó contínuo;\n-Nó probabilístico discreto.\nFilho:\n-Nó contínuo."},
		{"meanName","Média"},
		{"varianceName","Variância"}
	};
}