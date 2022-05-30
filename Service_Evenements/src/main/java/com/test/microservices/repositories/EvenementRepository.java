package com.test.microservices.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.test.microservices.pojos.Evenement;

public interface EvenementRepository extends MongoRepository<Evenement, String> {
	public Evenement findById(int id2);
	public Boolean existsById(int id2);
	public Evenement findByIdMongo(String idMongo);
	public Boolean existsByIdMongo(String idMongo);
	public Evenement deleteById(int id2);
	@Query("{CategorieID:?1,CategorieageID:?0,$and:[{DateDebut:{'$gt' : ?2}},{DateDebut:{'$lte' : ?3}},{DateDebut:{'$gt': new Date()}}]    }")
	public List<Evenement> findNextEventsByCatAndAge(int age,int categorie, Date debTrimestre,Date finTrimestre);
	@Query("{CategorieageID:?0,$and:[{DateDebut:{'$gt' : ?2}},{DateDebut:{'$lte' : ?3}},{DateDebut:{'$gt': new Date()}}]    }")
	public List<Evenement> findNextEventsByAge(int age,int categorie, Date debTrimestre,Date finTrimestre);
	@Query("{CategorieID:?1,$and:[{DateDebut:{'$gt' : ?2}},{DateDebut:{'$lte' : ?3}},{DateDebut:{'$gt': new Date()}}]    }")
	public List<Evenement> findNextEventsByCat(int age,int categorie, Date debTrimestre,Date finTrimestre);
	
	@Query("{$and:[{DateDebut:{'$gt' : ?0}},{DateDebut:{'$lte' : ?1}},{DateDebut:{'$gt': new Date()}}]  }")
	public List<Evenement> findNextEventsByTrimester(Date debTrimestre,Date finTrimestre,Sort s);


}
