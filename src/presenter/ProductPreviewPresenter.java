package presenter;

import domain.DataEntity;
import domain.DataEntityListener;
import domain.Executer;
import domain.error.ErrorHistory;
import domain.valueobject.Category;
import domain.valueobject.CategoryId;
import domain.valueobject.Product;
import domain.valueobject.ProductId;
import interactor.OnExecuteButtonPushInteractor;
import interactor.OnExecuteButtonPushInteractorImpl;
import view.IProductPreviewView;

public class ProductPreviewPresenter implements DataEntityListener {
    private DataEntity entity;
    private Executer executer;
    private IProductPreviewView mProductPreviewView;

    public ProductPreviewPresenter(DataEntity entity, Executer executer) {
        this(entity, executer, null);
    }

    public ProductPreviewPresenter(DataEntity entity, Executer executer, IProductPreviewView mProductPreviewView) {
        this.entity = entity;
        this.executer = executer;
        entity.addDataEntityListener(this);
        setView(mProductPreviewView);
    }

    public void setView(IProductPreviewView mProductPreviewView) {
        if (mProductPreviewView == null) return;
        this.mProductPreviewView = mProductPreviewView;
        mProductPreviewView.showNothingToShow();
    }

    public void onShowNothing() {
        mProductPreviewView.showNothingToShow();
    }

    public void onShowSpecificProduct(ProductId productId) {
        CategoryId categoryId = entity.getCategoryIdWhoBelongs(productId);
        Category category = entity.getCategoryById(categoryId);
        if (category == null) {
            ErrorHistory.inst().addError("ProductPreviewPresenter.onShowSpecificProduct", "categoryがnullだった");
            return;
        }
        Product product = entity.getProductById(productId);
        if (product == null) {
            ErrorHistory.inst().addError("ProductPreviewPresenter.onShowSpecificProduct", "productがnullだった");
            return;
        }
        ProductPreviewViewModel mProductPreviewViewModel = new ProductPreviewViewModel(
                product.getImage()
                , product.getTitle()
                , category.getImage()
                , product.getProductor().getFullText()
                , product.getDetail()
        );
        OnExecuteButtonPushInteractor mOnExecuteButtonPushInteractor
                = new OnExecuteButtonPushInteractorImpl(executer, productId, entity);
        mProductPreviewView.showProductPreview(mProductPreviewViewModel, mOnExecuteButtonPushInteractor);
    }

    @Override
    public void onDataEntityReset() {
        if (mProductPreviewView == null) {
            ErrorHistory.inst().addError("ProductPreviewPresenter.onDataEntityReset", "mProductPreviewViewがnullだった");
            return;
        }
        mProductPreviewView.showNothingToShow();

    }
}
