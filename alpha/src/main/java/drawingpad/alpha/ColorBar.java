package drawingpad.alpha;


/*
 * Programmer: Stephen Dighans Date: Feb 4, 2008 Program File: ColorBar.java
 * Purpose:
 */
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ColorBar implements ChangeListener {

    private JPanel clrBar;
    private JColorChooser cc;
    private Color selColor;
    private DrawingPad drawPad;

    public ColorBar(JFrame fram, DrawingPad dp, JPanel sb) {
        drawPad = dp;
        Box boxCC = new Box(BoxLayout.X_AXIS);
        clrBar = new JPanel();
        cc = new JColorChooser(dp.getFirstColor());
        cc.setPreviewPanel(new JPanel());
        AbstractColorChooserPanel[] currPanels = cc.getChooserPanels();
        for (int i = 0; i < currPanels.length; i++) {
            String clpName = currPanels[i].getClass().getName();
            if (clpName.equals("javax.swing.colorchooser.DefaultRGBChooserPanel")) {
                cc.removeChooserPanel(currPanels[i]);
            } else if (clpName.equals("javax.swing.colorchooser.DefaultHSBChooserPanel")) {
                cc.removeChooserPanel(currPanels[i]);
            }
        }
        cc.getSelectionModel().addChangeListener(this);
        clrBar.setLayout(new BoxLayout(clrBar, BoxLayout.X_AXIS));
        boxCC.setMaximumSize(new Dimension(425, 235));
        boxCC.add(cc);

        boxCC.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEtchedBorder(),
                boxCC.getBorder()));

        Component spcBox = Box.createRigidArea(new Dimension(sb.getPreferredSize().width, boxCC.getPreferredSize().height));

        clrBar.add(spcBox);
        clrBar.add(boxCC);
    }

    public JPanel getClrBar() {
        return clrBar;
    }

    public void stateChanged(ChangeEvent e) {
        selColor = cc.getColor();
        drawPad.setFirstColor(selColor);
    }
}