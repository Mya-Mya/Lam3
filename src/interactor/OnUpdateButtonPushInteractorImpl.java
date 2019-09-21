package interactor;

import domain.DataEntity;

public class OnUpdateButtonPushInteractorImpl implements OnUpdateButtonPushInteractor {
    private DataEntity entity;
    public OnUpdateButtonPushInteractorImpl(DataEntity entity){
        this.entity=entity;
    }
    @Override
    public void handle() {
        entity.loadData();
    }
}
