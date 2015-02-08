package eu.fugiczek.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import eu.fugiczek.service.UserService;

@Controller
@RequestMapping("/administration")
public class AdministrationController {

	@Autowired
	private UserService userService;
	
	@RequestMapping
	public String show(Model model) {
		model.addAttribute("users", userService.findAll());
		return "administration";
	}
	
}
