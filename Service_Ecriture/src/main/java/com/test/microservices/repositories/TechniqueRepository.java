package com.test.microservices.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.test.microservices.pojos.Technique;

public interface TechniqueRepository extends MongoRepository<Technique, String> {
	public Technique findById(int id2);
	public Boolean existsById(int id2);
	public Technique findByIdMongo(String Id);
	public Boolean existsByIdMongo(String Id);
	public Technique deleteById(int id2);
}
