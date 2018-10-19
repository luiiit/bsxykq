package edu.bsuc.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.cj.util.StringUtils;

import edu.bsuc.entity.Teacher;
import edu.bsuc.mapper.CommonMapper;
import edu.bsuc.mapper.InstituteMapper;
import edu.bsuc.mapper.TeacherMapper;
import edu.bsuc.service.impl.TeacherServiceImpl;
import edu.bsuc.utils.ExcelView;

@Controller("teacherController")
@RequestMapping("/admin/teacher")
public class TeacherController {
	
	@Autowired
	private TeacherMapper teacherMapper;
	@Autowired
	private InstituteMapper instituteMapper;
	@Autowired
	private CommonMapper commonMapper;
	@Autowired
	private TeacherServiceImpl teacherServiceImpl;
	
	@RequestMapping(value="list.jhtml")
	public String list(String institute,String name,String phone,ModelMap model) {
		model.addAttribute("institute",StringUtils.isNullOrEmpty(institute)?0:Integer.parseInt(institute));
		model.addAttribute("name",name);
		model.addAttribute("phone",phone);
		Map<String,String> map = new HashMap<String,String>();	
		map.put("institute", institute);
		map.put("phone", phone);
		map.put("name", name);
		
		List<Teacher> list = teacherMapper.list(map);
		model.addAttribute("list",list);
		List<Map<String, Object>> data = instituteMapper.list(null);
		model.addAttribute("data", data);
		
		
		return "/pages/admin/baseData/teacher/teacher";
	}
	
	
	@RequestMapping(value="teacher_add.jhtml")
	public String teacher_add(ModelMap model) {
		List<Map<String, Object>> data = instituteMapper.list(null);
		model.addAttribute("data", data);
		return "/pages/admin/baseData/teacher/teacher_add";
	}
	
	@RequestMapping(value="teacher_save.jhtml")
	@ResponseBody
	public Map<String,String> teacher_save(String name,String phone,
			String email,String sex,String professional,
			String no,String institute) {
		Map<String,String> map = new HashMap<String,String>();
	List<Map<String, Object>> list = commonMapper.checkNo(no);
	if(list.size()>0){
		map.put("type", "failed");
		map.put("msg", "教职工编号已存在");
		return  map;
	}
		//插入到教师表
		teacherMapper.insertIntoTeacher(name, no,professional,institute);
		//插入到会员表
		teacherMapper.insertIntoMember(no,name,sex,phone,email);
		map.put("type", "success");
		map.put("msg", "操作成功");
		return  map;
	}
	
	
	@RequestMapping(value="import_from_excel.jhtml")
	public String importFromExcel(){
		return "/pages/admin/baseData/teacher/toImportExcel";
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
			map = teacherServiceImpl.importProduct(tempFile, file);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			FileUtils.deleteQuietly(tempFile);
			return map;
		}
	}
	
	@RequestMapping(value="teacher_edit.jhtml")
	public String teacher_edit(ModelMap model,Long id) {
		Teacher teacher = teacherMapper.getOneById(id);
		model.addAttribute("teacher", teacher);
		return "/pages/admin/baseData/teacher/teacher_edit";
	}
	
	

	@RequestMapping(value="teacher_update.jhtml")
	@ResponseBody
	public Map<String,String> teacher_update(String phone,
			String email,String no,String professional) {
		Map<String,String> map = new HashMap<String,String>();
		teacherMapper.updateProfessional(professional, no);
		int i = teacherMapper.updateByNo(phone, email, no);
		if(i == 1){
			map.put("type", "success");
			map.put("msg", "操作成功");
		}else{
			map.put("type", "failed");
			map.put("msg", "操作失败");
		}
		return map;
	}
	
	//导出教师信息
	@RequestMapping(value = "/export_to_excel", method = RequestMethod.GET)
	public ModelAndView download_bypage(String institute,String name,String phone,ModelMap model) {
		
		Map<String,String> map = new HashMap<String,String>();	
		map.put("institute", institute);
		map.put("phone", phone);
		map.put("name", name);
		
		List<Teacher> maps = teacherMapper.list(map);
		
		// 设置导出表格名
		String filename = new SimpleDateFormat("yyyy-MM-dd").format(new Date())
				+ ".xls";
		// 设置标题
		// 设置标题
		String[] header = { "教职工编号", "教师姓名", "性别", "邮箱", "手机号", "职称","学院"};
		// 设置单元格取值
		String[] properties = { "no", "name",
				"sex", "email", "phone",
				"professional","instituteName"};
		Integer[] widths = { 5000, 5000, 5000, 5000, 5000, 5000,5000,5000};

		// 附加内容
		String[] contents = new String[3];
		contents[0] = "教师信息表";
		contents[2] = "生成日期" + ": "
				+ new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		// 调用ExcelView工具类生成Excel并导出
		return new ModelAndView(new ExcelView(filename, null, properties,
				header, widths, null, maps, contents));

	}
	
	
}
