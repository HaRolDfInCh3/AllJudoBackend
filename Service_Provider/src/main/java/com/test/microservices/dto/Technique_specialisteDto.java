package com.test.microservices.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data @NoArgsConstructor @AllArgsConstructor
public class Technique_specialisteDto {
	private String idMongo;
	public int id;
	public int id_technique;
	public int id_champion;
	public String nom_champion;
}
