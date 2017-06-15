package client;

import common.GameInfo;
import common.MainPanel;

import java.io.IOException;
import java.awt.event.KeyEvent;

class ClientMainPanel extends MainPanel {
    
    ClientMainPanel() {
	super();
	try{
	    super.comm = new ClientCommunicator("localhost");
	    super.comm.init();
	}catch(IOException e){
	    System.out.println("exception on initializing super.comm");
	    System.exit(1);
	}
    }

    // clientなので、p2yを送る
    @Override
    protected void runCommunication()throws IOException {
    	int p2ydash =super.p2.getY();
    	GameInfo info = super.comm.run(p2ydash);
    	super.p1.setY(info.p1y);
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
	    p2.setVY(-7);
	    break;
	case KeyEvent.VK_DOWN:
	    System.out.println("down");
	    p2.setVY(7);
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
