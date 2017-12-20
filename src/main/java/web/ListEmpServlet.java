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
			//���ݲ�ѯ��������,������
			out.println("<table width='80%' border='1' cellpadding='0' cellspacing='0'>");
			out.println("<tr><td>ID</td><td>����</td><td>нˮ</td><td>����</td></tr>");
			for(int i=0;i<employees.size();i++){
				Employee e=employees.get(i);
				out.println("<tr><td>"+e.getId()
				+"</td><td>"+e.getEname()
				+"</td><td>"+e.getSalary()
				+"</td><td>"+e.getAge()
				+"</td><td><a href='del?id="+e.getId()+"'>ɾ��</a>"
				+"&nbsp;<a href='load?id="+e.getId()+"'>�޸�</a></td></tr>");
			}
			out.println("</table>");
			out.println("<a href='addEmp.html'>���Ա��</a>");
		} catch (Exception e) {
			e.printStackTrace();
			out.println("ϵͳ��æ,�Ժ�����");
		}
	}
}
