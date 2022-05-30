package com.test.microservices.repositories;

import java.util.List;


import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.test.microservices.pojos.Commentaire;

public interface CommentairesRepository extends MongoRepository<Commentaire, String> {
	public Commentaire findById(int id2);
	public Boolean existsById(int id2);
	public Commentaire findByIdMongo(String idMongo);
	public Boolean existsByIdMongo(String idMongo);
	public Commentaire deleteById(int id2);
	@Query("{video_id:?0}")
	public List<Commentaire> findAllCommentsByVideoId(int videoId,Sort s);
	@Query("{user_id:?0}")
	public List<Commentaire> findAllCommentsByUserId(int userId,Sort s);
	@Query("{news_id:?0}")
	public List<Commentaire> findAllCommentsByNewsId(int newsId,Sort s);
	@Query("{user_id:?0,news_id:1}")
	public List<Commentaire> findAllCommentsByUserIdAndNewsId(int userId,int newsId,Sort s);
}
