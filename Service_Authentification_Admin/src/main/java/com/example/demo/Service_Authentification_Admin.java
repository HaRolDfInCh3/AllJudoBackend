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
public class Service_Authentification_Admin implements CommandLineRunner {
	@Value("${nom}")
	private String nom;
	AddRoleToUser artu;
	@Autowired
	CreateAdminPasswords cup;
	
	public static void main(String[] args) {
		SpringApplication.run(Service_Authentification_Admin.class, args);
	}
	public void run(String... args) throws Exception{
		//cup.regenerer();
		System.out.println(nom+"\nTous les rest controllers ont étés exposés ...");
		
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
