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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;

import javax.swing.JTextField;

public class UShapeText extends UShape  
{     
	/**
	 * 
	 */
	private static final long serialVersionUID = 6819706583768111310L;

	protected Rectangle2D rect;
	
	protected static JTextField Input;
	protected static FlowLayout Layout;
	
	public UShapeText(UShape s, int x, int y, int w, int h)
	{
		super(s.getCanvas(), null, x, y, w, h);
		s.add(this);
		setUseSelection(false);
		  
	
	    Input = new JTextField ("Input", 10);
	    Input.setBounds(20, 20, w-40, h-40); 
		 
	    Input.setBackground (Color.white);
		  
		add (Input);  
		 
		
		JTextField nameTextField = new JTextField("Input", 10);
		s.add(nameTextField, BorderLayout.NORTH);

	    KeyListener keyListener = new KeyListener() {
	      public void keyPressed(KeyEvent keyEvent) {
	        printIt("Pressed", keyEvent);
	      }

	      public void keyReleased(KeyEvent keyEvent) {
	        printIt("Released", keyEvent);
	      }

	      public void keyTyped(KeyEvent keyEvent) {
	        printIt("Typed", keyEvent);
	      }

	      private void printIt(String title, KeyEvent keyEvent) {
	        int keyCode = keyEvent.getKeyCode();
	        String keyText = KeyEvent.getKeyText(keyCode);
	        System.out.println(title + " : " + keyText + " / " + keyEvent.getKeyChar());
	      }
	    };
	    
	    nameTextField.addKeyListener(keyListener);
	    
	    InitShape();
    }     
	
	public void InitShape() 
	{
		rect = new Rectangle2D.Double(GAP ,GAP,getWidth()-GAP*4-1,getHeight()-GAP*4-1 );
	} 
	
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		
		InitShape();
		
		Graphics2D g2 = (Graphics2D) g;		
	  	g2.draw (rect);
	}	    
	
	public boolean contain(double x, double y) 
	{
		return false;
	}   
}

