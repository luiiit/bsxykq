package edu.bsuc.provider;

import java.util.Map;

import com.mysql.jdbc.StringUtils;


public class StudentProvide {

	public String listSql(Map<String,String> map){
		String sql =" select s.*,ma.name majorName,i.name instituteName,m.sex,m.phone,m.email,"
				+ " mt.name mainTeacherName,se.name secretaryName,c.name clazzName  from xx_student_info s "
				+ " left join xx_clazz_info c on c.id = s.clazz "
				+ " left join xx_member m on s.no = m.username "
				+ " left join xx_institute_info i on c.institute = i.id "
				+ " left join xx_major_info ma on ma.id = c.major "
				+ " left join xx_main_teacher_info mt on mt.id = c.main_teacher"
				+ " left join xx_secretary_info se on se.id = c.secretary "
				+ " where 1 = 1 ";
		
		if(map != null){
			String no = map.get("no");
			String phone = map.get("phone");
			String name = map.get("name");
			String institute = map.get("institute");
			String major = map.get("major");
			String clazzId = map.get("clazzId");
			if(!StringUtils.isNullOrEmpty(clazzId)){
				sql += " and s.clazz =  "+clazzId;
			}
			if(!StringUtils.isNullOrEmpty(no)){
				sql += " and s.no like '%"+no+"%' ";
			}
			if(!StringUtils.isNullOrEmpty(phone)){
				sql+= " and m.phone like '%"+phone+"%' ";
			}
			if(!StringUtils.isNullOrEmpty(name)){
				sql += " and s.name like '%"+name+"%' ";
			}
			if(!StringUtils.isNullOrEmpty(institute)){
				sql+= " and i.name like '%"+institute+"%' ";
			}
			if(!StringUtils.isNullOrEmpty(major)){
				sql+= " and ma.name like '%"+major+"%' ";
			}
		}
		
		return sql;
	}
	
	public String selectClazzSql(Map<String,String> map){
		String sql = " select c.*, i.name instituteName,m.name majorName,c.simple_name simpleName from xx_clazz_info c "
				+ " left join xx_institute_info i on c.institute = i.id "
				+ " left join xx_major_info m on m.id = c.major "
				+ " where 1= 1 ";
		if(map != null){
			String name = map.get("name");
			String institute = map.get("institute");
			if(!StringUtils.isNullOrEmpty(name)){
				sql += " and (c.name like '%"+name+"%' or c.simple_name like '%"+name+"%' ";
			}
			if(!StringUtils.isNullOrEmpty(institute)){
				sql+=" and i.name like '%"+institute+"%' ";
			}
		}
		
		return sql;
	}
}
