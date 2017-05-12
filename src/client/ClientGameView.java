package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;

import common.GameView;
import common.MainPanel;

public class ClientGameView extends GameView{
    ClientGameView(String title) {
        //constructor. フレームの設定関係を行う
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
            MainPanel MP = new ClientMainPanel();
            CP.add(MP);
            //CP.remove(MP);//フレームを外す
            addKeyListener(MP);//KeyListenerをフレームにセット
            //CP.removeKeyListener(MP);//KeyListenerを外す
    }
		// TODO Auto-generated constructor stub

	public static void main(String args[]){
        GameView frame = new ClientGameView("なんかゲーム(client)");//引数はWindow Title
        frame.setVisible(true);
    }
}
