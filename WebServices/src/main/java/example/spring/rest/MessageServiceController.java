package example.spring.rest;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import example.bl.security.service.AdminService;
import example.bl.security.service.GuestService;

@RestController
@RequestMapping("/service/message")
public class MessageServiceController {

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
}