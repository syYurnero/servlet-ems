package web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmployeeDAO;
import entity.Employee;

public class LoadEmpServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out=res.getWriter();
		//读取要修改的员工的id
		String id=req.getParameter("id");
		//依据id查询数据库(将要修改的员工信息查询出来)
		EmployeeDAO dao=new EmployeeDAO();
		try {
			Employee e=dao.findById(Integer.parseInt(id));
			//依据查询到的信息生成一个修改页面
			out.println("<form action='update' method='post'>");
			out.println("ID:"+e.getId()+"<br/>");
			out.println("姓名:<input name='ename' value='"+e.getEname()+"'/><br/>");
			out.println("薪水:<input name='salary' value='"+e.getSalary()+"'/><br/>");
			out.println("年龄:<input name='age' value='"+e.getAge()+"'/><br/>");
			out.println("<input type='hidden' name='id' value='"+e.getId()+"'/>");
			out.println("<input type='submit' value='确定'/><br/>");
			out.println("</form>");
		} catch (Exception e1) {
			e1.printStackTrace();
			out.println("系统繁忙,稍后重试");
		}
	}
}
