package com.test.microservices.pojos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.*;

@Document("article_img")
@Data @NoArgsConstructor @AllArgsConstructor 
public class Article_img {
	@Id
	private String idMongo;
	@Field("id")
	public int id;
	public int id_art;
	@DocumentReference
	private Article article2;
	public String nom;
}
