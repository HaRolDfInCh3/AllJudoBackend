package com.test.microservices.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.test.microservices.pojos.Champion_admin_externe_palmares;

public interface ChampionAdminExternePalmaresRepository extends MongoRepository<Champion_admin_externe_palmares, String> {
	public Champion_admin_externe_palmares findById(int id2);
	public Boolean existsById(int id2);
	public Champion_admin_externe_palmares findByIdMongo(String idMongo);
	public Boolean existsByIdMongo(String idMongo);
	public Champion_admin_externe_palmares deleteById(int id2);
}
