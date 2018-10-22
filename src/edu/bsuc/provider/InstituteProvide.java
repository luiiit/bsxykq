package edu.bsuc.provider;

import java.util.Map;

import com.mysql.jdbc.StringUtils;


public class InstituteProvide {

	public String list(Map<String,String> map){
		String institute = "";
		String secretatry ="";
		if(map != null){
			 institute = map.get("institute");
			secretatry = map.get("secretatry");
		}
		String sql = "Select i.id,si.name secretary,i.name institute "
				+ " from xx_institute_info i "
				+ "left join xx_secretary_info si on si.id = i.secretary "
				+ " where 1=1 ";
		if(!StringUtils.isNullOrEmpty(institute)){
			sql += " and i.name like '%"+institute+"%'";
		}
		if(!StringUtils.isNullOrEmpty(secretatry)){
			sql += " and si.name like '%"+secretatry+"%' ";
		}
		return sql;
	}
}
