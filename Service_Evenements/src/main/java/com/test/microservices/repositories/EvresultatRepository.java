package com.test.microservices.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.test.microservices.pojos.Evresultat;
import com.test.microservices.pojos.Palmares;

public interface EvresultatRepository extends MongoRepository<Evresultat, String> {
	public Evresultat findById(int id2);
	public Boolean existsById(int id2);
	public Evresultat findByIdMongo(String idMongo);
	
	public Boolean existsByIdMongo(String idMongo);
	public Evresultat deleteById(int id2);
	public Boolean existsByEvenementID(int id2);
	public List<Evresultat> findAllByEvenementID(int id2);
	public List<Evresultat> findAllByChampionID(int id2);
	//public List<Evresultat> findAllByChampionIDAndEve(int id2);
	public static final String match = "{ '$match':{ChampionID:722} }";
	public static final String project ="{'$project':{ID:1,Rang:1,PoidID:1,champion2:1,ChampionID:1,equipeID:1,enequipe: {$cond: [{$eq: ['$equipeID', 0]}, 1, 0]},evenement2:1,premier: {$cond: [{$eq: ['$Rang', '1']}, 1, 0]},troisieme: {$cond: [{$eq: ['$Rang', '3']}, 1, 0]},deuxieme: {$cond: [{$eq: ['$Rang', '2']}, 1, 0]}}}";
	
    public static final String group = "{ '$group':{_id: {'Categorieage':'$evenement2.CategorieageID','CategorieEvenement':'$evenement2.CategorieID','Equipe':'$enequipe'},total1er: {$sum: '$premier'},total2eme: {$sum: '$deuxieme'},total3eme: {$sum: '$troisieme'},championid: { $first: '$ChampionID' },nom:{ $first: '$champion2.Nom' },equipe: { $first: '$equipeID' },categorie_age:{$first: '$evenement2.evcategorieage2.Intitule'},categorie_evenement:{$first: '$evenement2.evcategorieevenement2.Intitule'}} }";
    
    @Aggregation(pipeline = { match,project,group })
    List<Palmares> aggregateBySample();
	
}
