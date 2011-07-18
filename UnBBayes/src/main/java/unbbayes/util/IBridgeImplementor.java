/**
 * 
 */
package unbbayes.util;

/**
 * Bridge pattern to separate abstraction to its operation.
 * For example, separating representation and how it is saved.
 * @author Shou Matsumoto
 *
 */
public interface IBridgeImplementor {
	public void execute();
}
