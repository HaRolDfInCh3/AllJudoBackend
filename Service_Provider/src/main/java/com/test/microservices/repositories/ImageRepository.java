package com.test.microservices.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.test.microservices.pojos.Image;

public interface ImageRepository extends MongoRepository<Image, String> {
	public Image findById(int id2);
	public Boolean existsById(int id2);
	public Image findByIdMongo(String Id);
	public Boolean existsByIdMongo(String Id);
	public Image deleteById(int id2);
}
