package view;

import domain.DataEntity;
import domain.Executer;
import interactor.OnLocateOnScreenButtonPushInteractor;
import interactor.OnReallyCloseButtonPushInteractor;
import ui.Lam3UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;

public class MasterView extends JFrame implements WindowFocusListener, WindowListener {

    public MasterView() {
        super("Lam3");
        setUndecorated(true);
        setIconImage(new ImageIcon("other/icon.png").getImage());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        //setResizable(false);

        OnLocateOnScreenButtonPushInteractor mOnLocateOnScreenButtonPushInteractor=new OnLocateOnScreenButtonPushInteractor() {
            @Override
            public void handle() {
                GraphicsEnvironment env=java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment();
                Rectangle rect=env.getMaximumWindowBounds();
                setPreferredSize(new Dimension(rect.width,rect.height));
                setLocation(rect.x,rect.y);
            }
        };
        mOnLocateOnScreenButtonPushInteractor.handle();

        JPanel p=Lam3UI.createPanel();
        p.setBackground(Lam3UI.black);

        JLabel l=Lam3UI.createLabel();
        l.setText("Lam3.view.MasterView");
        l.setFont(Lam3UI.bigFont);
        p.add(l,BorderLayout.SOUTH);

        add(p,BorderLayout.CENTER);

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
        mOnReallyCloseButtonPushInteractor.handle();
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
