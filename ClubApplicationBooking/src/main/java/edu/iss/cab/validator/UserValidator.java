package edu.iss.cab.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import edu.iss.cab.model.User;

@Component
public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "error.user.userName.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.user.password.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "error.user.phone.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error.user.email.empty");

		System.out.println(user.toString());
	}
}
