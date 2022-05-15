package com.test.microservices.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.test.microservices.pojos.Video;

public interface VideoRepository extends MongoRepository<Video, String> {
	public Video findById(int id2);
	public Boolean existsById(int id2);
	public Video findByIdMongo(String Id);
	public Boolean existsByIdMongo(String Id);
	public Video deleteById(int id2);
}
