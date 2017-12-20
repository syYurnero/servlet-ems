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
		//���������Դ·��
		String uri=req.getRequestURI();
		System.out.println("uri:"+uri);
		//����������Դ·��
		String action=uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf("."));
		System.out.println("action:"+action);
		
		//���ݷ����������ͬ����
		if("/toAdd".equals(action)){
			req.getRequestDispatcher("/WEB-INF/addEmp.jsp").forward(req, res);
		}else if("/list".equals(action)){
			System.out.println("����Ա���б�����");
			try {
				List<Employee> employees=dao.findAll();
				//ת����listEmp.jsp��������
				req.setAttribute("employees", employees);
				RequestDispatcher rd=req.getRequestDispatcher("/WEB-INF/listEmp.jsp");
				rd.forward(req, res);
				System.out.println("ת����Ĵ���");
			} catch (Exception e) {
				e.printStackTrace();
				out.println("ϵͳ��æ,�Ժ�����");
			}
		}else if("/add".equals(action)){
			System.out.println("�������Ա������");
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
				out.println("ϵͳ��æ,�Ժ�����");
			}
		}else if("/del".equals(action)){
			System.out.println("����ɾ��Ա������");
			
			int id=Integer.parseInt(req.getParameter("id"));
			
			try {
				dao.delete(id);
				res.sendRedirect("list.do");
			} catch (Exception e) {
				e.printStackTrace();
				out.println("ϵͳ��æ,�Ժ�����");
			}
		}else if("/load".equals(action)){
			System.out.println("�����޸�Ա������");
			//��ȡҪ�޸ĵ�Ա����id
			String id=req.getParameter("id");
			//����id��ѯ���ݿ�(��Ҫ�޸ĵ�Ա����Ϣ��ѯ����)
			try {
				Employee e=dao.findById(Integer.parseInt(id));
				//ת����updateEmp.jsp�������޸�ҳ��
				req.setAttribute("e", e);
				req.getRequestDispatcher("/WEB-INF/updateEmp.jsp").forward(req, res);
			} catch (Exception e1) {
				e1.printStackTrace();
				out.println("ϵͳ��æ,�Ժ�����");
			}
		}else if("/update".equals(action)){
			System.out.println("�����޸�Ա������");
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
				out.println("ϵͳ��æ,�Ժ�����");
			}
		}
	}
}
