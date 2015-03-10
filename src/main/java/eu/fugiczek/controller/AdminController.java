package eu.fugiczek.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import eu.fugiczek.entity.BlogPost;
import eu.fugiczek.service.BlogPostService;
import eu.fugiczek.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserService userService;

	@Autowired
	private BlogPostService blogPostService;

	@RequestMapping(value = { "", "/users" })
	public String users(Model model) {
		model.addAttribute("users", userService.findAll());
		return "admin-users";
	}

	@RequestMapping("/users/remove/{id:[\\d]+}")
	public String removeUsers(@PathVariable int id) {
		userService.delete(id);
		return "redirect:/admin/users";
	}

	@RequestMapping("/blog")
	public String blog(Model model) {
		blogPageAttributes(model, 1);
		return "admin-blog";
	}

	@RequestMapping("/blog/{page:[\\d]+}")
	public String blogPage(@PathVariable int page, Model model) {
		blogPageAttributes(model, page);
		return "admin-blog";
	}

	@ModelAttribute("blogPost")
	public BlogPost constructBlogPost() {
		return new BlogPost();
	}
	
	@RequestMapping(value = "/blog/add", method = RequestMethod.POST)
	public String addBlogPost(
			@Valid @ModelAttribute("blogPost") BlogPost blogPost,
			BindingResult result, RedirectAttributes redirectAttributes, Principal principal) {		
		
		if(result.hasErrors()) {
			redirectAttributes.addFlashAttribute("success", false);
			return "redirect:/admin/blog";
		}
		
		String name = principal.getName();
		blogPostService.save(blogPost, name);
		redirectAttributes.addFlashAttribute("success", true);
		
		return "redirect:/admin/blog";
	}

	private void blogPageAttributes(Model model, int page) {
		model.addAttribute("posts", blogPostService.findAll(page - 1));
		model.addAttribute("count", blogPostService.count());
		model.addAttribute("currentPage", page);
	}
	
	@RequestMapping("/blog/remove/{id:[\\d]+}")
	public String removeBlogPost(@PathVariable int id) {
		blogPostService.delete(id);
		return "redirect:/admin/blog";
	}

}
