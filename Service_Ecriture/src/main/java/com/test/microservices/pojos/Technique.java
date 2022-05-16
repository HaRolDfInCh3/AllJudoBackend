package com.test.microservices.pojos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("technique")
@Data @NoArgsConstructor @AllArgsConstructor
public class Technique {
	@Id
	private String idMongo;
	@Field("ID")
	public int id;
	public String Nom;
	public String Famille;
	public String Presentation;
	public String Presentation_en;
	public String Conseils;
	public String Conseils_en;
}
