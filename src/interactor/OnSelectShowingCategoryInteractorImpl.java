package interactor;

import domain.valueobject.CategoryId;
import presenter.ProductListPresenter;

public class OnSelectShowingCategoryInteractorImpl implements OnSelectShowingCategoryInteractor {
    private ProductListPresenter mProductListPresenter;
    public OnSelectShowingCategoryInteractorImpl(ProductListPresenter mProductListPresenter){
        this.mProductListPresenter=mProductListPresenter;
    }
    @Override
    public void handle(CategoryId categoryId) {
        mProductListPresenter.onShowingCategoryChosen(categoryId);
    }
}
