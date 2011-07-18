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

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Pageable;
import java.awt.print.Printable;

import javax.swing.JTextArea;

/**
 *  <code>JTextArea</code> printing class
 */
public class PrintText implements Printable, Pageable {

  protected JTextArea texto;
  protected PageFormat pageFormat;

  public PrintText(JTextArea texto, PageFormat pf) {
    this.texto = texto;
    pageFormat = pf;
  }

  /**
   * Perform the printing here
   */
  public int print(Graphics g, PageFormat pf, int index) {
    Dimension size = new Dimension();
    //  Get the TextArea's preferred size
    if ((texto.getWidth() == 0) || (texto.getHeight() == 0)) {
      texto.setSize(texto.getPreferredSize());
    }
//    int textWidth = texto.getWidth();
    int textHeight = texto.getHeight();

    int positionX = 0;
    int positionY = 0;

    //  Loop until we have printed the entire text
    int pageIndex = 0;
    while (positionY < textHeight) {
      positionX = 0;
//      while (positionX < textWidth) {
        size.setSize(getPrintSize(positionX, positionY));
        if (pageIndex == index) {
          //  Paint as much of the text as will fit on a page
          paintText(g, positionX, positionY, size);
          return Printable.PAGE_EXISTS;
        }
        pageIndex++;
        positionX += size.width;
//      }
      positionY += size.height;
    }
    return Printable.NO_SUCH_PAGE;
  }

  /**
   * Calculate how much of the table will fit on a page without
   * causing a row or column to be split across two pages
   */
  protected Dimension getPrintSize(int positionX, int positionY) {

    int maxWidth = (int)(pageFormat.getImageableWidth());
    int maxHeight = (int)(pageFormat.getImageableHeight());
//    int printWidth = texto.getWidth() - positionX;
//    int printHeight = texto.getHeight() - positionY;
    return new Dimension(maxWidth, maxHeight);
  }

  /**
   * Paint / print a portion of the text
   */
  protected void paintText(Graphics g, int positionX, int positionY,
      Dimension size) {
    int offsetX = (int)(pageFormat.getImageableX());
    int offsetY = (int)(pageFormat.getImageableY());
    g.translate(offsetX - positionX, offsetY - positionY);
    g.clipRect(positionX, positionY, size.width, size.height);
    texto.paint(g);
  }

  /**
   * Calculate the number of pages it will take to print the entire text
   */
  public int getNumberOfPages() {
    Dimension size = new Dimension();
    int textHeight = texto.getHeight();
    int positionX = 0;
    int positionY = 0;

    int pageIndex = 0;
    while (positionY < textHeight) {
      positionX = 0;
//      while (positionX < textWidth) {
          size.setSize(getPrintSize(positionX, positionY));
          positionX += size.width;
          pageIndex++;
//      }
      positionY += size.height;
    }
    return pageIndex;
  }

  public Printable getPrintable(int index) {
    return this;
  }

  public PageFormat getPageFormat(int index) {
    return pageFormat;
  }
}