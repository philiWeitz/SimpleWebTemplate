package test.service;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.UnknownHostException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import test.common.AbstractTest;
import test.mock.SocketServerMock;
import example.bl.security.service.GuestService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring*.xml"})
public class SocketServiceTest extends AbstractTest {

	private static final String MESSAGE = "Socket Test Message";
	
	@Autowired
	private GuestService guestService;	
	
	private static SocketServerMock server;
	
	
	@Test
	public void testSendMessage() throws UnknownHostException, IOException, InterruptedException {	
		
		// start server (waits for max 2 seconds)
		server = new SocketServerMock();
		Thread thread = new Thread(server);
        thread.start();
		
        // send message
		guestService.sendMessageToSocket(MESSAGE);
		
		while(thread.isAlive()) {
			Thread.sleep(10);
		}
		
		assertEquals(MESSAGE,server.getMessage()); 
	}
}
