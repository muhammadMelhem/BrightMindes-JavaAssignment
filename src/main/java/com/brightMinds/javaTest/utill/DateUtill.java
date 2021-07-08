package com.brightMinds.javaTest.utill;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class DateUtill {

	public static Date convertDatePattern(String dateString, String oldPattern, String newPattern)
			throws ParseException {

		final String OLD_FORMAT = oldPattern;
		final String NEW_FORMAT = newPattern;

		String oldDateString = dateString;
		String newDateString;

		SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
		java.util.Date d;
		try {
			d = sdf.parse(oldDateString);
			sdf.applyPattern(NEW_FORMAT);
			newDateString = sdf.format(d);

			Date datess = new SimpleDateFormat(NEW_FORMAT).parse(newDateString);

			return datess;

		} catch (ParseException e) {
			e.printStackTrace();

		}

		return null;

	}

}
