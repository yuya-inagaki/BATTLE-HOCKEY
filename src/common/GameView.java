// http://qiita.com/koukisayou/items/3ba742548cb0f4c84eaa のコピペ改変

package common;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.*;
import common.GameObject;

public class GameView extends JFrame{
    public static void main(String args[]){
        GameView frame = new GameView("なんかゲーム");//引数はWindow Title
        frame.setVisible(true);
    }

    int getScreensizex(){
	return 0;
    }
    
    
    //constructor. フレームの設定関係を行う
    protected GameView(String title){
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
        MainPanel MP = new MainPanel();
        CP.add(MP);
        //CP.remove(MP);//フレームを外す
        addKeyListener(MP);//KeyListenerをフレームにセット
        //CP.removeKeyListener(MP);//KeyListenerを外す
    }
}
