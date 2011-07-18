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
package unbbayes.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.border.Border;

import unbbayes.util.longtask.ILongTaskProgressObserver;
import unbbayes.util.longtask.LongTaskProgressChangedEvent;

public class LongTaskProgressBar implements ILongTaskProgressObserver {

	private JButton cancelButton;
	
	public JButton getCancelButton() {
		return cancelButton;
	}

	private JProgressBar progressBar;
	
	public JProgressBar getProgressBar() {
		return progressBar;
	}

	private JFrame frm;

	private Container content;

	private Thread t;

	public LongTaskProgressBar(boolean showProgressBar) {
		this("Reading...", showProgressBar);
	}
	
	public LongTaskProgressBar(String title, boolean showProgressBar) {
		frm = new JFrame();
		frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		cancelButton = new JButton("Cancel");
		content = frm.getContentPane();
		content.add(cancelButton);
		cancelButton.setBounds(100, 35, 100, 25);
		cancelButton.addActionListener(new CancelActionListener()); // Add the button's action
		progressBar = new JProgressBar(0, 10000);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		Border border = BorderFactory.createTitledBorder(title);
		progressBar.setBorder(border);
		content.add(progressBar, BorderLayout.NORTH);
		frm.setSize(300, 100);
		frm.setVisible(showProgressBar);
	}

	public void setThread(Thread t) {
		this.t = t;
	}

	public void setProgressBar(int n) {
		progressBar.setValue(n);
	}

	public void hideProgressBar() {
		frm.setVisible(false);
	}
	
	public void showProgressBar() {
		frm.setVisible(true);
	}
	
	public void update(){
		progressBar.paintImmediately(0, 0, progressBar.getWidth(), progressBar.getHeight()); 
	}
	
	public void update(LongTaskProgressChangedEvent status) {
		progressBar.setValue(status.getPercentageDone()); 
		update(); 
	}

	// The action
	protected class CancelActionListener implements ActionListener {

		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e) {
			frm.setVisible(false);
			frm.dispose();
			t.stop();
		}
	}

}
