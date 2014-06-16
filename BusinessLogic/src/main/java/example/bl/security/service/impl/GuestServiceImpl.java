package example.bl.security.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import example.bl.security.service.GuestService;
import example.jpa.model.Message;
import example.jpa.model.User;
import example.jpa.service.MessageDAOService;
import example.jpa.service.UserDAOService;


@Resource
public class GuestServiceImpl implements GuestService {

	@Autowired
	MessageDAOService messageService;
	
	@Autowired
	UserDAOService userService;
	
	
	public List<Message> getAllMessages() {
		return messageService.getAll();
	}

	public User getUser(String name, String password) {
		return userService.getUser(name, password);
	}
}
