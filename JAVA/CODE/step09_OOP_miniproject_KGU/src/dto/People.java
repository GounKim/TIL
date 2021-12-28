package dto;

public class People {
	private String personalID;	// 교수번호 or 학번
	private String major;
	private String birth;
	private String name;
	private String phone;
	
	public People() {}
	
	public People(String personalID, String major, String birth, String name, String phone) {
		super();
		this.personalID = personalID;
		this.major = major;
		this.birth = birth;
		this.name = name;
		this.phone = phone;
	}
	
	public String getPersonalID() {
		return personalID;
	}
	public void setPersonalID(String personalID) {
		this.personalID = personalID;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
