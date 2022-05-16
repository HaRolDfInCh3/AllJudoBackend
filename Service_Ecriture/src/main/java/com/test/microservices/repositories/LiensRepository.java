package com.test.microservices.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.test.microservices.pojos.Liens;

public interface LiensRepository extends MongoRepository<Liens, String> {
	public Liens findById(int id2);
	public Boolean existsById(int id2);
	public Liens findByIdMongo(String Id);
	public Boolean existsByIdMongo(String Id);
	public Liens deleteById(int id2);
}
