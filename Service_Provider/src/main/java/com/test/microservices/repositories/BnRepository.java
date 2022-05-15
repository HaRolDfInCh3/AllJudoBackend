package com.test.microservices.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.test.microservices.pojos.Bannierenewsletter;

public interface BnRepository extends MongoRepository<Bannierenewsletter, String> {
	public Bannierenewsletter findById(int id2);
	public Boolean existsById(int id2);
	public Bannierenewsletter findByIdMongo(String idMongo);
	public Boolean existsByIdMongo(String idMongo);
	public Bannierenewsletter deleteById(int id2);
}
