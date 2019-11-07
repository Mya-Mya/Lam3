package presenter;

import javax.swing.*;
import java.awt.*;

/**
 * ICategoryChoosingViewにて一つのカテゴリーの描画内容を表すDTO。
 * @see view.ICategoryChoosingView
 * @author mya
 */
public class CategoryViewModel {
    public final Image image;
    public final String title;
    public CategoryViewModel(Image image,String title){
        this.image=image;
        this.title=title;
    }
}
