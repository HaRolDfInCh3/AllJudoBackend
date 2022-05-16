package com.test.microservices.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.test.microservices.pojos.Pari;

public interface PariRepository extends MongoRepository<Pari, String> {
	public Pari findById(int id2);
	public Boolean existsById(int id2);
	public Pari findByIdMongo(String Id);
	public Boolean existsByIdMongo(String Id);
	public Pari deleteById(int id2);
}
