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
package unbbayes.util;

import java.util.ResourceBundle;

/**
 * Classe que representa um array din�mico do tipo <code>float</code>.
 *
 * @author Michael
 * @author Rommel
 */
public final class FloatCollection implements java.io.Serializable {

	/** Serialization runtime version number */
	private static final long serialVersionUID = 0;
	
	public static final int DEFAULT_SIZE = 30;

    public float data[];
    public int size;
    
    /** Load resource file from this package */
  	private static ResourceBundle resource = unbbayes.util.ResourceController.newInstance().getBundle(
  			unbbayes.util.resources.UtilResources.class.getName());

    public FloatCollection(int initialCapacity) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException(resource.getString("IllegalCapacityException") +
                                               initialCapacity);
        this.data = new float[initialCapacity];
    }

    public FloatCollection() {
       this(DEFAULT_SIZE);
    }


    /**
     * Increases the capacity of this <tt>floatCollection</tt> instance, if
     * necessary, to ensure  that it can hold at least the number of elements
     * specified by the minimum capacity argument.
     *
     * @param   minCapacity   the desired minimum capacity.
     */
    public final void ensureCapacity(int minCapacity) {
        int oldCapacity = data.length;
        if (minCapacity > oldCapacity) {
            float oldData[] = data;
            int newCapacity = (oldCapacity * 3)/2 + 1;
            if (newCapacity < minCapacity) {
               newCapacity = minCapacity;
            }
            data = new float[newCapacity];
            System.arraycopy(oldData, 0, data, 0, size);
        }
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return  the number of elements in this list.
     */
    public final int size() {
        return size;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param  index index of element to return.
     * @return the element at the specified position in this list.
     */
    public final float get(int index) {
        return data[index];
    }

    /**
     * Replaces the element at the specified position in this list with
     * the specified element.
     *
     * @param index index of element to replace.
     * @param element element to be stored at the specified position.
     * @return the element previously at the specified position.
     */
    public final float set(int index, float element) {
        float oldValue = data[index];
        data[index] = element;
        return oldValue;
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param o element to be appended to this list.
     * @return <tt>true</tt>
     */
    public final boolean add(float newElement) {
        ensureCapacity(size + 1);
        data[size++] = newElement;
        return true;
    }

    /**
     * Inserts the specified element at the specified position in this
     * list. Shifts the element currently at that position (if any) and
     * any subsequent elements to the right (adds one to their indices).
     *
     * @param index index at which the specified element is to be inserted.
     * @param element element to be inserted.
     * @throws    IndexOutOfBoundsException if index is out of range
     *		  <tt>(index &lt; 0 || index &gt; size())</tt>.
     */
    public final void add(int index, float element) {
        /*
        if (index > size || index < 0) {
           throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
        }
        */
        ensureCapacity(size+1);
        System.arraycopy(data, index, data, index + 1,
                 size - index);
        data[index] = element;
        size++;
    }

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their
     * indices).
     *
     * @param index the index of the element to removed.
     * @return the element that was removed from the list.
     */
    public final void remove(int index) {
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(data, index+1, data, index,
                     numMoved);
        if (size > 0) {        
	        data[--size] = 0;
        }
    }
}