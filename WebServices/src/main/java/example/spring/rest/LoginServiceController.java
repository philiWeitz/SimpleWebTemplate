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

import example.jpa.model.User;
import example.jpa.service.UserDAOService;

@RestController
@RequestMapping("/service/login")
public class LoginServiceController {
	
	private static Logger LOG = LogManager
			.getLogger(LoginServiceController.class);

	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private UserDAOService userService;

	
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> createUser(@RequestBody String userString, UriComponentsBuilder builder) {

		try {
			JsonNode rootNode = mapper.readTree(userString);
			String name = rootNode.get("name").getTextValue();
			String password = rootNode.get("password").getTextValue();			
			
			User user = userService.getUser(name, password);
			
			if(null == user) {
				return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<HttpStatus>(HttpStatus.OK);
			}
			
		} catch (Exception e) {
			LOG.error("Error login user", e);
		}
    	
        return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
    }
}