package edu.bsuc.entity;

public class Teacher {
	
	/**id*/
	private String id;
	
	/**教职工编号*/
	private String no;
	
	/**姓名*/
	private  String name;
	
	/** 性别*/
	private  String sex;
	
	/**职称*/
	private String professional;
	
	/**邮箱*/
	private String email;
	
	/**电话*/
	private String phone;

	
	/**所属学院id*/
	private Long institute;
	
	/**所属学院名称*/
	private String instituteName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
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

	public String getProfessional() {
		return professional;
	}

	public void setProfessional(String professional) {
		this.professional = professional;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getInstitute() {
		return institute;
	}

	public void setInstitute(Long institute) {
		this.institute = institute;
	}

	public String getInstituteName() {
		return instituteName;
	}

	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}
	
	
	
}
