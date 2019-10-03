package view;

import java.io.File;

import javax.swing.JFrame;

import domain.valueobject.Category;
import domain.valueobject.Product;
import repository.TestDataLoader;

public class ProductPreviewViewTest {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		new ProductPreviewViewTest();
	}

	ProductPreviewView preView;
	public ProductPreviewViewTest() {
		JFrame frame = new JFrame();
		preView = new ProductPreviewView();

		TestDataLoader tdl = new TestDataLoader();
		String test_data_path = System.getProperty("user.dir") + "\\data";
		File rootDir = new File(test_data_path);
		if (!rootDir.exists()) {
			System.out.println("読み込み失敗");
		}
		//作品ファイルの読み込みテスト
		for (int i = 0; i < 2; i++) {
			File productDir = rootDir.listFiles()[i];
			System.out.println(productDir.getName());
			if (productDir.getName().matches("^d.+")) {
				Product p = tdl.createProduct(productDir);
			} else if (productDir.getName().matches("^t.+")) {
				//カテゴリファイルの読み込みテスト
				System.out.println(productDir.getName());
				Category c = tdl.createCategory(productDir);
				//実行ファイル　IDはヌル
				System.out.println("1." + c.getDetail());
			}
		}
		preView.showNothingToShow();
		frame.add(preView);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);


//		title_model = new ProductPreviewViewModel(null, "ようこそ！コンピュータ部の展示へ！", null, null, "");
	}
}
