package com.test.microservices.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.test.microservices.pojos.Membre;

public interface MembreRepository extends MongoRepository<Membre, String> {
	public Membre findById(int id2);
	public Boolean existsById(int id2);
	public Membre findByIdMongo(String Id);
	public Boolean existsByIdMongo(String Id);
	public Membre deleteById(int id2);
}
