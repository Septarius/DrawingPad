package drawingpad.alpha;


/*
 * Programmer: Stephen Dighans
 * Date: Jan 13, 2008
 * Program File: dpCanvas.java
 * Purpose: Canvas Class for DrawingPad 
 */

import javax.swing.*;
import java.awt.*;

public class DrawArea extends JPanel
{
	DrawingPad drawPd;
	
	public DrawArea(DrawingPad dp)
	{
		drawPd = dp;
		this.setIgnoreRepaint(true);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(drawPd.getBiDrawPad(), 0, 0, null);
	}
}