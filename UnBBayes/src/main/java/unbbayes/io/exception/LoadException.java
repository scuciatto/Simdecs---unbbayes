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
package unbbayes.io.exception;

import java.io.IOException;

import javax.xml.bind.JAXBException;

/**
 * Exception class when loading a network.
 * @author Rommel N. Carvalho
 * @author Michael S. Onishi
 * @version 1.0
 */

public class LoadException extends UBIOException {

	/** Serialization runtime version number */
	private static final long serialVersionUID = 0;		
	
    public LoadException(Throwable t) {
        super(t);
    }
    
    public LoadException(){
    	this("");
    };
    
    public LoadException(String msg){
    	super(msg);
    };
}