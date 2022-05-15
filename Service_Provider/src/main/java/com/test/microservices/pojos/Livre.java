package com.test.microservices.pojos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Document("livres")
@Data @NoArgsConstructor @AllArgsConstructor
public class Livre {
	@Id
	private String idMongo;
	@Field("ID")
	private int id;
	public String titre;
	public String auteur;
	public String editeur;
	public String langue;
	public String description;
	public String annee;
	public String couverture;
	public String lien;
}
