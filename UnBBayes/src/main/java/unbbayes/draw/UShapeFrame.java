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
 
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import unbbayes.prs.Node;

public class UShapeFrame extends UShape   implements INodeHolderShape
{     
	/**
	 * 
	 */
	private static final long serialVersionUID = -4179123071455600926L;
	
	protected Rectangle2D rect;
	protected Rectangle rectTitle;
	protected int heightTitle = 30;	
	
	public UShapeFrame(UCanvas c, Node n, int x, int y, int w, int h)
	{
		super(c, n, x, y, w, h);
		
		InitShape();
    }     
	
	public void InitShape() 
	{
		rect = new Rectangle2D.Double(GAP ,GAP,getWidth()-GAP*2-1,getHeight()-GAP*2-1 );
		
		rectTitle = new Rectangle(GAP, GAP, getWidth()-GAP*2-1, heightTitle );
	}
	
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g); 
	 
		InitShape();
		
	    Graphics2D g2 = (Graphics2D) g;
   	    g2.draw (rect);
   	    g2.draw (rectTitle);
   	    
   	    drawText(g, rectTitle);
	}	    
	
	public boolean contain(double x, double y) 
	{
		return rectTitle.contains((double)(x-getGlobalX()), (double)(y-getGlobalY()));
	}   
}
 