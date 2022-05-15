package com.test.microservices.pojos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("lienscategorie")
@Data @NoArgsConstructor @AllArgsConstructor
public class Lienscategorie {
	@Id
	private String idMongo;
	@Field("ID")
	private int id;
	public String categorie;
	public String ENG_categorie;
}
