package eu.fugiczek.maturita.web.controller;

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

import eu.fugiczek.maturita.domain.BlogPost;
import eu.fugiczek.maturita.domain.exception.BlogPostNotFoundException;
import eu.fugiczek.maturita.model.service.BlogPostService;
import eu.fugiczek.maturita.web.controller.util.PageWrapper;

@Controller
@RequestMapping("/admin/blog")
public class AdminBlogController {
	
	@Autowired
	private BlogPostService blogPostService;

	@RequestMapping
	public String blog(Model model) {
		PageWrapper<BlogPost> page = new PageWrapper<>(
				blogPostService.findAll(0), "/admin/blog");
		model.addAttribute("page", page);
		return "admin/blog";
	}

	@RequestMapping("/{currentPage:[\\d]+}")
	public String blogPage(@PathVariable int currentPage, Model model) {
		PageWrapper<BlogPost> page = new PageWrapper<>(
				blogPostService.findAll(currentPage - 1), "/admin/blog");
		model.addAttribute("page", page);
		return "admin/blog";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addBlogPost(Model model) {
		model.addAttribute("edit", false);
		return "admin/blogform";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addBlogPost(Model model,
			@Valid @ModelAttribute("blogPost") BlogPost blogPost,
			BindingResult result, RedirectAttributes redirectAttributes,
			Principal principal) {
		if (result.hasErrors()) {
			model.addAttribute("edit", false);
			return "admin/blogform";
		}

		String name = principal.getName();
		blogPostService.save(blogPost, name);
		redirectAttributes.addFlashAttribute("success", true);

		return "redirect:/admin/blog";
	}

	@RequestMapping(value = "/update/{id:[\\d]+}", method = RequestMethod.GET)
	public String updateBlogPost(@PathVariable int id, Model model) {
		BlogPost blogPost = blogPostService.findOne(id);
		if (blogPost == null)
			throw new BlogPostNotFoundException(id);

		model.addAttribute("edit", true);
		model.addAttribute("blogPost", blogPost);
		return "admin/blogform";
	}

	@RequestMapping(value = "/update/{id:[\\d]+}", method = RequestMethod.POST)
	public String updateBlogPost(@PathVariable int id, Model model,
			@Valid @ModelAttribute("blogPost") BlogPost blogPost,
			BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			model.addAttribute("edit", true);
			model.addAttribute("blogPost", blogPost);
			return "admin/blogform";
		}

		blogPostService.update(id, blogPost);

		redirectAttributes.addFlashAttribute("success", true);
		return "redirect:/admin/blog";
	}
	
	@RequestMapping("/remove/{id:[\\d]+}")
	public String removeBlogPost(@PathVariable int id) {
		blogPostService.delete(id);
		return "redirect:/admin/blog";
	}
	
	@RequestMapping("/comments/enable")
	public String enableComments() {
		blogPostService.enableComments(true);
		return "redirect:/admin/blog";
	}
	
	@RequestMapping("/comments/disable")
	public String disableComments() {
		blogPostService.enableComments(false);
		return "redirect:/admin/blog";
	}
	
	@ModelAttribute("blogPost")
	public BlogPost constructBlogPost() {
		return new BlogPost();
	}
}