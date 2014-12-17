package com.chaocodes.plannit.util;

import java.util.Calendar;

public final class CalendarUtil
{
	public static String[] dayNames = {
		"Sunday",
		"Monday",
		"Tuesday",
		"Wednesday",
		"Thursday",
		"Friday",
		"Saturday",
	};

	public static String[] monthNames = {
		"January",
		"Febuary",
		"March",
		"April",
		"May",
		"June",
		"July",
		"August",
		"September",
		"October",
		"November",
		"December",
	};

	public static Calendar calendar = (Calendar)Calendar.getInstance().clone(); // For a mutable calendar object

	public static boolean isToday(int year, int month, int dayOfMonth) {
		return (getTodayYear() == year) && (getTodayMonth() == month) && (getTodayDayOfMonth() == dayOfMonth);
	}

	public static int getTodayYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	public static int getTodayMonth() {
		return Calendar.getInstance().get(Calendar.MONTH);
	}

	public static int getTodayDayOfMonth() {
		return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	}

	public static int getYear() {
		return calendar.get(Calendar.YEAR);
	}

	public static void setYear(int i) {
		calendar.set(Calendar.YEAR, i);
	}

	public static int getMaxDays() {
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	public static int getMonth() {
		return calendar.get(Calendar.MONTH);
	}

	public static void setMonth(int i) {
		calendar.set(Calendar.MONTH, i);
	}

	public static int getDayOfMonth() {
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	public static void setDayOfMonth(int i) {
		calendar.set(Calendar.DAY_OF_MONTH, i);
	}

	public static int getDate() {
		return calendar.get(Calendar.DATE);
	}

	public static void setDate(int i) {
		calendar.set(Calendar.DATE, i);
	}

	public static int getDayOfWeek() {
		return calendar.get(Calendar.DAY_OF_WEEK) - 1;
	}

	public static void setDayOfWeek(int i) {
		calendar.set(Calendar.DAY_OF_WEEK, i);
	}

	public static int getStartDate() {
		int tmp = getDayOfMonth();
		setDayOfMonth(1);
		int startDate = getDayOfWeek();
		setDayOfMonth(tmp); // Restore
		return startDate;
	}

	public static void addMonth(int i) {
		calendar.add(Calendar.MONTH, i);
	}

	public static int checkMaxDays(int year, int month) {
		int tmpYear = getYear();
		int tmpMonth = getMonth();
		setYear(year);
		setMonth(month);
		int maxDays = getMaxDays();
		setYear(tmpYear);
		setMonth(tmpMonth);
		return maxDays;
	}

	private CalendarUtil() {}
}
