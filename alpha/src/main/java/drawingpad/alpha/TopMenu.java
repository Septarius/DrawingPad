package drawingpad.alpha;

/*
 * Programmer: Stephen Dighans
 * Date: Jan 2, 2008
 * Program File: MenuSystem.java
 * Purpose: Menu System and Text Area
 */
import java.awt.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;
import java.awt.image.*;
import javax.swing.*;

public class TopMenu implements ActionListener
{
	private DrawingPad frame;
	private JMenuBar mnuBar;
	
	public TopMenu(DrawingPad fram)
	{
		frame = fram;
		
		//create an instance of the menu
		mnuBar = new JMenuBar();
		
		//construct and populate the File Menu
		JMenu mnuFile = new JMenu("File", true);
		mnuBar.add(mnuFile);
			JMenuItem mnuFileNew = new JMenuItem("New");
				mnuFile.add(mnuFileNew);
			JMenuItem mnuFileOpen = new JMenuItem("Open");
				mnuFile.add(mnuFileOpen);
			JMenuItem mnuFileClose = new JMenuItem("Close");
				mnuFile.add(mnuFileClose);
			mnuFile.insertSeparator(3);
			JMenuItem mnuFileSave = new JMenuItem("Save");
				mnuFile.add(mnuFileSave);
			JMenuItem mnuFileSaveAs = new JMenuItem("Save As");
				mnuFile.add(mnuFileSaveAs);
			mnuFile.insertSeparator(6);
			JMenuItem mnuFileExit = new JMenuItem("Exit");
				mnuFile.add(mnuFileExit);
			
		//construct and populate the Edit menu
		JMenu mnuEdit = new JMenu("Edit", true);
		mnuBar.add(mnuEdit);
			JMenuItem mnuEditCut = new JMenuItem("Cut");
				mnuEdit.add(mnuEditCut);
			JMenuItem mnuEditCopy = new JMenuItem("Copy");
				mnuEdit.add(mnuEditCopy);
			JMenuItem mnuEditPaste = new JMenuItem("Paste");
				mnuEdit.add(mnuEditPaste);
		
		//construct and populate the Tools menu
		JMenu mnuTools = new JMenu("Tools", true);
		mnuBar.add(mnuTools);
			JMenuItem mnuToolsOptions = new JMenuItem("Options");
				mnuTools.add(mnuToolsOptions);
		
		//construct and populate the About menu
		JMenu mnuHelp = new JMenu("Help", true);
			mnuBar.add(mnuHelp);
			JMenuItem mnuHelpHelpTopics = new JMenuItem("Help Topics");
				mnuHelp.add(mnuHelpHelpTopics);
			mnuHelp.insertSeparator(1);
			JMenuItem mnuHelpAbout = new JMenuItem("About");
				mnuHelp.add(mnuHelpAbout);
		//end of mnuBar
		
		//add the ActionListener to each menu item
		mnuFileNew.addActionListener(this);
		mnuFileOpen.addActionListener(this);
		mnuFileClose.addActionListener(this);
		mnuFileSave.addActionListener(this);
		mnuFileSaveAs.addActionListener(this);
		mnuFileExit.addActionListener(this);
		
		mnuEditCut.addActionListener(this);
		mnuEditCopy.addActionListener(this);
		mnuEditPaste.addActionListener(this);
		
		mnuToolsOptions.addActionListener(this);
		
		mnuHelpHelpTopics.addActionListener(this);
		mnuHelpAbout.addActionListener(this);
		
		//assign an ActionCommand to each menu item
		mnuFileNew.setActionCommand("New");
		mnuFileOpen.setActionCommand("Open");
		mnuFileClose.setActionCommand("Close");
		mnuFileSave.setActionCommand("Save");
		mnuFileSaveAs.setActionCommand("Save As");
		mnuFileExit.setActionCommand("Exit");
		
		mnuEditCut.setActionCommand("Cut");
		mnuEditCopy.setActionCommand("Copy");
		mnuEditPaste.setActionCommand("Paste");
		
		mnuToolsOptions.setActionCommand("Options");
		
		mnuHelpHelpTopics.setActionCommand("Help Topics");
		mnuHelpAbout.setActionCommand("About");

	}

	public void actionPerformed(ActionEvent e)
	{
		//Array of Frames
		Frame[] frames = JFrame.getFrames();
		//Test Menu Item Clicks
		String arg = e.getActionCommand();
		//New File
		File file;
		JFileChooser fileChoose;
		//File Menu
		if(arg.equals("New"))
		{
			//New MenuItem Code
			DrawingPad newDP = new DrawingPad();
			newDP.setBounds(frames[frames.length - 1].getX() + 25, frames[frames.length - 1].getY() + 25, newDP.getPreferredSize().width, newDP.getPreferredSize().height);
			newDP.setTitle("DrawingPad - Image " + (frames.length + 1));
			newDP.setResizable(false);
			newDP.setVisible(true);
			//End New MenuItem Code
		}
		
		else if(arg.equals("Open"))
		{
			//Open MenuItem Code
			fileChoose = new JFileChooser();
			int returnVal = fileChoose.showOpenDialog(frame);
			if(returnVal == JFileChooser.APPROVE_OPTION)
			{
				BufferedImage openImage;
				DrawingPad newDP = new DrawingPad();
				Dimension dpSize = newDP.getDrawPadSize();
				newDP.setBounds(frames[frames.length - 1].getX() + 25, frames[frames.length - 1].getY() + 25, newDP.getPreferredSize().width, newDP.getPreferredSize().height);
				newDP.setTitle("DrawingPad");
				file = fileChoose.getSelectedFile();
				try
				{
					openImage = new BufferedImage(dpSize.width, dpSize.height, newDP.getBiDrawPad().getType());
					Graphics2D g2dOpenImage = openImage.createGraphics();
					g2dOpenImage.drawImage(ImageIO.read(file), 0, 0, null);
					newDP.setBiDrawPad(openImage);
					newDP.setResizable(false);
					newDP.setVisible(true);
				}
				catch(IOException ex)
				{
					newDP.dispose();
					JOptionPane.showMessageDialog(newDP, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			//End Open MenuItem Code
		}
		
		else if(arg.equals("Close"))
		{
			//Close MenuItem Code
			frame.dispose();
			//End Close MenuItem Code
		}
		
		else if(arg.equals("Save"))
		{
			//Save MenuItem Code
			
			//End Save MenuItem Code
		}
		
		else if(arg.equals("Save As"))
		{
			//Save As MenuItem Code
			fileChoose = new JFileChooser();
			int returnVal = fileChoose.showSaveDialog(frame);
			if(returnVal == JFileChooser.APPROVE_OPTION)
			{
				file = fileChoose.getSelectedFile();
				try
				{
					file.createNewFile();
					ImageIO.write(frame.getBiDrawPad(), "png", file);
				}
				catch(IOException ex)
				{
					JOptionPane.showMessageDialog(frame, ex.toString(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
			//End Save As MenuItem Code
		}
		
		else if(arg.equals("Exit"))
		{
			//Exit MenuItem Code
			System.exit(0);
			//End Exit MenuItem Code
		}
		//End File Menu
		//Edit Menu
		else if(arg.equals("Cut"))
		{
			//Cut MenuItem Code
			JOptionPane.showMessageDialog(frame, "Feature not currently Implemented", "Error", JOptionPane.ERROR_MESSAGE);
			//End Cut MenuItem Code
		}
		
		else if(arg.equals("Copy"))
		{
			//Copy MenuItem Code
			JOptionPane.showMessageDialog(frame, "Feature not currently Implemented", "Error", JOptionPane.ERROR_MESSAGE);
			//End Copy MenuItem Code
		}
		
		else if(arg.equals("Paste"))
		{
			//Paste MenuItem Code
			
			//End Paste MenuItem Code
		}
		//End Edit Menu
		//Tools Menu
		else if(arg.equals("Options"))
		{
			//Options MenuItem Code
			JOptionPane.showMessageDialog(frame, "Feature not currently Implemented", "Error", JOptionPane.ERROR_MESSAGE);
			//End Options MenuItem Code
		}
		//End Tools Menu
		//Help Menu
		else if(arg.equals("Help Topics"))
		{
			//Help Topics Code
			JOptionPane.showMessageDialog(frame, "Feature not currently Implemented", "Error", JOptionPane.ERROR_MESSAGE);
			//End Help Topics MenuItem Code
		}
		
		else if(arg.equals("About"))
		{
			//About MenuItem Code
			JOptionPane.showMessageDialog(frame, "Feature not currently Implemented", "Error", JOptionPane.ERROR_MESSAGE);
			//End About MenuItem Code
		}
		//End Help Menu
	}
	
	public JMenuBar getMnuBar()
	{
		return mnuBar;
	}
}