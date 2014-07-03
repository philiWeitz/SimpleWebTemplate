package test.spring.security;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import test.common.AbstractTest;
import example.bl.security.service.AdminService;
import example.bl.security.service.GuestService;
import example.jpa.model.Message;
import example.jpa.model.User;
import example.jpa.model.UserRole;
import example.jpa.service.UserDAOService;
import example.spring.security.AuthenticationManager;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring*.xml"})
public class RoleServiceTest extends AbstractTest {

	@Autowired
	private GuestService guestService;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private UserDAOService userDAOService;
	
	
	private User admin;
	private User user;
	

	@Before
	public void beforeTest() {
		
		AuthenticationManager manager = new AuthenticationManager();
		manager.removeAuthentification();
		
		admin = new User();
		admin.setName("admin");
		admin.setPassword("admin");
		admin.getRoles().add(UserRole.ROLE_USER);
		admin.getRoles().add(UserRole.ROLE_ADMIN);
		admin = userDAOService.saveOrUpdate(admin);
		
		user = new User();
		user.setName("user");
		user.setPassword("user");
		user.getRoles().add(UserRole.ROLE_USER);
		user = userDAOService.saveOrUpdate(user);
	}
	
	@Test
	public void testGuestService() {
		
		Message msg = new Message();
		msg.setCaption("caption");
		msg.setText("text");	
		
		AuthenticationManager manager = new AuthenticationManager();

		manager.removeAuthentification();			
		assertNotNull(guestService.getAllMessages());
		
		manager.authenticate(user);
		assertNotNull(guestService.getAllMessages().isEmpty());
		
		manager.removeAuthentification();	
		manager.authenticate(admin);
		assertNotNull(guestService.getAllMessages().isEmpty());
	}
	
	
	@Test
	public void testAdminService() throws Exception {		

		Message msg = new Message();
		msg.setCaption("caption");
		msg.setText("text");		
		
		AuthenticationManager manager = new AuthenticationManager();
		manager.removeAuthentification();
		
		try {
			adminService.addOrUpdateMessage(msg);
			fail("AdminService should throw an exception");
		} catch (Exception e) {	}
		
		manager.removeAuthentification();
		manager.authenticate(user);
		
		try {
			adminService.addOrUpdateMessage(msg);
			fail("AdminService should throw an exception");
		} catch (Exception e) {	}
		
		manager.removeAuthentification();
		manager.authenticate(admin);
		assertNotNull(adminService.addOrUpdateMessage(msg));
		
		manager.removeAuthentification();
		
		try {
			adminService.addOrUpdateMessage(msg);
			fail("AdminService should throw an exception");
		} catch (Exception e) {	}
	}
}
