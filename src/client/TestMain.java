package client;

import domain.DataEntity;
import domain.Executer;
import domain.ExecuterWithDesktop;
import domain.DataLoader;
import repository.DataLoaderByFile;
import view.MasterView;

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
