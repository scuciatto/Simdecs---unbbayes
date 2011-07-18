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
package unbbayes.gui.continuous;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import unbbayes.util.ResourceController;
import unbbayes.util.SortUtil;

/**
 * 
 * @author Rommel Carvalho (rommel.carvalho@gmail.com)
 * @deprecated Continuous node is no longer supported in UnBBayes core. It has 
 * now been replaced by the CPS plugin available at http://sourceforge.net/projects/prognos/.
 */
public class ContinuousNormalDistributionPane extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JPanel followsPane;
	private JLabel followsLabel;
	
	private List<String> discreteParentNodeNameList;
	private List<String> continuousParentNodeNameList;
	
	private JPanel parentStateListPane;
	private List<JComboBox> discreteParentNodeStateSelectionList;
	
	private JPanel inputPane;
	private List<JTextField> constantTextFieldList;
	private JTextField meanTextField;
	private JTextField varianceTextField;
	
	private JPanel buttonPane;
	private JButton confirmButton;
	private JButton cancelButton;
	
	private ResourceBundle resource = ResourceController.RS_GUI;
	
	public ContinuousNormalDistributionPane(List<String> discreteParentNodeNameList, List<String> continuousParentNodeNameList) {
		if (discreteParentNodeNameList != null) {
			this.discreteParentNodeNameList = discreteParentNodeNameList;
		} else {
			this.discreteParentNodeNameList = new ArrayList<String>(0);
		}
		if (continuousParentNodeNameList != null) {
			this.continuousParentNodeNameList = continuousParentNodeNameList;
		} else {
			this.continuousParentNodeNameList = new ArrayList<String>(0);
		}
		SortUtil.sort(this.discreteParentNodeNameList);
		SortUtil.sort(this.continuousParentNodeNameList);
		
		createFollowsPane();
		createParentStateListPane();
		createInputPane();
		createButtonPane();
		createMainPane();
	}
	
	private void createMainPane() {
		this.setLayout(new BorderLayout());
		
		// Set north panel
		JPanel northPanel = new JPanel(new GridLayout(2, 1));
		northPanel.add(followsPane);
		northPanel.add(parentStateListPane);
		this.add(northPanel, BorderLayout.NORTH);
		
		// Set center panel
		this.add(inputPane, BorderLayout.CENTER);
		
		// Set south panel
		this.add(buttonPane, BorderLayout.SOUTH);
		
	}

	public void addConfirmButtonActionListener(ActionListener al) {
		this.confirmButton.addActionListener(al);
	}
	
	public void addCancelButtonActionListener(ActionListener al) {
		this.cancelButton.addActionListener(al);
	}
	
	public void addParentStateChangeActionListener(ActionListener al) {
		for (JComboBox comboBox : discreteParentNodeStateSelectionList) {
			comboBox.addActionListener(al);
		}
	}
	
	private void createButtonPane() {
		this.buttonPane = new JPanel();
		this.confirmButton = new JButton(resource.getString("confirmLabel"));
		this.cancelButton = new JButton(resource.getString("cancelLabel"));
		buttonPane.add(confirmButton);
		buttonPane.add(cancelButton);
	}

	private void createInputPane() {
		this.inputPane = new JPanel(new GridLayout((int)((continuousParentNodeNameList.size() + 2) / 2), 4));
		
		meanTextField = new JTextField(10);
		inputPane.add(new JLabel(resource.getString("meanLabel")));
		inputPane.add(meanTextField);
		varianceTextField = new JTextField(10);
		inputPane.add(new JLabel(resource.getString("varianceLabel")));
		inputPane.add(varianceTextField);
		
		this.constantTextFieldList = new ArrayList<JTextField>(continuousParentNodeNameList.size());
		for (int i = 0; i < continuousParentNodeNameList.size(); i++) {
			constantTextFieldList.add(i, new JTextField(10));
			inputPane.add(new JLabel(resource.getString("constantLabel") + i));
			inputPane.add(constantTextFieldList.get(i));
		}
		
	}

	public void createFollowsPane() {
		followsPane = new JPanel();

		StringBuffer followsTitle = new StringBuffer();
		followsTitle.append(resource.getString("followsLabel") + " ");
		for (int i = 0; i < continuousParentNodeNameList.size(); i++) {
			if (i != 0) {
				followsTitle.append(" + ");
			}
			followsTitle.append(resource.getString("constantLabel") + i + " * " + continuousParentNodeNameList.get(i));
		}
		if (continuousParentNodeNameList.size() > 0) {
			followsTitle.append(" + ");
		}
		followsTitle.append(resource.getString("normalFunctionLabel"));
		 
		followsLabel = new JLabel(followsTitle.toString());
		followsLabel.setFont(new Font("Arial", Font.BOLD, 12));
		followsLabel.setForeground(Color.BLUE);
		followsPane.add(followsLabel);
	}
	
	private void createParentStateListPane() {
		parentStateListPane = new JPanel();
		discreteParentNodeStateSelectionList = new ArrayList<JComboBox>(discreteParentNodeNameList.size());
		JLabel label;
		JComboBox comboBox;
		for (String name : discreteParentNodeNameList) {
			label = new JLabel(name + " = ");
			comboBox = new JComboBox();
			discreteParentNodeStateSelectionList.add(comboBox);
			parentStateListPane.add(label);
			parentStateListPane.add(comboBox);
			parentStateListPane.add(new JLabel("\t"));
		}
		parentStateListPane.add(new JLabel("-\t"));
		for (String name : continuousParentNodeNameList) {
			parentStateListPane.add(new JLabel(name + "\t"));
		}
	}
	
	public void fillDiscreteParentStateSelection(String parentName, List<String> stateList) {
		JComboBox comboBox = null;
		for (int i = 0; i < discreteParentNodeNameList.size(); i++) {
			if (discreteParentNodeNameList.get(i).equals(parentName)) {
				comboBox = discreteParentNodeStateSelectionList.get(i);
				break;
			}
		}
		if (comboBox != null) {
			comboBox.removeAllItems();
			for (String state : stateList) {
				comboBox.addItem(state);
			}
		}
	}

	public void setDiscreteAndContinuousParentNodeNameList(
			List<String> discreteParentNodeNameList, List<String> continuousParentNodeNameList) {
		this.discreteParentNodeNameList = discreteParentNodeNameList;
		this.continuousParentNodeNameList = continuousParentNodeNameList;
		SortUtil.sort(this.discreteParentNodeNameList);
		SortUtil.sort(this.continuousParentNodeNameList);
		createParentStateListPane();
	}
	
	public void setDiscreteParentNodeNameList(
			List<String> discreteParentNodeNameList) {
		this.discreteParentNodeNameList = discreteParentNodeNameList;
		SortUtil.sort(this.discreteParentNodeNameList);
		createParentStateListPane();
	}

	public void setContinuousParentNodeNameList(
			List<String> continuousParentNodeNameList) {
		this.continuousParentNodeNameList = continuousParentNodeNameList;
		SortUtil.sort(this.continuousParentNodeNameList);
		createParentStateListPane();
	}
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		
		List<String> dis = new ArrayList<String>();
		dis.add("D2");
		dis.add("D1");
		dis.add("D4");
		dis.add("D3");
		
		List<String> con = new ArrayList<String>();
		con.add("C2");
		con.add("C1");
		con.add("C3");
		
		ContinuousNormalDistributionPane p = new ContinuousNormalDistributionPane(dis, con);
		
		for (String string : dis) {
			p.fillDiscreteParentStateSelection(string, con);
		}
		
		
		f.add(p);
		f.pack();
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public String getMeanText() {
		return meanTextField.getText();
	}
	
	public void setMeanText(String mean) {
		meanTextField.setText(mean);
	}
	
	public String getVarianceText() {
		return varianceTextField.getText();
	}
	
	public void setVarianceText(String variance) {
		varianceTextField.setText(variance);
	}
	
	public List<String> getConstantTextList() {
		List<String> constantTextList = new ArrayList<String>(constantTextFieldList.size());
		for (JTextField textField : constantTextFieldList) {
			constantTextList.add(textField.getText());
		}
		return constantTextList;
	}
	
	public void setConstantTextList(List<String> constantList) {
		if (constantList.size() == constantTextFieldList.size()) {
			for (int i = 0; i < constantList.size(); i++) {
				constantTextFieldList.get(i).setText(constantList.get(i));
			}
		}
	}
	
	public int[] getDiscreteParentNodeStateSelectedList() {
		int[] parentStateList  = new int[discreteParentNodeStateSelectionList.size()];
		for (int i = 0; i < discreteParentNodeStateSelectionList.size(); i++) {
			parentStateList[i] = discreteParentNodeStateSelectionList.get(i).getSelectedIndex();
		}
		return parentStateList;
	}

}
