package example.jpa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import example.jpa.model.Message;
import example.jpa.service.MessageDAOService;


public class TestDataServiceImpl {

	@Autowired
	private MessageDAOService messageService;

	
	public void createTestData() {
		createMessageTestData();
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
}
