package api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import response.BaseResponse;
import service.TaskService;

@WebServlet(name="taskApiController", urlPatterns = {"/api/task"})
public class TaskApiController extends HttpServlet{
	
};
