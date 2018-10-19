package edu.bsuc.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import edu.bsuc.entity.Clazz;
import edu.bsuc.entity.Student;
import edu.bsuc.provider.StudentProvide;

public interface StudentMapper {

	@SelectProvider(type=StudentProvide.class,method="listSql")
	List<Student> list(Map<String,String> map);
	
	@SelectProvider(type=StudentProvide.class,method="selectClazzSql")
	List<Clazz> select_clazz(Map<String, String> map);
	
	@Insert("insert into xx_student_info(no,name,pphone,clazz,times,path) values(#{0},#{1},#{2},#{3},0,'')")
	int  insertIntoStudent(String no,String name,String pphone,Long clazz);
	
	@Insert(" insert into xx_member(username,real_name,phone,email,sex,type,password) values(#{0},#{1},#{3},#{2},#{4},2,'888888')")
	int insertIntoMember(String no,String name,String email,String phone,String sex);
	
	@Select("select s.*,ma.name majorName,i.name instituteName,m.sex,m.phone,m.email,"
				+ " mt.name mainTeacherName,se.name secretaryName,c.name clazzName  from xx_student_info s "
				+ " left join xx_clazz_info c on c.id = s.clazz "
				+ " left join xx_member m on s.no = m.username "
				+ " left join xx_institute_info i on c.institute = i.id "
				+ " left join xx_major_info ma on ma.id = c.major "
				+ " left join xx_main_teacher_info mt on mt.id = c.main_teacher"
				+ " left join xx_secretary_info se on se.id = c.secretary "
				+ " where s.id = #{0}")
	Student getOneById(Long id);
}
