package interactor;

import domain.valueobject.ProductId;

/**
 * IProductListViewにてプロダクトが選ばれたり解除されたりしたらここに連絡するように。
 * @see view.IProductListView
 * @author mya
 */
public interface OnSelectProductCellInteractor {
    /**
     * 画面上でプロダクトが選択された際はこのメソッドを呼ぶこと。
     * @param selectedProductId 選択されたプロダクトを表すID
     */
    void onSelectProductCell(ProductId selectedProductId);
    /**
     * 画面上で選択ているプロダクトがなくなった際はこのメソッドを呼ぶこと。
     */
    void onUnselectProductCell();
}
