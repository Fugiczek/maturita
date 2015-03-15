package eu.fugiczek.maturita.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import eu.fugiczek.maturita.domain.BlogPost;
import eu.fugiczek.maturita.model.service.BlogPostService;
import eu.fugiczek.maturita.web.controller.util.PageWrapper;

@Controller
@RequestMapping("/blog")
public class BlogController {

	@Autowired
	private BlogPostService blogPostService;
	
	@RequestMapping
	public String show(Model model) {
		PageWrapper<BlogPost> page = new PageWrapper<>(blogPostService.findAll(0), "/blog");
		model.addAttribute("page", page);
		return "blog/index";
	}
	
	@RequestMapping("/{currentPage:[\\d]+}")
	public String showPage(@PathVariable Integer currentPage, Model model) {
		PageWrapper<BlogPost> page = new PageWrapper<>(blogPostService.findAll(currentPage - 1), "/blog");
		model.addAttribute("page", page);
		return "blog/index";
	}
	
	@RequestMapping("/show/{id:[\\d]+}")
	public String show(@PathVariable int id, Model model) {
		model.addAttribute("post", blogPostService.findOne(id));
		return "blog/post";
	}
	
}
