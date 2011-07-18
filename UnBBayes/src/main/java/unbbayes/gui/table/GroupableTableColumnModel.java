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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;

// Steve Webb 16/09/04 swebb99_uk@hotmail.com

/**
 * Class which extends the functionality of DefaultColumnTableModel to
 * also provide capabilities to group columns. This can be used for
 * instance to aid in the layout of groupable table headers.
 */
public class GroupableTableColumnModel extends DefaultTableColumnModel {
    
    /**
     * Hold the list of ColumnGroups which define what group each normal
     * column is within, if any.
     */    
    protected ArrayList columnGroups = new ArrayList();
    

    /**
     * Add a new columngroup.
     * @param columnGroup new ColumnGroup
     */    
    public void addColumnGroup(ColumnGroup columnGroup) {
        columnGroups.add(columnGroup);
    }
    
    /**
     * Provides an Iterator to iterate over the
     * ColumnGroup list.
     * @return Iterator over ColumnGroups
     */    
    public Iterator columnGroupIterator() {
        return columnGroups.iterator();
    }
    
    /**
     * Returns a ColumnGroup specified by an index.
     * @param index index of ColumnGroup
     * @return ColumnGroup
     */    
    public ColumnGroup getColumnGroup(int index) {
        if(index >= 0 && index < columnGroups.size()) {
            return (ColumnGroup)columnGroups.get(index);
        }
        return null;
    }
    
    /**
     * Provides and iterator for accessing the ColumnGroups
     * associated with a column.
     * @param col Column
     * @return ColumnGroup iterator
     */    
    public Iterator getColumnGroups(TableColumn col) {
        if (columnGroups.isEmpty()) return null;
        Iterator iter = columnGroups.iterator();
        while (iter.hasNext()) {
            ColumnGroup cGroup = (ColumnGroup)iter.next();
            Vector v_ret = (Vector)cGroup.getColumnGroups(col,new Vector());
            if (v_ret != null) {
                return v_ret.iterator();
            }
        }
        return null;
    }
}


