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

import java.awt.print.PageFormat;
import java.awt.print.Pageable;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ResourceBundle;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * Creating an instance of this class and printing it allows it to
 * add a status dialog during printing. The print requests are
 * simply delegated to the Pageable that actually contains the
 * data to be printed, but by intercepting those calls, we can update
 * the page number displayed in our dialog so that it indicates
 * which page is currently being displayed.
 */
public class PrintMonitor implements Pageable {

  protected PrinterJob printerJob;
  protected Pageable pageable;
  protected JOptionPane optionPane;
  protected JDialog statusDialog;

  /** Load resource file from this package */
  private static ResourceBundle resource = unbbayes.util.ResourceController.newInstance().getBundle(
		  unbbayes.controller.resources.ControllerResources.class.getName());

  public PrintMonitor(Pageable p) {
    pageable = p;
    printerJob = PrinterJob.getPrinterJob();
    String[] options = {resource.getString("cancelOption")};
    optionPane = new JOptionPane("",
        JOptionPane.INFORMATION_MESSAGE,
        JOptionPane.CANCEL_OPTION,
        null, options);
    statusDialog = optionPane.createDialog(null,
        resource.getString("printerStatus"));
  }

  /**
   * Create a new thread and have it call the print() method.
   * This ensures that the AWT event thread will be able to handle
   * the Cancel button if it is pressed, and can cancel the print job.
   */
  public void performPrint(boolean showDialog)
      throws PrinterException {
    printerJob.setPageable(this);
    if (showDialog) {
      boolean isOk = printerJob.printDialog();
      if (!isOk) return;
    }
    optionPane.setMessage(resource.getString("initializingPrinter"));
    Thread t = new Thread(new Runnable() {
      public void run() {
        statusDialog.setVisible(true);
        if (optionPane.getValue() !=
            JOptionPane.UNINITIALIZED_VALUE) {
          printerJob.cancel();
        }
      }
    });
    t.start();
    printerJob.print();
    statusDialog.setVisible(false);
  }

  public int getNumberOfPages() {
    return pageable.getNumberOfPages();
  }

  /*
   * Update our dialog message and delegate the getPrintable() call
   */
  public Printable getPrintable(int index) {
    optionPane.setMessage(resource.getString("printingPage") + (index + 1));
    return pageable.getPrintable(index);
  }

  public PageFormat getPageFormat(int index) {
    return pageable.getPageFormat(index);
  }
}
