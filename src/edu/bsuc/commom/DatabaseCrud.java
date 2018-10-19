package edu.bsuc.commom;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseCrud {
	@Autowired
	private DataSource dataSource;
	
	/**
	 * @param sql 
	 * @param values sql占位符参数值
	 * @return 如果是查询，成功返回结果集，失败返回null；如果是增删改，成功返回true，失败返回false；
	 */
	public Object doCrud(String sql ,Object... values){
		boolean isSelect = sql.trim().toLowerCase().startsWith("select");
		try {
			//PreparedStatement pstmt = JdbcCommon.getConnection().prepareStatement(sql);
			PreparedStatement pstmt = dataSource.getConnection().prepareStatement(sql);
			if(null != values && 0 != values.length){
				for (int i = 0; i < values.length; i++) {
					//给sql中的占位符（?）设置值
					pstmt.setObject(i+1, values[i]);
				}
			}
			System.out.println("doCrud: "+sql);
			return isSelect ? pstmt.executeQuery() : pstmt.executeUpdate()>0;
		} catch (SQLException e) {
			e.printStackTrace();
			return isSelect ? null : false;
		}
	}
	
}
