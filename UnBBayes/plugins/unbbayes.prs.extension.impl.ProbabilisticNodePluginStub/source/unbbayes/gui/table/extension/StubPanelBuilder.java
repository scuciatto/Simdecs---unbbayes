/**
 * 
 */
package unbbayes.gui.table.extension;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import unbbayes.prs.Node;
import unbbayes.prs.bn.ProbabilisticNode;

/**
 * This is just a test for plugin support for new nodes.
 * It edits a boolean node.
 * @author Shou Matsumoto
 *
 */
public class StubPanelBuilder extends JPanel implements IProbabilityFunctionPanelBuilder {

	private Node node;
	
//	protected JLabel nameLabel;
	
	protected JTextField trueTxtField;
	protected JTextField falseTxtField;
	
	protected JButton okButton;
	protected JButton cancelButton;
	
	protected JPanel topPanel;
	
	/**
	 * Default constructor must be public for plugin compatibility
	 */
	public StubPanelBuilder() {	
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 10,5));
		this.setBorder(new TitledBorder("This is a panel displayed for plugin node's edition."));
		
		topPanel = new JPanel(new BorderLayout());
		topPanel.setBorder(new TitledBorder("Simplest Boolean Node"));
//		topPanel.add(new JLabel(" - Boolean Node - "));
//		this.nameLabel = new JLabel();
//		topPanel.add(this.nameLabel);
//		this.add(topPanel, BorderLayout.NORTH);
		
		JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20,10));
		centerPanel.setBorder(new TitledBorder("Probabilities"));
		centerPanel .add(new JLabel("true"));
		this.trueTxtField = new JTextField(10);
		centerPanel.add(trueTxtField);
		centerPanel.add(new JLabel("false"));
		this.falseTxtField = new JTextField(10);
		centerPanel.add(falseTxtField);
		topPanel.add(centerPanel, BorderLayout.CENTER);
		
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20,10));
		this.okButton = this.createButton("ok");
		this.okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((ProbabilisticNode)StubPanelBuilder.this.node).getProbabilityFunction().setValue(
						0, Float.parseFloat(StubPanelBuilder.this.trueTxtField.getText()));
				((ProbabilisticNode)StubPanelBuilder.this.node).getProbabilityFunction().setValue(
						1, Float.parseFloat(StubPanelBuilder.this.falseTxtField.getText()));
				StubPanelBuilder.this.updateUI();
				StubPanelBuilder.this.repaint();
			}
		});
		bottomPanel.add(this.okButton);

		this.cancelButton = this.createButton("cancel");
		this.cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StubPanelBuilder.this.trueTxtField.setText(String.valueOf(((ProbabilisticNode)node).getProbabilityFunction().getValue(0)));
				StubPanelBuilder.this.falseTxtField.setText(String.valueOf(((ProbabilisticNode)node).getProbabilityFunction().getValue(1)));
				StubPanelBuilder.this.updateUI();
				StubPanelBuilder.this.repaint();
			}
		});
		bottomPanel.add(this.cancelButton);
		
		topPanel.add(bottomPanel, BorderLayout.SOUTH);
		
		this.add(topPanel);
	}

	/* (non-Javadoc)
	 * @see unbbayes.gui.table.extension.IProbabilityFunctionPanelBuilder#buildProbabilityFunctionEditionPanel()
	 */
	public JPanel buildProbabilityFunctionEditionPanel() {
		return this;
	}

	/* (non-Javadoc)
	 * @see unbbayes.gui.table.extension.IProbabilityFunctionPanelBuilder#getProbabilityFunctionOwner()
	 */
	public Node getProbabilityFunctionOwner() {
		return this.node;
	}

	/* (non-Javadoc)
	 * @see unbbayes.gui.table.extension.IProbabilityFunctionPanelBuilder#setProbabilityFunctionOwner(unbbayes.prs.Node)
	 */
	public void setProbabilityFunctionOwner(Node node) {
		this.node = node;
		try {
			topPanel.setBorder(new TitledBorder("Simplest Boolean Node - " + node.getName()));
//			this.nameLabel.setText();
			this.trueTxtField.setText(String.valueOf(((ProbabilisticNode)node).getProbabilityFunction().getValue(0)));
			this.falseTxtField.setText(String.valueOf(((ProbabilisticNode)node).getProbabilityFunction().getValue(1)));
			this.updateUI();
			this.repaint();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates buttons containing labels translated to default locale, using {@link UIManager}
	 * @param key
	 * @return
	 */
	public JButton createButton(String key) {

		String text = UIManager.getString("OptionPane." + key + "ButtonText", Locale.getDefault());
		JButton button = new JButton(text);

		String num = UIManager.getString("OptionPane." + key + "ButtonMnemonic", Locale.getDefault());
		if (num != null) {
			int n = Integer.parseInt(num);
			if (n != 0) {
				button.setMnemonic(n);
			}
		}

		return button;
	}


}
