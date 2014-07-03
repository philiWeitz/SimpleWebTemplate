package example.bl.service;

import java.io.IOException;
import java.net.UnknownHostException;

public interface SocketService {
	 void sendMessage(String message) throws UnknownHostException, IOException;
}
