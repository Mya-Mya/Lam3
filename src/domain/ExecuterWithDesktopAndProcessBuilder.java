package domain;

import domain.error.ErrorHistory;
import domain.valueobject.Product;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ExecuterWithDesktopAndProcessBuilder implements Executer {
    @Override
    public void execute(Product product) {
        File entrypt=product.getEntrypt();
        String entryptName=entrypt.getName();
        int extIndex=entryptName.lastIndexOf(".");
        String ext=extIndex!=-1 ?entryptName.substring(extIndex+1):entryptName;

        if(ext.equals("exe")){
            //exeの拡張子
            String entryptPath=entrypt.getAbsolutePath();
            int directoryIndex=entryptPath.lastIndexOf("\\");
            String directory=directoryIndex!=-1?entryptPath.substring(0,directoryIndex):entryptPath;
            ProcessBuilder builder=new ProcessBuilder(entryptPath);
            builder.directory(new File(directory));
            try {
                builder.start();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else{
            //exe以外の拡張子
            try {
                Desktop.getDesktop().open(product.getEntrypt());
            } catch (IOException e) {
                ErrorHistory.inst().addError(e);
                e.printStackTrace();
            }
        }

    }
}
