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

import eu.fugiczek.maturita.domain.Quote;
import eu.fugiczek.maturita.domain.exception.QuoteNotFoundException;
import eu.fugiczek.maturita.model.service.QuoteService;
import eu.fugiczek.maturita.web.controller.util.PageWrapper;

@Controller
@RequestMapping("admin/quote")
public class AdminQuoteController {

	@Autowired
	private QuoteService quoteService;
	
	@RequestMapping
	public String quote(Model model) {
		PageWrapper<Quote> page = new PageWrapper<>(quoteService.findAll(0), "/admin/quote");
		model.addAttribute("page", page);
		return "admin/quote";
	}
	
	@RequestMapping("/{currentPage:[\\d]+}")
	public String quotePage(@PathVariable int currentPage, Model model) {
		PageWrapper<Quote> page = new PageWrapper<>(quoteService.findAll(currentPage - 1), "/admin/quote");
		model.addAttribute("page", page);
		return "admin/quote";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addQuote(Model model) {
		model.addAttribute("edit", false);
		return "admin/quoteform";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addQuote(Model model,
			@Valid @ModelAttribute("quote") Quote quote,
			BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			model.addAttribute("edit", false);
			return "admin/quoteform";
		}

		quoteService.save(quote);
		redirectAttributes.addFlashAttribute("success", true);

		return "redirect:/admin/quote";
	}

	@RequestMapping(value = "/update/{id:[\\d]+}", method = RequestMethod.GET)
	public String updateQuote(@PathVariable int id, Model model) {
		Quote quote = quoteService.findOne(id);
		if (quote == null)
			throw new QuoteNotFoundException(id);

		model.addAttribute("edit", true);
		model.addAttribute("quote", quote);
		return "admin/quoteform";
	}

	@RequestMapping(value = "/update/{id:[\\d]+}", method = RequestMethod.POST)
	public String updateQuote(@PathVariable int id, Model model,
			@Valid @ModelAttribute("quote") Quote quote,
			BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			model.addAttribute("edit", true);
			model.addAttribute("quote", quote);
			return "admin/quoteform";
		}

		quoteService.update(quote);

		redirectAttributes.addFlashAttribute("success", true);
		return "redirect:/admin/quote";
	}
	
	@RequestMapping("/remove/{id:[\\d]+}")
	public String removeQuote(@PathVariable int id) {
		quoteService.delete(id);
		return "redirect:/admin/quote";
	} 
	
	@ModelAttribute("quote")
	public Quote constructQuote() {
		return new Quote();
	}
	
}