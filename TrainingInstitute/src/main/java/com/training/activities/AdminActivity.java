package com.training.activities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.training.exceptions.InstituteNotFoundException;
import com.training.exceptions.StudentNotFoundException;

public class AdminActivity {
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

	public static void viewInstitute() {
		if (con == null) {
			creatValue();
		}
		System.out.println("Do you want to view Institute Details? y/n?");
		String input = scan.nextLine();
		if (input.equals("y")) {
			System.out.println("Enter Institute Name:");
			input = scan.nextLine();
			String sql = String.format("select * from Institute where Institute_name = '%s';", input);
			try {
				stm = con.createStatement();
				rs = stm.executeQuery(sql);
				if (rs.next() == true) {
					String name = rs.getString(1);
					String password = rs.getString(2);
					String date = rs.getString(3);
					String address = rs.getString(4);
					String seats = rs.getString(5);
					String course = rs.getString(6);
					System.out.println(name + " " + password + " " + date + " " + address + " " + seats + " " + course);
					System.out.println("Institute Viewed Successfully!");
				} else {
					throw new InstituteNotFoundException("Institution Not Found with Name: " + input);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (InstituteNotFoundException e) {
				System.err.println(e);
			}
		}
	}
	public static void deleteInstitute() {
		if (con == null) {
			creatValue();
		}
		System.out.println("Do you want to Delete a institute? y/n?");
		String input = scan.nextLine();
		if (input.equals("y")) {
			System.out.println("Enter Institute Name:");
			String insName = scan.nextLine();
			String sql = String.format("DELETE FROM Institute WHERE Institute_name = '%s';", insName);
			try {
				stm = con.createStatement();
				int isDeleted = stm.executeUpdate(sql);
				isDeleted++;
				if (isDeleted == 2) {
					System.out.println("Institute Deleted Successfully!");
				} else {
					throw new InstituteNotFoundException("Institution Not Found with Name: " + insName);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (InstituteNotFoundException e) {
				System.err.println(e);
			}
		}
	}
	public static void viewStudent() {
		if (con == null) {
			creatValue();
		}
		System.out.println("Do you want to view student details? y/n?");
		String input = scan.nextLine();
		if (input.equals("y")) {
			System.out.println("Enter Student ID:");
			input = scan.nextLine();
			String sql = String.format("select * from student where User_id = '%s';", input);
			try {
				stm = con.createStatement();
				rs = stm.executeQuery(sql);
				if (rs.next() == true) {
					String name = rs.getString(1);
					String password = rs.getString(2);
					String qualification = rs.getString(3);
					String contactNum = rs.getString(4);
					String address = rs.getString(5);
					String mail = rs.getString(6);
					int userId = rs.getInt(7);
					System.out.println(name + " " + password + " " + qualification + " " + contactNum + " " + address
							+ " " + mail + " " + userId);
					System.out.println("Student Viewed Successfully!");
				} else {
					throw new StudentNotFoundException("Student Not Found with ID: " + input);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (StudentNotFoundException e) {
				System.err.println(e);
			}
		}

	}
	public static void viewFeedback() {
		if (con == null) {
			creatValue();
		}
		System.out.println("Do you want to View feedback from Students? y/n?");
		String input = scan.nextLine();
		if (input.equals("y")) {
			System.out.println("Enter Student ID:");
			String stuID = scan.nextLine();
			String sql = String.format("select * from feedback where student_id = '%s';", stuID);
			try {
				stm = con.createStatement();
				rs = stm.executeQuery(sql);
				if (rs.next() == true) {
					String feedback = rs.getString(4);
					System.out.println("Feedback is: " + feedback);
					System.out.println("Feedback Viewed Successfully!");
				} else {
					throw new StudentNotFoundException("FeedBack Not Found from the student ID: " + stuID);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (StudentNotFoundException e) {
				System.err.println(e);
			}
		}
	}
}