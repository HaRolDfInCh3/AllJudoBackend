package com.test.microservices.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.test.microservices.pojos.Champion_admin_externe;

public interface ChampionAdminExterneRepository extends MongoRepository<Champion_admin_externe, String> {
	public Champion_admin_externe findById(int id2);
	public Boolean existsById(int id2);
	public Champion_admin_externe findByIdMongo(String idMongo);
	public Boolean existsByIdMongo(String idMongo);
	public Champion_admin_externe deleteById(int id2);
}
