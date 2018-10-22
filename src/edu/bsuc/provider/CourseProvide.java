package edu.bsuc.provider;

import java.util.Map;

import com.mysql.jdbc.StringUtils;


public class CourseProvide {

	public String listSql(Map<String,String> map){
		String courseName = map.get("courseName");
		String teacher = map.get("teacher");
		String weekDay = map.get("weekDay");
		String clazz = map.get("clazz");
		String sql =  " select c.id,c.course_name courseName,c.begin_time beginTime, "
				+ " c.end_time endTime,c.begin_date beginDate,c.week_day weekDay, "
				+ " c.end_date endDate,c.room,c.teacher,t.name teacherName,"
				+ "  c.clazz,cl.name clazzName "
				+ "  from xx_course_info c "
				+ " left join xx_teacher_info t on t.id = c.teacher "
				+ " left join xx_clazz_info cl on c.clazz=cl.id "
				+ " where 1=1 ";
		if(!StringUtils.isNullOrEmpty(courseName)){
			sql  += " and c.course_name like '%"+courseName+"%' ";
		}
		
		if(!StringUtils.isNullOrEmpty(teacher)){
			sql += " and t.name like '%"+teacher+"%' " ;
		}
		
		if(!StringUtils.isNullOrEmpty(weekDay)){
			sql+=" and c.week_day  = "+weekDay+" ";
		}
		if(!StringUtils.isNullOrEmpty(clazz)){
			sql+=" and (cl.name like '%"+clazz+"%' or cl.simple_name like '%"+clazz+"%' )" ;
		}
		
		sql+= " order by cl.name asc ";
		return sql;
	}
}
