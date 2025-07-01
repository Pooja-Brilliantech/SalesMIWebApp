package com.fieldmi.commons;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.fieldmi.FieldMILogger;

public class DateConversions {

	public static final String DATABASE_TIMEZONE = "UTC";

	public static Date convertToUTC(String inputDate) {
		Date date1 = null;
		try {
			DateFormat formatter;
			formatter = new SimpleDateFormat("M/d/y hh:mm:ss a");
			date1 = formatter.parse(inputDate);
			final SimpleDateFormat sdf = new SimpleDateFormat("M/d/y hh:mm:ss a");
			sdf.setTimeZone(TimeZone.getTimeZone("UTC")); // This line converts the given date into UTC time zone
			String utcDate = sdf.format(date1);

			formatter = new SimpleDateFormat("M/d/y hh:mm:ss a");
			date1 = new Date(utcDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			FieldMILogger.error(DateConversions.class.getName(),e);
		}
		return date1;

	}

	public static Date convertToUTC(Date date) {
		Date date1 = null;
		try {
			DateFormat formatter;
			formatter = new SimpleDateFormat("M/d/y hh:mm:ss a");
			date1 = formatter.parse(date.toString());
			final SimpleDateFormat sdf = new SimpleDateFormat("M/d/y hh:mm:ss a");
			sdf.setTimeZone(TimeZone.getTimeZone("UTC")); // This line converts the given date into UTC time zone
			String utcDate = sdf.format(date1);

			formatter = new SimpleDateFormat("M/d/y hh:mm:ss a");
			date1 = new Date(utcDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			FieldMILogger.error(DateConversions.class.getName(),e);
		}
		return date1;

	}

	public static java.util.Date convertTimeZone(java.util.Date date, TimeZone fromTZ, TimeZone toTZ) {
		Date date2 = null;
		if (date == null) {
			date2 = date = new Date();
		}
		if (fromTZ != null && toTZ != null) {

			long fromTZDst = 0;
			if (fromTZ.inDaylightTime(date)) {
				fromTZDst = fromTZ.getDSTSavings();
			}

			long fromTZOffset = fromTZ.getRawOffset() + fromTZDst;

			long toTZDst = 0;
			if (toTZ.inDaylightTime(date)) {
				toTZDst = toTZ.getDSTSavings();
			}
			long toTZOffset = toTZ.getRawOffset() + toTZDst;

			date2 = new java.util.Date(date.getTime() + (toTZOffset - fromTZOffset));
		}
		return date2;
	}

	public static java.util.Date convertTimeZone(java.util.Date date, TimeZone fromTZ) {
		return convertTimeZone(date, fromTZ, TimeZone.getTimeZone(DATABASE_TIMEZONE));
	}

	public static java.util.Date convertTimeZone(java.util.Date date, String fromTZ) {
		return convertTimeZone(date, TimeZone.getTimeZone(fromTZ), TimeZone.getTimeZone(DATABASE_TIMEZONE));
	}

	public static java.util.Date convertTimeZone(java.util.Date date, String fromTZ, String toTZ) {
		return convertTimeZone(date, TimeZone.getTimeZone(fromTZ), TimeZone.getTimeZone(toTZ));
	}

	public static String convertTimeZone(String date, String fromTZ, String toTZ) {
		return convertTimeZone(new Date(date), TimeZone.getTimeZone(fromTZ), TimeZone.getTimeZone(toTZ)).toString();
	}

	public static java.util.Date convertTimeZone(java.util.Date date) {
		return convertTimeZone(date, TimeZone.getDefault(), TimeZone.getTimeZone(DATABASE_TIMEZONE));
	}

	public static String parseDate(Date date, String format) {

		try {

			SimpleDateFormat sdf = new SimpleDateFormat(format); // the
			return sdf.format(date); // format

		} catch (Exception e) {

			// FieldMILogger.debug(this.getClass().getName(),e);
			return null;
		}

	}

	public static Date parseDateTimePicker(String stringDate, String dformat) {

		Date d1 = null;
		try {
			SimpleDateFormat format = new SimpleDateFormat(dformat);
			d1 = format.parse(stringDate);
		} catch (Exception e) {
			return null;
		}

		return d1;
	}

	public static String parseDateTimePicker(Date date, String dformat) {

		try {
			SimpleDateFormat format = new SimpleDateFormat(dformat);
			return format.format(date);
		} catch (Exception e) {
			// FieldMILogger.debug(this.getClass().getName(),e);
			return null;
		}

	}
}