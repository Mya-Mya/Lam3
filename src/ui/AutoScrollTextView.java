package ui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AutoScrollTextView extends JScrollPane implements ActionListener {
    private JLabel lText;
    private Timer timer;
    private BoundedRangeModel horizontalScrollBarModel;

    public AutoScrollTextView(String text) {
        super();
        lText = Lam3UI.createLabel();
        lText.setBackground(Lam3UI.orange);
        timer = new Timer(50, this);
        setViewportView(lText);
        setText(text);
        horizontalScrollBarModel = horizontalScrollBar.getModel();
        horizontalScrollBarModel.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                BoundedRangeModel model = horizontalScrollBar.getModel();
                if (model.getExtent() < model.getMaximum()) {//スクロールする必要がある
                    if (!timer.isRunning()) {
                        state = State.HeadStopping;
                        timer.start();
                    }
                } else {//スクロールする必要がない
                    if (timer.isRunning()) {
                        timer.stop();
                    }
                }
            }
        });
    }

    public AutoScrollTextView() {
        this("");
    }

    public void setText(String text) {
        lText.setText(text);
    }

    @Override
    public void setFont(Font font) {
        super.setFont(font);
        if (lText != null) {
            lText.setFont(font);
        }
    }

    @Override
    public void setForeground(Color fg) {
        super.setForeground(fg);
        if (lText != null) {
            lText.setForeground(fg);
        }
    }

    @Override
    public void setBackground(Color bg) {
        super.setBackground(bg);
        viewport.setBackground(bg);
    }

    private int clock = 0;
    private int headStoppingTime = 50;
    private int deltaValue = 5;
    private int footStoppingTime = 40;

    public enum State {
        HeadStopping,
        Moving,
        FootStopping
    }

    private State state = State.HeadStopping;

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (state) {
            case HeadStopping:
                clock++;
                if (headStoppingTime < clock) {
                    clock = 0;
                    state = State.Moving;
                }
                break;
            case Moving:
                int value = horizontalScrollBarModel.getValue();
                value += deltaValue;
                if (value > horizontalScrollBarModel.getMaximum() - horizontalScrollBarModel.getExtent()) {
                    value = horizontalScrollBarModel.getMaximum() - horizontalScrollBarModel.getExtent();
                    state = State.FootStopping;
                }
                horizontalScrollBarModel.setValue(value);
                break;
            case FootStopping:
                clock++;
                if(footStoppingTime<clock){
                    clock=0;
                    horizontalScrollBarModel.setValue(horizontalScrollBarModel.getMinimum());
                    state=State.HeadStopping;
                }
                break;
        }

    }
}
