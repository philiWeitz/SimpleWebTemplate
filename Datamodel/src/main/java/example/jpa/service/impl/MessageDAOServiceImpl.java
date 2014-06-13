package example.jpa.service.impl;

import javax.annotation.Resource;

import example.jpa.model.Message;
import example.jpa.service.MessageDAOService;


@Resource
public class MessageDAOServiceImpl 
	extends AbstractDAOServiceImpl<Message> 
	implements MessageDAOService {

}
