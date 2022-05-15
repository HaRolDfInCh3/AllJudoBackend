package com.test.microservices.pojos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("galeries")
@Data @NoArgsConstructor @AllArgsConstructor
public class Galerie {
	@Id
	private String idMongo;
	@Field("ID")
	private int id;
	public String Titre;
	public String Titre_en;
	public java.util.Date date;
	public String Photographe;
	public int Evenement_id;
	public String Admin;
	@DocumentReference
	private Admin admin2;
	@DocumentReference
	private Evenement evenement2;
	public String lien;
}
