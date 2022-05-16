package com.test.microservices.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.test.microservices.pojos.Partenaire;

public interface PartenairesRepository extends MongoRepository<Partenaire, String> {
	public Partenaire findById(int id2);
	public Boolean existsById(int id2);
	public Partenaire findByIdMongo(String Id);
	public Boolean existsByIdMongo(String Id);
	public Partenaire deleteById(int id2);
}
