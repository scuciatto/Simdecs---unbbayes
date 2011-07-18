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
package unbbayes.controller;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.print.PageFormat;
import java.awt.print.Pageable;
import java.awt.print.Printable;

import javax.swing.JComponent;

/**
 * Useful class for printing a view of a graphic-mode network
 */
public class PrintNet implements Printable, Pageable {

  protected JComponent net;
  protected PageFormat pageFormat;
  protected Rectangle rect;
  protected String title;

  public PrintNet(String title, JComponent net, Rectangle rect, PageFormat pf) {
     this.net = net;
     this.title = title;
     pageFormat = pf;
     this.rect = rect;
  }

  /**
   * Perform the printing here
   */
  public int print(Graphics g, PageFormat pf, int index) {
      if (index == 0) {
          paintComponent(g);
          return Printable.PAGE_EXISTS;
      }
      return Printable.NO_SUCH_PAGE;
  }

  /**
   * Paint / print a portion of the component
   */
  protected void paintComponent(Graphics g) {
    double scaleX = (pageFormat.getImageableWidth()) / rect.width;
    double scaleY = (pageFormat.getImageableHeight()-30) / (rect.height);
    double scaleFactor = Math.min(Math.min(scaleX, scaleY), 1.0);

    Graphics2D g2 = (Graphics2D)g;
    Rectangle clipRect = g2.getClipBounds();
    AffineTransform at = g2.getTransform();

    int x = (int)(pageFormat.getImageableX());
    int y = (int)(pageFormat.getImageableY());
//    int w = (int)(pageFormat.getImageableWidth());
//    int h = (int)(pageFormat.getImageableHeight());

    g.drawString(title,x,y+15);
    g2.translate(x - rect.getX(), y - rect.getY() + 30);
    g2.setClip(rect);
    g2.scale(scaleFactor, scaleFactor);
    net.paint(g);
    g2.setTransform(at);
    g2.setClip(clipRect);
  }


  /**
   * Calculate the number of pages it will take to print the entire Network
   */
  public int getNumberOfPages() {
    return 1;
  }

  public Printable getPrintable(int index) {
    return this;
  }

  public PageFormat getPageFormat(int index) {
    return pageFormat;
  }

}