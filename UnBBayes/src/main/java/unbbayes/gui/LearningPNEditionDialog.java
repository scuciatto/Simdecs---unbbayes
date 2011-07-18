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
package unbbayes.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.JViewport;

import unbbayes.controller.IconController;
import unbbayes.draw.UCanvas;
import unbbayes.prs.Edge;
import unbbayes.prs.Node;
import unbbayes.prs.bn.ProbabilisticNetwork;


/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @version 1.0
 */
public class LearningPNEditionDialog extends JDialog {
	
//TODO REMOVER ESSA CLASSE!!!
	/** Serialization runtime version number */
	private static final long serialVersionUID = 0;		
	
    private JToolBar jtb;
    private JViewport view;
    
    //by young
    //private LearningPNEditionPane rede;
    private GraphPane rede;
    private JButton insereArco;
    private JButton reaprende;
    private JScrollPane jspView;
    private ProbabilisticNetwork net;

    protected IconController iconController = IconController.getInstance();

	/** Load resource file from this package */
  	private static ResourceBundle resource = unbbayes.util.ResourceController.newInstance().getBundle(
  			unbbayes.gui.resources.GuiResources.class.getName());

    public LearningPNEditionDialog(ProbabilisticNetwork _net) {
		super(new Frame(), resource.getString("aprendizagemTitle"), true);
        Container contentPane = getContentPane();
        net = _net;

        setSize(550, 470);
        setResizable(true);

        insereArco = new JButton(iconController.getEdgeIcon());
        reaprende  = new JButton(iconController.getCompileIcon());
        view       = new JViewport();
        jtb        = new JToolBar();
                
     
        rede       = new LearningPNEditionPane(this, net);
        
        
        jspView    = new JScrollPane(view);

        view.setView(rede);

        insereArco.setToolTipText(resource.getString("arcToolTip"));
        reaprende.setToolTipText(resource.getString("calculateProbabilitiesFromLearningToEditMode"));

        //setar defaults para jspDesenho
        jspView.setHorizontalScrollBar(jspView.createHorizontalScrollBar());
        jspView.setVerticalScrollBar(jspView.createVerticalScrollBar());
        jspView.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jspView.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        //ao clicar no botao reaprende, mostra-se o menu para escolha do arquivo para o aprendizado.
        reaprende.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
            	List edgeList = net.getEdges();
            	Edge edge;
            	Node node;
            	boolean close = true;
            	for(int i = 0; i < edgeList.size() && close; i++){
                     edge = (Edge)edgeList.get(i);
                    if(!edge.hasDirection()){
                    	JOptionPane.showMessageDialog(null,"Todos os arcos tem que ter uma direcao.","ERROR",JOptionPane.ERROR_MESSAGE);
                    	return;
                    }
               	}
               	for(int i = 0; i < net.getNodeCount() && close; i++){
                     node = net.getNodeAt(i);
                    if(node.getChildren().size() == 0 && node.getParents().size() == 0){
                    	JOptionPane.showMessageDialog(null,"Todo no deve tem que ter pelo menos um adjacente.","ERROR",JOptionPane.ERROR_MESSAGE);
                    	return;
                    }
               	}
                setVisible(false);
                dispose();
            }
        });

        insereArco.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
            //by young
           //     rede.setbArco(true);
            	rede.setState(UCanvas.STATE_CONNECT_COMP);
            }
        });

        jtb.add(insereArco);
        jtb.add(reaprende);
        contentPane.add(jtb, BorderLayout.NORTH);
        contentPane.add(jspView, BorderLayout.CENTER);

        this.setVisible(true);
        this.addWindowListener(closeFunction);

    }

    /**
     *  Retorna o painel da tela de edicao.
     *
     *@return    retorna o jspView (<code>JScrollPane</code>)
     *@see       JScrollPane
     */
    public JScrollPane getJspView() {
        return this.jspView;
    }

    /**
     *  Retorna o painel o view.
     *
     *@return    retorna o view (<code>JViewport</code>)
     *@see       JViewport
     */
    public JViewport getView() {
        return this.view;
    }

    WindowAdapter closeFunction = new WindowAdapter(){
        public void windowClosing(WindowEvent we){}
    };
}