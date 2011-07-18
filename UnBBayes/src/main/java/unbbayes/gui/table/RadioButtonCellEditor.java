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
package unbbayes.gui.table;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 * Adapted from source code of javax.swing.DefaultCellEditor
 */
public class RadioButtonCellEditor extends AbstractCellEditor implements
		TableCellEditor {

	private static final long serialVersionUID = 1L;

	JRadioButton radioButton;

	protected int clickCountToStart = 1;

	public RadioButtonCellEditor() {
		radioButton = new JRadioButton();
		radioButton.setHorizontalAlignment(JRadioButton.CENTER);
		radioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// do not allow de-selection of radioButton
				// this is provided for in the method
				// resetNonSelectedValues
				// of the CustomTableModel class
				if (!radioButton.isSelected())
					cancelCellEditing();
				stopCellEditing();
			}
		});
	}

	public Component getTableCellEditorComponent(JTable table,
			Object value, boolean isSelected, int row, int col) {
		radioButton.setSelected(((Boolean) value).booleanValue());
		return radioButton;
	}

	public Component getComponent() {
		return radioButton;
	}

	public int getClickCountToStart() {
		return clickCountToStart;
	}

	public Object getCellEditorValue() {
		return Boolean.valueOf(radioButton.isSelected());
	}

	public boolean isCellEditable(EventObject anEvent) {
		if (anEvent instanceof MouseEvent)
			return ((MouseEvent) anEvent).getClickCount() >= clickCountToStart;
		return true;
	}

	public boolean shouldSelectCell(EventObject anEvent) {
		return true;
	}

	public boolean stopCellEditing(EventObject anEvent) {
		fireEditingStopped();
		return true;
	}

	public void cancelCellEditing() {
		fireEditingCanceled();
	}
}
