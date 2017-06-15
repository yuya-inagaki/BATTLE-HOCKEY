package server;
import java.io.*;
import java.net.*;
import common.Communicator;
import common.GameInfo;

public class ServerCommunicator implements Communicator{

    ServerSocket s=null;
    Socket sock = null;
    final int PORT = 8080;
    ServerCommunicator(){
    }
    
    @Override
    public void init()throws IOException{
    	s = new ServerSocket(PORT);
    	System.out.println("Started(server): " + s);
	sock = s.accept();
	System.out.println("accepted(server)");
    }	
    protected void finalize()throws IOException {
    	s.close();
    	sock.close();
    }

    // p1のy座標を送ってp2のy座標を受け取る
    @Override
    public GameInfo run(int p1y)throws IOException{
	String p2y;
	BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
	PrintWriter out = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()), true);
	try{
		Thread.sleep(60);
	}catch(InterruptedException e){
		
	}
	out.println(p1y);
	p2y = in.readLine();
	GameInfo res = new GameInfo();
	res.p1y = p1y;
	res.p2y = Integer.parseInt(p2y);
	return res;
    }
}

