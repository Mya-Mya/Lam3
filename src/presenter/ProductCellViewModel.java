package presenter;

import javax.swing.*;
import java.awt.*;

/**
 * IProductListViewにて表示される各プロダクトのセルの描画内容を表すDTO。
 * @see view.IProductListView
 * @author mya
 */
public class ProductCellViewModel {
    final public Image productImage;
    final public String title;
    final public Image categoryImage;
    final public String creator;
    public ProductCellViewModel(Image productImage, String title, Image categoryImage, String creator){
        this.productImage=productImage;
        this.title=title;
        this.categoryImage=categoryImage;
        this.creator=creator;
    }
}
