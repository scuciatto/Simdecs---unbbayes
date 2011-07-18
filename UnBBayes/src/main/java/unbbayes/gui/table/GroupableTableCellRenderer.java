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

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 * Cell renderer changing foreground and background colors.
 */
public class GroupableTableCellRenderer extends DefaultTableCellRenderer {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3785644336203425095L;
	private Color foregroundColor = Color.WHITE;
	private Color backgroundColor = Color.BLUE;
	private Color selectionForegroundColor = Color.WHITE;
	private Color selectionBackgroundColor = Color.BLUE;
	
	
	public GroupableTableCellRenderer() {
		
	}
	
	public GroupableTableCellRenderer(Color foregroundColor, Color backgroundColor) {
		this.foregroundColor = foregroundColor;
		this.backgroundColor = backgroundColor;
	}
	
	/**
	 * 
	 * @param table
	 * @param value
	 * @param selected
	 * @param focused
	 * @param row
	 * @param column
	 * @return
	 */
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean selected, boolean focused, int row, int column) {
		JTableHeader header = table.getTableHeader();
		if (header != null) {
			if (selected) {
				setForeground(selectionForegroundColor);
				setBackground(selectionBackgroundColor);
			} else {
				setForeground(foregroundColor);
				setBackground(backgroundColor);
			}
			
			
		}
		setHorizontalAlignment(SwingConstants.CENTER);
		setText(value != null ? value.toString() : " ");
		setBorder(UIManager.getBorder("TableHeader.cellBorder"));
		return this;
	}
}
