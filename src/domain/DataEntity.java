package domain;

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

    public Product getProductById(ProductId id){
        for(Category c:dataObject.getCategoryList()){
            for(Product p:c.getProductList()){
                if(p.getId().equals(id)){
                    return p;
                }
            }
        }
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
