package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import domain.valueobject.Category;
import domain.valueobject.Product;
import interactor.OnExecuteButtonPushInteractor;
import presenter.ProductPreviewViewModel;
import repository.TestDataLoader;
import ui.Lam3UI;

public class ProductPreviewView extends JPanel implements IProductPreviewView, ActionListener {

	private int width, height;
	private ProductPreviewViewModel title_model;
	private JLabel title = Lam3UI.createLabel();
	private JLabel image = Lam3UI.createLabel();
	private JLabel productor = Lam3UI.createLabel();
	private JLabel category = Lam3UI.createLabel();
	private JTextArea intro = Lam3UI.createUnEditableTextArea();
	private final int SIZE = 450;
	ArrayList<Product> p_list_test = new ArrayList<Product>();

	public ProductPreviewView() {
		TestDataLoader tdl = new TestDataLoader();
		String test_data_path = System.getProperty("user.dir") + "\\data";
		File rootDir = new File(test_data_path);
		if (!rootDir.exists()) {
			System.out.println("読み込み失敗");
		}
		//作品ファイルの読み込みテスト
		for (int i = 0; i < 1; i++) {
			File productDir = rootDir.listFiles()[i];
			System.out.println(productDir.getName());
			if (productDir.getName().matches("^d.+")) {
				Product p = tdl.createProduct(productDir);
				showProductLayout(p.getTitle(), p.getImage(), p.getDetail(), null, null);
			} else if (productDir.getName().matches("^t.+")) {
				//カテゴリファイルの読み込みテスト
				System.out.println(productDir.getName());
				Category c = tdl.createCategory(productDir);
				//実行ファイル　IDはヌル
				System.out.println("1." + c.getDetail());
			}
		}

		width = Testcase4Views.w * 2 / 3;
		height = Testcase4Views.h - 100;
		title_model = new ProductPreviewViewModel(null, "ようこそ！コンピュータ部の展示へ！", null, null, "");
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(width, height));
		setLayout(new BorderLayout());
		setBackground(Lam3UI.white);

		title.setFont(Lam3UI.bigFont);
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setForeground(Lam3UI.black);
		title.setBackground(Lam3UI.lightgray);
		title.setOpaque(true);
		title.setPreferredSize(new Dimension(Testcase4Views.w, 70));

		add(title, BorderLayout.NORTH);

		add(Box.createHorizontalStrut(width / 8), BorderLayout.WEST);
		add(Box.createHorizontalStrut(width / 8), BorderLayout.EAST);

		JPanel center = new JPanel();
		center.setLayout(new BorderLayout());
		center.setBorder(new EmptyBorder(0, 0, 10, 0));

		JPanel mid_center = new JPanel();
		mid_center.setPreferredSize(new Dimension(700, SIZE));
		mid_center.setBorder(new EmptyBorder(10, 10, 10, 10));
		mid_center.setOpaque(false);
		GridBagLayout layout = new GridBagLayout();
		mid_center.setLayout(layout);
		GridBagConstraints gbc = new GridBagConstraints();
//		productor.setOpaque(false);
		productor.setBorder(new EmptyBorder(10, 10, 10, 10));
		productor.setForeground(Lam3UI.black);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.weightx = 1.0d;
		gbc.weighty = 1.0d;
		layout.setConstraints(productor, gbc);
//		category.setOpaque(false);
		category.setBorder(new EmptyBorder(10, 10, 10, 10));
		category.setForeground(Lam3UI.black);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 1.0d;
		gbc.weighty = 1.0d;
		layout.setConstraints(category, gbc);
		image.setFont(Lam3UI.bigFont);
		image.setHorizontalAlignment(JLabel.CENTER);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 2;
		gbc.weightx = 0d;
		gbc.weighty = 0d;
		layout.setConstraints(image, gbc);
		mid_center.add(productor);
		mid_center.add(category);
		mid_center.add(image);
		center.add(mid_center, BorderLayout.NORTH);

		JButton category_select = Lam3UI.createButton();
		category_select.setPreferredSize(new Dimension(0, 70));
		category_select.setForeground(Lam3UI.darkgray);
		category_select.setBackground(Lam3UI.lightgray);
		category_select.addActionListener(this);
		category_select.setText("起動");
		center.add(category_select, BorderLayout.SOUTH);
		center.add(intro, BorderLayout.CENTER);

		add(center, BorderLayout.CENTER);
		//		showNothingToShow();
	}

	@Override
	public void showNothingToShow() {
		showProductLayout("ようこそ！コンピュータ部の展示へ！", null, "ｋっかかかかか", null, null);
		//
		//		JPanel left_interval = new JPanel();
		//		left_interval.setPreferredSize(new Dimension(width / 8, height - 70));
		//		left_interval.setOpaque(false);
		//		JPanel right_interval = new JPanel();
		//		right_interval.setPreferredSize(new Dimension(width / 8, height - 70));
		//		right_interval.setOpaque(false);

	}

	@Override
	public void showProductPreview(ProductPreviewViewModel mProductPreviewViewModel,
			OnExecuteButtonPushInteractor mOnExecuteButtonPushInteractor) {
		showProductLayout(mProductPreviewViewModel.title, mProductPreviewViewModel.productImage,
				mProductPreviewViewModel.detail, null, null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	private void showProductLayout(String _title, ImageIcon _icon, String _detail, String _productor,String _category) {
		title.setText(_title);
		productor.setText("ヒトラー");
		category.setText("運ゲー");

		MediaTracker tracker = new MediaTracker(this);
		Image bigImg = _icon.getImage().getScaledInstance(SIZE, SIZE, Image.SCALE_SMOOTH);
		tracker.addImage(bigImg, 2);
		ImageIcon format = new ImageIcon(bigImg);
		image.setIcon(format);
		intro.setText(_detail);
	}
}
