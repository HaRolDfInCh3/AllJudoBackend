package com.test.microservices.pojos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import com.test.microservices.enums.SituationAdminExterne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Document("champion_admin_externe")
@Data @NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
public class Champion_admin_externe {
	@Id
	public String idMongo;
	@Field("id")
	public int id;
	public String nom;
	public String prenom;
	public String telephone;
	public SituationAdminExterne situation;
	public String video;
	@DocumentReference
	private Champion champion2;
	@DocumentReference
	private User user2;
	public int user_id;
	public int champion_id;
	public String ip_creation;
	public java.util.Date date_mod2;
	public java.util.Date date_creation2;
	public String date_creation;
	public String ip_mod;
	public String date_mod;
	public byte actif;
}
