package edu.bsuc.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import edu.bsuc.entity.Institute;
import edu.bsuc.entity.Secretary;
import edu.bsuc.provider.InstituteProvide;

public interface InstituteMapper {

	/**列表*/
	@SelectProvider(type=InstituteProvide.class,method="list")
	List<Map<String,Object>> list(Map<String,String> map);
	
	/**秘书列表*/
	@Select("select id,name from xx_secretary_info")
	List<Map<String,Object>> showSecretary();
	
	@Select("select *from xx_institute_info where name = #{0}")
	Institute checkExist(String name);
	
	@Insert("insert into xx_institute_info(name,secretary) values(#{1},#{0})")
	int insertInstitute(Long secretary,String name);
	
	@Select("Select * from xx_institute_info where id = #{0}")
	Map<String,Object> getInstituteById(Long id);
	
	@Insert("update xx_institute_info set secretary = #{1},name = #{2} where id = #{0}")
	int updateInstitute(Long id,Long secretary,String name);
	
	@Select("select * from xx_institute_info where secretary = #{0}")
	Institute checkExists(Long id);

	@Select(" select s.* from xx_secretary_info s "
			+ " left join xx_institute_info i on i.secretary = s.id"
			+ " where i.id = #{0}")
	Secretary getSecretaryById(Long institute);
}
