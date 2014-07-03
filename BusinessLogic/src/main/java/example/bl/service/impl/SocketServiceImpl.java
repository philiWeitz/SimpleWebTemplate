package example.bl.service.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.annotation.Resource;

import example.bl.common.WebConsts;
import example.bl.service.SocketService;


@Resource
class SocketServiceImpl implements SocketService {
	
	private Socket server;	
	
	
	public void sendMessage(String message) throws UnknownHostException, IOException {
		PrintWriter out = new PrintWriter(getServer().getOutputStream(), true);		
		out.write(message);
	}
	
	
	private Socket getServer() throws UnknownHostException, IOException {
		
		if(null == server || server.isClosed() || !server.isConnected()) {
			server = new Socket(
					WebConsts.SOCKET_SERVER_ADDRESS, WebConsts.SOCKET_SERVER_PORT);
		}
		
		return server;
	}
}
