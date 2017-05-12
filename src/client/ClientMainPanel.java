package client;

import common.GameView;
import common.MainPanel;

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

class ClientMainPanel extends MainPanel {

    ClientMainPanel() {
	super();
	// TODO Auto-generated constructor stub
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
	    	p2.setVY(-7);
	    }
	    break;
	case KeyEvent.VK_DOWN:
	    System.out.println("down");
	    if(x > 0){
	    	p2.setVY(7);
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
	    p2.setVY(0);
	    break;
	case KeyEvent.VK_DOWN:
	    System.out.println("down release");
	    p2.setVY(0);
	    break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e){}
}
