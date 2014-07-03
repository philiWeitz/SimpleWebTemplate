package example.spring.rest;

import java.io.IOException;
import java.net.UnknownHostException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import example.bl.security.service.GuestService;

@RestController
@RequestMapping("/service/socket")
public class SocketServiceController {
	
	private static Logger LOG = LogManager
			.getLogger(SocketServiceController.class);


	@Autowired
	private GuestService guestService;

	    
    @RequestMapping(value = "/{msg}", method = RequestMethod.GET)
    public ResponseEntity<HttpStatus> sendMessage(@PathVariable String msg) {    	
    	try {
			guestService.sendMessageToSocket(msg);
		} catch (UnknownHostException e) {
			LOG.error("Socket Manager: Host unknown", e);
			return new ResponseEntity<HttpStatus>(HttpStatus.EXPECTATION_FAILED);
		} catch (IOException e) {
			LOG.error("Socket Manager: IOException", e);
			return new ResponseEntity<HttpStatus>(HttpStatus.EXPECTATION_FAILED);
		}
    	
    	return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }  
 }