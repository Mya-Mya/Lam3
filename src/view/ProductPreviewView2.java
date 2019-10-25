package view;

import interactor.OnExecuteButtonPushInteractor;
import presenter.ProductPreviewViewModel;
import ui.Lam3UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductPreviewView2 extends JPanel implements IProductPreviewView, ActionListener {
	private int width, height;

	private OnExecuteButtonPushInteractor mOnExecuteButtonPushInteractor;
	private JLabel lTitle = Lam3UI.createLabel();
	private JLabel lProductImage = Lam3UI.createLabel();
	private JLabel lProductor = Lam3UI.createLabel();
	private JLabel lCategoryImage = Lam3UI.createLabel();
	private JTextArea tDetail = Lam3UI.createUnEditableTextArea();

	private JButton bLaunch=Lam3UI.createButton();

	public ProductPreviewView2() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width = screenSize.width * 2 / 3;
		height = screenSize.height - 100;
		setLayout(new BorderLayout());
		setBackground(Lam3UI.black);

		lTitle.setFont(Lam3UI.bigFont);


	}


	@Override
	public void showNothingToShow() {
		//if(symbol == null || introduction == null || caution == null) {
			//initializeTopPageInfo();
		//}
		//showProductLayout("ようこそ！コンピュータ部の展示へ！", symbol, introduction, "2019年コンピュータ部員(通称 昆布)",caution);
		tDetail.setText("ここに作品の詳細と起動ボタンが表示されます。");
		lTitle.setVisible(false);
		bLaunch.setVisible(false);
		lProductImage.setVisible(false);
		lCategoryImage.setVisible(false);
		lProductor.setVisible(false);
	}

	@Override
	public void showProductPreview(ProductPreviewViewModel mProductPreviewViewModel,
			OnExecuteButtonPushInteractor mOnExecuteButtonPushInteractor) {
		//showProductLayout(mProductPreviewViewModel.title, mProductPreviewViewModel.productImage,
		//		mProductPreviewViewModel.detail, mProductPreviewViewModel.productor, mProductPreviewViewModel.categoryImage);
		this.mOnExecuteButtonPushInteractor = mOnExecuteButtonPushInteractor;

		lTitle.setText(mProductPreviewViewModel.title);
		lProductor.setText(mProductPreviewViewModel.productor);
		tDetail.setText(mProductPreviewViewModel.detail);

		MediaTracker tracker = new MediaTracker(this);
		Image thumb = mProductPreviewViewModel.productImage.getImage().getScaledInstance(SIZE, SIZE, Image.SCALE_SMOOTH);
		tracker.addImage( thumb, 2);
		ImageIcon format = new ImageIcon(thumb);
		lProductImage.setIcon(format);

		Image thumb2 = mProductPreviewViewModel.categoryImage.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		tracker.addImage( thumb2, 2);
		ImageIcon categ = new ImageIcon(thumb2);
		lCategoryImage.setIcon(categ);

		lTitle.setVisible(true);
		bLaunch.setVisible(true);
		lProductImage.setVisible(true);
		lCategoryImage.setVisible(true);
		lProductor.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		mOnExecuteButtonPushInteractor.handle();
	}

}
