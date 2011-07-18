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

public class LongTaskProgressChangedEvent {

	public String msg; 
	public int percentageDone;
	
	public LongTaskProgressChangedEvent(String msg, int percentageDone) {
		this.msg = msg;
		this.percentageDone = percentageDone;
	}
	
	public LongTaskProgressChangedEvent(int percentageDone) {
		this("", percentageDone);
	}
	
	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	/**
	 * Returns the percentage of the task completed. 
	 * @return the percentage of the task completed. An 
	 *  integer from 0 to 10000.
	 */
	public int getPercentageDone() {
		return percentageDone;
	}
	
	/**
	 * Sets the percentage of the task completed. 
	 * @param percentageDone the percentage of the task completed. 
	 * 	It has to be an integer from 0 to 10000.
	 */
	public void setPercentageDone(int percentageDone) {
		this.percentageDone = percentageDone;
	} 
	
}
