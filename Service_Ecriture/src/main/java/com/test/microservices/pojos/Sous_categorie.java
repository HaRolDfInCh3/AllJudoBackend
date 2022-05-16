package com.test.microservices.pojos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("sous_categorie")
@Data @NoArgsConstructor @AllArgsConstructor
public class Sous_categorie {
	@Id
	private String idMongo;
	@Field("ID")
	public int id;
	public int categorie_ID;
	@DocumentReference
	private Categorie categorie2;
	public String nom_sous_categorie;
}
