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

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

/** This class shows a screen that initializes a loading and shows
 *  it's status for the FileHistoryController instance
 *
 *  @author Danilo Balby Silva Castanheira (danbalby@yahoo.com)
 *  @version $1.0 $ (07/04/2003)
 */
public class ProgressDialog extends JDialog
{
	
	/** Serialization runtime version number */
	private static final long serialVersionUID = 0;	
	
	/** Create an instance of this class  */
	public ProgressDialog(String message, IProgress progress)
	{
		resource = unbbayes.util.ResourceController.newInstance().getBundle(
				unbbayes.controller.resources.ControllerResources.class.getName());
		
		setTitle(resource.getString("loading"));
		setSize(280,130);
		setModal(true);
		setResizable(false);
		
		//center screen
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
   		Dimension frameSize = getSize();
   		if (frameSize.height > screenSize.height) 
   		{
      		frameSize.height = screenSize.height;
    	}
    	if (frameSize.width > screenSize.width) 
    	{
     		 frameSize.width = screenSize.width;
    	}
		setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);

		Container contentPane = getContentPane();
		
		//label
		JPanel panelLabel = new JPanel();
		contentPane.add(panelLabel, "North");
		if (message != null)
		{
			JLabel label = new JLabel(resource.getString("loading")+message);		
			panelLabel.add(label);
		}
		
		
		//progress bar
		JPanel panelProgress = new JPanel();
		contentPane.add(panelProgress, "Center");
		///////////
		min = 0;
		max = progress.maxCount();
		this.progress = progress;
		///////////
		progressBar = new JProgressBar(min,max);
		panelProgress.add(progressBar);
						
		//button
		JPanel panelButton = new JPanel();
		contentPane.add(panelButton, "South");
		JButton cancelButton = new JButton(resource.getString("cancel"));
		panelButton.add(cancelButton);
		cancelButton.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					activity.requestCancel();
					activity.interrupt();
					activityMonitor.stop();
					hide();		
				}
			});
		
		//startActivity
		activity = new LoadingActivity(progress,max);
					
		//activityMonitor
		activityMonitor = new Timer(500,
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					int current = activity.getCurrent();
					progressBar.setValue(current);
					progressBar.setStringPainted(true);
					progressBar.setString(current+resource.getString("of")+max);
					if(current==activity.getTarget())
					{	
						activityMonitor.stop();
						hide();
					}
				}
			});
	}
	
	/** start the loading 
	 * @return boolean value indication if activity was terminated successfully or not
	 * */
	public boolean load()
	{
		if(max>NEED_DIALOG_VALUE)
		{
			activity.start();
			activityMonitor.start();
			show();
			return !activity.wasActivityCancelled();
		}
		else
		{
			while (progress.next());
			return true;
		}
	}
	
	//--------------------------------------------------------------------//
	
	public final int NEED_DIALOG_VALUE = 1000;
	private IProgress progress;	
	private Timer activityMonitor;
	private LoadingActivity activity;
	private JProgressBar progressBar;
	private int min;
	private int max;
	private ResourceBundle resource;
}
