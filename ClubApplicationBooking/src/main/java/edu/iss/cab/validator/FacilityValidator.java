package edu.iss.cab.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import edu.iss.cab.model.Facility;

@Component
public class FacilityValidator implements Validator {

	private static final Logger logger = LoggerFactory.getLogger(FacilityValidator.class);

	@Override
	public boolean supports(Class<?> clazz) {
		return Facility.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Facility facility = (Facility) target;
		ValidationUtils.rejectIfEmpty(errors, "facilityName", "error.facility.facilityName.empty");
		ValidationUtils.rejectIfEmpty(errors, "price", "error.facility.price.empty");
		ValidationUtils.rejectIfEmpty(errors, "description", "error.facility.description.empty");

		logger.info("Facility: " + facility.toString());
	}

}
