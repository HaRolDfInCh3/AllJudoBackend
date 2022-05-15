package com.test.microservices.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.test.microservices.pojos.Article;

public interface ArticleRepository extends MongoRepository<Article, String>{
	public Article findById(int id2);
	public Boolean existsById(int id2);
	public Article findByIdMongo(String idMongo);
	public Boolean existsByIdMongo(String idMongo);
	public Article deleteById(int id2);
}
