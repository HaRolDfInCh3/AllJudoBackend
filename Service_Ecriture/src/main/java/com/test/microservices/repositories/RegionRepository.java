package com.test.microservices.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.test.microservices.pojos.Region;

public interface RegionRepository extends MongoRepository<Region, String> {
	public Region findById(int id2);
	public Boolean existsById(int id2);
	public Region findByIdMongo(String Id);
	public Boolean existsByIdMongo(String Id);
	public Region deleteById(int id2);
}
