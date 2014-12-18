package com.chaocodes.plannit.context;

import java.io.File;

import com.almworks.sqlite4java.SQLiteConnection;
import com.almworks.sqlite4java.SQLiteException;

public class SQLiteContext implements Context
{
	private SQLiteConnection db;

	public SQLiteContext(String file) throws SQLiteException {
		db = new SQLiteConnection(new File(file));
		db.open(true);
	}
}
