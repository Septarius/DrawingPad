package drawingpad.alpha;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/*
 * Programmer: Stephen Dighans Date: Jan 28, 2008 Program File: dpSideBar.java
 * Purpose:
 */
public class SideBar implements ActionListener {

    private ImageIcon[] iiSideBar;
    private JButton[] butSideBar;
    private JButton butClear;
    private JPanel sideBarButtons, sideBar;
    private Box[] sideBarCols;
    private JFrame frame;
    private DrawingPad drawingPad;
    final static int IMAGENUM = 4;
    final static int COLNUM = 2;

    public SideBar(JFrame fram, DrawingPad dp) {
        int counter = 0;
        frame = fram;
        drawingPad = dp;
        iiSideBar = new ImageIcon[IMAGENUM];
        butSideBar = new JButton[IMAGENUM];
        sideBarCols = new Box[COLNUM];
        sideBarButtons = new JPanel();
        sideBar = new JPanel();
        butClear = new JButton("Clear");

        sideBar.setLayout(new BoxLayout(sideBar, BoxLayout.PAGE_AXIS));
        sideBarButtons.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEtchedBorder(),
                sideBarButtons.getBorder()));
        sideBarButtons.setLayout(new GridLayout(1, 2));
        for (int i = 0; i < sideBarCols.length; i++) {
            sideBarCols[i] = new Box(BoxLayout.PAGE_AXIS);
            sideBarButtons.add(sideBarCols[i]);
        }

        for (int j = 0; j < iiSideBar.length; j++) {
            iiSideBar[j] = new ImageIcon(frame.getToolkit().createImage(getClass().getResource("but" + (j + 1) + ".gif")));
            butSideBar[j] = new JButton(iiSideBar[j]);
            Insets in = butSideBar[j].getBorder().getBorderInsets(butSideBar[j]);
            butSideBar[j].setPreferredSize(new Dimension(iiSideBar[j].getIconWidth() + in.left, iiSideBar[j].getIconHeight() + in.top));
            butSideBar[j].addActionListener(this);
            if (counter == sideBarCols.length) {
                counter = 0;
            }
            sideBarCols[counter].add(butSideBar[j]);
            counter++;
        }
        sideBar.add(sideBarButtons);
        butClear.setAlignmentX(JButton.CENTER_ALIGNMENT);

        sideBar.add(butClear);
        butClear.addActionListener(this);

        butSideBar[0].setToolTipText("Line TOOOOOL");
        butSideBar[1].setToolTipText("Pencil TOOOOOL");
        butSideBar[2].setToolTipText("Rectangle TOOOOOOL");
        butSideBar[3].setToolTipText("TOOOOOOOL that doesn't work");

    }

    public JPanel getSideBar() {
        return sideBar;
    }

    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < butSideBar.length; i++) {
            if (e.getSource().equals(butSideBar[i])) {
                drawingPad.setAction(i);
            }
        }
        if (e.getSource().equals(butClear)) {
            Graphics2D g2dBiDrawPad = drawingPad.getBiDrawPad().createGraphics();
            g2dBiDrawPad.setColor(Color.white);
            g2dBiDrawPad.fillRect(0, 0, drawingPad.getBiDrawPad().getWidth(), drawingPad.getBiDrawPad().getHeight());
            g2dBiDrawPad.dispose();
            g2dBiDrawPad = null;
            frame.repaint();
        }
    }
}