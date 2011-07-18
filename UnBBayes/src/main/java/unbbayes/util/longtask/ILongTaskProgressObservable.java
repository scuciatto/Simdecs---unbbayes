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
package unbbayes.util.longtask;


/**
 * Represents a long task that has its progress observable.
 * 
 * @author Laecio
 * @author Rommel Carvalho (rommel.carvalho@gmail.com)
 */
public interface ILongTaskProgressObservable {

	public void registerObserver(ILongTaskProgressObserver observer); 
	
	public void removeObserver(ILongTaskProgressObserver observer);
	
	public void notityObservers(LongTaskProgressChangedEvent event);
	
	/**
	 * The maximum number allowed for this long task progress. 
	 * It represents 100%.
	 * @return the maximum number allowed for this long task progress. 
	 * It represents 100%.
	 */
	public int getMaxProgress();
	
	/**
	 * The current number of this long task progress. 
	 * It represents a percentage.
	 * @return the current number of this long task progress. 
	 * It represents a percentage.
	 */
	public int getCurrentProgress();
	
	/**
	 * Returns the percentage of the progress done so far. 
	 * It should be Math.round((float)currentProgress / maxProgress * 10000), 
	 * a number between 0 and 10000.
	 * @return the percentage of the progress done so far. 
	 */
	public int getPercentageDone();
	
	/**
	 * Returns a message with a description of the current 
	 * status of the long task progress.
	 * @return a message with a description of the current 
	 * status of the long task progress.
	 */
	public String getCurrentProgressStatus();
	
}
