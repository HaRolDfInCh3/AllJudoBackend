package com.test.microservices.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.test.microservices.pojos.Image;

public interface ImageRepository extends MongoRepository<Image, String> {
	public Image findById(int id2);
	public Boolean existsById(int id2);
	public Image findByIdMongo(String Id);
	public Boolean existsByIdMongo(String Id);
	public Image deleteById(int id2);
	@Query("{$or:[{Champion_id:?0},{Champion2_id:?0}]}")
	public List<Image>getAllImageByChampion_id(int id,Sort s);
	@Query("{Evenement_id:?0}")
	public List<Image>getAllImageByEvenement_id(int id,Sort s);
}
