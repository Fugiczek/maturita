package eu.fugiczek.maturita.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

public class MyMappingExceptionResolver extends
		SimpleMappingExceptionResolver {

	public static final String ERROR_VIEW_HOME = "home/error";
	public static final String ERROR_VIEW_BLOG = "blog/error";
	public static final String ERROR_VIEW_ADMIN = "admin/error";

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception exception) {
		ModelAndView mav = super.doResolveException(request, response, handler, exception);
		
		mav.addObject("uri", request.getRequestURI());
		mav.setViewName(resolveErrorLocation(request.getRequestURI()));
		
		return mav;
	}
	
	public static String resolveErrorLocation(String url) {
		if(url.contains("/admin")) {
			return ERROR_VIEW_ADMIN;
		} else if(url.contains("/blog")) {
			return ERROR_VIEW_BLOG;
		} else {
			return ERROR_VIEW_HOME;
		}
	}
	
}
