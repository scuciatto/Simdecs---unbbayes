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
package unbbayes.util;

import java.awt.geom.Point2D;

public class SerializablePoint2D extends Point2D.Double implements
		java.io.Serializable {

	/** Serialization runtime version number */
	private static final long serialVersionUID = 0;
	
	public SerializablePoint2D(){
		super(); 
	}

	public SerializablePoint2D(double x, double y){
		super(x,y); 
	}	
	
	private void writeObject(java.io.ObjectOutputStream out)
			throws java.io.IOException {
		out.writeDouble(x);
		out.writeDouble(y);
	}

	private void readObject(java.io.ObjectInputStream in)
			throws java.io.IOException, ClassNotFoundException {
		x = in.readDouble();
		y = in.readDouble();
	}
}