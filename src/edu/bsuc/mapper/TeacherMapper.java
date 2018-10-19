package edu.bsuc.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import edu.bsuc.entity.Teacher;
import edu.bsuc.provider.TeacherProvide;

public interface TeacherMapper {

	@SelectProvider(method = "listSql", type = TeacherProvide.class)
	List<Teacher> list(Map<String,String> map);
	
	@Insert("insert into xx_teacher_info(name,no,professional,institute) values(#{0},#{1},#{2},#{3})")
	int insertIntoTeacher(String name, String no, String professional, String institute);

	@Insert(" insert into xx_member(username,real_name,sex,phone,email,password,type) values(#{0},#{1},#{2},#{3},#{4},'888888',1)")
	int insertIntoMember(String no, String name, String sex, String phone, String email);

	@Select(" SELECT t.*,m.phone,m.sex,m.email,i.name instituteName from xx_teacher_info t "
			+ " left join xx_member m on m.username = t.no "
			+ " left join xx_institute_info  i  on i.id = t.institute"
			+ " where t.id = #{0} ")
	Teacher getOneById(Long id);
	
	@Update("update xx_member set phone=#{0},email=#{1} where username = #{2}")
	int updateByNo(String phone,String email,String no);
	
	@Update(" update xx_teacher_info set professional=#{0} where no = #{1}")
	int updateProfessional(String professional,String no);
	
	@Select(" select * from xx_teacher_info where name = #{0}")
	List<Teacher> getByName(String name);
	
}

