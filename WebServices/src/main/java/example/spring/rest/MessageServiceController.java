package example.spring.rest;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import example.jpa.model.Message;
import example.jpa.service.MessageDAOService;
import example.jpa.service.impl.AbstractDAOServiceImpl;

@RestController
@RequestMapping("/service/message")
public class MessageServiceController {
	private static Logger LOG = LogManager
			.getLogger(AbstractDAOServiceImpl.class);

	/*
	 * example call: -
	 * http://localhost:8080/SimpleWebTemplateWebServices/service/message/getAll
	 * /myStrParameter
	 */

	@Autowired
	private MessageDAOService messageService;

	private ObjectMapper mapper = new ObjectMapper();


	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public String getMessageById(@PathVariable Long id) {

		Message message = messageService.getById(id);

		String result = StringUtils.EMPTY;

		if (null != message) {
			try {
				result = mapper.writeValueAsString(message);
			} catch (Exception e) {
				LOG.error("Error converting message into json", e);
			}
		}

		return result;
	}

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public String getAllMessages() throws JsonGenerationException,
			JsonMappingException, IOException {

		return mapper.writeValueAsString(messageService.getAll());
	}

	@RequestMapping(value = "/add/{messageJson}", method = RequestMethod.GET)
	public void addMessage(@PathVariable String messageJson) {

		try {
			Message message = mapper.readValue(messageJson, Message.class);

			if (null != message) {
				messageService.saveOrUpdate(message);
			}
		} catch (Exception e) {
			LOG.error("Error adding message", e);
		}
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public void deleteMessageById(@PathVariable Long id) {
		messageService.delete(id);
	}
}