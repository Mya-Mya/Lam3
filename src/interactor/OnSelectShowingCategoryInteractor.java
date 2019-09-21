package interactor;

import domain.valueobject.CategoryId;

/**
 * ICategoryChoosingViewにて表示したいカテゴリが選ばれたらここに連絡するように。
 * @see view.ICategoryChoosingView
 * @author mya
 */
public interface OnSelectShowingCategoryInteractor {
    /**
     * カテゴリが選ばれたらこれを呼ぶこと。
     * @param categoryId 選択されたカテゴリを表すID。
     */
    void handle(CategoryId categoryId);
}
