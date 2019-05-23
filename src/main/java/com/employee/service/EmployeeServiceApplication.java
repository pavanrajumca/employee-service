package com.employee.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class EmployeeServiceApplication {
	public static void main(final String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class);
	}
}
