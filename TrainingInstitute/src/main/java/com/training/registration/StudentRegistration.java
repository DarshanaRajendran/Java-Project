package com.training.registration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

class Register {
	private String name;
	private String password;
	private String qualification;
	private String phnNum;
	private String address;
	private String emailId;
	private String userId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getPhnNum() {
		return phnNum;
	}

	public void setPhnNum(String phnNum) {
		this.phnNum = phnNum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
public class StudentRegistration {

	public static void registerStudent(Register register) {
		String DB_URL = "jdbc:mysql://localhost:3306/traininginstitute";
		String USER = "root";
		String PASS = "admin@123";
		
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stmt = conn.createStatement();) {
			// Execute a query
			String sql = String.format("insert into traininginstitute.student values('%s','%s','%s','%s','%s','%s','%s')", register.getName(),register.getPassword(),register.getQualification(),register.getPhnNum(),register.getAddress(),register.getEmailId(),register.getUserId());
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void studentRegistration() {
		Register register = new Register();
		Scanner scan = new Scanner(System.in);

		System.out.println("Enter Name: ");
		String name = scan.next();
		register.setName(name);

		System.out.println("Enter Password: ");
		String password = scan.next();
		register.setPassword(password);

		System.out.println("Enter Qualification: ");
		String qualification = scan.next();
		register.setQualification(qualification);

		System.out.println("Enter Phone Number: ");
		String phnNum = scan.next();
		register.setPhnNum(phnNum);

		System.out.println("Enter Address: ");
		scan.nextLine();
		String address = scan.nextLine();
		register.setAddress(address);

		System.out.println("Enter Email ID: ");
		String emailId = scan.next();
		register.setEmailId(emailId);

		System.out.println("Enter User ID: ");
		String userId = scan.next();
		register.setUserId(userId);
		
		registerStudent(register);

		System.out.println("Registration Successful!!");

	}
}
