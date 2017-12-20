package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmployeeDAO;
import entity.Employee;

public class ListEmpServlet extends HttpServlet{
	@Override
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out=res.getWriter();
		
		EmployeeDAO dao=new EmployeeDAO();
		try {
			List<Employee> employees=dao.findAll();
			//依据查询到的数据,输出表格
			out.println("<table width='80%' border='1' cellpadding='0' cellspacing='0'>");
			out.println("<tr><td>ID</td><td>姓名</td><td>薪水</td><td>年龄</td></tr>");
			for(int i=0;i<employees.size();i++){
				Employee e=employees.get(i);
				out.println("<tr><td>"+e.getId()
				+"</td><td>"+e.getEname()
				+"</td><td>"+e.getSalary()
				+"</td><td>"+e.getAge()
				+"</td><td><a href='del?id="+e.getId()+"'>删除</a>"
				+"&nbsp;<a href='load?id="+e.getId()+"'>修改</a></td></tr>");
			}
			out.println("</table>");
			out.println("<a href='addEmp.html'>添加员工</a>");
		} catch (Exception e) {
			e.printStackTrace();
			out.println("系统繁忙,稍后重试");
		}
	}
}
