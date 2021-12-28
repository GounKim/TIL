package dto;

public class Lecture extends Subject {
	String lectureNum;
	String teachingProf;
	Student[] enrolledStudent;
	
	public Lecture(String subjectNum, String subjectName, boolean isMajor, String lectureNum, String teachingProf) {
		super(subjectNum, subjectName, isMajor);
		this.lectureNum = lectureNum;
		this.teachingProf = teachingProf;
	}

	public String getLectureNum() {
		return lectureNum;
	}
	public void setLectureNum(String lectureNum) {
		this.lectureNum = lectureNum;
	}
	public String getTeachingProf() {
		return teachingProf;
	}
	public void setTeachingProf(String teachingProf) {
		this.teachingProf = teachingProf;
	}
	public Student[] getEnrolledStudent() {
		return enrolledStudent;
	}
	public void setEnrolledStudent(Student[] enrolledStudent) {
		this.enrolledStudent = enrolledStudent;
	}
	
	
	
	
	
	
	
}
