package com.test.microservices.pojos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("pari")
@Data @NoArgsConstructor @AllArgsConstructor
public class Pari {
	@Id
	private String idMongo;
	@Field("id")
	public int id;
	public java.util.Date date_debut;
	public java.util.Date date_fin;
	public String description;
	public Boolean actif;
	public Boolean corrige;
	public int evenement_id;
	@DocumentReference
	private Evenement evenement2;
}
