package exam2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * 	역할?	 => DB연동!
 * 	- SQL문 작성
 * 	- PreparedStatement 생성
 * 	- int n = pstmt.executeUpdate()
 *    ResultSet rs = pstmt.executeQuery()
 *  - 결과 데이터를 DeptMain에서 출력하도록
 *    결과 데이터를 List<DeptDTO) 형식으로 만들어서 반환
 */

public class DeptDAO {
	
	// SELECT
	public List<DeptDTO> selectAll(Connection con) {
		// 누적용
		List<DeptDTO> list = new ArrayList<DeptDTO>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// 4. SQL문 작성
		String sql = "SELECT deptno AS no, dname, loc FROM dept";
		
		try {
			// 5. SQL문 전송할 때 사용할 API
			pstmt = con.prepareStatement(sql);
			
			// 6. SQL문 요청 및 반환값 저장
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int deptno = rs.getInt("no");		// 별칭으로도 받아올 수 있음
				String dname = rs.getString(2);		// 위치(순서)로 받아올 수 있음 -> 가독성x, 위치를 알고있지 않으면 사용하기 어려움
				String loc = rs.getString("loc");	// 컬럼명으로 받아옴 -> 가독성o, 권장
				DeptDTO dto = new DeptDTO(deptno, dname, loc);
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}// end selectAll메소드
	
	
	// 부서 번호로 검색 (반드시 PK로 검색)
	public DeptDTO selectByDeptno(Connection con, int deptno) {
		DeptDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT deptno AS no, dname, loc FROM dept WHERE deptno=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, deptno);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String dname = rs.getString(2);		// 위치(순서)로 받아올 수 있음 -> 가독성x, 위치를 알고있지 않으면 사용하기 어려움
				String loc = rs.getString("loc");	// 컬럼명으로 받아옴 -> 가독성o, 권장
				dto = new DeptDTO(deptno, dname, loc);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return dto;
	}// end selectByDeptno 메소드
	
	
	// 저장
	public int insert(Connection con, DeptDTO dto) {
		int num = 0;
		PreparedStatement pstmt = null;
		
		String sql = "INSERT into dept (deptno, dname, loc) VALUES (?,?,?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getDeptno());
			pstmt.setString(2, dto.getDname());
			pstmt.setString(3, dto.getLoc());
			
			num = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return num;
	} //end insert 메소드
	
	
	// 삭제
	public int delete(Connection con, int deptno) {
		int num = 0;
		PreparedStatement pstmt = null;
		
		String sql = "DELETE FROM dept WHERE deptno = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, deptno);
			
			num = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return num;
	}// end delete 메소드
	
	
	// 수정
	public int update(Connection con, DeptDTO dto) {
		int num = 0;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE dept SET dname=?, loc=? WHERE deptno=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(3, dto.getDeptno());
			pstmt.setString(1, dto.getDname());
			pstmt.setString(2, dto.getLoc());
			
			num = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return num;
	}
	
	// 저장
	public int insert2(Connection con, DeptDTO dto) throws Exception {
		int num = 0;
		PreparedStatement pstmt = null;
		
		String sql = "INSERT into dept (deptno, dname, loc) VALUES (?,?,?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getDeptno());
			pstmt.setString(2, dto.getDname());
			pstmt.setString(3, dto.getLoc());
			
			num = pstmt.executeUpdate();
			
		} finally {
			try {
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return num;
	} //end insert 메소드
	
	
	// 삭제
	public int delete2(Connection con, int deptno) throws Exception {
		int num = 0;
		PreparedStatement pstmt = null;
		
		String sql = "DELETE FROM dept WHERE deptno = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, deptno);
			
			num = pstmt.executeUpdate();
			
		} finally {
			try {
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return num;
	}// end delete 메소드
	
	
	
	
	
	
	
	
	
	
}// end 클래스
