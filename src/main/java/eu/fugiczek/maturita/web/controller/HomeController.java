package eu.fugiczek.maturita.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import eu.fugiczek.maturita.domain.Inquiry;
import eu.fugiczek.maturita.model.service.InquiryService;

@Controller
public class HomeController {

	@Autowired
	private InquiryService inquiryService;
	
	@RequestMapping(value = { "", "/", "index", "home" })
	public String home() {
		return "home/home";
	}

	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String contact() {
		return "home/contact";
	}

	@RequestMapping(value = "/contact", method = RequestMethod.POST)
	public String contact(@Valid @ModelAttribute("msg") Inquiry inquiry,
			BindingResult result, RedirectAttributes redirectAttributes) {
		
		if(result.hasErrors()) {
			return "home/contact";
		}
		
		inquiryService.save(inquiry);
		redirectAttributes.addFlashAttribute("success", true);
		
		return "redirect:/contact";
	}

	@ModelAttribute("msg")
	public Inquiry constructInquiry() {
		return new Inquiry();
	}

}
