package com.test.microservices.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.test.microservices.pojos.Club_horaire;

public interface Club_horaireRepository extends MongoRepository<Club_horaire, String> {
	public Club_horaire findById(int id2);
	public Boolean existsById(int id2);
	public Club_horaire findByIdMongo(String idMongo);
	public Boolean existsByIdMongo(String idMongo);
	public Club_horaire deleteById(int id2);
}
