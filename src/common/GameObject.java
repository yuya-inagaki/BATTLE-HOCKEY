package common;
import java.io.*;
import java.awt.Graphics;
import java.awt.Color;

// 座標を持つゲーム上のオブジェクトで、位置情報をserverからclientへ送れる
// 四角以外の形のやつを作りたくなったら継承してpaintObjectをオーバーライドだ
class GameObject {
    int x;
    int y;
    int vx;
    int vy;
    int xsize;
    int ysize;
    Color color;
    public int getX(){return x;}
    public int getY(){return y;}
    public int getVX(){return vx;}
    public int getVY(){return vy;}
    public Color getColor(){return color;}
    public void setX(Integer x){this.x = x;}
    public void setY(Integer y){this.y = y;}
    public void setVX(Integer vx){this.vx = vx;}
    public void setVY(Integer vy){this.vy = vy;}
    public GameObject(int xpos, int ypos, int vx, int vy, int xsize, int ysize, Color color){
	this.x = xpos;
	this.y = ypos;
	this.vx = vx;
	this.vy = vy;
	this.xsize = xsize;
	this.ysize = ysize;
	this.color = color;
    }
    //毎フレーム行われる状態のアップデート
    public void update(){
	x += vx;
	y += vy;
    }
    public void paintObject(Graphics g){
	g.setColor(color);
        g.fillRect(x, y, xsize, ysize);
    }
    public void sendToClient(PrintWriter out){}
}
