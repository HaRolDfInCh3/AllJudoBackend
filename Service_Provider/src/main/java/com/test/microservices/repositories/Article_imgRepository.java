package com.test.microservices.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.test.microservices.pojos.Article_img;

public interface Article_imgRepository extends MongoRepository<Article_img, String> {
	public Article_img findById(int id2);
	public Boolean existsById(int id2);
	public Article_img findByIdMongo(String idMongo);
	public Boolean existsByIdMongo(String idMongo);
	public Article_img deleteById(int id2);
}
