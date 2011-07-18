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

import java.util.ArrayList;
import java.util.List;

import unbbayes.prs.Node;
import unbbayes.util.SetToolkit;

/**
 * Classe que define um vari�vel de um banco de casos, as vari�veis
 * possuem um vetor de pais(do tipo LearningNode), um veto de estados
 * (do tipo String), um vetor de predecessores, que s�o os candidatos
 * a pais (do tipo LearningNode), um nome e um numero m�ximo de pais.
 * O modelo ainda possui uma variavel que informa o numero de casos
 * em um determinado banco de casos.
 * @version 1.0
 * @author Danilo Cust�dio da Silva
 */

public class LearningNode extends ProbabilisticNode implements Cloneable {

	/** Serialization runtime version number */
	private static final long serialVersionUID = 0;		
	
    private ArrayList<Node> predecessores;
    private LearningNode variavelAux;
    private int numeroMaximoPais;
    private int pos;
    private boolean rep;
    private boolean participa;

    /**
     * Constutor de uma LearningNode.
     * @param nome - O Nome de uma LearningNode(<code>String<code>)
     * @param pos - A posi��o de uma LearningNode em um vetor de
     * vari�veis(<code>int<code>)
     * @see List
     */
    public LearningNode(String nome, int pos){
        super();
        setName(nome);
        predecessores = new ArrayList<Node>();
        numeroMaximoPais = 10;
        participa = true;
        this.pos = pos;
    }

    /**
     * M�todo para determinar a posi��o de uma vari�vel no vetor
     * de vari�veis
     * @param pos - Posi��o que a vari�vel ocupar� no vetor(<code>
     * int<code>)
     */
    public void setPos(int pos){
        this.pos = pos;
    }

    /**
     * M�todo que seta a vari�vel a condi��o de que ela ser� ou
     * n�o a vari�vel que conter� o numero de vezes que um deter
     * minado registro se repete.
     * @param rep - Indica se a condi�ao para essa vari�vel � ver
     * dadeira ou falsa(<code>boolean<code>)
     */
    public void isRep(boolean rep){
        this.rep = rep;
    }

    /**
     * M�todo que indica se uma determinada vari�vel � ou n�o
     * a vari�vel que contem o n�mero de vezes que um registro
     * se repete.
     * @return boolean - Indica se a vari�vel e ou nao a vari�vel
     * que contem o n�mero de vezes que um registro se repete
     */
    public boolean getRep(){
        return rep;
    }

    /**
     * M�todo que clona a vari�vel original.
     * @return Object - Retorna um objeto que � uma c�pia
     * da vari�vel original, mas com uma nova refer�ncia
     * @see LearningNode
     */
    public Object clone(){
        LearningNode variavel = new LearningNode(this.name, this.pos);
        variavel.setPais(SetToolkit.clone(parents));
        variavel.setEstados(SetToolkit.clone(states));
        variavel.setPredecessores(SetToolkit.clone(predecessores));
        variavel.setNumeroMaximoPais(this.numeroMaximoPais);
        return variavel;
    }

    /**
     * M�todo que indica a posi��o da vari�vel no vetor de vari�veis
     * @return int - Posi��o da vari�vel dentro do vetor de vari�veis
     */
    public int getPos(){
        return this.pos;
    }

    /**
     * M�todo que coloca na vari�vel o numero m�ximo de pais
     * permitidos a ela.
     * @param numero - N�mero m�ximo de pais(<code>int<code>)
     */
    public void setNumeroMaximoPais(int numero){
        numeroMaximoPais = numero;
    }

    /**
     * M�todo que indica o n�mero m�ximo de pais que uma
     * vari�vel pode ter.
     * @return int - Retorna o n�mero m�ximo de pais que uma
     * vari�vel pode conter
     */
    public int getNumeroMaximoPais(){
        return this.numeroMaximoPais;
    }

    /**
     * M�todo que retorna os estados de uma vari�vel.
     * @return List - Array de estados da vari�vel
     * @see List
     */
    public List<String> getEstados(){
        return states;
    }

    /**
     * M�todo que retorna os predecessores de uma vari�vel,
     * ou seja, as vari�veis que podem ser pais dessa vari�vel.
     * @return List - Array de predecessores da vari�vel
     * @see List
     */
    public ArrayList<Node> getPredecessores(){
        return predecessores;
    }

    /**
     * M�todo que retorna os pais de uma vari�vel.
     * @return List - Array de pais da vari�vel
     * @see List
     */
    public ArrayList<Node> getPais(){
        return parents;
    }

    /**
     * M�todo que retorna a tabela com todas as probabilidades
     * poss�veis para as vari�veis dependendo dos pais.
     * @return TTabPot - Tabela com as probabilidades(<code>
     * TTabPo<code>)
     * @see TTabPor
     */
    public PotentialTable getProbabilidades(){
        return this.getProbabilityFunction();
    }

    /**
     * M�todo que indica o tamanho do n�mero de predecessores
     * de uma vari�vel.
     * @return int - N�mero de predecessores de uma vari�vel
     * @see List
     */
    public int getTamanhoPredecessores(){
        return predecessores.size();
    }

    /**
     * M�todo que indica o tamanho do n�mero de pais de uma
     * vari�vel.
     * @return int - N�mero de pais de uma vari�vel
     * @see List
     */
    public int getTamanhoPais(){
        if (parents == null){
            parents = new ArrayList<Node>();
        }
        return parents.size();
    }

    /**
     * M�todo que retorna o estado que est� em um determinado
     * indice do vetor de estados.
     * @param indexo - Indice do estado no vetor de estados
     * (<code>int<code>
     * @return String - Nome do estado
     * @see String
     */
    public String getEstado(int index){

        return (String)states.get(index);
    }

    /**
     * M�todo que indica o numero de estados da vari�vel.
     * @return int - Tamanho do vetor de estados
     * @see List
     */
    public int getEstadoTamanho(){
        return states.size();
    }

    /**
     * M�todo para adicionar um predecessor � vari�vel.
     * @param predecessor - Vari�vel que ser� inserida no
     * vetor de predecessores(<code>LearningNode<code>)
     * @see List
     */
    public void adicionaPredecessor(LearningNode predecessor){
        predecessores.add(predecessor);
    }

    /**
     * M�todo para adicionar um estado � vari�vel.
     * @param predecessor - Estado que ser� inserido no
     * vetor de estados(<code>String<code>)
     * @see List
     */
    public void adicionaEstado(String estado){
        states.add(estado);
    }

    /**
     * M�todo que retorna o nome do Ancestral da v�riavel
     * que possui o nome espec�fico.
     * @param nome - Nome do ancestral(<code>String<code>)
     * @return String - Nome do ancestral, caso nao exista
     * retorna string vazia
     * @see String
     */
    public String getPai(String nome){
        for (int i = 0; i < parents.size();i++ ){
            variavelAux = (LearningNode)parents.get(i);
                if (variavelAux.getName().equals(nome)){
                    return nome;
                }
                if((variavelAux.getPai(nome).equals(nome))){
                   return nome;
                }
        }
        return "";
    }

    /**
     * M�todo para adicionar um pai � vari�vel.
     * @param pai - Vari�vel que ser� inserida no
     * vetor de pais(<code>LearningNode<code>)
     * @see List
     */
    public void adicionaPai(LearningNode pai){
        parents.add(pai);
    }

    /**
     * Method indicating state existence for this node.
     * @param nomeEstado - Name of the state to look for
     * (<code>String<code>)
     * @return boolean - true if exists. False otherwise.
     * @see List
     */
    public boolean existeEstado(String nomeEstado){
        int tamanho = states.size();
        for(int tamanhoEstado = 0; tamanhoEstado < tamanho; tamanhoEstado++){
            if(states.get(tamanhoEstado).equals(nomeEstado)){
                return true;
            }
        }
        return false;
    }

    /**
     * M�todo que retorna a posi��o de um estado no vetor
     * de estados.
     * @param nomeEstado - Nome do estado que esta sendo procurado
     * (<code>String<code>)
     * @return int - Posi��o do estado
     */
    public int getEstadoPosicao(String nomeEstado){
        int tamanho = states.size();
        for(int tamanhoEstado = 0; tamanhoEstado < tamanho; tamanhoEstado++){
            if(states.get(tamanhoEstado).equals(nomeEstado)){
                return tamanhoEstado;
            }
        }
        return 0;
    }

    public void setParticipa(boolean estado){
       participa = estado;
    }

    public boolean getParticipa(){
        return participa;
    }

    /**
     * M�todo para colocar uma lista de pais na vari�vel.
     * Serve para o m�todo clone.
     * @param parents - Lista de pais(<code>Object<code>)
     * @see Clone()
     */
    private void setPais(Object parents){
        this.parents = (ArrayList<Node>)parents;
    }

    /**
     * M�todo para colocar uma lista de estados na vari�vel.
     * Serve para o m�todo clone.
     * @param states - Lista de states(<code>Object<code>)
     * @see Clone()
     */
    private void setEstados(List<String> states){
        this.states = states;
    }

    /**
     * M�todo para colocar uma lista de predecessores na vari�vel.
     * Serve para o m�todo clone.
     * @param predecessores - Lista de predecessores(<code>Object<code>)
     * @see Clone()
     */
    private void setPredecessores(Object predecessores){
        this.predecessores = (ArrayList<Node>)predecessores;
    }
}