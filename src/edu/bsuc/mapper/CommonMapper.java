package edu.bsuc.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

public interface CommonMapper {
	
	@Select("select id from xx_member where username = #{0}")
	List<Map<String,Object>> checkNo(String no);

}
