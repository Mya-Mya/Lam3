package interactor;

import domain.error.ErrorHistory;
import domain.valueobject.ProductId;
import presenter.ProductPreviewPresenter;

public class OnSelectProductCellInteractorImpl implements OnSelectProductCellInteractor {
    private ProductPreviewPresenter mProductPreviewPresenter;

    public OnSelectProductCellInteractorImpl(ProductPreviewPresenter mProductPreviewPresenter){
        this.mProductPreviewPresenter=mProductPreviewPresenter;
    }

    @Override
    public void onSelectProductCell(ProductId selectedProductId) {
        if (mProductPreviewPresenter == null) {
            ErrorHistory.inst().addError("OnSelectProductCellInteractorImpl.onSelectProductCell","mProductPreviewPresenterがnullだった");
            return;
        }
        mProductPreviewPresenter.onShowSpecificProduct(selectedProductId);
    }

    @Override
    public void onUnselectProductCell() {
        if (mProductPreviewPresenter == null) {
            ErrorHistory.inst().addError("OnSelectProductCellInteractorImpl.onUnselectProductCell","mProductPreviewPresenterがnullだった");
        }
        mProductPreviewPresenter.onShowNothing();
    }
}
