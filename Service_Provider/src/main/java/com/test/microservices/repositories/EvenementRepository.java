package com.test.microservices.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.test.microservices.pojos.Annee;
import com.test.microservices.pojos.Evenement;

public interface EvenementRepository extends MongoRepository<Evenement, String> {
	public Evenement findById(int id2);
	public Boolean existsById(int id2);
	public Evenement findByIdMongo(String idMongo);
	public Boolean existsByIdMongo(String idMongo);
	public Evenement deleteById(int id2);
	@Query(" {Valider: false}")
	public List<Evenement> findAllNonValide();
	@Query("{DateDebut:{'$gt': new Date()}}")
	public List<Evenement> findNextEvents();
	@Query("{DateDebut:{'$gt': new Date()}}")
	public List<Evenement> findNextEventDescs(Pageable p);
	public static final String project ="{'$project':{annee: { $year: '$DateDebut' }}}";
	
	public static final String group = "{ '$group':{_id: '$annee',annee:{$first:'$annee'}} }";
	
    public static final String sort = "{ '$sort':{ 'annee':-1} }";

    @Aggregation(pipeline = { project,group ,sort})
    List<Annee>getAllYears();
}
