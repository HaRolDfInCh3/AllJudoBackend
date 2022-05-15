package com.test.microservices.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.test.microservices.pojos.Sous_categorie;

public interface Sous_categorieRepository extends MongoRepository<Sous_categorie, String> {
	public Sous_categorie findById(int id2);
	public Boolean existsById(int id2);
	public Sous_categorie findByIdMongo(String Id);
	public Boolean existsByIdMongo(String Id);
	public Sous_categorie deleteById(int id2);
}
