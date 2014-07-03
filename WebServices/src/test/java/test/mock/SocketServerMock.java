package test.mock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import example.bl.common.WebConsts;

public class SocketServerMock implements Runnable {
	
	private static final int SERVER_TIMEOUT = 2000;
	
	private ServerSocket serverSocket;	
	private String message;
	

	public void run() {
	    try {
			serverSocket = new ServerSocket(WebConsts.SOCKET_SERVER_PORT);
			serverSocket.setSoTimeout(SERVER_TIMEOUT);
			
			Socket socket = serverSocket.accept();
		
			BufferedReader in = new BufferedReader( 
				new InputStreamReader(socket.getInputStream())); 
			
			message = in.readLine();
			in.close();
					
		} catch (IOException e) {
			e.printStackTrace();
			
		} finally {
			if(null != serverSocket) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
