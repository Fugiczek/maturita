package eu.fugiczek.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import eu.fugiczek.service.BlogPostService;

@Controller
@RequestMapping("/blog")
public class BlogController {

	@Autowired
	private BlogPostService blogPostService;
	
	@RequestMapping
	public String show(Model model) {
		model.addAttribute("posts", blogPostService.findAll(0));
		model.addAttribute("count", blogPostService.count());
		model.addAttribute("currentPage", 1);
		return "blog";
	}
	
	@RequestMapping("/{page:[\\d]+}")
	public String showPage(@PathVariable Integer page, Model model) {
		model.addAttribute("posts", blogPostService.findAll(page - 1));
		model.addAttribute("count", blogPostService.count());
		model.addAttribute("currentPage", page);
		return "blog";
	}
	
	@RequestMapping("/show/{id:[\\d]+}")
	public String show(@PathVariable int id, Model model) {
		model.addAttribute("post", blogPostService.findOne(id));
		return "blog-post";
	}
	
}
