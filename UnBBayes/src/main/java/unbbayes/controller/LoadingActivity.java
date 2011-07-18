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


/** This class loads the instances in a separate thread for 
 *  the FileHistoryController instance
 *
 *  @author Danilo Balby Silva Castanheira (danbalby@yahoo.com)
 *  @version $1.0 $ (07/04/2003)
 */
public class LoadingActivity extends Thread
{
	/** Position of the last instance loaded */
	private int current;
	/** Position of the last instance */
	private int target;
	/** Data used for the loadings */
	private IProgress progress;
	/** Says if the progress was cancelled by the user */
	private boolean cancel;
	
	//--------------------------------------------------------------------//
	
	/**
   	* Creates a new instance of this class based on a loader
   	*
   	* @param loader Loader
	*/
	public LoadingActivity(IProgress l, int t)
	{
		current = 0;
		target = t;
		progress = l;
		cancel=false;
	}
	
	//--------------------------------------------------------------------//
	
	/**
   	* Gets the position of the last instance loaded
   	*
   	* @return Current instance
   	*/
	public int getCurrent()
	{
		return current;
	}
	
	//--------------------------------------------------------------------//
	
	/**
   	* Gets the position of the last instance 
   	*
   	* @return Target instance
   	*/
	public int getTarget()
	{
		return target;
	}
	
	//-------------------------------------------------------------------//
	
	/**
   	* Changes the status of the activity to cancelled by user
   	*/
	public void requestCancel()
	{
		cancel=true;
	}
	
	//-------------------------------------------------------------------//
	
	/**
   	* Returns if the the activity was cancelled by user or not
   	*/
	public boolean wasActivityCancelled()
	{
		return cancel;
	}
	
	//-------------------------------------------------------------------//
	
	/**
   	* Starts the loading
   	*/
	public void run()
	{
    	while (current < target)
		{  
			if (cancel)
			{
				progress.cancel();
				return;
			}
			if((!progress.next()) || isInterrupted())
			{
				current = target;
				return;
			}
			current++;
			Thread.yield();
		}	
	}
}