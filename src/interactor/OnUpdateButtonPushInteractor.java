package interactor;

/**
 * IMenuViewにて「更新」ボタンが押されたらここに連絡するように。
 * @see view.IMenuView
 * @author mya
 */
public interface OnUpdateButtonPushInteractor {
    /**
     * 更新する必要があったらこれを呼ぶこと。
     */
    void handle();
}
