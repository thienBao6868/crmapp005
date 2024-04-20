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

@WebFilter(urlPatterns = { "/login" })
public class LoginFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;

		HttpServletResponse resp = (HttpServletResponse) response;
		String isLogin = null;
		Cookie[] cookies = req.getCookies();
		// Check if cookies exist
		if (cookies != null) {
			// Iterate over the cookies array
			for (Cookie cookie : cookies) {
				String name = cookie.getName();
				String value = cookie.getValue();

				if (name.equals("isLogin")) {
					isLogin = value;
					break;
				}

			}
		} else {
			System.out.println("No cookies found.");
		}
		if (isLogin == null) {
			chain.doFilter(request, response);
		} else {
		
			resp.sendRedirect(req.getContextPath() + "/dashboard");
		}

		
	}

}
