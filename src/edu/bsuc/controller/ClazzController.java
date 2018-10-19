package edu.bsuc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.bsuc.entity.Clazz;
import edu.bsuc.entity.MainTeacher;
import edu.bsuc.entity.Major;
import edu.bsuc.mapper.ClazzMapper;
import edu.bsuc.mapper.InstituteMapper;
import edu.bsuc.mapper.MainTeacherMapper;
import edu.bsuc.mapper.MajorMapper;
import edu.bsuc.mapper.SecretaryMapper;

@Controller("clazzController")
@RequestMapping("/admin/clazz")
public class ClazzController {

	@Autowired
	private ClazzMapper clazzMapper;
	@Autowired
	private SecretaryMapper secretaryMapper;
	@Autowired
	private InstituteMapper instituteMapper;
	@Autowired
	private MainTeacherMapper mainTeacherMapper;
	@Autowired
	private MajorMapper majorMapper;
	
	
	@RequestMapping(value="/list.jhtml")
	public String list(String institute,String name,ModelMap model) {
		model.addAttribute("institute",institute);
		model.addAttribute("name",name);
		Map<String,String> map = new HashMap<String,String>();	
		map.put("institute", institute);
		map.put("name", name);
		
		List<Clazz> list = clazzMapper.list(map);
		model.addAttribute("list",list);
		
		return "/pages/admin/baseData/clazz/clazz";
	}
	
	@RequestMapping(value="/clazz_add.jhtml")
	public String clazz_add(ModelMap model) {
		Map<String,String> map = new HashMap<String,String>();
		model.addAttribute("lists", secretaryMapper.list(map));
		model.addAttribute("listm", mainTeacherMapper.list(map));
		model.addAttribute("listi", instituteMapper.list(map));
		model.addAttribute("listma", majorMapper.list(map));
		return "/pages/admin/baseData/clazz/clazz_add";
	}
	

	@RequestMapping(value="/clazz_edit.jhtml")
	public String clazz_edit(ModelMap model,Long id) {
		Map<String,String> map = new HashMap<String,String>();
		Clazz clazz  =  clazzMapper.getById(id);
		model.addAttribute("clazz", clazz);
		model.addAttribute("data",clazzMapper.getMainTeacherByInstitute(clazz.getId().longValue()));
		return "/pages/admin/baseData/clazz/clazz_edit";
	}
	
	@RequestMapping(value="/clazz_save.jhtml")
	@ResponseBody
	public Map<String,String> save(String name,String simpleName,
			Long institute,Long secretary,Long major,Long mainTeacher){
		Map<String,String> map = new HashMap<String,String>();
		 clazzMapper.save(name,simpleName,institute,secretary,major,mainTeacher);
		 map.put("type", "success");
			return map;
	}
	
	@RequestMapping(value="/clazz_update.jhtml")
	@ResponseBody
	public Map<String,String> update(Long id,Long mainTeacher){
		Map<String,String> map = new HashMap<String,String>();
		clazzMapper.updateClazz(id,mainTeacher);
		 map.put("type", "success");
		return map;
	}
	@RequestMapping(value="/getMajor.jhtml")
	@ResponseBody
	public List<Major> getMajorByInstitute(Long institute){
		List<Major> list = clazzMapper.getMajorByInstitute(institute);
			return list;
	}	
	
	@RequestMapping(value="/getMainTeacher.jhtml")
	@ResponseBody
	public List<MainTeacher> getMainTeacher(Long institute){
		List<MainTeacher> list = clazzMapper.getMainTeacherByInstitute(institute);
			return list;
	}
	
	
}
