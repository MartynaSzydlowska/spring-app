package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Locale;

@SpringBootApplication
public class BookingsApplication {

	public static void main(String[] args) {
		Locale.setDefault(Locale.ENGLISH);
		SpringApplication.run(BookingsApplication.class, args);
	}

}
