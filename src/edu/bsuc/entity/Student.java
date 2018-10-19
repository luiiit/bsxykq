package edu.bsuc.entity;

public class Student {

	/**id*/
	private String id;
	/**学号*/
	private String no;
	/**姓名*/
	private String name;
	/**性别*/
	private String sex;
	/**邮箱*/
	private  String email;
	/**手机号*/
	private String phone;
	/**学院id*/
	private Long institute;
	/**学院名称*/
	private String instituteName;
	/**专业id*/
	private Long major;
	/**专业名称*/
	private String majorName;
	/**班级id*/
	private Long clazz;
	/**班级名称*/
	private String clazzName;
	/**父母手机号*/
	private String pphone;
	/**异常信息推送次数*/
	private Integer times;
	/**头像路径*/
	private String path;
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
	public Long getMajor() {
		return major;
	}
	public void setMajor(Long major) {
		this.major = major;
	}
	public String getMajorName() {
		return majorName;
	}
	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}
	public Long getClazz() {
		return clazz;
	}
	public void setClazz(Long clazz) {
		this.clazz = clazz;
	}
	public String getClazzName() {
		return clazzName;
	}
	public void setClazzName(String clazzName) {
		this.clazzName = clazzName;
	}
	public String getPphone() {
		return pphone;
	}
	public void setPphone(String pphone) {
		this.pphone = pphone;
	}
	public Integer getTimes() {
		return times;
	}
	public void setTimes(Integer times) {
		this.times = times;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	
}
