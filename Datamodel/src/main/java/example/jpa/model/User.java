package example.jpa.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Entity @SuppressWarnings("serial")
public class User extends AbstractEntity {
	
	@Basic
	private String name;

	@Basic
	private String password;
	
	@ElementCollection(targetClass=UserRole.class)
	@Enumerated(EnumType.STRING)
	private List<UserRole> roles;
	
	
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

	public List<UserRole> getRoles() {
		if(null == roles) {
			roles = new ArrayList<UserRole>();
		}
		return roles;
	}

	public void setRoles(List<UserRole> roles) {
		this.roles = roles;
	}
}