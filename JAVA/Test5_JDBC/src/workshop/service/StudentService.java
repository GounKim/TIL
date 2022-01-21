package workshop.service;

import java.util.Map;
import java.util.List;

import workshop.dao.StudentPoint;
import workshop.dto.Student;

public interface StudentService {
	
	public List<Student> selectAll();
	public List<Student> selectByStuName(String stuName);
	public List<Student> selectByEntDate(Map<String, Integer> year);
	public List<Student> selectByStuNo(String[] stuNoArr);
	public int updateAbsenceYn(String[] stuNoArr);
	public int updateCapacity();
	public List<StudentPoint> findStuPoint(String stuNum);
}
