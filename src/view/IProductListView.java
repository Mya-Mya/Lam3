package view;

import domain.valueobject.ProductId;
import presenter.ProductCellViewModel;

/**
 * プロダクトを一覧で表示する。
 * この具象クラスはOnSelectProductCellInteractorに依存することになる。よって、コンストラクタでこれを得ること。
 * @see interactor.OnSelectProductCellInteractor
 * @author mya
 */
public interface IProductListView {
    /**
     * プロダクトを一覧の末尾に追加し表示する。
     * このプロダクトが画面上で選択された場合、これに対応するproductIdを特定のインタラクタに通知する必要があるため、この2つの引数は必ず対応させておくこと。
     * @see interactor.OnSelectProductCellInteractor
     * @param mProductCellViewModel 描画内容。
     * @param productId このプロダクトを表すID。
     */
    void addProductCell(ProductCellViewModel mProductCellViewModel, ProductId productId);

    /**
     * すべてのプロダクトの一覧を削除する。紐づけられているProductIdも同じように削除すること。
     */
    void clearAllProductCell();
}
