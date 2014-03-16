package pl.devcrowd.app.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CalendarUtils {

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"MM/dd/yyyy hh:mm:ss", Locale.GERMANY);

	public static Calendar getDateDifferBySeconds(int secs, String dateTime) {

		try {
			Date date = dateFormat.parse(dateTime);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.SECOND, secs);
			return cal;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Calendar getCurrentTime() {
		Calendar cal = Calendar.getInstance();
		return cal;
	}

	public static Calendar getTodayAt(int hours) {
		Calendar today = Calendar.getInstance();
		Calendar cal = Calendar.getInstance();
		cal.clear();

		int year = today.get(Calendar.YEAR);
		int month = today.get(Calendar.MONTH);

		int day = today.get(Calendar.DATE);
		cal.set(year, month, day, hours, 0, 0);
		return cal;
	}

	public static String getDateTimeString(Calendar cal) {
		dateFormat.setLenient(false);
		String s = dateFormat.format(cal.getTime());
		return s;
	}
}
