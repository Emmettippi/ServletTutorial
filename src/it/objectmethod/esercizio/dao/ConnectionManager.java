package it.objectmethod.esercizio.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {
	
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	private static final String DB_URL = "jdbc:mysql://localhost:3306/sakila";
	private static final String USER = "root";
	private static final String PASS = "password";
	
	public static Connection getConnection() {
		Connection conn=null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

}
