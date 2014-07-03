package example.bl.security.service.impl;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import example.bl.security.service.GuestService;
import example.bl.service.SocketService;
import example.jpa.model.Message;
import example.jpa.model.User;
import example.jpa.service.MessageDAOService;
import example.jpa.service.UserDAOService;


@Resource
class GuestServiceImpl implements GuestService {

	@Autowired
	MessageDAOService messageService;	
	@Autowired
	UserDAOService userService;	
	@Autowired
	SocketService socketService;
	
	
	public List<Message> getAllMessages() {
		return messageService.getAll();
	}

	public User getUser(String name, String password) {
		return userService.getUser(name, password);
	}
	
	public void sendMessageToSocket(String msg) throws UnknownHostException, IOException {
		socketService.sendMessage(msg);
	}
}
