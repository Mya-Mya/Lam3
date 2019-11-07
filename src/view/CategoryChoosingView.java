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
		width = screenSize.width / 4;
		height = 80;

		//setPreferredSize(new Dimension(width, height));
		setBackground(Lam3UI.darkerMain);
		setLayout(new BorderLayout());

		combo = new JComboBox();
		combo.setModel(categories);
		combo.setPreferredSize(new Dimension(200,height-30));
		combo.addItemListener(this);
		combo.setBackground(Lam3UI.lighterMain);
		combo.setRequestFocusEnabled(false);
		combo.setBorder(null);

		ListCellRenderer renderer = new ListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				CategoryViewModel cvm = (CategoryViewModel) value;

				JPanel out=Lam3UI.createPanel();
				out.setPreferredSize(combo.getPreferredSize());

				JLabel lText = new JLabel();
				lText.setIconTextGap(20);
				lText.setText(cvm.title);
				lText.setHorizontalTextPosition(JLabel.RIGHT);
				lText.setVerticalTextPosition(JLabel.CENTER);
				lText.setOpaque(false);
				//lText.setPreferredSize(new Dimension(combo.getWidth()-50,combo.getHeight()));

				if (isSelected) {
					lText.setForeground(Lam3UI.accent);
					out.setBackground(Lam3UI.base);
					lText.setFont(Lam3UI.bigFont);
				} else {
					lText.setForeground(Lam3UI.characters);
					out.setBackground(Lam3UI.darkerMain);
					lText.setFont(Lam3UI.bigFont);
				}

				if(categories.getSize() == 0) {
					return out;
				}
				out.add(lText,BorderLayout.CENTER);

				if(cvm.image!=null){
					JLabel lIcon=Lam3UI.createLabel();
					//MediaTracker tracker = new MediaTracker(lIcon);
					Image icon = cvm.image.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
					//tracker.addImage(icon, 2);
					lIcon.setIcon(new ImageIcon(icon));
					out.add(lIcon,BorderLayout.WEST);
				}

				return out;
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
