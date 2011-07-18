/*
 *  UnBBayes
 *  Copyright (C) 2002, 2008 Universidade de Brasilia - http://www.unb.br
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
package unbbayes.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUIUtils {

	public static Point getCenterPositionForComponent(int width, int heigth){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int positionX = screenSize.width - (screenSize.width/2) - (width/2);
        int positionY = screenSize.height - (screenSize.height/2) - (heigth/2);
        return new Point(positionX, positionY);
	}
	
	public static JFrame getWaitScreen(){
		JFrame jDialog = new JFrame();
		JPanel panelWait = new JPanel(); 
		JButton label = new JButton("Aguarde...");
		panelWait.setLayout(new GridLayout(1,1)); 
		panelWait.add(label);
		jDialog.setContentPane(panelWait);
		jDialog.setPreferredSize(new Dimension(200, 100));
		jDialog.setLocation(GUIUtils.getCenterPositionForComponent(200, 100)); 
		jDialog.pack();
		jDialog.validate();
		jDialog.repaint();
		return jDialog;
	}
	
}
