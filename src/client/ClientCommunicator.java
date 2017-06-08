package client;
import java.net.*;
import java.io.*;
import common.Communicator;
import common.GameInfo;

public class ClientCommunicator implements Communicator{
    InetAddress addr;
    Socket sock;
    final int PORT = 8080;
    public ClientCommunicator(String inetName)throws IOException{
	addr = InetAddress.getByName(inetName);
    }

    @Override
    public void init()throws IOException{
    	System.out.println("addr = " + addr);
    	try{
    	sock = new Socket(addr, PORT);
    	}catch(IOException e){
    		System.exit(1);
    	}
	System.out.println("Sock = " + sock);
    }
    
    protected void finalize()throws IOException {
    	sock.close();
    }
    
    public GameInfo run(int p2y)throws IOException{
	System.out.println("clientcommrun");
	String p1y;
	BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
	PrintWriter out = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()), true);
	    
	p1y = in.readLine();
	out.println(p2y);
	GameInfo res = new GameInfo();
	res.p1y = Integer.parseInt(p1y);
	res.p2y = p2y;
	return res;
    }

}

