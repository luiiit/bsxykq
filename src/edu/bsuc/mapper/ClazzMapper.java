package edu.bsuc.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import edu.bsuc.entity.Clazz;
import edu.bsuc.entity.MainTeacher;
import edu.bsuc.entity.Major;
import edu.bsuc.provider.ClazzProvide;

public interface ClazzMapper {

	@SelectProvider(type=ClazzProvide.class,method="listSql")
	List<Clazz> list(Map<String,String> map);

	@Select(" select m.* from xx_major_info m "
			+ " where institute = #{0}")
	List<Major> getMajorByInstitute(Long institute);
	
	@Select("select * from xx_main_teacher_info where institute = #{0}")
	List<MainTeacher> getMainTeacherByInstitute(Long institute);

	@Insert(" insert into xx_clazz_info(name,simple_name,institute,secretary,major,main_teacher)"
			+ " values(#{0},#{1},#{2},#{3},#{4},#{5})")
	void save(String name, String simpleName, Long institute, Long secretary, Long major, Long mainTeacher);

	@Select(" select c.*,c.simple_name simpleName,i.name instituteName,ma.name majorName,"
	 		+ " s.name secretaryName,m.name mainTeacherName from xx_clazz_info c "
	 		+ " left join xx_institute_info i on c.institute = i.id "
	 		+ " left join xx_secretary_info s on c.secretary = s.id "
	 		+ " left join xx_main_teacher_info m on m.id = c.main_teacher "
	 		+ " left join xx_major_info ma on c.major = ma.id "
	 		+ " where c.id = #{0} ")
	Clazz getById(Long id);

	@Update("update xx_clazz_info set main_teacher = #{1} where id = #{0}")
	void updateClazz(Long id, Long mainTeacher);
	
	@Select(" select id from xx_clazz_info where name = #{0} or simple_name=#{0} " )
	List<Map<String,Object>> checkExise(String name);
}
