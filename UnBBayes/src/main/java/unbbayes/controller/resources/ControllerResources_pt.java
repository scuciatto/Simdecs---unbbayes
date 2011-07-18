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
package unbbayes.controller.resources;

import java.util.ListResourceBundle;

/**
 * <p>Title: UnBBayes</p>
 * <p>Description: Arquivo de recurso para o pacote unbbayes.controller. Localization = portuguese.</p>
 * <p>Copyright: Copyright (c) 2001</p>
 * <p>Company: UnB</p>
 * @author Rommel Novaes Carvalho, Michael Onishi
 * @version 1.0
 * @since 05/04/2002
 */

public class ControllerResources_pt extends ListResourceBundle {

    /**
	 *  Sobrescreve getContents e retorna um array, onde cada item no array eh
	 *	um par de objetos. O primeiro elemento do par eh uma String chave, e o
	 *	segundo eh o valor associado a essa chave.
	 *
	 * @return O conteudo dos recursos
	 */
	public Object[][] getContents() {
		return contents;
	}

	/**
	 * Os recursos
	 */
	static final Object[][] contents =
	{	{"imageFileFilter","PNG (.png), JPEG (.jpg), GIF (.gif), BMP (.bmp)"},
		{"likelihoodName","Likelihood"},
		{"likelihoodException","Só tem zeros!"},
		{"statusEvidenceProbabilistic","Probabilidade da Evidência Total: "},
		{"statusEvidenceException","Evidências não consistentes ou underflow"},
		{"statusError","Erro!"},
		{"printLogToolTip","Imprimir o log de compilação"},
		{"previewLogToolTip","Visualizar a impressão"},
		{"okButtonLabel"," Ok "},
		{"closeButtonLabel","Fechar"},
		{"statusTotalTime","Tempo Total: "},
		{"statusSeconds"," segundos"},
		{"stateProbabilisticName","Estado "},
		{"stateDecisionName","Ação "},
		{"stateUtilityName","Utilidade "},
		{"firstStateProbabilisticName","Estado 0"},
		{"firstStateDecisionName","Ação 0"},
		{"nodeName","Nó: "},
		
		// Error message
		{"noNodeSelectedError","Um nó deve ser selecionado."},
		
		//Barra de status
		{"statusLoadingKB","Carregando base de conhecimento..."},
		{"statusSavingKB","Salvando base de conhecimento..."},
		{"statusGeneratingSSBN","Gerando SSBN..."},
		{"statusReady","Ready"},		
		
		//MainController
		{"NewPNName","Nova BN"},
		{"NewMSBNName","Nova MSBN"},
		
		{"probabilisticNodeName","C"},
		{"decisionNodeName","D"},
		{"utilityNodeName","U"},
		{"contextNodeName","CX"},
		{"residentNodeName","RX"},
		{"inputNodeName","IX"},		
		{"ordinaryVariableName", "OX"}, 	
		{"entityName", "EX"}, 	
		
		{"domainMFragName","DMFrag"},	
		{"findingMFragName","FMFrag"},				
		
		{"copiedNodeName","Cópia do "},
		{"askTitle","Digite um rótulo para a rede"},
		{"informationText","Informação"},
		{"printException","Erro de Impressão: "},
		{"loadNetException","Erro ao Abrir a Rede"},
		{"cancelOption","Cancelar"},
		{"printerStatus","Status da Impressora"},
		{"initializingPrinter","Inicializando impressora..."},
		{"printingPage","Imprimindo página "},
		{"previewButtonLabel","Anterior"},
		{"nextButtonLabel","Próxima"},
		{"fitToPageButtonLabel","Ajustar para Página"},
		{"loading","Carregando "},
		{"cancel","Cancelar"},
		{"of"," de "},
		{"numberFormatError","O valor deve ser um número real."},
		
		
		{"JAXBExceptionFound", "Erro de sintaxe..."},

		/* Numeric attribute node */
		{"mean", "Média"},
		{"stdDev", "Desv. Padrão"}, 
		
		/* Java helper */
		{"helperDialogTitle", "Ajuda"},

		//Network Controller
		{"logDialogTitle", "Log"},
		
		//Result Dialog
		{"ResultDialog", "Resultado"}, 
		
		/* load/save */
		{"saveSucess", "Arquivo salvo com sucesso!"},
		{"bnDontExists", "Operao falhou: No há Rede Bayesiana ativa"},
		{"msbnDontExists", "Operao falhou: No há MSBN ativa"},
		{"windowDontExists", "Operao falhou: No há janela ativa"},
		{"sucess", "Sucesso"}, 
		{"error", "Erro"},
		{"loadHasError", "O arquivo foi carregado com alguns erros"},
		{"withoutPosfixe", "Tipo do arquivo não informado!"},
		
		/* Likelihood Weighting Inference */
		{"sampleSizeInputMessage", "Favor entrar com o tamanho da amostragem (número de casos)."},
		{"sampleSizeInputTitle", "Tamanho da amostragem"}, 
		{"sampleSizeInputError", "O tamanho da amostragem deve ser um número inteiro maior zero."},
		{"likelihoodWeightingNotApplicableError", 
			"O algoritmo de Likelihood Weighting só pode ser \n" +
			"usado com uma rede bayesiana. Favor escolher \n" +
			"outro algoritmo."},
		
		/* Continuous Inference */
		{"continuousInferenceNotApplicableError", 
			"O algoritmo de inferência para redes com nós \n" +
			"contínuos só pode ser usado com uma rede \n" +
			"bayesiana híbrida (com pelo menos um nó \n" +
			"contínuo). Favor escolher outro algoritmo."},
		
		/* Junction Tree Inference */
		{"junctionTreeNotApplicableError", 
			"O algoritmo de Árvore de Junção só pode ser usado com uma \n" +
			"rede bayesiana ou diagrama de influência. Favor escolher \n" +
			"outro algoritmo."},

		// file managing	
		{"cannotHandleFileFormat", "Este módulo não pode tratar este tipo de arquivo."},
		
		// Saving graph as image
		{"useTransparencyTitle", "Transparência"},
		{"useTransparencyMessage", "Você deseja usar transparência?"},
		{"useShadowTitle", "Sombra"},
		{"useShadowMessage", "Você deseja usar sombra?"},
		{"shadowSizeInputMessage", "Favor entrar com tamanho da sombra.\n(Tem que ser um número inteiro)"},
		{"shadowSizeInputErrorTitle", "Erro com Tamanho da Sombra"},
		{"shadowSizeInputErrorMessage", "O valor tem que ser um número inteiro."},
		{"shadowOpacityInputMessage", "Favor entrar com opacidade.\n(Tem que ser um número entre 0 e 1)"},
		{"shadowOpacityInputErrorTitle", "Erro com Opacidade da Sombra"},
		{"shadowOpacityInputErrorMessage", "O valor tem que ser um número entre 0 e 1."},
		
	};
}
