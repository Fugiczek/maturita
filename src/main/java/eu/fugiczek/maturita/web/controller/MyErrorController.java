package eu.fugiczek.maturita.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import eu.fugiczek.maturita.web.MyMappingExceptionResolver;

@Controller
@RequestMapping
public class MyErrorController implements ErrorController {

	private static final String PATH = "/error";
	
	@RequestMapping(PATH)
	public String error(HttpServletRequest request, Model model) {
		String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
		if (requestUri == null) {
			requestUri = "Unknown";
		}
		
		model.addAttribute("uri", requestUri);
		model.addAttribute("ex", request.getAttribute("javax.servlet.error.exception"));
		model.addAttribute("statusCode", request.getAttribute("javax.servlet.error.status_code"));
		
		return MyMappingExceptionResolver.resolveErrorLocation(requestUri);
	}

	@Override
	public String getErrorPath() {
		return PATH;
	}
	
}
