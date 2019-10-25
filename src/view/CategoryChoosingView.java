package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import domain.valueobject.CategoryId;
import interactor.OnSelectShowingCategoryInteractor;
import presenter.CategoryViewModel;
import ui.Lam3UI;

public class CategoryChoosingView extends JPanel implements ICategoryChoosingView, ItemListener {

	private int width, height;
	private HashMap<CategoryViewModel, CategoryId> category_list = new HashMap<>();
	private DefaultComboBoxModel categories = new DefaultComboBoxModel();
	private OnSelectShowingCategoryInteractor intarator;
	private JComboBox combo;

	public CategoryChoosingView(OnSelectShowingCategoryInteractor mOnSelectShowingCategoryInteractor) {
		intarator = mOnSelectShowingCategoryInteractor;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width = screenSize.width / 3;
		height = 100;

		setPreferredSize(new Dimension(width, height));
		setBackground(Lam3UI.darkgray);
		setLayout(new BorderLayout());

		combo = new JComboBox();
		combo.setModel(categories);

		combo.addItemListener(this);
		ListCellRenderer renderer = new ListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {

				JLabel lText = new JLabel();
				lText.setPreferredSize(new Dimension(combo.getWidth()-50,combo.getHeight()));
				if(categories.getSize() == 0) {
					return lText;
				}

				lText.setOpaque(true);
				if (isSelected) {
					lText.setForeground(Lam3UI.orange);
					lText.setBackground(Lam3UI.lightgray);
					lText.setFont(Lam3UI.bigFont);
				} else {
					lText.setForeground(Lam3UI.white);
					lText.setBackground(Lam3UI.darkgray);
					lText.setFont(Lam3UI.bigFont);
				}

				CategoryViewModel cvm = (CategoryViewModel) value;
				MediaTracker tracker = new MediaTracker(lText);
				/*Image icon = cvm.image.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
				tracker.addImage(icon, 2);
				ImageIcon format = new ImageIcon(icon);
				jl.setIcon(format);*/
				lText.setIconTextGap(20);
				lText.setHorizontalTextPosition(JLabel.RIGHT);
				lText.setVerticalTextPosition(JLabel.CENTER);
				lText.setText(cvm.title);

				JPanel pHolder = new JPanel();
				pHolder.setBackground(Lam3UI.black);
				pHolder.add(lText);
				return pHolder;
			}
		};
		combo.setRenderer(renderer);
//		combo.setUI(new BasicComboBoxUI() {
//			@Override
//			public void configureArrowButton() {
//				super.configureArrowButton();
//				 arrowButton.setBackground(Lam3UI.);
//			}
//		});

		add(Box.createVerticalStrut(10), BorderLayout.NORTH);
		add(Box.createVerticalStrut(10), BorderLayout.SOUTH);
		add(Box.createHorizontalStrut(80), BorderLayout.WEST);
		add(Box.createHorizontalStrut(80), BorderLayout.EAST);
		add(combo, BorderLayout.CENTER);
	}

	@Override
	public void addCategory(CategoryViewModel mCategoryViewModel, CategoryId categoryId) {
		// TODO 自動生成されたメソッド・スタブ
		category_list.put(mCategoryViewModel, categoryId);
		categories.addElement(mCategoryViewModel);
	}

	@Override
	public void clearAllCategory() {
		// TODO 自動生成されたメソッド・スタブ
		category_list.clear();
		categories.removeAllElements();
	}

	public void itemStateChanged(ItemEvent e) {
		switch (e.getStateChange()) {
		case ItemEvent.SELECTED:
			try {
				intarator.handle(category_list.get((CategoryViewModel) e.getItem()));
			} catch (NullPointerException e2) {
				// TODO: handle exception
				System.out.println("カテゴリリストの不具合か予期しないItemEventの混入");
			}
			break;
		case ItemEvent.DESELECTED:
			System.out.println(e.getItem().toString() + "が非選択になりました。");
			break;
		}
	}
}
