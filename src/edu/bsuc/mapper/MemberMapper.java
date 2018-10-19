package edu.bsuc.mapper;

import org.apache.ibatis.annotations.Select;

import edu.bsuc.entity.Member;

public interface MemberMapper {
	
	@Select("select * from xx_member where id = #{0}")
	Member getById(Integer id);
}
