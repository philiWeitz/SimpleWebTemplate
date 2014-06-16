package example.jpa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import example.jpa.model.Message;
import example.jpa.model.User;
import example.jpa.model.UserRole;
import example.jpa.service.MessageDAOService;
import example.jpa.service.UserDAOService;


public class TestDataServiceImpl {

	@Autowired
	private MessageDAOService messageService;
	
	@Autowired
	private UserDAOService userService;

	
	public void createTestData() {
		createMessageTestData();
		createUserTestData();
	}
	
	private void createMessageTestData() {
		if(messageService.getAll().isEmpty()) {
			Message m1 = new Message();
			m1.setCaption("Caption 1");
			m1.setText("Message 1");
			
			Message m2 = new Message();
			m2.setCaption("Caption 2");
			m2.setText("Message 2");
			
			messageService.saveOrUpdate(m1);
			messageService.saveOrUpdate(m2);
		}
	}
	
	private void createUserTestData() {
		if(userService.getAll().isEmpty()) {
			User u1 = new User();
			u1.setName("admin");
			u1.setPassword("admin");
			u1.getRoles().add(UserRole.ROLE_ADMIN);
			u1.getRoles().add(UserRole.ROLE_USER);
			
			User u2 = new User();
			u2.setName("user");
			u2.setPassword("user");
			u2.getRoles().add(UserRole.ROLE_USER);
			
			userService.saveOrUpdate(u1);
			userService.saveOrUpdate(u2);
		}
	}
}
