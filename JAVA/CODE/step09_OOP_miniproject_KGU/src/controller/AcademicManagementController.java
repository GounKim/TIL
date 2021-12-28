package controller;

import dto.Lecture;
import dto.People;
import service.AcademicManagementService;

public class AcademicManagementController {
	// controller 생성
	private static AcademicManagementController controller = new AcademicManagementController();
	// service와 연결
	private AcademicManagementService service = AcademicManagementService.getService();
	
	// 생성자
	public AcademicManagementController() { }

	// getter()
	public static AcademicManagementController getController() {
		return controller;
	}
	

	// 로그인 (특정 사람 검색)
	public boolean login(String peopleID) {
		
		return false;
	}
	
	// 등록 (새로운 사람 등록)
	public boolean registration(People people) {
		
		return false;
	}
	
	// 교수: 과목 개설
	public boolean openLecture(Lecture lecture) {
		
		return false;
	}
	
	// 학생: 수강 신청
	public boolean enrollLecture(String lectureID, String student) {
		
		return false;
	}
	
	// 등록된 강의 목록
	public Lecture[] showOpenedLectures() {
		
		return null;
	}
	

	// 교수별 개설된 강의 목록
	
	
	// 학생별 수강신청한 과목 목록
	
}
