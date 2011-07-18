/*
 *  UnBBayes
 *  Copyright (C) 2002, 2009 Universidade de Brasilia - http://www.unb.br
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
package unbbayes.draw;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import unbbayes.prs.bn.IRandomVariable;
import unbbayes.prs.bn.PotentialTable;
import unbbayes.prs.bn.ProbabilisticNode;

public class TestUGraphEditor extends JFrame implements WindowStateListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6193584247576527941L;

	protected UCanvas m_Canvas;

	public TestUGraphEditor() {
		super("TestUGraphEditor");

		addWindowStateListener(this);

		// Using UGraphEditor
		m_Canvas = new UCanvas();
		m_Canvas.setPreferredSize(new Dimension(800, 600));

		int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
		int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;

		JScrollPane scrollPane = new JScrollPane(m_Canvas, v, h);
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		// Add Buttons
		JPanel buttonPanel = new JPanel(new GridLayout());
		getContentPane().add(buttonPanel, BorderLayout.NORTH);

		double x1 = 10;
		double y1 = 10;
		double x2 = 0;
		double y2 = 0;

		double newX = 5 * Math.cos(Math.atan2(y2 - y1, x2 - x1)) + x1;
		double newY = 5 * Math.sin(Math.atan2(y2 - y1, x2 - x1)) + y1;

		// double x3 = (-x1+newX)*Math.cos(-Math.PI/2) -
		// (-y1+newY)*Math.sin(-Math.PI/2) + x1;
		// double y3 = (-x1+newX)*Math.sin(-Math.PI/2) +
		// (-y1+newY)*Math.cos(-Math.PI/2) + y1;

		double x3 = (-x1 + newX) * Math.cos(Math.PI / 2) - (-y1 + newY)
				* Math.sin(Math.PI / 2) + x1;
		double y3 = (-x1 + newX) * Math.sin(Math.PI / 2) + (-y1 + newY)
				* Math.cos(Math.PI / 2) + y1;

		System.out.println(x3 + " " + y3);

		// Add Listener
		JButton btn;
		
		btn = addButton(buttonPanel, "Create MFrag");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UShape frame = new UShapeFrame(m_Canvas, new ProbabilisticNode(), 50, 50, 100, 100);

				ProbabilisticNode node = new ProbabilisticNode();
				node.setPosition(50, 50);
				node.appendState("State1");
				node.appendState("State2");
				node.appendState("State3");
				node.appendState("State4");
				node.setName("longname-longname-longname-longname-longname-longname-longname-longname");
				node.setDescription(node.getName());

				UShapeProbabilisticNode shape = new UShapeProbabilisticNode(
						m_Canvas, (ProbabilisticNode) node, (int) node
								.getPosition().x
								- node.getWidth() / 2,
						(int) node.getPosition().y - node.getHeight() / 2, node
								.getWidth(), node.getHeight());
				frame.add(shape);
//				m_Canvas.addShape(shape);
//				shape.setShapeType(UShapeProbabilisticNode.STYPE_BAR);

				ProbabilisticNode node2 = new ProbabilisticNode();
				node2.setPosition(160, 80);
				node2.appendState("firstStateProbabilisticName");
				node2.setName("123456789abcdefghijklmnopqrstuvwxyz");
				node2.setDescription(node2.getName());

				UShapeProbabilisticNode shape2 = new UShapeProbabilisticNode(
						m_Canvas, (ProbabilisticNode) node2, (int) node2
								.getPosition().x
								- node2.getWidth() / 2, (int) node2
								.getPosition().y
								- node2.getHeight() / 2, node2.getWidth(),
						node2.getHeight());
				frame.add(shape2);
//				m_Canvas.addShape(shape2);
				
				m_Canvas.addShape(frame);
			}
		});

		btn = addButton(buttonPanel, "Create ProbabilisticNode");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Test
				ProbabilisticNode node = new ProbabilisticNode();
				node.setPosition(50, 50);
				node.appendState("State1");
				node.appendState("State2");
				node.appendState("State3");
				node.appendState("State4");
				node
						.setName("longname-longname-longname-longname-longname-longname-longname-longname");
				node.setDescription(node.getName());
				PotentialTable auxTabProb = (PotentialTable)((IRandomVariable) node)
						.getProbabilityFunction();
				auxTabProb.addVariable(node);
				auxTabProb.setValue(0, 1);

				UShapeProbabilisticNode shape = new UShapeProbabilisticNode(
						m_Canvas, (ProbabilisticNode) node, (int) node
								.getPosition().x
								- node.getWidth() / 2,
						(int) node.getPosition().y - node.getHeight() / 2, node
								.getWidth(), node.getHeight());
				m_Canvas.addShape(shape);
				shape.setShapeType(UShapeProbabilisticNode.STYPE_BAR);

				// Test
				ProbabilisticNode node2 = new ProbabilisticNode();
				node2.setPosition(160, 80);
				node2.appendState("firstStateProbabilisticName");
				node2.setName("123456789abcdefghijklmnopqrstuvwxyz");
				node2.setDescription(node2.getName());
				PotentialTable auxTabProb2 =(PotentialTable) ((IRandomVariable) node2)
						.getProbabilityFunction();
				auxTabProb2.addVariable(node2);
				auxTabProb2.setValue(0, 1);

				UShapeProbabilisticNode shape2 = new UShapeProbabilisticNode(
						m_Canvas, (ProbabilisticNode) node2, (int) node2
								.getPosition().x
								- node2.getWidth() / 2, (int) node2
								.getPosition().y
								- node2.getHeight() / 2, node2.getWidth(),
						node2.getHeight());
				m_Canvas.addShape(shape2);

			}
		});

		btn = addButton(buttonPanel, "Create DLG");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createColorChooser();

			}
		});

		btn = addButton(buttonPanel, "Create Frame");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < 100; i++)
					m_Canvas.setFrame(110, 110, 220, 220);
			}
		});

		btn = addButton(buttonPanel, "Create Ellipse");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m_Canvas.setEllipse(110, 110, 120, 120);
			}
		});

		btn = addButton(buttonPanel, "Create RoundRect");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m_Canvas.setRoundRect(110, 110, 120, 120);
			}
		});

		btn = addButton(buttonPanel, "Create Trapezoid");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m_Canvas.setTrapezoid(110, 110, 120, 120);
			}
		});

		btn = addButton(buttonPanel, "Select Node");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m_Canvas.setState(UCanvas.STATE_NONE);
			}
		});

		btn = addButton(buttonPanel, "Connect Line");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m_Canvas.setState(UCanvas.STATE_CONNECT_COMP);
			}
		});
	}

	public JButton addButton(JPanel buttonPanel, String Name) {
		JButton button = new JButton(Name);
		button.setEnabled(true);
		buttonPanel.add(button);
		return button;
	}

	public void createColorChooser() {
		final JColorChooser colorChooser = new JColorChooser();
		final JLabel previewLabel = new JLabel("Color", JLabel.CENTER);
		previewLabel.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 48));
		previewLabel.setSize(previewLabel.getPreferredSize());
		previewLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 1, 0));
		colorChooser.setPreviewPanel(previewLabel);

		ActionListener okActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				System.out.println("OK Button");
				System.out.println(colorChooser.getColor().toString());

				repaint();
			}
		};

		ActionListener cancelActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				System.out.println("Cancel Button");
			}
		};

		final JDialog dialog = JColorChooser.createDialog(null,
				"Change Button Background", true, colorChooser,
				okActionListener, cancelActionListener);

		dialog.setVisible(true);

	}

	// Main
	public static void main(String argv[]) {
		TestUGraphEditor frame = new TestUGraphEditor();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(800, 600);
	}

	public void windowStateChanged(WindowEvent arg0) {

	}
}
