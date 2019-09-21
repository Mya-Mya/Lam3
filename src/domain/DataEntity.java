package domain;

import domain.error.ErrorHistory;
import domain.valueobject.*;
import repository.DataLoader;

import java.util.ArrayList;
import java.util.List;

public class DataEntity {
    private CategoryIdFactory mCategoryIdFactory;
    private ProductIdFactory mProductIdFactory;
    private DataObject dataObject;
    private DataLoader loader;

    private CategoryId mCategoryIdMeansAllCategory;

    public DataEntity(DataLoader loader) {
        this.loader = loader;
        dataObject=new DataObject();
    }

    public void loadData() {
        mCategoryIdFactory=new CategoryIdFactory();
        mProductIdFactory=new ProductIdFactory();
        mCategoryIdMeansAllCategory=mCategoryIdFactory.createNewId();
        dataObject = loader.execute(mCategoryIdFactory,mProductIdFactory);
        fireDataEntityListeners();
    }

    public DataObject getDataObject() {
        return dataObject;
    }

    public Category getCategoryById(CategoryId id){
        for(Category c:dataObject.getCategoryList()){
            if(c.getId().equals(id)){
                return c;
            }
        }
        ErrorHistory.inst().addError("DataEntity.getCategoryById","存在しないidが指定された");
        return null;
    }

    public CategoryId getCategoryIdWhoBelongs(ProductId id){
        for(Category c:dataObject.getCategoryList()){
            for(Product p:c.getProductList()){
                if(p.equals(id)){
                    return c.getId();
                }
            }
        }
        ErrorHistory.inst().addError("DataEntity.getCategoryIdWhoBelongs","存在しないidが指定された");
        return null;
    }

    public Product getProductById(ProductId id){
        for(Category c:dataObject.getCategoryList()){
            for(Product p:c.getProductList()){
                if(p.getId().equals(id)){
                    return p;
                }
            }
        }
        ErrorHistory.inst().addError("DataEntity.getProductById","存在しないidが指定された");
        return null;
    }
    public List<Product>getProductListByCategoryId(CategoryId id){
        for(Category c:dataObject.getCategoryList()){
            if(c.getId().equals(id)){
                return c.getProductList();
            }
        }
        if(mCategoryIdMeansAllCategory.equals(id)){
            List<Product>out=new ArrayList<>();
            for(Category c:dataObject.getCategoryList()){
                out.addAll(c.getProductList());
            }
            return out;
        }
        ErrorHistory.inst().addError("DataEntity.getProductListByCategoryId","存在しないidが指定された");
        return null;
    }
    public List<CategoryId>getCategoryIdList(){
        List<CategoryId>out=new ArrayList<>();
        for(Category c:dataObject.getCategoryList()){
            out.add(c.getId());
        }
        return out;
    }

    public CategoryId getAllCategoryId() {
        return mCategoryIdMeansAllCategory;
    }

    private List<DataEntityListener> listenerList=new ArrayList<>();
    public void addDataEntityListener(DataEntityListener l){
        listenerList.add(l);
    }
    private void fireDataEntityListeners(){
        for(DataEntityListener l:listenerList)l.onDataEntityReset();
    }
}
