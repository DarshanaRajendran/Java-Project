package com.training.activities;

import java.util.Scanner;

import com.training.exceptions.InstituteNotFoundException;
import com.training.exceptions.InstutionExistException;
import com.training.exceptions.StudentNotFoundException;

import java.sql.*;

public class StudentActivity {
	static Scanner scan = new Scanner(System.in);
	static String url = "jdbc:mysql://localhost:3306/traininginstitute";
	static String un = "root";
	static String pwd = "admin@123";

	static Connection con = null;
	static Statement stm = null;
	static ResultSet rs = null;

	private static void creatValue() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, un, pwd);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void updateProfile(String User_Id, String Password) {
		if (con == null) {
			creatValue();
		}
		System.out.println("Do you want to update profile? y/n?");
		String input1 = scan.nextLine();
		
		if (input1.equals("y")) {
			System.out.println("Do you want to change qualification? y/n?");
			String input = scan.nextLine();
			if (input.equals("y")) {
				System.out.println("Enter new qualification");
				input = scan.nextLine();
				String sql = String.format(
						"update student set Qualification = '%s' where User_id = '%s' and password = '%s';", input,
						User_Id, Password);
				try {
					stm = con.createStatement();
					stm.executeUpdate(sql);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		if (input1.equals("y")) {
			System.out.println("Do you want to change contact number? y/n?");
			String input = scan.nextLine();
			if (input.equals("y")) {
				System.out.println("Enter new contact number");
				input = scan.nextLine();
				String sql = String.format(
						"update student set contact_number = '%s' where User_id = '%s' and password = '%s';", input,
						User_Id, Password);
				try {
					stm = con.createStatement();
					stm.executeUpdate(sql);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		if (input1.equals("y")) {
			System.out.println("Do you want to change Address? y/n?");
			String input = scan.nextLine();
			if (input.equals("y")) {
				System.out.println("Enter new Address");
				input = scan.nextLine();
				String sql = String.format(
						"update student set Address = '%s' where User_id = '%s' and password = '%s';", input, User_Id,
						Password);
				try {
					stm = con.createStatement();
					stm.executeUpdate(sql);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		if (input1.equals("y")) {
			System.out.println("Do you want to change Mail ID? y/n?");
			String input = scan.nextLine();
			if (input.equals("y")) {
				System.out.println("Enter new Mail ID");
				input = scan.nextLine();
				String sql = String.format(
						"update student set email_id = '%s' where User_id = '%s' and password = '%s';", input, User_Id,
						Password);
				try {
					stm = con.createStatement();
					stm.executeUpdate(sql);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		if (input1.equals("y")) {
			System.out.println("Do you want to change name? y/n?");
			String input = scan.nextLine();
			if (input.equals("y")) {
				System.out.println("Enter new user name");
				input = scan.nextLine();
				String sql = String.format("update student set Name = '%s' where User_id = '%s' and password = '%s';",
						input, User_Id, Password);
				try {
					stm = con.createStatement();
					stm.executeUpdate(sql);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		if (input1.equals("y")) {
			System.out.println("Do you want to change password? y/n?");
			String input = scan.nextLine();
			if (input.equals("y")) {
				System.out.println("Enter new password");
				input = scan.nextLine();
				String sql = String.format(
						"update student set password = '%s' where User_id = '%s' and password = '%s';", input, User_Id,
						Password);
				try {
					stm = con.createStatement();
					stm.executeUpdate(sql);
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("profile Updated");
	}

	public static void sendRequest(String stuID) {
		if (con == null) {
			creatValue();
		}
		System.out.println("Do you want to send request to college? y/n?");
		String input = scan.nextLine();
		if (input.equals("y")) {
			System.out.println("Enter Institute Name:");
			String insName = scan.nextLine();
			System.out.println("Enter request to be sent: ");
			input = scan.nextLine();
			String sql = String.format(
					"insert into request(student_id,instution_name,request_stmt) values('%s','%s','%s');", stuID,
					insName, input);
			try {
				stm = con.createStatement();
				stm.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Request Sent Successfully!");
	}
	public static void viewResponse() {
		if (con == null) {
			creatValue();
		}
		System.out.println("Do you want to View response from College? y/n?");
		String input = scan.nextLine();
		if (input.equals("y")) {
			System.out.println("Enter Student ID:");
			String stuID = scan.nextLine();
			String sql = String.format("select * from response where student_id = '%s';", stuID);
			try {
				stm = con.createStatement();
				rs = stm.executeQuery(sql);
				if (rs.next() == true) {
					String response = rs.getString(4);
					System.out.println("Response is: " + response);
				} else {
					throw new StudentNotFoundException(stuID);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} catch (StudentNotFoundException e) {
				System.err.println(e);
			}
		}
		System.out.println("Response Viewed Successfully!");
	}

	public static void sendFeedback(String stuID) {
		if (con == null) {
			creatValue();
		}
		System.out.println("Do you want to send Feedback? y/n?");
		String input = scan.nextLine();
		if (input.equals("y")) {
			System.out.println("Enter Institute Name:");
			String insName = scan.nextLine();
			System.out.println("Enter Feedback to be sent: ");
			String inputs = scan.nextLine();
			String sql = String.format(
					"insert into feedback(feedback_stmt,student_id,institute_name) values('%s','%s','%s');", inputs, stuID,
					insName);
			try {
				stm = con.createStatement();
				stm.executeUpdate(sql);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Feedback Sent Successfully!");
	}
}