package view;

import java.awt.*;
import java.util.HashMap;

import javax.swing.*;
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
	private OnSelectProductCellInteractor mOnSelectProductCellInteractor;
	private DefaultListModel product = new DefaultListModel();
	private JList list;

	public ProductListView(OnSelectProductCellInteractor mOnSelectProductCellInteractor) {
		this.mOnSelectProductCellInteractor = mOnSelectProductCellInteractor;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		w = screenSize.width / 3;
		h = (screenSize.height - 100) * 2 / 3;

		setPreferredSize(new Dimension(w, h));
		setBackground(Lam3UI.lightgray);
		setLayout(new BorderLayout());

		list = new JList(product);
		list.setBackground(Lam3UI.lightgray);
		list.setCellRenderer(new ProductCellRenderer2());
		list.addListSelectionListener(this);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane sp = Lam3UI.getScrollPane();
		sp.setBackground(Lam3UI.lightgray);
		sp.getViewport().setView(list);
		sp.setBorder(new EmptyBorder(20, 10, 20, 10));
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
		//		mOnSelectProductCellInteractor.onSelectProductCell(models.get(list.getSelectedValue()));
	}

	@Deprecated
	class ProductCellRenderer implements ListCellRenderer {
		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {

			//			JLabel cell = Lam3UI.createLabel();
			AutoScrollTextView cell = new AutoScrollTextView();
			//cell.setPreferredSize(new Dimension(w - 10, 70));
			cell.setForeground(Lam3UI.white);
			if (isSelected) {
				cell.setBackground(Lam3UI.orange);
				mOnSelectProductCellInteractor.onSelectProductCell(
						models.get((ProductCellViewModel)value)
				);
				//				list.setSelectionBackground(Lam3UI.orange);
				//				list.setSelectionForeground(Lam3UI.white);
			} else {
				cell.setBackground(Lam3UI.lightgray);
			}
			cell.setOpaque(true);
			cell.setBorder(new LineBorder(Lam3UI.orange));

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
			String creator = product.creator;
			String text = "  " + title + "製作者:" + creator;
			cell.setText(text);

			JPanel p = new JPanel();
			p.setBackground(Lam3UI.white);
			p.add(image);
			p.add(cell);
			return p;
		}
	}

	class ProductCellRenderer2 implements ListCellRenderer{

		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
			ProductCellViewModel viewModel=(ProductCellViewModel)value;
			JPanel out=Lam3UI.createPanel();
			out.setBorder(BorderFactory.createEmptyBorder(0,1,0,5));

			JPanel contents=Lam3UI.createPanel();
			contents.setPreferredSize(new Dimension(600,90));
			contents.setLayout(new BorderLayout());
			contents.setOpaque(false);

			JLabel lImage=Lam3UI.createLabel();
			MediaTracker tracker = new MediaTracker(contents);
			Image icon = viewModel.productImage.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
			tracker.addImage(icon, 2);
			ImageIcon imageIcon = new ImageIcon(icon);
			lImage.setIcon(imageIcon);

			JLabel tTitle=Lam3UI.createLabel();
			tTitle.setText(viewModel.title);
			tTitle.setFont(Lam3UI.bigFont);
			tTitle.setForeground(Lam3UI.white);
			tTitle.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));

			JLabel lCreator=Lam3UI.createLabel();
			lCreator.setText(viewModel.creator);
			lCreator.setFont(Lam3UI.normalFont);
			lCreator.setForeground(Lam3UI.white);

			JLabel lCategoryImage=Lam3UI.createLabel();
			tracker=new MediaTracker(contents);
			icon=viewModel.categoryImage.getImage().getScaledInstance(20,20,Image.SCALE_FAST);
			tracker.addImage(icon,3);
			imageIcon=new ImageIcon(icon);
			lCategoryImage.setIcon(imageIcon);
			lCategoryImage.setHorizontalAlignment(SwingConstants.RIGHT);

			JPanel rightStuff=Lam3UI.createPanel();
			rightStuff.setOpaque(false);
			rightStuff.setLayout(new BorderLayout());
			rightStuff.add(lCategoryImage,BorderLayout.CENTER);
			rightStuff.add(lCreator,BorderLayout.NORTH);

			contents.add(lImage,BorderLayout.WEST);
			contents.add(tTitle,BorderLayout.CENTER);
			contents.add(rightStuff,BorderLayout.EAST);

			if(isSelected){
				mOnSelectProductCellInteractor.onSelectProductCell(models.get(value));
				out.setBackground(Lam3UI.black);
			}else{
				out.setBackground(Lam3UI.lightgray);
			}
			out.add(contents,BorderLayout.CENTER);
			return out;
		}
	}

}
