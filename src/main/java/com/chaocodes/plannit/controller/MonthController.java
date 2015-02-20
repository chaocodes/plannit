package com.chaocodes.plannit.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JTextArea;

import com.chaocodes.plannit.repository.EventRepository;
import com.chaocodes.plannit.util.CalendarUtil;
import com.chaocodes.plannit.view.MonthView;

public class MonthController extends Controller implements ActionListener
{
	private EventRepository repository;
	private MonthView view;
	private enum actions {
		PREVIOUSMONTH,
		NEXTMONTH,
	}

	private ModifyEventController modifyEventController;

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

	private void updateEditListener() {
		List<JTextArea> eventTexts = view.getEventTexts();
		for (int i = 0; i < eventTexts.size(); i++) {
			eventTexts.get(i).addMouseListener(modifyEventController);
		}
	}

	private void updateView() {
		view.setYear(CalendarUtil.getYear());
		view.setMonth(CalendarUtil.getMonth());
		view.setDay(CalendarUtil.getDayOfMonth());
		view.setStartDate(CalendarUtil.getStartDate());
		view.setMaxDays(CalendarUtil.getMaxDays());
		view.setEvents(repository.readByMonthYear(view.getMonth(), view.getYear()));
		view.update();
		view.refresh();
		updateEditListener();
	}

	public MonthView getView() {
		return view;
	}

	public ModifyEventController getModifyEventController() {
		return modifyEventController;
	}

	public void setModifyEventController(ModifyEventController modifyEventController) {
		this.modifyEventController = modifyEventController;
	}

	private void previousMonth() {
		CalendarUtil.addMonth(-1);
		updateView();
	}

	private void nextMonth() {
		CalendarUtil.addMonth(1);
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
