package com.brightMinds.javaTest.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

class AmountValidator implements ConstraintValidator<CheckAmount, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		try {
			double myDouble = Double.parseDouble(value);
			myDouble -= (int) myDouble;

			return myDouble != (double) 0.00;

		} catch (NumberFormatException e) {
			return false;
		}
	}
}
