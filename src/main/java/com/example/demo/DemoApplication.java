package com.example.demo;

import com.example.demo.util.ConsoleHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		ConsoleHelper.printGreetings();
		scanConsole();
	}

	public static void scanConsole() {
		Scanner scanner = new Scanner(System.in);
		for (String text;!(text = scanner.nextLine()).equals("close");) {}
		System.out.println("Server closed");
		System.exit(0);
	}

}
