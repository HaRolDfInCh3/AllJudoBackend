package com.test.microservices.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data @NoArgsConstructor @AllArgsConstructor
public class LiensDto {
	private String idMongo;
	private int id;
	public String site;
	public int categorieID;
	public String présentation;
	public String eNG_presentation;
	public String paysID;
	public String departement;
	public String langue_2;
	public String langue_3;
	public String langue_4;
	public String etoiles;
	public String asavoir;
	public Boolean valide;
	public String email;
}
