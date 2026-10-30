package com.vendor;

import java.net.URI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CareCoverApplication {

    public static void main(String[] args) {
        String databaseUrl = System.getenv("DB_URL");

        if (databaseUrl != null
                && (databaseUrl.startsWith("postgresql://")
                    || databaseUrl.startsWith("postgres://"))) {
            URI uri = URI.create(databaseUrl);

            if (uri.getHost() == null || uri.getRawPath() == null) {
                throw new IllegalStateException(
                        "DB_URL must be a valid PostgreSQL connection URL.");
            }

            String jdbcUrl = "jdbc:postgresql://" + uri.getHost()
                    + (uri.getPort() == -1 ? "" : ":" + uri.getPort())
                    + uri.getRawPath()
                    + (uri.getRawQuery() == null ? "" : "?" + uri.getRawQuery());

            System.setProperty("spring.datasource.url", jdbcUrl);
        }

        SpringApplication.run(CareCoverApplication.class, args);
    }
}