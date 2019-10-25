package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import interactor.OnUpdateButtonPushInteractor;
import ui.Lam3UI;

public class MenuView extends JPanel implements IMenuView, ActionListener {
	private final String BUTTON_ICON_PATH = "";
	private ImageIcon home, reload;
	private int width, height;
	private OnUpdateButtonPushInteractor intaractor;

	public MenuView(OnUpdateButtonPushInteractor mOnUpdateButtonPushInteractor) {
		intaractor = mOnUpdateButtonPushInteractor;
		width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		height = 35;
		//setPreferredSize(new Dimension(width, height));
		setBackground(Lam3UI.darkgray);
		FlowLayout layout = new FlowLayout(FlowLayout.TRAILING);
		layout.setHgap(300);
		setLayout(layout);
		add(Box.createHorizontalStrut(30));

		JButton reset = Lam3UI.createButton();
		reset.setPreferredSize(new Dimension(90, 30));
		reset.setBackground(Lam3UI.lightgray);
		reset.addActionListener(this);
		reset.setActionCommand("RELOAD");
		add(reset);

		JButton home = Lam3UI.createButton();
		home.setPreferredSize(new Dimension(30, 30));
		home.addActionListener(this);
		home.setActionCommand("HOME");
		home.setBackground(Lam3UI.lightgray);
		add(home);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		switch (e.getActionCommand()) {
		case "RELOAD":
			intaractor.handle();
			break;
		case "HOME":
			break;
		}
	}

	private void getButtonImages() {
		File rootDir = new File(BUTTON_ICON_PATH);
		for (File f : rootDir.listFiles()) {
			if (f.getName().matches("reload.png") || f.getName().matches("home.png")) {
				loadImage(f);
			}
		}
	}

	private ImageIcon loadImage(File f) {
		ImageIcon i = null;
		try {
			i = new ImageIcon(ImageIO.read(f));
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		MediaTracker tracker = new MediaTracker(this);
		Image icon = i.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
		tracker.addImage(icon, 2);
		return new ImageIcon(icon);
	}
}
