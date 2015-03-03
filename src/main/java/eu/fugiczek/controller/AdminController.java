package eu.fugiczek.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import eu.fugiczek.service.BlogPostService;
import eu.fugiczek.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private BlogPostService blogPostService;
	
	@RequestMapping
	public String users(Model model) {
		model.addAttribute("users", userService.findAll());
		return "admin-users";
	}
	
	@RequestMapping("/blog")
	public String blog(Model model) {
		model.addAttribute("posts", blogPostService.findAll(0));
		model.addAttribute("count", blogPostService.count());
		return "admin-blog";
	}
	
	@RequestMapping("/blog/{page:[\\d]+}")
	public String blogPage(@PathVariable int page, Model model) {
		model.addAttribute("posts", blogPostService.findAll(page - 1));
		model.addAttribute("count", blogPostService.count());
		return "admin-blog";
	}
	
}
