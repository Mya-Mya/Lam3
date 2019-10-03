package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicScrollBarUI;

import domain.valueobject.ProductId;
import interactor.OnSelectProductCellInteractor;
import presenter.ProductCellViewModel;
import ui.AutoScrollTextView;
import ui.Lam3UI;

public class ProductListView extends JPanel implements IProductListView, ListSelectionListener {

	private int w, h;
	private HashMap<ProductCellViewModel, ProductId> models = new HashMap<>();
	private OnSelectProductCellInteractor intaractor;
	private DefaultListModel product = new DefaultListModel();
	private JList list;

	public ProductListView(OnSelectProductCellInteractor mOnSelectProductCellInteractor) {
		intaractor = mOnSelectProductCellInteractor;
		//		w = Testcase4Views.w / 3;
		//		h = (Testcase4Views.h - 100) * 2 / 3;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		w = screenSize.width / 3;
		h = (screenSize.height - 100) * 2 / 3;

		setPreferredSize(new Dimension(w, h));
		setBackground(Lam3UI.white);
		setLayout(new BorderLayout());

		list = new JList(product);
		list.setCellRenderer(new ProductCellRenderer());
		list.addListSelectionListener(this);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane sp = new JScrollPane();
		sp.getViewport().setView(list);
		//		sp.setBackground(Lam3UI.white);
		sp.getVerticalScrollBar().setBackground(Lam3UI.darkgray);
		sp.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			@Override
			protected void configureScrollBarColors() {
				this.thumbColor = Lam3UI.orange;
			}
		});
		sp.setBorder(new EmptyBorder(20, 20, 20, 20));
		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		add(sp, BorderLayout.CENTER);
	}

	@Override
	public void addProductCell(ProductCellViewModel mProductCellViewModel, ProductId productId) {
		// TODO 自動生成されたメソッド・スタブ
		models.put(mProductCellViewModel, productId);
		product.addElement(mProductCellViewModel);
	}

	@Override
	public void clearAllProductCell() {
		// TODO 自動生成されたメソッド・スタブ
		models.clear();
		product.clear();
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		//		intaractor.onSelectProductCell(models.get(list.getSelectedValue()));
	}

	class ProductCellRenderer implements ListCellRenderer {
		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {

			//			JLabel cell = Lam3UI.createLabel();
			AutoScrollTextView cell = new AutoScrollTextView();
			cell.setPreferredSize(new Dimension(w - 50, 70));
			cell.setForeground(Lam3UI.white);
			if (isSelected) {
				cell.setBackground(Lam3UI.orange);
				//				list.setSelectionBackground(Lam3UI.orange);
				//				list.setSelectionForeground(Lam3UI.white);
			} else {
				cell.setBackground(Lam3UI.lightgray);
			}
			cell.setOpaque(true);
			cell.setBorder(new LineBorder(Lam3UI.darkgray));

			ProductCellViewModel product = (ProductCellViewModel) value;

			JLabel image = Lam3UI.createLabel();
			MediaTracker tracker = new MediaTracker(cell);
			Image icon = product.productImage.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			tracker.addImage(icon, 2);
			ImageIcon format = new ImageIcon(icon);
			image.setIcon(format);
			//			cell.setHorizontalAlignment(JLabel.LEFT);
			//			cell.setHorizontalTextPosition(JLabel.RIGHT);
			//			cell.setVerticalTextPosition(JLabel.BOTTOM);
			//			cell.setIconTextGap(5);

			cell.setFont(Lam3UI.normalFont);
			String title = product.title;
			String creator = product.productor;
			String text = "  " + title + "　　　　　　製作者:" + creator;
			cell.setText(text);

			JPanel p = new JPanel();
			p.setBackground(Lam3UI.darkgray);
			p.add(image);
			p.add(cell);
			return p;
		}
	}

}
