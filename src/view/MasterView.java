package view;

import domain.DataEntity;
import domain.Executer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;

public class MasterView extends JFrame implements WindowFocusListener, WindowListener {

    public MasterView(DataEntity entity, Executer executer) {
        super("Lam3");
        setPreferredSize(new Dimension(800,500));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);



        addWindowListener(this);
        setVisible(true);
        pack();
    }

    @Override
    public void windowGainedFocus(WindowEvent e) {

    }

    @Override
    public void windowLostFocus(WindowEvent e) {

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
