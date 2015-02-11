package com.chaocodes.plannit.context;

import java.io.File;
import java.util.ArrayList;

import com.almworks.sqlite4java.SQLiteConnection;
import com.almworks.sqlite4java.SQLiteException;
import com.almworks.sqlite4java.SQLiteStatement;
import com.chaocodes.plannit.model.Event;

public class SQLiteContext implements Context
{
	private SQLiteConnection db;

	public SQLiteContext(String file) {
		try {
			db = new SQLiteConnection(new File(file));
			db.open(true);
			createTables();
		} catch (SQLiteException e) {
			db.dispose();
			//TODO end program
		}
	}

	private void handleException(SQLiteException e) {
		e.printStackTrace();
		//TODO
	}

	private void createEventTable() {
		SQLiteStatement st = null;
		try {
			st = db.prepare("CREATE TABLE if NOT EXISTS events" +
					"(name varchar(255), year int, month int, day int, time varchar(100))");
			st.step();
		} catch (SQLiteException e) {
			handleException(e);
		} finally {
			if (st != null) {
				st.dispose();
			}
		}
	}

	@Override
	public void createTables() {
		createEventTable();
	}

	public Event extractEvent(SQLiteStatement st) throws SQLiteException {
		return new Event(st.columnInt(0), st.columnString(1), st.columnInt(2), st.columnInt(3), st.columnInt(4), st.columnString(5));
	}

	@Override
	public Event getEventById(int i) {
		SQLiteStatement st = null;
		Event event = null;
		try {
			st = db.prepare("SELECT rowid,* FROM events WHERE rowid = ?");
			st.bind(1, i);
			while (st.step()) {
				event = extractEvent(st);
			}
		} catch (SQLiteException e) {
			handleException(e);
		} finally {
			if (st != null) {
				st.dispose();
			}
		}
		return event;
	}

	@Override
	public ArrayList<Event> getEvents() {
		SQLiteStatement st = null;
		ArrayList<Event> events = new ArrayList<Event>();
		try {
			st = db.prepare("SELECT rowid,* FROM events");
			while (st.step()) {
				events.add(extractEvent(st));
			}
		} catch (SQLiteException e) {
			handleException(e);
		} finally {
			if (st != null) {
				st.dispose();
			}
		}
		return events;
	}

	@Override
	public ArrayList<Event> getEventsByYear(int year) {
		SQLiteStatement st = null;
		ArrayList<Event> events = new ArrayList<Event>();
		try {
			st = db.prepare("SELECT rowid,* FROM events WHERE year = ?");
			st.bind(1, year);
			while (st.step()) {
				events.add(extractEvent(st));
			}
		} catch (SQLiteException e) {
			handleException(e);
		} finally {
			st.dispose();
		}
		return events;
	}

	@Override
	public ArrayList<Event> getEventsByMonthYear(int month, int year) {
		SQLiteStatement st = null;
		ArrayList<Event> events = new ArrayList<Event>();
		try {
			st = db.prepare("SELECT rowid,* FROM events WHERE month = ? AND year = ?");
			st.bind(1, month);
			st.bind(2, year);
			while (st.step()) {
				events.add(extractEvent(st));
			}
		} catch (SQLiteException e) {
			handleException(e);
		} finally {
			if (st != null) {
				st.dispose();
			}
		}
		return events;
	}

	@Override
	public ArrayList<Event> getEventsByDayMonthYear(int day, int month, int year) {
		SQLiteStatement st = null;
		ArrayList<Event> events = new ArrayList<Event>();
		try {
			st = db.prepare("SELECT rowid,* FROM events WHERE day = ? AND month = ? AND year = ?");
			st.bind(1, day);
			st.bind(2, month);
			st.bind(3, year);
			while (st.step()) {
				events.add(extractEvent(st));
			}
		} catch (SQLiteException e) {
			handleException(e);
		} finally {
			if (st != null) {
				st.dispose();
			}
		}
		return events;
	}

	@Override
	public void insertEvent(String name, int year, int month, int day, String time) {
		SQLiteStatement st = null;
		try {
			st = db.prepare("INSERT INTO events (name, year, month, day, time) VALUES(?, ?, ?, ?, ?)");
			st.bind(1, name);
			st.bind(2, year);
			st.bind(3, month);
			st.bind(4, day);
			st.bind(5, time);
			st.step();
		} catch (SQLiteException e) {
			handleException(e);
		} finally {
			if (st != null) {
				st.dispose();
			}
		}
	}

	@Override
	public void updateEvent(int i, String name, int year, int month, int day, String time) {
		SQLiteStatement st = null;
		try {
			st = db.prepare("UPDATE events SET name = ?, year = ?, month = ?, day = ?, time = ? WHERE rowid = ?");
			st.bind(1, name);
			st.bind(2, year);
			st.bind(3, month);
			st.bind(4, day);
			st.bind(5, time);
			st.bind(6, i);
			st.step();
		} catch (SQLiteException e) {
			handleException(e);
		} finally {
			if (st != null) {
				st.dispose();
			}
		}
	}

	@Override
	public void deleteEvent(int i) {
		SQLiteStatement st = null;
		try {
			st = db.prepare("DELETE FROM events WHERE rowid = ?");
			st.bind(1, i);
			st.step();
		} catch (SQLiteException e) {
			handleException(e);
		} finally {
			if (st != null) {
				st.dispose();
			}
		}
	}

	public void close() {
		db.dispose();
	}
}
