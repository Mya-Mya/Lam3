package interactor;

import domain.DataEntity;
import domain.error.ErrorHistory;

public class OnUpdateButtonPushInteractorImpl implements OnUpdateButtonPushInteractor {
    private DataEntity entity;
    public OnUpdateButtonPushInteractorImpl(DataEntity entity){
        this.entity=entity;
    }
    @Override
    public void handle() {
        if (entity == null) {
            ErrorHistory.inst().addError("OnUpdateButtonPushInteractorImpl.handle","entityがnullだった");
        }
        entity.loadData();
    }
}
