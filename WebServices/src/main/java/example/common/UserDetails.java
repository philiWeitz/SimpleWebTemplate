package example.common;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import example.jpa.model.UserRole;


@SuppressWarnings("serial")
public class UserDetails implements Serializable {
	
	private String name;

	private List<UserRole> roles;

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<UserRole> getRoles() {
		if(null == roles) {
			roles = new LinkedList<UserRole>();
		}
		return roles;
	}

	public void setRoles(List<UserRole> roles) {
		this.roles = roles;
	}
}
