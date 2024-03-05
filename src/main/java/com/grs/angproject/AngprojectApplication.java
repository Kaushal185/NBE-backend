package com.grs.angproject;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.batch.BatchProperties.Jdbc;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
public class AngprojectApplication implements CommandLineRunner {
	private final JdbcTemplate jdbcTemplate;

	public AngprojectApplication(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public static void main(String[] args) {
		SpringApplication.run(AngprojectApplication.class, args);
	}

	@Override
	public void run(String... args) {
		try {

			String result = jdbcTemplate.queryForObject("SELECT 'Database is connected' FROM DUAL", String.class);
			System.out.println("Result from database: " + result);

		} catch (Exception e) {

			System.err.println("Error connecting to the Database" + e.getMessage());

		}
	}

}
