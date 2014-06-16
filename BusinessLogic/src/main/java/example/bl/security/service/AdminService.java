package example.bl.security.service;

import example.jpa.model.Message;

public interface AdminService {
	void addMessage(Message msg);
	void deleteMessage(Message msg);
}
