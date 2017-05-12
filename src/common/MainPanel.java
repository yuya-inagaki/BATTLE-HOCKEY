package common;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import javax.swing.JPanel;

//ゲーム本体の処理と描画はこのクラス。多分わけたほうがいい。
public abstract class MainPanel extends JPanel implements Runnable, KeyListener{
    protected int x = 100, y = 100;
    final String p1name = "p1";
    final String p2name = "p2";
    Thread t;
    protected GameObject p1;
    protected GameObject p2;
    protected GameObject ball;
    HashMap<String, GameObject> objects = new HashMap<String, GameObject>();

    // ゲームの初期化処理。gameobjectの生成とか
    void initializeGame(){
	p1 = new GameObject(50, 250, 0, 0, 20, 100, Color.red); //プレイヤー1 (左側、サーバ)
	p2 = new GameObject(700, 250, 0, 0, 20, 100, Color.blue);//プレイヤー2 (右側、クライアント)
	ball = new GameObject(400, 250, 7, 10, 20, 20, Color.white); // ぼーる(四角い)
	objects.put(p1name, p1);
	objects.put(p2name, p2);
	objects.put("ball", ball);
    }
 
    public MainPanel(){
	//setLayout(null);
	initializeGame();
	setBackground(Color.gray);//背景色
	t = new Thread(this);//Thread instance
	t.start();//Thread Start
    }

    // aとbの差がmargin以下ならtrue。衝突判定用
    private boolean nearlyEqual(int a, int b, int margin){
	return Math.abs(a - b) < margin;
    }

    void detectGameOver(){
	if(ball.x < 0 || ball.x > 800){
	    System.exit(0); // Todo: ゲームオーバー演出 p1 win とか
	}
    }
    
    void detectCollision(){
	// 上下の壁とボールの衝突
	if(ball.y > 450 || ball.y < 0){
	    ball.vy = - ball.vy;
	}

	//p1とボールの衝突
	if(nearlyEqual(ball.x, p1.x, 20) && nearlyEqual(ball.y, p1.y, 110)){
	    ball.vx = - ball.vx;
	}
	
	//p2とボールの衝突
	if(nearlyEqual(ball.x, p2.x, 20) && nearlyEqual(ball.y, p2.y, 110)){
	    ball.vx = - ball.vx;
	}
    };
    
    //Runnableによるrun() method
    @Override
    public void run(){
	//無限ループでThreadが終了しないようにする
	while(true){
	    
	    try{
		t.sleep(60);
	    }catch(InterruptedException e){}
	    detectGameOver();
	    detectCollision();
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
    //@Override
    //public void keyPressed(KeyEvent e);
    
    //Keyが離された場合
    //@Override
    //public void keyReleased(KeyEvent e){}

    //@Override
    //public void keyTyped(KeyEvent e){}
}
