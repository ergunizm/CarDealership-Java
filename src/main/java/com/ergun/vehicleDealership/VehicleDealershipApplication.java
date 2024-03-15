package com.ergun.vehicleDealership;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class VehicleDealershipApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehicleDealershipApplication.class, args);
	}

}
