package com.test.microservices.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.test.microservices.pojos.Categorie;

public interface CategorieRepository extends MongoRepository<Categorie, String> {
	public Categorie findById(int id2);
	public Boolean existsById(int id2);
	public Categorie findByIdMongo(String idMongo);
	public Boolean existsByIdMongo(String idMongo);
	public Categorie deleteById(int id2);
}
