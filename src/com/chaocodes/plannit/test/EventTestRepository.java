package com.chaocodes.plannit.test;

import java.util.ArrayList;
import java.util.List;

import com.chaocodes.plannit.model.Event;
import com.chaocodes.plannit.model.Model;
import com.chaocodes.plannit.repository.EventRepository;

public class EventTestRepository extends EventRepository
{

	@Override
	public void create(Model model) {
		System.out.println("Model Created: " + model);
	}

	@Override
	public Model read(int id) {
		int month = (int)(Math.random() * 11);
		int day = (int)(Math.random() * 31);
		return new Event("Event id:" + id, 2014, month, day, "2:00");
	}

	@Override
	public List<Event> readByMonthYear(int month, int year) {
		List<Event> events = new ArrayList<Event>();
		int numEvents = (int)(Math.random() * 20);
		for (int i = 0; i < numEvents; i++) {
			int day = (int)(Math.random() * 28);
			Event event = new Event("Event #" + i, year, month, day, "4:45");
			events.add(event);
		}
		return events;
	}

	@Override
	public void update(int id, Model model) {
		System.out.println("Model Updated: " + model);
	}

	@Override
	public void delete(int id) {
		System.out.println("Model id:" + id + " deleted");
	}
}
