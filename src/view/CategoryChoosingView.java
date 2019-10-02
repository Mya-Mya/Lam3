package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.util.HashMap;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

import domain.valueobject.CategoryId;
import interactor.OnSelectShowingCategoryInteractor;
import interactor.OnSelectShowingCategoryInteractorImpl;
import presenter.CategoryViewModel;
import ui.Lam3UI;

public class CategoryChoosingView extends JPanel implements ICategoryChoosingView {

	HashMap<CategoryViewModel, CategoryId> category_list = new HashMap<>();
	private int width, height;
	private DefaultComboBoxModel model;
	private JComboBox combo;

	public CategoryChoosingView() {
		width = Testcase4Views.w / 3;
		height = (Testcase4Views.h - 100) / 3;
		setPreferredSize(new Dimension(width, height));
		setBackground(Lam3UI.black);
		setLayout(new BorderLayout());

		String[] combodata = { "Swing", "Java2D", "Java3D", "JavaMail" };
		model = new DefaultComboBoxModel(combodata);
		combo = new JComboBox();
		combo.setModel(model);
		ListCellRenderer renderer = new ListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				JLabel jl = new JLabel();
				//				CategoryViewModel cvm = (CategoryViewModel) value;
				//				String data = cvm.title;
				String data = value.toString();
				jl.setFont(Lam3UI.bigFont);
				jl.setText(data);
				jl.setBorder(new EmptyBorder(0, 0, 0, 0));

				jl.setOpaque(true);
				if (isSelected) {
					jl.setForeground(Lam3UI.orange);
					//				list.setSelectionBackground(Lam3UI.orange);
					//				list.setSelectionForeground(Lam3UI.white);
				} else {
					jl.setForeground(Lam3UI.white);
				}

				jl.setBackground(Lam3UI.darkgray);

				return jl;
			}
		};
		combo.setRenderer(renderer);
		combo.setFont(Lam3UI.bigFont);
		combo.setForeground(Lam3UI.orange);
		combo.setBackground(Lam3UI.darkgray);
//		combo.addItemListener();


		//		combo.gett().setBackground(Lam3UI.darkgray);
		//		combo.setPreferredSize(new Dimension(width/2, height/2));
		//		Border border = new CompoundBorder(new EmptyBorder(50, 80, 50, 80), new EtchedBorder(EtchedBorder.RAISED));
		//		combo.setBorder(border);
		//		combo.setBorder(new EmptyBorder(50, 80, 50, 80));

		add(Box.createVerticalStrut(80), BorderLayout.NORTH);
		add(Box.createVerticalStrut(80), BorderLayout.SOUTH);
		add(Box.createHorizontalStrut(80), BorderLayout.WEST);
		add(Box.createHorizontalStrut(80), BorderLayout.EAST);
		add(combo, BorderLayout.CENTER);
	}

	@Override
	public void addCategory(CategoryViewModel mCategoryViewModel, CategoryId categoryId) {
		// TODO 自動生成されたメソッド・スタブ
		category_list.put(mCategoryViewModel, categoryId);

		model = new DefaultComboBoxModel();
		for (CategoryViewModel m : category_list.keySet()) {
			model.addElement(m);
		}
		combo.setModel(model);
	}

	@Override
	public void clearAllCategory() {
		// TODO 自動生成されたメソッド・スタブ
		category_list.clear();
		model = new DefaultComboBoxModel();
		combo.setModel(model);
	}

	public void itemStateChanged(ItemEvent e) {
        switch (e.getStateChange()) {
            case ItemEvent.SELECTED:
                System.out.println(e.getItem().toString() + "が選択されました。");
                //カテゴリ選択からプロダクトリスト表現者へ
                OnSelectShowingCategoryInteractor interactor = new OnSelectShowingCategoryInteractorImpl(null);

//                if(e.getItem() == CategoryViewModel.class) { //クラスの比較これであってたっけ？
//                	interactor.handle(category_list.get((CategoryViewModel) e.getItem()));
//                }
                try {
                	interactor.handle(category_list.get((CategoryViewModel) e.getItem()));
                }catch (NullPointerException e2) {
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
