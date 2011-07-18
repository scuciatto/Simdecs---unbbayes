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

package unbbayes.controller;

import unbbayes.gui.NetworkWindow;


public class CompilationThread implements Runnable {

	private NetworkController controller;
	private NetworkWindow netWindow;
	public static Thread t;

	public CompilationThread(NetworkController controller, NetworkWindow netWindow) {
		this.controller=controller;
		this.netWindow=netWindow;
		t = new Thread(this,"progress");
		t.start();
	}

	// TODO - Rommel - It would be better to have a controller that had the code below and we just had to delegate
	// responsability to this controller.
	public void run() {
		if (! controller.compileNetwork()) {
            return;
        }
        netWindow.changeToPNCompilationPane();
	}
	

}
