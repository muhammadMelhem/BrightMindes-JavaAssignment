package com.brightMinds.javaTest.exceptions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AuthExceptionsHandler implements AuthenticationEntryPoint {

	final Logger logger = LoggerFactory.getLogger(AuthExceptionsHandler.class);

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication Failed");
	}

	@ExceptionHandler(value = { AccessDeniedException.class })
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException {

		logger.error(
				HttpServletResponse.SC_UNAUTHORIZED + " Authorization Failed : " + accessDeniedException.getMessage());
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
				"Authorization Failed : " + accessDeniedException.getMessage());
	}

}