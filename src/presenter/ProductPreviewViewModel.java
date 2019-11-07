package presenter;

import javax.swing.*;
import java.awt.*;

/**
 * IProductPreviewViewでの描画内容を表すDTO。
 * @see view.IProductPreviewView
 * @author mya
 */
public class ProductPreviewViewModel {
    final public Image productImage;
    final public String title;
    final public Image categoryImage;
    final public String categoryTitle;
    final public String creator;
    final public String detail;
    public ProductPreviewViewModel(Image productImage,String title,Image categoryImage,String categoryTitle,String creator,String detail){
        this.productImage=productImage;
        this.title=title;
        this.categoryImage=categoryImage;
        this.categoryTitle=categoryTitle;
        this.creator=creator;
        this.detail=detail;
    }
}
