package com.training.registration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.training.exceptions.InstutionExistException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class RegisterIns {
	private String iName;
	private String iPswd;
	Date thisDate = new Date();
	SimpleDateFormat dateForm = new SimpleDateFormat("yyyy/mm/dd");
	private String affDate = dateForm.format(thisDate);
	private String iAddress;
	private String noOfSeats;
	private String noOfCourse;

	public String getiName() {
		return iName;
	}

	public void setiName(String iName) {
		this.iName = iName;
	}

	public String getiPswd() {
		return iPswd;
	}

	public void setiPswd(String iPswd) {
		this.iPswd = iPswd;
	}

	public String getAffDate() {
		return affDate;
	}

	public void setAffDate(String affDate) {
		this.affDate = affDate;
	}

	public String getiAddress() {
		return iAddress;
	}

	public void setiAddress(String iAddress) {
		this.iAddress = iAddress;
	}

	public String getNoOfSeats() {
		return noOfSeats;
	}

	public void setNoOfSeats(String noOfSeats) {
		this.noOfSeats = noOfSeats;
	}

	public String getNoOfCourse() {
		return noOfCourse;
	}

	public void setNoOfCourse(String noOfCourse) {
		this.noOfCourse = noOfCourse;
	}
}

public class InstituteRegistration {
	static Connection con = null;
	static Statement stm = null;
	static ResultSet rs = null;

	public static void register(RegisterIns regIns) {
		String DB_URL = "jdbc:mysql://localhost:3306/traininginstitute";
		String USER = "root";
		String PASS = "admin@123";
		
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stmt = conn.createStatement();) {
			String sql = String.format("insert into traininginstitute.institute(Institute_name,password,Affiliation_Date,Address,Number_of_seats,number_of_course) values('%s','%s','%s','%s','%s','%s')", regIns.getiName(),regIns.getiPswd(),regIns.getAffDate(),regIns.getiAddress(),regIns.getNoOfSeats(),regIns.getNoOfCourse());
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	public static void instituteRegistration() {
		RegisterIns regIns = new RegisterIns();
		Scanner scan = new Scanner(System.in);

		System.out.println("Enter Institution Name: ");
		String iName = scan.next();
		String DB_URL = "jdbc:mysql://localhost:3306/traininginstitute";
		String USER = "root";
		String PASS = "admin@123";
		try {
			con = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		String sql1 = String.format("select Institute_name from institute where Institute_name = '%s';", iName);
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(sql1);
			if(rs.next()) {
				throw new InstutionExistException("Institute Already Exist with: " + iName);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(InstutionExistException e) {
			System.err.println(e);
		}
		regIns.setiName(iName);

		System.out.println("Enter Institution Password: ");
		scan.nextLine();
		String iPswd = scan.next();
		regIns.setiPswd(iPswd);

		System.out.println("Enter the Date of Affiliation: ");
		String affDate = scan.next();
		regIns.setAffDate(affDate);

		System.out.println("Enter the Institution Address: ");
		scan.nextLine();
		String iAddress = scan.next();
		regIns.setiAddress(iAddress);
		scan.nextLine();

		System.out.println("Enter the Number of Seats available: ");
		String noOfSeats = scan.next();
		regIns.setNoOfSeats(noOfSeats);

		System.out.println("Enter the Number of Courses available: ");
		String noOfCourse = scan.next();
		regIns.setNoOfCourse(noOfCourse);
		
		register(regIns);
		System.out.println("Registration Successful!!");

	}

}