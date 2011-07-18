/**
 * 
 */
package unbbayes.util.extension.dto.impl;

import java.util.HashMap;

import javax.swing.ImageIcon;

import unbbayes.draw.extension.IPluginUShapeBuilder;
import unbbayes.gui.table.extension.IProbabilityFunctionPanelBuilder;
import unbbayes.prs.builder.INodeBuilder;
import unbbayes.util.extension.dto.INodeClassDataTransferObject;

/**
 * A simple implementation of {@link INodeClassDataTransferObject}
 * that just delegates each getter/setter to {@link #getObject(String)}
 * and {@link #setObject(String, Object)} using pre-generated keys.
 * @author Shou Matsumoto
 *
 */
public class NodeDto  extends Dto implements INodeClassDataTransferObject {

	private static final String NODE = "node";
	private static final String SHAPE = "shape";
	private static final String ICON = "icon";
	private static final String PANEL = "panel";
	private static final String CURSOR = "cursor";
	private static final String NAME = "name";
	private static final String DESCRIPTION = "description";
	
	/**
	 * The main constructor is not public. Use {@link #newInstance()} instead.
	 * This is kept protected in order to permit extension.
	 */
	protected NodeDto() {}
	
	/**
	 * Default constructor method
	 * @return
	 */
	public static NodeDto newInstance() {
		NodeDto ret = new NodeDto();
		ret.map = new HashMap<String, Object>();
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * @see unbbayes.util.extension.dto.INodeClassDataTransferObject#getCursorIcon()
	 */
	public ImageIcon getCursorIcon() {
		return (ImageIcon)this.getObject(CURSOR);
	}

	/*
	 * (non-Javadoc)
	 * @see unbbayes.util.extension.dto.INodeClassDataTransferObject#getIcon()
	 */
	public ImageIcon getIcon() {
		return (ImageIcon)this.getObject(ICON);
	}

	/*
	 * (non-Javadoc)
	 * @see unbbayes.util.extension.dto.INodeClassDataTransferObject#getNodeBuilder()
	 */
	public INodeBuilder getNodeBuilder() {
		return (INodeBuilder)this.getObject(NODE);
	}

	/*
	 * (non-Javadoc)
	 * @see unbbayes.util.extension.dto.INodeClassDataTransferObject#getProbabilityFunctionPanelHolder()
	 */
	public IProbabilityFunctionPanelBuilder getProbabilityFunctionPanelBuilder() {
		return (IProbabilityFunctionPanelBuilder)this.getObject(PANEL);
	}

	/*
	 * (non-Javadoc)
	 * @see unbbayes.util.extension.dto.INodeClassDataTransferObject#getShapeBuilder()
	 */
	public IPluginUShapeBuilder getShapeBuilder() {
		return (IPluginUShapeBuilder)this.getObject(SHAPE);
	}

	/*
	 * (non-Javadoc)
	 * @see unbbayes.util.extension.dto.INodeClassDataTransferObject#setCursorIcon(javax.swing.ImageIcon)
	 */
	public void setCursorIcon(ImageIcon cursorIcon) {
		this.setObject(CURSOR, cursorIcon);
	}

	/*
	 * (non-Javadoc)
	 * @see unbbayes.util.extension.dto.INodeClassDataTransferObject#setIcon(javax.swing.ImageIcon)
	 */
	public void setIcon(ImageIcon icon) {
		this.setObject(ICON, icon);
	}

	/*
	 * (non-Javadoc)
	 * @see unbbayes.util.extension.dto.INodeClassDataTransferObject#setNodeBuilder(unbbayes.prs.builder.INodeBuilder)
	 */
	public void setNodeBuilder(INodeBuilder node) {
		this.setObject(NODE, node);
	}

	/*
	 * (non-Javadoc)
	 * @see unbbayes.util.extension.dto.INodeClassDataTransferObject#setProbabilityFunctionPanelHolder(unbbayes.gui.table.extension.IProbabilityFunctionPanelBuilder)
	 */
	public void setProbabilityFunctionPanelBuilder(
			IProbabilityFunctionPanelBuilder panelHolder) {
		this.setObject(PANEL, panelHolder);
	}

	/*
	 * (non-Javadoc)
	 * @see unbbayes.util.extension.dto.INodeClassDataTransferObject#setShapeBuilder(unbbayes.draw.extension.IPluginUShapeBuilder)
	 */
	public void setShapeBuilder(IPluginUShapeBuilder shape) {
		this.setObject(SHAPE, shape);
	}

	/*
	 * (non-Javadoc)
	 * @see unbbayes.util.extension.dto.INodeClassDataTransferObject#getDescription()
	 */
	public String getDescription() {
		return (String)this.getObject(DESCRIPTION).toString();
	}

	/*
	 * (non-Javadoc)
	 * @see unbbayes.util.extension.dto.INodeClassDataTransferObject#getName()
	 */
	public String getName() {
		return (String)this.getObject(NAME);
	}

	/*
	 * (non-Javadoc)
	 * @see unbbayes.util.extension.dto.INodeClassDataTransferObject#setDescription(java.lang.String)
	 */
	public void setDescription(String description) {
		this.setObject(DESCRIPTION, description);
	}

	/*
	 * (non-Javadoc)
	 * @see unbbayes.util.extension.dto.INodeClassDataTransferObject#setName(java.lang.String)
	 */
	public void setName(String name) {
		this.setObject(NAME, name);
	}

}
