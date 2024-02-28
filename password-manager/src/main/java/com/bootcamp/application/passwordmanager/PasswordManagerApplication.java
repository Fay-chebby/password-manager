package com.bootcamp.application.passwordmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@CrossOrigin
public class PasswordManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PasswordManagerApplication.class, args);
	}
}
