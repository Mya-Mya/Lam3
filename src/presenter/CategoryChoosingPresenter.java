package presenter;

import domain.DataEntity;
import domain.DataEntityListener;

public class CategoryChoosingPresenter implements DataEntityListener {
    private DataEntity entity;
    public CategoryChoosingPresenter(DataEntity entity){
        this.entity=entity;
        entity.addDataEntityListener(this);
    }

    @Override
    public void onDataEntityReset() {
        
    }
}
