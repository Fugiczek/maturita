package eu.fugiczek.maturita.domain.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import eu.fugiczek.maturita.domain.User;
import eu.fugiczek.maturita.model.service.UserService;

@Component
public class UserEditValidator implements Validator {

	private Pattern pattern;
	private Matcher matcher;

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors e) {
		User user = (User) target;

		if (user.getName() != null) {
			if (!user.getName().trim().isEmpty()) {
				if (user.getName().length() < 3) {
					e.rejectValue("name", "name.length",
							"Name must be at least 3 characters!");
				} else {
					User tmp = userService.findOne(user.getName());
					if (tmp != null) {
						if (tmp.getId() != user.getId()) {
							e.rejectValue("name", "name.availability",
									"Such username already exists!");
						}
					}
				}
			}
		}

		if (user.getEmail() != null) {
			if (!user.getEmail().trim().isEmpty()) {
				pattern = Pattern.compile(EMAIL_PATTERN);
				matcher = pattern.matcher(user.getEmail());
				if (!matcher.matches()) {
					e.rejectValue("email", "email.format",
							"Invalid email address!");
				} else {
					User tmp = userService.findOneByEmail(user.getEmail());
					if (tmp != null) {
						if (tmp.getId() != user.getId()) {
							e.rejectValue("email", "email.availability",
									"Such email is already used!");
						}
					}
				}
			}
		}

		if (user.getPassword() != null) {
			if (!user.getPassword().trim().isEmpty()) {
				if (user.getPassword().trim().length() < 5) {
					e.rejectValue("password", "password.length",
							"Password must be at least 5 characters!");
				}
			}
		}
	}

}
