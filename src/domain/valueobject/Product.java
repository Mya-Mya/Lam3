package domain.valueobject;

import javax.swing.*;
import java.io.File;

public class Product {
    private String title;
    private Productor productor;
    private String detail;
    private ImageIcon image;
    private File entrypt;
    private ProductId id;
    public Product(ProductId id,String title,Productor productor,String detail,ImageIcon image,File entrypt){
        this.id=id;
        this.title=title;
        this.productor=productor;
        this.detail=detail;
        this.image=image;
        this.entrypt=entrypt;
    }

    public String getTitle() {
        return title;
    }

    public Productor getProductor() {
        return productor;
    }

    public String getDetail() {
        return detail;
    }

    public ImageIcon getImage() {
        return image;
    }

    public File getEntrypt() {
        return entrypt;
    }

    public ProductId getId() {
        return id;
    }
}
