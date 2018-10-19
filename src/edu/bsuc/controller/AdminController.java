package edu.bsuc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("adminController")
@RequestMapping("/admin")
public class AdminController {

	@RequestMapping(value="/index.jhtml")
	public String index(String username,ModelMap model){
		model.addAttribute("userame","sa");
		return "/pages/admin/index";
	}
}
