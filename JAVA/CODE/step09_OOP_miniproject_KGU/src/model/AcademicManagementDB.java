package model;

import java.util.ArrayList;

import dto.Lecture;
import dto.Professor;
import dto.Student;

public class AcademicManagementDB {
	private static AcademicManagementDB db = new AcademicManagementDB();
	private ArrayList<Student> students = new ArrayList<>();
	private ArrayList<Professor> professors = new ArrayList<>();
	private ArrayList<Lecture> lectures = new ArrayList<>();
	
	private AcademicManagementDB() {}

	public static AcademicManagementDB getDB() {
		return db;
	}

	public ArrayList<Student> getStudents() {
		return students;
	}

	public void insertStudents(Student newStudent) {
		students.add(newStudent);
	}

	public ArrayList<Professor> getProfessors() {
		return professors;
	}

	public void insertProfessors(Professor newProfessor) {
		professors.add(newProfessor);
	}

	public ArrayList<Lecture> getLectures() {
		return lectures;
	}

	public void insertLectures(Lecture newLecture) {
		lectures.add(newLecture);
	}
	
	
}
