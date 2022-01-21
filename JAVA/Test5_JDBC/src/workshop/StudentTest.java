package workshop;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import workshop.dao.StudentPoint;
import workshop.dto.Student;
import workshop.service.StudentService;
import workshop.service.StudentServiceImpl;

public class StudentTest {

	public static void main(String[] args) {
		
		while(true) {
			System.out.println("**********************************");
			System.out.println("\t[ 학생 정보 관리 메뉴 ]\t");
			System.out.println("**********************************");
			System.out.println("1. 전체 학생 목록");
			System.out.println("2. 학생 이름 검색");
			System.out.println("3. 학생 입학년도 범위 검색 (예> 2000부터 2003년까지");
			System.out.println("4. 학생 학번으로 다중 검색 (쉼표 구분자)");
			System.out.println("5. 학생 휴학 일괄수정");
			System.out.println("6. 학과 정원 일괄수정");
			System.out.println("7. 학생 학점 검색");
			System.out.println("0. 종료");
			System.out.println("**********************************");
			System.out.print("메뉴 입력 => ");
			Scanner scan = new Scanner(System.in);
			int num = scan.nextInt();
			
			if(num == 1) {
				StudentService service = new StudentServiceImpl();
				List<Student> list = service.selectAll();
				
				System.out.println("================================================================================");
				System.out.println("학번 \t 이름 \t 주민번호 \t\t 주소 \t\t\t 입학년도 \t\t휴학여부");
				System.out.println("--------------------------------------------------------------------------------");				for(Student student : list) {
					System.out.println(student);
				}
				System.out.println("총 학생 수: " + list.size() + "명");
				
			} else if (num == 2) {
				StudentService service = new StudentServiceImpl();

				System.out.print("검색할 학생명을 입력하시오 => ");
				String stuName = scan.next();
				
				List<Student> list = service.selectByStuName(stuName);
				
				System.out.println("================================================================================");
				System.out.println("학번 \t 이름 \t 주민번호 \t\t 주소 \t\t\t 입학년도 \t\t휴학여부");
				System.out.println("--------------------------------------------------------------------------------");
				for(Student student : list) {
					System.out.println(student);
				}
				System.out.println("총 학생 수: " + list.size() + "명");
					
			} else if (num == 3) {
				StudentService service = new StudentServiceImpl();
				Map<String, Integer> year = new HashMap<String, Integer>();
				
				System.out.print("시작 입학년도 입력하시오 => ");
				year.put("startYear", scan.nextInt());
				System.out.print("끝 입학년도 입력하시오 => ");
				year.put("endYear", scan.nextInt());
				
				List<Student> list = service.selectByEntDate(year);
				for(Student student : list) {
					System.out.println(student);
				}
				System.out.println("총 학생 수: " + list.size() + "명");
			
			} else if (num == 4) {
				StudentService service = new StudentServiceImpl();
				
				System.out.print("검색할 학생의 학번을 입력하시오 => ");
				scan.nextLine();
				String[] stuNoArr = scan.nextLine().split(",");

				System.out.println("================================================================================");
				System.out.println("학번 \t 이름 \t 주민번호 \t\t 주소 \t\t\t 입학년도 \t\t휴학여부");
				System.out.println("--------------------------------------------------------------------------------");
				List<Student> list = service.selectByStuNo(stuNoArr);
				for(Student student : list) {
					System.out.println(student);
				}
				System.out.println("총 학생 수: " + list.size() + "명");
				
			} else if (num == 5) {
				StudentService service = new StudentServiceImpl();
				
				System.out.print("수정할 학생의 학번을 입력하시오 => ");
				scan.nextLine();
				String[] stuNoArr = scan.nextLine().split(",");

				int numOfUpdate = service.updateAbsenceYn(stuNoArr);
				System.out.println("총 변경된 학생 수: " + numOfUpdate + "명");
				
			} else if (num == 6) {
				StudentService service = new StudentServiceImpl();
				
				int numOfUpdate = service.updateCapacity();
				System.out.println("총 변경된 학생 수: " + numOfUpdate + "명");
				
			} else if (num == 7) {
				StudentService service = new StudentServiceImpl();
				
				System.out.print("학생의 학번을 입력하시오 => ");
				String stuNum = scan.next();
				
				System.out.println("============================================================");
				System.out.println("학기 \t 학번 \t 이름 \t 과목명 \t\t 점수 \t학점");
				System.out.println("------------------------------------------------------------");
				List<StudentPoint> list = service.findStuPoint(stuNum);
				for(StudentPoint stuPoint : list) {
					System.out.println(stuPoint.toString());
				}
				
			} else if (num == 0) {
				System.out.println("프로그램이 종료되었습니다.");
				System.exit(0);
			}
			
		}// end while
		

	}//end main

}// end StudentTest 클래스
