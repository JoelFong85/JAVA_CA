package edu.iss.cab.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import edu.iss.cab.model.Booking;

@Component
public class BookingValidator implements Validator {

	private static final Logger logger = LoggerFactory.getLogger(BookingValidator.class);

	@Override
	public boolean supports(Class<?> arg0) {
		return Booking.class.isAssignableFrom(arg0);

	}

	@Override
	public void validate(Object target, Errors errors) {
		Booking booking = (Booking) target;
		ValidationUtils.rejectIfEmpty(errors, "date", "error.booking.empty");
		logger.info("Booking: " + booking.toString());
	}
}
