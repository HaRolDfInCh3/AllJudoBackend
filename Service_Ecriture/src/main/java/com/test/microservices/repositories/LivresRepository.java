package com.test.microservices.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.test.microservices.pojos.Livre;

public interface LivresRepository extends MongoRepository<Livre, String> {
	public Livre findById(int id2);
	public Boolean existsById(int id2);
	public Livre findByIdMongo(String Id);
	public Boolean existsByIdMongo(String Id);
	public Livre deleteById(int id2);
}
