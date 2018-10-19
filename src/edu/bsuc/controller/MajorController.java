package edu.bsuc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.cj.util.StringUtils;

import edu.bsuc.entity.Major;
import edu.bsuc.mapper.InstituteMapper;
import edu.bsuc.mapper.MajorMapper;

@Controller("majorController")
@RequestMapping("/admin/major")
public class MajorController {

	@Autowired
	private InstituteMapper instituteMapper;
	@Autowired 
	private MajorMapper majorMapper;
	
	@RequestMapping(value="/list.jhtml")
	public String list(ModelMap model,String major,String institute){
		Map<String,String> map = new HashMap<String,String>();
		map.put("institute", institute);
		map.put("major", major);
		model.addAttribute("major",major);
		model.addAttribute("institute",StringUtils.isNullOrEmpty(institute)?0:Integer.parseInt(institute));
		model.addAttribute("data", instituteMapper.list(null));
		List<Major> list = majorMapper.list(map);
		model.addAttribute("list",list);
		return "/pages/admin/baseData/major/major";
	}
	
	@RequestMapping(value="/major_add.jhtml")
	public String major_add(ModelMap model){
		model.addAttribute("list",instituteMapper.list(null));
		return "/pages/admin/baseData/major/major_add";
	}
	
	@RequestMapping(value="/major_edit.jhtml")
	public String major_edit(ModelMap model,Long id){
		model.addAttribute("map",majorMapper.getMajorById(id));
		model.addAttribute("list",instituteMapper.list(null));
		return "/pages/admin/baseData/major/major_edit";
	}
	
	
	@RequestMapping(value="/major_update.jhtml")
	@ResponseBody
	public Map<String,String> major_update(Long id,String name,Long institute){
		Map<String,String> map = new HashMap<String,String>();
			Major major = majorMapper.checkExists(name);
			if(major != null){
				map.put("type", "failed");
				return map;
			}
			majorMapper.update(id,institute, name);
			map.put("type", "success");
			return map;
			
	}	
	
	
	@RequestMapping(value="/major_save.jhtml")
	@ResponseBody
	public Map<String,String> major_save(String name,Long institute){
		Map<String,String> map = new HashMap<String,String>();
			Major major = majorMapper.checkExists(name);
			if(major != null){
				map.put("type", "failed");
				return map;
			}
			majorMapper.save(institute, name);
			map.put("type", "success");
			return map;
			
	}	
}
