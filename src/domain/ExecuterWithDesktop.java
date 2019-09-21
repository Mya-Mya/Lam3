package domain;

import domain.valueobject.Product;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ExecuterWithDesktop implements Executer {
    @Override
    public void execute(Product product) {
        try {
            Desktop.getDesktop().open(product.getEntrypt());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
