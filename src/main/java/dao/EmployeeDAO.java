package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sun.corba.se.impl.encoding.TypeCodeReader;

import entity.Employee;
import util.DBUtil;

public class EmployeeDAO {
	public void update(Employee e){
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="update t_emp set ename=?,salary=?,age=? where id=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, e.getEname());
			ps.setDouble(2, e.getSalary());
			ps.setInt(3, e.getAge());
			ps.setInt(4, e.getId());
			ps.executeUpdate();
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new RuntimeException(e1);
		}finally{
			DBUtil.closeConnection(conn);
		}
	}
	
	public Employee findById(int id){
		Connection conn=null;
		Employee e=null;
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT * FROM t_emp WHERE id=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				e=new Employee();
				e.setId(rs.getInt("id"));
				e.setEname(rs.getString("ename"));
				e.setSalary(rs.getDouble("salary"));
				e.setAge(rs.getInt("age"));
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new RuntimeException(e1);
		}finally{
			DBUtil.closeConnection(conn);
		}
		return e;
		
	}
	
	public void delete(int id){
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="delete from t_emp where id=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			DBUtil.closeConnection(conn);
		}
	}
	
	
	/**
	 * ��������Ա������Ϣ
	 * 
	 */
	public List<Employee> findAll(){
		Connection conn=null;
		List<Employee> list = new ArrayList<Employee>();
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT * FROM t_emp";
			PreparedStatement ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Integer id=rs.getInt("id");
				String ename=rs.getString("ename");
				Double salary=rs.getDouble("salary");
				Integer age=rs.getInt("age");
				Employee emp=new Employee(id, ename, salary, age);
				list.add(emp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			DBUtil.closeConnection(conn);
		}
		return list;
	}
	public void save(Employee e){
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="INSERT INTO t_emp "
					+ "VALUES(t_emp_seq.nextval,?,?,?)";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, e.getEname());
			ps.setDouble(2, e.getSalary());
			ps.setInt(3, e.getAge());
			ps.executeUpdate();
		} catch (Exception e1) {
			/*
			 * step1.����־(�����ֳ�)
			 */
			e1.printStackTrace();
			/*
			 * step2.�鿴�쳣�Ƿ��ܹ������ָ�,
			 * ������ܹ��ָ�(�������ݿ����ֹͣ,
			 * �����жϵ�,�������쳣���ǿ��Գ�֮Ϊ
			 * ϵͳ�쳣),��ʾ�û��Ժ�����.����ܹ�
			 * �ָ�,�������ָ�.
			 */
			throw new RuntimeException(e1);
		}finally{
			DBUtil.closeConnection(conn);
		}
	}
}
