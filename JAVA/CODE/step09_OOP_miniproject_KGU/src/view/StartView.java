package view;

import java.util.Scanner;

public class StartView {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("====정보 입력====");
		System.out.println("1. 학생 로그인");
		System.out.println("2. 교수 로그인");
		System.out.println("3. 학생 등록");
		System.out.println("4. 교수 등록");
		System.out.println("5. 관리자 로그인");
		System.out.println("===============");
		System.out.print("번호를 입력하세요: ");
		int identity = scan.nextInt();
		
		System.out.println();
		
		switch(identity) {
			case 1:
				System.out.println("====학생:" + "StudentName" + "====");
				System.out.println("1. 수강신청");
				System.out.println("2. 정보수정");
				System.out.println("==================");
				
				System.out.println("====수강신청(개설과목)====");
				
				// 개설된 과목 출력
				
				System.out.println("======================");
			
			case 2:
				System.out.println("====교수:" + "ProfessorName" + "====");
				System.out.println("1. 강좌 개설");
				System.out.println("2. 개설한 과목");
				System.out.println("3. 정보수정");
				System.out.println("==================");
		}
	}

}
