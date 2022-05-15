package com.test.microservices.pojos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Document("liens2")
@Data @NoArgsConstructor @AllArgsConstructor
public class Liens2 {
	@Id
	private String idMongo;
	@Field("ID")
	private int id;
	public String Site;
	public int categorieID;
	public String Présentation;
	public String ENG_presentation;
	public String PaysID;
	public String Departement;
	@DocumentReference
	private Departement departement2;
	@DocumentReference
	private Lienscategorie lienscategorie2;
	@DocumentReference
	private Pays pays2;
	public String Langue_2;
	public String Langue_3;
	public String Langue_4;
	public String Etoiles;
	public String Asavoir;
	public Boolean Valide;
	public String email;
}
