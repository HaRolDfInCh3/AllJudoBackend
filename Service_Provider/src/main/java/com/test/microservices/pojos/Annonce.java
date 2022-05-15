package com.test.microservices.pojos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document("annonce")
@Data @NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
public class Annonce {
	@Id
	public String idMongo;
	@Field("ID")
	public int id;
	public int sous_categorie_ID;
	@DocumentReference
	private Sous_categorie sous_categorie2;
	@DocumentReference
	private User user2;
	public int User_ID;
	public String Titre;
	public String Descriptif;
	public java.util.Date Date_publication;
	public java.util.Date Date_modification;
	public String Nom;
	public String Mail;
	public String Code_postal;
	public String Ville;
	public String Pays;
	public String Telephone;
	public int Premium;
	public int Valide;
}
