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
package unbbayes.gui.resources;

import java.util.ListResourceBundle;

/**
 * <p>Title: UnBBayes</p>
 * <p>Description: Arquivo de recurso para o pacote unbbayes.gui. Localization = portuguese.</p>
 * <p>Copyright: Copyright (c) 2001</p>
 * <p>Company: UnB</p>
 * @author Rommel Novaes Carvalho, Michael Onishi
 * @version 1.0
 * @since 05/04/2002
 */

public class GuiResources_pt extends ListResourceBundle {

    /**
	 *  Sobrescreve getContents e retorna um array, onde cada item no array é
	 *	um par de objetos. O primeiro elemento do par é uma String chave, e o
	 *	segundo é o valor associado a essa chave.
	 *
	 * @return O conteúdo dos recursos
	 */
	public Object[][] getContents() {
		return contents;
	}

	/**
	 * Os recursos
	 */
	static final Object[][] contents =
	{	
		//Types of files
		{"fileDirectoryType","Diretório"},
		{"fileARFFType","Arquivo Arff"},
		{"fileTXTType","Arquivo Texto TXT"},
		{"fileNETType","Arquivo de Rede Bayesiana NET"},
		{"fileGenericType","Arquivo Genérico"},
		
		{"allNetFileFilter","Arquivos Compatíveis"},
		{"textFileFilter","Text (.txt)"},
		{"fileUntitled","SemNome.txt"},
		
		//Titles of the file choosers
		{"saveTitle","Salvar"}, 
		{"openTitle","Abrir"}, 
		
		{"unbbayesTitle","UnBBayes"},
		
		//main toll bar tips 		
		{"newToolTip","Nova rede"},
		{"openToolTip","Abrir rede"},
		{"saveToolTip","Salvar rede"},
		{"learningToolTip","Modo de aprendizagem"},
		{"metalToolTip","Usar tema Metal"},
		{"motifToolTip","Usar tema Motif"},
		{"windowsToolTip","Usar tema Windows"},
		{"homeSystemToolTip","Usar tema local"},
		{"tileToolTip","Organizar as janelas em bloco"},
		{"cascadeToolTip","Organizar as janelas em cascata"},
		
		{"globalOptionTitle","Opções Globais"},
		{"hierarchyToolTip","Definição de hierarquia"},
        
		{"usaName","EUA"},
		{"chinaName","China"},
		{"japanName","Japão"},
		{"ukName","UK"},
		{"koreaName","Korea"},
		{"italyName","Itália"},
		{"canadaName","Canada"},
		{"brazilName","Brasil"},
		
		{"nodeName","Nó: "},
		{"radiusLabel","Raio:"},
		{"radiusToolTip","Raio do nó"},
		{"netLabel","Rede"},
		{"netToolTip","Tamanho da rede"},
		{"probabilisticDescriptionNodeColorLabel","Descrição"},
		{"probabilisticDescriptionNodeColorToolTip","Selecionar a cor do nó de descrição de probabilidade"},
		{"probabilisticExplanationNodeColorLabel","Explanação"},
		{"probabilisticExplanationNodeColorToolTip","Selecionar a cor do nó de explanação de probabilidade"},
		{"decisionNodeColorLabel","Decisão"},
		{"decisionNodeColorToolTip","Selecionar a cor do nó de decisão"},
		{"utilityNodeColorLabel","Utilidade"},
		{"utilityNodeColorToolTip","Selecionar a cor do nó de utilidade"},
		{"nodeColorLabel","Cor do nó"},
                {"arcColor","Cor do arco"},
                {"selectionColor","Cor de seleção"},
                {"backGroundColor","Cor de fundo"},
                {"arcColorLabel","Arco"},
		{"arcColorToolTip","Selecionar a cor do arco"},
		{"selectionColorLabel","Seleção"},
		{"selectionColorToolTip","Selecionar a cor de seleção"},
		{"backgroundColorLabel","Fundo"},
		{"backgroundColorToolTip","Selecionar a cor de fundo"},
		{"edgeColor","Cor do arco"},
		
		{"confirmLabel","Confirmar"},
		{"confirmToolTip","Corfimar as alterações"},
		{"cancelLabel","Cancelar"},
		{"cancelToolTip","Cancelar as alterações"},
		{"resetLabel","Repor"},
		{"resetToolTip","Repor os valores padrões"},
		{"decimalPatternTab","Padrão Decimal"},
		{"colorControllerTab","Controle de Cor"},
		{"sizeControllerTab","Controle de Tamanho"},
		{"logTab","Log"},
		{"createLogLabel","Gerar Log"},
		{"nodeGraphName","Nó"},
		{"closeButton", "Fechar"},
	
		{"likelihoodName", "Likelihood"},
		
		{"LookAndFeelUnsupportedException","Não suporta esse tema: "},
		{"LookAndFeelClassNotFoundException","A classe desse tema não foi encontrada: "},
		{"LookAndFeelInstantiationException","Não foi possível carregar esse tema: "},
		{"LookAndFeelIllegalAccessException","Esse tema não pode ser usado: "},
		{"nameError","Nome não aceito"},
		{"operationFail","Operação não aceita"},
		{"nameAlreadyExists","Já existe um objeto com este nome."},	
		{"objectEntityHasInstance","Há instâncias da entidade selecionada. Remova-as e tente novamente."},
		{"internalError","Erro interno... Reporte aos desenvolvedores"},
		{"error","Erro"},	
		{"argumentMissing","Faltando argumentos"},	
		{"stateUnmarked","Estado não marcado"},	
		
		{"loadNetException","Erro ao Abrir a Rede"},
		{"JAXBExceptionFound", "Erro de sintaxe..."},
		{"SaveNetException", "Erro ao salvar a rede"},
		{"saveSucess", "Arquivo salvo com sucesso!"},
		
		{"helpToolTip","Ajuda do UnBBayes"},
		{"propagateToolTip","Propagar as evidências"},
		{"expandToolTip","Expandir a árvore de evidências"},
		{"collapseToolTip","Contrair a árvore de evidências"},
		{"editToolTip","Retornar ao modo de edição"},
		{"logToolTip","Informações sobre a compilação (Log)"},
		{"resetBeliefsToolTip","Reiniciar as crenças"},
		{"removeEvidenceToolTip","Remove evidência do nó selecionado"},
		{"printNetToolTip","Imprimir o grafo"},
		{"previewNetToolTip","Visualizar a impressão do grafo"},
		{"saveNetImageToolTip","Salvar o grafo como imagem"},
		{"nameLabel", "Nome:"}, 
		{"typeLabel", "Tipo:"}, 
		{"descriptionLabel","Descrição:"},
		{"ordereableLabel", "É Ordenável"}, 
		{"evaluateToolTip","Avaliar rede probabilística"},
		{"compileToolTip","Compilar árvore de junção"},
		{"moreToolTip","Adicionar estado"},
		{"lessToolTip","Remover estado"},
		{"arcToolTip","Inserir Arco"},
		{"continuousNodeLabel","Variável contínua"},
		{"continuousNodeInsertToolTip","Inserir variável contínua"},
		{"probabilisticNodeInsertToolTip","Inserir variável de probabilidade"},
		{"decisionNodeInsertToolTip","Inserir variavel de decisão"},
		{"utilityNodeInsertToolTip","Inserir variável de utilidade"},
		{"contextNodeInsertToolTip","Inserir Nó de Contexto"},
		{"inputNodeInsertToolTip","Inserir Nó de Entrada"},
		{"ordinaryVariableInsertToolTip","Inserir Variável Ordinária"},
		{"selectObjectToolTip","Selecionar Objeto"},
		{"residentNodeInsertToolTip","Inserir Nó Residente"},
		{"mFragInsertToolTip","Inserir MFrag"},	
		{"inputActiveToolTip","Nó de Entrada Selecionado"},  
		{"mFragActiveToolTip","MFrag Selecionada"}, 		
		{"contextActiveToolTip","Nó de Contexto Selecionado"}, 
		{"residentActiveToolTip","Nó Residente Selecionado"}, 		
		{"addArgumentToolTip","Adicionar Argumento"}, 
		{"editFormulaToolTip","Editar Formula"},		
		{"selectToolTip","Selecionar vários nós e arcos"},
		{"printTableToolTip","Imprimir tabela"},
		{"previewTableToolTip","Visualizar impressão da tabela"},
		{"saveTableImageToolTip","Salvar a tabela como imagem"},
		{"newEntityToolTip","Criar Entidade"},		
		{"delEntityToolTip","Deletar Entidade"},
		{"newOVariableToolTip","Criar Variável Ordinária"},
		{"delOVariableToolTip", "Deletar var. ordinária"}, 
		{"newArgumentToolTip","Adicionar V. Ord. à lista de argumentos"},
		{"delArgumentToolTip", "Remover Var. Ord. da lista de argumentos"}, 		
		{"downArgumentToolTip", "Adicionar Var. Ord. à lista de argumentos"}, 		
		{"mTheoryEditionTip", "Editar MTheory"}, 		
		{"isGloballyExclusive", "Exclusivo Globalmente"}, 
		{"resetToolTip", "Resetar"},	
		{"deleteSelectedItemToolTip", "Deletar Item"},	
		{"menuOpen", "Abrir"},	
		
		{"showMTheoryToolTip","Árvore da MTheory"},	
		{"showEntitiesToolTip","Entidades"},
		{"showOVariablesToolTip","Variáveis Ordinárias"},
		{"showEntityInstancesToolTip","Instâncias de Entidades"},			
		{"showFingingsToolTip","Evidências"},
		
		{"executeQueryToolTip","Executar Query"},
		{"turnToSSBNModeToolTip","Mudar para o Modo SSBN"},
		{"clearKBToolTip","Limpar Base de Conhecimento"},
		{"loadKBToolTip","Carregar Base de Conhecimento"},
		{"saveKBToolTip","Salvar Base de Conhecimento"},
		
		{"formula","Formula:"},	
		{"inputOf","Input de:"},	
		{"arguments", "Args: "}, 	
		{"statusReadyLabel","Pronto"},
		
		{"andToolTip", "Operador 'E'"}, 
		{"orToolTip", "Operador 'OU'"},
		{"notToolTip", "Operador 'NÂO'"},
		{"equalToToolTip", "Operador 'IGUAL'"},
		{"impliesToolTip", "Operador 'IMPLICA'"},
		{"iffToolTip", "Operador 'SEE' "},
		{"forallToolTip", "Quantificador 'PARA TODO'"},
		{"existsToolTip", "Quantificador 'EXISTE'"},	
		
		
		
		//Titles for tab panel
		{"ResidentTabTitle", "Nó Residente"}, 
		{"InputTabTitle", "Nó de Input"}, 
		{"ContextTabTitle", "Nó de Contexto"}, 
		{"MTheoryTreeTitle", "Árvore MTheory"}, 
		{"EntityTitle", "Entidade"}, 
		{"OVariableTitle", "Variável Ord."},
		{"ArgumentTitle", "Argumentos"}, 
		{"StatesTitle", "Estados"}, 	
		{"FathersTitle", "Nós Pais"}, 	
		{"NodesTitle", "Nós"}, 		
		{"AddFinding", "Finding"}, 		
		
		//Label for buttons of tab selection
		/* Don't use names with more than fifteen letters */
		{"MTheoryButton", "MTheory"}, 
		{"ResidentButton", "Resident"}, 
		{"InputButton", "Input"}, 
		{"ContextButton", "Context"}, 
		{"MFragButton", "MFrag"}, 	
		{"ArgumentsButton", "Argumentos"}, 	
		{"OrdVariableButton", "Variável"}, 
		
		{"whithotMFragActive","Não há MFrag ativa"},			
		{"previewTitle","Pré visualização"},
		{"filesText"," arquivos"},
		{"aprendizagemTitle","Edição da Rede de Aprendizagem"},
		{"calculateProbabilitiesFromLearningToEditMode","Remontar a estrutura da rede e voltar para o modo de edição"},
        {"fileMenu","Arquivo"},
        {"recentFilesMenu","Arquivos Recentes"},
        {"lafMenu","Tema"},
        {"viewMenu","Exibir"},
        {"tbMenu","Barras de Ferramentas"},
        {"toolsMenu","Ferramentas"},
        {"samplingMenu","Amostragem"},
        {"windowMenu","Janela"},
        {"helpMenu","Ajuda"},
        {"newMenu","Novo..."},
        {"newBN","BN"},
        {"newOOBN","Nova OOBN"},
        {"openItem","Abrir..."},
        {"saveItem","Salvar como..."},
        {"exitItem","Sair"},
        {"tbNewProject","Barra de Ferramenta de Novo Projeto"},
        {"tbFile","Barra de Ferramenta de Arquivo"},
        {"tbView","Barra de Ferramenta de Exibir"},
        {"tbTools","Barra de Ferramenta de Ferramentas"},
        {"tbWindow","Barra de Ferramenta de Janela"},
        {"tbHelp","Barra de Ferramenta de Ajuda"},
        {"tbPlugin","Barra de Ferramenta de Plugins"},
        {"metalItem","Metal"},
        {"motifItem","Motif"},
        {"windowsItem","Local"},
        {"learningItem","Aprendizagem"},
        {"tanItem","TAN"},
        {"banItem","BAN"},
        {"logicItem","Logic"},
        {"likelihoodWeightingItem","Likelihood Weighting"},
        {"gibbsItem","Gibbs"},
        {"ILearningItem","Aprendizagem Incremental"},
        {"MetaphorItem","Metáfora"},
        {"MedicalMetaphorItem","Metáfora Médica"},
        {"UnBMinerItem","UnBMiner"},
        {"cascadeItem","Em cascata"},
        {"tileItem","Lado a lado verticalmente"},
        {"helpItem","Ajuda"},
        {"aboutItem","Sobre o UnBBayes"},
        
        {"recentFilesMn", "R"},
        {"fileMenuMn","A"},
        {"newMenuMn","N"},
        {"lafMenuMn","L"},
        {"viewMenuMn","X"},
        {"tbMenuMn","B"},
        {"toolsMenuMn","F"},
        {"windowMenuMn","J"},
        {"helpMenuMn","U"},
        {"newItemMn","N"},
        {"openItemMn","A"},
        {"saveItemMn","S"},
        {"exitItemMn","R"},
        {"metalItemMn","M"},
        {"motifItemMn","O"},
        {"windowsItemMn","H"},
        {"cascadeItemMn","C"},
        {"tileItemMn","V"},
        {"helpItemMn","U"},
        {"aboutItemMn","S"},
        {"newBNMn","B"},
        {"newOOBNMn","O"},
        
        {"learningItemMn","L"},
        {"tanItemMn","T"},
        {"banItemMn","B"},
        {"ILearningItemMn","I"},
        
        {"logicItemMn","L"},
        {"likelihoodWeightingItemMn","W"},
        {"gibbsItemMn","G"},    
        
        {"operationError","Erro na operação"},           
        {"oVariableAlreadyIsArgumentError","Variável ord. já é argumento deste nó!"},       
        {"properties","Propriedades..."},
        {"nameException","Erro no Nome"},
        {"nameEmpty","O nome não pode ser branco"},
        {"nameDuplicated", "Nome já existe..."}, 
        {"nameReserved", "Nome é uma palavra reservada."}, 
        {"nameError","O nome só pode ter letras, números, parênteses e vírgulas."},
        {"descriptionError","A descrição só pode ter letras, números, parênteses, vírgulas e espaços."},
        
        /* Resident Panel */
        {"stateEditionTip", "Editar Estados"}, 
        {"argumentEditionTip", "Editar Argumentos"}, 
        {"tableEditionTip", "Editar Tabela"}, 
        {"existentStatesDialogTip", "Estados Existentes"}, 
        
        {"addSelectedStatesTip", "Adicionar estados selecionados"}, 
        
        /* Input Panel */
        {"inputOf", "Nó origem"},         
        
        /* Arguments Typed Pane */
        {"nodeLabel", "Nó"}, 
        {"openTip", "Abrir"}, 
        
        /* Formula Pane */
        {"addOVariableTip", "Adicionar var. ordinária"}, 
        {"addNodeTip", "Adicionar nó"}, 
        {"addEntityTip", "Adicionar entidade"}, 
        {"addSkolenTip", "Adicionar skolen"}, 
        
        /* Query Panel*/
        {"queryPanelTitle","Query"}, 
        {"queryBtnBack","Voltar"}, 
        {"queryBtnSelect","Selecionar"},
        {"queryBtnExecute","Executar"},
        
        {"argumentFault","Argumentos incompletos. Query não pode ser executada."},
        {"inconsistentArgument","Argumentos inconsistentes. Query não pode ser executada."}, 
        {"selectOneVariable","Selecione uma variável:"}, 
        {"selectArgsValues","Selecione os valores dos argumentos:"}, 
        
        /* Findings Panel */
        {"stateLabel","Valor:"}, 
        {"booleanLabel","Boleano"}, 
        {"categoricalLabel","Categorico"}, 
        
        /* Randon Variable Finding Panel */
        {"editNodeFindingTip","Editar evidências do nó selecionado"}, 
        {"removeFindingTip","Remover evidência selecionada"}, 
        {"backToNodeSelectionTip","Voltar para seleção de nó"}, 
        {"addFindingTip","Adicionar evidência"}, 
        
        /* FormulaTreeConstructionException */
        {"notOperator", "Não é permitido operador nesta posição"}, 
        
		{"sucess", "Sucesso"}, 
		{"error", "Erro"},
        
		/* Tips for buttons of the table edition */
		{"clear", "clear"}, 
		{"ifAny", "if any"}, 
		{"ifAll", "if all"}, 
		{"else", "else"}, 
		{"default", "default"}, 
		{"equal", " = "}, 
		{"and", " & "}, 
		{"or", " | "}, 
		{"not", " ~ "}, 
		{"card", "card"}, 
		{"max", "max"}, 
		{"min", "min"}, 
		
		{"deleteTip", "Apaga o texto selecionado"}, 
		{"anyTip", "Inserir construção \"If any\""}, 
		{"allTip", "Inserir construção \"If all\""}, 
		{"defaultTip", "Inserir construção \"disbribuição padrão\""},
		{"elseTip", "Inserir \"else\""}, 
		{"equalTip", "Inserir operador de igualdade"}, 
		{"andTip", "Inserir construção AND"}, 
		{"orTip", "Inserir construção OR"}, 
		{"notTip", "Inserir construção NOT"}, 
		{"cadinalityTip", "Inserir construção CARDINALITY"}, 
		{"maxTip", "Inserir contrução MAX"}, 
		{"minTip", "Inserir contrução MIN"}, 
		{"saveTip", "Salvar a tabela"}, 
		{"statesTip", "Mostrar estados do nó"}, 
		{"fatherTip", "Mostrar pais do nó"}, 
		{"argTip", "Mostrar argumentos do nó"}, 
		{"exitTip", "Sair sem salvar"}, 
		{"compileCPTTip", "Compilar tabela"},
		{"saveCPTTip", "Salvar tabela"},
		{"exitCPTTip", "Fechar tabela"},
		{"fatherCPTTip", "Abrir lista de pais/estados dos nós pais"},
		{"argumentCPTTip", "Abrir lista de argumentos"},
		{"statesCPTTip", "Abrir lista de estados do nó"},
		
		{"position", "Posição"},
		
		/* CPT Edition messages */
		{"compileCPT", "Compilar"},
		{"saveCPT", "Salvar"},
		{"exitCPT", "Fechar"},
		{"fatherCPT", "Pais"},
		{"argumentCPT", "Argumentos"},
		{"statesCPT", "Estados"},
		
		{"CptSaveOK", "Tabela salva com sucesso"},
		{"CptCompileOK", "Tabela compilada sem erros"},
		
		{"apply", "Aplicar"},
		
		
		/* Edition of states */
		{"insertBooleanStates", "Inserir estados booleanos"}, 
		{"categoryStatesTip", "Inserir estados categóricos"}, 
		{"objectStatesTip", "Inserir entidades como estados"}, 
		{"booleanStatesTip", "Inserir estados booleanos"}, 
		{"addStateTip", "Inserir estado(s)"}, 
		{"removeState", "Remover estado"},
		{"addPreDefinedState", "Adicionar um estado pré-criado"}, 
		{"confirmation", "Confirmação"}, 
		{"warningDeletStates", "Os estados anteriores serão removidos. Tem certeza que deseja realizar a operação?"}, 
		
		
		
		/* Aboult pane */
		{"AboultPane" , "Sobre"},	
		
		{"ReadLicense" , "Licença"},
		{"Features" , "Características"},
		{"VersionHistory" , "Histórico"},
		{"CloseAboultPane" , "Fechar"},
		
		{"Collaborators" , "Colaboradores"},	
		
		{"Version" , "Versão"},	
		{"Buildid" , "ID do Produto"},	

		{"stableStatusAlpha" , "Alfa"},	
		{"stableStatusBeta" , "Beta"},	
		{"stableStatusStable" , "Estável"},	
		
		//Warning panel
		{"warningFound" , "Alertas"},
		{"openWarningDialogToolTip" , "Abrir lista de alertas"},
		{"nodeCause" , "Alerta durante a avaliação do nó"},
		
		//SSBN panel
		{"saveSSBNToolTip" , "Salvar ssbn gerada"},
		
        //Splash loader */
		{"loading" , "carregando"},
		
		/* Inference Algorithm Option Pane */
		{"algorithmTab" , "Algoritmo de Inferência"},
		{"junctionTreeAlgorithmName" , "Árvore de Junção"},
		{"likelihoodWeightingAlgorithmName" , "Likelihood Weighting"},
		{"gibbsAlgorithmName" , "Gibbs"},
		{"gaussianMixtureAlgorithmName" , "Gaussian Mixture"},
		
		/* Continuous Normal Distribution Pane */
		{"meanLabel" , "Média"},
		{"varianceLabel" , "Variância"},
		{"constantLabel" , "k"},
		{"followsLabel" , "~"},
		{"normalFunctionLabel" , "N(média,variância)"},
		{"continuousNormalDistributionParamError" , "Os valores devem ser números reais."},
		
		
		/* Suporte a plugins */
		{"pluginMenu" , "Plugins"},
		{"newPlugin" , "Novo "},
		{"reloadPlugin" , "Recarregar Plugins"},
		{"reloadPluginToolTip" , "Atualiza os plugins do sistema, situados na pasta \"plugins\"."},
		{"noModuleDesktop" , "Este móodulo/plugin não possui um desktop java associado."},
		{"unsupportedGraphFormat" , "Este módulo/plugin não possui suporte a este formato."},
		{"moduleLoadingError" , "Erro ao carregar módulo/plugin. "},
		{"moduleConflict" , "Houve conflito de módulos/plugins."},
		{"moduleConflictMessage" , "Mais de um módulo/plugin possui suporte a esse elemento. Selecione o desejado."},
		

		// nomes dos modulos/plugins incorporados no core
		{"PNModuleName" , "Redes Probabilísticas"},
		

		{"sampleSizeInputMessage", "Favor entrar com o tamanho da amostragem (número de casos)."},
//		{"sampleSizeInputTitle", "Tamanho da amostragem"}, 

		{"algorithmParameters", "Parâmetros para o algoritmo"},
		{"availableAlgorithms", "Algoritmos disponíveis"},
		
		{"selectNodeType", "Selecione o tipo de nó"},
		

		{"CPFTableTitle", "Tabela"},
		{"CPFTableToolTip", "Editar probabilidade condicional usando tabela"},
		
		{"IOConflictTitle", "Conflito de extensão de E/S"},
		{"IOConflictMessage", "Mais de um componente de E/S é compatível esta extensão de arquivo. Favor, selecionar o desejado."},

		// log dialogue
		{"printLogToolTip", "Imprimir log"},
		{"previewLogToolTip", "Visualizar impressão de log"},
		{"closeButtonLabel", "Fechar log"},
		{"logDialogTitle", "Log do sistema"},
		{"printException", "Erro ao imprimir"},
		
		// plugin dependency logs
		{"pluginDependencyLogTitle", "Erro de dependência em plugins"},
		{"pluginDependencyLogKeyMessage", "Não foi possível encontrar as dependências dos plugins abaixo:"},
		{"pluginDependencyLogID", "ID do plugin: "},
		{"pluginDependencyLogDependencies", "Dependências não resolvidas:"},
		{"pluginDependencyLogPleaseCheck", "Por favor, verifique a existência das dependências na sua pasta de plugins."},
		
	};
}