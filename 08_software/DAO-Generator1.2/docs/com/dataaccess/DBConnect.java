package com.dataaccess;
import java.util.ResourceBundle;
import java.util.MissingResourceException;
import java.sql.*;
import java.util.Locale;
public class DBConnect {
	
	private static ResourceBundle resources;
	private String driver;
	private String url ;
	private String userId ;
	private String password ;
	public DBConnect(){
		driver ="oracle.jdbc.driver.OracleDriver";
		url ="jdbc:oracle:thin:@hello:1521:orcl";
		userId ="scott";
		password ="tiger";
	}
	
	public  Connection connect()
	{	
		Connection connection = null;
		String createString;
		Statement stmt;

		try {
			Class.forName(driver);
		} catch(java.lang.ClassNotFoundException e) {
			System.err.println(e.getMessage());
		}

		try {
			connection = DriverManager.getConnection(url, userId, password);
			} catch(SQLException ex) {System.err.println("SQLException: " + ex.getMessage());	}
		return connection;
	}
}
