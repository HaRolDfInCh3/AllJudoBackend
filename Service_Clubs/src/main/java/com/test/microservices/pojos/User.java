package com.test.microservices.pojos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("users")
@Data @NoArgsConstructor @AllArgsConstructor
public class User {
	@Id
	private String idMongo;
	@Field("id")
	public int id;
	public String nom;
	public String prenom;
	public String username;
	public String email;
	public String date_naissance;
	public java.util.Date date_naissance2;
	public String code_postale;
	public String ville;
	public String pays;
	public String grade;
	public String club;
	@DocumentReference
	private Pays pays2;
	@DocumentReference
	private Club club2;
	public String user_ip;
	public Boolean newsletter;
	public Boolean offres;
	public String gcoo1;
	public String gcoo2;
	public String gaddress;
	public String password;
	public int user_regdate;
}
