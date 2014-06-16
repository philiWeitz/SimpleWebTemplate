package example.spring.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import example.bl.security.service.GuestService;
import example.jpa.model.User;
import example.spring.security.AuthenticationManager;

@RestController
@RequestMapping("/service/login")
public class LoginServiceController {
	
	private static Logger LOG = LogManager
			.getLogger(LoginServiceController.class);

	private ObjectMapper mapper = new ObjectMapper();
	private AuthenticationManager authManager = new AuthenticationManager();
	 
	@Autowired
	private GuestService guestService;

	
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> createUser(@RequestBody String userString, UriComponentsBuilder builder) {

		try {
			JsonNode rootNode = mapper.readTree(userString);
			String name = rootNode.get("name").getTextValue();
			String password = rootNode.get("password").getTextValue();			
			
			User user = guestService.getUser(name, password);
			
			if(null == user) {
				return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
			} else {
				authManager.authenticate(user);
				return new ResponseEntity<HttpStatus>(HttpStatus.OK);
			}
			
		} catch (Exception e) {
			LOG.error("Error login user", e);
		}
    	
        return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
    }
}