package workshop.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import workshop.dao.StudentDAO;
import workshop.dao.StudentPoint;
import workshop.dto.Student;

public class StudentServiceImpl implements StudentService {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userid = "workshop";
	String passwd = "workshop";
	
	public StudentServiceImpl() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}// end 생성자

	
	@Override
	public List<Student> selectAll() {
		List<Student> list = null;
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			StudentDAO dao = new StudentDAO();
			list = dao.selectAll(con);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}// end selectAll


	@Override
	public List<Student> selectByStuName(String stuName) {
		List<Student> list = null;
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			StudentDAO dao = new StudentDAO();
			list = dao.selectByStuName(con, stuName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}// end selectByStuName


	@Override
	public List<Student> selectByEntDate(Map<String, Integer> year) {
		List<Student> list = null;
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			StudentDAO dao = new StudentDAO();
			list = dao.selectByEntDate(con, year);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return list;
	}// end selectByEntDate


	@Override
	public List<Student> selectByStuNo(String[] stuNoArr) {
		List<Student> list = null;
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			StudentDAO dao = new StudentDAO();
			list = dao.selectByStuNo(con, stuNoArr);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}// end selectByStuNo


	@Override
	public int updateAbsenceYn(String[] stuNoArr) {
		int countUpdated = 0;
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			StudentDAO dao = new StudentDAO();
			countUpdated = dao.updateAbsenceYn(con, stuNoArr);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return countUpdated;
	}// end updateAbsenceYn


	@Override
	public int updateCapacity() {
		int countUpdated = 0;
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			StudentDAO dao = new StudentDAO();
			countUpdated = dao.updateCapacity(con);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return countUpdated;
	}// end updateCapacity


	@Override
	public List<StudentPoint> findStuPoint(String stuNum) {
		List<StudentPoint> list = null;
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			StudentDAO dao = new StudentDAO();
			list = dao.findStuPoint(con, stuNum);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	
	
	
	
	
}
