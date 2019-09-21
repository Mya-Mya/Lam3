package client;

import domain.DataEntity;
import repository.DataLoader;
import repository.DataLoaderByFile;

public class TestMain implements Main{
    @Override
    public void launch() {
        DataLoader loader=new DataLoaderByFile();
        DataEntity entity=new DataEntity(loader);
        System.out.println(entity.getDataObject());
        entity.loadData();

    }
}
