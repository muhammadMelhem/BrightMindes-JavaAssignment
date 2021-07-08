package com.brightMinds.javaTest.validation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.brightMinds.javaTest.utill.ApplicationConstants;

class DateValidator implements ConstraintValidator<CheckDate, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		final String OLD_FORMAT = "yyyy-MM-dd";
		final String NEW_FORMAT = ApplicationConstants.DATE_PATTERN;

 		String oldDateString = value;
		String newDateString;

		SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
		Date date;
		try {
			date = sdf.parse(oldDateString);
			sdf.applyPattern(NEW_FORMAT);
			newDateString = sdf.format(date);

 		} catch (ParseException e) {
			e.printStackTrace();
			return false;

		}

		return true;

//		DateFormat sdf = new SimpleDateFormat(ApplicationConstants.DATE_PATTERN);
//		sdf.setLenient(false);
//		try {
//			sdf.parse(value);
//		} catch (ParseException e) {
//			return false;
//		}
//		return true;
	}
}
