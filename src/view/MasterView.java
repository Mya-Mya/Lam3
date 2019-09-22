package view;

import domain.DataEntity;
import domain.Executer;
import interactor.OnReallyCloseButtonPushInteractor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;

public class MasterView extends JFrame implements WindowFocusListener, WindowListener {

    public MasterView() {
        super("Lam3");
        setUndecorated(true);
        setPreferredSize(new Dimension(800,500));
        setIconImage(new ImageIcon("other/icon.png").getImage());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);



        addWindowListener(this);
        pack();
        setVisible(true);
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
        System.out.println("MasterView.windowClosing");
        OnReallyCloseButtonPushInteractor mOnReallyCloseButtonPushInteractor=new OnReallyCloseButtonPushInteractor() {
            @Override
            public void handle() {
                System.exit(0);
            }
        };
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
