package com.test.microservices.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.test.microservices.pojos.Pari_resultat;

public interface Pari_resultatRepository extends MongoRepository<Pari_resultat, String> {
	public Pari_resultat findById(int id2);
	public Boolean existsById(int id2);
	public Pari_resultat findByIdMongo(String Id);
	public Boolean existsByIdMongo(String Id);
	public Pari_resultat deleteById(int id2);
}
