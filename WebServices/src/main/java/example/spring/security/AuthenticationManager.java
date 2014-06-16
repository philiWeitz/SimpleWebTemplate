package example.spring.security;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import example.jpa.model.User;
import example.jpa.model.UserRole;



@Component
public class AuthenticationManager {
	
	private static final String SECURITY_ATTRIBUTE = "SPRING_SECURITY_CONTEXT";
	
	
	public void authenticate(User user) {
        
		List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();  
		
		for(UserRole role : user.getRoles()) {
			grantedAuths.add(new SimpleGrantedAuthority(role.toString()));
		}

        UsernamePasswordAuthenticationToken token = 
        		new UsernamePasswordAuthenticationToken(
        				user.getName(), user.getPassword(), grantedAuths);
		
	    SecurityContext securityContext = SecurityContextHolder.getContext();
	    securityContext.setAuthentication(token);

	    // Create a new session and add the security context.
	    session().setAttribute(SECURITY_ATTRIBUTE, securityContext);
    }
	
	public void removeAuthentification() {
		session().setAttribute(SECURITY_ATTRIBUTE, null);
	}
	
	public Boolean isLoggedIn() {
		return (null != session().getAttribute(SECURITY_ATTRIBUTE));
	}
	
	private static HttpSession session() {
	    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	    return attr.getRequest().getSession(true);
	}
}
