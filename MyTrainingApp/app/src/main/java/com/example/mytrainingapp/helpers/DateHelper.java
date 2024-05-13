package com.example.mytrainingapp.helpers;

import com.example.mytrainingapp.MyException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Vladyslav Dzyhovskyi
 * @company UnitedThinkers
 * @since 2021/08/02
 */
public class DateHelper {

	private DateHelper() {}

	public static String formatDateToString(Date date, String pattern) {
		return new SimpleDateFormat(pattern).format(date);
	}

	public static Date formatStringToDate(String date) {
		Date result;
		try {
			result = new SimpleDateFormat("").parse(date);
		} catch (ParseException e) {
			throw MyException.error(e);
		}
		return result;
	}

	public static String  currentDate(){
		return new SimpleDateFormat().format(Calendar.getInstance().getTime());
	}
}
