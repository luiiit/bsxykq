package edu.bsuc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.bsuc.entity.Secretary;
import edu.bsuc.mapper.CommonMapper;
import edu.bsuc.mapper.InstituteMapper;
import edu.bsuc.mapper.SecretaryMapper;

@Controller("secretaryController")
@RequestMapping("/admin/secretary")
public class SecretaryController {

	@Autowired
	private SecretaryMapper secretatryMapper;
	@Autowired
	private InstituteMapper instituteMapper;
	@Autowired
	private CommonMapper commonMapper;
	
	@RequestMapping(value="list.jhtml")
	public String list(ModelMap model,String secretary,String institute,String phone){
		model.addAttribute("secretary",secretary==null?"":secretary);
		model.addAttribute("institute", institute==null?"":institute);
		model.addAttribute("phone", phone==null?"":phone);
		Map<String,String> map = new HashMap<String,String>();
		map.put("secretary", secretary);
		map.put("institute", institute);
		map.put("phone", phone);
		List<Secretary> list = secretatryMapper.list(map);
		model.addAttribute("list", list);
		return "/pages/admin/baseData/secretary/secretary";
	}
	
	@RequestMapping(value="secretary_edit.jhtml")
	public String secretary_edit(ModelMap model,Long id){
		model.addAttribute("list",instituteMapper.list(null));
		Secretary secretary = secretatryMapper.getById(id);

		model.addAttribute("secretary", secretary);
		return "/pages/admin/baseData/secretary/secretary_edit";
	}
	
	
	@RequestMapping(value="secretary_add.jhtml")
	public String secretary_add(ModelMap model,Long id){
		model.addAttribute("list",instituteMapper.list(null));
		return "/pages/admin/baseData/secretary/secretary_add";
	}
	
	@RequestMapping(value="secretary_save.jhtml")
	@ResponseBody
	public Map<String,Object> secretary_save(String name,String sex,String phone,String email,String no){
		Map<String,Object> map = new HashMap<String,Object>();
	List<Map<String, Object>> list = commonMapper.checkNo(no);
	if(list.size()>0){
		map.put("type", "failed");
		map.put("msg", "教职工编号已存在");
		return map;
	}
		secretatryMapper.insertToMember(name, sex, phone, email, no);
		secretatryMapper.insertToSecretary(no, name);
	
		map.put("type", "success");
		map.put("msg", "添加成功");
		return map;
	}
	
	@RequestMapping(value="secretary_update.jhtml")
	@ResponseBody
	public Map<String,Object> secretary_update(String no,String phone,String email){
		secretatryMapper.update(no, phone, email);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("type", "success");
		map.put("msg", "操作成功");
		return map;
	}
}
