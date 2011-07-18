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
package unbbayes.controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import unbbayes.evaluation.controller.EvaluationController;
import unbbayes.gui.FileIcon;
import unbbayes.gui.NetworkWindow;
import unbbayes.gui.SimpleFileFilter;
import unbbayes.gui.util.TextAreaDialog;
import unbbayes.io.BaseIO;
import unbbayes.io.extension.jpf.PluginAwareFileExtensionIODelegator;
import unbbayes.prs.Edge;
import unbbayes.prs.Graph;
import unbbayes.prs.Network;
import unbbayes.prs.Node;
import unbbayes.prs.bn.ProbabilisticNetwork;
import unbbayes.prs.bn.ProbabilisticNode;
import unbbayes.prs.bn.SingleEntityNetwork;
import unbbayes.prs.hybridbn.ContinuousNode;
import unbbayes.util.Debug;
import unbbayes.util.extension.bn.inference.IInferenceAlgorithm;
import unbbayes.util.graphics.DropShadowDemo;
import unbbayes.util.graphics.Transparency;

/**
 * This class is responsible for delegating instructions that is going to be 
 * executed in a SingleEntityNetwork or MultiEntityBayesianNetwork. Insert node 
 * and propagate evidences, for instance.
 *
 * @author     Rommel Novaes Carvalho
 * @author     Michael S. Onishi
 * @created    27 de Junho de 2001
 * @version    1.5 2006/09/12
 */

public class NetworkController implements KeyListener, INetworkMediator {

    private NetworkWindow screen;
    private SingleEntityNetwork singleEntityNetwork;
    
    private Node selectedNode;
    
    private SENController senController;
    
    private BaseIO baseIO;
    
    // TODO ROMMEL - CHANGE THIS!! NEW MODELING!!
    /* (non-Javadoc)
	 * @see unbbayes.controller.INetworkMediator#getInferenceAlgorithm()
	 */
    public IInferenceAlgorithm getInferenceAlgorithm() {
    	if (senController != null) {
    		return senController.getInferenceAlgorithm();
    	}
    	return null;
	}

	/* (non-Javadoc)
	 * @see unbbayes.controller.INetworkMediator#setInferenceAlgorithm(unbbayes.util.extension.bn.inference.IInferenceAlgorithm)
	 */
	public void setInferenceAlgorithm(IInferenceAlgorithm inferenceAlgorithm) {
		if (senController != null) {
    		senController.setInferenceAlgorithm(inferenceAlgorithm);
    	}
	}
    
	/** Load resource file from this package */
    private static ResourceBundle resource = unbbayes.util.ResourceController.newInstance().getBundle(
    		unbbayes.controller.resources.ControllerResources.class.getName());
    
    
    
    /**
     * This is the default constructor, initializing nothing.
     * This is made protected in order to make it easier to extend.
     */
    protected NetworkController() {}
    
    

    /***************** BEGIN CONTROLLING SINGLE ENTTITY NETWORK *********************/
    
    /**
     *  Constructs a controller for SingleEntityNetwork.
     *
     */
    public NetworkController(SingleEntityNetwork singleEntityNetwork, NetworkWindow screen) {
        this.singleEntityNetwork = singleEntityNetwork;
        this.screen = screen;
        this.senController = new SENController(singleEntityNetwork, screen);
        this.setBaseIO(PluginAwareFileExtensionIODelegator.newInstance());
    }
    
    /* (non-Javadoc)
	 * @see unbbayes.controller.INetworkMediator#getSENController()
	 */
    public SENController getSENController(){
    	return this.senController; 
    }
    
    /* (non-Javadoc)
	 * @see unbbayes.controller.INetworkMediator#getSingleEntityNetwork()
	 */
    public SingleEntityNetwork getSingleEntityNetwork() {
    	//TODO VERIFICAR SE POSSO RETIRAR ESSE M�TODO!!
        return this.singleEntityNetwork;
    }
    
    /* (non-Javadoc)
	 * @see unbbayes.controller.INetworkMediator#getNetwork()
	 */
    public Network getNetwork() {
    	return singleEntityNetwork;
    }
    
    /* (non-Javadoc)
	 * @see unbbayes.controller.INetworkMediator#getGraph()
	 */
    public Graph getGraph(){
    	return singleEntityNetwork;
    }

    /* (non-Javadoc)
	 * @see unbbayes.controller.INetworkMediator#initialize()
	 */
    public void initialize() {
    	if (senController != null) senController.initialize();
    }
    
    /* (non-Javadoc)
	 * @see unbbayes.controller.INetworkMediator#removeEvidence(unbbayes.prs.Node)
	 */
    public void removeEvidence(Node node) {
    	if (senController != null) senController.removeEvidence(node);
    }
    
    /* (non-Javadoc)
	 * @see unbbayes.controller.INetworkMediator#createTable(unbbayes.prs.Node)
	 */
	public void createTable(Node node) {
		if (node == null) {
			return;
		}
		if (node.getType() == Node.CONTINUOUS_NODE_TYPE) {
			createContinuousDistribution((ContinuousNode)node);
		} else {
			createDiscreteTable(node);
		}
	}
    

	/* (non-Javadoc)
	 * @see unbbayes.controller.INetworkMediator#createContinuousDistribution(unbbayes.prs.hybridbn.ContinuousNode)
	 */
	/**
	 * @deprecated Continuous node is no longer supported in UnBBayes core. It has 
	 * now been replaced by the CPS plugin available at http://sourceforge.net/projects/prognos/.
	 */
	public void createContinuousDistribution(ContinuousNode node) {
		if (senController != null) senController.createContinuousDistribution(node);
	}
	
	/* (non-Javadoc)
	 * @see unbbayes.controller.INetworkMediator#createDiscreteTable(unbbayes.prs.Node)
	 */
	public void createDiscreteTable(Node node) {
		if (senController != null) senController.createDiscreteTable(node);
	}

    /* (non-Javadoc)
	 * @see unbbayes.controller.INetworkMediator#makeTable(unbbayes.prs.Node)
	 */
    public JTable makeTable(final Node node) {
    	if (senController != null) return senController.makeTable(node);
    	return null;
    }

    /* (non-Javadoc)
	 * @see unbbayes.controller.INetworkMediator#propagate()
	 */
    public void propagate() {
    	if (senController != null) senController.propagate();
    }

    /* (non-Javadoc)
	 * @see unbbayes.controller.INetworkMediator#compileNetwork()
	 */
    public boolean compileNetwork() {
    	if (senController != null) return senController.compileNetwork();
    	return false;
    }
    
    /* (non-Javadoc)
	 * @see unbbayes.controller.INetworkMediator#evaluateNetwork()
	 */
    public void evaluateNetwork() {
    	if (singleEntityNetwork != null && singleEntityNetwork instanceof ProbabilisticNetwork) {
    		EvaluationController evaluationController = new EvaluationController((ProbabilisticNetwork)singleEntityNetwork);
    		screen.changeToPNEvaluationPane(evaluationController.getView());
    	} else {
    		JOptionPane.showMessageDialog(screen, "Evaluation can only be done in probabilistic networks.", "Evaluation Error", JOptionPane.ERROR_MESSAGE);
    	}
    }

    /* (non-Javadoc)
	 * @see unbbayes.controller.INetworkMediator#insertContinuousNode(double, double)
	 */
    public Node insertContinuousNode(double x, double y) {
    	if (senController != null) return senController.insertContinuousNode(x,y);
    	return null;
    }

    /* (non-Javadoc)
	 * @see unbbayes.controller.INetworkMediator#insertProbabilisticNode(double, double)
	 */
    public Node insertProbabilisticNode(double x, double y) {
    	if (senController != null) return senController.insertProbabilisticNode(x,y);
    	return null;
    }


    /* (non-Javadoc)
	 * @see unbbayes.controller.INetworkMediator#insertDecisionNode(double, double)
	 */
    public Node insertDecisionNode(double x, double y) {
    	if (senController != null) return senController.insertDecisionNode(x, y);
    	return null;
    }

    /* (non-Javadoc)
	 * @see unbbayes.controller.INetworkMediator#insertUtilityNode(double, double)
	 */
    public Node insertUtilityNode(double x, double y) {
    	if (senController != null) return senController.insertUtilityNode(x, y);
    	return null;
    }

    /* (non-Javadoc)
	 * @see unbbayes.controller.INetworkMediator#showExplanationProperties(unbbayes.prs.bn.ProbabilisticNode)
	 */
    public void showExplanationProperties(ProbabilisticNode node) {
    	if (senController != null) senController.showExplanationProperties(node);
    }
    
    /***************** END CONTROLLING SINGLE ENTTITY NETWORK *********************/

    
    /***************** BEGIN CONTROLLING BOTH *********************/
    
    /* (non-Javadoc)
	 * @see unbbayes.controller.INetworkMediator#insertEdge(unbbayes.prs.Edge)
	 */
    public boolean insertEdge(Edge edge) throws Exception{
    	if (senController != null) return senController.insertEdge(edge); 
    	return false;
    }
    
    /* (non-Javadoc)
	 * @see unbbayes.controller.INetworkMediator#insertState(unbbayes.prs.Node)
	 */
    public void insertState(Node node) {
    	if (senController != null) senController.insertState(node);
    }

    /* (non-Javadoc)
	 * @see unbbayes.controller.INetworkMediator#removeState(unbbayes.prs.Node)
	 */
    public void removeState(Node node) {
    	if (senController != null) senController.removeState(node);
    }

    /* (non-Javadoc)
	 * @see unbbayes.controller.INetworkMediator#deleteSelected(java.lang.Object)
	 */
    //by young
    public void deleteSelected(Object selected) {
    	if (senController != null) senController.deleteSelected(selected);
    }
    
    /***************** END CONTROLLING BOTH *********************/
    
    
    
    /****************** BEGIN KEY LISTENER METHODS *********************/
    
    /* (non-Javadoc)
	 * @see unbbayes.controller.INetworkMediator#keyTyped(java.awt.event.KeyEvent)
	 */
    public void keyTyped(KeyEvent e) { }


    /* (non-Javadoc)
	 * @see unbbayes.controller.INetworkMediator#keyPressed(java.awt.event.KeyEvent)
	 */
    public void keyPressed(KeyEvent e) {

    	//by young
      /*  if (e.getKeyCode() == KeyEvent.VK_DELETE) {
            Object selecionado = screen.getGraphPane().getSelected();
            deleteSelected(selecionado);
            
            for (int i = 0; i < screen.getGraphPane().getSelectedGroup().size(); i++) {
                selecionado = screen.getGraphPane().getSelectedGroup().get(i);
                deleteSelected(selecionado);
            }
        }
        screen.getGraphPane().update();*/
    }
    
    /* (non-Javadoc)
	 * @see unbbayes.controller.INetworkMediator#keyReleased(java.awt.event.KeyEvent)
	 */
    public void keyReleased(KeyEvent e) {
    }
    
    /****************** END KEY LISTENER METHODS *********************/
    
    /****************** BEGIN GENERIC METHODS *********************/
    
    /* (non-Javadoc)
	 * @see unbbayes.controller.INetworkMediator#getScreen()
	 */
    public NetworkWindow getScreen() {
        return this.screen;
    }

    /* (non-Javadoc)
	 * @see unbbayes.controller.INetworkMediator#saveNetImage()
	 */
    public void saveNetImage() {
        String images[] = { "PNG", "JPG", "GIF", "BMP" };
        JFileChooser chooser = new JFileChooser(FileHistoryController.getInstance().getCurrentDirectory());
        chooser.setMultiSelectionEnabled(false);

        chooser.setFileView(new FileIcon(screen));
        chooser.addChoosableFileFilter(new SimpleFileFilter( images, resource.getString("imageFileFilter")));

        int opcao = chooser.showSaveDialog(screen);
        if (opcao == JFileChooser.APPROVE_OPTION) {
        	Rectangle r = calculateNetRectangle();
        	Component comp = screen.getGraphPane().getGraphViewport();
        	File file = new File(chooser.getSelectedFile().getPath());
        	saveComponentAsImage(comp, r.width, r.height, file);
        	FileHistoryController.getInstance().setCurrentDirectory(chooser.getCurrentDirectory());
        	
        	JOptionPane.showMessageDialog(screen, resource.getString("saveSucess"));
        }
    }
    
    protected void saveComponentAsImage(Component comp, int width, int height, File file) {
    	BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = bufferedImage.createGraphics();
        comp.paint(g2d);
        g2d.dispose();
        
        int option = JOptionPane.showConfirmDialog(screen, resource.getString("useTransparencyMessage"), resource.getString("useTransparencyTitle"), JOptionPane.YES_NO_OPTION);
        
        if (option == JOptionPane.YES_OPTION) {
	        // Make the image transparent
//	        Image image = Transparency.transformColorToTransparency(bufferedImage, new Color(250, 250, 255), Color.WHITE);
        	Image image = Transparency.transformColorToTransparency(bufferedImage, Color.WHITE, Color.WHITE);
	        bufferedImage = Transparency.imageToBufferedImage(image, bufferedImage.getWidth(), bufferedImage.getHeight());
        }
        
        option = JOptionPane.showConfirmDialog(screen, resource.getString("useShadowMessage"), resource.getString("useShadowTitle"), JOptionPane.YES_NO_OPTION);
        
        if (option == JOptionPane.YES_OPTION) {
        	boolean askAgain = false;
        	String answer;
        	int size = 2;
        	do {
	        	try {
	        		answer = JOptionPane.showInputDialog(screen, resource.getString("shadowSizeInputMessage"));
	        		size = Integer.parseInt(answer);
	        		askAgain = false;
	        	} catch (NumberFormatException e) {
	        		JOptionPane.showMessageDialog(screen, resource.getString("shadowSizeInputErrorMessage"), resource.getString("shadowSizeInputErrorTitle"), JOptionPane.ERROR_MESSAGE);
	        		askAgain = true;
	        	}
        	} while (askAgain);
            
            float opacity = .5f;
            do {
	            try {
	            	answer = JOptionPane.showInputDialog(screen, resource.getString("shadowOpacityInputMessage"));
	            	opacity = Float.parseFloat(answer);
	            	askAgain = false;
	            	if (opacity < 0 || opacity > 1) {
	            		JOptionPane.showMessageDialog(screen, resource.getString("shadowOpacityInputErrorMessage"), resource.getString("shadowOpacityInputErrorTitle"), JOptionPane.ERROR_MESSAGE);
	            		askAgain = true;
	            	}
	            } catch (NumberFormatException e) {
	        		JOptionPane.showMessageDialog(screen, resource.getString("shadowOpacityInputErrorMessage"), resource.getString("shadowOpacityInputErrorTitle"), JOptionPane.ERROR_MESSAGE);
	        		askAgain = true;
	        	}
            } while (askAgain);
            bufferedImage = DropShadowDemo.addDropShadow(bufferedImage, size, opacity);
        }
        
        boolean wrongName = false;

        String fileName = file.getName();
        if (fileName.length() > 4) {
        	String fileExt = fileName.substring(fileName.length() - 3);
        	try {
    	        if (fileExt.equalsIgnoreCase("png")) {
    				ImageIO.write(bufferedImage, "png", file);
    	        } else if (fileExt.equalsIgnoreCase("jpg")) {
    	        	ImageIO.write(bufferedImage, "jpg", file);
    	        } else if (fileExt.equalsIgnoreCase("gif")) {
    	        	ImageIO.write(bufferedImage, "gif", file);
    	        } else if (fileExt.equalsIgnoreCase("bmp")) {
    	        	ImageIO.write(bufferedImage, "bmp", file);
    	        } else {
    	        	wrongName = true;
    	        }
    		} catch (IOException e1) {
    			// TODO SHOW MESSAGE TO USER
    			e1.printStackTrace();
    		}
        }  else {
        	wrongName = true;
        }
        
        if (wrongName) {
        	// TODO SHOW MESSAGE TO USER
        }
        
    }

    /* (non-Javadoc)
	 * @see unbbayes.controller.INetworkMediator#saveTableImage()
	 */
    public void saveTableImage() {
    	String images[] = { "PNG", "JPG", "GIF", "BMP" };
        JFileChooser chooser = new JFileChooser(FileHistoryController.getInstance().getCurrentDirectory());
        chooser.setMultiSelectionEnabled(false);

        chooser.setFileView(new FileIcon(screen));
        chooser.addChoosableFileFilter(new SimpleFileFilter( images, resource.getString("imageFileFilter")));

        int opcao = chooser.showSaveDialog(screen);
        if (opcao == JFileChooser.APPROVE_OPTION) {
        	// TODO MAKE IT SHOW THE HEADER ALSO
        	Component comp = screen.getTable();
        	File file = new File(chooser.getSelectedFile().getPath());
        	saveComponentAsImage(comp, comp.getWidth(), comp.getHeight(), file);
        	FileHistoryController.getInstance().setCurrentDirectory(chooser.getCurrentDirectory());
        	
        	JOptionPane.showMessageDialog(screen, resource.getString("saveSucess"));
        }
    }
    
    /**
     * This method is called inside {@link #showLog()} to retrieve the 
     * content of LOG. Extend this method in order to customize the log message
     * (e.g. customize where the log content is stored, and how to retrieve it)
     * @return a non null string. If {@link #singleEntityNetwork} is null, it returns
     * an empty string.
     */
    protected String getLogContent() {
    	if (singleEntityNetwork != null) {
    		return singleEntityNetwork.getLog();
    	}
    	return "";
    }
    
    /* (non-Javadoc)
	 * @see unbbayes.controller.INetworkMediator#getLog()
	 */
    public String getLog() {
    	return this.getLogContent();
    }

    /* (non-Javadoc)
	 * @see unbbayes.controller.INetworkMediator#showLog()
	 */
    public JDialog showLog() {
    	TextAreaDialog dialog = new TextAreaDialog(this.getScreen().getUnbbayesFrame(), false);
        dialog.setTextContent(this.getLog());
        return dialog; 
    }

    /* (non-Javadoc)
	 * @see unbbayes.controller.INetworkMediator#openWarningDialog()
	 */
    public void openWarningDialog(){
    	Debug.println("Not implemented yet");
    }
    
    /* (non-Javadoc)
	 * @see unbbayes.controller.INetworkMediator#closeWarningDialog()
	 */
    public void closeWarningDialog(){
    	Debug.println("Not implemented yet");
    }
    
    
    /* (non-Javadoc)
	 * @see unbbayes.controller.INetworkMediator#previewPrintLog(javax.swing.JTextArea, javax.swing.JDialog)
	 */
    public void previewPrintLog(final JTextArea texto, final JDialog dialog) {
        screen.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        Thread t = new Thread(new Runnable() {
          public void run() {
            PrintText it = new PrintText(texto,
                new PageFormat());
            PrintPreviewer pp = new PrintPreviewer(
                it, 0);

            JDialog dlg = new JDialog(dialog,
                resource.getString("previewLogToolTip"));
            dlg.getContentPane().add(pp);
            dlg.setSize(640, 480);
            dlg.setVisible(true);
          }
        });

        t.start();
        screen.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }


    /* (non-Javadoc)
	 * @see unbbayes.controller.INetworkMediator#previewPrintTable()
	 */
    public void previewPrintTable() {
        screen.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        Thread t = new Thread(new Runnable() {
          public void run() {
            List<JTable> tabelas = new ArrayList<JTable>();
            List<Object> donos = new ArrayList<Object>();
            List<Node> temp = screen.getGraphPane().getSelectedGroup();
            if (temp.size() == 0) {
               tabelas.add(screen.getTable());
               donos.add(screen.getTableOwner());
            }  else {
                for (int i = 0; i< temp.size(); i++) {
                    donos.add(((Node)temp.get(i)).toString());
                    tabelas.add(makeTable((Node)temp.get(i)));
                }
            }

            PrintTable tp = new PrintTable(tabelas, donos, new PageFormat());
            PrintPreviewer pp = new PrintPreviewer(
                tp, 0);
            JDialog dlg = new JDialog();
            dlg.getContentPane().add(pp);
            dlg.setSize(400, 300);
            dlg.setVisible(true);
          }
        });
        t.start();
        screen.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }


    /* (non-Javadoc)
	 * @see unbbayes.controller.INetworkMediator#previewPrintNet(javax.swing.JComponent, java.awt.Rectangle)
	 */
    public void previewPrintNet(final JComponent rede, final Rectangle retangulo) {
        screen.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        Thread t = new Thread(new Runnable() {
          public void run() {
            String rotulo = JOptionPane.showInputDialog(screen, resource.getString("askTitle"), resource.getString("informationText"), JOptionPane.INFORMATION_MESSAGE);
            if (rotulo == null) {
                rotulo = "";
            }
            PrintNet it = new PrintNet(rotulo, rede, retangulo, new PageFormat());
            PrintPreviewer pp = new PrintPreviewer(
                it, 0);

            JDialog dlg = new JDialog();
            dlg.getContentPane().add(pp);
            dlg.setSize(640, 480);
            dlg.setVisible(true);
          }
        });

        t.start();
        screen.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }


    /* (non-Javadoc)
	 * @see unbbayes.controller.INetworkMediator#printNet(javax.swing.JComponent, java.awt.Rectangle)
	 */
    public void printNet(final JComponent network, final Rectangle rectangle) {
        screen.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        Thread t = new Thread(new Runnable() {
          public void run() {
            String rotulo = JOptionPane.showInputDialog(screen, resource.getString("askTitle"), resource.getString("informationText"), JOptionPane.INFORMATION_MESSAGE);
            if (rotulo == null) {
                rotulo = "";
            }
            PrintNet it = new PrintNet(rotulo, network, rectangle, new PageFormat());
            PrintMonitor pm = new PrintMonitor(it);
            try {
              pm.performPrint(true);
            } catch (PrinterException pe) {
              JOptionPane.showMessageDialog(
                  screen,
                  resource.getString("printException") +
                  pe.getMessage());
            }
          }
        });
        t.start();
        screen.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }


    /* (non-Javadoc)
	 * @see unbbayes.controller.INetworkMediator#printTable()
	 */
    public void printTable() {
        screen.setCursor(new Cursor(Cursor.WAIT_CURSOR));
        Thread t = new Thread(new Runnable() {
          public void run() {
            List<JTable> tabelas = new ArrayList<JTable>();
            List<Object> donos = new ArrayList<Object>();
            List<Node> temp = screen.getGraphPane().getSelectedGroup();
            if (temp.size() == 0) {
               tabelas.add(screen.getTable());
               donos.add(screen.getTableOwner());
            }  else {
                for (int i = 0; i< temp.size(); i++) {
                    donos.add(((Node)temp.get(i)).toString());
                    tabelas.add(makeTable((Node)temp.get(i)));
                }
            }
            PrintTable impressora = new PrintTable(tabelas, donos, new PageFormat());
            PrintMonitor pm = new PrintMonitor(impressora);
            try {
              pm.performPrint(true);
            } catch (PrinterException pe) {
              JOptionPane.showMessageDialog(
                  screen,
                  resource.getString("printException") +
                  pe.getMessage());
            }
          }
        });
        t.start();
        screen.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }


    /**
     * Print the log contained in the given <code>JTextArea</code>.
     *
     * @param textArea The text area containing the log.
     * @deprecated this method was moved to {@link unbbayes.gui.util.TextAreaDialog}
     */
    protected void printLog(final JTextArea textArea) {
        Thread t = new Thread(new Runnable() {
          public void run() {
            PrintText it = new PrintText(textArea,
                new PageFormat());
            PrintMonitor pm = new PrintMonitor(it);
            try {
              pm.performPrint(true);
            } catch (PrinterException pe) {
              JOptionPane.showMessageDialog(
                  screen,
                  resource.getString("printException") +
                  pe.getMessage());
            }
          }
        });
        t.start();
    }

    /* (non-Javadoc)
	 * @see unbbayes.controller.INetworkMediator#calculateNetRectangle()
	 */
    public Rectangle calculateNetRectangle() {
    	
    	//by young
    	/*
    	ArrayList<Node> nos;
        List vetorAux = screen.getGraphPane().getSelectedGroup();

        if (vetorAux.size() == 0) {
            nos = new ArrayList<Node>();
            for (int i = 0; i < singleEntityNetwork.getNodeCount(); i++) {
            	nos.add(i, singleEntityNetwork.getNodeAt(i));
            }
        } else {
            nos = new ArrayList<Node>();
            for (int i = 0; i < vetorAux.size(); i++) {
                if (vetorAux.get(i) instanceof Node) {
                    nos.add((Node)vetorAux.get(i));
                }
            }
        }
        int maiorX = 0;
        int menorX = Integer.MAX_VALUE;
        int maiorY = 0;
        int menorY = Integer.MAX_VALUE;
        Node noAux;
        Point2D pontoAux;
        int xAux;
        int yAux;
        for (int i = 0; i < nos.size(); i++) {
            noAux = (Node)nos.get(i);
            pontoAux = noAux.getPosition();
            xAux = (int)pontoAux.getX();
            yAux = (int)pontoAux.getY();
            if (xAux > maiorX) {
                maiorX = xAux;
            }
            if (xAux < menorX) {
                menorX = xAux;
            }
            if (yAux > maiorY) {
                maiorY = yAux;
            }
            if (yAux < menorY) {
                menorY = yAux;
            }
        }
        //by young
         
        double nodeWidth = noAux.getWidth();
        maiorX += nodeWidth;
        maiorY += nodeWidth;
        menorX -= nodeWidth;
        menorY -= nodeWidth;
        
        return new Rectangle(menorX, menorY, maiorX - menorX, maiorY - menorY);*/
    	
    	return new Rectangle(0, 0, (int)screen.getGraphPane().getBiggestPoint().x, (int)screen.getGraphPane().getBiggestPoint().y);
        
        
    }
    
    public Node getSelectedNode(){
    	return selectedNode;
    }
	
    public void selectNode(Node node){
    	this.selectedNode = node;
    }
    
    public void unselectAll(){
    	this.selectedNode = null;
    }

	/* (non-Javadoc)
	 * @see unbbayes.controller.INetworkMediator#getBaseIO()
	 */
	public BaseIO getBaseIO() {
		return baseIO;
	}

	/* (non-Javadoc)
	 * @see unbbayes.controller.INetworkMediator#setBaseIO(unbbayes.io.BaseIO)
	 */
	public void setBaseIO(BaseIO baseIO) {
		this.baseIO = baseIO;
	}

	/* (non-Javadoc)
	 * @see unbbayes.controller.INetworkMediator#setScreen(unbbayes.gui.NetworkWindow)
	 */
	public void setScreen(NetworkWindow screen) {
		this.screen = screen;
	}
    
    /****************** END GENERIC METHODS *********************/
}