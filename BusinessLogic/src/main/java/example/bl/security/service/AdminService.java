package example.bl.security.service;

import example.jpa.model.Message;

public interface AdminService {
	Message addOrUpdateMessage(Message msg);
	void deleteMessage(Message msg);
}
