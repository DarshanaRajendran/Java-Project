package com.training.login;

import java.util.Scanner;

public class DefaultLogin {
	public static void defaultLogin() {
		Scanner scan = new Scanner(System.in);

		while (true) {
			System.out.println("Enter your input:");
			System.out.println("1.admin");
			System.out.println("2.institute");
			System.out.println("3.student");
			System.out.println("4.exit");
			int input = scan.nextInt();

			switch (input) {
			case 1:
				System.out.println("Admin");
				AdminLogin.adminLogin();
				break;

			case 2:
				System.out.println("Institute");
				InstituteLogin.instituteLogin();
				break;

			case 3:
				System.out.println("Student");
				StudentLogin.studentLogin();
				break;

			case 4:
				System.out.println("Exit from login");
				return;
			}
		}

	}

}
