package com.test.microservices.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class Club_judokaDto {
	private String idMongo;
	public int id;
	public int id_club;
	public int id_judoka;
	public String nom_judoka;
}
