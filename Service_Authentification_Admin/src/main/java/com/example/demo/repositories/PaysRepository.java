package com.example.demo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.entities.*;

public interface PaysRepository extends MongoRepository<Pays, String> {
	public Pays findById(int id2);
	public Boolean existsById(int id2);
	public Pays findByIdMongo(String Id);
	public Pays findByAbreviation(String Abr);
	public Boolean existsByAbreviation(String Abr);
	public Boolean existsByIdMongo(String Id);
	public Pays deleteById(int id2);
}
