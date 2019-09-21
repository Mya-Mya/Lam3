package interactor;

import domain.valueobject.ProductId;
import presenter.ProductPreviewPresenter;

public class OnSelectProductCellInteractorImpl implements OnSelectProductCellInteractor {
    private ProductPreviewPresenter mProductPreviewPresenter;

    public OnSelectProductCellInteractorImpl(ProductPreviewPresenter mProductPreviewPresenter){
        this.mProductPreviewPresenter=mProductPreviewPresenter;
    }

    @Override
    public void onSelectProductCell(ProductId selectedProductId) {
        mProductPreviewPresenter.onShowSpecificProduct(selectedProductId);
    }

    @Override
    public void onUnselectProductCell() {
        mProductPreviewPresenter.onShowNothing();
    }
}
