package exam2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/*
	 * 	역할? => 핵심 역할은 트랜잭션 처리이다.
	 * 	Connection단위로 commit을 비활성화 활성화가 가능하기 때문에 
	 * 	Connection을 Service에서 관리한다. (ROLLBACK 가능)
	 * 	- DeptService의 추상 메소드 재정의해서 CRUD작업 처리
	 * 	- DP연동 시 필요한 Connection까지 얻는다.
	 * 	   반드시 개별적인 CRUD 메소드 안에서 Connection 맺고 close한다.
	 * 	  (로컬 변수로 처리해야 된다.)
	 * 	- DeptDAO에 Connection을 전달
	 */

public class DeptServiceImpl implements DeptService {
	// 1. 4가지 정보 저장
	String driver = "oracle.jdbc.driver.OracleDriver";	// 드라이버 내에 핵심 클래스파일
	String url = "jdbc:oracle:thin:@localhost:1521:xe";	// 오라클의 위치 및 포트번호 + 데이터베이스명의 정보 (기본 포트번호: 1521)
	String userid = "SCOTT";	// 계정명
	String passwd = "TIGER";	// 비밀번호

	// 2. 드라이버 로딩 -> "oracle.jdbc.driver.OracleDriver" 객체 생성 작업
	// 문자열로된 객체 생성방법
	
	public DeptServiceImpl() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	} // end 생성자

	@Override
	public List<DeptDTO> selectAll() {
		List<DeptDTO> list = null;
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			DeptDAO dao = new DeptDAO();
			list = dao.selectAll(con);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}// end selectAll메소드

	@Override
	public DeptDTO selectByDeptno(int deptno) {
		DeptDTO dto = null;
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			DeptDAO dao = new DeptDAO();
			dto = dao.selectByDeptno(con, deptno);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return dto;
	}// end selectByDeptno 메소드

	@Override
	public int insert(DeptDTO dto) {
		int num = 0;
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			DeptDAO dao = new DeptDAO();
			num = dao.insert(con, dto);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return num;
	}//end insert 메소드

	@Override
	public int delete(int deptno) {
		int num = 0;
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			DeptDAO dao = new DeptDAO(); 
			num = dao.delete(con, deptno);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return num;
	}

	@Override
	public int update(DeptDTO dto) {
		int num = 0;
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			DeptDAO dao = new DeptDAO();
			num = dao.update(con, dto);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return num;
	}

	@Override
	public int tx() {
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			DeptDAO dao = new DeptDAO();
			
			///////////////트랜잭션 처리///////////////
			// insert
			con.setAutoCommit(false);
			
			dao.insert2(con, new DeptDTO(6, "a", "a"));
			
			// delete
			dao.delete2(con, 5);
			con.commit();
			//////////////////////////////////////
			
		} catch (Exception e) {
			try {
				con.rollback();
				System.out.println("tx작업 실패 rollback됨");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				if(con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
 		
		return 0;
	}//end tx
}
