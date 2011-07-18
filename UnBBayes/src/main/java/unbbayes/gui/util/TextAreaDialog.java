/**
 * 
 */
package unbbayes.gui.util;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import unbbayes.controller.IconController;
import unbbayes.controller.PrintMonitor;
import unbbayes.controller.PrintPreviewer;
import unbbayes.controller.PrintText;

/**
 * This is a default dialog containing a text field
 * to show string messages.
 * This can be useful to present a simple text message to a user.
 * @author Shou Matsumoto
 *
 */
public class TextAreaDialog extends JDialog {

	private JTextArea textArea;
	
	/** Load resource file from this package */
	private ResourceBundle resource = unbbayes.util.ResourceController.newInstance().getBundle(
			unbbayes.gui.resources.GuiResources.class.getName());

	private JScrollPane jspText;

	private IconController iconController;

	private JPanel topPanel;

	private JButton printButton;

	private JButton previewButton;

	private JPanel buttonPanel;

	private JPanel bottomPanel;

	/**
	 * Creates a non-modal dialog without a title and without a specified Frame owner. A shared, hidden frame will be set as the owner of the dialog. 
	 * This constructor sets the component's locale property to the value returned by JComponent.getDefaultLocale. 
	 * @throws HeadlessException :  if GraphicsEnvironment.isHeadless() returns true
	 */
	public TextAreaDialog() throws HeadlessException {
		super();
	}
	
	/**
	 * Creates a non-modal dialog without a title with the specified Frame as its owner. If owner is null, a shared, hidden frame will be set as the owner of the dialog. 
	 * This constructor sets the component's locale property to the value returned by JComponent.getDefaultLocale
	 * @param owner
	 * @throws HeadlessException : if GraphicsEnvironment.isHeadless() returns true
	 */
	public TextAreaDialog(Frame owner) throws HeadlessException {
		super(owner);
		this.initLog();
	}

	/**
	 * Creates a non-modal dialog without a title with the specified Dialog as its owner. 
	 * This constructor sets the component's locale property to the value returned by JComponent.getDefaultLocale. 
	 * @param owner
	 * @throws HeadlessException : if GraphicsEnvironment.isHeadless() returns true
	 */
	public TextAreaDialog(Dialog owner) throws HeadlessException {
		super(owner);
		this.initLog();
	}

	/**
	 * Creates a modal or non-modal dialog without a title and with the specified owner Frame. If owner is null, a shared, hidden frame will be set as the owner of the dialog. 
	 * This constructor sets the component's locale property to the value returned by JComponent.getDefaultLocale. 
	 * @param owner
	 * @param modal : true for a modal dialog, false for one that allows others windows to be active at the same time
	 * @throws HeadlessException : if GraphicsEnvironment.isHeadless() returns true
	 */
	public TextAreaDialog(Frame owner, boolean modal) throws HeadlessException {
		super(owner, modal);
		this.initLog();
	}

	/**
	 * Creates a non-modal dialog with the specified title and with the specified owner frame. If owner is null, a shared, hidden frame will be set as the owner of the dialog. 
	 * This constructor sets the component's locale property to the value returned by JComponent.getDefaultLocale. 
	 * @param owner
	 * @param title
	 * @throws HeadlessException : if GraphicsEnvironment.isHeadless() returns true
	 */
	public TextAreaDialog(Frame owner, String title) throws HeadlessException {
		super(owner, title);
		this.initLog();
		this.setTitle(title);
	}

	/**
	 * Creates a modal or non-modal dialog without a title and with the specified owner dialog. 
	 * This constructor sets the component's locale property to the value returned by JComponent.getDefaultLocale. 
	 * @param owner
	 * @param modal : true for a modal dialog, false for one that allows others windows to be active at the same time
	 * @throws HeadlessException : if GraphicsEnvironment.isHeadless() returns true
	 */
	public TextAreaDialog(Dialog owner, boolean modal) throws HeadlessException {
		super(owner, modal);
		this.initLog();
	}

	/**
	 * Creates a non-modal dialog with the specified title and with the specified owner dialog. 
	 * This constructor sets the component's locale property to the value returned by JComponent.getDefaultLocale. 
	 * @param owner
	 * @param title
	 * @throws HeadlessException : if GraphicsEnvironment.isHeadless() returns true
	 */
	public TextAreaDialog(Dialog owner, String title) throws HeadlessException {
		super(owner, title);
		this.initLog();
		this.setTitle(title);
	}

	/**
	 * Creates a modal or non-modal dialog with the specified title and the specified owner Frame. If owner is null, a shared, hidden frame will be set as the owner of this dialog. All constructors defer to this one. 
	 * NOTE: Any popup components (JComboBox, JPopupMenu, JMenuBar) created within a modal dialog will be forced to be lightweight. 
	 * This constructor sets the component's locale property to the value returned by JComponent.getDefaultLocale. 
	 * @param owner
	 * @param title
	 * @param modal : true for a modal dialog, false for one that allows others windows to be active at the same time
	 * @throws HeadlessException : if GraphicsEnvironment.isHeadless() returns true
	 */
	public TextAreaDialog(Frame owner, String title, boolean modal)
			throws HeadlessException {
		super(owner, title, modal);
		this.initLog();
		this.setTitle(title);
	}

	/**
	 * Creates a modal or non-modal dialog with the specified title and the specified owner frame. 
	 * This constructor sets the component's locale property to the value returned by JComponent.getDefaultLocale. 
	 * @param owner
	 * @param title
	 * @param modal : true for a modal dialog, false for one that allows others windows to be active at the same time
	 * @throws HeadlessException : if GraphicsEnvironment.isHeadless() returns true
	 */
	public TextAreaDialog(Dialog owner, String title, boolean modal)
			throws HeadlessException {
		super(owner, title, modal);
		this.initLog();
		this.setTitle(title);
	}

	/**
	 * Creates a modal or non-modal dialog with the specified title, owner Frame, and GraphicsConfiguration. 
	 * NOTE: Any popup components (JComboBox, JPopupMenu, JMenuBar) created within a modal dialog will be forced to be lightweight. 
	 * This constructor sets the component's locale property to the value returned by JComponent.getDefaultLocale. 
	 * @param owner
	 * @param title
	 * @param modal : true for a modal dialog, false for one that allows others windows to be active at the same time
	 * @param gc
	 * @throws HeadlessException : if GraphicsEnvironment.isHeadless() returns true
	 */
	public TextAreaDialog(Frame owner, String title, boolean modal,
			GraphicsConfiguration gc) {
		super(owner, title, modal, gc);
		this.initLog();
		this.setTitle(title);
	}

	/**
	 * Creates a modal or non-modal dialog with the specified title, owner Dialog, and GraphicsConfiguration. 
	 * NOTE: Any popup components (JComboBox, JPopupMenu, JMenuBar) created within a modal dialog will be forced to be lightweight. 
	 * This constructor sets the component's locale property to the value returned by JComponent.getDefaultLocale. 
	 * @param owner
	 * @param title
	 * @param modal : true for a modal dialog, false for one that allows others windows to be active at the same time
	 * @param gc
	 * @throws HeadlessException : if GraphicsEnvironment.isHeadless() returns true
	 */
	public TextAreaDialog(Dialog owner, String title, boolean modal,
			GraphicsConfiguration gc) throws HeadlessException {
		super(owner, title, modal, gc);
		this.initLog();
		this.setTitle(title);
	}
	
	/**
     * Initializes a default log dialog setting its attributes.
     * This is called by every constructors
     */
    protected void initLog() {
    	this.getOwner().setCursor(new Cursor(Cursor.WAIT_CURSOR));

    	this.getTextArea().setEditable(false);
        
    	this.getTextArea().moveCaretPosition(0);
    	this.getTextArea().setSelectionEnd(0);

    	this.getTextArea().setSize(this.getTextArea().getPreferredSize());
    	this.getTextArea().append("\n");

        jspText = new JScrollPane(this.getTextArea());
        jspText.setPreferredSize(new Dimension(450, 400));

        iconController = IconController.getInstance();
        topPanel = new JPanel(new BorderLayout());
        printButton = new JButton(iconController.getPrintIcon());
        printButton.setToolTipText(resource.getString("printLogToolTip"));
        previewButton = new JButton(iconController.getVisualizeIcon());
        previewButton.setToolTipText(resource.getString("previewLogToolTip"));
        printButton.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    printText(getTextArea());
                }
            });
        previewButton.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    previewPrintText(getTextArea(), TextAreaDialog.this);
                }
            });

        topPanel.add(jspText, BorderLayout.CENTER);

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(printButton);
        buttonPanel.add(previewButton);
        topPanel.add(buttonPanel, BorderLayout.NORTH);

        bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton botaoOK = new JButton(resource.getString("closeButtonLabel"));
        botaoOK.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    TextAreaDialog.this.dispose();
                }
            });


        bottomPanel.add(botaoOK);
        topPanel.add(bottomPanel, BorderLayout.SOUTH);

        this.getContentPane().add(topPanel);
        this.setTitle(resource.getString("logDialogTitle")); 
        
        this.getOwner().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        
    }
    
    
    /**
     * Preview the log printing.
     * This is called inside the action listener of {@link #getPreviewButton()}
     */
    protected void previewPrintText(final JTextArea texto, final JDialog dialog) {
        this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
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
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
    
    /**
     * Print the log contained in the given <code>JTextArea</code>.
     * This is called inside the action listener of {@link #getPrintButton()}
     * @param textArea The text area containing the log.
     */
    public void printText(final JTextArea textArea) {
        Thread t = new Thread(new Runnable() {
          public void run() {
            PrintText it = new PrintText(textArea,
                new PageFormat());
            PrintMonitor pm = new PrintMonitor(it);
            try {
              pm.performPrint(true);
            } catch (PrinterException pe) {
              JOptionPane.showMessageDialog(
            	  TextAreaDialog.this.getOwner(),
                  resource.getString("printException") +
                  pe.getMessage());
            }
          }
        });
        t.start();
    }

	/**
	 * @return the resource
	 */
	public ResourceBundle getResource() {
		return resource;
	}

	/**
	 * @param resource the resource to set
	 */
	public void setResource(ResourceBundle resource) {
		this.resource = resource;
	}

	/**
	 * This is the main text area
	 * @return the textArea : non-null value
	 */
	public JTextArea getTextArea() {
		if (this.textArea == null) {
			this.textArea = new JTextArea();
		}
		return this.textArea;
	}

	/**
	 * This is the main text area
	 * @param textArea the textArea to set
	 */
	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}

	/**
	 * This is equivalent to {@link JTextArea#getText()} from {@link #getTextArea()}
	 * @return the logContent
	 * @see #getTextArea()
	 */
	public String getTextContent() {
		return this.getTextArea().getText();
	}

	/**
	 * This is equivalent to {@link JTextArea#setText(String)} from {@link #getTextArea()}
	 * @param textContent the logContent to set
	 * @see #getTextArea()
	 */
	public void setTextContent(String textContent) {
		this.getTextArea().setText(textContent);
	}

	/**
	 * @return the jspText
	 */
	public JScrollPane getJspText() {
		return jspText;
	}

	/**
	 * @param jspText the jspText to set
	 */
	public void setJspText(JScrollPane jspText) {
		this.jspText = jspText;
	}

	/**
	 * @return the iconController
	 */
	public IconController getIconController() {
		return iconController;
	}

	/**
	 * @param iconController the iconController to set
	 */
	public void setIconController(IconController iconController) {
		this.iconController = iconController;
	}

	/**
	 * @return the topPanel
	 */
	public JPanel getTopPanel() {
		return topPanel;
	}

	/**
	 * @param topPanel the topPanel to set
	 */
	public void setTopPanel(JPanel topPanel) {
		this.topPanel = topPanel;
	}

	/**
	 * @return the printButton
	 */
	public JButton getPrintButton() {
		return printButton;
	}

	/**
	 * @param printButton the printButton to set
	 */
	public void setPrintButton(JButton printButton) {
		this.printButton = printButton;
	}

	/**
	 * @return the previewButton
	 */
	public JButton getPreviewButton() {
		return previewButton;
	}

	/**
	 * @param previewButton the previewButton to set
	 */
	public void setPreviewButton(JButton previewButton) {
		this.previewButton = previewButton;
	}

	/**
	 * @return the buttonPanel
	 */
	public JPanel getButtonPanel() {
		return buttonPanel;
	}

	/**
	 * @param buttonPanel the buttonPanel to set
	 */
	public void setButtonPanel(JPanel buttonPanel) {
		this.buttonPanel = buttonPanel;
	}

	/**
	 * @return the bottomPanel
	 */
	public JPanel getBottomPanel() {
		return bottomPanel;
	}

	/**
	 * @param bottomPanel the bottomPanel to set
	 */
	public void setBottomPanel(JPanel bottomPanel) {
		this.bottomPanel = bottomPanel;
	}

}
