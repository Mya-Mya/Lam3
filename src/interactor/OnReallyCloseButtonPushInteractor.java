package interactor;

/**
 * Lam3を本当に終了したいときはここに連絡するように。
 * @author mya
 */
public interface OnReallyCloseButtonPushInteractor {
    /**
     * Lam3を終了することになったら呼ぶこと。
     */
    void handle();
}
