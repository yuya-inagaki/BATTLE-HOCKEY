package server;
import javax.swing.*;
import java.awt.*;
import client.ClientGameView;
import common.GameView;
import common.MainPanel;

public class ServerGameView extends GameView {

    protected ServerGameView(String title) {
        setTitle(title);
        setSize(800,500);
        setLocationRelativeTo(null);//初期画面表示位置を中央に
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//CLOSEでプログラム終了

        Container CP = getContentPane();//getContentPane()はJFrameクラスに定義されている
        //CP.setLayout(null);//レイアウトマネージャを停止

        //上部の背景色を橙に設定する
        JPanel panel = new JPanel();
        panel.setBackground(Color.ORANGE);
        CP.add(panel, BorderLayout.NORTH);
	
        //Mainパネルの作成、フレームへのセット
        MainPanel MP = new ServerMainPanel();
	MP.init();
        CP.add(MP);
        //CP.remove(MP);//フレームを外す
        addKeyListener(MP);//KeyListenerをフレームにセット
        //CP.removeKeyListener(MP);//KeyListenerを外す
	// TODO Auto-generated constructor stub
    }
    public static void main(String args[]){
        GameView frame = new ServerGameView("なんかゲーム(server)");//引数はWindow Title
        frame.setVisible(true);
    }
}
