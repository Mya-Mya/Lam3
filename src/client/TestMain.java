package client;

import domain.DataEntity;
import domain.Executer;
import domain.ExecuterWithDesktop;
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
        Executer executer=new ExecuterWithDesktop();
        DataLoader loader=new DataLoaderByFile();
        DataEntity entity=new DataEntity(loader);
        entity.loadData();

        new MasterView(entity,executer);
    }
}
