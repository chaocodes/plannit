package com.chaocodes.plannit.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import com.chaocodes.plannit.model.Event;
import com.chaocodes.plannit.util.CalendarUtils;

public class MonthView implements View
{
	private JPanel container;
	private JPanel header;
	private JPanel monthOfYear;
	private JLabel monthName;
	private JPanel dayOfWeek;
	private JPanel calendar;

	private int year;
	private int month;
	private int day;
	private int startDate;
	private int maxDays;

	private JPanel[] days;
	private List<Event> events;

	private JButton previousMonth;
	private JButton nextMonth;

	public MonthView() {}

	private void initialContainer() {
		container = new JPanel();
		container.setLayout(new BorderLayout());
	}

	private JButton createChangeMonthButton(String text) {
		JButton changeMonthButton = new JButton(text);
		return changeMonthButton;
	}

	private void initialMonthOfYear() {
		monthOfYear = new JPanel();
		monthOfYear.setLayout(new GridLayout(0, 3, 0, 0));
		monthName = new JLabel("", SwingConstants.CENTER);
		previousMonth = createChangeMonthButton("<");
		nextMonth = createChangeMonthButton(">");
		monthOfYear.add(previousMonth);
		monthOfYear.add(monthName);
		monthOfYear.add(nextMonth);
		header.add(monthOfYear, BorderLayout.NORTH);
	}

	private JLabel createDayName(int i) {
		JLabel dayName = new JLabel(CalendarUtils.dayNames[i]);
		dayName.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		return dayName;
	}

	private void initialDayOfWeek() {
		dayOfWeek = new JPanel();
		dayOfWeek.setLayout(new GridLayout(0, 7, 0, 0));
		for (int i = 0; i < 7; i++) {
			dayOfWeek.add(createDayName(i));
		}
		header.add(dayOfWeek, BorderLayout.CENTER);
	}

	private void initialHeader() {
		header = new JPanel();
		header.setLayout(new BorderLayout());
		initialMonthOfYear();
		initialDayOfWeek();
		container.add(header, BorderLayout.NORTH);
	}

	private void initialCalendar() {
		calendar = new JPanel();
		calendar.setLayout(new GridLayout(0, 7, 0, 0));
		container.add(calendar, BorderLayout.CENTER);
	}

	private void initialDays() {
		days = new JPanel[31];
		for (int i = 0; i < days.length; i++) {
			days[i] = createDay();
		}
	}

	@Override
	public void initial() {
		initialContainer();
		initialHeader();
		initialCalendar();
		initialDays();
	}

	private void updateHeader() {
		monthName.setText(CalendarUtils.monthNames[month] + " " + year);
	}

	private JPanel createBox() {
		JPanel box = new JPanel();
		box.setLayout(new BorderLayout());
		box.setPreferredSize(new Dimension(100, 100));
		return box;
	}

	private JPanel createDay() {
		JPanel day = createBox();
		day.setBackground(Color.WHITE);
		day.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		return day;
	}

	private JPanel createFiller() {
		JPanel filler = createBox();
		filler.setBorder(BorderFactory.createLineBorder(Color.GRAY));
		return filler;
	}

	private void updateCalendar() {
		calendar.removeAll();
		if (startDate == 0 && startDate + maxDays < 35) { // Match alignment of Windows Calendar
			startDate = 7;
		}
		for (int i = 0; i < startDate; i++) { // Filler days
			calendar.add(createFiller());
		}
		for (int i = 0; i < maxDays; i++) { // Real days
			calendar.add(days[i]);
		}
		for (int i = maxDays + startDate; i < 42; i++) { // Filler days to always reach 6*7 calendar
			calendar.add(createFiller());
		}
	}

	private JLabel createDayNumber(int i) {
		JLabel dayNumber = new JLabel("" + (i + 1));
		return dayNumber;
	}

	private JTextArea createEvent(Event event) {
		JTextArea name = new JTextArea(event.getName() + " at " + event.getTime());
		return name;
	}

	private void updateEvents() {
		for (int i = 0; i < days.length; i++) {
			days[i].removeAll();
			days[i].add(createDayNumber(i), BorderLayout.NORTH);
			for (Event event : events) {
				if (event.getDay() == (i + 1)) {
					days[i].add(createEvent(event), BorderLayout.CENTER);
				}
			}
		}
	}

	@Override
	public void update() {
		updateHeader();
		updateCalendar();
		updateEvents();
	}

	private void refreshDays() {
		for (int i = 0; i < maxDays; i++) {
			days[i].repaint();
			days[i].revalidate();
		}
	}

	private void refreshCalendar() {
		calendar.repaint();
		calendar.revalidate();
	}

	@Override
	public void refresh() {
		refreshDays();
		refreshCalendar();
	}

	@Override
	public JPanel getContainer() {
		return container;
	}

	public JPanel getHeader() {
		return header;
	}

	public JPanel getMonthOfYear() {
		return monthOfYear;
	}

	public JPanel getDayOfWeek() {
		return dayOfWeek;
	}

	public JPanel getCalendar() {
		return calendar;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getStartDate() {
		return startDate;
	}

	public void setStartDate(int startDate) {
		this.startDate = startDate;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMaxDays() {
		return maxDays;
	}

	public void setMaxDays(int maxDays) {
		this.maxDays = maxDays;
	}

	public JPanel getDay(int i) {
		return days[i];
	}

	public void setDay(int i, JPanel day) {
		days[i] = day;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public JButton getPreviousMonth() {
		return previousMonth;
	}

	public JButton getNextMonth() {
		return nextMonth;
	}
}
