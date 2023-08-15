package com.training.login;

import java.sql.*;
import java.util.Scanner;

import com.training.activities.AdminActivity;

public class AdminLogin {

	public static void adminLogin() {
		Scanner scan = new Scanner(System.in);

		System.out.println("Enter Admin's User ID: ");
		String adminID = scan.next();

		System.out.println("Enter Admin's Password: ");
		String adminPswd = scan.next();

		String url = "jdbc:mysql://localhost:3306/traininginstitute";
		String un = "root";
		String pwd = "admin@123";

		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, un, pwd);
			String q = String.format("select * from admin where id= '%s' and password = '%s';", adminID, adminPswd);
			stm = con.createStatement();
			rs = stm.executeQuery(q);
			if (rs.next() == false) {
				System.out.println("Invalid userid and password.");
				return;
			} else {
				System.out.println("Admin Login successful!");
				System.out.println();
				System.out.println("Admin Activities");
				while (true) {
					System.out.println("Enter your choice: ");
					System.out.println("view_institute/delete_institute/view_student/view_feedback/exit");
					String input = scan.next();
					switch (input) {
					case "view_institute":
						AdminActivity.viewInstitute();;
						break;
					case "delete_institute":
						AdminActivity.deleteInstitute();
						break;
					case "view_student":
						AdminActivity.viewStudent();
						break;
					case "view_feedback":	
						AdminActivity.viewFeedback();
						break;
					case "exit":
						System.out.println("Exit From Institute Activities...");
						return;
					}
				}
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}