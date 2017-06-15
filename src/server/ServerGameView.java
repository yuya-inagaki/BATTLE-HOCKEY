package server;
import javax.swing.*;
import java.awt.*;
import client.ClientGameView;
import common.GameView;
import common.ImageView;
import common.MainPanel;

import javax.swing.border.LineBorder;//Lineborderインポート

public class ServerGameView extends GameView {


    protected ServerGameView(String title) {
        setTitle(title);
        setSize(800,520);
        setResizable(false);//サイズを固定させる
        setLocationRelativeTo(null);//初期画面表示位置を中央に
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//CLOSEでプログラム終了



        Container CP = getContentPane();//getContentPane()はJFrameクラスに定義されている
        //CP.setLayout(null);//レイアウトマネージャを停止

        //上部下部の背景色を設定する
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        panel1.setBackground(Color.ORANGE);
        panel2.setBackground(Color.BLUE);
        panel1.setPreferredSize(new Dimension(800, 10));//サイズ指定
        panel2.setPreferredSize(new Dimension(800, 10));//サイズ指定
        CP.add(panel1, BorderLayout.NORTH);
        CP.add(panel2, BorderLayout.SOUTH);



        //Mainパネルの作成、フレームへのセット
        MainPanel MP = new ServerMainPanel();
        MP.init();
        CP.add(MP);
        //CP.remove(MP);//フレームを外す
        addKeyListener(MP);//KeyListenerをフレームにセット
        //CP.removeKeyListener(MP);//KeyListenerを外す
    }
    public static void main(String args[]){
        GameView frame = new ServerGameView("なんかゲーム(server)");//引数はWindow Title
        frame.setVisible(true);
    }
}
