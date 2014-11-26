package com.chaocodes.plannit.model;

public class Event extends Model
{
	private int id;
	private String name;
	private int year;
	private int month;
	private int day;
	private String time;

	public Event() {}

	public Event(String name, int year, int month, int day, String time) {
		id = 0;
		this.name = name;
		this.year = year;
		this.month = month;
		this.day = day;
		this.time = time;
	}

	public Event(int id, String name, int year, int month, int day, String time) {
		this(name, year, month, day, time);
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "id: " + id + " name: " + name + " year: " + year + " month: " + month + " day: " + day + " time: " + time;
	}
}
