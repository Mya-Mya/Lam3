package interactor;

/**
 * ウィンドウを画面に合わせたいときはここに連絡するように。
 * @author mya
 */
public interface OnLocateOnScreenButtonPushInteractor {
    /**
     * ウィンドウを画面に合わせることになったら呼ぶこと。
     */
    void handle();
}
