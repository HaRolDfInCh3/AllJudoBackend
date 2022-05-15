package com.test.microservices.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data @NoArgsConstructor @AllArgsConstructor
public class LivreDto {
	private String idMongo;
	private int id;
	public String titre;
	public String auteur;
	public String editeur;
	public String langue;
	public String description;
	public String annee;
	public String couverture;
	public String lien;
}
