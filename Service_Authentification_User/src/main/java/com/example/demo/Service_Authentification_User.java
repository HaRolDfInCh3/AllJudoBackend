package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;




@EnableMongoRepositories
@EnableEurekaClient
@SpringBootApplication
public class Service_Authentification_User implements CommandLineRunner {
	@Value("${nom}")
	private String nom;
	AddRoleToUser artu;
	@Autowired
	CreateUserPasswords cup;
	
	public static void main(String[] args) {
		SpringApplication.run(Service_Authentification_User.class, args);
		
	}
	public void run(String... args) throws Exception{
		//cup.regenerer();
		
		
	    }
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//	 return (PasswordEncoder) NoOpPasswordEncoder.getInstance();
//	}
	
	

}
