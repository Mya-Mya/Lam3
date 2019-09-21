package view;

import interactor.OnExecuteButtonPushInteractor;
import presenter.ProductPreviewViewModel;

/**
 * 特定のプロダクトの詳細とその「起動」ボタンを大きく表示する。
 * @author mya
 */
public interface IProductPreviewView {
    /**
     * 表示するプロダクトが何も無い時に呼ばれるので、特定のプロダクトを表示するのではなく、
     * 「ようこそ」とか、「お好きな作品をお選びください」などという適当なメッセージを表示することが妥当。
     */
    void showNothingToShow();

    /**
     * 指定のプロダクトを大きく詳細に表示する。「起動」ボタンは押されたら引数にあるインタラクタに通知すること。
     * このインタラクタはこのメソッドが呼ばれる毎に異なるインスタンスになるので、注意すること。
     * @param mProductPreviewViewModel 描画内容。
     * @param mOnExecuteButtonPushInteractor 「起動」ボタンが押された際に通知すべきインタラクタ。
     */
    void showProductPreview(ProductPreviewViewModel mProductPreviewViewModel, OnExecuteButtonPushInteractor mOnExecuteButtonPushInteractor);
}
