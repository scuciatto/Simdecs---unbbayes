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
import java.awt.geom.Ellipse2D;

public class UShapeSizeBtn extends UShape implements MouseMotionListener, MouseListener, Cloneable
{       
	/**
	 * 
	 */
	private static final long serialVersionUID = 5019421060606694321L;
	
	Ellipse2D ellipse;
	
	public UShapeSizeBtn(UShape s, int x, int y, int cursor) 
	{
		super(s.getCanvas(), null, x, y, GAP, GAP);
        
		setOpaque(false);	
		s.add(this);
		setCursor(cursor); 
		
		InitShape();	
    }    
	  
	public void InitShape() 
	{
		ellipse = new Ellipse2D.Double(0,0,getWidth()-1,getHeight()-1);	
	} 
	
	public void paintComponent(Graphics g) 
	{ 
        Graphics2D g2 = (Graphics2D)g;
  
        g2.setPaint( new GradientPaint(getWidth()/2, 0, Color.white, getWidth()/2,getHeight()-1, Color.blue, false));
        g2.fill(ellipse);
	}
	   
	public Rectangle getDraggedRect(int x, int y) 
	{
		Rectangle rc = new Rectangle(0,0,0,0);
		 
		if(m_cursor == Cursor.NW_RESIZE_CURSOR)
		{
			rc.x = x; rc.y = y;	rc.width -= x; rc.height -= y;
		}
		else
		if(m_cursor == Cursor.N_RESIZE_CURSOR)
		{
			rc.y = y;	rc.height -= y;
		}		
		else			
		if(m_cursor == Cursor.NE_RESIZE_CURSOR)
		{
			rc.width = x; rc.y = y;	rc.height -= y;
		}	
		else
		if(m_cursor == Cursor.E_RESIZE_CURSOR)
		{
			rc.x = x; rc.width -= x;
		}
		else
		if(m_cursor == Cursor.SW_RESIZE_CURSOR)
		{
			rc.x = x; rc.height = y; rc.width -= x; 
		}
		else
		if(m_cursor == Cursor.S_RESIZE_CURSOR)
		{
			rc.height = y;
		}		
		else
		if(m_cursor == Cursor.W_RESIZE_CURSOR)
		{
			rc.width = x; 
		}	
		else
		if(m_cursor == Cursor.SE_RESIZE_CURSOR)
		{
			rc.width = x; rc.height = y;
		}	   
		
		return rc;
	}
	

	public void mouseDragged(MouseEvent arg0) 
	{
		System.out.println("UShapeSizeBtn_mouseDragged");
		
		Rectangle rc = getDraggedRect( arg0.getX(), arg0.getY() );
		
		getCanvas().drawResizeRectEnter(rc); 
	}


	public void mouseMoved(MouseEvent arg0) 
	{
		setCursor(new Cursor(getCursorStyle()));
	}


	public void mouseClicked(MouseEvent arg0) {
	
	}


	public void mouseEntered(MouseEvent arg0) {

	}


	public void mouseExited(MouseEvent arg0) {

	}


	public void mousePressed(MouseEvent arg0) {

	}


	public void mouseReleased(MouseEvent arg0) {
		System.out.println("mouseReleased");
		setDrawColor(getLineColor());
		repaint();
		
		Rectangle rc = getDraggedRect( arg0.getX(), arg0.getY() );
		
		getCanvas().drawResizeRectReleased(rc); 
	}
}

