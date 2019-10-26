package presenter;

import javax.swing.*;

/**
 * IProductListViewにて表示される各プロダクトのセルの描画内容を表すDTO。
 * @see view.IProductListView
 * @author mya
 */
public class ProductCellViewModel {
    final public ImageIcon productImage;
    final public String title;
    final public ImageIcon categoryImage;
    final public String creator;
    public ProductCellViewModel(ImageIcon productImage,String title,ImageIcon categoryImage,String creator){
        this.productImage=productImage;
        this.title=title;
        this.categoryImage=categoryImage;
        this.creator=creator;
    }
}
