package interactor;

import domain.error.ErrorHistory;
import domain.valueobject.CategoryId;
import presenter.ProductListPresenter;

public class OnSelectShowingCategoryInteractorImpl implements OnSelectShowingCategoryInteractor {
    private ProductListPresenter mProductListPresenter;
    public OnSelectShowingCategoryInteractorImpl(ProductListPresenter mProductListPresenter){
        this.mProductListPresenter=mProductListPresenter;
    }
    @Override
    public void handle(CategoryId categoryId) {
        if (mProductListPresenter == null) {
            ErrorHistory.inst().addError("OnSelectShowingCategoryInteractorImpl.handle","categoryIdがnullだった");
            return;
        }
        mProductListPresenter.onShowingCategoryChosen(categoryId);
    }
}
