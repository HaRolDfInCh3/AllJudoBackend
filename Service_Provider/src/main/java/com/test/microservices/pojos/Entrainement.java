package com.test.microservices.pojos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("entrainement")
@Data @NoArgsConstructor @AllArgsConstructor
public class Entrainement {
	@Id
	private String idMongo;
	@Field("ID")
	private int id;
	public String Auteur;
	public String Titre;
	public int Langue;
	public String Document;
	public String Picto;
}
