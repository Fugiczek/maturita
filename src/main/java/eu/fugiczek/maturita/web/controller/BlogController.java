package eu.fugiczek.maturita.web.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import eu.fugiczek.maturita.domain.BlogPost;
import eu.fugiczek.maturita.domain.Comment;
import eu.fugiczek.maturita.domain.exception.BlogPostNotFoundException;
import eu.fugiczek.maturita.model.service.BlogPostService;
import eu.fugiczek.maturita.model.service.CommentService;
import eu.fugiczek.maturita.model.service.QuoteService;
import eu.fugiczek.maturita.web.controller.util.PageWrapper;

@Controller
@RequestMapping("/blog")
public class BlogController {

	@Autowired
	private BlogPostService blogPostService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private QuoteService quoteService;
	
	@RequestMapping
	public String show(Model model) {
		PageWrapper<BlogPost> page = new PageWrapper<>(
				blogPostService.findAll(0), "/blog");
		model.addAttribute("quote", quoteService.getRandomQuote());
		model.addAttribute("page", page);
		return "blog/index";
	}

	@RequestMapping("/{currentPage:[\\d]+}")
	public String showPage(@PathVariable Integer currentPage, Model model) {
		PageWrapper<BlogPost> page = new PageWrapper<>(
				blogPostService.findAll(currentPage - 1), "/blog");
		model.addAttribute("quote", quoteService.getRandomQuote());
		model.addAttribute("page", page);
		return "blog/index";
	}

	@RequestMapping("/show/{id:[\\d]+}")
	public String show(@PathVariable int id, Model model)
			throws BlogPostNotFoundException {
		BlogPost blogPost = blogPostService.findOneWithComments(id);
		if (blogPost == null)
			throw new BlogPostNotFoundException(id);
		model.addAttribute("quote", quoteService.getRandomQuote());
		model.addAttribute("post", blogPost);
		return "blog/post";
	}

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/comment/{id:[\\d]+}", method = RequestMethod.POST)
	public String addComment(@PathVariable int id, Model model,
			@Valid @ModelAttribute("comment") Comment comment,
			BindingResult result, Principal principal) {

		if (result.hasErrors()) {
			BlogPost blogPost = blogPostService.findOneWithComments(id);
			if (blogPost == null)
				throw new BlogPostNotFoundException(id);
			model.addAttribute("quote", quoteService.getRandomQuote());
			model.addAttribute("post", blogPost);

			return "blog/post";
		}

		String name = principal.getName();
		commentService.save(comment, name, id);

		return "redirect:/blog/show/" + id;
	}

	@ModelAttribute("comment")
	public Comment constructComment() {
		return new Comment();
	}
}
