package com.chaocodes.plannit.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import com.chaocodes.plannit.test.EventTestRepository;
import com.chaocodes.plannit.view.ModifyEventView;
import com.chaocodes.plannit.view.MonthView;
import com.chaocodes.plannit.view.PlannitView;

public class MainController extends Controller implements ActionListener
{
	private PlannitView view;

	private MonthController monthController;
	private ModifyEventController modifyEventController;

	private enum actions {
		ADDEVENT,
	}

	public MainController() {
		view = new PlannitView();
		monthController = new MonthController(new EventTestRepository(), new MonthView());
		modifyEventController = new ModifyEventController(new EventTestRepository(), new ModifyEventView());
	}

	private void initialViewSetup() {
		view.initial();
		monthController.initial();
		view.setCurrentView(monthController.getView());
		view.update();
		view.refresh();
	}

	private void initialViewButtons() {
		view.getAddEvent().setActionCommand(actions.ADDEVENT.name());
		view.getAddEvent().addActionListener(this);
	}

	private void initialControllers() {
		modifyEventController.initial();
	}

	private void initialFrameSettings() {
		view.getWrapper().setTitle("Plannit - Month View");
		view.getWrapper().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		view.getWrapper().pack();
		view.getWrapper().setVisible(true);
	}

	public void initial() {
		initialViewSetup();
		initialViewButtons();
		initialControllers();
		initialFrameSettings();
	}

	public void addEvent() {
		modifyEventController.showView();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if (command == actions.ADDEVENT.name()) {
			addEvent();
		} else {
			//
		}
	}
}
