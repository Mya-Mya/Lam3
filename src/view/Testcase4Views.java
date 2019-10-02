package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import ui.Lam3UI;

public class Testcase4Views extends JFrame {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		new Testcase4Views();
	}

	static int w;
	static int h;
	CategoryChoosingView category;
	ProductListView products;
	public Testcase4Views() {
		super("Lam3 test");

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		w = screenSize.width;
		h = screenSize.height;

		setPreferredSize(new Dimension(w, h));
		setLayout(new BorderLayout());

		JPanel list_view = new JPanel();
		list_view.setLayout(new BorderLayout());
		category = new CategoryChoosingView();
		products = new ProductListView();

//		list_view.setPreferredSize(new Dimension(category.getSize().width + products.getSize().width,
//				category.getSize().height + products.getSize().height));
		list_view.setPreferredSize(new Dimension(Testcase4Views.w/3,(Testcase4Views.h-100)));
		list_view.setBackground(Lam3UI.black);
		list_view.add(category, BorderLayout.NORTH);
		list_view.add(products, BorderLayout.CENTER);

		JPanel menu = new MenuView();
		JPanel preview = new ProductPreviewView();

		add(list_view, BorderLayout.WEST);
		add(menu, BorderLayout.SOUTH);
		add(preview, BorderLayout.CENTER);

		try {
//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
//		SwingUtilities.updateComponentTreeUI(this);

		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
	}

}
