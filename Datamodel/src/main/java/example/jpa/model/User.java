package example.jpa.model;

import javax.persistence.Basic;
import javax.persistence.Entity;


@Entity @SuppressWarnings("serial")
public class User extends AbstractEntity {
	
	@Basic
	private String name;

	@Basic
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}