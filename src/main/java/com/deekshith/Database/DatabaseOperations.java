package com.deekshith.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.deekshith.initiator.DatabaseOpearionsInitiator;

public class DatabaseOperations {
	final static Logger logger = Logger.getLogger(DatabaseOperations.class);

	static int num;
	PreparedStatement st;
	ResultSet rs;

	private static Properties props = new Properties();
	public static ArrayList<String> al = new ArrayList<String>();

	public String obtainProfession(String city,Connection con) {
		logger.info("obtaining profession for "+city+" ");
		if (props.size() == 0) {
			
			try {
				st = con.prepareStatement("Select * from profession");
				rs = st.executeQuery();
				while (rs.next()) {
					props.put(rs.getString(2), rs.getString(3));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (st != null) {
						st.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		}

		if (props.get(city) != null) {
			return props.get(city).toString();
		} else {
			return "Profession not available for the city : " + city;
		}
	}

	public void addEmployee(String profession, String city, String name,Connection con) {

	
		try {
			st = con.prepareStatement("insert into empoyees(name,city,profession) values('" + name + "','" + city
					+ "','" + profession + "')");
			st.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null) {
					st.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}

	public String getRandomName() {
		num++;
		return "Deek" + num;
	}

	public String getRandomCity() {
		String cities[] = { "Bangalore", "Mumbai", "Kolkata", "Hyderabad", "Delhi", "Ranchi", "Patna", "Chennai",
				"Trivandrum", "Gandhinagar" };
		int randomnum = (int) (Math.random() * 10);
		return cities[randomnum];
	}

}
