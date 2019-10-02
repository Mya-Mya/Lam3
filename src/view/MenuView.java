package view;

import java.awt.Dimension;

import javax.swing.JPanel;

import ui.Lam3UI;

public class MenuView extends JPanel implements IMenuView{
	public MenuView() {
		setPreferredSize(new Dimension(Testcase4Views.w,100));
		setBackground(Lam3UI.orange);
	}
}
