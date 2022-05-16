package com.test.microservices.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data @NoArgsConstructor @AllArgsConstructor
public class Club_admin_externeDto {
	private String idMongo;
	private int id;
	public String nom;
	public String prenom;
	public String telephone;
	public String fonction;
	public int user_id;
	public int club_id;
	public String ip_creation;
	public String date_creation;
	public String ip_mod;
	public String date_mod;
	public byte actif;
}
