package unbbayes.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

/**
 * Splash Screen of UnBBayes 3 
 * 
 * @author Laecio Lima dos Santos (laecio@gmail.com)
 */
public class SplashScreen extends JWindow{

	private JPanel painelPrincipal = null;
	
	private JPanel picturePane = null; 
	private JPanel titlePane = null; 
	private JPanel subtitlePane = null; 
	private JPanel loadingPane = null;

	private Dimension dimension1 = new Dimension(400, 270); 
	
	private Dimension dimension2 = new Dimension(620, 415); 
	
	private boolean isHigthResolution = true; 
	
  	private static ResourceBundle resource =
		    unbbayes.util.ResourceController.newInstance().getBundle(unbbayes.gui.resources.GuiResources.class.getName());
	
	public SplashScreen(){
		super(); 
		initialize(); 
	}
	
	private void initialize(){
		
		if(Toolkit.getDefaultToolkit().getScreenSize().height > 600){
			isHigthResolution = true; 
		}else{
			isHigthResolution = false; 
		}
		
		if(isHigthResolution){
			setSize(dimension2);
			setPreferredSize(dimension2);
		}
		else{
			setSize(dimension1);
			setPreferredSize(dimension1);	
		}
		this.setContentPane(getJContentPane1());
		setLocationByPlatform(true); 
		setLocationRelativeTo(null); 
		setVisible(true); 
	}

	/**
	 * This method initializes jContentPane1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJContentPane1() {
		if (painelPrincipal == null) {
			painelPrincipal = new JPanel();
			painelPrincipal.setLayout(new BorderLayout());
			painelPrincipal.setBorder(BorderFactory.createLineBorder(Color.BLACK)); 
			
			painelPrincipal.add(getTitlePane(), BorderLayout.NORTH);
			painelPrincipal.add(getPicturePanel(), BorderLayout.CENTER); 
			painelPrincipal.add(getLoadingPane(), BorderLayout.SOUTH); 
			
		}
		return painelPrincipal;
	}
	
	public JPanel getPicturePanel(){
		if(picturePane == null ){
			
			ImageIcon splashImg = null; 
			
			if(isHigthResolution){
				splashImg = new ImageIcon(SplashScreen.class.getResource("/img/splashPane_big.jpg")); 
			}else{
				splashImg = new ImageIcon(SplashScreen.class.getResource("/img/splashPane_small.jpg")); 
			}
			
			JLabel labelImg = new JLabel(splashImg); 
			
			picturePane = new JPanel(new BorderLayout());
			
			picturePane.add(labelImg, BorderLayout.CENTER);
			picturePane.add(getSubtitlePane(), BorderLayout.LINE_END); 

			picturePane.setBackground(new Color(121, 187, 187)); 
		}
		
		return picturePane; 
	}
	
	
	
	public static void main(String... args){
		SplashScreen splash = new SplashScreen(); 
		splash.setVisible(true); 
		splash.pack(); 
	}

	public JPanel getTitlePane() {
		
		if(titlePane == null){
			titlePane = new JPanel(new BorderLayout()); 

			JLabel jLabel1 = null;
			jLabel1 = new JLabel();
			jLabel1.setText("UnBBayes 3");
			
			if(isHigthResolution){
				jLabel1.setFont(new Font("Verdana", Font.BOLD, 35));	
			}else{
				jLabel1.setFont(new Font("Verdana", Font.BOLD, 25));		
			}
			
			titlePane.add(jLabel1,BorderLayout.LINE_END);
			titlePane.setBackground(new Color(121, 187, 187)); 
		}
		
		return titlePane;
	}

	public JPanel getSubtitlePane() {
		
		if(subtitlePane == null){
			JLabel jLabel1 = null;
			jLabel1 = new JLabel();
			jLabel1.setText("MEBN");
			
            if(isHigthResolution){
				jLabel1.setFont(new Font("Verdana", Font.PLAIN, 20)); 	
			}else{
				jLabel1.setFont(new Font("Verdana", Font.PLAIN, 15)); 	
			}
			
			
			subtitlePane = new JPanel(new BorderLayout()); 
			subtitlePane.add(jLabel1, BorderLayout.NORTH); 
			subtitlePane.setBackground(new Color(121, 187, 187)); 
		}
		
		return subtitlePane;
	}

	public JPanel getLoadingPane() {
		
		if(loadingPane == null){
			JLabel jLabel1 = null;
			jLabel1 = new JLabel();
			jLabel1.setText("..." + resource.getString("loading") + " ");
			
            if(isHigthResolution){
    			jLabel1.setFont(new Font("Verdana", Font.ITALIC, 17)); 
			}else{
				jLabel1.setFont(new Font("Verdana", Font.ITALIC, 10)); 
			}

			loadingPane = new JPanel(new BorderLayout()); 
			loadingPane.add(jLabel1, BorderLayout.LINE_END); 
			loadingPane.setBackground(new Color(121, 187, 187)); 
		}
		
		return loadingPane;
	}
	
}
