/*
 * Programmer: Stephen Dighans
 * Date: Jan 10, 2008
 * Program File: DrawingPadMain.java
 * Purpose: Main method for DrawingPad
 */
package drawingpad.alpha;
public class DrawingPadMain
{
	public static void main(String[] args)
	{
		 DrawingPad dp = new DrawingPad();
		 dp.setTitle("DrawingPad");
		 dp.setBounds(0, 0, dp.getPreferredSize().width, dp.getPreferredSize().height);
		 //dp.setResizable(false);
		 dp.setVisible(true);
	}
}