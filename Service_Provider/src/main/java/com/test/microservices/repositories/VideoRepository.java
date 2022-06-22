package com.test.microservices.repositories;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.test.microservices.pojos.Video;

public interface VideoRepository extends MongoRepository<Video, String> {
	public Video findById(int id2);
	public Boolean existsById(int id2);
	public Video findByIdMongo(String Id);
	public Boolean existsByIdMongo(String Id);
	public Video deleteById(int id2);
	@Query("{$or:[{Champion_id:?0},{Technique_id:?1},{Technique2_id:?2},{Evenement_id:?3}]}")
	public Page<Video>findSimilars(int cid,int tid,int t2id,int eid,Pageable p);
	@Query("{$or:[{Titre:{$regex:'?0'}},{Categorie:'?1'}]}")
	public List<Video>findByTitreOrCategorie(String motcle,String categorie,Sort s);
	@Query("{$and:[{Titre:{$regex:'?0'}},{Categorie:'?1'}]}")
	public List<Video>findByTitreAndCategorie(String motcle,String categorie,Sort s);
	@Query("{Evenement_id:?0}")
	public List<Video>getVideosbyEvenementID(int id,Sort s);
	@Query("{Champion_id:?0}")
	public List<Video>getVideosbyChampionID(int id,Sort s);
}
