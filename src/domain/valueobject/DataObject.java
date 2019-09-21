package domain.valueobject;

import java.util.ArrayList;
import java.util.List;

public class DataObject {
    private List<Category> categoryList;

    public DataObject(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public DataObject() {
        this(new ArrayList<>());
    }

    public void addCategory(Category c) {
        categoryList.add(c);
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }
}
