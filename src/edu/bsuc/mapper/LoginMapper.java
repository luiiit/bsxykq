package edu.bsuc.mapper;

import org.apache.ibatis.annotations.Select;

import edu.bsuc.entity.Member;

public interface LoginMapper {

	@Select("select * from xx_member where username=#{0} and password=#{1}")
	Member login(String username,String password);

}
