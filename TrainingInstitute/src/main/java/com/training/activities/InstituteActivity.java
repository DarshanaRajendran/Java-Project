package com.training.activities;

import java.util.Scanner;

import com.training.exceptions.InstutionExistException;
import com.training.exceptions.StudentNotFoundException;

import java.sql.*;
import com.training.registration.StudentRegistration;

public class InstituteActivity {
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
	public static void handleStudent() {
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
					System.out.println(name + ", " + password + " ," + qualification + " ," + contactNum + ", " + address
							+ ", " + mail + ", " + userId);
					System.out.println("Student Details Viewed!");
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
	public static void uploadArticle(String insName) {
		if (con == null) {
			creatValue();
		}
		System.out.println("Do you want to Upload Article? y/n?");
		String input = scan.nextLine();
		if (input.equals("y")) {
			System.out.println("Enter Institute Name:");
			insName = scan.nextLine();
			System.out.println("Enter the article to be uploaded: ");
			String article = scan.nextLine();
			String sql = String.format("insert into upload_article(institute_name,article) values('%s','%s');", insName,
					article);
			try {
				stm = con.createStatement();
				stm.executeUpdate(sql);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		System.out.println("Article Uploaded");
	}

	public static void insertFaculty(String insName) {
		if (con == null) {
			creatValue();
		}
		System.out.println("Do you want to Insert Faculty? y/n?");
		String input = scan.nextLine();
		if (input.equals("y")) {
			System.out.println("Enter Institute Name:");
			insName = scan.nextLine();
			System.out.println("Enter Faculty Details: ");
			System.out.println("Enter Name of the Faculty: ");
			String name = scan.nextLine();
			System.out.println("Enter Qualification of the Faculty: ");
			String qualification = scan.nextLine();
			System.out.println("Enter contact number of the Faculty: ");
			String phn_num = scan.nextLine();
			System.out.println("Enter Address of the Faculty: ");
			String address = scan.nextLine();
			System.out.println("Enter Institution Name: ");
			String iname = scan.nextLine();
			String sql = String.format(
					"insert into faculty(name,qualification,contact_number,address,institution_name) values('%s','%s','%s','%s','%s');",
					name, qualification, phn_num, address, iname);

			try {
				stm = con.createStatement();
				stm.executeUpdate(sql);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		System.out.println("Faculty Data Inserted Successfully!!");
	}

	public static void updateInstitute(String insName, String insPswd) {
		if (con == null) {
			creatValue();
		}
		System.out.println("Do you want to update institute profile? y/n?");
		String input1 = scan.nextLine();
		
		if (input1.equals("y")) {
			System.out.println("Do you want to update Affiliation Date? y/n?");
			String input = scan.nextLine();
			if (input.equals("y")) {
				System.out.println("Enter new Affiliation Date:");
				input = scan.nextLine();
				String sql = String.format(
						"update institute set Affiliation_Date = '%s' where Institute_name = '%s' and password = '%s';",
						input, insName, insPswd);
				try {
					stm = con.createStatement();
					stm.executeUpdate(sql);

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		if (input1.equals("y")) {
			System.out.println("Do you want to update Address? y/n?");
			String input = scan.nextLine();
			if (input.equals("y")) {
				System.out.println("Enter new Address:");
				input = scan.nextLine();
				String sql = String.format(
						"update institute set Address = '%s' where Institute_name = '%s' and password = '%s';",
						input, insName, insPswd);
				try {
					stm = con.createStatement();
					stm.executeUpdate(sql);

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		if (input1.equals("y")) {
			System.out.println("Do you want to update Number of seats? y/n?");
			String input = scan.nextLine();
			if (input.equals("y")) {
				System.out.println("Enter new Number of seats:");
				input = scan.nextLine();
				String sql = String.format(
						"update institute set Number_of_seats = '%s' where Institute_name = '%s' and password = '%s';",
						input, insName, insPswd);
				try {
					stm = con.createStatement();
					stm.executeUpdate(sql);

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		if (input1.equals("y")) {
			System.out.println("Do you want to update number of course? y/n?");
			String input = scan.nextLine();
			if (input.equals("y")) {
				System.out.println("Enter new number of course:");
				input = scan.nextLine();
				String sql = String.format(
						"update institute set number_of_course = '%s' where Institute_name = '%s' and password = '%s';",
						input, insName, insPswd);
				try {
					stm = con.createStatement();
					stm.executeUpdate(sql);

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		if (input1.equals("y")) {
			System.out.println("Do you want to update Institute name? y/n?");
			String input = scan.nextLine();
			if (input.equals("y")) {
				System.out.println("Enter new Institute Name:");
				input = scan.nextLine();
				String sql1 = String.format("select Institute_name from institute where Institute_name = '%s';", input);
				try {
					stm = con.createStatement();
					rs = stm.executeQuery(sql1);
					if (rs.next()) {
						throw new InstutionExistException("Institute Already Exist " + input);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (InstutionExistException e) {
					System.err.println(e);
				}
				String sql = String.format(
						"update institute set Institute_name = '%s' where Institute_name = '%s' and password = '%s';",
						input, insName, insPswd);
				try {
					stm = con.createStatement();
					stm.executeUpdate(sql);

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		if (input1.equals("y")) {
			System.out.println("Do you want to update Password? y/n?");
			String input = scan.nextLine();
			if (input.equals("y")) {
				System.out.println("Enter new Password:");
				input = scan.nextLine();
				String sql = String.format(
						"update institute set password = '%s' where Institute_name = '%s' and password = '%s';", input,
						insName, insPswd);
				try {
					stm = con.createStatement();
					stm.executeUpdate(sql);

				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("Institute updates successfully!");
	}

	public static void viewRequest() {
		if (con == null) {
			creatValue();
		}
		System.out.println("Do you want to View request from Students? y/n?");
		String input = scan.nextLine();
		if (input.equals("y")) {
			System.out.println("Enter Student ID:");
			String stuID = scan.nextLine();
			String sql = String.format("select * from request where student_id = '%s';", stuID);

			try {
				stm = con.createStatement();
				rs = stm.executeQuery(sql);
				if (rs.next() == true) {
					String request = rs.getString(4);
					System.out.println("Request is: " + request);
					System.out.println("Request Viewed Successfully!");
				} else {
					throw new StudentNotFoundException("Student Not Found with ID: " + stuID);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} catch (StudentNotFoundException e) {
				System.err.println(e);
			}
		}

	}

	public static void sendResponse(String stuID) {
		if (con == null) {
			creatValue();
		}
		System.out.println("Do you want to send response to student? y/n?");
		String input = scan.nextLine();
		if (input.equals("y")) {
			System.out.println("Enter Student ID:");
			stuID = scan.nextLine();
			System.out.println("Enter Institute Name:");
			String insName = scan.nextLine();
			System.out.println("Enter response to be sent: ");
			input = scan.nextLine();
			String sql = String.format("insert into response(student_id,response_stmt,instution_name) values('%s','%s','%s');", stuID,input,insName);
			try {
				stm = con.createStatement();
				stm.executeUpdate(sql);

			}

			catch (SQLException e) {
				e.printStackTrace();
			}

		}
		System.out.println("Response Sent Successfully!");

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
					throw new StudentNotFoundException("Student Not Found with ID: " + stuID);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} catch (StudentNotFoundException e) {
				System.err.println(e);
			}
		}

	}

}
