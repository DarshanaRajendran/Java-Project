package com.training.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.training.activities.StudentActivity;
public class StudentLogin {

	public static void studentLogin() {
		Scanner scan = new Scanner(System.in);

		System.out.println("Enter Student's User ID: ");
		String userID = scan.next();

		System.out.println("Enter Student's Password: ");
		String stuPswd = scan.next();

		String url = "jdbc:mysql://localhost:3306/traininginstitute";
		String un = "root";
		String pwd = "admin@123";

		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, un, pwd);
			String q = String.format("select * from student where User_id= '%s' and password = '%s';", userID, stuPswd);
			stm = con.createStatement();
			rs = stm.executeQuery(q);
			if (rs.next() == false) {
				System.out.println("Invalid userid and password.");
				return;
			} else {
				System.out.println("Login successful!");
				System.out.println();
				System.out.println("Student Activities");
				while (true) {
					System.out.println("Enter your choice: ");
					System.out.println("update_profile/send_request/view_response/send_feedback/exit");
					String input = scan.next();
					switch (input) {
					case "update_profile":
						StudentActivity.updateProfile(userID, stuPswd);
						break;
					case "send_request":
						StudentActivity.sendRequest(userID);
						break;
					case "view_response":
						StudentActivity.viewResponse();
						break;
					case "send_feedback":
						StudentActivity.sendFeedback(userID);
						break;
					case "exit":
						System.out.println("Exit From Student Activities...");
						return;
					}
				}
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
