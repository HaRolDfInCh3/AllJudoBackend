package com.test.microservices.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.test.microservices.pojos.Lienscategorie;

public interface LienscategorieRepository extends MongoRepository<Lienscategorie, String> {
	public Lienscategorie findById(int id2);
	public Boolean existsById(int id2);
	public Lienscategorie findByIdMongo(String Id);
	public Boolean existsByIdMongo(String Id);
	public Lienscategorie deleteById(int id2);
}
