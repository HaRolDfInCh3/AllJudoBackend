package com.test.microservices.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.test.microservices.pojos.Bannierebackground;

public interface BbRepository extends MongoRepository<Bannierebackground, String> {
	public Bannierebackground findById(int id2);
	public Boolean existsById(int id2);
	public Bannierebackground findByIdMongo(String idMongo);
	public Boolean existsByIdMongo(String idMongo);
	public Bannierebackground deleteById(int id2);
}
