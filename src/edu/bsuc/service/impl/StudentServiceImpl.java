package edu.bsuc.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mysql.jdbc.StringUtils;

import edu.bsuc.mapper.ClazzMapper;
import edu.bsuc.mapper.CommonMapper;
import edu.bsuc.mapper.StudentMapper;
import edu.bsuc.service.StudentService;
import edu.bsuc.utils.RegCheck;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

@Service("studentServiceImpl")
public class StudentServiceImpl implements StudentService{

	
	@Autowired
	private CommonMapper commonMapper;
	@Autowired
	private ClazzMapper clazzMapper;
	@Autowired
	private StudentMapper studentMapper;
	/**
	 * 先校验格式是否正确
	 * */
	public Map<String,Object> importProduct(File tempFile, MultipartFile file) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		if (!tempFile.getParentFile().exists()) {
			tempFile.getParentFile().mkdirs();
		}
		int rows = 0;
		Sheet sheet = null;
		Workbook wb = null;
		try {
			file.transferTo(tempFile);
			 wb = Workbook.getWorkbook(tempFile);
			 sheet = wb.getSheets()[0];
			 rows = sheet.getRows();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Cell cell = null;
		String no = "";//学号
		String name = "";//姓名
		String sex = "";//性别
		String email = "";//邮箱
		String phone = "";//手机号
		String pphone = "";//父母手机号
		String clazz = "";//班级名称
		
		
		String temno = "";//临时学号
		
		StringBuilder errorReason = new StringBuilder();
		for (int i = 1; i < rows; i++) {
			cell = sheet.getCell(0, i);
			no = cell.getContents().trim();
			if(StringUtils.isNullOrEmpty(no)){
				errorReason.append("第"+(i+1)+"行，学号为空<br />");
			}else{
				if(commonMapper.checkNo(no).size() >0){
					errorReason.append("第"+(i+1)+"行，教职工编号已存在<br />");
				}
			}
			
			//判断是否存在于本表的其他行
			for(int j = 0;j<i;j++){
				cell = sheet.getCell(0,j);
				 temno = cell.getContents().trim();
				 if(no.equals(temno)){
					 errorReason.append("第"+(i+1)+"行，学号已在第"+(j+1)+"行存在<br/>");
				 }
			}
			
			
			cell=sheet.getCell(1,i);
			name = cell.getContents().trim();
			if(StringUtils.isNullOrEmpty(name)){
				errorReason.append("第"+(i+1)+"行，姓名为空<br />");
			}
			
			cell= sheet.getCell(2,i);
			sex = cell.getContents().trim();
			if(!StringUtils.isNullOrEmpty(sex)){
				if(!sex.equals("男")&&!sex.equals("女"))
				errorReason.append("第"+(i+1)+"行，性别只能为男或女<br/>");
			}
			cell = sheet.getCell(3,i);
			email = cell.getContents().trim();
			if(!StringUtils.isNullOrEmpty(email)){
				if(!RegCheck.emailFormat(email)){
					errorReason.append("第"+(i+1)+"行，邮箱格式不对<br/>");
				}
			}
			
			cell=sheet.getCell(4,i);
			phone = cell.getContents().trim();
			if(!StringUtils.isNullOrEmpty(phone)){
				if(!RegCheck.isChinaPhoneLegal(phone)){
					errorReason.append("第"+(i+1)+"行，手机号格式有误<br/>");
				}
			}
			
			
			if(StringUtils.isNullOrEmpty(phone)&&StringUtils.isNullOrEmpty(email)){
				errorReason.append("第"+(i+1)+"行，手机号码和邮箱必须有一个<br/>");
			}
			
			cell=sheet.getCell(5,i);
			pphone = cell.getContents().trim();
			if(!StringUtils.isNullOrEmpty(pphone)){
				if(!RegCheck.isChinaPhoneLegal(pphone)){
					errorReason.append("第"+(i+1)+"行，父母手机号格式有误<br/>");
				}
			}
			
			cell=sheet.getCell(6,i);
			clazz = cell.getContents().trim();
			
			if(StringUtils.isNullOrEmpty(clazz)){
				errorReason.append("第"+(i+1)+"行，班级不能为空<br/>");
			}else{
				List<Map<String, Object>> list = clazzMapper.checkExise(clazz);
				if(list.size() == 0){
					errorReason.append("第"+(i+1)+"行，班级不存在<br/>");
				}else if(list.size()>1){
					errorReason.append("第"+(i+1)+"行，班级名称或简称不唯一<br/>");
				}
			}
				
			
		}
		if(errorReason.length()>0){
			map.put("type", "failed");
			map.put("msg", errorReason.toString());
		}else{
			String msg = save(sheet, wb);
			map.put("type", "success");
			map.put("msg", msg);
		}
		return map;
	}
	
	/**校验完  保存*/
	public String save(Sheet sheet,Workbook wb){
		int rows = sheet.getRows();
		int success = 0;
		Cell cell = null;
		String no = "";//学号
		String name = "";//姓名
		String sex = "";//性别
		String email = "";//邮箱
		String phone = "";//手机号
		String pphone = "";//父母手机号
		String clazz = "";//班级名称
		
		for (int i = 1; i <rows; i++) {
			cell = sheet.getCell(0, i);
			no = cell.getContents().trim();
			cell=sheet.getCell(1,i);
			name = cell.getContents().trim();
			cell= sheet.getCell(2,i);
			sex = cell.getContents().trim();
			cell = sheet.getCell(3,i);
			email = cell.getContents().trim();
			cell=sheet.getCell(4,i);
			phone = cell.getContents().trim();
			cell = sheet.getCell(5,i);
			pphone = cell.getContents().trim();
			cell = sheet.getCell(6,i);
			clazz = cell.getContents().trim();
			
			try{
			studentMapper.insertIntoMember(no, name, email, pphone, sex);
			List<Map<String, Object>> list = clazzMapper.checkExise(clazz);
			studentMapper.insertIntoStudent(no, name, pphone,Long.valueOf(list.get(0).get("id").toString()));
			}catch(Exception e){
				e.printStackTrace();
			}
			success++;
		}
		
		return "共"+(rows-1)+"行，成功导入"+success+"行";
	}
}
