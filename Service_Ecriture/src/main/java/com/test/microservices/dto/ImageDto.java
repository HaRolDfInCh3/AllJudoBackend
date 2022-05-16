package com.test.microservices.dto;



import com.test.microservices.pojos.Champion;
import com.test.microservices.pojos.Evenement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data @NoArgsConstructor @AllArgsConstructor
public class ImageDto {
	private String idMongo;
	private int id;
	public String nom;
	public int champion_id;
	public int champion2_id;
	public int evenement_id;
	public int news_id;
	public int galerie_id;
	public int technique_id;
	public String description;
	private EvenementDto evenement2;
	private ChampionDto champion2;
	private ChampionDto Champion22;
	public Boolean actif;
}
