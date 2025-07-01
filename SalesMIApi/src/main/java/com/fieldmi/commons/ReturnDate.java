package com.fieldmi.commons;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class ReturnDate {
	
	
	public static String returnLastUpdateddate()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR, 5);
		calendar.add(Calendar.MINUTE, 30);
		calendar.getTime();
		String date=sdf.format(calendar.getTime());
		
		return date;
	}
	
	public static String formatDateToString(Date date, String format,
			String timeZone) {
		// null check
		if (date == null) 
			return null;
		// create SimpleDateFormat object with input format
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		// default system timezone if passed null or empty
		if (timeZone == null || "".equalsIgnoreCase(timeZone.trim())) {
			timeZone = Calendar.getInstance().getTimeZone().getID();
		}
		// set timezone to SimpleDateFormat
		sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
		// return Date in required format with timezone as String
		return sdf.format(date);
	}
	
	
	
	public static String UTCDateTime(String date)
	{
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		 
        String currentTime=date;
 
       
 
        LocalDateTime datetime = LocalDateTime.parse(currentTime,formatter);
 
        datetime=datetime.minusHours(5);
        datetime=datetime.minusMinutes(30);
 
        String aftersubtraction=datetime.format(formatter);
       
		return aftersubtraction;
		
	}

}
