package com.training.registration;

import java.util.Scanner;

public class DefaultRegistration {

	public static void defaultRegistration() {

		Scanner scan = new Scanner(System.in);
		System.out.println("Enter your input");
		System.out.println("1.Institute");
		System.out.println("2.student");
		System.out.println("3.exit");
		int input = scan.nextInt();

		switch (input) {
		case 1:
			System.out.println("Institute");
			InstituteRegistration.instituteRegistration();
			break;

		case 2:
			System.out.println("Student");
			StudentRegistration.studentRegistration();
			break;

		case 3:
			System.out.println("Exit from registration");
			return;
		}

	}

}
