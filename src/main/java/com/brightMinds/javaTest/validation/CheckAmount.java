package com.brightMinds.javaTest.validation;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ PARAMETER })
@Retention(RUNTIME)
@Constraint(validatedBy = AmountValidator.class)
@Documented
public @interface CheckAmount {

	String message() default "Amount Is Invalid";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}