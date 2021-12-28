package dto;

import java.util.HashMap;

public class Student extends People{
	private HashMap<Lecture[], Integer> enrolledLectures;	// 신청과목명, 성적

	public HashMap<Lecture[], Integer> getEnrolledLectures() {
		return enrolledLectures;
	}

	public void setEnrolledLectures(HashMap<Lecture[], Integer> enrolledLectures) {
		this.enrolledLectures = enrolledLectures;
	}

}
