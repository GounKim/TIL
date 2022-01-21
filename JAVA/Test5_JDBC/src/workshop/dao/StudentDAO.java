package workshop.dao;

import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import workshop.dto.Student;

public class StudentDAO {
	
	public List<Student> selectAll(Connection con) {
		List<Student> list = new ArrayList<Student>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT student_no AS 학번, "
				+ " student_name AS 이름, "
				+ " RPAD(SUBSTR(student_ssn, 0, 8), 14, '*') AS 주민번호, "
				+ " RPAD(SUBSTR(student_address, 0, 10) || '...', 22, ' ') AS 주소, "
				+ " TO_CHAR(entrance_date, 'YYYY/MM/DD') AS 입학년도, "
				+ " absence_yn AS 휴학여부 "
				+ " FROM tb_student "
				+ " ORDER BY 1";
		
		try {
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String stuNo = rs.getString("학번");
				String stuName = rs.getString("이름");
				String stuSsn = rs.getString("주민번호");
				String stuAddress = rs.getString("주소");
				String entDate = rs.getString("입학년도");
				String absYn = rs.getString("휴학여부");
				Student student = new Student(stuNo, stuName, stuSsn, stuAddress, entDate, absYn);
				
				list.add(student);
			}//end while
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}//end selectAll
	
	
	public List<Student> selectByStuName(Connection con, String stuName) {
		List<Student> list = new ArrayList<Student>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT student_no AS 학번, "
				+ " student_name AS 이름, "
				+ " RPAD(SUBSTR(student_ssn, 0, 8), 14, '*') AS 주민번호, "
				+ " CASE WHEN student_address IS NULL THEN '***주소 미상***     ' "
				+ " ELSE SUBSTR(student_address, 0, 10) || '...' END 주소, "
				+ " TO_CHAR(entrance_date, 'YYYY/MM/DD') AS 입학년도, "
				+ " absence_yn AS 휴학여부 "
				+ " FROM tb_student "
				+ " WHERE student_name LIKE ? "
				+ " ORDER BY 1";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + stuName + "%");
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String stuNo = rs.getString("학번");
				stuName = rs.getString("이름");
				String stuSsn = rs.getString("주민번호");
				String stuAddress = rs.getString("주소");
				String entDate = rs.getString("입학년도");
				String absYn = rs.getString("휴학여부");
				Student student = new Student(stuNo, stuName, stuSsn, stuAddress, entDate, absYn);
				
				list.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;
	}// end selectByStuName
	
	
	public List<Student> selectByEntDate(Connection con, Map<String, Integer> year) {
		List<Student> list = new ArrayList<Student>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		

		String sql = "SELECT student_no AS 학번, "
				+ " student_name AS 이름, "
				+ " RPAD(SUBSTR(student_ssn, 0, 8), 14, '*') AS 주민번호, "
				+ " CASE WHEN student_address IS NULL THEN '***주소 미상***      ' "
				+ " ELSE SUBSTR(student_address, 0, 10) || '...' END 주소, "
				+ " TO_CHAR(entrance_date, 'YYYY/MM/DD') AS 입학년도, "
				+ " absence_yn AS 휴학여부 "
				+ " FROM tb_student "
				+ " WHERE entrance_date BETWEEN (TO_DATE(?, 'YYYY')) AND (TO_DATE(?,'YYYY')) "
				+ " ORDER BY 5";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, year.get("startYear"));
			pstmt.setInt(2, year.get("endYear") + 1);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String stuNo = rs.getString("학번");
				String stuName = rs.getString("이름");
				String stuSsn = rs.getString("주민번호");
				String stuAddress = rs.getString("주소");
				String entDate = rs.getString("입학년도");
				String absYn = rs.getString("휴학여부");
				Student student = new Student(stuNo, stuName, stuSsn, stuAddress, entDate, absYn);
				
				list.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}// end selectByEntDate
	
	
	public List<Student> selectByStuNo(Connection con, String[] stuNoArr) {
		List<Student> list = new ArrayList<Student>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT student_no AS 학번, "
				+ " student_name AS 이름, "
				+ " RPAD(SUBSTR(student_ssn, 0, 8), 14, '*') AS 주민번호, "
				+ " CASE WHEN student_address IS NULL THEN '***주소 미상***      ' "
				+ " ELSE SUBSTR(student_address, 0, 10) || '...' END 주소, "
				+ " TO_CHAR(entrance_date, 'YYYY/MM/DD') AS 입학년도, "
				+ " absence_yn AS 휴학여부 "
				+ " FROM tb_student "
//				+ " WHERE student_no in (?)"
				+ " WHERE student_no in (";
		for (int i = 0; i < stuNoArr.length; i++) {
			sql += "?";
			if (i != stuNoArr.length - 1) {
				sql += ", ";
			}
		}
		
		sql += ") ORDER BY 1";
		
		try {
			pstmt = con.prepareStatement(sql);
			
			for (int i = 0; i < stuNoArr.length; i++) {
				pstmt.setString(i + 1, stuNoArr[i].trim());
			}
			
/*
			String stuNos = "";
			for (int i = 0; i < stuNoArr.length; i++) {
				stuNos += ("\'" + stuNoArr[i].trim() + "\'");
				if (i != (stuNoArr.length - 1)) {
					stuNos += ",";
				}
			}
			pstmt.setString(1, stuNos);
*/
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String stuNo = rs.getString("학번");
				String stuName = rs.getString("이름");
				String stuSsn = rs.getString("주민번호");
				String stuAddress = rs.getString("주소");
				String entDate = rs.getString("입학년도");
				String absYn = rs.getString("휴학여부");
				Student student = new Student(stuNo, stuName, stuSsn, stuAddress, entDate, absYn);
				
				list.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return list;
	}// end selectByStuNo
	
	public int updateAbsenceYn(Connection con, String[] stuNoArr) {
		int countUpdated = 0;
		PreparedStatement pstmt = null;
		int arrLen = stuNoArr.length;
		
		String sql = "UPDATE tb_student "
				  + " SET	 absence_yn = 'Y' "
				  + " WHERE student_no IN (";
		for (int i = 0; i < arrLen; i++) {
			sql += (i != arrLen - 1) ? "?, " : "? )";
		}
		
		try {
			pstmt = con.prepareStatement(sql);
			
			for (int i = 0; i < arrLen; i++) {
			pstmt.setString(i + 1, stuNoArr[i].trim());
			}
			
			countUpdated = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		
		return countUpdated;
	}// end updateAbsenceYn


	public int updateCapacity(Connection con) {
		int countUpdated = 0;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE tb_department "
			+ " SET capacity = CASE WHEN capacity BETWEEN 0 AND 20 THEN capacity + 5 "
								+ " WHEN capacity BETWEEN 21 AND 25 THEN capacity + 4 "
								+ " WHEN capacity BETWEEN 26 AND 30 THEN capacity + 3 " 
								+ " ELSE capacity "
			+ " END";
		
		
		try {
			pstmt = con.prepareStatement(sql);
			countUpdated = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return countUpdated;
	}// end updateCapacity


	public List<StudentPoint> findStuPoint(Connection con, String stuNum) {
		List<StudentPoint> list = new ArrayList<StudentPoint>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
//		sql = "SELECT";
		
//		pstmt = con.prepareStatement(sql);
		
		
		
		return list;
	}// end findStuPoint
	
}// end class
