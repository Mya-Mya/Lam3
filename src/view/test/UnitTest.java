package view.test;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import domain.valueobject.Product;
import presenter.CategoryViewModel;
import presenter.ProductCellViewModel;
import repository.TestDataLoader;
import ui.Lam3UI;
import view.CategoryChoosingView;
import view.MenuView;
import view.ProductListView;
import view.ProductPreviewView;

public class UnitTest extends JFrame {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		new UnitTest();
	}

	static int w;
	static int h;
	public UnitTest() {
		super("Lam3 test");

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		w = screenSize.width;
		h = screenSize.height;
		setPreferredSize(new Dimension(w, h));
		setLayout(new BorderLayout());

		JPanel list_view = new JPanel();
		list_view.setLayout(new BorderLayout());

		CategoryChoosingView category = new CategoryChoosingView(null);
		ProductListView listView = new ProductListView(null);
		TestDataLoader tdl = new TestDataLoader();
		String test_data_path = System.getProperty("user.dir") + "\\data";
		File rootDir = new File(test_data_path);
		for (int i = 0; i < 1; i++) {
			File productDir = rootDir.listFiles()[i];
			System.out.println(productDir.getName());
			if (productDir.getName().matches("^d.+")) {
				Product p = tdl.createProduct(productDir);
				ProductCellViewModel model1 = new ProductCellViewModel(p.getImage(), p.getTitle() + "A", null, "たなしゅん");
				ProductCellViewModel model2 = new ProductCellViewModel(p.getImage(), p.getTitle() + "B", null, "くれふぁ");
				ProductCellViewModel model3 = new ProductCellViewModel(p.getImage(), p.getTitle() + "C", null, "えってぃ");
				ProductCellViewModel model4 = new ProductCellViewModel(p.getImage(), p.getTitle() + "D", null, "みゃ神");
				ProductCellViewModel model5 = new ProductCellViewModel(p.getImage(), p.getTitle() + "E", null, "ごみ主将");

				listView.addProductCell(model1, null);
				listView.addProductCell(model1, null);
				listView.addProductCell(model1, null);
				listView.addProductCell(model1, null);
				listView.addProductCell(model1, null);
				listView.addProductCell(model1, null);
				listView.addProductCell(model1, null);
				listView.addProductCell(model1, null);
				listView.addProductCell(model1, null);
				listView.addProductCell(model1, null);
				listView.addProductCell(model2, null);
				listView.addProductCell(model3, null);
				listView.addProductCell(model4, null);
				listView.addProductCell(model5, null);

				CategoryViewModel c_model1 = new CategoryViewModel(p.getImage(), "ぼどげ");
				CategoryViewModel c_model2 = new CategoryViewModel(p.getImage(), "ゆーれい");
				CategoryViewModel c_model3 = new CategoryViewModel(p.getImage(), "うぇぶ");
				CategoryViewModel c_model4 = new CategoryViewModel(p.getImage(), "ぷろぐらみんぐ");
				category.addCategory(c_model1, null);
				category.addCategory(c_model2, null);
				category.addCategory(c_model3, null);
				category.addCategory(c_model4, null);
			}
		}

		MenuView menu = new MenuView(null);
		ProductPreviewView preview = new ProductPreviewView();
		preview.showNothingToShow();

		list_view.setPreferredSize(new Dimension(UnitTest.w/3,(UnitTest.h-100)));
		list_view.setBackground(Lam3UI.base);
		list_view.add(category, BorderLayout.NORTH);
		list_view.add(listView, BorderLayout.CENTER);

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
