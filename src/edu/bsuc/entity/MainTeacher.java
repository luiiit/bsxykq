package edu.bsuc.entity;

public class MainTeacher {

	
	//id
	private Integer id;
	//教职工编号
	private String no;
	//名称
	private String name;
	//性别
	private String sex;
	//手机号
	private String phone;
	//邮箱
	private String email;

	//学院
	private Integer institute;
	//学院名称
	private String instituteName;
	
	
	
	public Integer getInstitute() {
		return institute;
	}

	public void setInstitute(Integer institute) {
		this.institute = institute;
	}

	public String getInstituteName() {
		return instituteName;
	}

	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
