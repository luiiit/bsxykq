package edu.bsuc.entity;

public class Member {

	/**主键id*/
	private Integer id;
	
	/**登陆用户名  即学号*/
	private String username;
	
	/**密码*/
	private String password;
	
	/**身份类型 0：教学秘书 1：教师 2：学生*/
	private Integer type;
	
	/**微信openId*/
	private String openId;
	
	/**邮箱*/
	private  String email;
	
	/**手机号*/
	private String phone;
	
	/**微信号*/
	private String wechat;
	
	/**真实姓名*/
	private String real_name;

	/**头像路径*/
	private String path;
	
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
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

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}
	
}
