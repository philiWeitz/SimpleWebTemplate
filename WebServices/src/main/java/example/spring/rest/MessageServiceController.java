package example.spring.rest;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import example.bl.security.service.AdminService;
import example.bl.security.service.GuestService;
import example.jpa.model.Message;

@RestController
@RequestMapping("/service/message")
public class MessageServiceController {
	
	private static Logger LOG = LogManager
			.getLogger(MessageServiceController.class);

	/*
	 * example call: -
	 * http://localhost:8080/SimpleWebTemplateWebServices/service/message/getAll
	 * /myStrParameter
	 */

	@Autowired
	private GuestService guestService;
	@Autowired
	private AdminService adminService;

	private ObjectMapper mapper = new ObjectMapper();


	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public String getAllMessages() throws JsonGenerationException,
			JsonMappingException, IOException {

		return mapper.writeValueAsString(guestService.getAllMessages());
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ResponseEntity<String> createMessage() {
		try {
			
			String result = mapper.writeValueAsString(new Message());
			return new ResponseEntity<String>(result, HttpStatus.BAD_REQUEST); 
			
		} catch (Exception e) {
			LOG.error("Error while creating new message", e);
		}
		
		return new ResponseEntity<String>("Error while creating new message", HttpStatus.BAD_REQUEST); 
	}
	
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<String> saveMessage(@RequestBody String messString, UriComponentsBuilder builder) {

		try {
			
			Message message = mapper.readValue(messString, Message.class);
			message = adminService.addOrUpdateMessage(message);

			String result = mapper.writeValueAsString(message);
			return new ResponseEntity<String>(result,HttpStatus.OK);
		} catch (Exception e) {
			LOG.error("Error saving message", e);
		}
    	
        return new ResponseEntity<String>("Error while saving message",HttpStatus.BAD_REQUEST);
    }
    
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> deleteMessage(@RequestBody String messString, UriComponentsBuilder builder) {

		try {
			
			Message message = mapper.readValue(messString, Message.class);
			adminService.deleteMessage(message);

			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		} catch (Exception e) {
			LOG.error("Error deleting message", e);
		}
    	
        return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
    }
}