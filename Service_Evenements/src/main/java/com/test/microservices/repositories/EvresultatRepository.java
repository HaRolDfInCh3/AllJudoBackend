package com.test.microservices.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.test.microservices.pojos.Evresultat;

public interface EvresultatRepository extends MongoRepository<Evresultat, String> {
	public Evresultat findById(int id2);
	public Boolean existsById(int id2);
	public Evresultat findByIdMongo(String idMongo);
	
	public Boolean existsByIdMongo(String idMongo);
	public Evresultat deleteById(int id2);
	public Boolean existsByEvenementID(int id2);
	public List<Evresultat> findAllByEvenementID(int id2);
	public List<Evresultat> findAllByChampionID(int id2);
	
}
