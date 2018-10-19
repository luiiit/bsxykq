package edu.bsuc.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.web.servlet.ModelAndView;



import edu.bsuc.entity.Student;
import edu.bsuc.mapper.ClazzMapper;
import edu.bsuc.mapper.CommonMapper;
import edu.bsuc.mapper.StudentMapper;
import edu.bsuc.service.StudentService;
import edu.bsuc.utils.ExcelView;

@Controller("studentController")
@RequestMapping("/admin/student")
public class StudentController {

	@Autowired
	private StudentMapper studentMapper;
	@Autowired
	private ClazzMapper clazzMapper;
	@Autowired
	private CommonMapper commonMapper;

	@Resource(name="studentServiceImpl")
	private StudentService studentService;
	
	@RequestMapping(value="/list.jhtml",method=RequestMethod.GET)
	public String list(String no,String name,String phone,String major,String institute,ModelMap model,String clazz,Long clazzId){
		Map<String,String> map = new HashMap<String,String>();
		map.put("no", no);
		map.put("name", name);
		map.put("major", major);
		map.put("institute",institute);
		map.put("phone", phone);
		map.put("clazzId", clazzId == null?"":clazzId.toString());
		
		model.addAttribute("list",studentMapper.list(map));
		
		model.addAttribute("no", no);
		model.addAttribute("name", name);
		model.addAttribute("phone",phone);
		model.addAttribute("clazzId", clazzId);
		model.addAttribute("major", major);
		model.addAttribute("institute", institute);
		model.addAttribute("clazz", clazz);
		
		return "/pages/admin/baseData/student/student";
	}
	@RequestMapping(value="/student_add.jhtml",method=RequestMethod.GET)
	public String student_add(ModelMap model){
		model.addAttribute("list", clazzMapper.list(null));
		return "/pages/admin/baseData/student/student_add";
	}
	@RequestMapping(value="/student_edit.jhtml",method=RequestMethod.GET)
	public String student_edit(ModelMap model,Long id){
		model.addAttribute("list", clazzMapper.list(null));
		model.addAttribute("student", studentMapper.getOneById(id));
		return "/pages/admin/baseData/student/student_edit";
	}
	
	
	@RequestMapping(value="/select_clazz.jhtml",method=RequestMethod.GET)
	public String select_clazz(String name,String institute,ModelMap model){
		Map<String,String> map = new HashMap<String,String>();
		map.put("name", name);
		map.put("institute",institute);
		model.addAttribute("data",studentMapper.select_clazz(map));
		model.addAttribute("name", name);
		model.addAttribute("institute", institute);
		return "/pages/admin/baseData/student/select_clazz";
	}
	
	
	@RequestMapping(value="/student_save.jhtml",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> student_save(ModelMap model,String name,
			String no,String email,String phone,String sex,String pphone,
			Long clazz){
		Map<String, String> map = new HashMap<String,String>();
	List<Map<String, Object>> list = commonMapper.checkNo(no);
	if(list.size()>0){
		map.put("type", "failed");
		map.put("msg", "此学号已存在");
		return map;
	}
		studentMapper.insertIntoMember(no, name, email, phone, sex);
		studentMapper.insertIntoStudent(no, name, pphone, clazz);
	
		map.put("type", "success");
		return map;
	}
	
	
	

	@RequestMapping(value="import_from_excel.jhtml")
	public String importFromExcel(){
		return "/pages/admin/baseData/student/toImportExcel";
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
			map = studentService.importProduct(tempFile, file);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			FileUtils.deleteQuietly(tempFile);
			return map;
		}
	}
	
	@RequestMapping(value="export_to_excel",method=RequestMethod.POST)
	public ModelAndView export(String no,String name,String phone,String major,String institute,ModelMap model,String clazz,Long clazzId){
		Map<String,String> map = new HashMap<String,String>();
		map.put("no", no);
		map.put("name", name);
		map.put("major", major);
		map.put("institute",institute);
		map.put("phone", phone);
		map.put("clazzId", clazzId == null?"":clazzId.toString());
		
		List<Student> list = studentMapper.list(map);
		
		
		// 设置导出表格名
				String filename = new SimpleDateFormat("yyyy-MM-dd").format(new Date())
						+ ".xls";
				// 设置标题
				// 设置标题
				String[] header = { "姓名", "学院", "专业", "班级", "性别","手机号", "邮箱","父母手机号"};
				// 设置单元格取值
				String[] properties = { "name", "instituteName",
						"majorName", "clazzName", "sex","phone",
						"email","pphone"};
				Integer[] widths = { 5000,5000, 5000, 5000, 5000, 5000, 5000,5000,5000};

				// 附加内容
				String[] contents = new String[2];
				contents[0] = "教师信息表";
				contents[1] = "生成日期" + ": "
						+ new SimpleDateFormat("yyyy-MM-dd").format(new Date());
				// 调用ExcelView工具类生成Excel并导出
				return new ModelAndView(new ExcelView(filename, null, properties,
						header, widths, null, list, contents));
	}
}
