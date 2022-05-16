package com.test.microservices.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.test.microservices.pojos.Bannieremobile;

public interface BmRepository extends MongoRepository<Bannieremobile, String>{
	public Bannieremobile findById(int id2);
	public Boolean existsById(int id2);
	public Bannieremobile findByIdMongo(String idMongo);
	public Boolean existsByIdMongo(String idMongo);
	public Bannieremobile deleteById(int id2);
}
