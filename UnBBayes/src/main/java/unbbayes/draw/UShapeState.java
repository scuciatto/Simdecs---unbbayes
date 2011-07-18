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
import java.awt.Cursor;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.text.NumberFormat;

import unbbayes.prs.Node;

public class UShapeState extends UShape implements MouseMotionListener, MouseListener, Cloneable
{       
	/**
	 * 
	 */
	private static final long serialVersionUID = -6856101647287130364L;
	
	protected Rectangle2D rect;
	protected Rectangle   rectTextArea;
	protected float marginal; 
	private NumberFormat nf;
	
	public UShapeState(UCanvas s, Node pNode, int x, int y, int w, int h) 
	{
		super(s, pNode, x, y, w, h);
        
		setOpaque(false);
  
		
		nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);
		
		rectTextArea = new Rectangle(0,0,(int)(w*(0.70)), h );
		 
		InitShape() ;
    }    
	  
	public void	setMarginal( float d )
	{
		marginal = d;
	}
	
	public void InitShape() 
	{
		rect = new Rectangle2D.Double(0, 0,getWidth()-1,getHeight()-1);
	} 
	
	public void paintComponent(Graphics g) 
	{
		InitShape();
		
        Graphics2D g2 = (Graphics2D)g;
  
	    g2.setPaint( new GradientPaint( (int)(rectTextArea.getWidth()), 
	    								(int)(getHeight()/2), 
	    								Color.white, 
	    								(int)((getWidth())),
	    								(int)(getHeight()/2), 
	    								getBackColor(), 
	    								false));
	    
	    g2.fillRect( (int)rectTextArea.getWidth(), 0,(int)((getWidth() -rectTextArea.getWidth())*marginal), getHeight());
	     
	    g2.setPaint( Color.BLUE );
	    	
	    g2.drawLine( (int)rectTextArea.getWidth(), (int)0, (int)rectTextArea.getWidth(), (int)getHeight());
	    g2.drawLine( (int)0, (int)0, (int)rect.getWidth(), (int)0);
  	 
	    g2.setPaint( getDrawColor() );
  	  	drawText(g, rectTextArea, getName(), TTYPE_LEFT);
	 
  	  	drawText(g, rectTextArea, nf.format(marginal * 100.0) + "%", TTYPE_RIGHT);
  	}
	
	public void mouseDragged(MouseEvent arg0) 
	{
		System.out.println("UShapeSizeBtn_mouseDragged"); 
	}

	public void mouseMoved(MouseEvent arg0) 
	{
		setCursor(new Cursor(getCursorStyle()));
	}


	public void mouseClicked(MouseEvent arg0) { 
		getCanvas().onShapeChanged(this);
	}
 

	public void mousePressed(MouseEvent arg0) {

	}
 
}

