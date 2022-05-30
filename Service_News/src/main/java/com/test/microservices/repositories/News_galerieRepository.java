package com.test.microservices.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.test.microservices.pojos.News_galerie;

public interface News_galerieRepository extends MongoRepository<News_galerie, String> {
	public News_galerie findById(int id2);
	public Boolean existsById(int id2);
	public News_galerie findByIdMongo(String Id);
	public Boolean existsByIdMongo(String Id);
	public News_galerie deleteById(int id2);
	@Query("{news_id:?0}")
	public List<News_galerie>findAllPhotosByNewsId(int news_id);
}
