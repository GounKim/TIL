package workshop.dao;

import workshop.dto.Student;

public class StudentPoint extends Student {
	private String termNo;
	private String className;
	private double point;
	private String grade;
	
	public StudentPoint() {
		super();
	}
	public StudentPoint(String stuNo, String stuName, String termNo, String className, double point, String grade) {
		super(stuNo, stuName);
		this.termNo = termNo;
		this.className = className;
		this.point = point;
		this.grade = grade;
	}
	public String getTermNo() {
		return termNo;
	}
	public void setTermNo(String termNo) {
		this.termNo = termNo;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public double getPoint() {
		return point;
	}
	public void setPoint(double point) {
		this.point = point;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	@Override
	public String toString() {
		return "StudentPoint [termNo =" + termNo + ", stuNo =" + getStuNo() + ", stuName =" + getStuName() + ", className=" + className + ", point=" + point + ", grade=" + grade
				+ "]";
	}
	
	
	

}
