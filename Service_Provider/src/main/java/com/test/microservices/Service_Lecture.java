package com.test.microservices;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@EnableMongoRepositories
@SpringBootApplication
@EnableEurekaClient
public class Service_Lecture implements CommandLineRunner{

	
	public static void main(String[] args) {
		SpringApplication.run(Service_Lecture.class, args);
	}
	
public void run(String... args) throws Exception{

	System.out.println("Tous les rest controllers ont étés exposés ...");
	
    }


}
