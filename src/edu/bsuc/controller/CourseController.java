package edu.bsuc.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import edu.bsuc.entity.Clazz;
import edu.bsuc.entity.Course;
import edu.bsuc.mapper.ClazzMapper;
import edu.bsuc.mapper.CourseMapper;
import edu.bsuc.mapper.StudentMapper;
import edu.bsuc.mapper.TeacherMapper;
import edu.bsuc.service.CourseService;

@Controller("courseController")
@RequestMapping("/admin/course")
public class CourseController {
	
	@Autowired
	private CourseMapper courseMapper;
	@Autowired
	private StudentMapper studentMapper;
	@Autowired
	private ClazzMapper clazzMapper;
	@Autowired
	private TeacherMapper teacherMapper;
	@Resource(name="courseServiceImpl")
	private CourseService CourseService;

	@RequestMapping(value="list",method=RequestMethod.GET)
	public String list(ModelMap model,String courseName,String teacher,String weekDay,String clazz){
		model.addAttribute("courseName",courseName);
		model.addAttribute("teacher",teacher);
		model.addAttribute("weekDay",weekDay==null?"":weekDay);
		model.addAttribute("clazz",clazz);
		
		Map<String, String> map = new HashMap<String,String>();
		map.put("courseName", courseName);
		map.put("teacher", teacher);
		map.put("weekDay", weekDay);
		map.put("clazz",clazz);
		
		model.addAttribute("list",courseMapper.list(map));
		return "/pages/admin/course/course";
	}
	
	@RequestMapping(value="course_add",method=RequestMethod.GET)
	public String course_add(ModelMap model,String courseName,String teacher,String weekDay,String clazz){

		return "/pages/admin/course/course_add";
	}
	
	@RequestMapping(value="/select_clazz.jhtml",method=RequestMethod.GET)
	public String select_clazz(String name,String institute,ModelMap model){
		Map<String,String> map = new HashMap<String,String>();
		map.put("name", name);
		map.put("institute",institute);
		model.addAttribute("data",studentMapper.select_clazz(map));
		model.addAttribute("name", name);
		model.addAttribute("institute", institute);
		return "/pages/admin/course/select_clazz";
	}
	
	@RequestMapping(value="/select_teacher.jhtml",method=RequestMethod.GET)
	public String select_teacher(String name,Long cid,ModelMap model){
		Map<String,String> map = new HashMap<String,String>();
		map.put("name", name);
		model.addAttribute("data",teacherMapper.list(map));
		model.addAttribute("name", name);
		model.addAttribute("cid", cid);
		return "/pages/admin/course/select_teacher";
	}
	
	@RequestMapping(value="/course_save.jhtml",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> course_save(String name,Long clazzId,Long teacherId,String weekDay,String beginDate,String endDate,
			String beginTime,String endTime,String room){
		Map<String,String> map = new HashMap<String,String>();
		List<Course> list1 = courseMapper.checkExistRoom(weekDay,beginDate,endDate,beginTime,endTime,room,null);
		if(list1.size() != 0){
			map.put("type", "failed");
			map.put("msg","此教室在此时间范围内已有班级上课");
			return map;
		}
		List<Course> list2 = courseMapper.checkExistTeacher(weekDay,beginDate,endDate,beginTime,endTime,teacherId,null);
		if(list2.size() != 0){
			map.put("type", "failed");
			map.put("msg","此教师在此时间范围内已有班级上课");
			return map;
		}
		List<Course> list3 = courseMapper.checkExistClazz(weekDay,beginDate,endDate,beginTime,endTime,clazzId,null);
		if(list3.size() != 0){
			map.put("type", "failed");
			map.put("msg","此班级在此时间范围内已有课课程");
			return map;
		}
		int i = courseMapper.insert(name,clazzId,teacherId,weekDay,beginDate,endDate,beginTime,endTime,room);
		
		if(i == 1){map.put("type", "success");}
		else{
			map.put("type", "failed");
			map.put("msg", "操作失败");
		}
		return map;
	}
	
	@RequestMapping(value="/toImportExcel.jhtml",method=RequestMethod.GET)
	public String toImportExcel(ModelMap model){
		return "/pages/admin/course/toImportExcel";
	}
	
	@RequestMapping(value="/course_edit.jhtml",method=RequestMethod.GET)
	public String course_edit(ModelMap model,Long id){
		
		model.addAttribute("course",courseMapper.getById(id));
		return "/pages/admin/course/course_edit";
	}
	
	//excel导入教师数据
		@SuppressWarnings("finally")
		@RequestMapping(value="importExcel.jhtml")
		@ResponseBody
		public Map<String,Object> importFromExcel( MultipartFile file,
			HttpServletResponse response, ModelMap model) {
			Map<String, Object> map =null;
			File tempFile = null;
			try { 
				tempFile = new File(System.getProperty("java.io.tmpdir")
						+ "/upload_"
						+ UUID.randomUUID()
						+ ".tmp");
				map = CourseService.importProduct(tempFile, file);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				FileUtils.deleteQuietly(tempFile);
				return map;
			}
		}
		
		
		@RequestMapping(value="/course_update.jhtml",method=RequestMethod.POST)
		@ResponseBody
		public Map<String,String> course_update(Long id,String name,Long clazzId,Long teacherId,String weekDay,String beginDate,String endDate,
				String beginTime,String endTime,String room){
			Map<String,String> map = new HashMap<String,String>();
			List<Course> list1 = courseMapper.checkExistRoom(weekDay,beginDate,endDate,beginTime,endTime,room,id);
			if(list1.size() != 0){
				map.put("type", "failed");
				map.put("msg","此教室在此时间范围内已有班级上课");
				return map;
			}
			List<Course> list2 = courseMapper.checkExistTeacher(weekDay,beginDate,endDate,beginTime,endTime,teacherId,id);
			if(list2.size() != 0){
				map.put("type", "failed");
				map.put("msg","此教师在此时间范围内已有班级上课");
				return map;
			}
			List<Course> list3 = courseMapper.checkExistClazz(weekDay,beginDate,endDate,beginTime,endTime,clazzId,id);
			if(list3.size() != 0){
				map.put("type", "failed");
				map.put("msg","此班级在此时间范围内已有课课程");
				return map;
			}
			int i = courseMapper.update(name,clazzId,teacherId,weekDay,beginDate,endDate,beginTime,endTime,room,id);
			
			if(i == 1){map.put("type", "success");}
			else{
				map.put("type", "failed");
				map.put("msg", "操作失败");
			}
			return map;
		}
}
