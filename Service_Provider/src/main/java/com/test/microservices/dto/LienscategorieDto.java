package com.test.microservices.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data @NoArgsConstructor @AllArgsConstructor
public class LienscategorieDto {
	private String idMongo;
	private int id;
	public String categorie;
	public String eNG_categorie;
}
