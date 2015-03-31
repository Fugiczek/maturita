package eu.fugiczek.maturita.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import eu.fugiczek.maturita.web.MyMappingExceptionResolver;

@Configuration
public class ExceptionConfig {

	@Bean(name = "simpleMappingExceptionResolver")
	public MyMappingExceptionResolver createMyMappingExceptionResolver() {
		MyMappingExceptionResolver resolver = new MyMappingExceptionResolver();

		/*Properties mappings = new Properties();
		resolver.setExceptionMappings(mappings);*/
		  
		resolver.setExceptionAttribute("ex");
		resolver.setDefaultErrorView(""); 
		
		return resolver;
	}
	
}
