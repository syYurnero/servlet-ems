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
		//��ȡҪ�޸ĵ�Ա����id
		String id=req.getParameter("id");
		//����id��ѯ���ݿ�(��Ҫ�޸ĵ�Ա����Ϣ��ѯ����)
		EmployeeDAO dao=new EmployeeDAO();
		try {
			Employee e=dao.findById(Integer.parseInt(id));
			//���ݲ�ѯ������Ϣ����һ���޸�ҳ��
			out.println("<form action='update' method='post'>");
			out.println("ID:"+e.getId()+"<br/>");
			out.println("����:<input name='ename' value='"+e.getEname()+"'/><br/>");
			out.println("нˮ:<input name='salary' value='"+e.getSalary()+"'/><br/>");
			out.println("����:<input name='age' value='"+e.getAge()+"'/><br/>");
			out.println("<input type='hidden' name='id' value='"+e.getId()+"'/>");
			out.println("<input type='submit' value='ȷ��'/><br/>");
			out.println("</form>");
		} catch (Exception e1) {
			e1.printStackTrace();
			out.println("ϵͳ��æ,�Ժ�����");
		}
	}
}
