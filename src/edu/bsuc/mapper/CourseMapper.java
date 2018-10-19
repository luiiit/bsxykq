package edu.bsuc.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import edu.bsuc.entity.Course;
import edu.bsuc.provider.CourseProvide;

public interface CourseMapper {
	
	@SelectProvider(type=CourseProvide.class,method="listSql")
	List<Course> list(Map<String,String> map);

	@Insert(" insert into xx_course_info(course_name,teacher,week_day,begin_time,end_time,begin_date,end_date,clazz,room)"
			+ "values(#{0},#{2},#{3},#{6},#{7},#{4},#{5},#{1},#{8})")
	int insert(String name, Long clazzId, Long teacherId, String weekDay, String beginDate, String endDate,
			String beginTime, String endTime,String room);

	@Select(" select * from xx_course_info where 1 = 1 "
			+ " and (( #{1} <= begin_date and #{2} >= end_date) "
			+ "			or (begin_date <= #{1} and end_date >= #{1})"
			+ "         or (begin_date <= #{2} and end_date >= #{2})"
			+ "         or ( begin_date >= #{1} and end_date >= #{2})"
			+ " ) "
			+ " and ( ("
			+ " DATE_FORMAT(CONCAT('2018-01-01 ',begin_time),'%y-%d-%m %H:%i-%s') <= "
			+ " DATE_FORMAT(CONCAT('2018-01-01 ',#{3}),'%y-%d-%m %H:%i-%s') "
			+ " and DATE_FORMAT(CONCAT('2018-01-01 ',end_time),'%y-%d-%m %H:%i-%s') >= "
			+ " DATE_FORMAT(CONCAT('2018-01-01 ',#{3}),'%y-%d-%m %H:%i-%s' ) )"
			+ " or "
			+ " ( DATE_FORMAT(CONCAT('2018-01-01 ',begin_time),'%y-%d-%m %H:%i-%s') <= "
			+ " DATE_FORMAT(CONCAT('2018-01-01 ',#{4}),'%y-%d-%m %H:%i-%s') "
			+ " and DATE_FORMAT(CONCAT('2018-01-01 ',end_time),'%y-%d-%m %H:%i-%s') >= "
			+ " DATE_FORMAT(CONCAT('2018-01-01 ',#{4}),'%y-%d-%m %H:%i-%s' ) ) "
			+ " or ("
			+ " DATE_FORMAT(CONCAT('2018-01-01 ',begin_time),'%y-%d-%m %H:%i-%s') >= "
			+ " DATE_FORMAT(CONCAT('2018-01-01 ',#{3}),'%y-%d-%m %H:%i-%s') "
			+ " and DATE_FORMAT(CONCAT('2018-01-01 ',end_time),'%y-%d-%m %H:%i-%s') <= "
			+ " DATE_FORMAT(CONCAT('2018-01-01 ',#{4}),'%y-%d-%m %H:%i-%s' ) )"
			+ " or ( "
			+ " DATE_FORMAT(CONCAT('2018-01-01 ',#{3}),'%y-%d-%m %H:%i-%s') <= "
			+ " DATE_FORMAT(CONCAT('2018-01-01 ',begin_time),'%y-%d-%m %H:%i-%s') "
			+ " and DATE_FORMAT(CONCAT('2018-01-01 ',#{4}),'%y-%d-%m %H:%i-%s') >= "
			+ " DATE_FORMAT(CONCAT('2018-01-01 ',end_time),'%y-%d-%m %H:%i-%s' )) "
			+ ") "
			+ " and week_day = #{0} "
			+ " and room= #{5}"
			+ " and id <> #{6}")
	List<Course> checkExistRoom(String weekDay, String beginDate, String endDate, String beginTime, String endTime,
			String room,Long id);
	
	@Select(" select * from xx_course_info where 1 = 1 "
			+ " and (( #{1} <= begin_date and #{2} >= end_date) "
			+ "			or (begin_date <= #{1} and end_date >= #{1})"
			+ "         or (begin_date <= #{2} and end_date >= #{2})"
			+ "         or ( begin_date >= #{1} and end_date >= #{2})"
			+ " ) "
			+ " and ( ("
			+ " DATE_FORMAT(CONCAT('2018-01-01 ',begin_time),'%y-%d-%m %H:%i-%s') <= "
			+ " DATE_FORMAT(CONCAT('2018-01-01 ',#{3}),'%y-%d-%m %H:%i-%s') "
			+ " and DATE_FORMAT(CONCAT('2018-01-01 ',end_time),'%y-%d-%m %H:%i-%s') >= "
			+ " DATE_FORMAT(CONCAT('2018-01-01 ',#{3}),'%y-%d-%m %H:%i-%s' ) )"
			+ " or "
			+ " ( DATE_FORMAT(CONCAT('2018-01-01 ',begin_time),'%y-%d-%m %H:%i-%s') <= "
			+ " DATE_FORMAT(CONCAT('2018-01-01 ',#{4}),'%y-%d-%m %H:%i-%s') "
			+ " and DATE_FORMAT(CONCAT('2018-01-01 ',end_time),'%y-%d-%m %H:%i-%s') >= "
			+ " DATE_FORMAT(CONCAT('2018-01-01 ',#{4}),'%y-%d-%m %H:%i-%s' ) ) "
			+ " or ("
			+ " DATE_FORMAT(CONCAT('2018-01-01 ',begin_time),'%y-%d-%m %H:%i-%s') >= "
			+ " DATE_FORMAT(CONCAT('2018-01-01 ',#{3}),'%y-%d-%m %H:%i-%s') "
			+ " and DATE_FORMAT(CONCAT('2018-01-01 ',end_time),'%y-%d-%m %H:%i-%s') <= "
			+ " DATE_FORMAT(CONCAT('2018-01-01 ',#{4}),'%y-%d-%m %H:%i-%s' ) )"
			+ " or ( "
			+ " DATE_FORMAT(CONCAT('2018-01-01 ',#{3}),'%y-%d-%m %H:%i-%s') <= "
			+ " DATE_FORMAT(CONCAT('2018-01-01 ',begin_time),'%y-%d-%m %H:%i-%s') "
			+ " and DATE_FORMAT(CONCAT('2018-01-01 ',#{4}),'%y-%d-%m %H:%i-%s') >= "
			+ " DATE_FORMAT(CONCAT('2018-01-01 ',end_time),'%y-%d-%m %H:%i-%s' )) "
			+ ") "
			+ " and week_day = #{0} "
			+ " and teacher= #{5} "
			+ " and id <> #{6}")
	List<Course> checkExistTeacher(String weekDay, String beginDate, String endDate, String beginTime, String endTime,
			Long teacherId,Long id);

	@Select(" select * from xx_course_info where 1 = 1 "
			+ " and (( #{1} <= begin_date and #{2} >= end_date) "
			+ "			or (begin_date <= #{1} and end_date >= #{1})"
			+ "         or (begin_date <= #{2} and end_date >= #{2})"
			+ "         or ( begin_date >= #{1} and end_date >= #{2})"
			+ " ) "
			+ " and ( ("
			+ " DATE_FORMAT(CONCAT('2018-01-01 ',begin_time),'%y-%d-%m %H:%i-%s') <= "
			+ " DATE_FORMAT(CONCAT('2018-01-01 ',#{3}),'%y-%d-%m %H:%i-%s') "
			+ " and DATE_FORMAT(CONCAT('2018-01-01 ',end_time),'%y-%d-%m %H:%i-%s') >= "
			+ " DATE_FORMAT(CONCAT('2018-01-01 ',#{3}),'%y-%d-%m %H:%i-%s' ) )"
			+ " or "
			+ " ( DATE_FORMAT(CONCAT('2018-01-01 ',begin_time),'%y-%d-%m %H:%i-%s') <= "
			+ " DATE_FORMAT(CONCAT('2018-01-01 ',#{4}),'%y-%d-%m %H:%i-%s') "
			+ " and DATE_FORMAT(CONCAT('2018-01-01 ',end_time),'%y-%d-%m %H:%i-%s') >= "
			+ " DATE_FORMAT(CONCAT('2018-01-01 ',#{4}),'%y-%d-%m %H:%i-%s' ) ) "
			+ " or ("
			+ " DATE_FORMAT(CONCAT('2018-01-01 ',begin_time),'%y-%d-%m %H:%i-%s') >= "
			+ " DATE_FORMAT(CONCAT('2018-01-01 ',#{3}),'%y-%d-%m %H:%i-%s') "
			+ " and DATE_FORMAT(CONCAT('2018-01-01 ',end_time),'%y-%d-%m %H:%i-%s') <= "
			+ " DATE_FORMAT(CONCAT('2018-01-01 ',#{4}),'%y-%d-%m %H:%i-%s' ) )"
			+ " or ( "
			+ " DATE_FORMAT(CONCAT('2018-01-01 ',#{3}),'%y-%d-%m %H:%i-%s') <= "
			+ " DATE_FORMAT(CONCAT('2018-01-01 ',begin_time),'%y-%d-%m %H:%i-%s') "
			+ " and DATE_FORMAT(CONCAT('2018-01-01 ',#{4}),'%y-%d-%m %H:%i-%s') >= "
			+ " DATE_FORMAT(CONCAT('2018-01-01 ',end_time),'%y-%d-%m %H:%i-%s' )) "
			+ ") "
			+ " and week_day = #{0} "
			+ " and clazz= #{5} "
			+ " and id <> #{6}")
	List<Course> checkExistClazz(String weekDay, String beginDate, String endDate, String beginTime, String endTime,
			Long clazzId,Long id);
	
	@Update(" update  xx_course_info set course_name = #{0},teacher=#{2},week_day=#{3},begin_time=#{6},end_time=#{7},"
			+ " begin_date=#{4},end_date=#{5},clazz=#{1},room=#{8}"
			+ " where id = #{9} ")
	int update(String name, Long clazzId, Long teacherId, String weekDay, String beginDate, String endDate,
			String beginTime, String endTime,String room,Long id);
			
			
	@Select( " select c.id,c.course_name courseName,c.begin_time beginTime, "
			+ " c.end_time endTime,c.begin_date beginDate,c.week_day weekDay, "
			+ " c.end_date endDate,c.room,c.teacher,t.name teacherName,"
			+ "  c.clazz,cl.name clazzName "
			+ "  from xx_course_info c "
			+ " left join xx_teacher_info t on t.id = c.teacher "
			+ " left join xx_clazz_info cl on c.clazz=cl.id "
			+ " where c.id = #{1} ")		
	Course getById(Long id);		
}
