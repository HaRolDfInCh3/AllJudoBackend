package com.test.microservices.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.test.microservices.pojos.Nationality;

public interface NationalityRepository extends MongoRepository<Nationality, String> {
	public Nationality findById(int id2);
	public Boolean existsById(int id2);
	public Nationality findByIdMongo(String Id);
	public Boolean existsByIdMongo(String Id);
	public Nationality deleteById(int id2);
}
