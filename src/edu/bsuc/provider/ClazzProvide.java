package edu.bsuc.provider;

import java.util.Map;

import com.mysql.cj.util.StringUtils;


public class ClazzProvide {

	public String listSql(Map<String,String> map){
		
		 String sql = " select c.*,c.simple_name simpleName,i.name instituteName,ma.name majorName,"
		 		+ " s.name secretaryName,m.name mainTeacherName from xx_clazz_info c "
		 		+ " left join xx_institute_info i on c.institute = i.id "
		 		+ " left join xx_secretary_info s on c.secretary = s.id "
		 		+ " left join xx_main_teacher_info m on m.id = c.main_teacher "
		 		+ " left join xx_major_info ma on c.major = ma.id "
		 		+ " where 1=1 ";
		 
			if(map != null){
				String name = map.get("name");
				String institute = map.get("institute");
				 if(!StringUtils.isNullOrEmpty(name)){
					 sql += " and ( c.name like '%"+name+"%' or"
					 		+ " c.simple_name like '%"+name+"%') ";
				 }
				 if(!StringUtils.isNullOrEmpty(institute)){
					 sql+=" and i.name like '%"+institute+"%' ";
				 }
			}
		return sql;
	}
}
