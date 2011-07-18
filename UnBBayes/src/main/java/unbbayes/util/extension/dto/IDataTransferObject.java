/**
 * 
 */
package unbbayes.util.extension.dto;

/**
 * General interface for data tranfer objects
 * (objects that are created
 * in order to transfer data into and from several
 * architectural layers).
 * @author Shou Matsumoto
 *
 */
public interface IDataTransferObject {

	
	/**
	 * These methods can be used to store additional parameters into
	 * this DTO.
	 * @param key : cannot be null
	 * @return
	 */
	public Object getObject(String key);
	
	/**
	 * These methods can be used to store additional parameters into
	 * this DTO.
	 * @param key. Cannot be null
	 * @param object. If set to null, the key/object pair will be removed
	 */
	public void setObject(String key, Object object);
}
