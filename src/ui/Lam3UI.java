package ui;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;

/**
 * UIに関する様々な定数とファクトリーを定義する。
 * 視覚に一貫性を持たせるため、どうぞご利用ください。
 * @author mya
 */
public class Lam3UI {
    static public final Font normalFont=new Font("メイリオ",Font.PLAIN,20);
    static public final Font boldFont =new Font("メイリオ",Font.BOLD,20);
    static public final Font bigFont =new Font("メイリオ",Font.BOLD,25);

    static public final Color black=new Color(24, 24, 24);
    static public final Color darkgray=new Color(49, 49, 49);
    static public final Color lightgray=new Color(115, 115, 115);
    static public final Color white=new Color(252, 252, 252);
    static public final Color orange=new Color(255, 203, 0);

    static public final JButton createButton(){
        JButton b=new JButton();
        b.setFont(normalFont);
        b.setForeground(white);
        b.setBackground(darkgray);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
        return b;
    }
    static public final JLabel createLabel(){
        JLabel l=new JLabel();
        l.setFont(normalFont);
        l.setForeground(white);
        l.setBackground(black);
        return l;
    }

    /**
     * 見た目はJLabelと全く一緒なのだが、なんと自動改行してくれる！！すごいよ
     * @return
     */
    static public final JTextArea createUnEditableTextArea(){
        JTextArea t=new JTextArea();
        t.setFont(normalFont);
        t.setForeground(white);
        t.setBackground(black);
        t.setEditable(false);
        t.setLineWrap(true);
        return t;
    }
    static public JPanel createPanel(){
        JPanel p=new JPanel();
        p.setBackground(white);
        return p;
    }

    static public final JCheckBox createCheckBox(){
        JCheckBox c=new JCheckBox();
        c.setBorderPaintedFlat(true);
        c.setBackground(darkgray);
        c.setForeground(white);
        return c;
    }
}
