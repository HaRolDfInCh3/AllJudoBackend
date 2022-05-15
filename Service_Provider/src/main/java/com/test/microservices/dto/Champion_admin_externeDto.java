package com.test.microservices.dto;


import com.test.microservices.enums.SituationAdminExterne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data @NoArgsConstructor @AllArgsConstructor
public class Champion_admin_externeDto {
	public String idMongo;
	public int id;
	public String nom;
	public String prenom;
	public String telephone;
	public SituationAdminExterne situation;
	public String video;
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
