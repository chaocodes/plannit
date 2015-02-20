package com.chaocodes.plannit.repository;

import java.util.List;

import com.chaocodes.plannit.context.Context;
import com.chaocodes.plannit.model.Event;
import com.chaocodes.plannit.model.Model;

public class EventRepository implements Repository
{
	private Context context;

	public EventRepository() {
		context = null;
	}

	public EventRepository(Context context) {
		this.context = context;
	}

	@Override
	public void create(Model model) {
		Event event = (Event)model;
		context.insertEvent(event.getName(), event.getYear(), event.getMonth(), event.getDay(), event.getTime());
	}

	@Override
	public Model read(int id) {
		Event event = context.getEventById(id);
		return event;
	}

	public List<Event> readAll() {
		List<Event> events = context.getEvents();
		return events;
	}

	public List<Event> readByYear(int year) {
		List<Event> events = context.getEventsByYear(year);
		return events;
	}

	public List<Event> readByMonthYear(int month, int year) {
		List<Event> events = context.getEventsByMonthYear(month, year);
		return events;
	}

	public List<Event> readByDayMonthYear(int day, int month, int year) {
		List<Event> events = context.getEventsByDayMonthYear(day, month, year);
		return events;
	}

	@Override
	public void update(int id, Model model) {
		Event event = (Event)model;
		context.updateEvent(id, event.getName(), event.getYear(), event.getMonth(), event.getDay(), event.getTime());
	}

	@Override
	public void delete(int id) {
		context.deleteEvent(id);
	}
}
