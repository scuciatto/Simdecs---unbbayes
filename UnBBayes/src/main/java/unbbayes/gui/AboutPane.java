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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.border.Border;

import unbbayes.util.ApplicationPropertyHolder;
import unbbayes.util.ResourceController;

/**
 * About pane with informations of the program. 
 * 
 * @author Laecio Lima dos Santos (laecio@gmail.com)
 * @author Rommel Novaes Carvalho (rommel.carvalho@gmail.com)
 */
public class AboutPane extends JDialog{

	private static final long serialVersionUID = -4899450284495188960L;

	private Color backgroundColor; 
	
	private String version; 
	private String buildID; 
	private String stableStatus; 
	
	private static final String COLABORATORS_PAGE = "/html/Colaborators.html"; 
	private static final String LOGO_PICTURE = "/img/logo_small.png"; 
	private static final String GNU_LICENCE_PREVIEW_PAGE = "/html/GNULicencePreview.html"; 
	
	private ResourceBundle resource = ResourceController.RS_GUI; 
	
	public AboutPane(){
		super(UnBBayesFrame.getIUnBBayes(), true); 
		initialize(); 
		setTitle(resource.getString("AboultPane")); 
	
		this.setLocation(GUIUtils.getCenterPositionForComponent(400,270));	

		BorderLayout borderLayout = new BorderLayout(); 
		setLayout(borderLayout);
		add(new MainPane(), BorderLayout.CENTER); 
		add(new LogoPane(), BorderLayout.LINE_START); 
		
		setMinimumSize(new Dimension(400, 270)); 
		setMaximumSize(new Dimension(400, 270));
		pack(); 
		backgroundColor = getBackground(); 
	}
	
	private void initialize(){
	    version = ApplicationPropertyHolder.getProperty().getProperty("version", ""); 
	    buildID = ApplicationPropertyHolder.getProperty().getProperty("buildId", ""); 
	    stableStatus = ApplicationPropertyHolder.getProperty().getProperty("stableStatus", ""); 	
	}
	
	class MainPane extends JPanel{
		
		public MainPane(){
			super();
			setLayout(new BorderLayout()); 
			add(buildHelpPanel(), BorderLayout.NORTH); 
			setPreferredSize(new Dimension(400, 300)); 
			setResizable(false);
		}
		
		private JPanel buildHelpPanel(){
			JPanel helpPanel = new JPanel(); 
			helpPanel.setLayout(new BorderLayout()); 
			
			JEditorPane editorPane = new JEditorPane();
			editorPane.setEditable(false);
			java.net.URL helpURL = AboutPane.class.getResource(
					GNU_LICENCE_PREVIEW_PAGE);
			
			if (helpURL != null) {
			    try {
			        editorPane.setPage(helpURL);
			    } catch (IOException e) {
			        System.err.println("Attempted to read a bad URL: " + helpURL);
			    }
			} else {
			    System.err.println("Attempted to read a bad URL: " + helpURL);
			}
			
			editorPane.setBackground(backgroundColor); 
			
			Border etched = BorderFactory.createLoweredBevelBorder(); 
			Border empty = BorderFactory.createEmptyBorder(5, 5, 5, 5); 
			Border compound = BorderFactory.createCompoundBorder(empty, etched); 
			editorPane.setBorder(compound); 
			
			helpPanel.add(editorPane, BorderLayout.NORTH); 
			helpPanel.add(new CollaboratorPane(), BorderLayout.CENTER); 
			helpPanel.add(new InformationPane(), BorderLayout.SOUTH); 
			
			return helpPanel; 
		}
		
	}
	
	private class InformationPane extends JPanel{
		
		JButton btnClose; 
		JButton btnLicence; 
		JButton btnFeatures; 
		JButton btnHistoric; 
		
		public InformationPane(){
			
			setLayout(new BorderLayout()); 
			this.setPreferredSize(new Dimension(100, 50)); 
			
			btnLicence = new JButton(resource.getString("ReadLicense")); 
			btnLicence.setEnabled(false); 
			btnFeatures = new JButton(resource.getString("Features"));
			btnFeatures.setEnabled(false); 
			btnHistoric = new JButton(resource.getString("VersionHistory")); 
			btnHistoric.setEnabled(false); 
			btnClose = new JButton(resource.getString("CloseAboultPane")); 
			
			JToolBar jtb = new JToolBar();
			jtb.setLayout(new FlowLayout(FlowLayout.TRAILING)); 
//			jtb.add(btnLicence); 
//			jtb.add(btnFeatures); 
//			jtb.add(btnHistoric); 
			jtb.add(btnClose); 
			jtb.setFloatable(false); 
			
			add(jtb, BorderLayout.NORTH); 
			
			addListeners(); 
		}
		
		private void addListeners(){
			btnClose.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent e) {
					AboutPane.this.dispose(); 
				}
				
			}); 
		}
		
	}
	
	/**
	 * Pane that show a html with the list of colaborators of this project
	 * 
	 * @author Laecio
	 */
	private class CollaboratorPane extends JPanel{
		
		public CollaboratorPane(){
			
			super(); 
			this.setPreferredSize(new Dimension(100, 150)); 
			this.setLayout(new BorderLayout()); 
			
			JEditorPane editorPane = new JEditorPane();
			editorPane.setEditable(false);
			java.net.URL helpURL = AboutPane.class.getResource(COLABORATORS_PAGE);
			
			if (helpURL != null) {
			    try {
			        editorPane.setPage(helpURL);
			    } catch (IOException e) {
			        System.err.println("Attempted to read a bad URL: " + helpURL);
			    }
			} else {
			    System.err.println("Attempted to read a bad URL: " + helpURL);
			}
			
			editorPane.setBackground(backgroundColor); 
			
  	        JScrollPane scrollPane =
  	            new JScrollPane(editorPane,
  	                            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
  	                            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
  	        scrollPane.setBorder(BorderFactory.createTitledBorder(
  	        		resource.getString("Collaborators"))); 
			this.add(scrollPane, BorderLayout.CENTER); 

		}
	}
	
	private class LogoPane extends JPanel{

		ImageIcon imgLogo = new ImageIcon(getClass().getResource(LOGO_PICTURE)); 
		
		public LogoPane(){
			super(new BorderLayout()); 
			setPreferredSize(new Dimension(200, 300)); 
			
			JLabel labelImg = new JLabel(imgLogo); 
			this.add(labelImg, BorderLayout.CENTER);
			this.add(new VersionPane(), BorderLayout.SOUTH); 
		}
		
	}
	
	private class VersionPane extends JPanel{
		
		public VersionPane(){
			super(new GridLayout(4,1)); 
			
			String status; 
			if(stableStatus.equals("0")){
				status = resource.getString("stableStatusAlpha"); 
			}else{
				if(stableStatus.equals("1")){
					status = resource.getString("stableStatusBeta"); 
				}else{
					if(stableStatus.equals("2")){
						status = resource.getString("stableStatusStable"); 
					}else{
						status = ""; 
					}
				}
			}
			
			JLabel labelVersion = new JLabel(("   " + resource.getString("Version") + ": " + version + " (" + status + ")")); 
			JLabel labelBuildID= new JLabel("   " + resource.getString("Buildid") + ": " + buildID); 
			
			add(labelVersion); 
			add(labelBuildID); 
			add(new JLabel()); 
			add(new JLabel()); 
		}
		
	}
	
}
