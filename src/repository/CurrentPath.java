package repository;

import java.io.File;

public class CurrentPath {
    public static String getCurrentPath(){
        String jarPath=System.getProperty("java.class.path");
        String dirPath=jarPath.substring(0,jarPath.lastIndexOf(File.separator)+1);

        //デバッグ時のみ有効
        //dirPath=System.getProperty("user.dir");

        return dirPath;
    }
}
