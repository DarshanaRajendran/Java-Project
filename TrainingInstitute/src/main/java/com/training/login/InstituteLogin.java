package com.training.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import com.training.activities.InstituteActivity;

public class InstituteLogin {

	public static void instituteLogin() {

		Scanner scan = new Scanner(System.in);

		System.out.println("Enter Institute Name: ");
		String insName = scan.next();
		scan.nextLine();

		System.out.println("Enter Institute Password: ");
		String insPswd = scan.next();

		String url = "jdbc:mysql://localhost:3306/traininginstitute";
		String un = "root";
		String pwd = "admin@123";

		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, un, pwd);
			String q = String.format("select * from institute where Institute_name = '%s' and password = '%s';",insName,insPswd);
			stm = con.createStatement();
			rs = stm.executeQuery(q);
			if (rs.next() == false) {
				System.out.println("Invalid username and password.");
				return;
			} else {
				System.out.println("Login successful!");
				System.out.println();
				System.out.println("Institute Activities");
				while(true) {
					System.out.println("Enter your choice:");
					System.out.println("view_student/upload_article/insert_faculty/update_institute/view_request/send_response/view_feedback/exit");
					String input = scan.next();
					switch(input) {
					case "view_student":
						InstituteActivity.handleStudent();
						break;
					case "update_institute":
						InstituteActivity.updateInstitute(insName, insPswd);
						break;
					case"view_request":
						InstituteActivity.viewRequest();
						break;
					case "send_response":
						InstituteActivity.sendResponse(insPswd);
						break;
					case "view_feedback":
						InstituteActivity.viewFeedback();
						break;
					case "insert_faculty":
						InstituteActivity.insertFaculty(insName);
						break;
					case "upload_article":
						InstituteActivity.uploadArticle(insName);
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
