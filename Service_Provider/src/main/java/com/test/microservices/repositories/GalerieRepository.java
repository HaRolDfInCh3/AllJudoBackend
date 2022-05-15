package com.test.microservices.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.test.microservices.pojos.Galerie;

public interface GalerieRepository extends MongoRepository<Galerie, String> {
	public Galerie findById(int id2);
	public Boolean existsById(int id2);
	public Galerie findByIdMongo(String idMongo);
	public Boolean existsByIdMongo(String idMongo);
	public Galerie deleteById(int id2);
}
