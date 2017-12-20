package test;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import dao.EmployeeDAO;
import entity.Employee;
import util.DBUtil;

public class TestCase {
//	@Test
//	public void test1() throws SQLException{
//		System.out.println(DBUtil.getConnection());
//	}
//	@Test
//	public void test2(){
//		EmployeeDAO dao=new EmployeeDAO();
//		Employee e=new Employee();
//		e.setEname("π∑±»’‘¿ˆ√€");
//		e.setSalary(2000);
//		e.setAge(22);
//		dao.save(e);
//	}
	@Test
	public void Test3(){
		EmployeeDAO dao=new EmployeeDAO();
		List<Employee> employees=dao.findAll();
		System.out.println(employees);
	}
//	@Test
//	public void Test4(){
//		EmployeeDAO dao=new EmployeeDAO();
//		dao.delete(30);
//	}
//	@Test
//	public void Test5(){
//		EmployeeDAO dao=new EmployeeDAO();
//		Employee e=dao.findById(37);
//		System.out.println(e);
//	}
}
