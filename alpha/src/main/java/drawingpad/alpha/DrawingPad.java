package drawingpad.alpha;


/*
 * Programmer: Stephen Dighans
 * Date: Jan 7, 2008
 * Program File: DrawingPad.java
 * Purpose: A painting program in Java main instance method
 */
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.awt.geom.*;
public class DrawingPad extends JFrame
{
	private JPanel drawPad, pnlColorBar, pnlSideBar;
	private BufferedImage biDrawPad, biOrig;
	private Dimension drawPadSize;
	private Color firstColor, secondColor;
	private Graphics2D g2dBiTemp;
	private int sx, sy, fx, fy;
	private int onmask = MouseEvent.BUTTON1_DOWN_MASK | MouseEvent.BUTTON3_DOWN_MASK;
	private int action;
	
	public DrawingPad()
	{
		try 
		{
	        UIManager.setLookAndFeel(
	            UIManager.getSystemLookAndFeelClassName());
	    } 
	    catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e){}
	    
		TopMenu tm;
		tm = new TopMenu(this);
		setJMenuBar(tm.getMnuBar());
		
		drawPad = new DrawArea(this);
		drawPad.setPreferredSize(new Dimension(1024, 786));
		
		SideBar sb = new SideBar(this, this);
		pnlSideBar = sb.getSideBar();
		
		firstColor = Color.black;
		secondColor = Color.white;
		ColorBar cb = new ColorBar(this, this, sb.getSideBar());
		pnlColorBar = cb.getClrBar();
		
		this.setLayout(new BorderLayout());
			this.add(drawPad, BorderLayout.CENTER);
			this.add(pnlSideBar, BorderLayout.WEST);
			this.add(pnlColorBar, BorderLayout.SOUTH);
		this.pack();
		
		drawPadSize = drawPad.getPreferredSize();
		biDrawPad = drawPad.getGraphicsConfiguration().createCompatibleImage(drawPadSize.width, drawPadSize.height);
		Graphics2D g2dBiDrawPad = biDrawPad.createGraphics();
		g2dBiDrawPad.setColor(Color.white);
		g2dBiDrawPad.fillRect(0, 0, drawPadSize.width, drawPadSize.height);
		g2dBiDrawPad.dispose();
		g2dBiDrawPad = null;
		
		
		
		addWindowListener(
				new WindowAdapter()
				{
					public void windowClosing(WindowEvent e)
					{
							e.getWindow().dispose();
					}//end of windowClosing
				}//end of WindowAdapter
			);//end of addWindowListener
		drawPad.addMouseListener(
				new MouseAdapter()
				{
					public void mousePressed(MouseEvent e)
					{
						switch(action)
						{
							case 0:
								if(e.getModifiers() == MouseEvent.BUTTON1_MASK)
								{
									sx = e.getX();
									sy = e.getY();
									mousePressedLineCode();
								}
								else if((e.getModifiersEx() & onmask) == onmask)
								{
									sx = e.getX();
									sy = e.getY();
									mousePressedPensilCode();
								}
							break;
							case 1:
								if(e.getModifiers() == MouseEvent.BUTTON1_MASK || e.getModifiers() == MouseEvent.BUTTON3_MASK)
								{
									sx = e.getX();
									sy = e.getY();
									mousePressedPensilCode();
								}
								else if((e.getModifiersEx() & onmask) == onmask)
								{
									sx = e.getX();
									sy = e.getY();
									mousePressedPensilCode();
								}
							break;
							case 2:
								if(e.getModifiers() == MouseEvent.BUTTON1_MASK || e.getModifiers() == MouseEvent.BUTTON3_MASK)
								{
									sx = e.getX();
									sy = e.getY();
									mousePressedSquareCode();
								}
								else if((e.getModifiersEx() & onmask) == onmask)
								{
									sx = e.getX();
									sy = e.getY();
									mousePressedSquareCode();
								}
							break;
							case 3:
								if(e.getModifiers() == MouseEvent.BUTTON1_MASK || e.getModifiers() == MouseEvent.BUTTON3_MASK)
								{
									sx = e.getX();
									sy = e.getY();
									mousePressedCircleCode();
								}
								else if((e.getModifiersEx() & onmask) == onmask)
								{
									sx = e.getX();
									sy = e.getY();
									mousePressedCircleCode();
								}
							break;
						}
					}
					
					public void mouseReleased(MouseEvent e)
 					{
						switch(action)
						{
							case 0:
								if(e.getModifiers() == MouseEvent.BUTTON1_MASK)
								{
									mouseReleasedLineCode();
								}
								else if((e.getModifiersEx() & onmask) == onmask)
								{
									mouseReleasedLineCode();
								}
							break;
							case 1:
								if(e.getModifiers() == MouseEvent.BUTTON1_MASK || e.getModifiers() == MouseEvent.BUTTON3_MASK)
								{
									mouseReleasedPensilCode();
								}
								else if((e.getModifiersEx() & onmask) == onmask)
								{
									mouseReleasedPensilCode();
								}
							break;
							case 2:
								if(e.getModifiers() == MouseEvent.BUTTON1_MASK || e.getModifiers() == MouseEvent.BUTTON3_MASK)
								{
									mouseReleasedSquareCode();
								}
								else if((e.getModifiersEx() & onmask) == onmask)
								{
									mouseReleasedSquareCode();
								}
							break;
							case 3:
								if(e.getModifiers() == MouseEvent.BUTTON1_MASK || e.getModifiers() == MouseEvent.BUTTON3_MASK)
								{
									mouseReleasedCircleCode();
								}
								else if((e.getModifiersEx() & onmask) == onmask)
								{
									mouseReleasedCircleCode();
								}
							break;
						}
					}
				}
			);
		drawPad.addMouseMotionListener(
				new MouseMotionAdapter()
				{
					public void mouseDragged(MouseEvent e)
					{
						fx = e.getX();
						fy = e.getY();
						switch(action)
						{
							case 0:
								if(e.getModifiers() == MouseEvent.BUTTON1_MASK)
								{
									mouseDraggedLineCode();
								}
								else if((e.getModifiersEx() & onmask) == onmask)
								{
									mouseDraggedLineCode();
								}
							break;
							case 1:
								if(e.getModifiers() == MouseEvent.BUTTON1_MASK || e.getModifiers() == MouseEvent.BUTTON3_MASK)
								{
									mouseDraggedPensilCode();
								}
								else if((e.getModifiersEx() & onmask) == onmask)
								{
									mouseDraggedPensilCode();
								}
							break;
							case 2:
								if(e.getModifiers() == MouseEvent.BUTTON1_MASK || e.getModifiers() == MouseEvent.BUTTON3_MASK)
								{
									mouseDraggedSquareCode();
								}
								else if((e.getModifiersEx() & onmask) == onmask)
								{
									mouseDraggedSquareCode();
								}
							break;
							case 3:
								if(e.getModifiers() == MouseEvent.BUTTON1_MASK || e.getModifiers() == MouseEvent.BUTTON3_MASK)
								{
									mouseDraggedCircleCode();
								}
								else if((e.getModifiersEx() & onmask) == onmask)
								{
									mouseDraggedCircleCode();
								}
							break;
						}
					}
				}
			);
	}
	
	private void mouseDraggedLineCode()
	{
		g2dBiTemp.drawImage(biOrig, 0, 0, null);
		g2dBiTemp.drawLine(sx, sy, fx, fy);
		drawPad.repaint();
	}
	
	private void mouseReleasedLineCode()
	{
		biOrig = null;
		g2dBiTemp.dispose();
		g2dBiTemp = null;
	}
	
	private void mousePressedLineCode()
	{
		biOrig = new BufferedImage(drawPadSize.width, drawPadSize.height, biDrawPad.getType());
		Graphics2D g2dBiOrig = biOrig.createGraphics();
		g2dBiOrig.drawImage(biDrawPad, 0, 0, null);
		g2dBiOrig.dispose();
		g2dBiOrig = null;
		g2dBiTemp = biDrawPad.createGraphics();
		g2dBiTemp.setColor(firstColor);
	}
	
	private void mouseDraggedPensilCode()
	{
		g2dBiTemp.drawLine(sx, sy, fx, fy);
		drawPad.repaint();
		sx = fx;
		sy = fy;
	}
	
	private void mouseReleasedPensilCode()
	{
		biOrig = null;
		g2dBiTemp.dispose();
		g2dBiTemp = null;
	}
	
	private void mousePressedPensilCode()
	{
		biOrig = new BufferedImage(drawPadSize.width, drawPadSize.height, biDrawPad.getType());
		Graphics2D g2dBiOrig = biOrig.createGraphics();
		g2dBiOrig.drawImage(biDrawPad, 0, 0, null);
		g2dBiOrig.dispose();
		g2dBiOrig = null;
		g2dBiTemp = biDrawPad.createGraphics();
		g2dBiTemp.drawImage(biOrig, 0, 0, null);
		g2dBiTemp.setColor(firstColor);
	}
	
	private void mouseDraggedSquareCode()
	{
		int width = fx - sx;
		int height = fy - sy;
		
		g2dBiTemp.drawImage(biOrig, 0, 0, null);
		g2dBiTemp.setColor(firstColor);
		if(width < 0 && height < 0)
		{
			g2dBiTemp.fillRect((sx + width + 1), (sy + height + 1), Math.abs(width) - 1, Math.abs(height) - 1);
			g2dBiTemp.setColor(secondColor);
			g2dBiTemp.drawRect((sx + width), (sy + height), Math.abs(width), Math.abs(height));
		}
		else if(width < 0 && height > 0)
		{
			g2dBiTemp.fillRect((sx + width + 1), sy + 1, Math.abs(width) - 1, height - 1);
			g2dBiTemp.setColor(secondColor);
			g2dBiTemp.drawRect((sx + width), sy, Math.abs(width), height);
		}
		else if(width > 0 && height < 0)
		{
			g2dBiTemp.fillRect(sx + 1, (sy + height + 1), width - 1, Math.abs(height) - 1);
			g2dBiTemp.setColor(secondColor);
			g2dBiTemp.drawRect(sx, (sy + height), width, Math.abs(height));
		}
		else
		{
			g2dBiTemp.fillRect(sx + 1, sy + 1, (fx - sx - 1), (fy - sy - 1));
			g2dBiTemp.setColor(secondColor);
			g2dBiTemp.drawRect(sx, sy, (fx - sx), (fy - sy));
		}
		drawPad.repaint();
	}
	
	private void mouseReleasedSquareCode()
	{
		biOrig = null;
		g2dBiTemp.dispose();
		g2dBiTemp = null;
	}
	
	private void mousePressedSquareCode()
	{
		biOrig = new BufferedImage(drawPadSize.width, drawPadSize.height, biDrawPad.getType());
		Graphics2D g2dBiOrig = biOrig.createGraphics();
		g2dBiOrig.drawImage(biDrawPad, 0, 0, null);
		g2dBiOrig.dispose();
		g2dBiOrig = null;
		g2dBiTemp = biDrawPad.createGraphics();
		secondColor = new Color(255 - firstColor.getRed(), 255 - firstColor.getGreen(), 255 - firstColor.getBlue());
	}
	private void mouseDraggedCircleCode()
	{
		
		double width = fx - sx;
		double height = fy - sy;
		
		g2dBiTemp.drawImage(biOrig, 0, 0, null);
		g2dBiTemp.setColor(secondColor);
		if(width < 0 && height < 0)
		{
			Ellipse2D.Double oval = new Ellipse2D.Double((sx + width), (sy + height), Math.abs(width), Math.abs(height)); 
			g2dBiTemp.draw(oval);
			g2dBiTemp.setColor(firstColor);
			g2dBiTemp.fill(oval);
		}
		else if(width < 0 && height > 0)
		{
			Ellipse2D.Double oval = new Ellipse2D.Double((sx + width), sy, Math.abs(width), height);
			g2dBiTemp.draw(oval);
			g2dBiTemp.setColor(firstColor);
			g2dBiTemp.fill(oval);
		}
		else if(width > 0 && height < 0)
		{
			Ellipse2D.Double oval = new Ellipse2D.Double(sx, (sy + height), width, Math.abs(height));
			g2dBiTemp.draw(oval);
			g2dBiTemp.setColor(firstColor);
			g2dBiTemp.fill(oval);
		}
		else
		{
			Ellipse2D.Double oval = new Ellipse2D.Double(sx, sy, (fx - sx), (fy - sy));
			g2dBiTemp.draw(oval);
			g2dBiTemp.setColor(secondColor);
			g2dBiTemp.fill(oval);
		}
		drawPad.repaint();
	}
	
	private void mouseReleasedCircleCode()
	{
		biOrig = null;
		g2dBiTemp.dispose();
		g2dBiTemp = null;
	}
	
	private void mousePressedCircleCode()
	{
		biOrig = new BufferedImage(drawPadSize.width, drawPadSize.height, biDrawPad.getType());
		Graphics2D g2dBiOrig = biOrig.createGraphics();
		g2dBiOrig.drawImage(biDrawPad, 0, 0, null);
		g2dBiOrig.dispose();
		g2dBiOrig = null;
		g2dBiTemp = biDrawPad.createGraphics();
		secondColor = new Color(255 - firstColor.getRed(), 255 - firstColor.getGreen(), 255 - firstColor.getBlue());
	}
	public void setAction(int act)
	{
		action = act;
	}
	
	public BufferedImage getBiDrawPad()
	{
		return biDrawPad;
	}
	
	public Dimension getDrawPadSize()
	{
		return drawPadSize;
	}
	
	public Color getFirstColor()
	{
		return firstColor;
	}
	
	public Color getSecondColor()
	{
		return secondColor;
	}
	
	public void setFirstColor(Color fc)
	{
		firstColor = fc;
	}
	
	public void setSecondColor(Color sc)
	{
		secondColor = sc;
	}
	
	public void setBiDrawPad(BufferedImage biDp)
	{
		biDrawPad = biDp;
		drawPad.repaint();
	}
}