package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import enumdata.PathName;
import service.RoleService;

@WebServlet(name = "roleController", urlPatterns = { "/role-add", "/role-table","/role-edit" })
public class RoleController extends HttpServlet {

	private RoleService roleService = new RoleService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String servletPath = req.getServletPath();
		
		if(servletPath.equals(PathName.ROLES.getName())) {
			
			int id_role = 0;
			// Retrieve the cookies associated with the request
			Cookie[] cookies = req.getCookies();

			// Check if cookies exist
			if (cookies != null) {
				// Iterate over the cookies array
				for (Cookie cookie : cookies) {
					String name = cookie.getName();
					String value = cookie.getValue();
					if (name.equals("role")) {
						id_role=Integer.parseInt(value) ;
					}
				}
			}
			
			req.setAttribute("idRole",id_role);
			
			
			req.setAttribute("listRole", roleService.CallGetAllRole());
	
			req.getRequestDispatcher("role-table.jsp").forward(req, resp);
			
		}else if (servletPath.equals(PathName.ADDROLES.getName())) {
			req.getRequestDispatcher("role-add.jsp").forward(req, resp);
		}else if  (servletPath.equals(PathName.EDITROLES.getName())) {
			
			int id_role = Integer.parseInt(req.getParameter("id_role")) ;
			
			
			
			req.setAttribute("role", roleService.callGetRoleById(id_role));
			
			req.getRequestDispatcher("role-edit.jsp").forward(req, resp);
			
		}
		
		

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String servletPath = req.getServletPath();
		
		
		if (servletPath.equals(PathName.ADDROLES.getName())) {
			String name = req.getParameter("name");
			String description = req.getParameter("description");
			
			if(roleService.callCheckUserExists(name)) {
				
				boolean isAddRoleSuccess = false;
				req.setAttribute("errorMessage", "Role đã tồn tại");
				req.setAttribute("isAddRoleSuccess", isAddRoleSuccess);
				req.getRequestDispatcher("role-add.jsp").forward(req, resp);
				
			}else {
				boolean isAddRoleSuccess = roleService.callCreateRole(name, description);
				
				req.setAttribute("isAddRoleSuccess", isAddRoleSuccess);
				req.getRequestDispatcher("role-add.jsp").forward(req, resp);
			}
			

			
		}
		

	}

}
