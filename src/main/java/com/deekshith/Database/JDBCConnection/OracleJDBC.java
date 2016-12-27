package com.deekshith.Database.JDBCConnection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class OracleJDBC {

	Scanner scan;

	public static void main(String[] argv) throws SQLException {

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

		}

		catch (ClassNotFoundException e) {

			System.out.println("Where is your Oracle JDBC Driver?");
			e.printStackTrace();
			return;

		}

		@SuppressWarnings("unused")
		Connection connection = null;
		try {

			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "hr", "oracle");
			PreparedStatement stmt = null;
			stmt = connection.prepareStatement("Drop table students");
			stmt.executeQuery();
			System.out.println("Sql operation Done");

		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;

		}
	}
}