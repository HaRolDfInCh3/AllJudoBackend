package com.test.microservices.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.test.microservices.pojos.Bannieres_par_taille;

public interface Bannieres_par_tailleRepository extends MongoRepository<Bannieres_par_taille,String> {
	@Query(value="{taille:'?0'}")
    List<Bannieres_par_taille> rechercheParTaille(String taille);
    public Bannieres_par_taille findById(int id2);
    public Boolean existsById(int id2);
    public Bannieres_par_taille findByIdMongo(String idMongo);
    public Boolean existsByIdMongo(String idMongo);
    public Bannieres_par_taille deleteById(int id2);
}
