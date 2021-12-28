[ step09_OOP_miniproject_김고운 ]

1. 주제 : 수강신청 시스템

2. 구조 : 
	- DTO
		- People: 고유번호(교수번호, 학번), 소속학과, 생년월일, 이름, 전화번호 등
			- Professor: 개설한 과목
			- Student: 수강과목
		- Subject: 과목번호, 과목명, 전공수업여부(T/F)
			- Lecture : 강좌 번호, 개설한 교수, 수강 학생, 성적(?)
		
3. 기능 :
	- Boolean login(String peopleID)
	- Boolean registration(People people)
	- Boolean openLecture(Lecture lecture)
	- Boolean enrollLecture(String lectureID, String student)
	- Subject[] showOpenedLectures()
	- Student[] showEnrolledStudent(String lectureName)
	- Lecture findLecture(String professorName)
	
4. 실행 :
	- 학생 또는 교수로 로그인
	- 학생 또는 교수 등록
	- 교수로 로그인
		- 원하는 과목 개설
		- 자신이 개설한 과목 & 등록된 학생들 확인 가능
	- 학생으로 로그인
		- 등록된 강의 목록 출력 -> 수강할 과목 선택
		- 수강신청한 수업
		