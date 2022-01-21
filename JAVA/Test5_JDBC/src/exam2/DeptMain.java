package exam2;
/*
 *	# 아키텍쳐		  
 *				  호출				  호출
 *	DeptMin		<---->	DeptService	<----> DeptDAO			<----> 오라클DB
 *	(dept 테이블 정보 출력)					  DataAccessObject 패턴
 *										  (DB 접근을 전담하는 객체)
 *	
 *	DTO(Data Transfer Object, 전달) - DB의 테이블의 레코드(행)를 받아올 수 있는 객체
 *
 *	# 서비스(Service)						
 *		- 인터페이스 활용
 */

import java.util.List;
import java.util.Scanner;

public class DeptMain {
	public static void main(String[] args) {
		
		while(true) {
			System.out.println("**********************");
			System.out.println("1. 전체 부서 목록");
			System.out.println("2. 특정 부서 목록");
			System.out.println("3. 부서 등록");
			System.out.println("4. 부서 삭제");
			System.out.println("5. 부서 수정");
			System.out.println("6. 트랜잭션 실습");
			System.out.println("0. 종료");
			System.out.println("**********************");
			Scanner scan = new Scanner(System.in);
			System.out.print("메뉴 입력 ==> ");
			int n = scan.nextInt();
			
			if(n == 1) {
				DeptService service = new DeptServiceImpl();
				List<DeptDTO> list = service.selectAll();
				for (DeptDTO deptDTO : list) {
					System.out.println(deptDTO);
				}
				
			} else if (n == 2) {
				System.out.println("검색할 부서 번호 입력하세요");
				int deptno = scan.nextInt();
				
				DeptService service = new DeptServiceImpl();
				DeptDTO dto = service.selectByDeptno(deptno);
				System.out.println(dto);
				
			} else if (n == 3) {
				System.out.println("부서 번호 입력하세요");
				int deptno = scan.nextInt();
				System.out.println("부서명 입력하세요");
				String dname = scan.next();
				System.out.println("부서 위치 입력하세요");
				String loc = scan.next();
				DeptDTO dto = new DeptDTO(deptno, dname, loc);
				
				DeptService service = new DeptServiceImpl();
				int num = service.insert(dto);
				if (num == 1) {
					System.out.println("저장 성공");
				}
				
			} else if (n == 4) {
				System.out.println("부서 번호 입력하세요");
				int deptno = scan.nextInt();
				
				DeptService service = new DeptServiceImpl();
				int num = service.delete(deptno);
				if (num == 1) {
					System.out.println("삭제 성공");
				}
			} else if (n == 5) {
				System.out.println("부서 번호 입력하세요");
				int deptno = scan.nextInt();
				System.out.println("부서명 입력하세요");
				String dname = scan.next();
				System.out.println("부서 위치 입력하세요");
				String loc = scan.next();
				DeptDTO dto = new DeptDTO(deptno, dname, loc);
				
				DeptService service = new DeptServiceImpl();
				int num = service.update(dto);
				if (num == 1) {
					System.out.println("수정 성공");
				}
				
			} else if (n == 6) {
				System.out.println("트랜잭션 실습");
				
				DeptService service = new DeptServiceImpl();
				service.tx();
				
			} else if (n == 0) {
				System.out.println("프로그램 종료");
				System.exit(0);
			}
			
		}//end while

	}
}
