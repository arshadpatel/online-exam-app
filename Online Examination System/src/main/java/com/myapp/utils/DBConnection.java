package com.myapp.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//This class is to create the connection with the database
public class DBConnection {
	private static final String URL = "jdbc:mysql://localhost:3306/onlineexamapp";
//	onlineexamapp is the database name
	private static final String USERNAME = "root";
	private static final String PASSWORD = "admin";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}
}
