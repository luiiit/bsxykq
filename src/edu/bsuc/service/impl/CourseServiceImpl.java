package edu.bsuc.service.impl;


import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mysql.jdbc.StringUtils;

import edu.bsuc.entity.Course;
import edu.bsuc.entity.Teacher;
import edu.bsuc.mapper.ClazzMapper;
import edu.bsuc.mapper.CourseMapper;
import edu.bsuc.mapper.TeacherMapper;
import edu.bsuc.service.CourseService;
import edu.bsuc.utils.DateUtil;
import edu.bsuc.utils.RegCheck;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

@Service("courseServiceImpl")
public class CourseServiceImpl implements CourseService{
	
	
	
	@Autowired
	private CourseMapper courseMapper;
	@Autowired
	private ClazzMapper clazzMapper;
	@Autowired
	private TeacherMapper teacherMapper;
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
		String courseName = "";//课程名称
		String weekDay = "";//周几上课
		String clazzName = "";//性别
		String teacherName = "";//教师姓名
		String beginDate = "";//课程开始日期
		String endDate = "";//课程结束日期
		String beginTime = "";//课程开始时间
		String endTime = "";//课程结束时间
		String room = "";//上课教室
		
		
		String tempWeek = "";//临时周几
		String tempTeacher ="";//临时教师
		String tempClazz = "";//临时班级
		String tempRoom = "";//临时教室
		String tempBd = "";//临时开始日期
		String tempEd ="";//临时结束日期
		String tempBt ="";//临时开始时间
		String tempEt ="";//临时结束时间
		
		StringBuilder errorReason = new StringBuilder();
		for (int i = 1; i < rows; i++) {
			cell = sheet.getCell(0, i);
			courseName = cell.getContents().trim();
			if(StringUtils.isNullOrEmpty(courseName)){
				errorReason.append("第"+(i+1)+"行，课程名称为空<br />");
			}
			
		
			
			cell=sheet.getCell(1,i);
			weekDay = cell.getContents().trim();
			if(StringUtils.isNullOrEmpty(weekDay)){
				errorReason.append("第"+(i+1)+"行，请填写周几上课<br />");
			}
			int week = 0;
			switch (weekDay) {
			case "周一":
				week = 1;
				break;
			case "周二":
				week = 2;
				break;
			case "周三":
				week = 3;
				break;
			case "周四":
				week = 4;
				break;
			case "周五":
				week = 5;
				break;
			default:
				break;
			}
			if(week == 0){
				errorReason.append("第"+(i+1)+"行，周几上课请按规定格式填写<br/>");

			}
			cell= sheet.getCell(2,i);
			clazzName = cell.getContents().trim();
			if(StringUtils.isNullOrEmpty(clazzName)){
				errorReason.append("第"+(i+1)+"行，上课班级不能为空<br />");
			}
			cell = sheet.getCell(3,i);
			teacherName = cell.getContents().trim();
			if(StringUtils.isNullOrEmpty(teacherName)){
				errorReason.append("第"+(i+1)+"行，任课教师不能为空<br />");
			}
			
			cell=sheet.getCell(4,i);
			beginDate = cell.getContents().trim();
			if(StringUtils.isNullOrEmpty(beginDate)){
				if(!RegCheck.isChinaPhoneLegal(beginDate)){
					errorReason.append("第"+(i+1)+"行，上课开始日期不能为空<br/>");
				}
			}
			
			
			
			cell=sheet.getCell(5,i);
			endDate = cell.getContents().trim();
			if(StringUtils.isNullOrEmpty(endDate)){
				errorReason.append("第"+(i+1)+"行，上课结束日期不能为空<br/>");
			}
			
			cell=sheet.getCell(6,i);
			beginTime = cell.getContents().trim();
			
			if(StringUtils.isNullOrEmpty(beginTime)){
				errorReason.append("第"+(i+1)+"行，上课开始时间不能为空<br/>");
			}
			cell=sheet.getCell(7,i);
			endTime = cell.getContents().trim();
			
			if(StringUtils.isNullOrEmpty(endTime)){
				errorReason.append("第"+(i+1)+"行，上课结束时间不能为空<br/>");
			}
			cell=sheet.getCell(8,i);
			room = cell.getContents().trim();
			
			if(StringUtils.isNullOrEmpty(beginTime)){
				errorReason.append("第"+(i+1)+"行，上课教室不能为空<br/>");
			}
			//基础校验完毕   下面重复校验
			//班级
			List<Map<String, Object>> clist = clazzMapper.checkExise(clazzName);
			if(clist.size() == 0){
				errorReason.append("第"+(i+1)+"行，此班级不存在<br/>");
			}else if(clist.size() > 1 ){
				errorReason.append("第"+(i+1)+"行，此班级名称有冲突，请到页面手动添加<br/>");
			}else{
			List<Course> list1 = courseMapper.checkExistClazz(week+"", beginDate, endDate, beginTime, endTime,Long.valueOf(clist.get(0).get("id").toString()),null );
			if(list1.size()!=0){
					errorReason.append("第"+(i+1)+"行，此班级在此时间段已有课程<br/>");
				}
			}
			//教师
			 List<Teacher> tlist = teacherMapper.getByName(teacherName);
			if(tlist.size() == 0){
				errorReason.append("第"+(i+1)+"行，此教师不存在<br/>");
			}else if(tlist.size() > 1 ){
				errorReason.append("第"+(i+1)+"行，此教师姓名有冲突，请到页面手动添加<br/>");
			}else{
			List<Course> list2 = courseMapper.checkExistTeacher(week+"", beginDate, endDate, beginTime, endTime,Long.valueOf(tlist.get(0).getId()),null);
				if(list2.size()!=0){
					errorReason.append("第"+(i+1)+"行，此教师在此时间段已有课程<br/>");
				}
			}
			//教室
			List<Course> list3 = courseMapper.checkExistRoom(week+"", beginDate, endDate, beginTime, endTime, room,null);
			if(list3.size()>0){
				errorReason.append("第"+(i+1)+"行，此教室在此时间段已有班级上课<br/>");
			}
		
			//判断是否存在于本表的其他行
			for(int j = 1;j<i;j++){
				cell = sheet.getCell(1,j);
				 tempWeek = cell.getContents().trim();
				 cell = sheet.getCell(2,j);
				 tempClazz = cell.getContents().trim();
				 cell = sheet.getCell(3,j);
				 tempTeacher = cell.getContents().trim();
				cell = sheet.getCell(4,j);
				 tempBd = cell.getContents().trim();
				 cell = sheet.getCell(5,j);
				 tempEd = cell.getContents().trim();
				 cell = sheet.getCell(6,j);
				 tempBt = cell.getContents().trim();
				 cell = sheet.getCell(7,j);
				 tempEt = cell.getContents().trim();
				 cell = sheet.getCell(8,j);
				 tempRoom = cell.getContents().trim();
				 
				 if(weekDay.equals(tempWeek)){
					 if((DateUtil.compareTime(tempBd, beginDate,"yyyy-MM-dd")
							&& DateUtil.compareTime(endDate, tempEd, "yyyy-MM-dd"))
					|| DateUtil.compareTime(tempBd, beginDate,"yyyy-MM-dd")
							&& DateUtil.compareTime(beginDate, tempEd, "yyyy-MM-dd")
					|| DateUtil.compareTime(beginDate, tempBd,"yyyy-MM-dd")
							&& DateUtil.compareTime(endDate, tempEd, "yyyy-MM-dd")
					|| DateUtil.compareTime(beginDate, tempBd,"yyyy-MM-dd")
					&& DateUtil.compareTime(tempBd, endDate, "yyyy-MM-dd")
				 || beginDate.equals(tempBd) && endDate.equals(tempEd)){
						 tempBt = "2018-01-01 "+tempBt;
						 tempEt = "2018-01-01 "+tempEt;
						 beginTime = "2018-01-01 "+beginTime;
						 endTime = "2018-01-01 "+endTime;
						 if((DateUtil.compareTime(tempBt, beginTime,"yyyy-MM-dd HH:mm:ss")
									&& DateUtil.compareTime(endTime, tempEt, "yyyy-MM-dd HH:mm:ss"))
							|| DateUtil.compareTime(tempBt, beginTime,"yyyy-MM-dd HH:mm:ss")
									&& DateUtil.compareTime(beginTime, tempEt, "yyyy-MM-dd HH:mm:ss")
							|| DateUtil.compareTime(beginTime, tempBt,"yyyy-MM-dd HH:mm:ss")
									&& DateUtil.compareTime(endTime, tempEt, "yyyy-MM-dd HH:mm:ss")
							|| DateUtil.compareTime(beginTime, tempBt,"yyyy-MM-dd HH:mm:ss")
							&& DateUtil.compareTime(tempBt, endTime, "yyyy-MM-dd HH:mm:ss")
							 || beginTime.equals(tempBt) && endTime.equals(tempEt)){
							 if(room.equals(tempRoom)){
									errorReason.append("第"+(i+1)+"行，此教室在此时间段在第"+(j+1)+"行已有上课数据<br/>");
							 }
							 
							 if (clazzName.equals(tempClazz)) {
									errorReason.append("第"+(i+1)+"行，此班级在此时间段在第"+(j+1)+"行已有上课数据<br/>");
							}
							 
							 if(teacherName.equals(tempTeacher)){
									errorReason.append("第"+(i+1)+"行，此教室在此时间段在第"+(j+1)+"行已有上课数据<br/>");

							 }
						 
						 } 
					 }	 
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
		String courseName = "";//课程名称
		String weekDay = "";//周几上课
		String clazzName = "";//班级
		String teacherName = "";//教师姓名
		String beginDate = "";//课程开始日期
		String endDate = "";//课程结束日期
		String beginTime = "";//课程开始时间
		String endTime = "";//课程结束时间
		String room = "";//上课教室
		
		for (int i = 1; i <rows; i++) {
			cell = sheet.getCell(0, i);
			courseName = cell.getContents().trim();
			cell=sheet.getCell(1,i);
			weekDay = cell.getContents().trim();
			cell= sheet.getCell(2,i);
			 clazzName = cell.getContents().trim();
			cell = sheet.getCell(3,i);
			teacherName = cell.getContents().trim();
			cell=sheet.getCell(4,i);
			beginDate = cell.getContents().trim();
			cell = sheet.getCell(5,i);
			endDate = cell.getContents().trim();
			cell = sheet.getCell(6,i);
			beginTime = cell.getContents().trim();
			cell = sheet.getCell(7,i);
			endTime = cell.getContents().trim();
			cell = sheet.getCell(8,i);
			room = cell.getContents().trim();
			
			List<Map<String, Object>> clazz = clazzMapper.checkExise(clazzName);
			List<Teacher> teacher = teacherMapper.getByName(teacherName);
			int week = 0;
			switch (weekDay) {
			case "周一":
				week = 1;
				break;
			case "周二":
				week = 2;
				break;
			case "周三":
				week = 3;
				break;
			case "周四":
				week = 4;
				break;
			case "周五":
				week = 5;
				break;
			default:
				break;
			}
			courseMapper.insert(courseName, Long.valueOf(clazz.get(0).get("id").toString()),Long.valueOf(teacher.get(0).getId()), week+"", beginDate, endDate, beginTime, endTime, room);
			success++;
		}
		
		return "共"+(rows-1)+"行，成功导入"+success+"行";
	}
	

}
