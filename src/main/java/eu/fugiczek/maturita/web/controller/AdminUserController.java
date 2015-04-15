package eu.fugiczek.maturita.web.controller;

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

import eu.fugiczek.maturita.domain.User;
import eu.fugiczek.maturita.domain.exception.UserNotFoundException;
import eu.fugiczek.maturita.domain.validation.UserEditValidator;
import eu.fugiczek.maturita.model.service.UserService;

@Controller
@RequestMapping("/admin/user")
public class AdminUserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserEditValidator userEditValidator;
	
	@RequestMapping
	public String users(Model model) {
		model.addAttribute("users", userService.findAll());
		return "admin/users";
	}
	
	@RequestMapping(value = "/update/{id:[\\d]+}", method = RequestMethod.POST)
	public String updateUser(@PathVariable int id, Model model,
			@Valid @ModelAttribute("user") User user, BindingResult result,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			model.addAttribute("user", user);
			return "admin/userform";
		}

		userService.update(id, user);

		redirectAttributes.addFlashAttribute("success", true);
		return "redirect:/admin/user";
	}

	@RequestMapping(value = "/update/{id:[\\d]+}", method = RequestMethod.GET)
	public String updateUser(@PathVariable int id, Model model) {
		User user = userService.findOne(id);
		if (user == null)
			throw new UserNotFoundException(id);

		model.addAttribute("user", user);
		return "admin/userform";
	}
	
	@RequestMapping("/enable/{id:[\\d]+}")
	public String enableUser(@PathVariable int id) {
		userService.enableUser(true, id);
		return "redirect:/admin/user";
	}
	
	@RequestMapping("/disable/{id:[\\d]+}")
	public String disableUser(@PathVariable int id) {
		userService.enableUser(false, id);
		return "redirect:/admin/user";
	}
	
	@RequestMapping("/remove/{id:[\\d]+}")
	public String removeUser(@PathVariable int id) {
		userService.delete(id);
		return "redirect:/admin/user";
	}
	
	@InitBinder("user")
	public void initBinderUser(WebDataBinder binder) {
		binder.setValidator(userEditValidator);
	}
}
