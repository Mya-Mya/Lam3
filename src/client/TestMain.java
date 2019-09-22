package client;

import domain.DataEntity;
import presenter.CategoryChoosingPresenter;
import repository.DataLoader;
import repository.DataLoaderByFile;
import ui.AutoScrollTextView;
import ui.Lam3UI;
import view.MasterView;

import javax.swing.*;
import java.awt.*;

public class TestMain implements Main{
    @Override
    public void launch() {
        new MasterView();
    }
}
