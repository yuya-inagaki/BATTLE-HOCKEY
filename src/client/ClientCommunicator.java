package client;
import java.net.*;
import java.io.*;
import common.Communicator;

public class ClientCommunicator implements Communicator{
    InetAddress addr;
    int PORT = 8080;
    ClientCommunicator(String inetName)throws IOException{
	InetAddress addr = InetAddress.getByName(inetName);
    }
    
    public int run(int p2y)throws IOException{
    	String p1y;
	System.out.println("addr = " + addr);
	Socket sock = new Socket(addr, PORT);
	try{
	    System.out.println("Sock = " + sock);
	    BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
	    PrintWriter out = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()), true);
	    
	    p1y = in.readLine();
	    out.println(p2y);
	    
	    System.out.println("end(client)");
	}finally{
	    System.out.println("closing(client)");
	    sock.close();
	}
	return Integer.parseInt(p1y);	
    }
}
