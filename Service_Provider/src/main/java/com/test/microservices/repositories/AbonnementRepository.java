package com.test.microservices.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.test.microservices.pojos.Abonnement;

public interface AbonnementRepository extends MongoRepository<Abonnement, String> {
public Abonnement findById(int id2);
public Boolean existsById(int id2);
public Abonnement findByIdMongo(String idMongo);
public Boolean existsByIdMongo(String idMongo);
public Abonnement deleteById(int id2);

}
