package com.chaocodes.plannit.test;

import java.util.ArrayList;
import java.util.List;

import com.chaocodes.plannit.model.Event;
import com.chaocodes.plannit.model.Model;
import com.chaocodes.plannit.repository.EventRepository;
import com.chaocodes.plannit.utils.CalendarUtils;

public class EventTestRepository extends EventRepository
{

	@Override
	public void create(Model model) {
		System.out.println("Model Created: " + model);
	}

	@Override
	public Model read(int id) {
		int month = (int)(Math.random() * 11);
		int day = (int)(Math.random() * 28);
		return new Event(id, "Event id:" + id, 2014, month, day, "2:00");
	}

	@Override
	public List<Event> readByMonthYear(int month, int year) {
		List<Event> events = new ArrayList<Event>();
		for (int i = 1; i <= CalendarUtils.checkMaxDays(year, month); i++) {
			int random = (int)(Math.random() * 10);
			if (random > 6) {
				Event event = new Event("Event #" + i, year, month, i, "4:45");
				events.add(event);
			}
		}
		return events;
	}

	@Override
	public void update(int id, Model model) {
		System.out.println("Model id: " + id + " Updated: " + model);
	}

	@Override
	public void delete(int id) {
		System.out.println("Model id:" + id + " deleted");
	}
}
