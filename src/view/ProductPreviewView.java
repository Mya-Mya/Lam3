package view;

import java.awt.*;
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
import javax.swing.border.EmptyBorder;

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
	private OnExecuteButtonPushInteractor mOnExecuteButtonPushInteractor;
	private JLabel lTitle = Lam3UI.createLabel();
	private JLabel lImage = Lam3UI.createLabel();
	private JLabel lProductor = Lam3UI.createLabel();
	private JLabel lCategory = Lam3UI.createLabel();
	private JButton bLaunch=Lam3UI.createButton();
	private JTextArea tDetail = Lam3UI.createUnEditableTextArea();

	public ProductPreviewView() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width = screenSize.width * 2 / 3;
		height = screenSize.height - 100;
		setLayout(new BorderLayout());
		//setPreferredSize(new Dimension(width, height));
		setBackground(Lam3UI.base);

		lTitle.setFont(Lam3UI.bigFont);
		lTitle.setHorizontalAlignment(JLabel.CENTER);
		lTitle.setForeground(Lam3UI.characters);
		lTitle.setOpaque(false);
		lTitle.setPreferredSize(new Dimension(width, 70));
		//lTitle.setBorder(new CompoundBorder(new EmptyBorder(5, 10, 0, 10), new LineBorder(Lam3UI.accent)));
		add(lTitle, BorderLayout.NORTH);

		add(Box.createHorizontalStrut(width / 8), BorderLayout.WEST);
		add(Box.createHorizontalStrut(width / 8), BorderLayout.EAST);

		//bLaunch.setPreferredSize(new Dimension(width - 200, 100));
		bLaunch.addActionListener(this);
		bLaunch.setFont(Lam3UI.bigFont);
		bLaunch.setBackground(Lam3UI.accent);
		bLaunch.setForeground(Lam3UI.characters);
		bLaunch.setText("起動");

		JPanel pLaunchButtonHolder = new JPanel();
		pLaunchButtonHolder.setOpaque(false);
		pLaunchButtonHolder.setBorder(new EmptyBorder(0, 10, 10, 10));
		pLaunchButtonHolder.add(bLaunch);
		add(pLaunchButtonHolder, BorderLayout.SOUTH);

		JPanel pCenter = new JPanel();
		pCenter.setOpaque(false);
		pCenter.setLayout(new BorderLayout());
		pCenter.setBorder(new EmptyBorder(0, 0, 10, 0));

		JPanel pMidCenter = new JPanel();
		//pMidCenter.setPreferredSize(new Dimension(700, SIZE));
		pMidCenter.setBorder(new EmptyBorder(10, 10, 10, 10));
		pMidCenter.setOpaque(false);
		GridBagLayout layout = new GridBagLayout();
		pMidCenter.setLayout(layout);
		GridBagConstraints gbc = new GridBagConstraints();
		lProductor.setPreferredSize(new Dimension(250,SIZE/2));
		lProductor.setFont(Lam3UI.normalFont);
		lProductor.setForeground(Lam3UI.characters);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.weightx = 1.0d;
		gbc.weighty = 1.0d;
		layout.setConstraints(lProductor, gbc);
		lCategory.setPreferredSize(new Dimension(250,SIZE/2));
//		category.setBorder(new CompoundBorder(new EmptyBorder(10, 10, 10, 10),new LineBorder(Lam3UI.base)));
		lCategory.setFont(Lam3UI.boldFont);
		lCategory.setForeground(Lam3UI.darkerMain);
		lCategory.setHorizontalTextPosition(JLabel.RIGHT);
		lCategory.setVerticalTextPosition(JLabel.BOTTOM);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weightx = 1.0d;
		gbc.weighty = 1.0d;
		layout.setConstraints(lCategory, gbc);
		lImage.setFont(Lam3UI.bigFont);
		lImage.setHorizontalAlignment(JLabel.CENTER);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 2;
		gbc.weightx = 0d;
		gbc.weighty = 0d;
		layout.setConstraints(lImage, gbc);
		pMidCenter.add(lProductor);
		pMidCenter.add(lCategory);
		pMidCenter.add(lImage);
		pCenter.add(pMidCenter, BorderLayout.NORTH);

		tDetail.setBackground(Lam3UI.base);
		tDetail.setForeground(Lam3UI.characters);

		JPanel pDetailLabelHolder = new JPanel();
		pDetailLabelHolder.setLayout(new BorderLayout());
		pDetailLabelHolder.add(tDetail);
		//pDetailLabelHolder.setBorder(new EmptyBorder(0, 10, 10, 10));
		pCenter.add(pDetailLabelHolder, BorderLayout.CENTER);

		add(pCenter, BorderLayout.CENTER);
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
		lImage.setVisible(false);
		lCategory.setVisible(false);
		lProductor.setVisible(false);
	}

	@Override
	public void showProductPreview(ProductPreviewViewModel mProductPreviewViewModel,
			OnExecuteButtonPushInteractor mOnExecuteButtonPushInteractor) {
		//showProductLayout(mProductPreviewViewModel.title, mProductPreviewViewModel.productImage,
		//		mProductPreviewViewModel.detail, mProductPreviewViewModel.productor, mProductPreviewViewModel.categoryImage);
		this.mOnExecuteButtonPushInteractor = mOnExecuteButtonPushInteractor;

		lTitle.setText(mProductPreviewViewModel.title);
		lProductor.setText(mProductPreviewViewModel.creator);
		tDetail.setText(mProductPreviewViewModel.detail);

		MediaTracker tracker = new MediaTracker(this);
		Image thumb = mProductPreviewViewModel.productImage.getImage().getScaledInstance(SIZE, SIZE, Image.SCALE_SMOOTH);
		tracker.addImage( thumb, 2);
		ImageIcon format = new ImageIcon(thumb);
		lImage.setIcon(format);

		Image thumb2 = mProductPreviewViewModel.categoryImage.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		tracker.addImage( thumb2, 2);
		ImageIcon categ = new ImageIcon(thumb2);
		lCategory.setIcon(categ);

		lTitle.setVisible(true);
		bLaunch.setVisible(true);
		lImage.setVisible(true);
		lCategory.setVisible(true);
		lProductor.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		mOnExecuteButtonPushInteractor.handle();
	}


	private void showProductLayout(String titleText, ImageIcon imageIcon, String detailText, String productorText, ImageIcon categoryImageIcon) {
		lTitle.setText(titleText);
		lProductor.setText(productorText);
		tDetail.setText(detailText);

		MediaTracker tracker = new MediaTracker(this);
		Image thumb = imageIcon.getImage().getScaledInstance(SIZE, SIZE, Image.SCALE_SMOOTH);
		tracker.addImage( thumb, 2);
		ImageIcon format = new ImageIcon(thumb);
		lImage.setIcon(format);

		Image thumb2 = categoryImageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		tracker.addImage( thumb2, 2);
		ImageIcon categ = new ImageIcon(thumb2);
		lCategory.setIcon(categ);

		lTitle.setVisible(true);
		bLaunch.setVisible(true);
		lImage.setVisible(true);
		lCategory.setVisible(true);
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
