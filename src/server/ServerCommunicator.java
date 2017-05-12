package server;
import java.io.*;
import java.net.*;
import common.Communicator;

public class ServerCommunicator implements Communicator{
    int PORT = 8080;
    ServerCommunicator(){}

    // p1のy座標を送ってp2のy座標を受け取る
    public int run(int p1y)throws IOException{
	String p2y;
	ServerSocket s=null;
	Socket sock = null; 
	try{
	    s = new ServerSocket(PORT);
	    System.out.println("Started(server): " + s);
	    try{
		sock = s.accept();
		System.out.println("accepted: " + sock);
		BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		PrintWriter out = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()), true);
		out.println(p1y);
		p2y = in.readLine();
		
	    }finally{
		System.out.println("closing(server)");
		sock.close();
	    }
	}finally{
	    s.close();
	}
	return Integer.parseInt(p2y);
    }
}

