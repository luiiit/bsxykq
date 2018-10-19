package edu.bsuc.entity;

public class Major {
	/**id*/
	private Long id;
	/**专业名称*/
	private String name;
	
	/**学院id*/
	private Integer institute;
	
	/**学院名称*/
	private String instituteName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	

}
