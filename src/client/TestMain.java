package client;

import domain.DataEntity;
import presenter.CategoryChoosingPresenter;
import repository.DataLoader;
import repository.DataLoaderByFile;
import ui.AutoScrollTextView;
import ui.Lam3UI;

import javax.swing.*;
import java.awt.*;

public class TestMain implements Main{
    @Override
    public void launch() {
        JFrame frame=new JFrame();
        frame.setPreferredSize(new Dimension(500,500));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        AutoScrollTextView v=Lam3UI.createAutoScrollTextView();
        v.setText("ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789");
        v.setText("<------------------>");
        v.setForeground(Lam3UI.white);
        v.setBackground(Lam3UI.darkgray);

        frame.add(v);
        frame.pack();
        frame.setVisible(true);
    }
}
