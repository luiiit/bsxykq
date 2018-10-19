package edu.bsuc.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import edu.bsuc.entity.MainTeacher;
import edu.bsuc.provider.MainTeacherProvide;

public interface MainTeacherMapper {

	@SelectProvider(type=MainTeacherProvide.class,method="listSql")
	List<MainTeacher> list(Map<String,String> map);

	@Insert(" insert into xx_member(username,real_name,sex,phone,email,password,type) "
			+ " values(#{0},#{1},#{2},#{3},#{4},'888888',3)")
	void insertIntoMember(String no, String name, String sex, String phone, String email);

	@Insert(" insert into xx_main_teacher_info(no,name,institute) values(#{0},#{1},#{2})")
	void insertIntoMainTeacher(String no, String name, Long institute);
	
	@Select(" select t.*,m.sex,m.email,m.phone,i.name instituteName from xx_main_teacher_info t"
			+ " left join xx_member m on m.username = t.no "
			+ " left join xx_institute_info i on i.id = t.institute"
			+ " where t.id = #{0}")
	MainTeacher getOneById(Long id);

	@Update(" update xx_member set email = #{1},phone=#{2} where username = #{0}")
	int updateMember(String no,String email,String phone);
}

