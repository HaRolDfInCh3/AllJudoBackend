package com.test.microservices.dto;





import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data @NoArgsConstructor @AllArgsConstructor
public class Sous_categorieDto {
	private String idMongo;
	public int id;
	public int categorie_ID;
	public String nom_sous_categorie;
}
