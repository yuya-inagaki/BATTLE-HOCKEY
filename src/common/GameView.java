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
    GameView(String title){
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

// ゲーム本体の処理と描画はこのクラス。多分わけたほうがいい。
class MainPanel extends JPanel implements Runnable, KeyListener{
    int x = 100, y = 100;
    final String p1name = "p1";
    final String p2name = "p2";
    Thread t;
    GameObject p1;
    GameObject p2;
    HashMap<String, GameObject> objects = new HashMap<String, GameObject>();

    // ゲームの初期化処理。gameobjectの生成とか
    void initializeGame(){
	p1 = new GameObject(50, 250, 0, 0, 20, 100, Color.red);
	p2 = new GameObject(700, 250, 0, 0, 20, 100, Color.blue);
	objects.put(p1name, p1);
	objects.put(p2name, p2);
    }
    
    MainPanel(){
        //setLayout(null);
	initializeGame();
        setBackground(Color.gray);//背景色を青に
        t = new Thread(this);//Thread instance
        t.start();//Thread Start
    }
    //Runnableによるrun() method
    @Override
    public void run(){
        //無限ループでThreadが終了しないようにする
        while(true){
	    
            try{
                t.sleep(60);
            }catch(InterruptedException e){}
            repaint();
        }
    }

    //JComponentによるpaintComponent method
    //JPanel は JComponent を継承している
    @Override    
    public void paintComponent(Graphics g){
        //ゲーム上のオブジェクトをアップデートと描画する
	for(final GameObject obj : objects.values()){
	    obj.update();
	    obj.paintObject(g);
	}
    }

    /**********************
        KeyEvent
    **********************/
    //Keyが押された場合
    @Override
    public void keyPressed(KeyEvent e){
        switch(e.getKeyCode()){
	case KeyEvent.VK_UP:
	    System.out.println("up");
	    if(x < 300){
		p1.setVY(-7);
            }
	    break;
	case KeyEvent.VK_DOWN:
	    System.out.println("down");
	    if(x > 0){
		p1.setVY(7);
            }
	    break;
        }
	
    }

    //Keyが離された場合
    @Override
    public void keyReleased(KeyEvent e){
        switch(e.getKeyCode()){
	case KeyEvent.VK_UP:
	    System.out.println("up release");
	    p1.setVY(0);
	    break;
	case KeyEvent.VK_DOWN:
	    System.out.println("down release");
	    p1.setVY(0);
	    break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e){}
}
