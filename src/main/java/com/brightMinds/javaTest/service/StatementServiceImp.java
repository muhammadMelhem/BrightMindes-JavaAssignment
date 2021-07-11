package com.brightMinds.javaTest.service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brightMinds.javaTest.Repository.StatmentRepository;
import com.brightMinds.javaTest.model.Statement;
import com.brightMinds.javaTest.utill.ApplicationConstants;
import com.brightMinds.javaTest.utill.DateUtill;

@Service
public class StatementServiceImp implements StatementService {

	@Autowired
	StatmentRepository statmentDAO;

	@Override
	public List<Statement> getStatementsByDateRange(Long accountId, String fromDate, String toDate, String fromAmount,
			String toAmount) throws ParseException {

		List<Statement> statementsWithDateRange = statmentDAO.findByAccountId(accountId);
		List<Statement> statements = new ArrayList<Statement>();

		if (!fromDate.equals("1970-01-01") && !fromAmount.equals("-1.1")) {

			statements = applyDateFilters(statementsWithDateRange, fromDate, toDate);
			statements = applyAmountFilters(statements, fromAmount, toAmount);

		} else if (!fromDate.equals("1970-01-01") && fromAmount.equals("-1.1")) {
			statements = applyDateFilters(statementsWithDateRange, fromDate, toDate);

		} else if (fromDate.equals("1970-01-01") && !fromAmount.equals("-1.1")) {
			statements = applyAmountFilters(statementsWithDateRange, fromAmount, toAmount);

		} else if (fromDate.equals("1970-01-01") && fromAmount.equals("-1.1") && !accountId.equals(-1L)) {
			statements = statementsWithDateRange;

		} else {
			statements = getStatements();

		}

		return statements;
	}

	@Override
	public List<Statement> getStatementsByAmountRange(Long accountId, String fromAmount, String toAmount) {

		Double from = Double.parseDouble(fromAmount);
		Double to = Double.parseDouble(toAmount);

		List<Statement> statementsWithDateRange = statmentDAO.findByAccountId(accountId).parallelStream()
				.filter(statment -> {
					return Double.parseDouble(statment.getAmount()) >= from
							&& Double.parseDouble(statment.getAmount()) <= to;
				}).collect(Collectors.toList());

		return statementsWithDateRange;

	}

	@Override
	public List<Statement> getStatements() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ApplicationConstants.DATE_PATTERN);

		List<Statement> statements = statmentDAO.findAll();
		statements.sort((obj1, obj2) -> LocalDate.parse(obj1.getDateField(), formatter)
				.compareTo(LocalDate.parse(obj2.getDateField(), formatter)));

		LocalDate fromDate = LocalDate.parse(statements.get(0).getDateField(), formatter);
		LocalDate toDate = fromDate.plusMonths(3);

		List<Statement> statementsWithin3Months = new ArrayList<Statement>();
		for (int i = 0; i < statements.size(); i++) {
			if (LocalDate.parse(statements.get(i).getDateField(), formatter).isBefore(toDate)) {
				statementsWithin3Months.add(statements.get(i));

			} else
				break;
		}

		return statementsWithin3Months;
	}

	private List<Statement> applyDateFilters(List<Statement> statments, String fromDate, String toDate)
			throws ParseException {
		Date fDate = DateUtill.convertDatePattern(fromDate, "yyyy-MM-dd", ApplicationConstants.DATE_PATTERN);
		Date tDate = DateUtill.convertDatePattern(toDate, "yyyy-MM-dd", ApplicationConstants.DATE_PATTERN);

		List<Statement> filteredStatments = statments.parallelStream().filter(statment -> {
			try {
				return checkDates(fDate, tDate, statment.getDateField());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return false;
		}).collect(Collectors.toList());

		return filteredStatments;

	}

	private List<Statement> applyAmountFilters(List<Statement> statments, String fromAmount, String toAmount) {

		Double from = Double.parseDouble(fromAmount);
		Double to = Double.parseDouble(toAmount);

		List<Statement> filteredStatments = statments.parallelStream().filter(statment -> {
			return Double.parseDouble(statment.getAmount()) >= from && Double.parseDouble(statment.getAmount()) <= to;
		}).collect(Collectors.toList());

		return filteredStatments;

	}

	private boolean checkDates(Date fromDate, Date toDate, String date) throws ParseException {

		return (DateUtill.convertDatePattern(date, ApplicationConstants.DATE_PATTERN, "yyyy-MM-dd").after(fromDate)
				|| DateUtill.convertDatePattern(date, ApplicationConstants.DATE_PATTERN, "yyyy-MM-dd").equals(fromDate))
				&& (DateUtill.convertDatePattern(date, ApplicationConstants.DATE_PATTERN, "yyyy-MM-dd").before(toDate)
						|| DateUtill.convertDatePattern(date, ApplicationConstants.DATE_PATTERN, "yyyy-MM-dd")
								.equals(toDate));

	}

}
