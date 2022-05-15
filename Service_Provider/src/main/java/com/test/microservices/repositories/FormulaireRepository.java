package com.test.microservices.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.test.microservices.pojos.Formulaire;

public interface FormulaireRepository extends MongoRepository<Formulaire, String> {
	public Formulaire findById(int id2);
	public Boolean existsById(int id2);
	public Formulaire findByIdMongo(String idMongo);
	public Boolean existsByIdMongo(String idMongo);
	public Formulaire deleteById(int id2);
}
