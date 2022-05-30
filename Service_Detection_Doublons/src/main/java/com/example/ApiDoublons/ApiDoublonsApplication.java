package com.example.ApiDoublons;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	@Value("${blocsize}")
	private int blocsize;
	@Autowired
	ChampionRepository repo;
	public static void main(String[] args) {
		SpringApplication.run(ApiDoublonsApplication.class, args);
	}
 
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Demarrage avec une taille de bloc de "+blocsize+" !");
		List<Champion>liste=repo.findAll();
		Singleton.getInstance().setLc(liste);
		Singleton.getInstance().setBlocsize(blocsize);
		System.out.println("liste des "+liste.size()+" champions recuperée !");
		//StringSimilarity.printSimilarity("Ayrton Gonsallo", "Ayrton Gonzalo");
		
	}

}
