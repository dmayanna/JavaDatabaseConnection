package com.deekshith.initiator;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.deekshith.Database.DatabaseOperations;
import com.deekshith.Database.JDBCConnection;

public class DatabaseOpearionsInitiator {
	final static Logger logger = Logger.getLogger(DatabaseOpearionsInitiator.class);
	public static void main(String[] args) {
		logger.info("Entering main Method");
		DatabaseOperations dbop = new DatabaseOperations();
		long startTime = System.currentTimeMillis();
		JDBCConnection jdbc = new JDBCConnection();
		Connection con = jdbc.getSqlConnection();

		for (int i = 0; i < 1000; i++) {
			String name = dbop.getRandomName();
			String city = dbop.getRandomCity();
			String profession = dbop.obtainProfession(city,con);
			dbop.addEmployee(profession, city, name,con);
		}		
		System.out.println("total time taken : " + (System.currentTimeMillis() - startTime)/1000);
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
