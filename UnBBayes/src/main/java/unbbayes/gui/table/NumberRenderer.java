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
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.UIResource;
import javax.swing.table.TableCellRenderer;

/**
 * 
 */
public class NumberRenderer implements TableCellRenderer, UIResource {
	JFormattedTextField ftf;

	Border emptyBorder;
	
	public NumberRenderer() {
		this(2, 2);
	}

	public NumberRenderer(int minFracDigits, int maxFracDigits) {
		NumberFormat numberFormat = NumberFormat.getNumberInstance();
		numberFormat.setMinimumFractionDigits(minFracDigits);
		numberFormat.setMaximumFractionDigits(maxFracDigits);
		numberFormat.setMinimumIntegerDigits(1);
		ftf = new JFormattedTextField(numberFormat);
		ftf.setHorizontalAlignment(JTextField.CENTER);

		emptyBorder = BorderFactory.createEmptyBorder(1, 1, 1, 1);
	}

	public Component getTableCellRendererComponent(JTable table,
			Object value, boolean isSelected, boolean hasFocus, int row,
			int col) {
		if (isSelected) {
			ftf.setBackground(table.getSelectionBackground());
			ftf.setForeground(table.getSelectionForeground());
		} else {
			ftf.setBackground(table.getBackground());
			ftf.setForeground(table.getForeground());
		}
		if (hasFocus)
			ftf.setBorder(UIManager
					.getBorder("Table.focusCellHighlightBorder"));
		else
			ftf.setBorder(emptyBorder);

		ftf.setValue(((Float) value));
		
		return ftf;
	}
}
