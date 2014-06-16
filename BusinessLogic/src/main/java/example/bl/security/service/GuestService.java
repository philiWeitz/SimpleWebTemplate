package example.bl.security.service;

import java.util.List;

import example.jpa.model.Message;
import example.jpa.model.User;

public interface GuestService {
	List<Message> getAllMessages();
	User getUser(String name, String password);
}
