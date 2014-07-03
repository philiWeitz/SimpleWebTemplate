package example.bl.security.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

import example.bl.security.service.AdminService;
import example.jpa.model.Message;
import example.jpa.service.MessageDAOService;
import example.jpa.service.UserDAOService;


@Resource
@Secured ({"ROLE_ADMIN"})
class AdminServiceImpl implements AdminService {
	
	@Autowired
	MessageDAOService messageService;
	
	@Autowired
	UserDAOService userService;
	
		
	public Message addOrUpdateMessage(Message msg) {		
		return messageService.saveOrUpdate(msg);
	}
	
	public void deleteMessage(Message msg) {
		messageService.delete(msg);
	}
}
