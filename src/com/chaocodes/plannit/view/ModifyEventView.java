package com.chaocodes.plannit.view;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.chaocodes.plannit.util.CalendarUtils;

public class ModifyEventView implements View
{
	JDialog wrapper;
	JPanel container;

	JTextField name;
	JComboBox<String> year;
	JComboBox<String> month;
	JComboBox<String> day;
	JTextField time;

	JButton addEvent;
	JButton editEvent;

	public ModifyEventView() {}

	private void initialWrapper() {
		wrapper = new JDialog();
	}

	private void initialContainer() {
		container = new JPanel();
		container.setLayout(new GridLayout(0, 1));
		container.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		wrapper.add(container);
	}

	private JLabel createLabel(String label) {
		JLabel fieldLabel = new JLabel(label + ":");
		return fieldLabel;
	}

	private JTextField createField() {
		JTextField field = new JTextField();
		field.setPreferredSize(new Dimension(250, 25));
		return field;
	}

	private JComboBox<String> createSelector(String[] options) {
		JComboBox<String> selector = new JComboBox<String>(options);
		return selector;
	}

	private JButton createEventButton(String text) {
		JButton button = new JButton(text);
		return button;
	}

	private String[] createSelectorYears() {
		String[] years = new String[30];
		int year = CalendarUtils.getTodayYear();
		for (int i = 0; i < 30; i++) {
			years[i] = Integer.toString(year);
			year++;
		}
		return years;
	}

	public String[] createSelectorDays(int maxDays) {
		String[] days = new String[maxDays];
		for (int i = 0; i < maxDays; i++) {
			days[i] = Integer.toString(i + 1);
		}
		return days;
	}

	private void initialComponents() {
		container.add(createLabel("Name"));
		name = createField();
		container.add(name);
		container.add(createLabel("Year"));
		year = createSelector(createSelectorYears());
		container.add(year);
		container.add(createLabel("Month"));
		month = createSelector(CalendarUtils.monthNames);
		month.setSelectedIndex(CalendarUtils.getTodayMonth());
		container.add(month);
		container.add(createLabel("Day"));
		day = createSelector(createSelectorDays(CalendarUtils.getMaxDays()));
		container.add(day);
		container.add(createLabel("Time"));
		time = createField();
		container.add(time);
		addEvent = createEventButton("Add Event");
		editEvent = createEventButton("Edit Event");
		editEvent.setVisible(false);
		container.add(addEvent);
		container.add(editEvent);
	}

	@Override
	public void initial() {
		initialWrapper();
		initialContainer();
		initialComponents();
	}

	@Override
	public void update() {

	}

	@Override
	public void refresh() {

	}

	public JDialog getWrapper() {
		return wrapper;
	}

	@Override
	public JPanel getContainer() {
		return container;
	}

	public JTextField getName() {
		return name;
	}

	public JComboBox<String> getYear() {
		return year;
	}

	public JComboBox<String> getMonth() {
		return month;
	}

	public JComboBox<String> getDay() {
		return day;
	}

	public JTextField getTime() {
		return time;
	}

	public JButton getAddEvent() {
		return addEvent;
	}

	public JButton getEditEvent() {
		return editEvent;
	}
}
