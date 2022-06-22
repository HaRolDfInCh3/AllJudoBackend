package com.test.microservices.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.test.microservices.pojos.Classement_Clubs;
import com.test.microservices.pojos.Classement_Pays;
import com.test.microservices.pojos.Evresultat;
import com.test.microservices.pojos.GroupementResultatClubs;
import com.test.microservices.pojos.GroupementResultatPays;
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
	@Query("{ChampionID:?0}")
	public List<Evresultat> findAllResultatsDesc(int id2,Sort s);
	//public List<Evresultat> findAllByChampionIDAndEve(int id2);
	public static final String match = "{ '$match':{ChampionID:?0} }";
	public static final String project ="{'$project':{ID:1,Rang:1,PoidID:1,champion2:1,ChampionID:1,equipeID:1,enequipe: {$cond: [{$eq: ['$equipeID', 0]}, 1, 0]},evenement2:1,premier: {$cond: [{$eq: ['$Rang', '1']}, 1, 0]},troisieme: {$cond: [{$eq: ['$Rang', '3']}, 1, 0]},deuxieme: {$cond: [{$eq: ['$Rang', '2']}, 1, 0]}}}";
	public static final String group = "{ '$group':{_id: {'Categorieage':'$evenement2.CategorieageID','CategorieEvenement':'$evenement2.CategorieID','Equipe':'$enequipe'},total1er: {$sum: '$premier'},total2eme: {$sum: '$deuxieme'},total3eme: {$sum: '$troisieme'},championid: { $first: '$ChampionID' },nom:{ $first: '$champion2.Nom' },equipe: { $first: '$equipeID' },categorie_age:{$first: '$evenement2.evcategorieage2.Intitule'},categorie_evenement:{$first: '$evenement2.evcategorieevenement2.Intitule'}} }";
    public static final String sort = "{ '$sort':{ 'categorie_age':-1, 'categorie_evenement':1 } }";

    @Aggregation(pipeline = { match,project,group ,sort})
    List<Palmares> aggregateBySample(int championid);
    public static final String match2 = "{ '$match':{EvenementID:?0} }";
	public static final String project2 ="{'$project':	{categorie:{$toInt:'$PoidID'},'champion':{'nom':'$champion2.Nom','pays':'$champion2.PaysID','rang':'$Rang'}}}";
	public static final String group2 = "{ '$group':{_id: '$categorie',champions: { $push: '$champion' },categoriepoids:{ $first: '$categorie' },categorieAbsolue:{$first:{'$abs':'$categorie'}}}}";
    public static final String sort2 = "{ '$sort':{ 'categorieAbsolue': 1 } }";
	@Aggregation(pipeline = {match2,project2,group2,sort2})
    List<GroupementResultatPays> getclassementChampionsParEvenementID(int id);
	
	public static final String match3 = "{ '$match':{EvenementID:?0} }";
	public static final String project3 ="{'$project':		{'Rang':1,'champion2':1,premiere: {$cond: [{$eq: ['$Rang', '1']}, 1, 0]},deuxieme: {$cond: [{$eq: ['$Rang', '2']}, 1, 0]},troiseme: {$cond: [{$eq: ['$Rang', '3']}, 1, 0]},cinquieme: {$cond: [{$eq: ['$Rang', '5']}, 1, 0]},septieme: {$cond: [{$eq: ['$Rang', '7']}, 1, 0]} }}";
	public static final String group3 = "{ '$group':	{_id: '$champion2.PaysID',pays:{'$first':'$champion2.pays2.NomPays'},drapeau:{'$first':'$champion2.pays2.Flag'},total_premiere_place: {$sum: '$premiere'},total_deuxieme_place: {$sum: '$deuxieme'},total_troisieme_place: {$sum: '$troiseme'},total_cinquieme_place: {$sum: '$cinquieme'}, total_septieme_place: { $sum: '$septieme' }} }";
	public static final String sort3 = "{ '$sort':{ total_premiere_place: -1 ,total_deuxieme_place: -1,total_troisieme_place: -1,total_cinquieme_place: -1,total_septieme_place: -1} }";
    @Aggregation(pipeline = { match3,project3,group3 ,sort3})
	List<Classement_Pays> getClassementPaysParEvenementID(int evId);
	public static final String match4 = "{ '$match':{EvenementID:?0,'champion2.Sexe':?1} }";
	public static final String project4 ="{'$project':	{'Rang':1,'champion2':1,premiere: {$cond: [{$eq: ['$Rang', '1']}, 1, 0]},deuxieme: {$cond: [{$eq: ['$Rang', '2']}, 1, 0]},troiseme: {$cond: [{$eq: ['$Rang', '3']}, 1, 0]},cinquieme: {$cond: [{$eq: ['$Rang', '5']}, 1, 0]},septieme: {$cond: [{$eq: ['$Rang', '7']}, 1, 0]} }}";
	public static final String group4 = "{ '$group': {_id: '$champion2.PaysID',pays:{'$first':'$champion2.pays2.NomPays'},drapeau:{'$first':'$champion2.pays2.Flag'},total_premiere_place: {$sum: '$premiere'},total_deuxieme_place: {$sum: '$deuxieme'},total_troisieme_place: {$sum: '$troiseme'},total_cinquieme_place: {$sum: '$cinquieme'}, total_septieme_place: { $sum: '$septieme' }} }";
    public static final String sort4 = "{ '$sort':{ total_premiere_place: -1,total_deuxieme_place: -1,total_troisieme_place: -1,total_cinquieme_place: -1,total_septieme_place: -1} }";
    @Aggregation(pipeline = { match4,project4,group4 ,sort4})
	List<Classement_Pays> getClassementPaysParEvenementIDetParSexe(int evId,String sexe);
    public static final String match5 = "{ '$match':{EvenementID:?0} }";
	public static final String project5 ="{'$project':	{'Rang':1,'equipeID':1,'evequipe2':1,premiere: {$cond: [{$eq: ['$Rang', '1']}, 1, 0]},deuxieme: {$cond: [{$eq: ['$Rang', '2']}, 1, 0]},troiseme: {$cond: [{$eq: ['$Rang', '3']}, 1, 0]},cinquieme: {$cond: [{$eq: ['$Rang', '5']}, 1, 0]},septieme: {$cond: [{$eq: ['$Rang', '7']}, 1, 0]} }	 }";
	public static final String group5 = "{ '$group':	{_id: '$equipeID',equipeid:{'$first':'$equipeID'},equipe:{'$first':'$evequipe2.NomEquipe'},total_premiere_place: {$sum: '$premiere'},total_deuxieme_place: {$sum: '$deuxieme'},total_troisieme_place: {$sum: '$troiseme'},total_cinquieme_place: {$sum: '$cinquieme'}, total_septieme_place: { $sum: '$septieme' }}  }";
	public static final String sort5 = "{ '$sort':{ total_premiere_place: -1 ,total_deuxieme_place: -1,total_troisieme_place: -1,total_cinquieme_place: -1,total_septieme_place: -1} }";
    @Aggregation(pipeline = { match5,project5,group5 ,sort5})
	List<Classement_Clubs> getClassementClubsParEvenementID(int evId);
	public static final String match6 = "{ '$match':{EvenementID:?0} }";
	public static final String project6 ="{'$project':	{'championid':'$ChampionID','equipeid':'$equipeID','nomchampion':'$champion2.Nom','equipe':'$evequipe2.NomEquipe','rang':'$Rang'} }";
	public static final String group6 = "{ '$group':   {_id: '$equipeid',champions: { $push: '$nomchampion' },championsid: { $push: '$championid' },equipe:{'$first':'$equipe'}}  }";
	public static final String project61 ="{'$project':	 {_id:1,championsid:1,champions:1, equipe:1, total:{'$size':'$championsid'}}   }";
	public static final String sort6 = "{ '$sort':{ 'total': -1 } }";
	
	@Aggregation(pipeline = {match6,project6,group6,project61,sort6})
    List<GroupementResultatClubs> getclassementChampionsParClubAndEvenementID(int id);
}
