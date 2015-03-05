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
	
	@RequestMapping(value={"", "/users"})
	public String users(Model model) {
		model.addAttribute("users", userService.findAll());
		return "admin-users";
	}
	
	@RequestMapping("/users/remove/{id:[\\d]+}")
	public String removeUsers(@PathVariable int id) {
		userService.remove(id);
		return "redirect:/admin/users";
	}
	
	@RequestMapping("/blog")
	public String blog(Model model) {
		model.addAttribute("posts", blogPostService.findAll(0));
		model.addAttribute("count", blogPostService.count());
		model.addAttribute("currentPage", 1);
		return "admin-blog";
	}
	
	@RequestMapping("/blog/{page:[\\d]+}")
	public String blogPage(@PathVariable int page, Model model) {
		model.addAttribute("posts", blogPostService.findAll(page - 1));
		model.addAttribute("count", blogPostService.count());
		model.addAttribute("currentPage", page);
		return "admin-blog";
	}
	
	@RequestMapping("/blog/remove/{id:[\\d]+}")
	public String removeBlogPost(@PathVariable int id) {
		blogPostService.delete(id);
		return "redirect:/admin/blog";
	}
	
}
