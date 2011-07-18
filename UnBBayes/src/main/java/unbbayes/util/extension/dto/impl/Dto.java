/**
 * 
 */
package unbbayes.util.extension.dto.impl;

import java.util.HashMap;
import java.util.Map;

import unbbayes.util.extension.dto.IDataTransferObject;

/**
 * Basic implementation of general data transfer object not using generics
 * @author Shou Matsumoto
 *
 */
public class Dto implements IDataTransferObject {

	protected Map<String, Object> map;
	
	/**
	 * The main constructor is not public. Use {@link #newInstance()} instead.
	 * This is kept protected in order to permit extension.
	 */
	protected Dto() {}
	
	public static Dto newInstance() {
		Dto ret = new Dto();
		ret.map = new HashMap<String, Object>();
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * @see unbbayes.util.extension.dto.INodeClassDataTransferObject#getObject(java.lang.String)
	 */
	public Object getObject(String key) {
		return this.map.get(key);
	}

	/*
	 * (non-Javadoc)
	 * @see unbbayes.util.extension.dto.INodeClassDataTransferObject#setObject(java.lang.String, java.lang.Object)
	 */
	public void setObject(String key, Object object) {
		if (object == null) {
			this.map.remove(key);
		} else {
			this.map.put(key, object);
		}
	}

}
