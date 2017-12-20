package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmployeeDAO;
import entity.Employee;

public class AddEmpServlet extends HttpServlet{
	@Override
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out=res.getWriter();
		
		String ename=req.getParameter("ename");
		String salary=req.getParameter("salary");
		String age=req.getParameter("age");
		
		EmployeeDAO dao=new EmployeeDAO();
		Employee e=new Employee();
		e.setEname(ename);
		e.setSalary(Double.parseDouble(salary));
		e.setAge(Integer.parseInt(age));
		
		try {
			dao.save(e);
			res.sendRedirect("list");
		} catch (Exception e1) {
			e1.printStackTrace();
			out.println("系统繁忙,稍后重试");
		}
	}
}
