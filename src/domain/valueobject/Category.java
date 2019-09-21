package domain.valueobject;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Category {
    private List<Product> productList;
    private String title;
    private String detail;
    private ImageIcon image;
    private CategoryId id;

    public Category(CategoryId id,List<Product> productList, String title, String detail, ImageIcon image) {
        this.id=id;
        this.productList = productList;
        this.title = title;
        this.detail = detail;
        this.image = image;
    }

    public Category(CategoryId id,String title, String detail, ImageIcon image) {
        this(id,new ArrayList<Product>(), title, detail, image);
    }

    public void addProducts(List<Product> p) {
        productList.addAll(p);
    }

    public void addProduct(Product p) {
        productList.add(p);
    }

    public List<Product> getProductList() {
        return productList;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }

    public ImageIcon getImage() {
        return image;
    }

    public CategoryId getId() {
        return id;
    }
}
