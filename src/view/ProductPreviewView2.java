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
	private JLabel lCategoryTitle=Lam3UI.createLabel();
	private JTextArea tDetail = Lam3UI.createUnEditableTextArea();

	private JButton bLaunch=Lam3UI.createButton();

	public ProductPreviewView2() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width = screenSize.width * 2 / 3;
		height = screenSize.height - 100;
		setPreferredSize(new Dimension(width,height));

		setLayout(new BorderLayout());
		setBackground(Lam3UI.black);

		add(Box.createHorizontalStrut(50),BorderLayout.WEST);
		add(Box.createHorizontalStrut(50),BorderLayout.EAST);

		JPanel pCenter=Lam3UI.createPanel();
		BoxLayout layout=new BoxLayout(pCenter,BoxLayout.Y_AXIS);
		GridBagConstraints c=new GridBagConstraints();
		pCenter.setLayout(layout);
		pCenter.setOpaque(false);

		c.gridy=0;
		lTitle.setFont(Lam3UI.bigFont);
		lTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		lTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lTitle.setForeground(Lam3UI.white);
		lTitle.setOpaque(false);
		//layout.setConstraints(lTitle,c);

		c.gridy=1;
		lProductImage.setPreferredSize(new Dimension(400,400));
		lProductImage.setAlignmentX(Component.CENTER_ALIGNMENT);
		lProductImage.setHorizontalAlignment(SwingConstants.CENTER);
		//layout.setConstraints(lProductImage,c);

		lProductor.setFont(Lam3UI.boldFont);
		lProductor.setForeground(Lam3UI.white);
		lProductor.setOpaque(false);

		lCategoryTitle.setFont(Lam3UI.boldFont);
		lCategoryTitle.setForeground(Lam3UI.white);
		lCategoryTitle.setOpaque(false);

		c.gridy=2;
		JPanel pProductorCategory=Lam3UI.createPanel();
		pProductorCategory.setLayout(new FlowLayout(FlowLayout.CENTER,10,0));
		pProductorCategory.setOpaque(false);
		pProductorCategory.add(lCategoryTitle);
		pProductorCategory.add(lCategoryImage);
		pProductorCategory.add(lProductor);
		//layout.setConstraints(pProductorCategory,c);

		c.gridy=3;
		tDetail.setFont(Lam3UI.normalFont);
		tDetail.setAlignmentX(Component.CENTER_ALIGNMENT);
		tDetail.setBackground(Lam3UI.darkgray);
		tDetail.setForeground(Lam3UI.white);
		//layout.setConstraints(tDetail,c);

		c.gridy=4;
		bLaunch.setFont(Lam3UI.bigFont);
		bLaunch.setAlignmentX(Component.CENTER_ALIGNMENT);
		bLaunch.setHorizontalAlignment(SwingConstants.CENTER);
		bLaunch.setForeground(Lam3UI.white);
		bLaunch.setBackground(Lam3UI.orange);
		bLaunch.addActionListener(this);
		bLaunch.setText("起動");
		//layout.setConstraints(bLaunch,c);

		pCenter.add(lTitle);
		pCenter.add(lProductImage);
		pCenter.add(pProductorCategory);
		pCenter.add(tDetail);
		pCenter.add(bLaunch);

		add(pCenter,BorderLayout.CENTER);
	}


	@Override
	public void showNothingToShow() {
		//if(symbol == null || introduction == null || caution == null) {
			//initializeTopPageInfo();
		//}
		//showProductLayout("ようこそ！コンピュータ部の展示へ！", symbol, introduction, "2019年コンピュータ部員(通称 昆布)",caution);
		tDetail.setText("ここに作品の詳細と起動ボタンが表示されます。");
		tDetail.setAlignmentX(JTextArea.CENTER_ALIGNMENT);
		lTitle.setVisible(false);
		bLaunch.setVisible(false);
		lProductImage.setVisible(false);
		lCategoryImage.setVisible(false);
		lProductor.setVisible(false);
		lCategoryTitle.setVisible(false);
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
		tDetail.setAlignmentX(JTextArea.LEFT_ALIGNMENT);
		lCategoryTitle.setText(mProductPreviewViewModel.categoryTitle);

		MediaTracker tracker = new MediaTracker(this);
		int productImageSize=Math.min(lProductImage.getWidth(),lProductImage.getHeight());
		Image thumb = mProductPreviewViewModel.productImage.getImage().getScaledInstance(productImageSize, productImageSize, Image.SCALE_SMOOTH);
		tracker.addImage( thumb, 2);
		ImageIcon format = new ImageIcon(thumb);
		lProductImage.setIcon(format);

		Image thumb2 = mProductPreviewViewModel.categoryImage.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		tracker.addImage( thumb2, 2);
		ImageIcon categ = new ImageIcon(thumb2);
		lCategoryImage.setIcon(categ);

		lTitle.setVisible(true);
		bLaunch.setVisible(true);
		lProductImage.setVisible(true);
		lCategoryImage.setVisible(true);
		lProductor.setVisible(true);
		lCategoryTitle.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		mOnExecuteButtonPushInteractor.handle();
	}

}
