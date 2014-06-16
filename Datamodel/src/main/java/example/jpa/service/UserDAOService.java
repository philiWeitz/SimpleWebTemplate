package example.jpa.service;

import example.jpa.model.User;

public interface UserDAOService extends AbstractDAOService<User> {
	User getUser(String name, String password);
}
