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
    static public final Font normalFont=new Font("メイリオ",Font.PLAIN,18);
    static public final Font boldFont =new Font("メイリオ",Font.BOLD,18);
    static public final Font bigFont =new Font("メイリオ",Font.BOLD,22);

    static public final Color black=new Color(21, 21, 31);
    static public final Color darkgray=new Color(35, 35, 46);
    static public final Color lightgray=new Color(54, 54, 64);
    static public final Color white=new Color(252, 252, 252);
    static public final Color orange=new Color(238, 163, 25);

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
     */
    static public final JTextArea createUnEditableTextArea(){
        JTextArea t=new JTextArea();
        t.setMargin(new Insets(20,20,20,20));
        t.setFont(normalFont);
        t.setForeground(white);
        t.setBackground(black);
        t.setEditable(false);
        t.setLineWrap(true);
        return t;
    }

    /**
     * 見た目はJLabelと全く一緒なのだが、なんと自動スクロールしてくれる！！
     */
    @Deprecated
    static public final AutoScrollTextView createAutoScrollTextView(){
        AutoScrollTextView s=new AutoScrollTextView();
        s.setFont(normalFont);
        s.setForeground(white);
        s.setBackground(darkgray);
        return s;
    }

    static public JPanel createPanel(){
        JPanel p=new JPanel();
        p.setBackground(white);
        return p;
    }

    static public JScrollPane getScrollPane(){
        JScrollPane s=new JScrollPane();
        s.getVerticalScrollBar().setUI(new Lam3ScrollBarUI());
        s.setBorder(null);
        s.setOpaque(false);
        s.getVerticalScrollBar().setOpaque(false);
        return s;
    }

    static public final JCheckBox createCheckBox(){
        JCheckBox c=new JCheckBox();
        c.setBorderPaintedFlat(true);
        c.setBackground(darkgray);
        c.setForeground(white);
        return c;
    }
}
