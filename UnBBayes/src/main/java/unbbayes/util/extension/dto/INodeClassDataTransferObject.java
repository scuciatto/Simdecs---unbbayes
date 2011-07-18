/**
 * 
 */
package unbbayes.util.extension.dto;

import javax.swing.ImageIcon;

import unbbayes.draw.extension.IPluginUShapeBuilder;
import unbbayes.gui.table.extension.IProbabilityFunctionPanelBuilder;
import unbbayes.prs.builder.INodeBuilder;

/**
 * This is a general interface for classes implementing
 * data transfer objects (objects that are created
 * in order to transfer data into and from several
 * architectural layers), associated with nodes
 * loaded from plugin managers (extensions).
 * 
 * It basically carries a node builder, a shape
 * builder, icons, and a panel in order to render
 * forms to edit probability functions (e.g. probability tables)
 * 
 * @author Shou Matsumoto
 *
 */
public interface INodeClassDataTransferObject extends IDataTransferObject {

	/**
	 * Obtains the node builder being transferred
	 * @return
	 */
	public INodeBuilder getNodeBuilder();
	
	/**
	 * Sets the node builder being transferred
	 * @param node
	 */
	public void setNodeBuilder(INodeBuilder node);
	
	/**
	 * The shape builder used to render the node inside the canvas.
	 * @return
	 */
	public IPluginUShapeBuilder getShapeBuilder();
	
	/**
	 * The shape builder used to render the node inside the canvas.
	 * @param shape
	 */
	public void setShapeBuilder(IPluginUShapeBuilder shape);
	
	/**
	 * The object containing a panel used by UnBBayes to visually edit
	 * the probability function of a node.
	 * @return
	 */
	public IProbabilityFunctionPanelBuilder getProbabilityFunctionPanelBuilder();
	
	/**
	 * The object containing a panel used by UnBBayes to visually edit
	 * the probability function of a node.
	 * @param panelBuilder
	 */
	public void setProbabilityFunctionPanelBuilder(IProbabilityFunctionPanelBuilder panelBuilder);
	
	/**
	 * The icon of the node.
	 * This is usually used by the GUI in order to 
	 * render the cursor when a "add new plugin node" is
	 * called.
	 * @return
	 */
	public ImageIcon getIcon();
	
	/**
	 * The icon of the node.
	 * This is usually used by the GUI in order to 
	 * render the cursor when a "add new plugin node" is
	 * called.
	 * @param icon
	 */
	public void setIcon(ImageIcon icon);
	
	/**
	 * Icons used by the program to create custom cursors
	 * called when a user presses a "create new plugin node" button.
	 * @return
	 */
	public ImageIcon getCursorIcon();
	
	/**
	 * Icons used by the program to create custom cursors
	 * called when a user presses a "create new plugin node" button.
	 * @param cursorIcon
	 */
	public void setCursorIcon(ImageIcon cursorIcon);

	/**
	 * Obtains the name of this set of information. This is
	 * just a simple identifier for users, and may be used as a label
	 * on buttons.
	 * @return
	 */
	public String getName();
	
	/**
	 * The name of this set of information. This is
	 * just a simple identifier for users, and may be used as a label
	 * on buttons.
	 * @param name
	 */
	public void setName(String name);
	
	/**
	 * Obtains a description of this set of information. This is
	 * just a additional information for users, and may be used as a tool tip text.
	 * @return
	 */
	public String getDescription();
	
	/**
	 * A description of this set of information. This is
	 * just a additional information for users, and may be used as a tool tip text.
	 * @param description
	 */
	public void setDescription(String description);
		
}
