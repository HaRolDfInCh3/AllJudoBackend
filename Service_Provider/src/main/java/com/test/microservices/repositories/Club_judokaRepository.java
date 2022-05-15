package com.test.microservices.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.test.microservices.pojos.Club_judoka;

public interface Club_judokaRepository extends MongoRepository<Club_judoka, String> {
	public Club_judoka findById(int id2);
	public Boolean existsById(int id2);
	public Club_judoka findByIdMongo(String idMongo);
	public Boolean existsByIdMongo(String idMongo);
	public Club_judoka deleteById(int id2);
}
