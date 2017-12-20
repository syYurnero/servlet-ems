package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmployeeDAO;

public class DelEmpServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html);charset=utf-8");
		PrintWriter out=res.getWriter();
		
		int id=Integer.parseInt(req.getParameter("id"));
		EmployeeDAO dao=new EmployeeDAO();
		
		try {
			dao.delete(id);
			res.sendRedirect("list");
		} catch (Exception e) {
			e.printStackTrace();
			out.println("系统繁忙,稍后重试");
		}
	}
}
