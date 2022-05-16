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

@Document("club_admin_externe")
@Data @NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Club_admin_externe {
	@Id
	private String idMongo;
	@Field("id")
	private int id;
	public String nom;
	public String prenom;
	public String telephone;
	public String fonction;
	public int user_id;
	public int club_id;
	@DocumentReference
	private Club club2;
	@DocumentReference
	private User user2;
	public String ip_creation;
	public String date_creation;
	public String ip_mod;
	public String date_mod;
	public byte actif;
}
