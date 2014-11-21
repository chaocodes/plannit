package com.chaocodes.plannit.repository;

import java.util.ArrayList;
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
		// Add model to DB
	}

	@Override
	public Model read(int id) {
		Event event = new Event();
		return event;
	}

	public List<Event> readAll() {
		List<Event> events = new ArrayList<Event>();
		return events;
	}

	public List<Event> readByYear(int year) {
		List<Event> events = new ArrayList<Event>();
		return events;
	}

	public List<Event> readByMonthYear(int month, int year) {
		List<Event> events = new ArrayList<Event>();
		return events;
	}

	public List<Event> readByDayMonthYear(int day, int month, int year) {
		List<Event> events = new ArrayList<Event>();
		return events;
	}

	@Override
	public void update(int id, Model model) {
		// Update model in DB
	}

	@Override
	public void delete(int id) {
		// Delete model from DB
	}
}
