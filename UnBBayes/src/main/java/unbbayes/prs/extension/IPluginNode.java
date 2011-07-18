/**
 * 
 */
package unbbayes.prs.extension;

import unbbayes.prs.INode;
import unbbayes.prs.Node;




/**
 * A plugin node must extend this class, so that UnBBayes can understand that
 * currently selected node shall have special treatment.
 * Besides, UnBBayes's basic GUI architecture expects all nodes to extend {@link unbbayes.prs.Node}
 * (so, a plugin node must extend some subclass of Node and implement this interface)
 * @author Shou Matsumoto
 *
 */
public interface IPluginNode extends INode  {

	/**
	 * Obtains a {@link Node} representation of this plugin node.
	 * In other word, this method converts an {@link IPluginNode} into {@link Node}
	 * @return
	 */
	public Node getNode();
	
}
