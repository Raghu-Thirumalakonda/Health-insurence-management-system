package com.vendor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CareCoverApplication {

	public static void main(String[] args) {
		// Render provides PostgreSQL URLs as postgresql://; JDBC drivers expect jdbc:postgresql://.
		String databaseUrl = System.getenv("DB_URL");
		if (databaseUrl != null && databaseUrl.startsWith("postgresql://")) {
			System.setProperty("spring.datasource.url", "jdbc:" + databaseUrl);
		}
		SpringApplication.run(CareCoverApplication.class, args);
	}

}
