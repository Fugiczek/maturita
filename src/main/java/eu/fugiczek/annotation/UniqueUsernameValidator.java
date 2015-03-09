package eu.fugiczek.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import eu.fugiczek.annotation.UniqueUsername;
import eu.fugiczek.repository.UserRepository;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String>{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void initialize(UniqueUsername constraintAnnotation) {
	}

	@Override
	public boolean isValid(String userName, ConstraintValidatorContext context) {
		if(userRepository == null) {
			return true;
		}
		
		return userRepository.findByName(userName) == null;
	}
	
}
