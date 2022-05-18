package com.test.microservices.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.test.microservices.pojos.Pari_compositionElement;

public interface Pari_compositionElementRepository extends MongoRepository<Pari_compositionElement, String> {
	public Pari_compositionElement findById(int id2);
	public Boolean existsById(int id2);
	public Pari_compositionElement findByIdMongo(String Id);
	public Boolean existsByIdMongo(String Id);
	public Pari_compositionElement deleteById(int id2);
}
