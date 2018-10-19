package edu.bsuc.provider;

import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.mysql.cj.util.StringUtils;


public class MajorProvide {

	public String listSql(Map<String, String> map){
		String sql =" SELECT m.*,i.name instituteName "
				+ " FROM xx_major_info m "
				+ " left join xx_institute_info i on m.institute = i.id"
				+ " where 1=1 ";
		if(map != null){
			String name =map.get("major");
			String institute = map.get("institute");
			if(!StringUtils.isNullOrEmpty(name)){
				sql += " and m.name like '%"+name+"%' ";
			}
			if(!StringUtils.isNullOrEmpty(institute)){
				sql+= " and m.institute ="+institute;
			}
		}
		
		return sql;
	}
}
