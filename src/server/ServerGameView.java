package server;
import client.ClientGameView;
import common.GameView;

public class ServerGameView extends GameView {

	protected ServerGameView(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}
	public static void main(String args[]){
        GameView frame = new ServerGameView("なんかゲーム(server)");//引数はWindow Title
        frame.setVisible(true);
    }
}
