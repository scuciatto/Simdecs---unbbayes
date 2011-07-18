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

import javax.swing.BorderFactory;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.UIResource;
import javax.swing.table.TableCellRenderer;

/**
 * Adapted from BooleanRenderer class found in source code of
 * javax.swing.JTable
 */
public class RadioButtonCellRenderer implements TableCellRenderer, UIResource {
	JRadioButton radioButton;

	Border emptyBorder;

	public RadioButtonCellRenderer() {
		radioButton = new JRadioButton();
		radioButton.setHorizontalAlignment(JRadioButton.CENTER);
		radioButton.setBorderPainted(true);
		emptyBorder = BorderFactory.createEmptyBorder(1, 1, 1, 1);
	}

	public Component getTableCellRendererComponent(JTable table,
			Object value, boolean isSelected, boolean hasFocus, int row,
			int col) {
		if (isSelected) {
			radioButton.setBackground(table.getSelectionBackground());
			radioButton.setForeground(table.getSelectionForeground());
		} else {
			radioButton.setBackground(table.getBackground());
			radioButton.setForeground(table.getForeground());
		}
		if (hasFocus)
			radioButton.setBorder(UIManager
					.getBorder("Table.focusCellHighlightBorder"));
		else
			radioButton.setBorder(emptyBorder);

		radioButton.setSelected(((Boolean) value).booleanValue());
		return radioButton;
	}
}
