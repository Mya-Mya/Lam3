package view;

import java.awt.*;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import domain.valueobject.ProductId;
import interactor.OnSelectProductCellInteractor;
import presenter.ProductCellViewModel;
import ui.AutoScrollTextView;
import ui.Lam3ScrollBarUI;
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
		setBackground(Lam3UI.lighterMain);
		setLayout(new BorderLayout());

		list = new JList(product);
		list.setBackground(Lam3UI.lighterMain);
		list.setCellRenderer(new ProductCellRenderer3());
		list.addListSelectionListener(this);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane sp = Lam3UI.getScrollPane();
		sp.setBackground(Lam3UI.lighterMain);
		sp.getViewport().setView(list);
		sp.setBorder(new EmptyBorder(20, 10, 20, 10));
		sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		sp.getVerticalScrollBar().setUI(new Lam3ScrollBarUI());
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

			AutoScrollTextView cell = new AutoScrollTextView();
			//cell.setPreferredSize(new Dimension(w - 10, 70));
			cell.setForeground(Lam3UI.characters);
			if (isSelected) {
				cell.setBackground(Lam3UI.accent);
				mOnSelectProductCellInteractor.onSelectProductCell(
						models.get((ProductCellViewModel)value)
				);

			} else {
				cell.setBackground(Lam3UI.lighterMain);
			}
			cell.setOpaque(true);
			cell.setBorder(new LineBorder(Lam3UI.accent));

			ProductCellViewModel product = (ProductCellViewModel) value;

			JLabel image = Lam3UI.createLabel();
			Image icon = product.productImage.getScaledInstance(50, 50, Image.SCALE_FAST);
			image.setIcon(new ImageIcon(icon));

			cell.setFont(Lam3UI.normalFont);
			String title = product.title;
			String creator = product.creator;
			String text = "  " + title + "製作者:" + creator;
			cell.setText(text);

			JPanel p = new JPanel();
			p.setBackground(Lam3UI.characters);
			p.add(image);
			p.add(cell);
			return p;
		}
	}
	class ProductCellRenderer3 implements ListCellRenderer{

		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
			ProductCellViewModel viewModel=(ProductCellViewModel)value;

			JLabel lImage=Lam3UI.createLabel();
			JLabel lCategoryImage=Lam3UI.createLabel();

			Thread productImageLoader=new Thread(new Runnable() {
				@Override
				public void run() {
					Image productImage = viewModel.productImage.getScaledInstance(80, 80, Image.SCALE_FAST);
					lImage.setIcon(new ImageIcon(productImage));
				}
			});
			Thread categoryImageLoader=new Thread(new Runnable() {
				@Override
				public void run() {
					Image categoryImage=viewModel.categoryImage.getScaledInstance(20,20,Image.SCALE_FAST);
					lCategoryImage.setIcon(new ImageIcon(categoryImage));
					lCategoryImage.setHorizontalAlignment(SwingConstants.RIGHT);
				}
			});
			productImageLoader.start();
			categoryImageLoader.start();

			JPanel out=Lam3UI.createPanel();
			out.setBorder(null);
			if(isSelected){
				new Runnable(){
					@Override
					public void run() {
						mOnSelectProductCellInteractor.onSelectProductCell(models.get(value));
					}
				}.run();
				out.setBackground(Lam3UI.base);
			}else{
				out.setBackground(Lam3UI.lighterMain);
			}

			JLabel tTitle=Lam3UI.createLabel();
			tTitle.setText(viewModel.title);
			tTitle.setFont(Lam3UI.bigFont);
			tTitle.setForeground(Lam3UI.characters);
			tTitle.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));

			JLabel lCreator=Lam3UI.createLabel();
			lCreator.setText(viewModel.creator);
			lCreator.setFont(Lam3UI.normalFont);
			lCreator.setForeground(Lam3UI.characters);
			lCreator.setHorizontalAlignment(SwingConstants.RIGHT);

			JPanel rightStuff=Lam3UI.createPanel();
			rightStuff.setOpaque(false);
			rightStuff.setLayout(new BorderLayout());
			rightStuff.add(Box.createHorizontalStrut(50),BorderLayout.EAST);
			rightStuff.add(lCreator,BorderLayout.NORTH);
			rightStuff.add(lCategoryImage,BorderLayout.CENTER);

			JPanel contents=Lam3UI.createPanel();
			contents.setPreferredSize(new Dimension(600,90));
			contents.setLayout(new BorderLayout());
			contents.setOpaque(false);
			contents.add(tTitle,BorderLayout.CENTER);
			contents.add(rightStuff,BorderLayout.EAST);
			contents.add(lImage,BorderLayout.WEST);

			out.add(contents,BorderLayout.CENTER);
			out.add(Box.createHorizontalStrut(40),BorderLayout.EAST);
			out.add(Box.createHorizontalStrut(40),BorderLayout.WEST);

			try {
				productImageLoader.join();
				categoryImageLoader.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return out;
		}
	}

}
