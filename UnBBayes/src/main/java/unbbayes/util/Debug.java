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

public class Debug {
	
	private static boolean debug;
	
	public static void setDebug(boolean debug) {
		Debug.debug = debug;
		
	}
	
	public static boolean isDebugMode(){
		return debug; 
	}
	
	public static void print(char message) {
		if (debug)
			System.out.print(message);
	}
	
	public static void print(String message) {
		if (debug)
			System.out.print(message);
	}
	
	public static void println(String message) {
		if (debug)
			System.out.println(message);
	}
	
	public static void print(String format, String message) {
		if (debug)
			System.out.printf(format, message);
	}
	
	public static void print(String format, Object ... message) {
		if (debug)
			System.out.printf(format, message);
	}
	
	public static void println(Class classOrigin, String message) {
		if (debug)
			System.out.println("[DEBUG] " + classOrigin + ": " + message);
	}
	
	public static void println(Class classOrigin, String message, Throwable t) {
		if (debug) {
			System.out.println("[DEBUG] " + classOrigin + ": " + message);
			System.out.println("[DEBUG] \t " + t.getMessage() + ": ");
			for (StackTraceElement element : t.getStackTrace()) {
				System.out.println("[DEBUG] \t \t" + element.toString() + "; ");
			}
		}
	}
}
