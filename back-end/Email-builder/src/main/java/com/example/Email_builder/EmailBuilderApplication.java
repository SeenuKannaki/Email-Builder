package com.example.Email_builder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class EmailBuilderApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure().directory("C:/Users/seenu/Downloads/Email-builder/Email-builder/.env").load();
		System.setProperty("DB_URL", dotenv.get("DB_URL"));
        System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
        System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
		SpringApplication.run(EmailBuilderApplication.class, args);
	}

}
