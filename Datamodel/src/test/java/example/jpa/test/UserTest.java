package example.jpa.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import example.jpa.model.User;
import example.jpa.service.UserDAOService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-test-context.xml"})
public class UserTest {

	@Autowired
	UserDAOService userService;
	
	@Test
	public void testDatabaseConnection() {		
		
		User user = new User();
		user.setName("test");
		user.setPassword("test");	
		user = userService.saveOrUpdate(user);	
		
		User u = userService.getById(user.getId());
		assertNotNull(u);
		
		User result = userService.getUser(user.getName(), user.getPassword());
		assertNotNull(result);
	}
}
