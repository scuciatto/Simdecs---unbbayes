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

public final class GeometricUtil {
	
	/**
     *  This method is responsible for finding the tangent point in the circunference of the point2 
     *  in relation to the point1.
     *
     *@param  point1  First circunference's center point (x,y).
     *@param  point2  Second circunference's center point (x,y).
     *@param  r       Circunference's radius.
     *@return         The tangent point to the second circunference.
     */
    public static Point2D.Double getCircunferenceTangentPoint(Point2D.Double point1, Point2D.Double point2, double r) {
        double x = 0;
        double y = 0;
        double x1 = point1.getX();
        double y1 = point1.getY();
        double x2 = point2.getX();
        double y2 = point2.getY();

        if (x2 < x1) {
            x = Math.abs((r * Math.cos(Math.atan((y2 - y1) / (x2 - x1)))) - x1);
            y = Math.abs((r * Math.sin(Math.atan((y2 - y1) / (x2 - x1)))) - y1);
        }
        else {
            x = Math.abs((r * Math.cos(Math.atan((y2 - y1) / (x2 - x1)))) + x1);
            y = Math.abs((r * Math.sin(Math.atan((y2 - y1) / (x2 - x1)))) + y1);
        }
        return new Point2D.Double(x, y);
    }

}
