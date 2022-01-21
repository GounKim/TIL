package exam2;

import java.util.List;

/*
 * 	역할?
 * 		-CRUD 할 수 있는 추상 메소드 정의
 * 			C-Crate(저장인 insert의미)
 * 			R-Read(조회인 Select의미)
 * 			U-Update
 * 			D-Delete
 */

public interface DeptService {
	
	// SELECT
	public List<DeptDTO> selectAll();
	public DeptDTO selectByDeptno(int deptno);
	public int insert(DeptDTO dto);
	public int delete(int deptno);
	public int update(DeptDTO dto);
	
	// 트랜잭션 처리 실습 메소드
	public int tx();
}
