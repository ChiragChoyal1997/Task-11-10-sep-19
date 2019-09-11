package com.ssi.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.ServletContext;

public class DBConnect {
	private static DBConnect connection = null;
	static ServletContext context;
	private static Connection con = null;
	
	private DBConnect(ServletContext c) {
		DBConnect.context = c;
	}
	
	public static DBConnect connect(ServletContext c) {
		if(connection == null) {
			connection = new DBConnect(c);
		}
		return connection;
	}

	public Connection getConnection() throws SQLException {
		if(con == null) {
		try {
			Class.forName(context.getInitParameter("drivername"));
			con = DriverManager.getConnection(context.getInitParameter("url"),context.getInitParameter("username"),context.getInitParameter("pass"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		}
		return con;
	}
	
	
}
