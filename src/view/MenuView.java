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
import java.util.concurrent.BlockingDeque;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import interactor.OnReallyCloseButtonPushInteractor;
import interactor.OnUpdateButtonPushInteractor;
import ui.Lam3UI;

public class MenuView extends JPanel implements IMenuView, ActionListener {
	private final String BUTTON_ICON_PATH = "";
	private ImageIcon home, reload;
	private int width, height;
	private OnUpdateButtonPushInteractor mOnUpdateButtonPushInteractor;
	private OnReallyCloseButtonPushInteractor mOnReallyCloseButtonPushInteractor;

	public MenuView(OnUpdateButtonPushInteractor mOnUpdateButtonPushInteractor, OnReallyCloseButtonPushInteractor mOnReallyCloseButtonPushInteractor) {
		this.mOnUpdateButtonPushInteractor = mOnUpdateButtonPushInteractor;
		this.mOnReallyCloseButtonPushInteractor=mOnReallyCloseButtonPushInteractor;
		width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		height = 25;
		//setPreferredSize(new Dimension(width, height));
		setBackground(Lam3UI.darkerMain);
		FlowLayout layout = new FlowLayout(FlowLayout.TRAILING);
		layout.setHgap(20);
		setLayout(layout);

		JButton bReload = Lam3UI.createButton();
		bReload.setPreferredSize(new Dimension(90, 30));
		bReload.setBackground(Lam3UI.lighterMain);
		bReload.addActionListener(this);
		bReload.setActionCommand("RELOAD");
		add(bReload);

		JButton bClose=Lam3UI.createButton();
		bClose.setPreferredSize(new Dimension(30,30));
		bClose.setBackground(Lam3UI.darkerMain);
		bClose.addActionListener(this);
		bClose.setActionCommand("CLOSE");
		add(bClose);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		switch (e.getActionCommand()) {
		case "RELOAD":
			mOnUpdateButtonPushInteractor.handle();
			break;
		case "CLOSE":
			mOnReallyCloseButtonPushInteractor.handle();
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
