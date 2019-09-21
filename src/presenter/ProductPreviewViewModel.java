package presenter;

import javax.swing.*;

/**
 * IProductPreviewViewでの描画内容を表すDTO。
 * @see view.IProductPreviewView
 * @author mya
 */
public class ProductPreviewViewModel {
    final public ImageIcon productImage;
    final public String title;
    final public ImageIcon categoryImage;
    final public String productor;
    final public String detail;
    public ProductPreviewViewModel(ImageIcon productImage,String title,ImageIcon categoryImage,String productor,String detail){
        this.productImage=productImage;
        this.title=title;
        this.categoryImage=categoryImage;
        this.productor=productor;
        this.detail=detail;
    }
}
