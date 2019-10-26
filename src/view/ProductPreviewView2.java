package view;

import interactor.OnExecuteButtonPushInteractor;
import presenter.ProductPreviewViewModel;
import ui.Lam3UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductPreviewView2 extends JPanel implements IProductPreviewView, ActionListener {
	private int width, height;

	private OnExecuteButtonPushInteractor mOnExecuteButtonPushInteractor;
	private JLabel lTitle = Lam3UI.createLabel();
	private JLabel lProductImage = Lam3UI.createLabel();
	private JLabel lCreator = Lam3UI.createLabel();
	private JLabel lCategoryImage = Lam3UI.createLabel();
	private JLabel lCategoryTitle=Lam3UI.createLabel();
	private JTextArea tDetail = Lam3UI.createUnEditableTextArea();
	private JScrollPane sDetailHolder;

	private JButton bLaunch=Lam3UI.createButton();

	public ProductPreviewView2() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width = screenSize.width * 2 / 3;
		height = screenSize.height - 100;
		setPreferredSize(new Dimension(width,height));

		setLayout(new BorderLayout());
		setBackground(Lam3UI.base);

		add(Box.createHorizontalStrut(100),BorderLayout.WEST);
		add(Box.createHorizontalStrut(100),BorderLayout.EAST);
		add(Box.createVerticalStrut(20),BorderLayout.NORTH);
		add(Box.createVerticalStrut(20),BorderLayout.SOUTH);

		JPanel pHolder=Lam3UI.createPanel();
		pHolder.setLayout(new GridLayout(0,1,0,20));
		pHolder.setOpaque(false);

		JPanel pUpper=Lam3UI.createPanel();
		pUpper.setOpaque(false);
		pUpper.setLayout(new BorderLayout());

		lTitle.setFont(Lam3UI.bigFont);
		lTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		lTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lTitle.setPreferredSize(new Dimension(600,50));
		lTitle.setForeground(Lam3UI.characters);
		lTitle.setOpaque(false);

		lProductImage.setPreferredSize(new Dimension(400,400));
		lProductImage.setAlignmentX(Component.CENTER_ALIGNMENT);
		lProductImage.setHorizontalAlignment(SwingConstants.CENTER);

		lCreator.setFont(Lam3UI.boldFont);
		lCreator.setForeground(Lam3UI.characters);
		lCreator.setOpaque(false);

		lCategoryTitle.setFont(Lam3UI.boldFont);
		lCategoryTitle.setForeground(Lam3UI.characters);
		lCategoryTitle.setOpaque(false);

		JPanel pCreatorCategory=Lam3UI.createPanel();
		pCreatorCategory.setLayout(new FlowLayout(FlowLayout.CENTER,10,0));
		pCreatorCategory.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
		pCreatorCategory.setOpaque(false);
		pCreatorCategory.add(lCategoryTitle);
		pCreatorCategory.add(lCategoryImage);
		pCreatorCategory.add(lCreator);

		pUpper.add(lTitle,BorderLayout.NORTH);
		pUpper.add(lProductImage,BorderLayout.CENTER);
		pUpper.add(pCreatorCategory,BorderLayout.SOUTH);

		JPanel pDown=Lam3UI.createPanel();
		pDown.setOpaque(false);
		pDown.setLayout(new BorderLayout());

		tDetail.setFont(Lam3UI.normalFont);
		tDetail.setAlignmentX(Component.CENTER_ALIGNMENT);
		tDetail.setAutoscrolls(true);
		tDetail.setBackground(Lam3UI.base);
		tDetail.setForeground(Lam3UI.characters);
		sDetailHolder=Lam3UI.getScrollPane();
		sDetailHolder.getViewport().setView(tDetail);
		sDetailHolder.setBackground(Lam3UI.base);
		sDetailHolder.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		sDetailHolder.setPreferredSize(new Dimension(600,100));

		bLaunch.setFont(Lam3UI.bigFont);
		bLaunch.setAlignmentX(Component.CENTER_ALIGNMENT);
		bLaunch.setHorizontalAlignment(SwingConstants.CENTER);
		bLaunch.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
		bLaunch.setPreferredSize(new Dimension(600,50));
		bLaunch.setForeground(Lam3UI.characters);
		bLaunch.setBackground(Lam3UI.accent);
		bLaunch.addActionListener(this);
		bLaunch.setText("起動");

		pDown.add(sDetailHolder,BorderLayout.CENTER);
		pDown.add(bLaunch,BorderLayout.SOUTH);

		pHolder.add(pUpper);
		pHolder.add(pDown);

		add(pHolder,BorderLayout.CENTER);

		validate();
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
		lCreator.setVisible(false);
		lCategoryTitle.setVisible(false);
	}

	@Override
	public void showProductPreview(ProductPreviewViewModel mProductPreviewViewModel,
			OnExecuteButtonPushInteractor mOnExecuteButtonPushInteractor) {
		//showProductLayout(mProductPreviewViewModel.title, mProductPreviewViewModel.productImage,
		//		mProductPreviewViewModel.detail, mProductPreviewViewModel.productor, mProductPreviewViewModel.categoryImage);
		this.mOnExecuteButtonPushInteractor = mOnExecuteButtonPushInteractor;

		lTitle.setText(mProductPreviewViewModel.title);
		lCreator.setText(mProductPreviewViewModel.creator);
		tDetail.setText(mProductPreviewViewModel.detail);
		tDetail.setAlignmentX(JTextArea.LEFT_ALIGNMENT);
		sDetailHolder.getVerticalScrollBar().setValue(sDetailHolder.getVerticalScrollBar().getMinimum());
		lCategoryTitle.setText(mProductPreviewViewModel.categoryTitle);

		MediaTracker tracker = new MediaTracker(this);
		int productImageSize=Math.min(lProductImage.getWidth(),lProductImage.getHeight());
		if(productImageSize<80)productImageSize=100;
		Image thumb = mProductPreviewViewModel.productImage.getImage().getScaledInstance(productImageSize, productImageSize, Image.SCALE_SMOOTH);
		tracker.addImage( thumb, 2);
		ImageIcon format = new ImageIcon(thumb);
		lProductImage.setIcon(format);

		Image thumb2 = mProductPreviewViewModel.categoryImage.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		tracker.addImage( thumb2, 2);
		ImageIcon categ = new ImageIcon(thumb2);
		lCategoryImage.setIcon(categ);

		lTitle.setVisible(true);
		bLaunch.setVisible(true);
		lProductImage.setVisible(true);
		lCategoryImage.setVisible(true);
		lCreator.setVisible(true);
		lCategoryTitle.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		mOnExecuteButtonPushInteractor.handle();
	}

}
