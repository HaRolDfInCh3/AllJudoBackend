package com.test.microservices.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.test.microservices.pojos.Technique_specialiste;

public interface Technique_specialisteRepository extends MongoRepository<Technique_specialiste, String> {
	public Technique_specialiste findById(int id2);
	public Boolean existsById(int id2);
	public Technique_specialiste findByIdMongo(String Id);
	public Boolean existsByIdMongo(String Id);
	public Technique_specialiste deleteById(int id2);
}
