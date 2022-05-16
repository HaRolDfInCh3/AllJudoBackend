package com.test.microservices.dto;



import com.test.microservices.enums.StatusBanniere;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data @NoArgsConstructor @AllArgsConstructor
public class BannierebackgroundDto {
	public String idMongo;
	public int id;
	public String nom;
	public String code;
	public String image;
	public String url;
	public StatusBanniere actif;
	public String restriction;
	public String pubAppId;
	public String pubBanID;
}
