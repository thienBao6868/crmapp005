package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import enumdata.RoleName;

@WebFilter(filterName = "authorFilter", urlPatterns = { "/add-user","/user-edit","/user-details","/add-project","/role-add","/role-edit","/groupwork-add","/groupwork-edit","/task-add" })
public class AuthorFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;

		HttpServletResponse resp = (HttpServletResponse) response;

		Cookie[] cookies = req.getCookies();
		String roleUser = "";

		for (int i = 0; i < cookies.length; i++) {
			String name = cookies[i].getName();
			String value = cookies[i].getValue();

			if (name.equals("role")) {
				roleUser = value;
				break;
			}
		}
		String path = req.getServletPath();
		
		if(roleUser.equals(RoleName.LEAD.getName())) {
			if(path.equals("/add-project") || path.equals("/groupwork-add") || path.equals("/task-add") || path.equals("/groupwork-edit")) {
				chain.doFilter(request, response);
			}else {
				resp.sendRedirect(req.getContextPath()+"/404");
			}
			
		}else if(roleUser.equals(RoleName.ADMIN.getName()) ) {
			chain.doFilter(request, response);
		}else {
			resp.sendRedirect(req.getContextPath()+"/404");
		}

	}
}
