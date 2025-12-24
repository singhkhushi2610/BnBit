package com.projects.BnBit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BnBitAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BnBitAppApplication.class, args);
	}

}
