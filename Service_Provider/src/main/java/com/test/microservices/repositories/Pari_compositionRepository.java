package com.test.microservices.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.test.microservices.pojos.Pari_composition;

public interface Pari_compositionRepository extends MongoRepository<Pari_composition, String> {
	public Pari_composition findById(int id2);
	public Boolean existsById(int id2);
	public Pari_composition findByIdMongo(String Id);
	public Boolean existsByIdMongo(String Id);
	public Pari_composition deleteById(int id2); 
	public List<Pari_composition>findByPari(int pari_id,Sort s);
}
