package com.test.microservices.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.test.microservices.pojos.Champion_admin_externe_journal;

public interface ChampionAdminExterneJournalRepository extends MongoRepository<Champion_admin_externe_journal, String> {
	public Champion_admin_externe_journal findById(int id2);
	public Boolean existsById(int id2);
	public Champion_admin_externe_journal findByIdMongo(String idMongo);
	public Boolean existsByIdMongo(String idMongo);
	public Champion_admin_externe_journal deleteById(int id2);
}
