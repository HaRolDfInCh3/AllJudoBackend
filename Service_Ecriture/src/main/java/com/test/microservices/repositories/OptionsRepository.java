package com.test.microservices.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.test.microservices.pojos.Option;

public interface OptionsRepository extends MongoRepository<Option, String> {
	public Option findById(int id2);
	public Boolean existsById(int id2);
	public Option findByIdMongo(String Id);
	public Boolean existsByIdMongo(String Id);
	public Option deleteById(int id2);
}
