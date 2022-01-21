package exam1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeptInsert {

	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";	// 드라이버 내에 핵심 클래스파일
		String url = "jdbc:oracle:thin:@localhost:1521:xe";	// 오라클의 위치 및 포트번호 + 데이터베이스명의 정보 (기본 포트번호: 1521)
		String userid = "SCOTT";	// 계정명
		String passwd = "TIGER";	// 비밀번호

		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(url, userid, passwd);
		
			String sql = "INSERT into dept (deptno, dname, loc) values(?, ?, ?)";	
			/*
			 *  ?: 바인딩 변수
			 *  oracle에서의 바인딩변수는 &n 
			 *  select * 
			 *  from dept 
			 *  where deptno = &n;
			 *  -> 준비된 sql
			 *  -> 자바의 prepareStatement
			 */
			pstmt = con.prepareStatement(sql);
			
			// ? 대신에 값 설정하기
			// 첫번째 파라미터: 인덱스 -> 위에서 작성한 sql문의 순서
			// 두번째 파라미터: 대입할 값
			pstmt.setInt(1, 2); 
			pstmt.setString(2, "개발");
			pstmt.setString(3, "서울");
			
			int num = pstmt.executeUpdate();
			System.out.println("저장된 레코드 개수: " + num);
			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		

	}	// end main

}	// end class
