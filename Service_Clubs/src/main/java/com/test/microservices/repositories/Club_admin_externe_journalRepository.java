package com.test.microservices.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.test.microservices.pojos.Club_admin_externe_journal;


public interface Club_admin_externe_journalRepository extends MongoRepository<Club_admin_externe_journal, String> {
	public Club_admin_externe_journal findById(int id2);
	public Boolean existsById(int id2);
	public Club_admin_externe_journal findByIdMongo(String idMongo);
	public Boolean existsByIdMongo(String idMongo);
	public Club_admin_externe_journal deleteById(int id2);
}
