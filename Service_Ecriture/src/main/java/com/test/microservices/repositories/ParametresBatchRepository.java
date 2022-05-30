package com.test.microservices.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.test.microservices.pojos.ParametresBatch;

public interface ParametresBatchRepository extends MongoRepository<ParametresBatch, String>{
	public ParametresBatch findById(int id2);
	public List<ParametresBatch> findByActif(boolean b);
	public Boolean existsById(int id2);
	public ParametresBatch findByIdMongo(String idMongo);
	public Boolean existsByIdMongo(String idMongo);
	public ParametresBatch deleteById(int id2);
}
