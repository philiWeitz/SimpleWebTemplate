package example.spring.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.DispatcherServlet;

import example.spring.security.AuthenticationManager;


@SuppressWarnings("serial")
public class CustomDispatcherServlet extends DispatcherServlet {

	private AuthenticationManager authManager = new AuthenticationManager();

	public CustomDispatcherServlet() {
		super();
	}
	

	@Override
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		authManager.authenticationFromSession();
		
		super.doService(request, response);
	}
}
