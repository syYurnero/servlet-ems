package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmployeeDAO;
import entity.Employee;

public class SomeServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		System.out.println("service()");
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out=res.getWriter();
		EmployeeDAO dao=new EmployeeDAO();
		//获得请求资源路径
		String uri=req.getRequestURI();
		System.out.println("uri:"+uri);
		//分析请求资源路径
		String action=uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		System.out.println("action:"+action);
		
		//依据分析结果做不同处理
		if("/toAdd".equals(action)){
			req.getRequestDispatcher("/WEB-INF/addEmp.jsp").forward(req, res);
		}else if("/list".equals(action)){
			System.out.println("处理员工列表请求");
			try {
				List<Employee> employees=dao.findAll();
				//转发给listEmp.jsp来输出表格
				req.setAttribute("employees", employees);
				RequestDispatcher rd=req.getRequestDispatcher("/WEB-INF/listEmp.jsp");
				rd.forward(req, res);
				System.out.println("转发后的代码");
			} catch (Exception e) {
				e.printStackTrace();
				out.println("系统繁忙,稍后重试");
			}
		}else if("/add".equals(action)){
			System.out.println("处理添加员工请求");
			String ename=req.getParameter("ename");
			String salary=req.getParameter("salary");
			String age=req.getParameter("age");
			
			
			Employee e=new Employee();
			e.setEname(ename);
			e.setSalary(Double.parseDouble(salary));
			e.setAge(Integer.parseInt(age));
			
			try {
				dao.save(e);
				res.sendRedirect("list.do");
			} catch (Exception e1) {
				e1.printStackTrace();
				out.println("系统繁忙,稍后重试");
			}
		}else if("/del".equals(action)){
			System.out.println("处理删除员工请求");
			
			int id=Integer.parseInt(req.getParameter("id"));
			
			try {
				dao.delete(id);
				res.sendRedirect("list.do");
			} catch (Exception e) {
				e.printStackTrace();
				out.println("系统繁忙,稍后重试");
			}
		}else if("/load".equals(action)){
			System.out.println("处理修改员工请求");
			//读取要修改的员工的id
			String id=req.getParameter("id");
			//依据id查询数据库(将要修改的员工信息查询出来)
			try {
				Employee e=dao.findById(Integer.parseInt(id));
				//转发给updateEmp.jsp来生成修改页面
				req.setAttribute("e", e);
				req.getRequestDispatcher("/WEB-INF/updateEmp.jsp").forward(req, res);
			} catch (Exception e1) {
				e1.printStackTrace();
				out.println("系统繁忙,稍后重试");
			}
		}else if("/update".equals(action)){
			System.out.println("返回修改员工请求");
			try {
				String ename=req.getParameter("ename");
				double salary=Double.parseDouble(req.getParameter("salary"));
				int age=Integer.parseInt(req.getParameter("age"));
				int id=Integer.parseInt(req.getParameter("id"));
				Employee e=new Employee(id, ename, salary, age);
				dao.update(e);
				res.sendRedirect("list.do");
			} catch (Exception e1) {
				e1.printStackTrace();
				out.println("系统繁忙,稍后重试");
			}
		}
	}
}
