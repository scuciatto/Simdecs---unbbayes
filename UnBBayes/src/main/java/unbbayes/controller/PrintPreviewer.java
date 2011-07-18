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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.Pageable;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

/**
 * Creates a pre-visualization of a printing
 *
 */
public class PrintPreviewer extends JPanel {

  /** Serialization runtime version number */
  private static final long serialVersionUID = 0;	
	
  protected Pageable pageable;
  protected PrintComponent printComponent;
  protected int pageIndex;

  protected JScrollPane scrollPane;
  protected JButton previousButton;
  protected JButton nextButton;
  protected JButton sizeButton;
  protected JTextField scaleText;

  /** Load resource file from this package */
  private static ResourceBundle resource = unbbayes.util.ResourceController.newInstance().getBundle(
		  unbbayes.controller.resources.ControllerResources.class.getName());

  public PrintPreviewer(Pageable p, int page) {
    pageable = p;
    pageIndex = page;
    printComponent = new PrintComponent(null, null);
    printComponent.setBorder(BorderFactory.createBevelBorder(
        BevelBorder.RAISED));
    buildLayout();
    displayPage(pageIndex);
  }

  /**
   * Adds the appropriate components to this panel
   */
  protected void buildLayout() {
    setLayout(new BorderLayout());
    JPanel panel = new JPanel();
    panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
    panel.add(printComponent);
    scrollPane = new JScrollPane(panel);
    add(scrollPane, BorderLayout.CENTER);
    add(getBottomPanel(), BorderLayout.SOUTH);
    addListeners();
  }

  /**
   * Returns a panel that contains the buttons supported by this interface
   */
  protected JPanel getBottomPanel() {
    JPanel outer = new JPanel();
    outer.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
    JPanel inner = new JPanel();
    inner.setLayout(new GridLayout(1, 2, 10, 0));
    previousButton = new JButton(resource.getString("previewButtonLabel"));
    inner.add(previousButton);
    nextButton = new JButton(resource.getString("nextButtonLabel"));
    inner.add(nextButton);
    outer.add(inner);
    scaleText = new JTextField(3);
    outer.add(scaleText);
    sizeButton = new JButton(resource.getString("fitToPageButtonLabel"));
    outer.add(sizeButton);
    return outer;
  }

  /**
   * Adds listeners to the buttons and the text field
   */
  protected void addListeners() {
    previousButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        displayPage(pageIndex - 1);
      }
    });
    nextButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        displayPage(pageIndex + 1);
      }
    });
    sizeButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        sizeToFit();
      }
    });
    scaleText.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        try {
          int scale = Integer.parseInt(
              scaleText.getText());
          printComponent.setScaleFactor(scale);
        } catch (NumberFormatException nfe) {};
      }
    });
  }

  /**
   * Displays the specified page within the panel
   */
  protected void displayPage(int index) {
    pageIndex = index;
    printComponent.setPrintable(pageable.getPrintable(pageIndex));
    printComponent.setPageFormat(pageable.getPageFormat(pageIndex));
    printComponent.setDisplayPage(index);
    previousButton.setEnabled(pageIndex > 0);
    nextButton.setEnabled(pageIndex <
        (pageable.getNumberOfPages() - 1));
    repaint();
  }

  /**
   * Determine the largest scale factor that can be used that will
   * allow the current page to be displayed completely. In other words,
   * make the page as large as possible without making it necessary for
   * the user to use scroll bars to view the entire page. This is
   * accomplished by calculating the ratios that represent the actual
   * width of the page relative to the available width, and the actual
   * height of the page to the available height. The smaller of the two
   * values will dictate how large the ratio can be set while still
   * allowing the entire page to be displayed.
   */
  protected void sizeToFit() {
    int newScaleFactor;
    Dimension compSize = printComponent.getSizeWithScale(100d);
    Dimension viewSize = scrollPane.getSize();

    int scaleX = (viewSize.width - 25) * 100 / compSize.width;
    int scaleY = (viewSize.height - 25) * 100 / compSize.height;
    newScaleFactor = Math.min(scaleX, scaleY);

    printComponent.setScaleFactor(newScaleFactor);
    scaleText.setText(Integer.toString(newScaleFactor));
    repaint();
  }

}
