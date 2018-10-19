package edu.bsuc.entity;

import java.util.Date;

public class Course {
	private Integer id;
	//课程名称
	private  String courseName;
	//教师id
	private Integer teacher;
	//教师姓名
	private String teacherName;
	//周几
	private int weekDay;
	//开始日期
	private Date beginDate;
	//结束日期
	private Date endDate;
	//开始时间
	private String beginTime;
	//结束时间
	private String endTime;
	//班级id
	private Integer clazz;
	//班级名称
	private String clazzName;
	//上课教室
	private String room;
	public Integer getId() {
		return id;
	}
	public String getCourseName() {
		return courseName;
	}
	public Integer getTeacher() {
		return teacher;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public int getWeekDay() {
		return weekDay;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public Integer getClazz() {
		return clazz;
	}
	public String getClazzName() {
		return clazzName;
	}
	public String getRoom() {
		return room;
	}
	
	
	
}
