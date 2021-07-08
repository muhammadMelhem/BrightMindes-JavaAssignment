package com.brightMinds.javaTest.service;

import java.text.ParseException;
import java.util.List;

import com.brightMinds.javaTest.model.Statement;

public interface StatementService {

	List<Statement> getStatementsByDateRange(Long accountId, String fromDate, String toDate, String fromAmount, String toAmount) throws ParseException;

	List<Statement> getStatementsByAmountRange(Long accountId, String fromAmount, String toAmount);

	List<Statement> getStatements();

}
