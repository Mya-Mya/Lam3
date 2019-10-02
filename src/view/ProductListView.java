package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

import interactor.OnExecuteButtonPushInteractor;
import presenter.ProductPreviewViewModel;
import ui.Lam3UI;

public class ProductListView extends JPanel implements IProductPreviewView {

	private int w, h;

	public ProductListView() {
		w = Testcase4Views.w / 3;
		h = (Testcase4Views.h - 100) * 2 / 3;
		setPreferredSize(new Dimension(w, h));
		setBackground(Lam3UI.lightgray);
		setLayout(new BorderLayout());

		String[] testdata = { "Swing", "Java2D", "Java3D", "JavaMail" };
		JList model = new JList(testdata);
		model.setCellRenderer(new ProductCellRenderer());
		JScrollPane sp = new JScrollPane();
		sp.getViewport().setView(model);
		sp.setBorder(new EmptyBorder(20,20,20,20));

		add(sp,BorderLayout.CENTER);
	}

	@Override
	public void showNothingToShow() {

	}

	@Override
	public void showProductPreview(ProductPreviewViewModel mProductPreviewViewModel,
			OnExecuteButtonPushInteractor mOnExecuteButtonPushInteractor) {

	}

	class ProductCellRenderer implements ListCellRenderer {
		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			JLabel cell = Lam3UI.createLabel();
			//				CategoryViewModel cvm = (CategoryViewModel) value;
			//				String data = cvm.title;
			String data = value.toString();
			cell.setFont(Lam3UI.bigFont);
			cell.setText(data);
			cell.setBorder(new EmptyBorder(0, 0, 0, 0));

			cell.setOpaque(true);
			if (isSelected) {
				cell.setForeground(Lam3UI.orange);
				//				list.setSelectionBackground(Lam3UI.orange);
				//				list.setSelectionForeground(Lam3UI.white);
			} else {
				cell.setForeground(Lam3UI.black);
			}

			cell.setBackground(Lam3UI.white);

			return cell;
		}
	}
}
