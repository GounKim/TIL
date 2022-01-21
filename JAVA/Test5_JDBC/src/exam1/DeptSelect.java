package exam1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeptSelect {

	public static void main(String[] args) {
		// 1. 4가지 정보 저장
		String driver = "oracle.jdbc.driver.OracleDriver";	// 드라이버 내에 핵심 클래스파일
		String url = "jdbc:oracle:thin:@localhost:1521:xe";	// 오라클의 위치 및 포트번호 + 데이터베이스명의 정보 (기본 포트번호: 1521)
		String userid = "SCOTT";	// 계정명
		String passwd = "TIGER";	// 비밀번호

		// 2. 드라이버 로딩 -> "oracle.jdbc.driver.OracleDriver" 객체 생성 작업
		// 문자열로된 객체 생성방법
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 3. Connection 맺기  -> 데이터베이스에 연결(세션 생성)
			con = DriverManager.getConnection(url, userid, passwd);
		
			// 4. SQL문 작성
			String sql = "SELECT deptno AS no, dname, loc FROM dept";
		
			// 5. SQL문 전송할 때 사용할 API
			pstmt = con.prepareStatement(sql);
		
			// 6. SQL문 요청 및 반환값 저장
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int deptno = rs.getInt("no");		// 별칭으로도 받아올 수 있음
				String dname = rs.getString(2);		// 위치(순서)로 받아올 수 있음 -> 가독성x, 위치를 알고있지 않으면 사용하기 어려움
				String loc = rs.getString("loc");	// 컬럼명으로 받아옴 -> 가독성o, 권장
				System.out.println(deptno + "\t" + dname + "\t" + loc);
			}

		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 7. 자원 반남 -> 사용했던 객체의 역순으로 close
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}


// 자바에서 외부 자원 (파일/DB) 사용할 때 필수작업
// 1. open
// 2. close
// 즉 closㄷ는 반드시 실행되어야 함 -> finally에 작성