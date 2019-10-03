package view;

import java.io.File;

import javax.swing.JFrame;

import org.junit.jupiter.api.Test;

import domain.valueobject.Product;
import presenter.ProductCellViewModel;
import repository.TestDataLoader;

class ProductListViewTest{
	ProductListView listView;

	public static void main(String[] args) {
		new ProductListViewTest();
	}

	public ProductListViewTest() {
		JFrame frame = new JFrame();
		listView = new ProductListView(null);

		//テスト用プロダクト
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
			}
		}
		frame.add(listView);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	@Test
	void test() {

	}

}
