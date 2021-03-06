package eu.fugiczek.maturita.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import eu.fugiczek.maturita.domain.User;
import eu.fugiczek.maturita.model.service.UserService;

@Controller
@RequestMapping("/register")
public class RegisterController {
	
	@Autowired
	private UserService userService;

	@ModelAttribute("user")
	public User constructUser() {
		return new User();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String register() {
		return "register";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String register(@Valid @ModelAttribute("user") User user,
			BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "register";
		}
		userService.save(user);
		redirectAttributes.addFlashAttribute("success", true);
		return "redirect:/blog";
	}

	@RequestMapping("/available/username")
	@ResponseBody
	public String availableUsername(@RequestParam String username) {
		Boolean available = userService.findOne(username) == null;
		return available.toString();
	}
	
	@RequestMapping("/available/email")
	@ResponseBody
	public String availableEmail(@RequestParam String email) {
		Boolean available = userService.findOneByEmail(email) == null;
		return available.toString();
	}
}
