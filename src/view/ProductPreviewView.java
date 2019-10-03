package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import interactor.OnExecuteButtonPushInteractor;
import presenter.ProductPreviewViewModel;
import repository.DataLoaderByFile;
import ui.Lam3UI;

public class ProductPreviewView extends JPanel implements IProductPreviewView, ActionListener {

	private int width, height;
	private final String DEFOULT_TOPPAGE_PATH = System.getProperty("user.dir") + "\\data\\tタイトル画面";
	private ImageIcon symbol,caution;
	private String introduction;
	private final int SIZE = 450;
	private OnExecuteButtonPushInteractor now;
	private JLabel title = Lam3UI.createLabel();
	private JLabel image = Lam3UI.createLabel();
	private JLabel productor = Lam3UI.createLabel();
	private JLabel category = Lam3UI.createLabel();
	private JTextArea detail = Lam3UI.createUnEditableTextArea();

	public ProductPreviewView() {
		initializeTopPageInfo();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width = screenSize.width * 2 / 3;
		height = screenSize.height - 100;
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(width, height));
		setLayout(new BorderLayout());
		setBackground(Lam3UI.white);

		title.setFont(Lam3UI.bigFont);
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setForeground(Lam3UI.black);
		title.setBackground(Lam3UI.white);
		title.setOpaque(true);
		title.setPreferredSize(new Dimension(width, 70));
		title.setBorder(new CompoundBorder(new EmptyBorder(5, 10, 0, 10), new LineBorder(Lam3UI.orange)));
		add(title, BorderLayout.NORTH);

		add(Box.createHorizontalStrut(width / 8), BorderLayout.WEST);
		add(Box.createHorizontalStrut(width / 8), BorderLayout.EAST);

		JButton lunch = Lam3UI.createButton();
		lunch.setPreferredSize(new Dimension(width - 50, 100));
		lunch.addActionListener(this);
		lunch.setFont(Lam3UI.bigFont);
		lunch.setText("起動");
		JPanel p = new JPanel();
		p.setBackground(Lam3UI.white);
		p.setBorder(new EmptyBorder(0, 10, 10, 10));
		p.add(lunch);
		add(p, BorderLayout.SOUTH);

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
		productor.setPreferredSize(new Dimension(250,SIZE/2));
//		productor.setBorder(new CompoundBorder(new EmptyBorder(10, 10, 10, 10),new LineBorder(Lam3UI.black)));
		productor.setFont(Lam3UI.boldFont);
		productor.setForeground(Lam3UI.black);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.weightx = 1.0d;
		gbc.weighty = 1.0d;
		layout.setConstraints(productor, gbc);
		category.setPreferredSize(new Dimension(250,SIZE/2));
//		category.setBorder(new CompoundBorder(new EmptyBorder(10, 10, 10, 10),new LineBorder(Lam3UI.black)));
		category.setFont(Lam3UI.boldFont);
		category.setForeground(Lam3UI.black);
		category.setHorizontalTextPosition(JLabel.RIGHT);
		category.setVerticalTextPosition(JLabel.BOTTOM);
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

		detail.setBackground(Lam3UI.lightgray);
		JPanel intro_p = new JPanel();
		intro_p.setLayout(new BorderLayout());
		intro_p.add(detail);
		intro_p.setBorder(new EmptyBorder(0, 10, 10, 10));
		center.add(intro_p, BorderLayout.CENTER);

		add(center, BorderLayout.CENTER);
	}

	@Override
	public void showNothingToShow() {
		if(symbol == null || introduction == null || caution == null) {
			initializeTopPageInfo();
		}
		showProductLayout("ようこそ！コンピュータ部の展示へ！", symbol, introduction,
					"2019年コンピュータ部員(通称 昆布)",caution);
	}

	@Override
	public void showProductPreview(ProductPreviewViewModel mProductPreviewViewModel,
			OnExecuteButtonPushInteractor mOnExecuteButtonPushInteractor) {
		showProductLayout(mProductPreviewViewModel.title, mProductPreviewViewModel.productImage,
				mProductPreviewViewModel.detail, mProductPreviewViewModel.productor, mProductPreviewViewModel.categoryImage);
		now = mOnExecuteButtonPushInteractor;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		now.handle();
	}

	private void showProductLayout(String _title, ImageIcon _icon, String _detail, String _productor, ImageIcon _cateIcon) {
		title.setText(_title);
		productor.setText(_productor);
		detail.setText(_detail);

		MediaTracker tracker = new MediaTracker(this);
		Image thumb = _icon.getImage().getScaledInstance(SIZE, SIZE, Image.SCALE_SMOOTH);
		tracker.addImage( thumb, 2);
		ImageIcon format = new ImageIcon(thumb);
		image.setIcon(format);

		Image thumb2 = _cateIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		tracker.addImage( thumb2, 2);
		ImageIcon categ = new ImageIcon(thumb2);
		category.setIcon(categ);
	}

	private void initializeTopPageInfo() {
		File toppage_info = new File(DEFOULT_TOPPAGE_PATH);
		if (toppage_info.exists()) {
			for (File f : toppage_info.listFiles()) {
				if (f.getName().matches("detail.txt")) {
					DataLoaderByFile loader = new DataLoaderByFile();
					introduction = loader.loadAllText(f);
				} else if (f.getName().matches("image.png")) {
					try {
						symbol = new ImageIcon(ImageIO.read(f));
					} catch (IOException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					}
				}else if(f.getName().matches("caution.png")) {
					try {
						caution = new ImageIcon(ImageIO.read(f));
					} catch (IOException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					}
				}
			}
		} else {
			System.out.println("読み込み失敗。ファイルパスが間違っているか該当ファイルが存在しません。");
		}
	}
}
