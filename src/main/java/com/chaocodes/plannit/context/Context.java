package com.chaocodes.plannit.context;

import java.util.ArrayList;

import com.chaocodes.plannit.model.Event;

public interface Context
{
	public void createTables();

	public Event getEventById(int i);

	public ArrayList<Event> getEvents();

	public ArrayList<Event> getEventsByYear(int year);

	public ArrayList<Event> getEventsByMonthYear(int month, int year);

	public ArrayList<Event> getEventsByDayMonthYear(int day, int month, int year);

	public void insertEvent(String name, int year, int month, int day, String time);

	public void updateEvent(int i, String name, int year, int month, int day, String time);

	public void deleteEvent(int i);
}