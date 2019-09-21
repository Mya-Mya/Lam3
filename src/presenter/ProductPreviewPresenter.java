package presenter;

import domain.valueobject.ProductId;
import view.IProductPreviewView;

public class ProductPreviewPresenter {
    private IProductPreviewView mProductPreviewView;
    public ProductPreviewPresenter(){
    }
    public ProductPreviewPresenter(IProductPreviewView mProductPreviewView){
        setView(mProductPreviewView);
    }
    public void setView(IProductPreviewView mProductPreviewView){
        this.mProductPreviewView=mProductPreviewView;
        mProductPreviewView.showNothingToShow();
    }
    public void onShowNothing(){
        mProductPreviewView.showNothingToShow();
    }
    public void onShowSpecificProduct(ProductId productId){
        ProductPreviewViewModel mProductPreviewViewModel=new ProductPreviewViewModel(

        )
        mProductPreviewView.showProductPreview();
    }
}
