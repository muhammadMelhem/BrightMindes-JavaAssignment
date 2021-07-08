package com.brightMinds.javaTest.exceptions;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.brightMinds.javaTest.wrapper.ApiError;

@ControllerAdvice
public class ParamExceptionsHandler {

	final Logger logger = LoggerFactory.getLogger(ParamExceptionsHandler.class);

	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<ApiError> handleConstraintViolationException(Exception ex, WebRequest request) {

		logger.error(ex.getMessage());

		return new ResponseEntity<ApiError>(new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage().toString(), ""),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ MissingServletRequestParameterException.class })
	public ResponseEntity<ApiError> handleMissingServletRequestParameterExceptionn(Exception ex, WebRequest request) {

		logger.error(ex.getMessage());

		return new ResponseEntity<ApiError>(
				new ApiError(HttpStatus.BAD_REQUEST,
						ex.getMessage().substring(0, ex.getMessage().lastIndexOf("'") + 1).toString(), ""),
				HttpStatus.BAD_REQUEST);
	}

}