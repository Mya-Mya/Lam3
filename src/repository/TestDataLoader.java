package repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import domain.error.ErrorHistory;
import domain.valueobject.Category;
import domain.valueobject.Product;

public class TestDataLoader {
	public static void main(String[] args) {
		TestDataLoader tdl = new TestDataLoader();
		System.out.println("Hello World");
		String test_data_path = System.getProperty("user.dir") + "\\data";
		File rootDir = new File(test_data_path);
		if (!rootDir.exists()) {
			System.out.println("読み込み失敗");
		}
		//作品ファイルの読み込みテスト
		for (File productDir : rootDir.listFiles()) {
			System.out.println(productDir.getName());
			if(productDir.getName().matches("^d.+")) {
				Product p = tdl.createProduct(productDir);
				//実行ファイル　IDはヌル
				System.out.println("1." + p.getProductor() + "\n" +
						"2." + p.getTitle() + "\n" +
						"3." + p.getTitle() + "\n" +
						"4." + p.getProductor());
			}else if(productDir.getName().matches("^t.+")) {
				//カテゴリファイルの読み込みテスト
				System.out.println(productDir.getName());
				Category c = tdl.createCategory(productDir);
				//実行ファイル　IDはヌル
				System.out.println("1."+c.getDetail());
			}
		}
	}

	public Category createCategory(File dir) {
		String title = dir.getName();
		String detail = "";
		ImageIcon image = null;
		List<Product> productList = new ArrayList<>();
		for (File f : dir.listFiles()) {
			//カテゴリーの情報に関するファイル
			if (f.isFile()) {
				if (f.getName().equals("detail.txt")) {
					detail = loadAllText(f);
				}
				if (f.getName().matches("^image\\..*$")) {
					image = new ImageIcon(f.toString());
				}
			}
			//カテゴリーに連なるプロダクトに関するフォルダ
			if (f.isDirectory()) {
				productList.add(createProduct(f));
			}
		}
		return new Category(null
		//        		mCategoryIdFactory.createNewId()
				, productList, title, detail, image);
	}

	public Product createProduct(File dir) {
		String title = dir.getName();
		String productor = "";
		String detail = "";
		ImageIcon image = null;
		File entrypt = null;
		for (File f : dir.listFiles()) {
			if (f.getName().equals("productor.txt")) {
				productor = loadAllText(f);
			}
			if (f.getName().equals("detail.txt")) {
				detail = loadAllText(f);
			}
			if (f.getName().matches("^entrypt\\..*$")) {
				entrypt = f;
			}
			if (f.getName().matches("^image\\..*$")) {
				image = new ImageIcon(f.toString());
			}
		}
		return new Product(null, title, productor, detail, image, entrypt);
	}

	private String separatorKey = "line.separator";

	private String loadAllText(File textFile) {
		StringBuilder sb = new StringBuilder();
		String line;
		try {
			BufferedReader br = new BufferedReader(new FileReader(textFile));
			line = br.readLine();
			while (line != null) {
				sb.append(line);
				sb.append(System.getProperty(separatorKey));
				line = br.readLine();
			}
		} catch (FileNotFoundException e) {
			ErrorHistory.inst().addError(e);
			e.printStackTrace();
		} catch (IOException e) {
			ErrorHistory.inst().addError(e);
			e.printStackTrace();
		}
		return sb.toString().substring(1);
	}
}