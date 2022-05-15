package com.test.microservices.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.test.microservices.pojos.Champion_popularite;
public interface Champion_populariteRepository extends MongoRepository<Champion_popularite, String> {
	public Champion_popularite findById(int id2);
	public Boolean existsById(int id2);
	public Champion_popularite findByIdMongo(String idMongo);
	public Boolean existsByIdMongo(String idMongo);
	public Champion_popularite deleteById(int id2);
}
