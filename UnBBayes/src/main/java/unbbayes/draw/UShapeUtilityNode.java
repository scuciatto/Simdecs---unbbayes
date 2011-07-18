/*
 *  UnBBayes
 *  Copyright (C) 2002, 2009 Universidade de Brasilia - http://www.unb.br
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
package unbbayes.draw;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;

import unbbayes.prs.Node;

public class UShapeUtilityNode extends UShape   implements INodeHolderShape
{       
	/**
	 * 
	 */
	private static final long serialVersionUID = 6554173137402074445L;
	
	protected GeneralPath parallelogram; 
	
	public UShapeUtilityNode(UCanvas c, Node pNode, int x, int y, int w, int h)
	{
		super(c, pNode, x, y, w, h);  
		  
		InitShape();
    }
	
	public void update() 
	{  	
		//by young4
		super.update();
		
		//by young3
		updateNodeInformation();	
		InitShape();
		repaint();
	}
	
	public void InitShape() 
	{
		parallelogram = new GeneralPath();
		parallelogram.moveTo((float)((getWidth())/2),		(float)(GAP));
		parallelogram.lineTo((float)(-GAP + getWidth()), 	(float)(getHeight()/2));  
		parallelogram.lineTo((float)((getWidth())/2 ), 		(float)(getHeight()-GAP));  
		parallelogram.lineTo((float)(GAP ), 				(float)(getHeight()/2));
		parallelogram.closePath(); 
	}
	 
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g); 
		
		InitShape();
		
		Graphics2D g2 = (Graphics2D) g;
		 
		g2.setPaint( new GradientPaint( getWidth()/2, getHeight(),  getBackColor(), 
				getWidth()/2, 0,Color.white, false));
 
		g2.fill(parallelogram);
		g2.setColor(getLineColor());
 		g2.draw(parallelogram);
 		drawText(g);
		 
	}	    
	
	public boolean contain(double x, double y) 
	{
		return parallelogram.contains((double)(x-getGlobalX()), (double)(y-getGlobalY()));
	}
}

