package ui;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class Lam3ScrollBarUI extends BasicScrollBarUI {
    private final Dimension d = new Dimension();
    @Override protected JButton createDecreaseButton(int orientation) {
        return new JButton() {
            @Override public Dimension getPreferredSize() {
                return d;
            }
        };
    }
    @Override protected JButton createIncreaseButton(int orientation) {
        return new JButton() {
            @Override public Dimension getPreferredSize() {
                return d;
            }
        };
    }


    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {

    }

    @Override
    protected void paintDecreaseHighlight(Graphics g) {

    }

    @Override
    protected void paintIncreaseHighlight(Graphics g) {

    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        Graphics2D g2 = (Graphics2D)g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        Color color = null;
        JScrollBar sb = (JScrollBar)c;
        if(!sb.isEnabled() || thumbBounds.width>thumbBounds.height) {
            return;
        }else if(isDragging) {
            color = Lam3UI.lighterMain;
        }else if(isThumbRollover()) {
            color = Lam3UI.lighterMain;
        }else {
            color = Lam3UI.lighterMain;
        }
        g2.setPaint(color);
        g2.fillRoundRect(thumbBounds.x,thumbBounds.y,thumbBounds.width,thumbBounds.height,1,1);
        g2.dispose();
    }

}
