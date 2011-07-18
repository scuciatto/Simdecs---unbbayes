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
package unbbayes.draw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import unbbayes.prs.Node;
import unbbayes.simdecs.SimdecsNodeWindow;
import unbbayes.util.Debug;

/*
 * CROSSHAIR_CURSOR, DEFAULT_CURSOR, E_RESIZE_CURSOR, HAND_CURSOR, ICONIFIED, MAXIMIZED_BOTH, 
 * MAXIMIZED_HORIZ, MAXIMIZED_VERT, MOVE_CURSOR, N_RESIZE_CURSOR, NE_RESIZE_CURSOR, NORMAL, 
 * NW_RESIZE_CURSOR, S_RESIZE_CURSOR, SE_RESIZE_CURSOR, SW_RESIZE_CURSOR, TEXT_CURSOR, 
 * W_RESIZE_CURSOR, WAIT_CURSOR
 * 
 */

public class UShape extends JComponent implements ActionListener,
		FocusListener, MouseMotionListener, MouseListener, Cloneable,
		ComponentListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2906022266659227068L;

	public Point pressedPoint;

	public String m_state;

	public String m_shapeType;

	public String m_label;

	public UCanvas m_canvas;

	protected Color backColor;

	protected Color lineColor;

	protected Color drawColor;

	protected int m_cursor;

	protected BasicStroke stroke;

	protected boolean bUseSelection;

	protected Node node = null;

	public static final int GAP = 10;

	public static final String STATE_NONE = "None";

	public static final String STATE_SELECTED = "Selected";

	public static final String STATE_WAIT_EDIT = "WaitEdit";

	public static final String STATE_RESIZED = "Resized";

	public static final String STATE_MOVE = "Move";

	public static final String STATE_UPDATE = "Update";

	public static final String STATE_CHANGECURSOR = "ChangeCursor";

	public static final String STYPE_NONE = "None";

	public static final String TTYPE_NONE = "None";

	public static final String TTYPE_CENTER = "Center Text";

	public static final String TTYPE_LEFT = "Left Text";

	public static final String TTYPE_RIGHT = "Right Texte";

	public static final String TTYPE_TOP = "Top Text";

	public static final String TTYPE_BOTTOM = "Bottom Text";

	protected UShapeSizeBtn shape1;

	protected UShapeSizeBtn shape2;

	protected UShapeSizeBtn shape3;

	protected UShapeSizeBtn shape4;

	protected UShapeSizeBtn shape5;

	protected UShapeSizeBtn shape6;

	protected UShapeSizeBtn shape7;

	protected UShapeSizeBtn shape8;

	protected JTextField textInput;

	protected Rectangle rectText;

	protected Rectangle rectTitle;

	public JPopupMenu popup = new JPopupMenu();

	//
	protected String ID;

	protected static int nextId = 0;

	private List<UShape> friends;

	protected static Color m_tRed = new Color(255, 0, 0, 150);

	protected static Color m_tGreen = new Color(0, 255, 0, 150);

	protected static Color m_tBlue = new Color(0, 0, 255, 150);

	protected static Color m_tBlue2 = new Color(0, 0, 255, 50);

	protected static Color m_tYellow = new Color(255, 255, 50, 150);

	protected static Font m_biFont = new Font("Monospaced", Font.BOLD
			| Font.ITALIC, 36);

	protected static Font m_pFont = new Font("SanSerif", Font.PLAIN, 12);

	protected static Font m_bFont = new Font("Serif", Font.BOLD, 24);

	final static BasicStroke stroke1 = new BasicStroke(1.0f);

	final static BasicStroke stroke2 = new BasicStroke(2.0f);

	final static BasicStroke stroke3 = new BasicStroke(3.0f);

	final static float dash1[] = { 10.0f };

	final static BasicStroke dashed = new BasicStroke(1.0f,
			BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);

	final static float dash2[] = { 15.0f, 3.0f };

	final static BasicStroke dashed2 = new BasicStroke(1.0f,
			BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash2, 0.0f);

	/** Load resource file from this package */
	public static ResourceBundle resource = unbbayes.util.ResourceController
			.newInstance().getBundle(
					unbbayes.gui.resources.GuiResources.class.getName());

	static public int iUpdate = 0;

	public UShape(UCanvas c, Node pNode, int x, int y, int w, int h) {

		super();
		createID();
		setName(this.ID);
		setLabel(this.ID);
		setCanvas(c);
		setState(STATE_NONE, null);
		setShapeType(STYPE_NONE);
		setLineColor(Color.blue);
		setBackColor(Color.white);
		setStroke(stroke1);

		// Debug.println("create Shape  = " + this.ID + " " + iUpdate++ );

		if (pNode != null) {
			setNode(pNode);
			// by young3
			updateNodeInformation();
		}

		addMouseMotionListener(this);
		addMouseListener(this);
		addComponentListener(this);

		// by young 1/23/2010
		// setCursor(Cursor.MOVE_CURSOR);
		setUseSelection(true);

		setBounds(x, y, w, h);
		setOpaque(false);

		rectText = new Rectangle(0, 0, w, h);
		rectTitle = new Rectangle(0, 0, w, h);

		friends = new ArrayList<UShape>();
		pressedPoint = new Point();

	}

	public void finalize() {

	}

	public void updateNodeInformation() {
		if (getNode() != null) {
			setName(getNode().getName());

			if (getNode().getLabel().equals(""))
				setLabel(getNode().getName());
			else
				setLabel(getNode().getLabel());

			if (getCanvas().getTextOutputMode() == UCanvas.TEXTOUTPUTMODEMODE_USE_NAME)
				setLabel(getNode().getName());
			else if (getCanvas().getTextOutputMode() == UCanvas.TEXTOUTPUTMODEMODE_USE_DESC)
				setLabel(getNode().getDescription());

			setBackColor(getNode().getColor());
		}
	}

	public void update() {

	}

	public void shapeTypeChange(String s) {

	}

	/**
	 * Resets the menu and fills it with the "resize" and "color change" menu.
	 */
	public void createBasicPopupMenu() {
		popup.removeAll();

		// TODO use resource file for localization
		JMenuItem item = new JMenuItem("Resize to fit text");
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				getCanvas().onResizeToFitText();
			}
		});

		JMenuItem item1 = new JMenuItem("Color Change");
		item1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				createColorChooser();
			}
		});

		popup.add(item);
		popup.add(item1);
	}

	/**
	 * Fills the popup menu with the "align" options and basic options
	 */
	public void createPopupMenu() {
		this.createBasicPopupMenu();

		// TODO use resource file for localization
		JMenuItem item2 = new JMenuItem("Node Align: Left");
		item2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				getCanvas().onAlignNodes(UCanvas.NODE_ALIGN_LEFT);
			}
		});

		JMenuItem item3 = new JMenuItem("Node Align: Right");
		item3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				getCanvas().onAlignNodes(UCanvas.NODE_ALIGN_RIGHT);
			}
		});

		JMenuItem item4 = new JMenuItem("Node Align: Top");
		item4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				getCanvas().onAlignNodes(UCanvas.NODE_ALIGN_TOP);
			}
		});

		JMenuItem item5 = new JMenuItem("Node Align: Bottom");
		item5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				getCanvas().onAlignNodes(UCanvas.NODE_ALIGN_BOTTOM);
			}
		});

		popup.add(item2);
		popup.add(item3);
		popup.add(item4);
		popup.add(item5);
	}

	public void createColorChooser() {
		final JColorChooser colorChooser = new JColorChooser();
		final JLabel previewLabel = new JLabel("Color", JLabel.CENTER);
		previewLabel.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 48));
		previewLabel.setSize(previewLabel.getPreferredSize());
		previewLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 1, 0));
		colorChooser.setPreviewPanel(previewLabel);

		ActionListener okActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				Debug.println("OK Button");
				Debug.println(colorChooser.getColor().toString());

				getCanvas().onShapeColorChanged(colorChooser.getColor());
				repaint();
			}
		};

		ActionListener cancelActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				Debug.println("Cancel Button");
			}
		};

		final JDialog dialog = JColorChooser.createDialog(null,
				"Change Button Background", true, colorChooser,
				okActionListener, cancelActionListener);

		dialog.setVisible(true);

	}

	/**
	 * 
	 * @param str
	 */
	public void setLabel(String str) {
		m_label = str;
	}

	public String getLabel() {
		return m_label;
	}

	public void setUseSelection(boolean b) {
		bUseSelection = b;
	}

	public boolean getUseSelection() {
		return bUseSelection;
	}

	public void setState(String s, Object o) {
		m_state = s;

		if (getUseSelection() == true) {
			if (s == STATE_WAIT_EDIT) {
				createTextBox();
			} else if (s == STATE_UPDATE) {
				update();
				m_state = STATE_NONE;
			} else if (s == STATE_SELECTED) {
				moveResizeBtn();
				removeTextBox();
				getCanvas().requestFocus();
			} else if (s == STATE_RESIZED) {
				moveResizeBtn();
				removeTextBox();
				update();
				m_state = STATE_SELECTED;
			} else if (s == STATE_CHANGECURSOR) {
				Cursor customCursor = (Cursor) o;
				setCursor(customCursor);
			} else if (s == STATE_NONE) {
				hideResizeBtn();
				removeTextBox();
			}

		}
	}

	public Color getLineColor() {
		return lineColor;
	}

	public void setLineColor(Color c) {
		lineColor = c;
		setDrawColor(c);
	}

	public Color getBackColor() {
		return backColor;
	}

	public void setBackColor(Color c) {
		if (node != null)
			node.setColor(c);

		backColor = c;
	}

	public void setBackColorWithoutNode(Color c) {
		backColor = c;
	}

	public Color getDrawColor() {
		return drawColor;
	}

	public void setDrawColor(Color c) {
		drawColor = c;
	}

	public BasicStroke getStroke() {
		return stroke;
	}

	public void setStroke(BasicStroke c) {
		stroke = c;
	}

	public String getState() {
		return m_state;
	}

	public void setShapeType(String s) {
		m_shapeType = s;
	}

	public String getShapeType() {
		return m_shapeType;
	}

	public void setNode(Node n) {
		node = n;
	}

	public Node getNode() {
		return node;
	}

	public void setCanvas(UCanvas s) {
		m_canvas = s;
	}

	public UCanvas getCanvas() {
		return m_canvas;
	}

	public int getCursorStyle() {
		return m_cursor;
	}

	public void setCursor(int s) {
		m_cursor = s;
	}

	public void paint(Graphics2D graphics) {
		super.paintComponent(graphics);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(getDrawColor());
	}

	public Rectangle getTextRect(String str) {
		Rectangle rect;
		int w = 30;
		int h = 20;

		if (str != null) {
			FontMetrics fm = this.getFontMetrics(m_pFont);

			w = fm.stringWidth(str);
			h = fm.getAscent();
		}

		return rect = new Rectangle(0, 0, w, h);
	}

	public void resizeToFitText() {
		String strName = getLabel();

		if (strName.equals(""))
			strName = getName();

		Rectangle rect = getTextRect(strName);

		setNewSize(getX(), getY(), (int) (rect.getWidth() + 50), Node.DEFAULT_SIZE.y);
	}

	public String checkLimitedTextSize(Graphics g, Rectangle rect, String str) {
		int w = 0;
		int h = 0;
		int l;

		g.setFont(m_pFont);
		FontMetrics fm = g.getFontMetrics();

		w = fm.stringWidth(str + "#####");
		h = fm.getAscent();
		l = str.length();

		if (w > rect.getWidth()) {
			if (l - 1 <= 0)
				return str;

			str = str.substring(0, l - 1);

			str = checkLimitedTextSize(g, rect, str);

		}

		return str;
	}

	public String getLimitedTextSize(Graphics g, Rectangle rect, String str) {
		String strNew;

		strNew = checkLimitedTextSize(g, rect, str);

		if (strNew != str)
			return strNew + "...";

		return str;
	}

	public void drawText(Graphics g) {
		String strName = getLabel();

		if (strName.equals(""))
			strName = getName();

		drawText(g, new Rectangle(0, 0, getBounds().width, getBounds().height),
				strName, TTYPE_CENTER);
	}

	public void drawText(Graphics g, Rectangle rect) {
		String strName = getLabel();

		if (strName.equals(""))
			strName = getName();

		drawText(g, rect, strName, TTYPE_CENTER);
	}

	public void drawText(Graphics g, Rectangle rect, String strName,
			String strType) {
		if (!strName.equals("") && getState() != STATE_WAIT_EDIT) {
			String str;
			str = getLimitedTextSize(g, rect, strName);
			Rectangle rectStatic = getTextRect(str);

			int x = 0;
			int y = 0;
			int leftGap = 2;
			int topGap = 2;

			if (strType == TTYPE_CENTER) {
				x = (int) (rect.getWidth() / 2 - (rectStatic.getWidth() / 2));
				y = (int) (rect.getHeight() / 2 - (rectStatic.getHeight() / 2));
			} else if (strType == TTYPE_LEFT) {

				x = leftGap;
				y = (int) (rect.getHeight() / 2 - (rectStatic.getHeight() / 2));
			} else if (strType == TTYPE_RIGHT) {
				x = (int) (rect.getWidth() - rectStatic.getWidth());
				y = topGap
						+ (int) (rect.getHeight() / 2 - rectStatic.getHeight() / 2);
			}

			rectStatic.move((int) rect.getX() + x, (int) rect.getY() + y);

			// +----+
			// |Text|
			// o----+
			// Leftmost
			g.drawString(str, (int) rectStatic.getX(), (int) rectStatic.getY()
					+ (int) rectStatic.getHeight());

			// set text rectangle
			rectText = rectStatic;

			// by young 1/23/2010
			// if this shape is using selection mode, can use selecting line.
			if (getUseSelection() == true) {
				if (getState() == STATE_SELECTED) {
					g.setColor(Color.blue);
					g.drawRoundRect(GAP / 2, GAP / 2, getWidth() - GAP - 1,
							getHeight() - GAP - 1, 5, 5);
				}
			}
		}
	}

	public void createTextBox() {
		if (textInput != null)
			remove(textInput);

		int widthGap = 20;
		Rectangle rect = getTextRect(getName());
		textInput = new JTextField(getName(), 10);
		textInput.setBounds(widthGap, (int) rectText.getY(), (int) getWidth()
				- widthGap * 2, (int) rectText.getHeight() + 4);
		textInput.setBackground(Color.white);
		textInput.addActionListener(this);
		textInput.addFocusListener(this);
		add(textInput);
		textInput.requestFocusInWindow();
		repaint();
	}

	public void removeTextBox() {
		if (textInput != null) {
			setName(textInput.getText());
			Debug.println(textInput.getText());

			if (node != null) {
				node.setName(textInput.getText());

				getCanvas().onShapeChanged(this);
			}

			remove(textInput);
			repaint();
			requestFocusInWindow();
			getCanvas().setState(STATE_UPDATE);
			getCanvas().onSelectionChanged();
			textInput = null;
		}
	}

	public boolean createResizeBtn() {
		// by young 1/23/2010
		// if this shape is not to use selection mode, don't create resize
		// buttons.
		if (getUseSelection() == false)
			return false;

		// the below if was added because somehow those shapes were being
		// instantiated more than twice and
		// they were not being hidden or removed...
		if (shape1 != null || shape2 != null || shape3 != null
				|| shape4 != null || shape5 != null || shape6 != null
				|| shape7 != null || shape8 != null) {
			removeResizeBtn();
		}
		shape1 = new UShapeSizeBtn(this, 0, 0, Cursor.NW_RESIZE_CURSOR);
		shape2 = new UShapeSizeBtn(this, (getWidth() - GAP) / 2, 0,
				Cursor.N_RESIZE_CURSOR);
		shape3 = new UShapeSizeBtn(this, getWidth() - GAP, 0,
				Cursor.NE_RESIZE_CURSOR);
		shape4 = new UShapeSizeBtn(this, 0, (getHeight() - GAP) / 2,
				Cursor.E_RESIZE_CURSOR);
		shape5 = new UShapeSizeBtn(this, 0, getHeight() - GAP,
				Cursor.SW_RESIZE_CURSOR);
		shape6 = new UShapeSizeBtn(this, (getWidth() - GAP) / 2, getHeight()
				- GAP, Cursor.S_RESIZE_CURSOR);
		shape7 = new UShapeSizeBtn(this, getWidth() - GAP,
				(getHeight() - GAP) / 2, Cursor.W_RESIZE_CURSOR);
		shape8 = new UShapeSizeBtn(this, getWidth() - GAP, getHeight() - GAP,
				Cursor.SE_RESIZE_CURSOR);

		return true;
	}

	public void moveResizeBtn() {
		if (shape1 == null)
			createResizeBtn();

		if (shape1.isVisible() == false)
			showResizeBtn();

		shape1.move(0, 0);
		shape2.move((getWidth() - GAP) / 2, 0);
		shape3.move(getWidth() - GAP, 0);
		shape4.move(0, (getHeight() - GAP) / 2);
		shape5.move(0, getHeight() - GAP);
		shape6.move((getWidth() - GAP) / 2, getHeight() - GAP);
		shape7.move(getWidth() - GAP, (getHeight() - GAP) / 2);
		shape8.move(getWidth() - GAP, getHeight() - GAP);
	}

	public void showResizeBtn() {
		if (shape1 != null)
			shape1.setVisible(true);
		if (shape2 != null)
			shape2.setVisible(true);
		if (shape3 != null)
			shape3.setVisible(true);
		if (shape4 != null)
			shape4.setVisible(true);
		if (shape5 != null)
			shape5.setVisible(true);
		if (shape6 != null)
			shape6.setVisible(true);
		if (shape7 != null)
			shape7.setVisible(true);
		if (shape7 != null)
			shape8.setVisible(true);
	}

	public void hideResizeBtn() {
		if (shape1 != null)
			shape1.setVisible(false);
		if (shape2 != null)
			shape2.setVisible(false);
		if (shape3 != null)
			shape3.setVisible(false);
		if (shape4 != null)
			shape4.setVisible(false);
		if (shape5 != null)
			shape5.setVisible(false);
		if (shape6 != null)
			shape6.setVisible(false);
		if (shape7 != null)
			shape7.setVisible(false);
		if (shape7 != null)
			shape8.setVisible(false);
	}

	public void removeResizeBtn() {
		if (shape1 != null)
			this.remove(shape1);
		if (shape2 != null)
			this.remove(shape2);
		if (shape3 != null)
			this.remove(shape3);
		if (shape4 != null)
			this.remove(shape4);
		if (shape5 != null)
			this.remove(shape5);
		if (shape6 != null)
			this.remove(shape6);
		if (shape7 != null)
			this.remove(shape7);
		if (shape7 != null)
			this.remove(shape8);
	}

	public int getGlobalX() {
		int xParent = 0;

		// check Container
		if (this.getParent() instanceof UShapeFrame)
			xParent = this.getParent().getX();

		return xParent + getX();
	}

	public int getGlobalY() {
		int yParent = 0;

		// check Container
		if (this.getParent() instanceof UShapeFrame)
			yParent = this.getParent().getY();

		return yParent + getY();
	}

	public int getCenterX() {
		return getGlobalX() + getWidth() / 2;
	}

	public int getCenterY() {
		return getGlobalY() + getHeight() / 2;
	}

	// by young2
	public Rectangle getShapeRect() {
		Rectangle rc = this.getBounds();
		return new Rectangle(rc.x + GAP, rc.y + GAP, rc.width - GAP * 2,
				rc.height - GAP * 2);
	}

	public boolean checkLimitSize(Rectangle rc) {
		Rectangle rcCheck = new Rectangle(getX() + rc.x, getY() + rc.y,
				getWidth() + rc.width, getHeight() + rc.height);

		if (rcCheck.height < 15 || rcCheck.width < 45) {
			return false;
		}

		return true;
	}

	public boolean contain(double x, double y) {
		return false;
	}

	public boolean isContained(Rectangle rc) {
		// by young2
		if (rc.contains(getShapeRect()))
			return true;

		return false;
	}

	public UShape checkContainer() {
		int n = getCanvas().getComponentCount();

		for (int i = 0; i < n; i++) {
			UShape shape = (UShape) getCanvas().getComponent(i);

			if (shape != this)
				if (shape instanceof UShapeFrame) {
					if (this.isContained(shape.getBounds())) {
						return shape;
					}
				}
		}

		return null;
	}

	public boolean checkExactEdge(Graphics g, UShape shapeDes,
			Point2D.Double pSrcResult, Point2D.Double pDesResult) {

		double xSrc = getCenterX();
		double ySrc = getCenterY();

		double xDes = shapeDes.getCenterX();
		double yDes = shapeDes.getCenterY();

		double xOri = xSrc;
		double yOri = ySrc;

		double ratio = 0;
		double x = 0;
		double y = 0;
		double xPrev = 0;
		double yPrev = 0;

		// ScreenToQuadrant
		xSrc = xSrc - xOri;
		ySrc = ySrc - yOri;
		xDes = xDes - xOri;
		yDes = yDes - yOri;

		if (xSrc == xDes) {
			if (ySrc > yDes) {
				for (y = 0; y > yDes; y--) {
					if (!this.contain(xOri + x, yOri + y) && pSrcResult.x == 0
							&& pSrcResult.y == 0) {
						pSrcResult.x = (int) (xOri + x);
						pSrcResult.y = (int) (yOri + y);
					}
					if (shapeDes.contain(xOri + x, yOri + y)
							&& pDesResult.x == 0 && pDesResult.y == 0) {
						pDesResult.x = (int) (xOri + xPrev);
						pDesResult.y = (int) (yOri + yPrev);
					}

					xPrev = x;
					yPrev = y;
				}
			} else if (ySrc < yDes) {
				for (y = 0; y < yDes; y++) {
					if (!this.contain(xOri + x, yOri + y) && pSrcResult.x == 0
							&& pSrcResult.y == 0) {
						pSrcResult.x = (int) (xOri + x);
						pSrcResult.y = (int) (yOri + y);
					}
					if (shapeDes.contain(xOri + x, yOri + y)
							&& pDesResult.x == 0 && pDesResult.y == 0) {
						pDesResult.x = (int) (xOri + xPrev);
						pDesResult.y = (int) (yOri + yPrev);
					}

					xPrev = x;
					yPrev = y;
				}
			}
		} else if (ySrc == yDes) {
			if (xSrc > xDes) {
				for (x = 0; x > xDes; x--) {
					if (!this.contain(xOri + x, yOri + y) && pSrcResult.x == 0
							&& pSrcResult.y == 0) {
						pSrcResult.x = (int) (xOri + x);
						pSrcResult.y = (int) (yOri + y);
					}
					if (shapeDes.contain(xOri + x, yOri + y)
							&& pDesResult.x == 0 && pDesResult.y == 0) {
						pDesResult.x = (int) (xOri + xPrev);
						pDesResult.y = (int) (yOri + yPrev);
					}

					xPrev = x;
					yPrev = y;
				}
			} else if (xSrc < xDes) {
				for (x = 0; x < xDes; x++) {
					if (!this.contain(xOri + x, yOri + y) && pSrcResult.x == 0
							&& pSrcResult.y == 0) {
						pSrcResult.x = (int) (xOri + x);
						pSrcResult.y = (int) (yOri + y);
					}
					if (shapeDes.contain(xOri + x, yOri + y)
							&& pDesResult.x == 0 && pDesResult.y == 0) {
						pDesResult.x = (int) (xOri + xPrev);
						pDesResult.y = (int) (yOri + yPrev);
					}

					xPrev = x;
					yPrev = y;
				}
			}
		} else if (xSrc > xDes) {
			ratio = (double) ((double) (ySrc - yDes) / (double) (xSrc - xDes));

			if (ratio > 1) {
				ratio = (double) ((double) (xSrc - xDes) / (double) (ySrc - yDes));

				for (x = 0, y = 0; y > yDes; y--) {
					if (!this.contain(xOri + x, yOri + y) && pSrcResult.x == 0
							&& pSrcResult.y == 0) {
						pSrcResult.x = (int) (xOri + x);
						pSrcResult.y = (int) (yOri + y);
					}
					if (shapeDes.contain(xOri + x, yOri + y)
							&& pDesResult.x == 0 && pDesResult.y == 0) {
						pDesResult.x = (int) (xOri + xPrev);
						pDesResult.y = (int) (yOri + yPrev);
					}

					xPrev = x;
					yPrev = y;

					x = (int) (ratio * y);
				}
			} else if (ratio < -1) {
				ratio = (double) ((double) (xSrc - xDes) / (double) (ySrc - yDes));

				for (x = 0, y = 0; y < yDes; y++) {
					if (!this.contain(xOri + x, yOri + y) && pSrcResult.x == 0
							&& pSrcResult.y == 0) {
						pSrcResult.x = (int) (xOri + x);
						pSrcResult.y = (int) (yOri + y);
					}
					if (shapeDes.contain(xOri + x, yOri + y)
							&& pDesResult.x == 0 && pDesResult.y == 0) {
						pDesResult.x = (int) (xOri + xPrev);
						pDesResult.y = (int) (yOri + yPrev);
					}

					xPrev = x;
					yPrev = y;

					x = (int) (ratio * y);
				}
			} else {
				for (x = 0, y = 0; x > xDes; x--) {
					if (!this.contain(xOri + x, yOri + y) && pSrcResult.x == 0
							&& pSrcResult.y == 0) {
						pSrcResult.x = (int) (xOri + x);
						pSrcResult.y = (int) (yOri + y);
					}
					if (shapeDes.contain(xOri + x, yOri + y)
							&& pDesResult.x == 0 && pDesResult.y == 0) {
						pDesResult.x = (int) (xOri + xPrev);
						pDesResult.y = (int) (yOri + yPrev);
					}

					xPrev = x;
					yPrev = y;

					y = (int) (ratio * x);
				}
			}
		} else if (xSrc < xDes) {
			ratio = (double) ((double) (yDes - ySrc) / (double) (xDes - xSrc));

			if (ratio < -1) {
				ratio = (double) ((double) (xSrc - xDes) / (double) (ySrc - yDes));

				for (x = 0, y = 0; y > yDes; y--) {
					if (!this.contain(xOri + x, yOri + y) && pSrcResult.x == 0
							&& pSrcResult.y == 0) {
						pSrcResult.x = (int) (xOri + x);
						pSrcResult.y = (int) (yOri + y);
					}
					if (shapeDes.contain(xOri + x, yOri + y)
							&& pDesResult.x == 0 && pDesResult.y == 0) {
						pDesResult.x = (int) (xOri + xPrev);
						pDesResult.y = (int) (yOri + yPrev);
					}

					xPrev = x;
					yPrev = y;

					x = (int) (ratio * y);
				}
			} else if (ratio > 1) {
				ratio = (double) ((double) (xSrc - xDes) / (double) (ySrc - yDes));

				for (x = 0, y = 0; y < yDes; y++) {
					if (!this.contain(xOri + x, yOri + y) && pSrcResult.x == 0
							&& pSrcResult.y == 0) {
						pSrcResult.x = (int) (xOri + x);
						pSrcResult.y = (int) (yOri + y);
					}
					if (shapeDes.contain(xOri + x, yOri + y)
							&& pDesResult.x == 0 && pDesResult.y == 0) {
						pDesResult.x = (int) (xOri + xPrev);
						pDesResult.y = (int) (yOri + yPrev);
					}

					xPrev = x;
					yPrev = y;

					x = (int) (ratio * y);
				}
			} else {
				for (x = 0, y = 0; x < xDes; x++) {
					if (!this.contain(xOri + x, yOri + y) && pSrcResult.x == 0
							&& pSrcResult.y == 0) {
						pSrcResult.x = (int) (xOri + x);
						pSrcResult.y = (int) (yOri + y);
					}
					if (shapeDes.contain(xOri + x, yOri + y)
							&& pDesResult.x == 0 && pDesResult.y == 0) {
						pDesResult.x = (int) (xOri + xPrev);
						pDesResult.y = (int) (yOri + yPrev);
					}

					xPrev = x;
					yPrev = y;

					y = (int) (ratio * x);
				}
			}
		}

		if ((pSrcResult.x == 0 && pSrcResult.y == 0)
				|| (pDesResult.x == 0 && pDesResult.y == 0))
			return false;

		return true;
	}

	public Object clone() {
		UShape clone = new UShape(m_canvas, null, 0, 0, 30, 30);

		return clone;
	}

	protected void createID() {
		ID = String.valueOf(nextId++);
	}

	protected void addFriend(UShape friend) {
		friends.add(friend);
	}

	protected void removeFriend(UShape friend) {
		friends.remove(friend);
	}

	public List<UShape> getFriends() {
		return friends;
	}

	public Object getChild(String id) {
		Object result = null;

		return result;
	}

	public void sendMessageToFriends(String Msg) {
		Iterator it = friends.iterator();

		while (it.hasNext()) {
			UShape shape = (UShape) it.next();
			if (shape != null)
				shape.receiveMessage(Msg);
		}
	}

	public void receiveMessage(String Msg) {
	}

	public void setNewSize(int x, int y, int w, int h) {
		setBounds(x, y, w, h);
		setState(UShape.STATE_RESIZED, null);
		sendMessageToFriends(STATE_MOVE);

		int n = this.getComponentCount();

		for (int i = 0; i < n; i++) {
			UShape shape = (UShape) this.getComponent(i);

			shape.update();
			shape.sendMessageToFriends(STATE_MOVE);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.Component#setBounds(int, int, int, int)
	 */
	@Override
	public void setBounds(int x, int y, int width, int height) {
		// adapting it in order to stop going "out" of canvas
		if (x < 0) {
			x = 0;
		}
		if (y < 0) {
			y = 0;
		}
		super.setBounds(x, y, width, height);
	}

	public Object[] cloneChildren(Object[] children, boolean includeChildren) {
		Map mapping = new Hashtable();
		Object[] clones = new Object[children.length];

		for (int i = 0; i < children.length; i++) {
			// clones[i] = cloneChild(children[i], mapping, includeChildren);
		}

		for (int i = 0; i < children.length; i++) {
			// restoreClone(clones[i], children[i], mapping);
		}

		return clones;
	}

	public void mouseDragged(MouseEvent arg0) {
		if (getUseSelection() == false) {
			getCanvas().mouseDragged(arg0.getX() + getGlobalX(),
					arg0.getY() + getGlobalY());
		} else if (getUseSelection() == true) {
			Point loc = this.getLocation();
			Rectangle rc = this.getBounds();

			/*
			 * Debug.println("mouseDragged . " + rc.x + "  " + rc.y + " " +
			 * loc.x + "  " + loc.y + " " + pressedPoint.x + "  " + arg0.getX()
			 * + "  " + pressedPoint.y + "  " + arg0.getY());
			 */

			if (contain(pressedPoint.x + loc.x, pressedPoint.y + loc.y) == true) {
				if (m_canvas.getState() == UCanvas.STATE_RESIZE_COMP
						|| m_canvas.getState() == UCanvas.STATE_NONE) {
					setNewSize(loc.x - pressedPoint.x + arg0.getX(), loc.y
							- pressedPoint.y + arg0.getY(), rc.width, rc.height);

					getCanvas().onShapeMoved(this,
							-pressedPoint.x + arg0.getX(),
							-pressedPoint.y + arg0.getY());
				} else if (m_canvas.getState() == UCanvas.STATE_CONNECT_COMP) {
					getCanvas().onDrawConnectLineEnter(loc.x + arg0.getX(),
							loc.y + arg0.getY());
				}

				getCanvas().repaint();
			}
		}

	}

	public void changeToLocalPosition(Point2D.Double p) {
		p.x = p.x - this.getLocation().x;
		p.y = p.y - this.getLocation().y;
	}

	public void changeToGlobalPosition(Point2D.Double p) {
		p.x = p.x + this.getLocation().x;
		p.y = p.y + this.getLocation().y;
	}

	public void mouseMoved(MouseEvent arg0) {
		Point2D.Double p = new Point2D.Double(arg0.getX(), arg0.getY());
		changeToGlobalPosition(p);

		// by young 1/23/2010
		if (contain(p.x, p.y)) {
			setDrawColor(Color.red);
			repaint();
		} else {
			setDrawColor(getLineColor());
			repaint();
		}
	}

	public void mouseClicked(MouseEvent arg0) {
		if (SwingUtilities.isLeftMouseButton(arg0)) {
			if (arg0.getClickCount() == 2 && !arg0.isConsumed()) {
				Debug.println("handle double click.");

				arg0.consume();

				setState(STATE_WAIT_EDIT, null);
			}
		}

		if (SwingUtilities.isMiddleMouseButton(arg0)) {
			Debug.println("Middle button released.");
		}

		if (SwingUtilities.isRightMouseButton(arg0)) {
			Debug.println("Right button released.");

			createPopupMenu();

			popup.setEnabled(true);
			popup.show(arg0.getComponent(), arg0.getX(), arg0.getY());
		}

	}

	public void mouseEntered(MouseEvent arg0) {
		Point2D.Double p = new Point2D.Double(arg0.getX(), arg0.getY());
		changeToGlobalPosition(p);
	}

	public void mouseExited(MouseEvent arg0) {
		setDrawColor(getLineColor());
		repaint();
	}

	public void mousePressed(MouseEvent arg0) {
		// set mouse pressed point
		pressedPoint.x = arg0.getX();
		pressedPoint.y = arg0.getY();

		// set this shape to TopLayer
		getCanvas().setLayer(this, getCanvas().DRAG_LAYER);

		// check Container
		if (this.getParent() instanceof UShapeFrame) {
			Point loc = this.getLocation();
			Point locFrame = this.getParent().getLocation();

			this.move(loc.x + locFrame.x, loc.y + locFrame.y);

			getCanvas().add(this);
		}

		// For selection
		if (getCanvas().getState() == UCanvas.STATE_CTRL) {
			if (getState() != STATE_SELECTED) {
				setState(STATE_SELECTED, null);
				getCanvas().onShapeChanged(this);
				getCanvas().onSelectionChanged();
			} else {
				setState(STATE_NONE, null);
				getCanvas().onShapeChanged(this);
				getCanvas().onSelectionChanged();
			}
		} else if (getState() != STATE_SELECTED) {
			// by young 1/23/2010
			Point2D.Double p = new Point2D.Double(arg0.getX(), arg0.getY());
			changeToGlobalPosition(p);

			if (contain(p.x, p.y) == true) {
				getCanvas().setShapeStateAll(STATE_NONE, null);
				setState(STATE_SELECTED, null);
				getCanvas().onShapeChanged(this);
				getCanvas().onSelectionChanged();
			}
		}

		repaint();

	}

	public void mouseReleased(MouseEvent arg0) {
		Point loc = this.getLocation();
		Rectangle rc = this.getBounds();

		// check Container
		UShape shapeFrame = checkContainer();
		if (shapeFrame != null) {
			Point locFrame = shapeFrame.getLocation();

			this.move(loc.x - locFrame.x, loc.y - locFrame.y);
			shapeFrame.add(this);
		}

		// connect line between nodes
		if (getCanvas().getState() == UCanvas.STATE_CONNECT_COMP) {
			getCanvas().onDrawConnectLineReleased(this,
					getGlobalX() + arg0.getX(), getGlobalY() + arg0.getY());
		}

		// release state
		if (shapeFrame != null)
			this.setState(STATE_NONE, null);

		// for right mouse button
		if (SwingUtilities.isRightMouseButton(arg0)) {
			Debug.println("Right button released.");
		}

		if (getUseSelection() == false) {
			getCanvas().mouseReleased(arg0.getX() + getGlobalX(),
					arg0.getY() + getGlobalY());
		}
	}

	public void actionPerformed(ActionEvent arg0) {
		removeTextBox();
		setState(STATE_SELECTED, null);
	}

	public void focusGained(FocusEvent arg0) {
		Debug.println("focusGained");
		SimdecsNodeWindow janela = new SimdecsNodeWindow(this.node);
	}

	public void focusLost(FocusEvent arg0) {
		Debug.println("focusLost");

		removeTextBox();
	}

	public void componentHidden(ComponentEvent arg0) {

	}

	public void componentMoved(ComponentEvent arg0) {
		if (node != null) {
			node.setPosition(getX(), getY());
		}

		getCanvas().onPositionChanged(this);
	}

	public void componentResized(ComponentEvent arg0) {
		if (node != null) {
			node.setSize(getWidth(), getHeight());
		}

		if (this.getCanvas() != null) {
			getCanvas().onResized(this);
		}
	}

	public void componentShown(ComponentEvent arg0) {

	}
}
