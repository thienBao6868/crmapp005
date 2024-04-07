package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import enumdata.PathName;
import service.GroupworkService;

@WebServlet(name = "groupworkController", urlPatterns = { "/groupwork-add", "/groupwork", "/groupwork-details" })
public class GroupworkController extends HttpServlet {

	private GroupworkService groupworkService = new GroupworkService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String servletPath = req.getServletPath();

		if (servletPath.equals(PathName.GROUPWORK.getName())) {
			req.getRequestDispatcher("groupwork.jsp").forward(req, resp);
		} else if (servletPath.equals(PathName.ADDGROUPWORK.getName())) {
			req.getRequestDispatcher("groupwork-add.jsp").forward(req, resp);
		} else if (servletPath.equals(PathName.DETAILSGROUPWORK.getName())) {
			req.getRequestDispatcher("groupwork-details.jsp").forward(req, resp);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String tenDuAn = req.getParameter("tenDuAn");
		String ngayBatDau = req.getParameter("ngayBatDau");
		String ngayKetThuc = req.getParameter("ngayKetThuc");

		groupworkService.CallCreateProject(tenDuAn, ngayBatDau, ngayKetThuc);

		resp.sendRedirect(req.getContextPath() + "/groupwork-add");
	}

}
