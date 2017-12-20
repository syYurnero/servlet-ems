package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmployeeDAO;
import entity.Employee;

public class UpdateEmpServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out=res.getWriter();
		try {
			EmployeeDAO dao=new EmployeeDAO();
			String ename=req.getParameter("ename");
			double salary=Double.parseDouble(req.getParameter("salary"));
			int age=Integer.parseInt(req.getParameter("age"));
			int id=Integer.parseInt(req.getParameter("id"));
			Employee e=new Employee(id, ename, salary, age);
			dao.update(e);
			res.sendRedirect("list");
		} catch (Exception e1) {
			e1.printStackTrace();
			out.println("系统繁忙,稍后重试");
		}
	}
}
