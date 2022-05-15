package com.example.ApiDoublons;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import com.example.ApiDoublons.entities.Champion;
import com.example.ApiDoublons.repositories.ChampionRepository;
import com.example.ApiDoublons.services.Singleton;


@SpringBootApplication
@EnableEurekaClient
public class ApiDoublonsApplication implements CommandLineRunner{
	@Autowired
	ChampionRepository repo;
	public static void main(String[] args) {
		SpringApplication.run(ApiDoublonsApplication.class, args);
	}
 
	@Override
	public void run(String... args) throws Exception {
		List<Champion>liste=repo.findAll();
		Singleton.getInstance().setLc(liste);
		System.out.println("liste des champions recuperée !");
		//http://localhost:1000/API-DOUBLONS/get_Similars/10549
		
	}

}
