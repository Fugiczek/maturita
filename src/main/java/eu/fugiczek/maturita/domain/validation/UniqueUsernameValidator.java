package eu.fugiczek.maturita.domain.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import eu.fugiczek.maturita.domain.validation.UniqueUsername;
import eu.fugiczek.maturita.model.repository.UserRepository;

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
