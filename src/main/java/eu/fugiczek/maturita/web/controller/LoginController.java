package eu.fugiczek.maturita.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/login")
public class LoginController {

	@RequestMapping
	public String login() {
		return "login";
	}
	
	@RequestMapping("/error")
	public String loginError(RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("loginError", true);
		return "redirect:/login";
	}
	
}
