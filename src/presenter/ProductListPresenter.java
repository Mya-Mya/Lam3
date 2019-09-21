package presenter;

import domain.DataEntity;
import domain.DataEntityListener;
import domain.valueobject.Category;
import domain.valueobject.CategoryId;
import domain.valueobject.Product;
import domain.valueobject.ProductId;
import view.IProductListView;

public class ProductListPresenter implements DataEntityListener {
    private DataEntity entity;
    private IProductListView mProductListView;
    public ProductListPresenter(DataEntity entity){
        this(entity,null);
    }
    public ProductListPresenter(DataEntity entity,IProductListView mProductListView){
        this.entity=entity;
        entity.addDataEntityListener(this);
        setView(mProductListView);
    }
    public void setView(IProductListView mProductListView){
        if(mProductListView==null)return;
        this.mProductListView=mProductListView;
    }
    public void onShowingCategoryChosen(CategoryId categoryId){
        mProductListView.clearAllProductCell();
        for(Product product:entity.getProductListByCategoryId(categoryId)){
            CategoryId cid=entity.getCategoryIdWhoBelongs(product.getId());
            Category c=entity.getCategoryById(cid);
            ProductCellViewModel mProductCellViewModel=new ProductCellViewModel(
                    product.getImage()
                    ,product.getTitle()
                    ,c.getImage()
                    ,product.getProductor()
            );
            mProductListView.addProductCell(mProductCellViewModel,product.getId());
        }
    }

    @Override
    public void onDataEntityReset() {
        onShowingCategoryChosen(entity.getAllCategoryId());
    }
}
