package common;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JLabel; //追加
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon; //追加
import java.awt.Container; //追加
import java.awt.BorderLayout; //追加
import javax.swing.JFrame; //追加
import javax.swing.border.LineBorder;//Lineborderインポート

//ゲーム本体の処理と描画はこのクラス。多分わけたほうがいい。
public abstract class MainPanel extends JPanel implements Runnable, KeyListener{
    protected int x = 100, y = 100;
    final String p1name = "p1";
    final String p2name = "p2";
    Thread t;
    protected GameObject p1;
    protected GameObject p2;
    protected GameObject ball;
    protected Communicator comm;
    HashMap<String, GameObject> objects = new HashMap<String, GameObject>();
    GameObject background = new BackgroundPanel(0, 0, 0, 0, 800, 500, Color.green);
    JFrame frame = new JFrame();

    // ゲームの初期化処理。gameobjectの生成とか
    void initializeGame(){
    	p1 = new ServerPlayer(50, 250, 0, 0, 20, 100, Color.red); //プレイヤー1 (左側、サーバ)
    	p2 = new ClientPlayer(750, 250, 0, 0, 20, 100, Color.blue);//プレイヤー2 (右側、クライアント)
    	ball = new Ball(400, 250, 7, 10, 20, 20, Color.black); // ぼーる(四角くなくなった！)
    	objects.put(p1name, p1);
    	objects.put(p2name, p2);
    	objects.put("ball", ball);
    }

    public MainPanel(){
    	//setLayout(null);
    	initializeGame();
    	setOpaque(false);//背景透明

    }
    public void init(){
    	t = new Thread(this);//Thread instance
    	t.start();//Thread Start

    }

    // aとbの差がmargin以下ならtrue。衝突判定用
    private boolean nearlyEqual(int a, int b, int margin){
    	return Math.abs(a - b) < margin;
    }

    void detectGameOver(){
    	if(ball.x < 0){ // server win
	    PlayWave play2=new PlayWave("./bgm/check2.wav");
	    play2.start();
	    System.out.println("GAME OVER");

	    ImageView gameover=new ImageView("./img/gameover.png");
	    gameover.run();
	    repaint();
	    try{   Thread.sleep(5000);  }
	    catch (InterruptedException e){}

	    System.exit(0); // Todo

    	} else if (800 < ball.x){ // client win
	    PlayWave play2=new PlayWave("./bgm/check4.wav");
	    play2.start();
	    System.out.println("GAME OVER");
	    System.exit(0); // Todo
    	}
    }; // ゲームオーバー判定してwinとかloseとか表示する

    void detectCollision(){
    	// 上下の壁とボールの衝突
    	if(ball.y > 450 || ball.y < 10){
	    ball.vy = - ball.vy;
	    PlayWave play=new PlayWave("./bgm/check4.wav");
	    play.start();
    	}

    	//p1とボールの衝突
    	if(nearlyEqual(ball.x, p1.x, 20) && nearlyEqual(ball.y, p1.y, 110)){
	    ball.vx = Math.abs(ball.vx);
	    PlayWave play=new PlayWave("./bgm/check4.wav");
	    play.start();
    	}

    	//p2とボールの衝突
    	if(nearlyEqual(ball.x, p2.x, 20) && nearlyEqual(ball.y, p2.y, 110)){
	    ball.vx = - Math.abs(ball.vx);
	    PlayWave play=new PlayWave("./bgm/check4.wav");
	    play.start();
    	}
    };

    protected abstract void runCommunication()throws IOException;

    //Runnableによるrun() method
    @Override
    public void run(){
	//無限ループでThreadが終了しないようにする
    	while(true){
	    try{
		long connectionstart = System.currentTimeMillis();
		try{
		    runCommunication();
		}catch(IOException e){
		    System.out.println("comm failed");
		}
		long connectionend = System.currentTimeMillis();
		long timetosleep = 60- (connectionend - connectionstart);
		if(timetosleep > 0){
		    Thread.sleep(timetosleep);
		}else{
		    // System.out.println("処理落ち中: " + (timetosleep - 60) + "msec");
		}

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
    	background.paintObject(g);

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
