package eu.fugiczek.maturita.web.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import eu.fugiczek.maturita.domain.BlogPost;
import eu.fugiczek.maturita.domain.User;
import eu.fugiczek.maturita.domain.exception.BlogPostNotFoundException;
import eu.fugiczek.maturita.domain.exception.UserNotFoundException;
import eu.fugiczek.maturita.domain.validation.UserEditValidator;
import eu.fugiczek.maturita.model.service.BlogPostService;
import eu.fugiczek.maturita.model.service.UserService;
import eu.fugiczek.maturita.web.controller.util.PageWrapper;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserService userService;

	@Autowired
	private BlogPostService blogPostService;

	@Autowired
	private UserEditValidator userEditValidator;

	@RequestMapping
	public String index() {
		return "admin/index";
	}

	@RequestMapping("/users")
	public String users(Model model) {
		model.addAttribute("users", userService.findAll());
		return "admin/users";
	}

	@RequestMapping("/user/remove/{id:[\\d]+}")
	public String removeUsers(@PathVariable int id) {
		userService.delete(id);
		return "redirect:/admin/users";
	}

	@RequestMapping(value = "/user/update/{id:[\\d]+}", method = RequestMethod.POST)
	public String updateUser(@PathVariable int id, Model model,
			@Valid @ModelAttribute("user") User user, BindingResult result,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			model.addAttribute("user", user);
			return "admin/userform";
		}

		userService.update(id, user);

		redirectAttributes.addFlashAttribute("success", true);
		return "redirect:/admin/users";
	}

	@RequestMapping(value = "/user/update/{id:[\\d]+}", method = RequestMethod.GET)
	public String updateUser(@PathVariable int id, Model model) {
		User user = userService.findOne(id);
		if (user == null)
			throw new UserNotFoundException(id);

		model.addAttribute("user", user);
		return "admin/userform";
	}

	@RequestMapping("/blog")
	public String blog(Model model) {
		PageWrapper<BlogPost> page = new PageWrapper<>(
				blogPostService.findAll(0), "/admin/blog");
		model.addAttribute("page", page);
		return "admin/blog";
	}

	@RequestMapping("/blog/{currentPage:[\\d]+}")
	public String blogPage(@PathVariable int currentPage, Model model) {
		PageWrapper<BlogPost> page = new PageWrapper<>(
				blogPostService.findAll(currentPage - 1), "/admin/blog");
		model.addAttribute("page", page);
		return "admin/blog";
	}

	@RequestMapping(value = "/blog/add", method = RequestMethod.GET)
	public String addBlogPost(Model model) {
		model.addAttribute("edit", false);
		return "admin/blogform";
	}

	@RequestMapping(value = "/blog/add", method = RequestMethod.POST)
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

	@RequestMapping(value = "/blog/update/{id:[\\d]+}", method = RequestMethod.GET)
	public String updateBlogPost(@PathVariable int id, Model model) {
		BlogPost blogPost = blogPostService.findOne(id);
		if (blogPost == null)
			throw new BlogPostNotFoundException(id);

		model.addAttribute("edit", true);
		model.addAttribute("blogPost", blogPost);
		return "admin/blogform";
	}

	@RequestMapping(value = "/blog/update/{id:[\\d]+}", method = RequestMethod.POST)
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

	@RequestMapping("/blog/remove/{id:[\\d]+}")
	public String removeBlogPost(@PathVariable int id) {
		blogPostService.delete(id);
		return "redirect:/admin/blog";
	}

	@ModelAttribute("blogPost")
	public BlogPost constructBlogPost() {
		return new BlogPost();
	}

	@InitBinder("user")
	public void initBinderUser(WebDataBinder binder) {
		binder.setValidator(userEditValidator);
	}
}
