package service;

import java.util.ArrayList;

import controller.AcademicManagementController;
import dto.Lecture;
import dto.People;
import dto.Professor;
import dto.Student;
import model.AcademicManagementDB;

public class AcademicManagementService {
	// service 생성
	private static AcademicManagementService service = new AcademicManagementService();
	// db연결과 연결
	private AcademicManagementDB db = AcademicManagementDB.getDB();
	
	// getter()
	public static AcademicManagementService getService() {
		return service;
	}
	
	// DB에 학생 데이터를 리스트로 가져오기
	public ArrayList<Student> getAllStudents() {
		return db.getStudents();
	}
	// DB에 교수 데이터를 리스트로 가져오기
	public ArrayList<Professor> getAllProfessors() {
		return db.getProfessors();
	}
	// 등록된 강의 목록 : DB에 강의 데이터를 리스트로 가져오기
	public ArrayList<Lecture> getAllLectures() {
		return db.getLectures();
	}
	
	// 교수별 개설된 과목 목록
	
		
	// 학생별 수강신청한 과목 목록
	


	// 특정 사람 검색

	
	// 새로운 학생 추가
	
	// 새로운 교수 추가

	// 새로운 강의 추가

	
	// 수강신청(Student의 enrolledLectures에 추가, Lecture의  enrolledStudent에 추가)
	

	
	
	
}
