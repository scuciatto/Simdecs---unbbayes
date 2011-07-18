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
package unbbayes.example;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import unbbayes.io.BaseIO;
import unbbayes.io.NetIO;
import unbbayes.prs.Edge;
import unbbayes.prs.bn.PotentialTable;
import unbbayes.prs.bn.ProbabilisticNetwork;
import unbbayes.prs.bn.ProbabilisticNode;
import unbbayes.prs.exception.InvalidParentException;

/**
 * Title: Sample code for using this API's graphic mode.
 * Description: This class, written in JAVA, opens a ".net" file ("asia.net"), and, after that,
 * 				modifies some parts and then compiles it. This class is provided only as
 * 				a sample, in order to show how to work out using this API, developed for bayesian
 * 				network manipulation.
 * 
 * Copyright:   Copyright (c) 2001
 * Company:     UnB - Universidade de Brasilia
 * @author      Rommel Novaes Carvalho
 * @author      Michael S. Onishi
 * @version 1.0
 */
public class GraphicMode {
	
	/** Load resource file from this package */
  	private static ResourceBundle resource = unbbayes.util.ResourceController.newInstance().getBundle(
  			unbbayes.example.resources.ExampleResources.class.getName());
	
    public static void main(String[] args) {
        JFrame frame = new JFrame(resource.getString("exampleTitle"));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = frame.getContentPane();
        pane.setLayout(new FlowLayout());

        JLabel labelArquivo = new JLabel(resource.getString("fileName"));
        final JTextField nomeArquivo = new JTextField(20);

        JButton botao = new JButton(resource.getString("compileTree"));
        pane.add(labelArquivo);
        pane.add(nomeArquivo);
        pane.add(botao);

        botao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ProbabilisticNetwork rede = null;
                try {
                    BaseIO io = new NetIO();
                    rede = (ProbabilisticNetwork)io.load(new File(nomeArquivo.getText()));
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    System.exit(1);
                }

                ProbabilisticNode auxVP = new ProbabilisticNode();
                auxVP.setName(resource.getString("nodeName1"));
                auxVP.setDescription(resource.getString("nodeDescription"));
                auxVP.appendState(resource.getString("stateName0"));
                auxVP.appendState(resource.getString("stateName1"));
                PotentialTable auxTabPot = auxVP.getProbabilityFunction();

                auxTabPot.addVariable(auxVP);
                auxTabPot.addValueAt(0, 0.3f);
                auxTabPot.addValueAt(1, 0.7f);
                rede.addNode(auxVP);

                ProbabilisticNode auxVP2 = (ProbabilisticNode)rede.getNode("A");
                Edge auxArco = new Edge(auxVP, auxVP2);
                try {
					rede.addEdge(auxArco);
				} catch (InvalidParentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, e1.getMessage(), "Could not add edge", JOptionPane.WARNING_MESSAGE);
				}

                try {
                   rede.compile();
                } catch (Exception ex) {
                   System.out.println(ex.getMessage());
                   JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                   System.exit(1);
                }

                float likelihood[] = new float[auxVP.getStatesSize()];
                likelihood[0] = 1f;
                likelihood[1] = 0.8f;

                auxVP.addLikeliHood(likelihood);
                try {
                	rede.updateEvidences();
                } catch (Exception exc) {
                	System.out.println(exc.getMessage());    
                    JOptionPane.showMessageDialog(null, exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);  
                    System.exit(1);         	
                }
                
                JOptionPane.showMessageDialog(null, "Net compiled", "Success", JOptionPane.INFORMATION_MESSAGE);
           
            }
        });

        frame.pack();
        frame.setVisible(true);
    }
}