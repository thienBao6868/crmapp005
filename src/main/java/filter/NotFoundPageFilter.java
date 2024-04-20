package filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebFilter(urlPatterns = { "/*" })
public class NotFoundPageFilter implements Filter{
	private final List<String> validUrls = Arrays.asList(
            "/add-user", "/users", "/user-details", "/user-edit", "/role-table",
            "/role-add", "/role-edit", "/groupwork", "/groupwork-add", "/groupwork-details",
            "/groupwork-edit", "/task-add", "/task", "/task-edit", "/profile",
            "/profile-edit", "/login", "/logout","/","/404","/dashboard","/blank"
    );
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;

		HttpServletResponse resp = (HttpServletResponse) response;
		
		String requestURI = req.getRequestURI();
        String contextPath = req.getContextPath();
        String servletPath = requestURI.substring(contextPath.length());
        
        if (!validUrls.contains(servletPath)) {
            // Redirect to error page if the servlet path is not valid
             resp.sendRedirect(req.getContextPath() + "/404");
            return;
        }
        chain.doFilter(request, response);
	}

}
