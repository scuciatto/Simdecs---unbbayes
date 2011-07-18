/**
 * 
 */
package unbbayes.draw.extension;

import unbbayes.draw.UCanvas;
import unbbayes.draw.UShape;
import unbbayes.prs.Node;

/**
 * A common interface for UShape objects loaded
 * from plugin managers.
 * @author Shou Matsumoto
 *
 */
public interface IPluginUShape {

	/**
	 * Obtains an UShape in order to draw a node
	 * within a canvas.
	 * @param node : a node to be drawn into canvas. Some informations
	 * such as position and width/height would be used.
	 * @param canvas : canvas where this shape will be painted.
	 * @return : a instance of UShape
	 */
	public UShape getUShape(Node node, UCanvas canvas);
	
}
