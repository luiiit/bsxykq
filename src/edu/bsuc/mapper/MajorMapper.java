package edu.bsuc.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import edu.bsuc.entity.Institute;
import edu.bsuc.entity.Major;
import edu.bsuc.provider.MajorProvide;

public interface MajorMapper {
	
	@SelectProvider(type=MajorProvide.class,method="listSql")
	List<Major> list(Map<String,String> map);

	@Select(" select * from xx_major_info where id = #{0}")
	Major getMajorById(Long id);

	@Select(" select * from xx_major_info where name = #{0}")
	Major checkExists(String name);

	@Update(" update xx_major_info set institute = #{1},name=#{2} where id = #{0}")
	void update(Long id, Long institute, String name);

	@Insert(" insert into xx_major_info(name,institute) values(#{1},#{0})")
	void save(Long institute, String name);
	
}
