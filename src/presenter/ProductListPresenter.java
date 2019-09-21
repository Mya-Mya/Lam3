package presenter;

import domain.DataEntity;
import domain.DataEntityListener;
import domain.valueobject.CategoryId;
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
        onShowingCategoryChosen(categoryId);
    }

    @Override
    public void onDataEntityReset() {
        onShowingCategoryChosen(entity.getAllCategoryId());
    }
}
