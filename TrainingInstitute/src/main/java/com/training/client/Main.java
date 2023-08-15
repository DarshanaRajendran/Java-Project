package com.training.client;

import java.util.Scanner;

import com.training.login.AdminLogin;
import com.training.login.DefaultLogin;
import com.training.registration.DefaultRegistration;

public class Main {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		System.out.println("1.register");
		System.out.println("2.login");
		System.out.println("3.admin");
		System.out.println("4.exit");
		System.out.println("Enter your choice: ");
		int num = scan.nextInt();

		switch (num) {
		case 1:
			DefaultRegistration.defaultRegistration();
			break;

		case 2:
			DefaultLogin.defaultLogin();
			break;
			
		case 3:
			AdminLogin.adminLogin();

		case 4:
			System.out.println("Exit from main");
			return;
		}
	}
}
