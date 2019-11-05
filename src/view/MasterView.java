package view;

import domain.DataEntity;
import domain.Executer;
import interactor.*;
import presenter.CategoryChoosingPresenter;
import presenter.ProductListPresenter;
import presenter.ProductPreviewPresenter;
import repository.CurrentPath;
import ui.Lam3UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;

public class MasterView extends JFrame implements WindowFocusListener, WindowListener {

    public MasterView(DataEntity entity,Executer executer) {
        super("Lam3");
        setUndecorated(true);
        setIconImage(new ImageIcon(CurrentPath.getCurrentPath()+"\\other\\icon.png").getImage());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        //setResizable(false);

        OnLocateOnScreenButtonPushInteractor mOnLocateOnScreenButtonPushInteractor=new OnLocateOnScreenButtonPushInteractor() {
            @Override
            public void handle() {
                GraphicsEnvironment env=java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment();
                Rectangle rect=env.getMaximumWindowBounds();
                setPreferredSize(new Dimension(rect.width,rect.height));
                setLocation(rect.x,rect.y);
            }
        };
        mOnLocateOnScreenButtonPushInteractor.handle();

        //プレゼンターの起動
        CategoryChoosingPresenter mCategoryChoosigPresenter
                =new CategoryChoosingPresenter(entity);
        ProductListPresenter mProductListPresenter
                =new ProductListPresenter(entity);
        ProductPreviewPresenter mProductPreviewPresnter
                =new ProductPreviewPresenter(entity,executer);
        //インタラクターの起動
        OnSelectProductCellInteractor mOnSelectProductCellInteractor
                =new OnSelectProductCellInteractorImpl(mProductPreviewPresnter);
        OnSelectShowingCategoryInteractor mOnSelectShowingCategoryInteractor
                =new OnSelectShowingCategoryInteractorImpl(mProductListPresenter);
        OnUpdateButtonPushInteractor mOnUpdateButtonPushInteractor
                =new OnUpdateButtonPushInteractorImpl(entity);
        OnReallyCloseButtonPushInteractor mOnReallyCloseButtonPushInteractor
                =new OnReallyCloseButtonPushInteractor() {
            @Override
            public void handle() {
                System.exit(0);
            }
        };

        //ビューの起動
        ICategoryChoosingView mCategoryChoosingView
                =new CategoryChoosingView(mOnSelectShowingCategoryInteractor);
        IProductListView mProductListView
                =new ProductListView(mOnSelectProductCellInteractor);
        IProductPreviewView mProductPreviewView
                =new ProductPreviewView2();
        IMenuView mMenuView
                =new MenuView(mOnUpdateButtonPushInteractor,mOnReallyCloseButtonPushInteractor);

        //ビューとプレゼンターのバインド
        mProductListPresenter.setView(mProductListView);
        mProductPreviewPresnter.setView(mProductPreviewView);
        mCategoryChoosigPresenter.setView(mCategoryChoosingView);

        //ビューコンポーネントの登録
        setLayout(new BorderLayout());
        //JPanel mainContent=Lam3UI.createPanel();
        //mainContent.setLayout(new BorderLayout());

        JPanel left=Lam3UI.createPanel();
        left.setLayout(new BorderLayout());
        left.add((JPanel)mCategoryChoosingView,BorderLayout.NORTH);
        left.add((JPanel)mProductListView,BorderLayout.CENTER);
        add(left,BorderLayout.WEST);

        JPanel right=Lam3UI.createPanel();
        right.setLayout(new BorderLayout());
        right.add((JPanel)mProductPreviewView,BorderLayout.CENTER);
        add(right,BorderLayout.EAST);

        //add(mainContent, BorderLayout.CENTER);

        add((JPanel)mMenuView,BorderLayout.SOUTH);


        addWindowListener(this);
        setVisible(true);
        pack();
        setVisible(true);
    }

    @Override
    public void windowGainedFocus(WindowEvent e) {

    }

    @Override
    public void windowLostFocus(WindowEvent e) {

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("MasterView.windowClosing");
        OnReallyCloseButtonPushInteractor mOnReallyCloseButtonPushInteractor=new OnReallyCloseButtonPushInteractor() {
            @Override
            public void handle() {
                System.exit(0);
            }
        };
        mOnReallyCloseButtonPushInteractor.handle();
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
