package exam2;

// dept 테이블의 하나의 레코드 저장
public class DeptDTO {
	
	// 컬럼명과 동일하게 변수이름 부여(관례)
	private int deptno;
	private String dname;
	private String loc;
	
	public DeptDTO() {
		super();
	}

	public DeptDTO(int deptno, String dname, String loc) {
		super();
		this.deptno = deptno;
		this.dname = dname;
		this.loc = loc;
	}

	public int getDeptno() {
		return deptno;
	}

	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	@Override
	public String toString() {
		return "DetpDTO [deptno=" + deptno + ", dname=" + dname + ", loc=" + loc + "]";
	}
	
}
