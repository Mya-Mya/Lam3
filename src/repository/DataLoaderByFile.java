package repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import domain.DataLoader;
import domain.error.ErrorHistory;
import domain.valueobject.*;

public class DataLoaderByFile implements DataLoader {
    private CategoryIdFactory mCategoryIdFactory;
    private ProductIdFactory mProductIdFactory;

    @Override
    public DataObject execute(CategoryIdFactory mCategoryIdFactory,ProductIdFactory mProductIdFactory) {
        this.mCategoryIdFactory=mCategoryIdFactory;
        this.mProductIdFactory=mProductIdFactory;
        DataObject dataObject = new DataObject();

        File rootDir = new File(CurrentPath.getCurrentPath() + "\\data");
        if (!rootDir.exists()) {
            return dataObject;
        }
        for (File categoryDir : rootDir.listFiles()) {
            if (!categoryDir.isDirectory()) continue;
            dataObject.addCategory(createCategory(categoryDir));
        }
        return dataObject;
    }

    private Category createCategory(File dir) {
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
        return new Category(mCategoryIdFactory.createNewId()
                ,productList
                , title
                , detail
                , image);
    }

    private Product createProduct(File dir) {
        String title = dir.getName();
        Creator creator=new Creator(new ArrayList<>());

        String detail = "";
        ImageIcon image = null;
        File entrypt = null;
        for (File f : dir.listFiles()) {
            if (f.getName().equals("creator.txt")) {
                creator = new Creator(loadTextRows(f));
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
        return new Product(
                mProductIdFactory.createNewId()
                ,title
                , creator
                , detail
                , image
                , entrypt);
    }

    private static String separatorKey = "line.separator";

    public static String loadAllText(File textFile) {
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(textFile),"utf-8"));
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

    public static List<String>loadTextRows(File textFile){
        List<String>out=new ArrayList<>();
        String line;
        try {
            BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(textFile),"utf-8"));
            line=br.readLine();
            line=line.substring(1);
            while(line!=null){
                out.add(line);
                line=br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out;
    }
}
