package com.test.microservices.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.test.microservices.pojos.Annonce;

public interface AnnonceRepository extends MongoRepository<Annonce, String> {
	public Annonce findById(int id2);
	public Boolean existsById(int id2);
	public Annonce findByIdMongo(String idMongo);
	public Boolean existsByIdMongo(String idMongo);
	public Annonce deleteById(int id2);
}
