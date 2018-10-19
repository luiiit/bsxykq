package edu.bsuc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.bsuc.entity.Institute;
import edu.bsuc.entity.Secretary;
import edu.bsuc.mapper.InstituteMapper;

@Controller("instituteController")
@RequestMapping("/admin/institute")
public class InstituteController {

	@Autowired 
	private InstituteMapper instituteMapper;
	
	@RequestMapping(value="/list.jhtml")
	public String list(ModelMap model,String secretary,String institute){
		Map<String,String> map = new HashMap<String,String>();
		map.put("institute", institute);
		map.put("secretatry", secretary);
		model.addAttribute("secretary",secretary==null?"":secretary);
		model.addAttribute("institute",institute==null?"":institute);
		List<Map<String, Object>> list = instituteMapper.list(map);
		model.addAttribute("list",list);
		return "/pages/admin/baseData/institute/institute";
	}
	
	@RequestMapping(value="/institute_add.jhtml")
	public String institute_add(ModelMap model){
		model.addAttribute("list",instituteMapper.showSecretary());
		return "/pages/admin/baseData/institute/institute_add";
	}
	
	@RequestMapping(value="/institute_save.jhtml")
	@ResponseBody
	public Map<String,String> institute_save(Long secretary,String name){
		Map<String, String> map = new HashMap<String,String>();
		Institute ins = instituteMapper.checkExist(name);
		Institute insti = instituteMapper.checkExists(secretary);
		if(ins != null){
			map.put("type", "failed");
			map.put("msg", "此学院已存在");
		}else if(insti != null){
			map.put("type", "failed");
			map.put("msg", "此秘书已负责其他学院");
		}else{
			map.put("type", "success");
			instituteMapper.insertInstitute(secretary, name);
		}
		return map;
	}
	
	@RequestMapping(value="/institute_edit.jhtml")
	public String institute_edit(ModelMap model,Long id){
		model.addAttribute("map",instituteMapper.getInstituteById(id));
		model.addAttribute("list",instituteMapper.showSecretary());
		return "/pages/admin/baseData/institute/institute_edit";
	}
	
	@RequestMapping(value="/institute_update.jhtml")
	@ResponseBody
	public Map<String,String> institute_update(Long id,String name,Long secretary){
		Map<String,String> map = new HashMap<String,String>();
			Institute insti = instituteMapper.checkExists(secretary);
			if(insti != null){
				map.put("type", "failed");
				return map;
			}
			instituteMapper.updateInstitute(id,secretary, name);
			map.put("type", "success");
			return map;
			
	}	
	@RequestMapping(value="/getSecretaryById.jhtml")
	@ResponseBody
	public Secretary getSecretaryById(Long institute){
		Secretary secretary = instituteMapper.getSecretaryById(institute);
			return secretary;
			
	}	
	
}
