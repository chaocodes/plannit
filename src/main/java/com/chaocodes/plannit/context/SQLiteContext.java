package com.chaocodes.plannit.context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.chaocodes.plannit.model.Event;

public class SQLiteContext implements Context
{
	private Connection db;

	public SQLiteContext(String file) {
		try {
			Class.forName("org.sqlite.JDBC");
			db = DriverManager.getConnection("jdbc:sqlite:" + file);
			createTables();
		} catch (Exception e) {
			handleException(e);
		}
	}

	private void handleException(Exception e) {
		e.printStackTrace();
		close();
		//TODO
	}

	private void cleanUpStatements(Statement st, ResultSet rs) {
		try { if (rs != null) rs.close(); } catch (Exception e) {};
		try { if (st != null) st.close(); } catch (Exception e) {};
	}

	private void createEventTable() {
		Statement st = null;
		try {
			st = db.createStatement();
			st.executeUpdate("CREATE TABLE if NOT EXISTS events" +
					"(name varchar(255)," +
					"year int, month int," +
					"day int, " +
					"time varchar(100))");
			st.close();
		} catch (Exception e) {
			handleException(e);
		} finally {
			cleanUpStatements(st, null);
		}
	}

	@Override
	public void createTables() {
		createEventTable();
	}

	public Event extractEvent(ResultSet rs) throws Exception {
		return new Event(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getString(6));
	}

	@Override
	public Event getEventById(int i) {
		PreparedStatement st = null;
		ResultSet rs = null;
		Event event = null;
		try {
			st = db.prepareStatement("SELECT rowid,* FROM events WHERE rowid = ?");
			st.setInt(1, i);
			rs = st.executeQuery();
			while (rs.next()) {
				event = extractEvent(rs);
			}
		} catch (Exception e) {
			handleException(e);
		} finally {
			cleanUpStatements(st, rs);
		}
		return event;
	}

	@Override
	public ArrayList<Event> getEvents() {
		Statement st = null;
		ResultSet rs = null;
		ArrayList<Event> events = new ArrayList<Event>();
		try {
			st = db.createStatement();
			rs = st.executeQuery("SELECT rowid,* FROM events");
			while (rs.next()) {
				events.add(extractEvent(rs));
			}
		} catch (Exception e) {
			handleException(e);
		} finally {
			cleanUpStatements(st, rs);
		}
		return events;
	}

	@Override
	public ArrayList<Event> getEventsByYear(int year) {
		PreparedStatement st = null;
		ResultSet rs = null;
		ArrayList<Event> events = new ArrayList<Event>();
		try {
			st = db.prepareStatement("SELECT rowid,* FROM events WHERE year = ?");
			st.setInt(1, year);
			rs = st.executeQuery();
			while (rs.next()) {
				events.add(extractEvent(rs));
			}
		} catch (Exception e) {
			handleException(e);
		} finally {
			cleanUpStatements(st, rs);
		}
		return events;
	}

	@Override
	public ArrayList<Event> getEventsByMonthYear(int month, int year) {
		PreparedStatement st = null;
		ResultSet rs = null;
		ArrayList<Event> events = new ArrayList<Event>();
		try {
			st = db.prepareStatement("SELECT rowid,* FROM events WHERE month = ? AND year = ?");
			st.setInt(1, month);
			st.setInt(2, year);
			rs = st.executeQuery();
			while (rs.next()) {
				events.add(extractEvent(rs));
			}
		} catch (Exception e) {
			handleException(e);
		} finally {
			cleanUpStatements(st, rs);
		}
		return events;
	}

	@Override
	public ArrayList<Event> getEventsByDayMonthYear(int day, int month, int year) {
		PreparedStatement st = null;
		ResultSet rs = null;
		ArrayList<Event> events = new ArrayList<Event>();
		try {
			st = db.prepareStatement("SELECT rowid,* FROM events WHERE day = ? AND month = ? AND year = ?");
			st.setInt(1, day);
			st.setInt(2, month);
			st.setInt(3, year);
			rs = st.executeQuery();
			while (rs.next()) {
				events.add(extractEvent(rs));
			}
		} catch (Exception e) {
			handleException(e);
		} finally {
			cleanUpStatements(st, rs);
		}
		return events;
	}

	@Override
	public void insertEvent(String name, int year, int month, int day, String time) {
		PreparedStatement st = null;
		try {
			st = db.prepareStatement("INSERT INTO events (name, year, month, day, time) VALUES(?, ?, ?, ?, ?)");
			st.setString(1, name);
			st.setInt(2, year);
			st.setInt(3, month);
			st.setInt(4, day);
			st.setString(5, time);
			st.executeUpdate();
		} catch (Exception e) {
			handleException(e);
		} finally {
			cleanUpStatements(st, null);
		}
	}

	@Override
	public void updateEvent(int i, String name, int year, int month, int day, String time) {
		PreparedStatement st = null;
		try {
			st = db.prepareStatement("UPDATE events SET name = ?, year = ?, month = ?, day = ?, time = ? WHERE rowid = ?");
			st.setString(1, name);
			st.setInt(2, year);
			st.setInt(3, month);
			st.setInt(4, day);
			st.setString(5, time);
			st.setInt(6, i);
			st.executeUpdate();
		} catch (Exception e) {
			handleException(e);
		} finally {
			cleanUpStatements(st, null);
		}
	}

	@Override
	public void deleteEvent(int i) {
		PreparedStatement st = null;
		try {
			st = db.prepareStatement("DELETE FROM events WHERE rowid = ?");
			st.setInt(1, i);
			st.executeUpdate();
		} catch (Exception e) {
			handleException(e);
		} finally {
			cleanUpStatements(st, null);
		}
	}

	public void close() {
		try {
			db.close();
		} catch (SQLException e1) {
			System.exit(0);
		}
	}
}
