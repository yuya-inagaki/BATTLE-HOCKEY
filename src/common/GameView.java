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
    }
}
