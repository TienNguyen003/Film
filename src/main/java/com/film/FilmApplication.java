package com.film;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class FilmApplication {

	public static void main(String[] args) throws IOException {
		
		SpringApplication.run(FilmApplication.class, args);
	}

}
