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

import java.util.ArrayList;

/**
 *
 * @author  Paulo F. Duarte
 */
public class TwoLevelMap<P,K,V> {
    private ArrayMap<P,ArrayMap<K,V>> mainMap = new ArrayMap<P,ArrayMap<K,V>>();

	/*
	 * P -> chave principal
	 * K -> chave secund�ria
	 * V -> valor
	 */    
    
    public Object put(P mainKey, K subKey, V value) {
        ArrayMap<K,V> subMap = mainMap.get(mainKey);
        Object oldValue;
        if (subMap == null) {
            subMap = new ArrayMap<K,V>();
            mainMap.put(mainKey, subMap);
            subMap.put(subKey, value);
            return null;
        }
        oldValue = (String[])subMap.get(subKey);
        subMap.put(subKey, value);
        return oldValue;
    }
    public Object get(String mainKey, String subKey) {
        ArrayMap<K,V> subMap = mainMap.get(mainKey);
        if (subMap == null)
            return null;
        return subMap.get(subKey);
    }

    public ArrayList<P> getMainKeys() {
        return mainMap.getKeys();
    }

    public ArrayList<K> getSubKeys(String mainKey) {
        ArrayMap<K,V> subMap = mainMap.get(mainKey);
        if (subMap == null)
            return null;
        return subMap.getKeys();
    }

    public void clear() {
        mainMap.clear();
    }
}
