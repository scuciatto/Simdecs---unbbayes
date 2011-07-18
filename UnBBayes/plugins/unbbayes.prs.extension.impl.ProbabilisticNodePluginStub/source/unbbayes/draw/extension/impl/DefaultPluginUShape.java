/**
 * 
 */
package unbbayes.draw.extension.impl;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import unbbayes.draw.UCanvas;
import unbbayes.draw.UShape;
import unbbayes.draw.UShapeDecisionNode;
import unbbayes.draw.UShapePentagon;
import unbbayes.draw.extension.IPluginUShape;
import unbbayes.prs.Node;

/**
 * This is a sample of plugin ushape.
 * @author Shou Matsumoto
 *
 */
public class DefaultPluginUShape extends UShapePentagon implements
		IPluginUShape {

	/**
	 * This is a sample of plugin ushape.
	 */
	public DefaultPluginUShape() {
		this(null, null, 0,0,0,0);
	}
	
	/**
	 * This is a sample of plugin ushape.
	 * @param c
	 * @param node
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public DefaultPluginUShape(UCanvas c, Node node, int x, int y, int w, int h) {
		super(c, x, y, w, h);
		if (node != null) {
			this.setNode(node);
			this.setLabel(node.getName());
			this.setName(node.getName());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see unbbayes.draw.extension.IPluginUShape#getUShape(unbbayes.prs.Node, unbbayes.draw.UCanvas)
	 */
	public UShape getUShape(Node node, UCanvas canvas) {
		this.setCanvas(canvas);
		this.setNode(node);
		this.setLocation(
				(int) node.getPosition().x - node.getWidth() / 2, 
				(int) node.getPosition().y - node.getHeight() / 2
			);
		this.setSize(node.getWidth(), node.getHeight());
		this.setName(node.getName());
		this.setLabel(node.getName());
		this.setVisible(true);
		this.updateUI();
		return this;
	}

	/* (non-Javadoc)
	 * @see unbbayes.draw.UShapePentagon#paintComponent(java.awt.Graphics)
	 */
	@Override
	public void paintComponent(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
	 	
		g2.setPaint( new GradientPaint( getWidth()/2, getHeight(),  getBackColor(), 
										getWidth()/2, 0, 			Color.white, false));
		
		g2.fill(parallelogram);
		
 		super.paintComponent(g);
	}
	
	

}
