package com.example.smart_paper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SmartPaperApplication {

	public static void main(String[] args) {
		System.out.println("http://localhost:8080");
		SpringApplication.run(SmartPaperApplication.class, args);
	}

}
