package server;
import common.GameInfo;
import common.MainPanel;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class ServerMainPanel extends MainPanel{
    final int upkey = KeyEvent.VK_P;
    final int downkey = KeyEvent.VK_U;
    public ServerMainPanel() {
	// TODO Auto-generated constructor stub
	super();
	super.comm = new ServerCommunicator();
	try{
	    super.comm.init();
	}catch(IOException e){
	    System.out.println("exception on initializing super.comm");
	    System.exit(1);
	}

    }
    
    // serverなのでp1yを送る
    @Override
    protected void runCommunication()throws IOException{
	GameInfo info = super.comm.run(super.p1.getY());
	super.p2.setY(info.p2y);
	
    }

    /**********************
     KeyEvent
    **********************/
    //Keyが押された場合
    @Override
    public void keyPressed(KeyEvent e){
	switch(e.getKeyCode()){
	case upkey:
	    System.out.println("up");
	    p1.setVY(-7);
	    break;
	case downkey:
	    System.out.println("down");
	    p1.setVY(7);
	    break;
	}
	
    }
    
    //Keyが離された場合
    @Override
    public void keyReleased(KeyEvent e){
	switch(e.getKeyCode()){
	case upkey:
	    System.out.println("up release");
	    p1.setVY(0);
	    break;
	case downkey:
	    System.out.println("down release");
	    p1.setVY(0);
	    break;
	}
    }

    @Override
    public void keyTyped(KeyEvent e){}
}
