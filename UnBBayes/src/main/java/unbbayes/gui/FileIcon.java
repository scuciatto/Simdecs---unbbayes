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
package unbbayes.gui;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.util.ResourceBundle;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileView;

import unbbayes.controller.IconController;
/**
 *  Essa classe extende o <code>FileView</code> que é o responsável por
 *  mostrar os ícones correspondentes para cada tipo de aquivo e pasta.
 *
 *@author     Rommel Novaes Carvalho, Michael S. Onishi
 *@created    27 de Junho de 2001
 *@see        FileView
 *@version    1.0 06/07/2001
 */
public class FileIcon extends FileView {

    private JFileChooser fc;
    private Component observer;
    protected IconController iconController = IconController.getInstance();

	/** Load resource file from this package */
  	private static ResourceBundle resource = unbbayes.util.ResourceController.newInstance().getBundle(
  			unbbayes.gui.resources.GuiResources.class.getName());

    /**
     *  Constroi um observer para desenhar ícones e um JFileChooser para pegar
     *  ícones (<code>Icon</code>) padrões.
     *
     *@param  c o componente (<code>Component</code>) que sera o observer para desenhar icones.
     *@see    Component
     *@see    Icon
     */
    public FileIcon(Component c) {
        //componente para criar icones de arquivos
        observer = c;
        fc = new JFileChooser();
    }


    /**
     *  Retorna a descricao do arquivo desejado.
     *
     *@param     f o arquivo (<code>File</code>) ao qual se deseja a descricao
     *@return    a descricao do arquivo (<code>String</code>)
     *@see       File
     *@see       String
     */
    public String getDescription(File f) {
        return getTypeDescription(f);
    }


    /**
     *  Retorna o icone (<code>Icon</code>) correnspondente ao arquivo desejado.
     *
     *@param     f o arquivo (<code>File</code>) que se deseja pegar o icone
     *@return    o icone correspondente ao arquivo f
     *@see       Icon
     *@see       File
     */
    public Icon getIcon(File f) {
        /*
         * if (f.isDirectory())
         * {
         * return directoryIcon;
         * }
         */
        String name = f.getName();
        if (name.endsWith(".arff")) {
            return iconController.getArffFileIcon();
        }
        if (name.toLowerCase().endsWith(".txt")) {
            return iconController.getTxtFileIcon();
        }
        if (name.toLowerCase().endsWith(".net")) {
            return iconController.getNetFileIcon();
        }
        return fc.getIcon(f);
    }


    /**
     *  Retorna o name do arquivo desejado.
     *
     *@param     f o arquivo (<code>File</code>) que se deseja receber o name
     *@return    o name do arquivo (<code>String</code>)
     *@see       String
     *@see       File
     */
    public String getName(File f) {
        String name = f.getName();
        return name.equals("") ? f.getPath() : name;
    }


    /**
     *  Retorna o tipo do arquivo.
     *
     *@param     f o arquivo (<code>File</code>) que se deseja descricao do tipo
     *@return    o tipo do arquivo (<code>String</code>)
     *@see       String
     *@see       File
     */
    public String getTypeDescription(File f) {
        String name = f.getName().toLowerCase();
        if (f.isDirectory()) {
            return resource.getString("fileDirectoryType");
        }

        if (name.endsWith(".arff")) {
            return resource.getString("fileARFFType");
        }

        if (name.endsWith(".txt")) {
            return resource.getString("fileTXTType");
        }

        if (name.endsWith(".net")) {
            return resource.getString("fileNETType");
        }
        return resource.getString("fileGenericType");
    }


    /**
     *  Retorna se e tranversable ou nao.
     *
     *@param     f o arquivo (<code>File</code>) que se deseja saber se e tranversable
     *@return    true se for tranversable e falso caso contrario
     *@see       File
     */
    public boolean isTranversable(File f) {
        //todos diretorios serao transversable
        return f.isDirectory() ? true : false;
    }


    /**
     *  Classe que extende <code>ImageIcon</code> responsavel por desenhar
     *  um icone.
     *
     *@author     Rommel Novaes Carvalho, Michael S. Onishi
     *@created    27 de Junho de 2001
     *@see        ImageIcon
     */
    public class Icon16 extends ImageIcon {
        
    	/** Serialization runtime version number */
    	private static final long serialVersionUID = 0;	
    	
    	/**
         *  Cria e desenha um icone para o arquivo desejado.
         *
         *@param  f  o arquivo (<code>String</code>) que deseja-se criar um icone
         *@see    String
         */
        public Icon16(String f) {
            super(f);
            Image i = observer.createImage(16, 16);
            i.getGraphics().drawImage(getImage(), 0, 0, 16, 16, observer);
            setImage(i);
        }


        /**
         *  Retorna altura do icone
         *
         *@return    a altura do icone (int)
         */
        public int getIconHeight() {
            return 16;
        }


        /**
         *  Retorna a largura do icone
         *
         *@return    a altura do icone (int)
         */
        public int getIconWidth() {
            return 16;
        }


        /**
         *  Desenha o icone.
         *
         *@param  c  o componente (<code>Component</code>)
         *@param  g  o grafico (<code>Graphics</code>)
         *@param  x  a largura do icone (int)
         *@param  y  a altura do icone (int)
         *@see    Component
         *@see    Graphics
         */
        public void paintIcon(Component c, Graphics g, int x, int y) {
            g.drawImage(getImage(), x, y, c);
        }
    }

}

