package client;

import domain.DataEntity;
import domain.Executer;
import domain.ExecuterWithDesktopAndProcessBuilder;
import domain.DataLoader;
import repository.DataLoaderByFile;
import view.MasterView;

public class TestMain implements Main{
    @Override
    public void launch() {
        Executer executer=new ExecuterWithDesktopAndProcessBuilder();
        DataLoader loader=new DataLoaderByFile();
        DataEntity entity=new DataEntity(loader);
        entity.loadData();

        new MasterView(entity,executer);
    }
}
