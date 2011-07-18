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
import java.awt.geom.RoundRectangle2D;

public class UShapeRoundRect extends UShape   implements INodeHolderShape
{     
	/**
	 * 
	 */
	private static final long serialVersionUID = 2316247200702304811L;
	
	protected RoundRectangle2D roundRect;
	
	public UShapeRoundRect(UCanvas c, int x, int y, int w, int h)
	{
		super(c, null, x, y, w, h);
		
		InitShape();

    }     
	
	public void InitShape() 
	{
		roundRect = new RoundRectangle2D.Double(GAP ,GAP,getWidth()-GAP*2-1,getHeight()-GAP*2-1, getHeight()/2,getHeight()/2 );
	}  
	
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		
		InitShape();
		
		Graphics2D g2 = (Graphics2D) g;
		g2.draw (roundRect);
	    drawText(g);
	}	   
	
	public boolean contain(double x, double y) 
	{
		return roundRect.contains((double)(x-getGlobalX()), (double)(y-getGlobalY()));
	}
}