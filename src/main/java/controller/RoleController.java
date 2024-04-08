package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import enumdata.PathName;
import service.RoleService;

@WebServlet(name = "roleController", urlPatterns = { "/role-add", "/role-table" })
public class RoleController extends HttpServlet {

	private RoleService roleService = new RoleService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String servletPath = req.getServletPath();
		
		if(servletPath.equals(PathName.ROLES.getName())) {
			
			req.setAttribute("listRole", roleService.CallGetAllRole());
	
			req.getRequestDispatcher("role-table.jsp").forward(req, resp);
			
		}else if (servletPath.equals(PathName.ADDROLES.getName())) {
			req.getRequestDispatcher("role-add.jsp").forward(req, resp);
		}
		
		

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String name = req.getParameter("name");
		String description = req.getParameter("description");

		roleService.callCreateRole(name, description);

		resp.sendRedirect(req.getContextPath() + "/role-add");

	}

}
