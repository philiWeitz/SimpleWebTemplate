package test.common;

import org.junit.BeforeClass;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


public abstract class AbstractTest {

	@BeforeClass 
	public static void beforeTests() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestAttributes attr = new ServletRequestAttributes(request);
		RequestContextHolder.setRequestAttributes(attr);
	}
}
