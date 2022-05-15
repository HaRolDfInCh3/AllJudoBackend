package com.test.microservices.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.test.microservices.pojos.Entrainement;

public interface EntrainementRepository extends MongoRepository<Entrainement, String> {
	public Entrainement findById(int id2);
	public Boolean existsById(int id2);
	public Entrainement findByIdMongo(String idMongo);
	public Boolean existsByIdMongo(String idMongo);
	public Entrainement deleteById(int id2);
}
