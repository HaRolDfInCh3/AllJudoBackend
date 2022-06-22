package com.test.microservices.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	@Query("{Visible:true,DateDebut:{'$lt':?0}}")
	public Page<Evenement> findResultatsAnciens(Date annee, Pageable p);
	@Query("{$or:[{'evcategorieage2.Intitule':{$regex:'.*?0.*','$options' : 'i'}},{'evcategorieevenement2.Intitule':{$regex:'.*?0.*','$options' : 'i'}},{Nom:{$regex:'.*?0.*','$options' : 'i'}}]}")
	public List<Evenement> getEventsByMotCle_In_Categorie_Age_Nom(String motcle);
	@Query("{$or: [{ CategorieID: ?0},{ CategorieageID: ?1},{$and:[{DateDebut:{'$gte':?2}},{DateDebut:{'$lt':?3}}]}]}")
	public List<Evenement> findEvents_ByCategorie_Or_Age_Or_Date(int categorie,int age, Date date,Date dateplusun);
	@Query("{$and: [{ CategorieID: ?0},{ CategorieageID: ?1},{$and:[{DateDebut:{'$gte':?2}},{DateDebut:{'$lt':?3}}]}]}")
	public List<Evenement> findEventsByCategorieAndAgeAndDate(int categorie,int age, Date date,Date dateplusun);

}
