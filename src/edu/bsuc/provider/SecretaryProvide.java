package edu.bsuc.provider;

import java.util.Map;

import com.mysql.jdbc.StringUtils;


public class SecretaryProvide {

	public String listSql(Map<String,String> map){
		String secretary = "";
		String institute = "";
		String phone = "";
		if(map != null){
			 secretary = map.get("secretary");
			 institute = map.get("institute");
			 phone = map.get("phone");
		}
		
		
		String sql = " select s.id,s.name,s.no,i.id institute,group_concat(i.name) instituteName, "
				+ "  m.sex,m.phone,m.email "
				+ " from  xx_secretary_info s "
				+ " left join xx_institute_info i on i.secretary = s.id "
				+ " left join xx_member m on s.no = m.username "
				+ " where 1=1 ";
		if(!StringUtils.isNullOrEmpty(secretary)){
			sql+= " and s.name like '%"+secretary+"%' ";
		}
		if(!StringUtils.isNullOrEmpty(institute)){
			sql+= " and i.name like '%"+institute+"%' ";
		}
		if(!StringUtils.isNullOrEmpty(phone)){
			sql+= " and m.phone like '%"+phone+"%' ";
		}
		sql+= " group by s.id,s.name,s.no,i.id ";
		
		return sql;
	}
	
}
