package com.chaocodes.plannit.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.chaocodes.plannit.repository.EventRepository;
import com.chaocodes.plannit.util.CalendarUtils;
import com.chaocodes.plannit.view.MonthView;

public class MonthController extends Controller implements ActionListener
{
	private EventRepository repository;
	private MonthView view;
	private enum actions {
		PREVIOUSMONTH,
		NEXTMONTH,
	}

	public MonthController(EventRepository repository, MonthView view) {
		this.repository = repository;
		this.view = view;
	}

	private void initialView() {
		view.initial();
		updateView();
	}

	private void initialViewButtons() {
		view.getPreviousMonth().setActionCommand(actions.PREVIOUSMONTH.name());
		view.getPreviousMonth().addActionListener(this);
		view.getNextMonth().setActionCommand(actions.NEXTMONTH.name());
		view.getNextMonth().addActionListener(this);
	}

	public void initial() {
		initialView();
		initialViewButtons();
	}

	private void updateView() {
		view.setYear(CalendarUtils.getYear());
		view.setMonth(CalendarUtils.getMonth());
		view.setDay(CalendarUtils.getDayOfMonth());
		view.setStartDate(CalendarUtils.getStartDate());
		view.setMaxDays(CalendarUtils.getMaxDays());
		view.setEvents(repository.readByMonthYear(view.getMonth(), view.getYear()));
		view.update();
		view.refresh();
	}

	public MonthView getView() {
		return view;
	}

	private void previousMonth() {
		CalendarUtils.addMonth(-1);
		updateView();
	}

	private void nextMonth() {
		CalendarUtils.addMonth(1);
		updateView();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if (command == actions.PREVIOUSMONTH.name()) {
			previousMonth();
		} else if (command == actions.NEXTMONTH.name()) {
			nextMonth();
		} else {
			//
		}
	}
}
