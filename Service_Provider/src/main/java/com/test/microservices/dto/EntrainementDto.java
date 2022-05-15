package com.test.microservices.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data @NoArgsConstructor @AllArgsConstructor
public class EntrainementDto {
	private String idMongo;
	private int id;
	public String auteur;
	public String titre;
	public int langue;
	public String document;
	public String picto;
}
