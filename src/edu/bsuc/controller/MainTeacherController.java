package edu.bsuc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.bsuc.mapper.CommonMapper;
import edu.bsuc.mapper.InstituteMapper;
import edu.bsuc.mapper.MainTeacherMapper;

@Controller("mainTeacherController")
@RequestMapping("/admin/mainTeacher")
public class MainTeacherController {

	@Autowired
	private MainTeacherMapper mainTeacherMapper;
	@Autowired
	private InstituteMapper instituteMapper;
	@Autowired
	private CommonMapper commonMapper;
	
	@RequestMapping(value="/list.jhtml")
	public String list(ModelMap model,String name,String phone){
		Map<String,String> map = new HashMap<String,String>();
		map.put("name", name);
		map.put("phone", phone);
		model.addAttribute("name",name);
		model.addAttribute("phone",phone);
		model.addAttribute("list", mainTeacherMapper.list(map));
		return "/pages/admin/baseData/mainTeacher/mainTeacher";
	}
	@RequestMapping(value="/mainTeacher_add.jhtml")
	public String mainTeacher_add(ModelMap model){
		model.addAttribute("data", instituteMapper.list(null));
		return "/pages/admin/baseData/mainTeacher/mainTeacher_add";
	}
	
	
	
	@RequestMapping(value="/mainTeacher_save.jhtml")
	@ResponseBody
	public Map<String,String> mainTeacher_save(String name,String no,String sex,
			String phone,String email,Long institute){
		Map<String, String> map = new HashMap<String,String>();
		List<Map<String, Object>> data = commonMapper.checkNo(no);
		if(data.size()>0){
			map.put("type", "failed");
			map.put("msg", "教职工编号已存在");
			return map;
		}
		mainTeacherMapper.insertIntoMember(no,name,sex,phone,email);
		mainTeacherMapper.insertIntoMainTeacher(no,name,institute);
		map.put("type", "success");
		return map;
	}
	
	
	@RequestMapping(value="/mainTeacher_edit.jhtml")
	public String mainTeacher_edit(ModelMap model,Long id){
		model.addAttribute("mainTeacher", mainTeacherMapper.getOneById(id));
		model.addAttribute("data", instituteMapper.list(null));
		return "/pages/admin/baseData/mainTeacher/mainTeacher_edit";
	}
	
	
	@RequestMapping(value="/mainTeacher_update.jhtml")
	@ResponseBody
	public Map<String,String> mainTeacher_update(String name,String no,String sex,
			String phone,String email,Long institute){
		Map<String, String> map = new HashMap<String,String>();
		mainTeacherMapper.updateMember(no, email, phone);
		System.out.println("---------------------");
		map.put("type", "success");
		return map;
	}
}
