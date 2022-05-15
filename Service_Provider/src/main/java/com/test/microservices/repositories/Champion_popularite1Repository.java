package com.test.microservices.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.test.microservices.pojos.Champion_popularite1;

public interface Champion_popularite1Repository extends MongoRepository<Champion_popularite1, String>{
	public Champion_popularite1 findById(int id2);
	public Boolean existsById(int id2);
	public Champion_popularite1 findByIdMongo(String idMongo);
	public Boolean existsByIdMongo(String idMongo);
	public Champion_popularite1 deleteById(int id2);
}
