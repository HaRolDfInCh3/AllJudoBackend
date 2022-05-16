package com.test.microservices.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.test.microservices.pojos.Liens2;

public interface Liens2Repository extends MongoRepository<Liens2, String> {
	public Liens2 findById(int id2);
	public Boolean existsById(int id2);
	public Liens2 findByIdMongo(String Id);
	public Boolean existsByIdMongo(String Id);
	public Liens2 deleteById(int id2);
}
