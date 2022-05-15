package com.test.microservices.pojos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Document("technique_specialiste")
@Data @NoArgsConstructor @AllArgsConstructor
public class Technique_specialiste {
	@Id
	private String idMongo;
	@Field("id")
	public int id;
	@DocumentReference
	private Technique technique;
	@DocumentReference
	private Champion champion;
	public int id_technique;
	public int id_champion;
	public String nom_champion;
}
