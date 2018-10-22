package edu.bsuc.provider;

import java.util.Map;

import com.mysql.jdbc.StringUtils;


public class MainTeacherProvide {

	public  String listSql(Map<String,String> map){
		String sql = " select t.*,m.sex,m.phone,m.email,i.name instituteName from xx_main_teacher_info t "
				+ " left join xx_member m on m.username = t.no "
				+ " left join xx_institute_info i on t.institute = i.id"
				+ " where 1= 1 ";
				
		if(map != null){
			String name = map.get("name");
			String phone= map.get("phone");
			if(!StringUtils.isNullOrEmpty(name)){
				sql += " and t.name like '%"+name+"%' ";
			}
			if(!StringUtils.isNullOrEmpty(phone)){
				sql+= " and m.phone like '%"+phone+"%' ";
			}
			
		}
		return sql;
	}
}
