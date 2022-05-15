package com.test.microservices.dto;



import com.test.microservices.enums.Sexe;

import com.test.microservices.enums.VideoCategorie;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;


@Data @NoArgsConstructor @AllArgsConstructor
public class VideoDto {
	private String idMongo;
	public int id;
	public String titre;
	public java.util.Date date;
	public String duree;
	public String objet;
	public VideoCategorie categorie;
	public String vignette;
	public Boolean a_la_une;
	public int champion_id;
	public int technique_id;
	public int technique2_id;
	public int evenement_id;
	public String poidID;
	public Sexe sexe;
	public Boolean top_ippon;

}
