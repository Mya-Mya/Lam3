package view;

import domain.valueobject.CategoryId;
import presenter.CategoryViewModel;

/**
 * IProductListViewにて表示するプロダクトのカテゴリを選ぶチューザーを表示する。
 * この具象クラスはOnSelectShowingCategoryInteractorに依存することになる。よって、コンストラクタでこれを得ること。
 * @see interactor.OnSelectShowingCategoryInteractor
 * @author mya
 */
public interface ICategoryChoosingView {
    /**
     * カテゴリをチューザーに追加する。
     * このカテゴリが画面上で選択された場合、これに対応するcategoryIdを特定のインタラクタに通知する必要があるため、この2つの引数は必ず対応させておくこと。
     * @see interactor.OnSelectShowingCategoryInteractor
     * @param mCategoryViewModel
     * @param categoryId
     */
    void addCategory(CategoryViewModel mCategoryViewModel, CategoryId categoryId);

    /**
     * すべてのカテゴリーの一覧を削除する。紐づけられているCategoryIdも同じように削除すること。
     */
    void clearAllCategory();
}
