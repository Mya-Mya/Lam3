package presenter;

import domain.DataEntity;
import domain.DataEntityListener;
import domain.valueobject.Category;
import domain.valueobject.CategoryId;
import view.ICategoryChoosingView;

public class CategoryChoosingPresenter implements DataEntityListener {
    private DataEntity entity;
    private ICategoryChoosingView mCategoryChoosingView;
    public CategoryChoosingPresenter(DataEntity entity){
        this(entity,null);
    }
    public CategoryChoosingPresenter(DataEntity entity,ICategoryChoosingView mCategoryChoosingView){
        this.entity=entity;
        entity.addDataEntityListener(this);
        setView(mCategoryChoosingView);
    }
    public void setView(ICategoryChoosingView mCategoryChoosingView){
        if(mCategoryChoosingView==null)return;
        this.mCategoryChoosingView=mCategoryChoosingView;
        initialView();
    }

    @Override
    public void onDataEntityReset() {
        initialView();
    }

    private void initialView(){
        mCategoryChoosingView.clearAllCategory();
        for(CategoryId categoryId:entity.getCategoryIdList()){
            Category category=entity.getCategoryById(categoryId);
            if(category==null)continue;

            CategoryViewModel mCategoryViewModel=new CategoryViewModel(
                    category.getImage()
                    ,category.getTitle()
            ) ;
            mCategoryChoosingView.addCategory(mCategoryViewModel,categoryId);
        }
    }
}
