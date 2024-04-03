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

@WebFilter(filterName = "authorFilter", urlPatterns = { "/add-user","/add-project","/role-add","/groupwork-add","/task-add" })
public class AuthorFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;

		HttpServletResponse resp = (HttpServletResponse) response;

		// làm thế nào lấy được email

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

//		switch (roleUser) {
//		case "1":
//			if (path.equals("/add-user") || path.equals("/add-project")) {
//				chain.doFilter(req, resp);
//			}
//			;
//		case "2":
//			if (path.equals("/add-project")) {
//				chain.doFilter(request, response);
//			}
//		default:
//			resp.sendRedirect("/login");
//
//		}
		
		if(roleUser.equals(RoleName.LEAD.getName())) {
			if(path.equals("/add-project") || path.equals("/groupwork-add") || path.equals("/task-add")) {
				chain.doFilter(request, response);
			}else {
				resp.sendRedirect(req.getContextPath()+"/login");
			}
			
		}else if(roleUser.equals(RoleName.ADMIN.getName()) ) {
			chain.doFilter(request, response);
		}else {
			resp.sendRedirect(req.getContextPath()+"/login");
		}

	}
}
