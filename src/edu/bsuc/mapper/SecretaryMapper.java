package edu.bsuc.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import edu.bsuc.entity.Secretary;
import edu.bsuc.provider.SecretaryProvide;

public interface SecretaryMapper {

	@SelectProvider(type=SecretaryProvide.class,method="listSql")
	List<Secretary> list(Map<String,String> map);
	
	@Select(" select s.id,s.name,s.no,i.id institute,i.name instituteName, "
			+ "  m.sex,m.phone,m.email "
			+ " from  xx_secretary_info s "
			+ " left join xx_institute_info i on i.secretary = s.id "
			+ " left join xx_member m on s.no = m.username "
			+ " where s.id = #{0}")
	Secretary getById(Long id);
	
	@Update("update xx_member set phone=#{1},email=#{2} where username = #{0}")
	int update(String no,String phone,String email);
	
	@Insert("insert into xx_member(username,sex,phone,email,password,real_name,type) values(#{4},#{1},#{2},#{3},'888888',#{0},0)")
	int insertToMember(String name,String sex,String phone,String email,String no);
	
	@Insert("insert into xx_secretary_info(no,name) values(#{0},#{1})")
	int insertToSecretary(String no,String name);
}
