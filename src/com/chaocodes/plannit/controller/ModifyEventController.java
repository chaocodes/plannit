package com.chaocodes.plannit.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import com.chaocodes.plannit.model.Event;
import com.chaocodes.plannit.repository.EventRepository;
import com.chaocodes.plannit.utils.CalendarUtils;
import com.chaocodes.plannit.view.ModifyEventView;

public class ModifyEventController extends Controller implements ActionListener
{
	private EventRepository repository;
	private ModifyEventView view; // Use dedicated view? Or keep spawning new view?
	private enum actions {
		ADDEVENT,
		EDITEVENT,
		CHANGEDATE,
	}

	public ModifyEventController(EventRepository repository, ModifyEventView view) {
		this.repository = repository;
		this.view = view;
	}

	private void initialView() {
		view.initial();
		view.getWrapper().setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		view.getWrapper().setVisible(false);
	}

	private void initialButtons() {
		view.getAddEvent().setActionCommand(actions.ADDEVENT.name());
		view.getAddEvent().addActionListener(this);
		view.getEditEvent().setActionCommand(actions.EDITEVENT.name());
		view.getEditEvent().addActionListener(this);
		view.getYear().setActionCommand(actions.CHANGEDATE.name());
		view.getYear().addActionListener(this);
		view.getMonth().setActionCommand(actions.CHANGEDATE.name());
		view.getMonth().addActionListener(this);
	}

	public void initial() {
		initialView();
		initialButtons();
	}

	public void updateView() {
		view.update();
		view.refresh();
	}

	public void showView() {
		view.getWrapper().setTitle("Add Event");
		view.getWrapper().pack();
		view.getWrapper().setVisible(true);
	}

	public ModifyEventView getView() {
		return view;
	}

	private void addEvent() {
		if (view.getName() != null) {
			String name = view.getName().getText();
			int year = Integer.parseInt((String)view.getYear().getSelectedItem());
			int month = view.getMonth().getSelectedIndex();
			int day = view.getDay().getSelectedIndex() + 1;
			String time = view.getTime().getText();
			Event event = new Event(name, year, month, day, time);
			repository.create(event);
		}
	}

	private void editEvent() {

	}

	private void changeDate() {
		int year = Integer.parseInt((String)view.getYear().getSelectedItem());
		int month = view.getMonth().getSelectedIndex();
		int maxDays = CalendarUtils.checkMaxDays(year, month);
		int currentDays = view.getDay().getItemCount();
		if (currentDays > maxDays) {
			for (int i = currentDays; i > maxDays; i--) {
				view.getDay().removeItemAt(i - 1); // Subtract days
			}
		} else if (currentDays < maxDays) {
			for (int i = currentDays + 1; i <= maxDays; i++) {
				view.getDay().addItem("" + i); // Add more days
			}
		} else {
			// Do nothing
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if (command == actions.ADDEVENT.name()) {
			addEvent();
		} else if (command == actions.EDITEVENT.name()) {
			editEvent();
		} else if (command == actions.CHANGEDATE.name()) {
			changeDate();
		} else {
			//
		}
	}
}
