package unbbayes.simdecs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.AbstractListModel;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.JCheckBox;

import unbbayes.prs.Node;
import unbbayes.simdecs.PerguntaNodo;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import java.awt.List;


public class SimdecsNodeWindow extends JDialog {
	private JTextField edTempo;
	private JTextField edCusto;
	private JTextField ed0;
	private JTextField ed25;
	private JTextField ed50;
	private JTextField ed100;
	private JTextField edPergunta;
	private JCheckBox chkBogus;
	private List listaPerguntas;
	
	private Node nodo;
	private Vector<PerguntaNodo> Perguntas = new Vector<PerguntaNodo>();
	
	
	/**
	 * Create the dialog.
	 */
	public SimdecsNodeWindow(Node no) {
		
		/*
		 * Setando o nodo ao qual vou trabalhar
		 */
		this.nodo = no;
		
		
		setBounds(100, 100, 505, 336);
		getContentPane().setLayout(null);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 275, 505, 39);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("Gravar");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						setPropriedades();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 505, 275);
		getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Propriedades", null, panel, null);
		panel.setLayout(null);
		
		edTempo = new JTextField();
		edTempo.setBounds(45, 26, 134, 28);
		edTempo.setColumns(10);
		panel.add(edTempo);
		
		JLabel label = new JLabel("Tempo decorrido");
		label.setBounds(45, 11, 107, 16);
		panel.add(label);
		
		edCusto = new JTextField();
		edCusto.setBounds(45, 82, 134, 28);
		edCusto.setColumns(10);
		panel.add(edCusto);
		
		JLabel label_1 = new JLabel("Custo da Etapa");
		label_1.setBounds(45, 66, 94, 16);
		panel.add(label_1);
		
		chkBogus = new JCheckBox("Bogus");
		chkBogus.setBounds(45, 127, 128, 23);
		panel.add(chkBogus);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Perguntas e Respostas", null, panel_1, null);
		panel_1.setLayout(null);
		
		ed0 = new JTextField();
		ed0.setBounds(264, 6, 203, 28);
		panel_1.add(ed0);
		ed0.setColumns(10);
		
		ed25 = new JTextField();
		ed25.setBounds(264, 40, 203, 28);
		panel_1.add(ed25);
		ed25.setColumns(10);
		
		ed50 = new JTextField();
		ed50.setBounds(264, 80, 203, 28);
		panel_1.add(ed50);
		ed50.setColumns(10);
		
		ed100 = new JTextField();
		ed100.setBounds(264, 120, 203, 28);
		panel_1.add(ed100);
		ed100.setColumns(10);
		
		edPergunta = new JTextField();
		edPergunta.setBounds(6, 180, 374, 28);
		panel_1.add(edPergunta);
		edPergunta.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Pergunta");
		lblNewLabel.setBounds(6, 163, 79, 16);
		panel_1.add(lblNewLabel);
		
		JLabel label_2 = new JLabel("0%");
		label_2.setBounds(235, 12, 17, 16);
		panel_1.add(label_2);
		
		JLabel label_3 = new JLabel("50%");
		label_3.setBounds(228, 86, 25, 16);
		panel_1.add(label_3);
		
		JLabel label_4 = new JLabel("25%");
		label_4.setBounds(227, 46, 25, 16);
		panel_1.add(label_4);
		
		JLabel label_5 = new JLabel("100%");
		label_5.setBounds(220, 126, 33, 16);
		panel_1.add(label_5);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				adicionaPergunta();
			}
		});
		btnAdicionar.setBounds(380, 181, 98, 29);
		panel_1.add(btnAdicionar);
		
		listaPerguntas = new List();
		listaPerguntas.setBounds(10, 10, 172, 138);
		panel_1.add(listaPerguntas);
		
				
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		
		carregaPropriedades();
	}
	
	private void setPropriedades() {
		this.nodo.setSimdecsTempoEtapa(Float.parseFloat(this.edTempo.getText()));
		this.nodo.setSimdecsCustoEtapa(Float.parseFloat(this.edCusto.getText()));
		this.nodo.setSimdecsIsBogus(this.chkBogus.isSelected());
		
		// Setando as perguntas
		/*for (int i=0; i<this.Perguntas.size(); i++) {
			this.nodo.adicionarPerguntaEtapa(this.Perguntas.get(i));
		}*/
		this.nodo.setPerguntasEtapa(this.Perguntas);
		
	}
	
	private void adicionaPergunta() {
		PerguntaNodo pergunta = new PerguntaNodo(this.edPergunta.getText(), this.ed0.getText(),this.ed25.getText(),this.ed50.getText(),this.ed100.getText());
		this.Perguntas.add(pergunta);
		listaPerguntas.add(this.edPergunta.getText());
	}
	
	private void carregaPropriedades() {
		this.edCusto.setText(String.valueOf(this.nodo.getSimdecsCustoEtapa()));
		this.edTempo.setText(String.valueOf(this.nodo.getSimdecsTempoEtapa()));
		this.chkBogus.setSelected(this.nodo.getSimdecsIsBogus());
		
		this.Perguntas = this.nodo.getSimdecsPerguntasEtapa();
		
		for (int i = 0; i<this.Perguntas.size(); i++) {
			listaPerguntas.add(this.Perguntas.get(i).getPergunta());
		}
	}
}