package com.brightMinds.javaTest.controller;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.brightMinds.javaTest.model.Statement;
import com.brightMinds.javaTest.service.StatementService;
import com.brightMinds.javaTest.validation.CheckAmount;
import com.brightMinds.javaTest.validation.CheckDate;

@RestController
@Validated
public class StatementController {

	@Autowired
	StatementService statementService;

	final Logger logger = LoggerFactory.getLogger(StatementController.class);

	@GetMapping("/find-statements")
	public ResponseEntity<List<Statement>> getStatementsByDate(Authentication authentication,
			HttpServletRequest request, @RequestParam(defaultValue = "-1") Long accountId,
			@RequestParam(defaultValue = "1970-01-01") @CheckDate String fromDate,
			@RequestParam(defaultValue = "1970-01-01") @CheckDate String toDate,
			@RequestParam(defaultValue = "-1.1") @CheckAmount String fromAmount,
			@RequestParam(defaultValue = "-1.1") @CheckAmount String toAmount) throws ParseException {

		logger.trace("calling /find-statements");

		SimpleGrantedAuthority auth = new SimpleGrantedAuthority("ADMIN");
		if (authentication != null) {
			List<GrantedAuthority> adminAuth = authentication.getAuthorities().stream()
					.filter((authority) -> authority.getAuthority().equalsIgnoreCase("ADMIN"))
					.collect(Collectors.toList());
			if (!adminAuth.contains(auth) && (!fromDate.equals("1970-01-01") || !toDate.equals("1970-01-01")
					|| !fromAmount.equals("-1.1") || !toAmount.equals("-1.1") || !accountId.equals(-1L))) {
				throw new AccessDeniedException("User without Admin role can't specify filter parameters");
			} else {
				return new ResponseEntity<List<Statement>>(
						statementService.getStatementsByDateRange(accountId, fromDate, toDate, fromAmount, toAmount),
						HttpStatus.OK);
			}
		}

		return new ResponseEntity<List<Statement>>(HttpStatus.NO_CONTENT);

	}

}
