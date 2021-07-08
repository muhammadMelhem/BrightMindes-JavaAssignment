package com.brightMinds.javaTest.wrapper;

import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ApiError {
	private HttpStatus status;
	private String message;
	private Date date;

	public ApiError(HttpStatus status, String message, String error) {
		super();
		this.status = status;
		this.message = message.substring(message.indexOf('.') + 1);
		this.date = new Date();
	}
}
