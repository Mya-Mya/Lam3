package interactor;

/**
 * IProductPreviewViewにて「起動」ボタンが押されたらここに連絡するように。
 * @see view.IProductPreviewView
 * @author mya
 */
public interface OnExecuteButtonPushInteractor {
    /**
     * 表示中のプロダクトを起動することになったら呼ぶこと。
     */
    void handle();
}
