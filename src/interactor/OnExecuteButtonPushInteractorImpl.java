package interactor;

import domain.DataEntity;
import domain.Executer;
import domain.error.ErrorHistory;
import domain.valueobject.Product;
import domain.valueobject.ProductId;

public class OnExecuteButtonPushInteractorImpl implements OnExecuteButtonPushInteractor {
    private Executer executer;
    private Product product;

    public OnExecuteButtonPushInteractorImpl(Executer executer, ProductId productId, DataEntity entity) {
        this.executer = executer;
        this.product = entity.getProductById(productId);
    }

    @Override
    public void handle() {
        if (product == null) {
            ErrorHistory.inst().addError("OnExecuteButtonPushInteractorImpl.handle", "productがnullだった");
            return;
        }
        executer.execute(product);
    }
}
