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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

import unbbayes.prs.Node;
import unbbayes.prs.bn.ProbabilisticNode;
import unbbayes.prs.bn.TreeVariable;

public class UShapeProbabilisticNode extends UShape  implements INodeHolderShape
{       
	/**
	 * 
	 */
	private static final long serialVersionUID = 1848727135580831185L;
	
	protected Ellipse2D ellipse;  
	protected Rectangle2D rect; 
	protected UShapeState stateShape; 
	protected int stateHeight = 18;
	protected String finding;
	
	
	protected Color stateColor[] = {Color.BLUE, Color.RED, Color.GREEN, Color.ORANGE, Color.CYAN, Color.DARK_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.YELLOW };
	 
	public static final String STYPE_BAR  = "Bar";
 
	public UShapeProbabilisticNode(UCanvas c, Node pNode, int x, int y, int w, int h)
	{
		super(c, pNode, x, y, w, h);  
		 		
		if( pNode != null )
		{
			if( (pNode).getDisplayMode() == Node.DISPLAY_MODE_ELLIPSE )
			{
				shapeTypeChange(STYPE_NONE);
			}
			else
			if( (pNode).getDisplayMode() == Node.DISPLAY_MODE_BAR )
			{
				shapeTypeChange(STYPE_BAR);
			}
		}
		
		InitShape();		 
    }    
	 
	public void InitShape() 
	{
		rect = new Rectangle2D.Double(GAP ,GAP,getWidth()-GAP*2-1,getHeight()-GAP*2-1 );
		ellipse = new Ellipse2D.Double(GAP,GAP,getWidth()-GAP*2-1,getHeight()-GAP*2-1); 
	} 

	public void shapeTypeChange(String s) 
	{  
		super.setShapeType(s);
		
		if( s == STYPE_NONE )
		{
			setBounds(getX(), getY(), getWidth(), getHeight());
			node.setDisplayMode(Node.DISPLAY_MODE_ELLIPSE ); 
		}
		else
		if( s == STYPE_BAR )
		{
			node.setDisplayMode(Node.DISPLAY_MODE_BAR );
		}
		
		update();
	}	
	 	 
	public void removeShapeState()
	{	
	 	List<UShape> temp;
	 	
	 	temp = new ArrayList<UShape>();
	 	
		int n = this.getComponentCount();
    	for( int i = 0; i < n; i++ )
    	{
    		UShape shape = (UShape)this.getComponent(i);
    		
    		if (shape instanceof UShapeState)
    		{
    			temp.add(shape);
    		}
    	}
    	
    	Iterator it = temp.iterator();

		while (it.hasNext())			 
    	{
			UShape shape = (UShape) it.next();
    		remove(shape);
    	}
    }
	
	public void update(String strFinding) 
	{ 
		finding = strFinding;
		update();
	}
	
	public void update() 
	{  		 
		//by young4
		super.update();
		
		//by young3
		updateNodeInformation();
		
		if( node instanceof ProbabilisticNode && ((ProbabilisticNode)node).hasEvidence() ) {
			finding = node.getStateAt(((ProbabilisticNode)node).getEvidence());
		} else {
			finding = "";
		}
			
		
		if( STYPE_BAR == getShapeType() )
		{	
			removeShapeState();
					
			int size = node.getStatesSize(); 
						
			if( size > 0 ) 
			{ 
				if(getWidth() < 250 )
					setBounds(getX(), getY(), 250, 50+size*stateHeight);
				else
					setBounds(getX(), getY(), getWidth(), 50+size*stateHeight);
				
				InitShape(); 
				//by young4
			//	createResizeBtn();
				removeTextBox();
				
				TreeVariable treeVariable = (TreeVariable) node;
				 
			// 	double[] mean = node.getMean();
			// 	double[] stdDev = node.getStandardDeviation();
				
			//	table.setValueAt(parent.getName(), 0, 0);
			//	table.setValueAt(resource.getString("mean"), 1, 0);
			//	table.setValueAt(resource.getString("stdDev"), 2, 0);
	
				setBackColor(getNode().getColor());
				
				/* Other columns */
				for (int i = 0; i < size; i++) 
				{ 
					stateShape = new UShapeState( getCanvas(), 
												  null, 
												  GAP+1, 
												  getHeight() - GAP + stateHeight*i - stateHeight*size - 1, 
												  getWidth() - GAP*2 - 2, 
												  stateHeight);
					stateShape.setName(node.getStateAt(i));
					stateShape.setMarginal(treeVariable.getMarginalAt(i));
					
					if( finding == node.getStateAt(i) )
					{
						//setBackColorWithoutNode(Color.ORANGE);
						setBackColorWithoutNode(Color.lightGray);
						stateShape.setBackColor(stateColor[0]);
						
					//	stateShape.setBackColor(Color.GRAY);
					}
					else
					{						
						stateShape.setBackColor(stateColor[0]);
					}
					
					add(stateShape); 
					
					//Using Table, but we will use marginal distribution
					/*
					PotentialTable potTab;
					
					potTab = ((IRandomVariable) node).getPotentialTable();
					stateShape.setMarginal(potTab.getValue(i));
					//stateShape.setMarginal((float)(1/(float)(size-1))*(float)i);
					//stateShape.setMarginal(treeVariable.getMarginalAt(i));
					*/			
										
				}
				
				//Set Text Area
				rectTitle.width = getWidth();
				rectTitle.height = 50;				
			}
		}
		else
		if( STYPE_NONE == getShapeType() )
		{
			removeShapeState();
			
		//	setBounds(getX(), getY(), defaultWidth, defaultHeight);
			
			InitShape();
			//by young4
		//	createResizeBtn();
			removeTextBox();
		}
		
		repaint();
		
//		getCanvas().onShapeChanged(this);
	}
	   
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g); 
		
		InitShape();
		
		Graphics2D g2 = (Graphics2D) g;
		
		if( STYPE_NONE == getShapeType() )
		{  
			g2.setPaint( new GradientPaint( getWidth()/2, getHeight(),  getBackColor(), 
											getWidth()/2, 0,Color.white, false));
 
			g2.fill(ellipse);
			g2.setColor(getDrawColor());
	  	    g2.draw (ellipse);
	  	    drawText(g); 
		}
		else
		if( STYPE_BAR == getShapeType() )
		{
			g2.setPaint( new GradientPaint( getWidth()/2, getHeight(),  getBackColor(), 
					getWidth()/2, 0,Color.white, false));

 		    g2.fillRect(GAP ,GAP,getWidth()-GAP*2-1,getHeight()-GAP*2-1 );
		    g2.setColor(getDrawColor());
	    	g2.draw (rect);
	    	drawText(g, rectTitle);
		}
	}	    
	
	public boolean contain(double x, double y) 
	{
		if( STYPE_BAR == getShapeType() )
		{
			if( rect != null )
				return rect.contains((double)(x-getGlobalX()), (double)(y-getGlobalY()));
		}
			
		if( ellipse != null )
			return ellipse.contains((double)(x-getGlobalX()), (double)(y-getGlobalY()));	
		
		return false;
	}
	
	public void mouseClicked(MouseEvent arg0) 
	{  
		if (SwingUtilities.isLeftMouseButton(arg0)) 
	    {
			
	        if (arg0.getClickCount() == 2 && !arg0.isConsumed()) 
	        { 
	        	//by young4
	        	if( STYPE_NONE == getShapeType() )
	    		{  
	        		arg0.consume();	        	
	         		setState( STATE_WAIT_EDIT, null );
	    		}
	        	 
	        }
	    }
	        
	    if (SwingUtilities.isMiddleMouseButton(arg0)) 
	    {
	          System.out.println("Middle button released.");
	    }
	        
	    if (SwingUtilities.isRightMouseButton(arg0)) 
	    {
	       	System.out.println("Right button released.");
	       	
	       	createPopupMenu();
	       	
	     	JMenuItem item;
	     	
	     	if( STYPE_NONE == getShapeType() )
	     	{
	     		item = new JMenuItem("Belief bar");
	     		     		
				item.addActionListener
				(	
					new ActionListener() 
					{
						public void actionPerformed(ActionEvent ae)
						{   
							getCanvas().onShapeTypeChanged(STYPE_BAR);
							node.setDisplayMode(Node.DISPLAY_MODE_BAR );
						}
					}
				);		 

				popup.add(item);
	     	}
	     	else
	     	if( STYPE_BAR == getShapeType() )
	     	{
	     		item = new JMenuItem("Default Shape");
		     		
				item.addActionListener
				(	
					new ActionListener() 
					{
						public void actionPerformed(ActionEvent ae)
						{   
							getCanvas().onShapeTypeChanged(STYPE_NONE);
							node.setDisplayMode(ProbabilisticNode.DISPLAY_MODE_ELLIPSE );
						}
					}
				);		 
				
				popup.add(item);
	     	}
	     	 
	     	// the if below was added because it was trying to open "properties"
	     	// even to Continuous Node or learning nodes, which had no implementation of "properties"
	     	// and was not even a probabilistic node (why?)
			if (getNode() instanceof ProbabilisticNode && getCanvas().controller != null) {
				item = new JMenuItem(resource.getString("properties"));
				item.addActionListener
				(	
					new ActionListener() 
					{
						public void actionPerformed(ActionEvent ae)
						{   
							getCanvas().controller.showExplanationProperties((ProbabilisticNode)getNode());
						}
					}
				);
				
				popup.add(item);
			}
			
	     	
	       	popup.setEnabled(true);
			popup.show(arg0.getComponent(),arg0.getX(),arg0.getY());
	    }
	} 
}

