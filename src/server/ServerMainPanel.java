package server;
import common.MainPanel;
import java.awt.event.KeyEvent;

public class ServerMainPanel extends MainPanel{
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
