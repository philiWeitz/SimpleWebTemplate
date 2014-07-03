package example.jpa.service.impl;

import javax.annotation.Resource;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import example.jpa.model.User;
import example.jpa.service.UserDAOService;


@Resource
class UserDAOServiceImpl 
	extends AbstractDAOServiceImpl<User> 
	implements UserDAOService {
	
	private static Logger LOG = LogManager
			.getLogger(UserDAOServiceImpl.class);
	
	
	public User getUser(String name, String password) {
		User result = null;
		
		if(StringUtils.isNotBlank(name) && 
				StringUtils.isNotBlank(password)) {
		
			try {
				em.getTransaction().begin();
	
				Query query = em.createQuery("SELECT u FROM " + 
						User.class.getSimpleName() + " u" +
						" WHERE u.name=\'" + name + "\'" +
						" AND u.password=\'" + password + "\'");
				
				if(query.getMaxResults() > 0) {
					result = (User) query.getSingleResult();
				}
	
				em.getTransaction().commit();
	
			} catch (Exception e) {
				em.getTransaction().rollback();
				LOG.error("Error while getting user \"" + name + "\"", e);
			}
		}
		
		return result;
	}
}
