package eu.fugiczek.maturita.web.controller;

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

import eu.fugiczek.maturita.domain.Inquiry;
import eu.fugiczek.maturita.domain.ReplyWrapper;
import eu.fugiczek.maturita.domain.exception.InquiryNotFoundException;
import eu.fugiczek.maturita.model.service.InquiryService;
import eu.fugiczek.maturita.model.service.Mailer;
import eu.fugiczek.maturita.web.controller.util.PageWrapper;

@Controller
@RequestMapping("/admin/contact")
public class AdminContactController {

	@Autowired
	private InquiryService inquiryService;
	
	@Autowired
	private Mailer mailer;
	
	@RequestMapping
	public String contact(Model model) {
		PageWrapper<Inquiry> page = new PageWrapper<>(
				inquiryService.findAll(0), "/admin/contact");
		model.addAttribute("page", page);
		return "admin/contact";
	}

	@RequestMapping("/{currentPage:[\\d]+}")
	public String contactPage(@PathVariable int currentPage, Model model) {
		PageWrapper<Inquiry> page = new PageWrapper<>(
				inquiryService.findAll(currentPage - 1), "/admin/contact");
		model.addAttribute("page", page);
		return "admin/contact";
	}
	
	@RequestMapping(value = "/reply/{id:[\\d]+}", method = RequestMethod.GET)
	public String updateBlogPost(@PathVariable int id, Model model) {
		Inquiry inquiry = inquiryService.findOne(id);
		if (inquiry == null)
			throw new InquiryNotFoundException(id);

		model.addAttribute("inquiry", inquiry);
		return "admin/contactform";
	}

	@RequestMapping(value = "/reply/{id:[\\d]+}", method = RequestMethod.POST)
	public String updateBlogPost(@PathVariable int id, Model model,
			@Valid @ModelAttribute("reply") ReplyWrapper reply,
			BindingResult result, RedirectAttributes redirectAttributes) {
		Inquiry inquiry = inquiryService.findOne(id);
		if (inquiry == null)
			throw new InquiryNotFoundException(id);
		
		if (result.hasErrors()) {
			model.addAttribute("inquiry", inquiry);
			return "admin/contactform";
		}

		mailer.send(reply, inquiry.getSender());
		inquiryService.setProcessed(true, id);

		redirectAttributes.addFlashAttribute("success", true);
		return "redirect:/admin/contact";
	}

	@ModelAttribute("reply")
	public ReplyWrapper constructReplyWrapper() {
		return new ReplyWrapper();
	}
	
}