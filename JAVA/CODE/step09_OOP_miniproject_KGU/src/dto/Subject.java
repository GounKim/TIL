package dto;

public class Subject {
	String subjectNum;
	String subjectName;
	boolean isMajor;
	
	public Subject(String subjectNum, String subjectName, boolean isMajor) {
		this.subjectNum = subjectNum;
		this.subjectName = subjectName;
		this.isMajor = isMajor;
	}

	public String getSubjectNum() {
		return subjectNum;
	}

	public void setSubjectNum(String subjectNum) {
		this.subjectNum = subjectNum;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public boolean isMajor() {
		return isMajor;
	}

	public void setMajor(boolean isMajor) {
		this.isMajor = isMajor;
	}
	
	
}
