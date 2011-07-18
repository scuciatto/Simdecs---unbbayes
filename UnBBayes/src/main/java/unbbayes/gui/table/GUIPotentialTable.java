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
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import unbbayes.prs.Node;
import unbbayes.prs.bn.PotentialTable;

public class GUIPotentialTable {
	
	private PotentialTable potentialTable;
	
	public GUIPotentialTable(PotentialTable potentialTable) {
		this.potentialTable = potentialTable;
	}
	/**
	 * This method is responsible to represent the potential table as a JTable.
	 * @return Returns the JTable representing this potential table.
	 * TODO MIGRATE TO A DIFFERENT CLASS - GUI.TABLE.PROBABILISTICTABLE
	 */
	public JTable makeTable() {
		JTable table;
		int nStates = 1;
		// Number of variables
		int nVariables = potentialTable.variableCount();
		Node node = (Node)potentialTable.getVariableAt(0);
		NumberFormat df = NumberFormat.getInstance(Locale.getDefault());
		df.setMaximumFractionDigits(4);

		// calculate the number of states by multiplying the number of
		// states that each father (variables) has. Where variable 0 is the
		// node itself. That is why we divide the table size by the number 
		// of states in the node itself. 
		/*
		 * Ex: states = 12 / 3;
		 * 
		 * |------------------------------------------------------| 
		 * | Father 2     |      State 1      |      State 2      |
		 * |--------------|-------------------|-------------------| 
		 * | Father 1     | State 1 | State 2 | State 1 | State 2 |
		 * |------------------------------------------------------| 
		 * | Node State 1 |    1    |    1    |    1    |    1    | 
		 * | Node State 2 |    0    |    0    |    0    |    0    |
		 * | Node State 3 |    0    |    0    |    0    |    0    |
		 * |------------------------------------------------------|
		 * 
		 */
		nStates = potentialTable.tableSize() / node.getStatesSize();

		// the number of rows is the number of states the node has.
		int rows = node.getStatesSize();

		// the number of columns is the number of states that we calculated
		// before plus one that is the column where the fathers names and
		// the states of the node itself will be placed.
		int columns = nStates + 1;
		
		// Constructing the data of the data model.
		/*
		 * Ex: data[3][4 + 1]
		 * |------------------------------------------------------| 
		 * | Node State 1 |    1    |    1    |    1    |    1    | 
		 * | Node State 2 |    0    |    0    |    0    |    0    |
		 * | Node State 3 |    0    |    0    |    0    |    0    |
		 * |------------------------------------------------------|
		 */
		String[][] data = new String[rows][columns];

		// Constructing the first header's row
		/*
		 * Ex: Following the example above this is the first header's row. 
		 * 
		 * |--------------|-------------------|-------------------| 
		 * | Father 1     | State 1 | State 2 | State 1 | State 2 |
		 * |------------------------------------------------------| 
		 * 
		 */
		String[] column = new String[data[0].length];
		Node firtHeaderNode;
		// If there is no father, this is going to be the first header's 
		// row:
		/*
		 * |-----------|---------------| 
		 * | State     |  Probability  |
		 * |---------------------------| 
		 * 
		 */
		if (nVariables == 1) {
			column[0] = "State";
			column[1] = "Probability";
		} else {
			firtHeaderNode = (Node)potentialTable.getVariableAt(1);
			/*
			 * Ex: Here we get the variable "Father 1" and set its name in 
			 *     the header. 
			 * 
			 * |--------------| 
			 * | Father 1     |
			 * |--------------- 
			 * 
			 */
			column[0] = firtHeaderNode.getName();
			for (int i = 0; i < data[0].length - 1; i++) {
				if (nVariables > 1) {
					// Reapeats all states in the node until there are cells to
					// fill.
					/*
					 * Ex: Following the example above. Here the states go. 
					 * 
					 * |-------------------|-------------------| 
					 * | State 1 | State 2 | State 1 | State 2 |
					 * ----------------------------------------| 
					 * 
					 */
					column[i + 1] = firtHeaderNode.getStateAt(i % firtHeaderNode.getStatesSize());
				}
			}
		}
		
		// Filling the data of the data model.
		/*
		 * Ex: Fill the data[3][5] constructed above.
		 * |------------------------------------------------------| 
		 * | Node State 1 |    1    |    1    |    1    |    1    | 
		 * | Node State 2 |    0    |    0    |    0    |    0    |
		 * | Node State 3 |    0    |    0    |    0    |    0    |
		 * |------------------------------------------------------|
		 */
		// The values are arranged in the potential table as a vector.
		/*
		 * Ex: This would be the vector in the potential table.
		 * |-------------------------------------------------------------------| 
		 * | Vector Position | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 | 11 | 
		 * | Vector Value    | 1 | 0 | 0 | 1 | 0 | 0 | 1 | 0 | 0 | 1 | 0  | 0  |
		 * |-------------------------------------------------------------------|
		 */
		// So, for each column we jump the number of values corresponding 
		// that column, that is, the number of rows. 
		for (int c = 1, n = 0; c < columns; c++, n += rows) {
			for (int r = 0; r < rows; r++) {
				// So, data[0][3] = vector[6 + 0] = 1 
				data[r][c] = "" + "" + df.format(potentialTable.getValue(n + r));
			}
		}
		// Now that we filled the values, we are going to put this node's
		// states name.
		/*
		 * Ex: Fill the data[i][0] constructed above, that is, its states 
		 *     name.
		 * |--------------- 
		 * | Node State 1 | 
		 * | Node State 2 |
		 * | Node State 3 |
		 * |---------------
		 */ 
		for (int i = 0; i < rows; i++) {
			data[i][0] = node.getStateAt(i);
		}
		
		// Constructing the table so far.
		/*
		 * Ex: The table so far, following the example above.
		 * 
		 * |--------------|-------------------|-------------------| 
		 * | Father 1     | State 1 | State 2 | State 1 | State 2 |
		 * |------------------------------------------------------| 
		 * | Node State 1 |    1    |    1    |    1    |    1    | 
		 * | Node State 2 |    0    |    0    |    0    |    0    |
		 * | Node State 3 |    0    |    0    |    0    |    0    |
		 * |------------------------------------------------------|
		 * 
		 */
		DefaultTableModel model = new DefaultTableModel();
		model.setDataVector(data, column);
		table = new JTable();
		
		// Setup to allow grouping the header.
		table.setColumnModel(new GroupableTableColumnModel());
		table.setTableHeader(new GroupableTableHeader(
				(GroupableTableColumnModel) table.getColumnModel()));
		table.setModel(model);
		
		// Setup Column Groups
		GroupableTableColumnModel cModel = (GroupableTableColumnModel) table
				.getColumnModel();
		ColumnGroup cNodeGroup = null;
		ColumnGroup cNodeTempGroup = null;
		ColumnGroup cGroup = null;
		List<ColumnGroup> cGroupList = new ArrayList<ColumnGroup>();
		List<ColumnGroup> previousCGroupList = new ArrayList<ColumnGroup>();
		int columnIndex;
		boolean firstNode = true;
		int sizeColumn = 1;
		// Sets default color for parents name in first column.
		/*
		 * |--------------- 
		 * | Father 2     |
		 * |--------------| 
		 * | Father 1     |
		 * |--------------- 
		 * 
		 */
		cModel.getColumn(0).setHeaderRenderer(new GroupableTableCellRenderer());
		// Sets default color for node's states
		/*
		 * |--------------- 
		 * | Node State 1 | 
		 * | Node State 2 |
		 * | Node State 3 |
		 * |---------------
		 * 
		 */
		cModel.getColumn(0).setCellRenderer(new GroupableTableCellRenderer(Color.BLACK, Color.YELLOW));
		// Fill all other headers, but the first (that has already been 
		// set). It ignores k = 0 (the node itself) and k = 1 (the fist 
		// father).
		for (int k = 2; k < nVariables; k++) {
			Node parent = (Node)potentialTable.getVariableAt(k);
			int nPreviousParentStates = potentialTable.getVariableAt(k-1).getStatesSize();
			sizeColumn *= nPreviousParentStates;
			// Set the node name as a header in the first column
			if (!firstNode) {
				cNodeTempGroup = cNodeGroup;
				cNodeGroup = new ColumnGroup(new GroupableTableCellRenderer(), parent.getName());
				cNodeGroup.add(cNodeTempGroup);
			} else {
				cNodeGroup = new ColumnGroup(new GroupableTableCellRenderer(), parent.getName());
				cNodeGroup.add(cModel.getColumn(0));
			}
			columnIndex = 1;
			cGroup = null;
			while (columnIndex <= nStates) {
				for (int i = 0; i < parent.getStatesSize(); i++) {
					cGroup = new ColumnGroup(parent.getStateAt(i));
					if (!firstNode) {
						for (int j = 0; j < nPreviousParentStates; j++) {
							ColumnGroup group = previousCGroupList.get(columnIndex-1);
							cGroup.add(group);
							columnIndex++;
						}
					} else {
						for (int j = 0; j < sizeColumn; j++) {
							cGroup.add(cModel.getColumn(columnIndex++));
						}
					}
					cGroupList.add(cGroup);
				}
			}
			previousCGroupList = cGroupList;
			cGroupList = new ArrayList<ColumnGroup>();
			firstNode = false;
			// Update the number of states
			nStates /= nPreviousParentStates;
		}
		// It adds all parents' node name as header
		if (cNodeGroup != null) {
			cModel.addColumnGroup(cNodeGroup);
		}
		// It adds only the first row (here it is the last parent's states) 
		// of the header that has all other headers (all other parent's states)
		// as sub-headers.
		if (previousCGroupList != null) {
			for (ColumnGroup group : previousCGroupList) {
				cModel.addColumnGroup(group);
			}
		}
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		return table;
	}

	/**
	 * Show the potential table. Used for DEBUG.
	 * 
	 * @param title Title of the window to be shown.
	 */
	public void showTable(String title) {
		JDialog diag = new JDialog();
		diag.getContentPane().add(new JScrollPane(makeTable()));
		diag.pack();
		diag.setVisible(true);
		diag.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		diag.setTitle(title);
	}

}
