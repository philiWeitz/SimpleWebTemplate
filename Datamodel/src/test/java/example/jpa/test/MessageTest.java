package example.jpa.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import example.jpa.model.Message;
import example.jpa.service.MessageDAOService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-test-context.xml"})
public class MessageTest {

	@Autowired
	MessageDAOService messageService;
	
	@Test
	public void testDatabaseConnection() {		
		
		Message message = new Message();
		message.setCaption("Greetings!");
		message.setText("Hello World");

		message = messageService.saveOrUpdate(message);
		assertNotNull(message.getId());
		
		Message mResult = messageService.getById(message.getId());
		assertNotNull(mResult);
		assertEquals(message.getCaption(), mResult.getCaption());
			
		message.setCaption("updated");
		Message updated = messageService.saveOrUpdate(message);
		assertEquals(message.getCaption(), updated.getCaption());
		
		List<Message> mList = messageService.getAll();
		assertNotNull(mList);
		assertEquals(1, mList.size());
		
		messageService.delete(mResult);
		Message deleted = messageService.getById(mResult.getId());
		assertNull(deleted);
	}
}
