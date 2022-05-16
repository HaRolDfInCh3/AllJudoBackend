package com.test.microservices.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.test.microservices.pojos.Club_admin_externe;

public interface Club_admin_externeRepository extends MongoRepository<Club_admin_externe, String> {
	public Club_admin_externe findById(int id2);
	public Boolean existsById(int id2);
	public Club_admin_externe findByIdMongo(String idMongo);
	public Boolean existsByIdMongo(String idMongo);
	public Club_admin_externe deleteById(int id2);
}
